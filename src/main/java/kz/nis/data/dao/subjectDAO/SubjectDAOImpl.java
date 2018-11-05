package kz.nis.data.dao.subjectDAO;

import kz.nis.model.entity.Group;
import kz.nis.model.entity.Subject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Service("jpaSubjectDAO")
@Transactional
@Repository
public class SubjectDAOImpl implements SubjectDAO {
    private Log log = LogFactory.getLog(SubjectDAOImpl.class);

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Subject findByName(String name) {
        TypedQuery<Subject> query = entityManager.createNamedQuery("Subject.findByName", Subject.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Subject save(Subject subject) {
        if(subject.getSubjectId()==null){
            log.info("Inserting new subject");
            entityManager.persist(subject);
        }else{
            entityManager.merge(subject);
            log.info("Updating existing subject");
        }
        log.info("Student saved with id " + subject.getSubjectId());
        return subject;
    }
}
