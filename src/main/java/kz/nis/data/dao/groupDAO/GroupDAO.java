package kz.nis.data.dao.groupDAO;

import kz.nis.model.entity.Group;
import kz.nis.model.entity.Student;
import org.hibernate.tool.schema.internal.StandardUniqueKeyExporter;

import java.util.List;

public interface GroupDAO {
    Group findGroupByStudent(Student student);
    Group findByName(String name);
    Group save(Group group);
    void delete(Group group);

}
