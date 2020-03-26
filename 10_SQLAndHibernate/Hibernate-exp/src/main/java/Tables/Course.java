package Tables;


import javax.persistence.*;

@Entity

@Table(name = "courses")
public class Course {

    /*
        'id                  ', 'int unsigned', 'NO', 'PRI', NULL, 'auto_increment'
        'name           ', 'varchar(500)', 'YES', '', NULL, ''
        'duration           ', 'int unsigned', 'YES', '', NULL, ''
        'type           ', 'enum(\'DESIGN\',\'PROGRAMMING\',\'MARKETING\',\'MANAGEMENT\',\'BUSINESS\')', 'NO', '', NULL, ''
        'description        ', 'varchar(500)', 'YES', '', NULL, ''
        'teacher_id         ', 'int unsigned', 'YES', 'MUL', NULL, ''
        'students_count         ', 'int unsigned', 'YES', '', NULL, ''
        'price                  ', 'int unsigned', 'YES', '', NULL, ''
        'price_per_hour         ', 'float', 'YES', '', NULL, ''

    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getStudents_count() {
        return students_count;
    }

    public void setStudents_count(int students_count) {
        this.students_count = students_count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(float price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    @Column(name = "duration")
    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    private String description;

    @Column(name = "teacher_id")
    private int teacher_id;

    @Column(name = "students_count")
    private int students_count;

    @Column(name = "price")
    private int price;

    @Column(name = "price_per_hour")
    private float price_per_hour;


}