package by.itacademy.place;

import by.itacademy.base.BaseEntity;
import by.itacademy.education.Lesson;
import by.itacademy.persons.Group;
import by.itacademy.persons.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "auditory")
public class Auditory extends BaseEntity {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "auditory_group",
            joinColumns = @JoinColumn(
                    name = "group_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__auditory_group__group__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "auditory_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__group_auditory__auditory__id")
            )
    )
    private List<Group> groups;

    @ManyToMany(mappedBy = "auditories")
    private List<Lesson> lessons;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__auditory__school__id")
    )
    private School school;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "auditory_teacher",
            joinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__auditory_teacher__teacher__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "auditory_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__teacher_auditory__auditory__id")
            )
    )
    private Teacher teacher;

    public Auditory(
            final Integer id,
            final List<Group> groups,
            final List<Lesson> lessons,
            final School school,
            final Teacher teacher
    ) {
        super(id);
        this.groups = groups;
        this.lessons = lessons;
        this.school = school;
        this.teacher = teacher;
    }

    public final List<Group> getGroups() {
        return groups;
    }

    public void setGroups(final List<Group> groups) {
        this.groups = groups;
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

    public final Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(final Teacher teacher) {
        this.teacher = teacher;
    }
}
