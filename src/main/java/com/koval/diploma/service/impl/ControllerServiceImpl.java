package com.koval.diploma.service.impl;

import com.koval.diploma.model.User;
import com.koval.diploma.service.ControllerService;
import com.koval.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ControllerServiceImpl implements ControllerService {

    private final UserService userService;
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = authentication.getName();
        return userService.getByUsername(currentPrincipalName);
    }
}
