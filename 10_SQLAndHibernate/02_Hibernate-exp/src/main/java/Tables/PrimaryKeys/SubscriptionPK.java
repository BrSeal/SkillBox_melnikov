package Tables.PrimaryKeys;

import Tables.Course;
import Tables.Student;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
public class SubscriptionPK implements Serializable
{
	@OneToOne
	private Student student;
	@OneToOne
	private Course course;
	
	public SubscriptionPK() {
	}
	
	public SubscriptionPK(Student student, Course course) {
		this.student = student;
		this.course = course;
	}
}