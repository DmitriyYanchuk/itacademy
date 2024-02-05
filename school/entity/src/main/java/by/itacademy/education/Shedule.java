package by.itacademy.education;

import by.itacademy.base.BaseEntity;
import by.itacademy.persons.Group;
import by.itacademy.place.School;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "shedule")
public class Shedule extends BaseEntity {

    @ManyToMany(mappedBy = "shedule")
    private List<Lesson> lesson;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__shedule__school__id")
    )
    private School school;

    @Column(name = "city", nullable = false)
    private OffsetDateTime startDate;

    @Column(name = "city", nullable = false)
    private OffsetDateTime endDate;

    public Shedule(
            final Integer id,
            final List<Lesson> lesson,
            final School school,
            final OffsetDateTime startDate,
            final OffsetDateTime endDate
    ) {
        super(id);
        this.lesson = lesson;
        this.school = school;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public final List<Lesson> getLesson() {
        return lesson;
    }

    public void setLesson(final List<Lesson> lesson) {
        this.lesson = lesson;
    }

    public final School getSchool() {
        return school;
    }

    public void setSchool(final School school) {
        this.school = school;
    }

    public final OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(final OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public final OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(final OffsetDateTime endDate) {
        this.endDate = endDate;
    }
}
