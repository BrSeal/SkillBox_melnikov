package Tables;

import Tables.PrimaryKeys.PurchaseListPK;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;

@Entity
@IdClass (PurchaseListPK.class)
@Data
public class PurchaseList
{
	@Id
	private String student_name;
	@Id
	private String course_name;
	private Integer price;
	private Date subscription_date;
}
