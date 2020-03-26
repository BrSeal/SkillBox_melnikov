    package Tables;

    import javax.persistence.*;
    import java.util.Date;

    @Entity

    @Table(name = "students")
    public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        String name;
        int age;
        Date registration_date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Date getRegistration_date() {
            return registration_date;
        }

        public void setRegistration_date(Date registration_date) {
            this.registration_date = registration_date;
        }


    }
