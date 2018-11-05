package kz.nis.data.dao.attendDAO;

import kz.nis.model.entity.Attendance;
import kz.nis.model.entity.Group;
import kz.nis.model.entity.Student;

public interface AttendanceDAO {
    Attendance findByid(long id);
    Attendance save(Attendance attendance);
    void delete(Attendance attendance);
}
