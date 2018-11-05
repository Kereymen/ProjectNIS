package kz.nis.data.dao.studentDAO;

import kz.nis.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service("jpaStudentDAO")
@Transactional
@Repository
public class StudentDAOImpl implements StudentDAO {

    private Log log = LogFactory.getLog(StudentDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public List<Student> findAll() {
        List<Student> students = entityManager.createNamedQuery("Student.findAll",Student.class).getResultList();
        return students;
    }

    @Transactional
    @Override
    public Student findById(long id) {
        TypedQuery<Student> query = entityManager.createNamedQuery("Student.findById",Student.class);
        query.setParameter("id",id);
        return query.getSingleResult();

    }

    @Override
    public List<Student> findByLessonViziting(LocalDate dayOne, LocalDate dayTwo) {
        return null;
    }

    @Override
    public Student save(Student student) {
        if(student.getStudentID()==null){
            log.info("Inserting new student");
            entityManager.persist(student);
        }else{
            entityManager.merge(student);
            log.info("Updating existing student");
        }
        log.info("Student saved with id " + student.getStudentID());
        return student;
    }

    @Override
    public void delete(Student student) {

    }
}
