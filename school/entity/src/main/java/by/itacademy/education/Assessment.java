package by.itacademy.education;


import by.itacademy.persons.Student;
import jakarta.persistence.*;

@Entity
@Table(name = "assessment")
public class Assessment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "lesson_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__assessment__lesson__id")
    )
    private Lesson lesson;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__assessment__student__id")
    )
    private Student student;

    @Column(name = "value", nullable = false, length = 2)
    private Integer value;

    public Assessment(
            final Long id,
            final Lesson lesson,
            final Student student,
            final Integer value
    ) {
        this.id = id;
        this.lesson = lesson;
        this.student = student;
        this.value = value;
    }

    public final Long getId() {
        return id;
    }

    public final Lesson getLesson() {
        return lesson;
    }

    public void setLesson(final Lesson lesson) {
        this.lesson = lesson;
    }

    public final Student getStudent() {
        return student;
    }

    public void setStudent(final Student student) {
        this.student = student;
    }

    public final Integer getValue() {
        return value;
    }

    public void setValue(final Integer value) {
        this.value = value;
    }

}
