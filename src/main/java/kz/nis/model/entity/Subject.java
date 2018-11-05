package kz.nis.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "subject")
@NamedQuery(name = "Subject.findByName",
        query = "select distinct c from Subject c  where c.subjectName = :name")
public class Subject implements Serializable {

    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @Column(name = "subject_name")
    private String subjectName;


    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Teacher> teacherSet = new HashSet<>();


    @OneToMany(mappedBy = "subjectSet", cascade = CascadeType.ALL)
    private Set<Lesson> lessonSet;

    public Subject() {
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;

    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    public Set<Teacher> getTeacherSet() {  return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacher) {
        this.teacherSet = teacher;
    }

    public void addTeacher(Teacher teacher){
       teacher.setSubject(this);
        getTeacherSet().add(teacher);
    }

    public void removeTeacher(Teacher teacher){
        getTeacherSet().remove(teacher);
    }


    public Set<Lesson> getLessonSet() {  return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lessonSet) {
        this.lessonSet =lessonSet;
    }

    public void addLesson(Lesson lesson){
        lesson.setSubjectSet(this);
        getLessonSet().add(lesson);
    }

    public void removeLesson(Lesson lesson){
        getLessonSet().remove(lesson);
    }



    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                "}";
    }
}
