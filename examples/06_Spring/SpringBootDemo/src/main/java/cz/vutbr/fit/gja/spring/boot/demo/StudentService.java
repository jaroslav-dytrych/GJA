package cz.vutbr.fit.gja.spring.boot.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll()
                .stream()
                .map(studentEntity -> Student.fromEntity(studentEntity))
                .collect(Collectors.toList());
    }

    public Student saveStudent(Student student) {
        StudentEntity persistedStudent = studentRepository.save(StudentEntity.fromStudent(student));
        return Student.fromEntity(persistedStudent);
    }
}
