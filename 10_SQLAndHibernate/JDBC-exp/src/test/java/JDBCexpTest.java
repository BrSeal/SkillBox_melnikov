import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JDBCexpTest {
    static String user = "root";
    static String pass = "root";
    static String url = "jdbc:mysql://localhost:3306/skillbox?serverTimezone=UTC";

    private JDBCexp db;
    String query;

    @BeforeEach
    void setUp() throws Exception {
        db = new JDBCexp(url, user, pass);
    }

    @Test
    void helloWorldQuery()  throws SQLException{
        query = "SELECT 'hello world'";
		ResultSet queryResult = db.query(query);
		queryResult.next();
		assertEquals(queryResult.getString("hello world"), "hello world");
    }

    @Test
    void taskQuery() throws SQLException {
		/*
		Написать код, который выведет для каждого курса среднее количество покупок в месяц.
		Лучше только средствами SQL (вариант “со звёздочкой”),
		но группировку по месяцам можно реализовать и с помощью Java.
		 */
        query = "SELECT DISTINCT course_name," +
                "COUNT(subscription_date)/ COUNT(MONTH( subscription_date)) AS avg_per_month\n" +
                "FROM purchaselist\n" +
                "GROUP BY course_name\n" +
                "ORDER BY course_name";

        ResultSet result = db.query(query);
        while (result.next()) {
            System.out.println(result.getString("course_name") +
                    result.getString("avg_per_month"));
        }
        db.close();
    }
}