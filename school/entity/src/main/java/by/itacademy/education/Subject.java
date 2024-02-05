package by.itacademy.education;

import by.itacademy.base.BaseEntity;
import by.itacademy.persons.Group;
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

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "subject_group",
            joinColumns = @JoinColumn(
                    name = "group_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__subject_group__group__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__group_subject__subject__id")
            )
    )
    private List<Group> groups;

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
            final String name,
            final List<Group> groups,
            final List<Teacher> teachers
    ) {
        super(id);
        this.lessons = lessons;
        this.schools = schools;
        this.name = name;
        this.groups = groups;
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

    public final String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public final List<Group> getGroup() {
        return groups;
    }

    public void setGroup(final List<Group> groups) {
        this.groups = groups;
    }

    public final List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(final List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
