package Tables;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "teachers")
@Data
public class Teacher
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer age;
	private Integer salary;
	private String name;
	
	@OneToMany (targetEntity = Course.class, mappedBy = "teacher", cascade = CascadeType.ALL)
	private List<Course> courses;
	
	public String toString() {
		return id + " " + name + " " + age + " " + salary;
	}
}
