package Tables.PrimaryKeys;

import Tables.Course;
import Tables.Student;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Data
public class LinkedPurchaseListPK implements Serializable
{
    @OneToOne
    private Student student;
    @OneToOne
    private Course course;
    
    public LinkedPurchaseListPK() {
    }
    
   public LinkedPurchaseListPK(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
}
