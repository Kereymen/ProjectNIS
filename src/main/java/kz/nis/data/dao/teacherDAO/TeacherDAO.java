package kz.nis.data.dao.teacherDAO;

import kz.nis.model.entity.Teacher;

public interface TeacherDAO {
    Teacher findGroupByStudent(Teacher teacher);
    Teacher findByName(Long id);
    Teacher save(Teacher teacher);
    void delete(Teacher teacher);
}
