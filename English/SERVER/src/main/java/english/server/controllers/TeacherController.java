package english.server.controllers;

import english.server.model.Teacher;
import english.server.repositories.TeacherRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Api("admin controller")
public class TeacherController {
    final
    TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @GetMapping("/teachers")
    @ApiOperation("get all teachers")
    public ResponseEntity<List<Teacher>> getTeacher() {
        return ResponseEntity.ok(teacherRepository.findAll());
    }

    @GetMapping("/teachers/{id}")
    @ApiOperation("get teacher by id")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Long id) {

        return teacherRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
