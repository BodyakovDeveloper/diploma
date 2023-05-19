package com.koval.diploma.controller;

import com.koval.diploma.model.Group;
import com.koval.diploma.model.Student;
import com.koval.diploma.model.Teacher;
import com.koval.diploma.model.User;
import com.koval.diploma.repository.GroupRepository;
import com.koval.diploma.repository.StudentRepository;
import com.koval.diploma.repository.TeacherRepository;
import com.koval.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @GetMapping("/new")
    public String getAdminDashboard(Model model) {

        model.addAttribute("user", new User());
        List<User> users = userService.getAll();
        model.addAttribute("users", users);

        return "admin/new-user";
    }
    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        switch (user.getRole()) {
            case TEACHER -> {
                Teacher teacher = new Teacher();
                teacher.setUser(user);
                userService.saveUser(user);
                teacherRepository.save(teacher);
            }
            case STUDENT -> {
                Student student = new Student();
                Group studentGroup = groupRepository.findById(user.getStudent().getGroup().getId()).orElseThrow();
                student.setGroup(studentGroup);
                user.setStudent(null);
                User savedUser = userService.saveUser(user);
                student.setUser(savedUser);
                studentRepository.save(student);
            }
            case ADMIN -> userService.saveUser(user);
        }

        return "redirect:/admin/dashboard";
    }
}