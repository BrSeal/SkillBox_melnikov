package Tables.PrimaryKeys;

import lombok.Data;

import java.io.Serializable;

@Data
public class PurchaseListPK implements Serializable
{
	private String student_name;
	private String course_name;
	
	public PurchaseListPK() {
	}
	
	public PurchaseListPK(String student_name, String course_name) {
		this.student_name = student_name;
		this.course_name = course_name;
	}
}