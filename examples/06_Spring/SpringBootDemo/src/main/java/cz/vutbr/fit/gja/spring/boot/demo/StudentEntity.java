package cz.vutbr.fit.gja.spring.boot.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class StudentEntity {

    @Id
    @GeneratedValue
    private long id;

    private String login;

    public StudentEntity() {

    }

    public StudentEntity(long id, String login) {
        this.id = id;
        this.login = login;
    }

    public static StudentEntity fromStudent(Student student) {
        return new StudentEntity(student.getId(), student.getLogin());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
