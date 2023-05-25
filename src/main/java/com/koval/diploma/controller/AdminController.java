package com.koval.diploma.controller;

import com.koval.diploma.model.*;
import com.koval.diploma.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final GroupService groupService;
    private final StudentService studentService;
    private final ControllerService controllerService;
    private final CathedraService cathedraService;

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

        List<Cathedra> cathedras = cathedraService.getAll();
        model.addAttribute("cathedras", cathedras);

        List<Teacher> teachers = teacherService.getAll();
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
        User admin = controllerService.getCurrentUser();

        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("newGroup", new Group());
        model.addAttribute("admin", admin);
        return "admin/groups";
    }

    @GetMapping("/teachers")
    public String getTeachers(@RequestParam("cathedraId") Long cathedraId, Model model) {
        User admin = controllerService.getCurrentUser();
        Cathedra currentCathedra = cathedraService
                .getById(cathedraId == null
                        ? Long.valueOf(model.getAttribute("cathedraId").toString())
                        : cathedraId);

        model.addAttribute("admin", admin);
        model.addAttribute("currentCathedra", currentCathedra);
        model.addAttribute("teachers", teacherService.getByCathedra(cathedraId));
        model.addAttribute("newTeacher", new User());
        return "admin/teachers";
    }

    @GetMapping("/students")
    public String getStudents(@RequestParam("groupId") Long groupId, Model model) {
        List<Student> students = studentService.getStudentsByGroup(groupId);
        Group currentGroup = groupService
                .getById(groupId == null
                        ? Long.valueOf(model.getAttribute("groupId").toString())
                        : groupId);
        User admin = controllerService.getCurrentUser();

        model.addAttribute("admin", admin);
        model.addAttribute("students", students);
        model.addAttribute("newStudent", new User());
        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("group", currentGroup);
        return "admin/students";
    }

    @PostMapping("/groups")
    public String createGroup(@RequestParam("cathedraId") Long cathedraId, @ModelAttribute("newGroup") Group group) {
        group.setCathedra(cathedraService.getById(cathedraId));
        groupService.save(group);
        return "redirect:/admin/groups";
    }

    @PostMapping("/subjects")
    public String createSubject(@ModelAttribute("newSubject") Subject subject) {
        subjectService.save(subject);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/groups/edit")
    public ModelAndView submitForm(@ModelAttribute("entity") Group group, ModelMap model) {
        groupService.update(group);
        model.addAttribute("groupId", group.getId());

        return new ModelAndView("redirect:/admin/students", model);
    }
}