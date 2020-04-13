package webapp.storage;

import webapp.exception.NotExistStorageException;
import webapp.model.ContactsType;
import webapp.model.Resume;
import webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {

    private SqlHelper sqlHelper;

    public SqlStorage(String dburl, String dbuser, String dbpassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dburl, dbuser, dbpassword));
    }

    @Override
    public void clear() {
        sqlHelper.executeQuery("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteContact(conn, resume);
            putContact(conn, resume);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume(uuid, full_name) VALUES (?, ?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            putContact(conn, resume);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.executeQuery("SELECT * FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid WHERE r.uuid =?",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContact(r, rs);
                    } while (rs.next());
                    return r;
                });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.executeQuery("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.executeQuery("SELECT * FROM resume LEFT JOIN contact c ON resume.uuid = c.resume_uuid ORDER BY full_name, uuid", ps -> {
            Map<String, Resume> resumeMap = new LinkedHashMap<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                resumeMap.computeIfAbsent(uuid, v -> {
                    try {
                        v = rs.getString("full_name");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return new Resume(uuid, v);
                });
                addContact(resumeMap.get(uuid), rs);
            }
            return new ArrayList<>(resumeMap.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.executeQuery("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        });
    }

    private void putContact(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact(resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactsType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContact(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void addContact(Resume resume, ResultSet rs) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            ContactsType contactsType = ContactsType.valueOf(rs.getString("type"));
            resume.addContact(contactsType, value);
        }
    }
}
