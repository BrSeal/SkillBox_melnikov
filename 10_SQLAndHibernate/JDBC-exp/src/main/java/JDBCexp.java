import java.sql.*;

public class JDBCexp {
    private Statement statement;
    private Connection connection;
    private ResultSet resultSet;

    public JDBCexp(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
    }

    public ResultSet query(String sqlQuery) throws SQLException {

        return resultSet = statement.executeQuery(sqlQuery);
    }

    public void close() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }
}
