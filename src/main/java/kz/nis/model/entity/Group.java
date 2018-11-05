package kz.nis.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "group_")
@NamedQuery(name = "Group.findByName",
        query = "select distinct c from Group c  where c.nameGroup = :name")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", unique = true)
    private Long groupId;
    @Column(name = "name_group")
    private String nameGroup;

    @OneToMany(mappedBy = "group",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
        private Set<Student> students = new HashSet<>();

    public Group() {
    }

    public Group(String nameGroup) {
        this.nameGroup = nameGroup;
    }


    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }


    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }


    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student){
        student.setGroup(this);
        getStudents().add(student);
    }

    public void removeStudent(Student student){
        getStudents().remove(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId &&
                Objects.equals(nameGroup, group.nameGroup) &&
                Objects.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, nameGroup, students);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", nameGroup='" + nameGroup + '\'' +
                ", students=" + students +
                '}';
    }
}
