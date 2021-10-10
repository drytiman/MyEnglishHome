package english.server.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import english.server.model.Student;
import english.server.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("student controller")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;


    @GetMapping("/students")
    @ApiOperation("get all student")
    public ResponseEntity<List<Student>> getStudent() {
        return ResponseEntity.ok(studentRepository.findAll());
    }
    @GetMapping("/students/{id}")
    @ApiOperation("get student by id")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        return studentRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/student_by_login/{login}")
    public Student find_student(@PathVariable("login") String login) {
        System.out.println(login);
        return studentRepository.findByStudentlogin(login);
    }

}
