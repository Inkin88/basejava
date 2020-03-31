package webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface BlockOfCode<T> {

    T execute(PreparedStatement preparedStatement) throws SQLException;
}
