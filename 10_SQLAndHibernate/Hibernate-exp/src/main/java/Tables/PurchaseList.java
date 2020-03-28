package Tables;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
public class PurchaseList
{
	private String student_name;
	private String course_name;
	private Integer price;
	private Date subscription_date;
}
