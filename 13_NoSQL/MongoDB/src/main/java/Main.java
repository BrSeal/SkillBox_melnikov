import au.com.bytecode.opencsv.CSVReader;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final File CSV = new File("src/main/resources/mongo.csv");
    private static final String DB_NAME = "test";

    private static final MongoDatabase db = MongoClients.create().getDatabase(DB_NAME).withCodecRegistry(
            CodecRegistries.fromRegistries(
                    MongoClient.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider
                            .builder()
                            .automatic(true)
                            .build()))
    );
    private static final MongoCollection<Student> students = db.getCollection("students", Student.class);


    public static void main(String[] args) throws IOException {
        loadCSV();

        //общее количество студентов в базе.
        countStudents();

        //количество студентов старше 40 лет
        countOlderThan40();

        //имя самого молодого студента.
        getNamesYoungest();

        //список курсов самого старого студента.
        getCoursesOldest();
    }

    private static void loadCSV() throws IOException {
        if (students.countDocuments() != 0) {
            System.out.println("DB renewed!");
            students.drop();
        }
        CSVReader reader = new CSVReader(new FileReader(CSV));
        List<Student> studentList = reader.readAll()
                .stream()
                .map(row -> {
                    String name = row[0];
                    int age = Integer.parseInt(row[1]);
                    String[] courses = row[2].replaceAll("\"", "").split(",");
                    return new Student(name, age, Arrays.asList(courses));
                })
                .collect(Collectors.toList());

        students.insertMany(studentList);
    }

    private static void countStudents() {
        long countStudents = students.countDocuments();
        System.out.printf("There are %d students in total\n", countStudents);
        System.out.println("======================");
    }

    private static void countOlderThan40() {
        long count = students.countDocuments(Filters.gt("age", 40));
        System.out.printf("There are %d students older than 40\n", count);
        System.out.println("======================");
    }

    private static void getNamesYoungest() {
        Iterable<Student> youngest = students.find().sort(new BasicDBObject("age", 1));
        System.out.println("Youngest students:");
        int minAge = youngest.iterator().next().getAge();
        for (Student student : youngest) {
            if (student.getAge() != minAge) break;
            System.out.println(student.getName());
        }
        System.out.println("======================");
    }

    private static void getCoursesOldest() {
        Iterable<Student> youngest = students.find().sort(new BasicDBObject("age", -1));
        System.out.println("Oldest students:");
        int maxAge = youngest.iterator().next().getAge();
        for (Student student : youngest) {
            if (student.getAge() != maxAge) break;
            System.out.printf("%s %s\n", student.getName(), student.getCourses());
        }
        System.out.println("======================");
    }
}
