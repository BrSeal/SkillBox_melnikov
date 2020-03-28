package Tables;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "courses")
@Data
public class Course
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated (EnumType.STRING)
	@Column (columnDefinition = "enum")
	private CourseType type;
	
	private String name;
	private Integer duration;
	private String description;
	
	@ManyToOne (cascade = CascadeType.ALL)
	private Teacher teacher;
	
	private Integer students_count;
	private Integer price;
	private float price_per_hour;
	
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable (name = "subscriptions", joinColumns = { @JoinColumn (name = "course_id") }, inverseJoinColumns = { @JoinColumn (name = "student_id") })
	private List<Student> students;
	
	public String toString() {
		return String.format("%d %s %d", id, name, price);
	}
}