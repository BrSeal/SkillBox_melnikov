import Exceptions.AlreadyExistsException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.ArrayList;

public class MongoRepository {
    private static final String SHOP_EXISTS_ERR = "Shop already exists";
    private static final String ITEM_EXISTS_ERR = "Item already exists";
    private static final String DB_NAME = "shops";

    private static final MongoDatabase repository = MongoClients.create().getDatabase(DB_NAME);
    private static final MongoCollection<Document> items = repository.getCollection("items");
    private static final MongoCollection<Document> shops = repository.getCollection("shops");

    public void addShop(String name) {
        BsonDocument query = BsonDocument.parse("{name:\"" + name + "\"");
        if (shops.countDocuments(query) != 0) throw new AlreadyExistsException(SHOP_EXISTS_ERR);

        Document shop = new Document()
                .append("name", name)
                .append("items", new ArrayList<Integer>());

        shops.insertOne(shop);
    }

    public void addItem(String itemInfo) {
        String[] info = itemInfo.split(" ");

        BsonDocument query = BsonDocument.parse("{name:\"" + info[0] + "\"");
        if (items.countDocuments(query) != 0) throw new AlreadyExistsException(ITEM_EXISTS_ERR);

        Document item = new Document()
                .append("name", info[0])
                .append("price", info[1]);
        items.insertOne(item);
    }

    public void goodToShop(String goodAndShop) {

    }

    public void report() {

    }


}
