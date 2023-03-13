package com.koval.diploma.security.service;

import com.koval.diploma.entity.User;
import com.koval.diploma.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

import static com.koval.diploma.util.SecurityConstant.ROLE_PREFIX;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getUserAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getUserAuthorities(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole().name().toUpperCase()));
    }
}