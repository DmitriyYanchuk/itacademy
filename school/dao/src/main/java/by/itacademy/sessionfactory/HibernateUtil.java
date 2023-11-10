package by.itacademy.sessionfactory;

import by.itacademy.education.*;
import by.itacademy.persons.*;
import by.itacademy.place.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static SessionFactory createSessionFactory() {
        try {
            final Configuration configuration = new Configuration();

            configuration.configure("hibernate.config.xml");
            configuration
                    .addAnnotatedClass(Assessment.class)
                    .addAnnotatedClass(Attend.class)
                    .addAnnotatedClass(Lesson.class)
                    .addAnnotatedClass(Shedule.class)
                    .addAnnotatedClass(Subject.class)
                    .addAnnotatedClass(Group.class)
                    .addAnnotatedClass(Parent.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Auditory.class)
                    .addAnnotatedClass(School.class);

            final SessionFactory sessionFactory = configuration.buildSessionFactory();

            return sessionFactory;
        } catch (final Exception exc) {
            System.err.println("Error creating session");
            throw new ExceptionInInitializerError(exc);
        }
    }
}
