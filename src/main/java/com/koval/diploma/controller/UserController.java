package com.koval.diploma.controller;

import com.koval.diploma.model.Group;
import com.koval.diploma.model.User;
import com.koval.diploma.repository.GroupRepository;
import com.koval.diploma.service.StudentService;
import com.koval.diploma.service.TeacherService;
import com.koval.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final GroupRepository groupRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @PostMapping("/admins/new")
    public String saveAdmin(@ModelAttribute("newAdmin") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);

        return "redirect:/admin/teachers";
    }

    @PostMapping("/teachers/new")
    public ModelAndView saveTeacher(@RequestParam("cathedraId") Long cathedraId,
                                    @ModelAttribute("newTeacher") User user,
                                    ModelMap model) {

        teacherService.save(user, cathedraId);
        model.addAttribute("cathedraId", cathedraId);

        return new ModelAndView("redirect:/admin/teachers", model);
    }

    @PostMapping("/students/new")
    public ModelAndView saveStudent(@ModelAttribute("newStudent") User user, ModelMap model) {
        Group studentGroup = groupRepository.findById(user.getStudent().getGroup().getId()).orElseThrow();
        studentService.save(user, studentGroup);

        model.addAttribute("groupId", studentGroup.getId());
        return new ModelAndView("redirect:/admin/students", model);
    }
}