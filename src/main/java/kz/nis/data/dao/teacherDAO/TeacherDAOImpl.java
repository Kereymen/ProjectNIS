package kz.nis.data.dao.teacherDAO;

import kz.nis.model.entity.Lesson;
import kz.nis.model.entity.Teacher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Service("jpaTeacherDAO")
@Transactional
@Repository
public class TeacherDAOImpl implements TeacherDAO {

    private Log log = LogFactory.getLog(TeacherDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Teacher findGroupByStudent(Teacher teacher) {
        return null;
    }

    @Override
    public Teacher findByName(Long id) {

        TypedQuery<Teacher> query = entityManager.createNamedQuery("Teacher.findByName", Teacher.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Teacher save(Teacher teacher) {
        if(teacher.getTeacherId()==null){
            log.info("Inserting new subject");
            entityManager.persist(teacher);
        }else{
            entityManager.merge(teacher);
            log.info("Updating existing subject");
        }
        log.info("Student saved with id " + teacher.getTeacherId());
        return teacher;
    }

    @Override
    public void delete(Teacher teacher) {

    }
}
