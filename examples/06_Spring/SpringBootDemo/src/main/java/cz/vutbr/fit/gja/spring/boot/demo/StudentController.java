package cz.vutbr.fit.gja.spring.boot.demo;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Student addStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
}
