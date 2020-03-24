import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JDBCexpTest
{
	String user="root";
	String pass="root";
	String url="jdbc:mysql://localhost:3306/skillbox?serverTimezone=UTC";
	String query="select * from courses";
	@Test
	void query() throws SQLException {
		JDBCexp db=new JDBCexp(url,user,pass);
		ResultSet result=db.query(query);
		while (result.next()){
			System.out.println(result.getString("name"));
		}
		db.close();
	}
}