package by.itacademy.place;

import by.itacademy.base.BaseEntity;
import by.itacademy.education.Shedule;
import by.itacademy.education.Subject;
import by.itacademy.persons.Group;
import by.itacademy.persons.Student;
import by.itacademy.persons.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "school")
public class School extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__school__address__id")
    )
    private Address address;

    @OneToMany(mappedBy = "school")
    private List<Auditory> auditory;

    @OneToMany(mappedBy = "school")
    private List<Group> groups;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "school")
    private List<Shedule> shedules;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "school_student",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_student__student__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "school_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__student_school__school__id")
            )
    )
    private List<Student> students;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "school_subject",
            joinColumns = @JoinColumn(
                    name = "subject_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_subject__subject__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "school_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__subject_school__school__id")
            )
    )
    private List<Subject> subjects;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "school_teacher",
            joinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__school_teacher__teacher__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "school_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__teacher_school__school__id")
            )
    )
    private List<Teacher> teachers;

    public School(
            final Integer id,
            final Address address,
            final List<Auditory> auditory,
            final List<Group> groups,
            final String name,
            final List<Shedule> shedules,
            final List<Student> students,
            final List<Subject> subjects,
            final List<Teacher> teachers
    ) {
        super(id);
        this.address = address;
        this.auditory = auditory;
        this.groups = groups;
        this.name = name;
        this.shedules = shedules;
        this.students = students;
        this.subjects = subjects;
        this.teachers = teachers;
    }

    public final Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public final List<Auditory> getAuditory() {
        return auditory;
    }

    public void setAuditory(final List<Auditory> auditory) {
        this.auditory = auditory;
    }

    public final List<Group> getGroups() {
        return groups;
    }

    public void setGroups(final List<Group> groups) {
        this.groups = groups;
    }

    public final String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public final List<Shedule> getShedules() {
        return shedules;
    }

    public void setShedules(final List<Shedule> shedules) {
        this.shedules = shedules;
    }

    public final List<Student> getStudents() {
        return students;
    }

    public void setStudents(final List<Student> students) {
        this.students = students;
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
