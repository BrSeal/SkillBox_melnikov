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