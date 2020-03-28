package Tables;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompositeKey implements Serializable
{
	private Integer student_id;
	private Integer course_id;
	
	public CompositeKey() {
	}
	
	public CompositeKey(Integer student_id, Integer course_id) {
		this.student_id = student_id;
		this.course_id = course_id;
	}
}