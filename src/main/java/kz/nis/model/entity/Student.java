package kz.nis.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "student")
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select c from Student c"),
        @NamedQuery(name = "Student.findById",
                query = "select distinct c from Student c " +
                        "left join fetch c.group t left join fetch c.lessonSet h " +
                        " where c.studentID = :id"),
        @NamedQuery(name = "Student.findAllWithDetalis",
                query = "select distinct c from Student c left join fetch c.group t left join fetch c.lessonSet h")

})

public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "student_id", unique = true)
    private Long studentID;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "studentSet")
    private  Set<Lesson> lessonSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Attendance> attendances = new HashSet<>();

    public Student() {}

    public Student(String surname,String firstname, String patronymic) {
        this.firstname = firstname;
        this.surname = surname;
        this.patronymic = patronymic;

    }


    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }


    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lesson) {
        this.lessonSet = lesson;
    }

    public void addLesson(Lesson lesson){
        getLessonSet().add(lesson);
    }
    public void removeLesson(Lesson lesson){
        getLessonSet().remove(lesson);
    }

   public Set<Attendance> getAttendances() { return attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void addAttendances(Attendance attendance){
        attendance.setStudent(this);
        getAttendances().add(attendance);
    }

    public void removeAttedances(Attendance attendance){
        getAttendances().remove(attendance);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentID == student.studentID &&
                Objects.equals(firstname, student.firstname) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(patronymic, student.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, firstname, surname, patronymic);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +

                '}';
    }
}
