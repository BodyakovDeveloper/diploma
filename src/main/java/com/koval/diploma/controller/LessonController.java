package com.koval.diploma.controller;
// Import necessary dependencies

import com.koval.diploma.model.Lesson;
import com.koval.diploma.service.ClassTypeService;
import com.koval.diploma.service.GroupService;
import com.koval.diploma.service.LessonService;
import com.koval.diploma.service.SubjectService;
import com.koval.diploma.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final ClassTypeService classTypeService;
    private final SubjectService subjectService;

    // Constructor injection of services
    public LessonController(LessonService lessonService, TeacherService teacherService,
                            GroupService groupService, ClassTypeService classTypeService,
                            SubjectService subjectService) {
        this.lessonService = lessonService;
        this.teacherService = teacherService;
        this.groupService = groupService;
        this.classTypeService = classTypeService;
        this.subjectService = subjectService;
    }

    // Handler method to display the lesson creation form
    @GetMapping("/create")
    public String showCreateLessonForm(Model model) {
        // Prepare necessary data for the form
        model.addAttribute("newLesson", new Lesson());
        model.addAttribute("classTypes", classTypeService.getAllClassTypes());
        return "lesson-create";
    }

    // Handler method to process the creation of a new lesson
    @PostMapping("/create")
    public String createLesson(@ModelAttribute("newLesson") Lesson lesson) {
        // Save the new lesson using the lessonService
        lessonService.createLesson(lesson);
        return "redirect:/lessons"; // Redirect to the lessons list page
    }

    // Other handler methods for lesson-related operations

    // ...
}
