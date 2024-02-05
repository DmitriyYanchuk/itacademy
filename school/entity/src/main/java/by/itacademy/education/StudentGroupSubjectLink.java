package by.itacademy.education;

import by.itacademy.base.BaseEntity;
import by.itacademy.persons.Group;
import by.itacademy.persons.Teacher;
import jakarta.persistence.*;

@Entity
@Table(name = "student_group_subject_link")
public class StudentGroupSubjectLink extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "group_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__student_group_subject_link__group__id")
    )
    private Group group;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__student_group_subject_link__teacher__id")
    )
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "subject_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__student_group_subject_link__subject__id")
    )
    private Subject subject;

    public StudentGroupSubjectLink(
            final Integer id,
            final Group group,
            final Teacher teacher,
            final Subject subject
    ) {
        super(id);
        this.group = group;
        this.teacher = teacher;
        this.subject = subject;
    }

    public final Group getGroup() {
        return group;
    }

    public void setGroup(final Group group) {
        this.group = group;
    }

    public final Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(final Teacher teacher) {
        this.teacher = teacher;
    }

    public final Subject getSubject() {
        return subject;
    }

    public void setSubject(final Subject subject) {
        this.subject = subject;
    }
}
