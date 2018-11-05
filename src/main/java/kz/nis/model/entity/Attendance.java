package kz.nis.model.entity;



import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attend_id")
    private Long attendID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column(name = "is_present")
    private Boolean isPresent;

    @Column(name = "date_attend")
    private LocalDate localDate;

    public Attendance() {}

    public Long getAttendID() {
        return attendID;
    }

    public void setAttendID(Long attendID) {
        this.attendID = attendID;
    }


    public Boolean isPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }



    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}

