package webapp.sql;

import webapp.exception.ExistStorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public  <T> T executeQuery(String sqlQuery, BlockOfCode <T> block) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
             return block.execute(ps);
        } catch (SQLException e) {
            throw new ExistStorageException(e.getMessage());
        }
    }

    public <T> T transactionalExecute(SqlTransaction<T> executor) {
        try (Connection conn = connectionFactory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T res = executor.execute(conn);
                conn.commit();
                return res;
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new ExistStorageException(e.getMessage());
        }
    }

}
