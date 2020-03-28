package Tables.PrimaryKeys;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubscriptionPK implements Serializable
{
	private Integer student_id;
	private Integer course_id;
	
	public SubscriptionPK() {
	}
	
	public SubscriptionPK(Integer student_id, Integer course_id) {
		this.student_id = student_id;
		this.course_id = course_id;
	}
}