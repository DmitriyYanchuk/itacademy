package by.itacademy.persons;

import by.itacademy.base.BasePerson;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "parent")
public class Parent extends BasePerson {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "parent_student",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__parent_student__student__id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "parent_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk__student_parent__parent__id")
            )
    )
    private List<Student> students;

    public Parent(
            final Integer id,
            final String firstName,
            final String lastName,
            final List<Student> students
    ) {
        super(id, firstName, lastName);
        this.students = students;
    }

    public final List<Student> getStudents() {
        return students;
    }

    public void setStudents(final List<Student> students) {
        this.students = students;
    }
}
