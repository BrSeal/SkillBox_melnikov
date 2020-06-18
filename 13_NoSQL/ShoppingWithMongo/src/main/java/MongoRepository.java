import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.ArrayList;

public class MongoRepository {
    private static final String SHOP_EXISTS_ERR = "Shop already exists";
    private static final String ITEM_EXISTS_ERR = "Item already exists";
    private static final String NPE_ITEM = "No such item";
    private static final String NPE_SHOP = "No such shop";

    private static final String DB_NAME = "shops";

    private static final MongoDatabase repository = MongoClients.create().getDatabase(DB_NAME);
    private static final MongoCollection<Document> items = repository.getCollection("items");
    private static final MongoCollection<Document> shops = repository.getCollection("shops");

    public void addShop(String name) {
        if (shopExists(name)) throw new IllegalArgumentException(SHOP_EXISTS_ERR);
        Document shop = new Document()
                .append("_id", name)
                .append("items", new ArrayList<Integer>());

        shops.insertOne(shop);
    }

    public void addItem(String itemInfo) {
        String[] info = itemInfo.split(" ");
        if (itemExists(info[0])) throw new IllegalArgumentException(ITEM_EXISTS_ERR);

        Document item = new Document()
                .append("_id", info[0])
                .append("price", info[1]);
        items.insertOne(item);
    }

    public void goodToShop(String itemToShop) {
        String[] data = itemToShop.split(" ");
        String item = data[0];
        String shop = data[1];

        if (!itemExists(item)) throw new NullPointerException(NPE_ITEM);
        if (!shopExists(shop)) throw new NullPointerException(NPE_SHOP);

        shops.updateOne(new Document("_id", shop), new Document("$push", new Document("items", item)));
    }

    /**
     * ToDo  Команда должна выводить для каждого магазина
     * общее количество товаров,
     * среднюю цену товара,
     * самый дорогой и самый дешевый товар,
     * количество товаров дешевле 100 рублей.
     */
    public void report() {
        //общее количество товаров для магазина
    }

    public void removeItem(String item) {
        if (!itemExists(item)) throw new NullPointerException(NPE_ITEM);

        BsonDocument query = BsonDocument.parse("{_id:\"" + item + "\"");
        items.deleteOne(query);
    }

    public void removeShop(String shop) {
        if (!shopExists(shop)) throw new NullPointerException(NPE_SHOP);

        BsonDocument query = BsonDocument.parse("{_id:\"" + shop + "\"");
        shops.deleteOne(query);
    }

    private boolean shopExists(String name) {
        BsonDocument query = BsonDocument.parse("{_id:\"" + name + "\"");
        return shops.countDocuments(query) != 0;
    }

    private boolean itemExists(String name) {
        BsonDocument query = BsonDocument.parse("{_id:\"" + name + "\"");
        return items.countDocuments(query) != 0;
    }
}
