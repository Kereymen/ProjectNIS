package kz.nis.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lesson")
@NamedQuery(name = "Lesson.findByName",
        query = "select distinct c from Lesson c  where c.lessonId = :id")
public class Lesson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id", unique = true)
    private Long lessonId;

    @Column(name = "begin_lesson")
    private LocalTime beginLesson;

    @Column(name = "end_lesson")
    private LocalTime endLesson;

    @Column(name = "date_lesson")
    private LocalDate date;

    @Column(name = "lesson_type")
    @Enumerated(EnumType.STRING)
    private LessonType lessonType;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private Set<Attendance> attendances = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name ="student_to_lesson",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> studentSet = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subjectSet;

    public Lesson() {}

    public Lesson(LessonType lessonType) {
        this.lessonType = lessonType;
    }


    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }


    public LocalTime getBeginLesson() {
        return beginLesson;
    }

    public void setBeginLesson(LocalTime beginLesson) {
        this.beginLesson = beginLesson;
    }


    public LocalTime getEndLesson() {
        return endLesson;
    }

    public void setEndLesson(LocalTime endLesson) {
        this.endLesson = endLesson;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }


    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public void addStudent(Student student){
        getStudentSet().add(student);
    }
    public void removeStudentn(Student student){
        getStudentSet().remove(student);
    }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public Subject getSubjectSet() {
        return subjectSet;
    }

    public void setSubjectSet(Subject subject) {
        this.subjectSet = subject;
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void addAttend(Attendance attendance){
        attendance.setLesson(this);
        getAttendances().add(attendance);
    }

    public void removeAttend(Attendance attendance){
        getAttendances().remove(attendance);
    }
}
