package kz.nis.data.dao.studentDAO;

import kz.nis.model.entity.Student;

import java.time.LocalDate;
import java.util.List;

public interface StudentDAO {
    List<Student> findAll();
    Student findById(long id);
    List<Student> findByLessonViziting(LocalDate dayOne, LocalDate dayTwo);
    Student save(Student student);
    void delete(Student student);

}
