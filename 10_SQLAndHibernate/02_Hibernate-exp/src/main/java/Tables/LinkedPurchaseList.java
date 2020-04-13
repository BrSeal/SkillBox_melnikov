package Tables;

import Tables.PrimaryKeys.LinkedPurchaseListPK;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class LinkedPurchaseList
{
	@EmbeddedId
	LinkedPurchaseListPK key;
	private Integer price;
	private Date subscription_date;
	
	public LinkedPurchaseList() {
	}
	
	public LinkedPurchaseList(LinkedPurchaseListPK key, Integer price, Date subscription_date) {
		this.key = key;
		this.price = price;
		this.subscription_date = subscription_date;
	}
	
	public String toString(){
		return key.getStudent().getName()+" "+key.getCourse().getName()+" "+subscription_date;
	}
}
