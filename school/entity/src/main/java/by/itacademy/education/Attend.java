package by.itacademy.education;


import by.itacademy.base.BaseEntity;
import by.itacademy.persons.Student;
import jakarta.persistence.*;

@Entity
@Table(name = "attend")
public class Attend extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "lesson_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__attend__lesson__id")
    )
    private Lesson lesson;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__attend__student__id")
    )
    private Student student;

    @Column(name = "visited", nullable = false)
    private boolean visited;

    public Attend(final Integer id, final Lesson lesson, final Student student, final boolean visited) {
        super(id);
        this.lesson = lesson;
        this.student = student;
        this.visited = visited;
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

    public final boolean isVisited() {
        return visited;
    }

    public void setVisit(final boolean visited) {
        this.visited = visited;
    }
}
