package com.koval.diploma.controller;

import com.koval.diploma.model.Group;
import com.koval.diploma.model.Student;
import com.koval.diploma.model.Subject;
import com.koval.diploma.model.Teacher;
import com.koval.diploma.model.User;
import com.koval.diploma.service.ControllerService;
import com.koval.diploma.service.GroupService;
import com.koval.diploma.service.SubjectService;
import com.koval.diploma.service.TeacherService;
import com.koval.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final GroupService groupService;
    private final ControllerService controllerService;

    @GetMapping("/dashboard")
    public String getAdminDashboard(Model model) {

        User currentUser = controllerService.getCurrentUser();
        User user = new User();
        user.setStudent(new Student());

        model.addAttribute("admin", currentUser);
        model.addAttribute("user", user);
        model.addAttribute("subject", new Subject());

        List<User> users = userService.getAll();
        model.addAttribute("users", users);

        List<Teacher> teachers = teacherService.getTeachers();
        model.addAttribute("teachers", teachers);
        Group selectedGroup = new Group();
        model.addAttribute("selectedGroup", selectedGroup);
        List<Group> groups = groupService.getAll();
        model.addAttribute("groups", groups);
        model.addAttribute("newGroup", new Group());

        return "admin/admin-dashboard";
    }

    @GetMapping("/groups")
    public String createGroup(Model model) {
        User user = controllerService.getCurrentUser();

        model.addAttribute("admin", user);
        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("newGroup", new Group());
        return "admin/groups";
    }

    @GetMapping("/teachers")
    public String getTeachers(Model model) {
        User user = controllerService.getCurrentUser();
        model.addAttribute("admin", user);

        model.addAttribute("teachers", teacherService.getTeachers());
        model.addAttribute("newTeacher", new Teacher());
        return "admin/teachers";
    }

    @PostMapping("/groups")
    public String createGroup(@ModelAttribute("newGroup") Group group) {
        groupService.save(group);
        return "redirect:/admin/groups";
    }

    @PostMapping("/subjects")
    public String createSubject(@ModelAttribute("newSubject") Subject subject) {
        subjectService.save(subject);
        return "redirect:/admin/dashboard";
    }
}