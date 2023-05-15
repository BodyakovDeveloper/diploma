package com.koval.diploma.mvccontroller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
import com.koval.diploma.service.impl.ClassTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

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

        List<Group> groups = groupService.getGroupsForTeacher(currentPrincipalName);
        model.addAttribute("groups", groups);

        return "teacher/teacher-dashboard";
    }

    @GetMapping("/groups/{groupId}/subjects")
    public String getGroupSubjects(Model model, @PathVariable Long groupId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = authentication.getName();
        User user = userService.getByUsername(currentPrincipalName);
        model.addAttribute("user", user);

        List<Student> students = studentService.getStudentByGroup(groupId);
        model.addAttribute("students", students);

        List<Subject> subjects = subjectService.getSubjectsForTeacherByGroup(user, groupId);
        model.addAttribute("subjects", subjects);

        return "teacher/subjects";
    }

    @GetMapping("/subjects/{groupId}/{subjectId}/table")
    public String getGroupListForSubject(Model model, @PathVariable Long groupId, @PathVariable Long subjectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = authentication.getName();
        User user = userService.getByUsername(currentPrincipalName);
        model.addAttribute("user", user);

        Subject subject = subjectService.getSubjectById(subjectId);
        model.addAttribute("subject", subject);

        List<Student> students = studentService.getStudentByGroup(groupId);
        model.addAttribute("students", students);
        model.addAttribute("numbers", rangeClosed(1, students.size()).boxed().toList());

        Group group = groupService.getGroupById(groupId);
        model.addAttribute("group", group);

        List<ClassType> classTypes = classTypeService.getAllClassTypes();
        model.addAttribute("classTypes", classTypes);
        model.addAttribute("lesson", new Lesson());

        List<Lesson> lessons = lessonService.getLessonsForTeacherByGroupAndSubject(groupId, subjectId);
        model.addAttribute("lessons", lessons);

        return "teacher/group-subject";
    }

    @GetMapping("/subject/{id}")
    public String showTeacherPage(@PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = authentication.getName();
        User user = userService.getByUsername(currentPrincipalName);
        model.addAttribute("user", user);

        List<Subject> subjects = subjectService.getSubjectsForTeacher(user);
        model.addAttribute("subjects", subjects);

        return "teacher/teacher-dashboard";
    }
}