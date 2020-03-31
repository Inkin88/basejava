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

}
