package cz.vutbr.fit.gja.spring.boot.demo;

import java.util.Objects;

public class Student {

    private long id;
    private String login;

    public static Student fromEntity(StudentEntity studentEntity) {
        return new Student(studentEntity.getId(), studentEntity.getLogin());
    }

    public Student(){

    }
    public Student(long id, String login) {
        this.id = id;
        this.login = login;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
