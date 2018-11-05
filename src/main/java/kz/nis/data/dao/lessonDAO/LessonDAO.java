package kz.nis.data.dao.lessonDAO;

import kz.nis.model.entity.Lesson;

public interface LessonDAO {
    Lesson save(Lesson lesson);
    Lesson findByName(Long id);
    void delete(Lesson lesson);
}
