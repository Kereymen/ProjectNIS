package kz.nis;

import kz.nis.data.dao.attendDAO.AttendanceDAO;
import kz.nis.data.dao.attendDAO.AttendanceDAOImpl;
import kz.nis.data.dao.groupDAO.GroupDAO;
import kz.nis.data.dao.groupDAO.GroupDAOImpl;
import kz.nis.data.dao.lessonDAO.LessonDAO;
import kz.nis.data.dao.lessonDAO.LessonDAOImpl;
import kz.nis.data.dao.studentDAO.StudentDAO;
import kz.nis.data.dao.studentDAO.StudentDAOImpl;
import kz.nis.data.dao.subjectDAO.SubjectDAO;
import kz.nis.data.dao.subjectDAO.SubjectDAOImpl;
import kz.nis.data.dao.teacherDAO.TeacherDAO;
import kz.nis.data.dao.teacherDAO.TeacherDAOImpl;
import kz.nis.model.entity.*;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:META-INF/app-context-annotation.xml");
        context.refresh();
        StudentDAO studentDAO = context.getBean("jpaStudentDAO", StudentDAOImpl.class);
        GroupDAO groupDAO = context.getBean("jpaGroupDAO", GroupDAOImpl.class);
        SubjectDAO subjectDAO = context.getBean("jpaSubjectDAO", SubjectDAOImpl.class);
        LessonDAO lessonDAO = context.getBean("jpaLessonDAO", LessonDAOImpl.class);
        TeacherDAO teacherDAO = context.getBean("jpaTeacherDAO", TeacherDAOImpl.class);
        AttendanceDAO attendDAO = context.getBean("jpaAttendanceDAO", AttendanceDAOImpl.class);
        Lesson lesson = lessonDAO.findByName(4L);
        lesson.setSubjectSet(teacherDAO.findByName(2L).getSubject());
        lessonDAO.save(lesson);
    }
}
