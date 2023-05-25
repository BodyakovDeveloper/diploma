package com.koval.diploma.controller;

import com.koval.diploma.model.ClassType;
import com.koval.diploma.model.Group;
import com.koval.diploma.model.Lesson;
import com.koval.diploma.model.Student;
import com.koval.diploma.model.Subject;
import com.koval.diploma.model.User;
import com.koval.diploma.service.ClassTypeService;
import com.koval.diploma.service.GroupService;
import com.koval.diploma.service.LessonService;
import com.koval.diploma.service.StudentService;
import com.koval.diploma.service.SubjectService;
import com.koval.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.IntStream.rangeClosed;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherMvcController {

    private final UserService userService;
    private final SubjectService subjectService;
    private final GroupService groupService;
    private final LessonService lessonService;
    private final StudentService studentService;
    private final ClassTypeService classTypeService;

    @GetMapping("/dashboard")
    public String showTeacherPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = authentication.getName();
        User user = userService.getByUsername(currentPrincipalName);
        model.addAttribute("user", user);

        List<Group> groups = groupService.getForTeacher(currentPrincipalName);
        model.addAttribute("groups", groups);

        return "teacher/teacher-dashboard";
    }

    @GetMapping("/groups/{groupId}/subjects")
    public String getGroupSubjects(Model model, @PathVariable Long groupId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Group group = groupService.getById(groupId);
        model.addAttribute("currentGroup", group);
        String currentPrincipalName = authentication.getName();
        User user = userService.getByUsername(currentPrincipalName);
        model.addAttribute("user", user);

        List<Student> students = studentService.getStudentsByGroup(groupId);
        model.addAttribute("students", students);

        List<Subject> subjects = subjectService.getForTeacherByGroup(user, groupId);
        model.addAttribute("subjects", subjects);

        return "teacher/subjects";
    }

    @GetMapping("/subjects/{groupId}/{subjectId}/table")
    public String getGroupListForSubject(Model model, @PathVariable Long groupId, @PathVariable Long subjectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = authentication.getName();
        User user = userService.getByUsername(currentPrincipalName);
        model.addAttribute("user", user);

        Subject subject = subjectService.getById(subjectId);
        model.addAttribute("subject", subject);

        List<Student> students = studentService.getStudentsByGroup(groupId);
        model.addAttribute("students", students);
        model.addAttribute("numbers", rangeClosed(1, students.size()).boxed().toList());

        Group group = groupService.getById(groupId);
        model.addAttribute("group", group);

        List<ClassType> classTypes = classTypeService.getAll();
        model.addAttribute("classTypes", classTypes);
        model.addAttribute("lesson", new Lesson());

        List<Lesson> lessons = lessonService.getForTeacherByGroupAndSubject(groupId, subjectId);
        model.addAttribute("lessons", lessons);

        return "teacher/group-subject";
    }

    @GetMapping("/subject/{id}")
    public String showTeacherPage(@PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = authentication.getName();
        User user = userService.getByUsername(currentPrincipalName);
        model.addAttribute("user", user);

        List<Subject> subjects = subjectService.getForTeacher(user);
        model.addAttribute("subjects", subjects);

        return "teacher/teacher-dashboard";
    }
}