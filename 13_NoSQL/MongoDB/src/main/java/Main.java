import au.com.bytecode.opencsv.CSVReader;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    private static final File CSV = new File("src/main/resources/mongo.csv");
    private static final String DB_NAME = "test";
    private static final MongoDatabase repository = MongoClients.create().getDatabase(DB_NAME);
    private static final MongoCollection<Document> students = repository.getCollection("students");


    public static void main(String[] args) throws IOException {
        loadCSV();

        //общее количество студентов в базе.
        countStudents();

        //количество студентов старше 40 лет
        countOlderThan40();

        //имя самого молодого студента.
        getNameYoungest();

        //список курсов самого старого студента.
        getCoursesOldest();
    }

    private static void loadCSV() throws IOException {
        if (students.countDocuments() != 0) {
            System.out.println("DB renewed!");
            students.drop();
        }
        CSVReader reader = new CSVReader(new FileReader(CSV));
        List<String[]> allRows = reader.readAll();

        for (String[] row : allRows) {
            Document student = new Document()
                    .append("name", row[0])
                    .append("age", row[1])
                    .append("courses", row[2]);
            students.insertOne(student);
        }
    }

    private static void countStudents() {
        long countStudents = students.countDocuments();
        System.out.printf("There are %d students in total\n", countStudents);
    }

    private static void countOlderThan40() {
        BsonDocument query = BsonDocument.parse("{age:{$gt:\"40\"}}");
        long count = students.countDocuments(query);
        System.out.printf("There are %d students older than 40\n", count);
    }

    private static void getNameYoungest() {
        BsonDocument age = BsonDocument.parse("{age:1}");
        Document youngest = students.find().sort(age).first();
        System.out.printf("The name of youngest student is %s\n", youngest.getString("name"));
    }

    private static void getCoursesOldest() {
        BsonDocument age = BsonDocument.parse("{age:-1}");
        Document oldest = students.find().sort(age).first();
        System.out.printf("Courses of oldest student is %s", oldest.getString("courses"));
    }
}
