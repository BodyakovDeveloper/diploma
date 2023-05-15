package com.koval.diploma.mvccontroller;

import com.koval.diploma.model.User;
import com.koval.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentMvcController {

    private final UserService userService;

    @GetMapping("/dashboard")
    public String showTeacherPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = authentication.getName();
        User user = userService.getByUsername(currentPrincipalName);
        model.addAttribute("user", user);

        return "student/student-dashboard";
    }
}
