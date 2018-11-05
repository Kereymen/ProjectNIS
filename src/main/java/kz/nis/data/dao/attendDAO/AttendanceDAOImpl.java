package kz.nis.data.dao.attendDAO;

import kz.nis.model.entity.Attendance;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Service("jpaAttendanceDAO")
@Repository
@Transactional
public class AttendanceDAOImpl implements AttendanceDAO {
    private Log log = LogFactory.getLog(Attendance.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Attendance findByid(long id) {
        TypedQuery<Attendance> query = entityManager.createNamedQuery("Attend.findById",Attendance.class);
        query.setParameter("id", id);
        return  query.getSingleResult();
    }

    @Override
    public Attendance save(Attendance attendance) {
         if(attendance.getAttendID()==null){
             log.info("Inserting new attend");
             entityManager.persist(attendance);
         } else { entityManager.merge(attendance);
             log.info("Updating existing attend");
         } log.info("Attend saved with id " + attendance.getAttendID());
        return attendance;
    }

    @Override
    public void delete(Attendance attendance) {

    }
}
