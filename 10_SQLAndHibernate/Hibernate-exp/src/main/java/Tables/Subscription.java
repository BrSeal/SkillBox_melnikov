package Tables;

import Tables.PrimaryKeys.SubscriptionPK;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table (name = "subscriptions")
@Data
public class Subscription
{
	@EmbeddedId
	private SubscriptionPK key;
	private Date subscription_date;
	
	public String toString(){
		return key.getStudent().getName()+" "
				+key.getCourse().getName()+" "
				+subscription_date;
	}
}
