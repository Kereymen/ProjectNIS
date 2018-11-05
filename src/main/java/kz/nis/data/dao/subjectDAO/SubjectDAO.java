package kz.nis.data.dao.subjectDAO;

import kz.nis.model.entity.Subject;

public interface SubjectDAO {
    Subject findByName(String name);
    Subject save(Subject subject);
}
