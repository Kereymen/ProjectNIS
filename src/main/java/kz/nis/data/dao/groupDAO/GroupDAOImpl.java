package kz.nis.data.dao.groupDAO;

import kz.nis.data.dao.studentDAO.StudentDAOImpl;
import kz.nis.model.entity.Group;
import kz.nis.model.entity.Student;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Service("jpaGroupDAO")
@Transactional
@Repository
public class GroupDAOImpl implements GroupDAO {

    private Log log = LogFactory.getLog(StudentDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Group findGroupByStudent(Student student) {
        return null;
    }


    public Group findByName(String name){
        TypedQuery<Group> query = entityManager.createNamedQuery("Group.findByName", Group.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Group save(Group group) {
        if(group.getGroupId()==null){
            log.info("Inserting new student");
            entityManager.persist(group);
        }else{
            entityManager.merge(group);
            log.info("Updating existing student");
        }
        log.info("Student saved with id " + group.getGroupId());
        return group;
    }

    @Override
    public void delete(Group group) {

    }
}
