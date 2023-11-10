package by.itacademy.education;

import by.itacademy.base.BaseEntity;
import by.itacademy.persons.Student;
import by.itacademy.place.School;
import by.itacademy.persons.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject")
public class Subject extends BaseEntity {

    @OneToMany(mappedBy = "subject")
    private List<Lesson> lessons;

    @ManyToMany(mappedBy = "subjects")
    private List<School> schools;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "subject_student",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__subject_student__student__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__student_subject__subject__id")
            )
    )
    private List<Student> students;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "subject_teacher",
            joinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__subject_teacher__teacher__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__teacher_subject__subject__id")
            )
    )
    private List<Teacher> teachers;

    public Subject(
            final Integer id,
            final List<Lesson> lessons,
            final List<School> schools,
            final List<Student> students,
            final List<Teacher> teachers
    ) {
        super(id);
        this.lessons = lessons;
        this.schools = schools;
        this.students = students;
        this.teachers = teachers;
    }

    public final List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(final List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public final List<School> getSchools() {
        return schools;
    }

    public void setSchools(final List<School> schools) {
        this.schools = schools;
    }

    public final List<Student> getStudents() {
        return students;
    }

    public void setStudents(final List<Student> students) {
        this.students = students;
    }

    public final List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(final List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
