package Tables;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "students")
@Data
public class Student
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private Integer age;
	private Date registration_date;
	
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable (name = "subscriptions", joinColumns = { @JoinColumn (name = "student_id") }, inverseJoinColumns = { @JoinColumn (name = "course_id") })
	private List<Course> courses;
	
	public String toString() {
		return id + " " + name + " " + age + " " + registration_date;
	}
}
