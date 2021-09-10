package ru.zsa.repeat_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.zsa.repeat_spring.config.JwtProvider;
import ru.zsa.repeat_spring.model.AuthRequestDto;
import ru.zsa.repeat_spring.model.AuthResponseDto;
import ru.zsa.repeat_spring.model.Student;
import ru.zsa.repeat_spring.model.User;
import ru.zsa.repeat_spring.services.StudentService;
import ru.zsa.repeat_spring.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;
    private UserService userService;
    private JwtProvider jwtProvider;

    @Autowired
    public StudentController(StudentService studentService, UserService userService, JwtProvider jwtProvider) {
        this.studentService = studentService;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public Student add(@RequestBody Student student) {
        student.setId(null);
        return studentService.addOrUpdate(student);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public Student update(@RequestBody Student student) {
        return studentService.addOrUpdate(student);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        studentService.delete(id);
    }

    @PostMapping("/user_login")
    public AuthResponseDto login(@RequestBody AuthRequestDto req) {
        User user = userService.findByLoginAndPassword(req.getLogin(), req.getPassword());
        String token = jwtProvider.generateToken(user.getLogin(), user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()));
        return new AuthResponseDto(token);
    }
}
