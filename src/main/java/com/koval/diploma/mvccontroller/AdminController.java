package com.koval.diploma.mvccontroller;

import com.koval.diploma.model.Group;
import com.koval.diploma.model.Student;
import com.koval.diploma.model.Subject;
import com.koval.diploma.model.Teacher;
import com.koval.diploma.model.User;
import com.koval.diploma.repository.GroupRepository;
import com.koval.diploma.repository.SubjectRepository;
import com.koval.diploma.repository.TeacherRepository;
import com.koval.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;

    @GetMapping("/dashboard")
    public String getAdminDashboard(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = authentication.getName();
        User admin = userService.getByUsername(currentPrincipalName);
        User user = new User();
        user.setStudent(new Student());

        model.addAttribute("admin", admin);
        model.addAttribute("user", user);
        model.addAttribute("subject", new Subject());

        List<User> users = userService.getAll();
        model.addAttribute("users", users);

        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("teachers", teachers);
        Group selectedGroup = new Group();
        model.addAttribute("selectedGroup", selectedGroup);
        List<Group> groups = groupRepository.findAll();
        model.addAttribute("groups", groups);
        model.addAttribute("newGroup", new Group());

        return "admin/admin-dashboard";
    }

    @GetMapping("/groups")
    public String createGroup(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = authentication.getName();
        User user = userService.getByUsername(currentPrincipalName);

        model.addAttribute("admin", user);
        model.addAttribute("groups", groupRepository.findAll());
        model.addAttribute("newGroup", new Group());
        return "admin/groups";
    }

    @PostMapping("/groups")
    public String createGroup(@ModelAttribute("newGroup") Group group) {
        groupRepository.save(group);
        return "redirect:/admin/groups";
    }

    @PostMapping("/subjects")
    public String createSubject(@ModelAttribute("newSubject") Subject subject) {
        subjectRepository.save(subject);
        return "redirect:/admin/dashboard";
    }
}