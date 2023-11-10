package by.itacademy.education;

import by.itacademy.base.BaseEntity;
import by.itacademy.persons.Group;
import by.itacademy.persons.Teacher;
import by.itacademy.place.Auditory;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "lesson")
public class Lesson extends BaseEntity {

    @OneToMany(mappedBy = "lesson")
    private List<Assessment> assessments;

    @OneToMany(mappedBy = "lesson")
    private List<Attend> attends;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "lesson_auditory",
            joinColumns = @JoinColumn(
                    name = "auditory_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__lesson_auditory__auditory__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "lesson_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__auditory_lesson__lesson__id")
            )
    )
    private List<Auditory> auditories;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "group_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__lesson__group__id")
    )
    private Group group;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "shedule_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__lesson__shedule__id")
    )
    private Shedule shedule;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "subject_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__lesson__subject__id")
    )
    private Subject subject;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__lesson__teacher__id")
    )
    private Teacher teacher;

    public Lesson(
            final Integer id,
            final List<Assessment> assessments,
            final List<Attend> attends,
            final List<Auditory> auditories,
            final Group group,
            final Shedule shedule,
            final Subject subject,
            final Teacher teacher
    ) {
        super(id);
        this.assessments = assessments;
        this.attends = attends;
        this.auditories = auditories;
        this.group = group;
        this.shedule = shedule;
        this.subject = subject;
        this.teacher = teacher;
    }

    public final List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(final List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public final List<Attend> getAttends() {
        return attends;
    }

    public void setAttends(final List<Attend> attends) {
        this.attends = attends;
    }

    public final List<Auditory> getAuditories() {
        return auditories;
    }

    public void setAuditories(final List<Auditory> auditories) {
        this.auditories = auditories;
    }

    public final Group getGroup() {
        return group;
    }

    public void setGroup(final Group group) {
        this.group = group;
    }

    public final Shedule getShedule() {
        return shedule;
    }

    public void setShedule(final Shedule shedule) {
        this.shedule = shedule;
    }

    public final Subject getSubject() {
        return subject;
    }

    public void setSubject(final Subject subject) {
        this.subject = subject;
    }

    public final Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(final Teacher teacher) {
        this.teacher = teacher;
    }
}
