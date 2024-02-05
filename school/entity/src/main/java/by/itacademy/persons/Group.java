package by.itacademy.persons;

import by.itacademy.base.BaseEntity;
import by.itacademy.education.Lesson;
import by.itacademy.education.StudentGroupSubjectLink;
import by.itacademy.education.Subject;
import by.itacademy.place.Auditory;
import by.itacademy.place.School;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "group")
public class Group extends BaseEntity {

    @ManyToMany(mappedBy = "groups")
    private List<Auditory> auditories;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "group")
    private List<Lesson> lessons;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__group__school__id")
    )
    private School school;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_student",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__group_student__student__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "group_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__student_group__group__id")
            )
    )
    private List<Student> students;

    @OneToMany(mappedBy = "group")
    private List<StudentGroupSubjectLink> studentGroupSubjectLinks;

    @ManyToMany(mappedBy = "group")
    private List<Subject> subjects;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_teacher",
            joinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__group_teacher__teacher__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "group_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__teacher_group__group__id")
            )
    )
    private List<Teacher> teachers;

    public Group(
            final Integer id,
            final List<Auditory> auditories,
            final String name,
            final List<Lesson> lessons,
            final School school,
            final List<Student> students,
            final List<StudentGroupSubjectLink> studentGroupSubjectLinks,
            final List<Subject> subjects,
            final List<Teacher> teachers
    ) {
        super(id);
        this.auditories = auditories;
        this.name = name;
        this.lessons = lessons;
        this.school = school;
        this.students = students;
        this.studentGroupSubjectLinks = studentGroupSubjectLinks;
        this.subjects = subjects;
        this.teachers = teachers;
    }

    public final List<Auditory> getAuditories() {
        return auditories;
    }

    public void setAuditories(final List<Auditory> auditories) {
        this.auditories = auditories;
    }

    public final String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public final List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(final List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public final School getSchool() {
        return school;
    }

    public void setSchool(final School school) {
        this.school = school;
    }

    public final List<Student> getStudents() {
        return students;
    }

    public void setStudents(final List<Student> students) {
        this.students = students;
    }

    public final List<StudentGroupSubjectLink> getStudentGroupSubjectLinks() {
        return studentGroupSubjectLinks;
    }

    public void setStudentGroupSubjectLinks(final List<StudentGroupSubjectLink> studentGroupSubjectLinks) {
        this.studentGroupSubjectLinks = studentGroupSubjectLinks;
    }

    public final List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(final List<Subject> subjects) {
        this.subjects = subjects;
    }

    public final List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(final List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
