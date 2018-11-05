package kz.nis.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="teacher")
@NamedQuery(name = "Teacher.findByName", query = "select c from Teacher c where c.teacherId = :id")
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "teacher_surname")
    private String teaherSurname;

    @Column(name="teacher_patronymic")
    private String teacherPatronymic;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<Lesson> lessonSet = new HashSet<>();

    public Teacher() {
    }

    public Teacher(String teachername, String teacherSurname, String teacherPatronymic) {
        this.teacherName = teachername;
        this.teaherSurname = teacherSurname;
        this.teacherPatronymic = teacherPatronymic;
    }


    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }


    public String getTeaherSurname() {
        return teaherSurname;
    }

    public void setTeaherSurname(String teaherSurname) {
        this.teaherSurname = teaherSurname;
    }


    public String getTeacherPatronymic() {
        return teacherPatronymic;
    }

    public void setTeacherPatronymic(String teacherPatronymic) {
        this.teacherPatronymic = teacherPatronymic;
    }


    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lessonSet) {
        this.lessonSet = lessonSet;
    }
    public void addLesson(Lesson lesson){
        lesson.setTeacher(this);
        getLessonSet().add(lesson);
    }

    public void removeLesson(Lesson lesson){
        getLessonSet().remove(lesson);
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teaherSurname='" + teaherSurname + '\'' +
                ", teacherPatronymic='" + teacherPatronymic + '\'' +
                '}';
    }
}
