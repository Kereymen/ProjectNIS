package kz.nis.data.dao.lessonDAO;

import kz.nis.model.entity.Group;
import kz.nis.model.entity.Lesson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Service("jpaLessonDAO")
@Transactional
@Repository
public class LessonDAOImpl implements LessonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private Log log = LogFactory.getLog(LessonDAOImpl.class);

    @Override
    public Lesson save(Lesson lesson) {
        if(lesson.getLessonId()==null){
            log.info("Inserting new subject");
            entityManager.persist(lesson);
        }else{
            entityManager.merge(lesson);
            log.info("Updating existing subject");
        }
        log.info("Student saved with id " + lesson.getLessonId());
        return lesson;
    }

    @Override
    public Lesson findByName(Long id) {
        TypedQuery<Lesson> query = entityManager.createNamedQuery("Lesson.findByName", Lesson.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(Lesson lesson) {

    }
}
