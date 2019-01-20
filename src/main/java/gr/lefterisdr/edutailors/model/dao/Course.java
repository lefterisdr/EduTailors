package gr.lefterisdr.edutailors.model.dao;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private int id;
    private String title;
    private String description;
    @OneToMany(mappedBy = "pk.course", cascade=CascadeType.ALL)
    private Set<StudentCourse> courseStudents;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Set<StudentCourse> getCourseStudents()
    {
        return courseStudents;
    }

    public void setCourseStudents(Set<StudentCourse> courseStudents)
    {
        this.courseStudents = courseStudents;
    }
}
