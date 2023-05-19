package com.koval.diploma.service.impl;

import com.koval.diploma.MockDataProvider;
import com.koval.diploma.exception.SubjectNotFoundException;
import com.koval.diploma.model.Subject;
import com.koval.diploma.model.Teacher;
import com.koval.diploma.model.User;
import com.koval.diploma.repository.SubjectRepository;
import com.koval.diploma.repository.TeacherRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceImplTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private TeacherRepository teacherRepository;

    private SubjectServiceImpl subjectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        subjectService = new SubjectServiceImpl(subjectRepository, teacherRepository);
    }

    @Test
    public void testGetSubjectsForTeacher() {
        // Mock data
        User user = new User();
        user.setUsername("username");
        Teacher teacher = new Teacher();
        teacher.setId(1L);

        when(teacherRepository.findByUserUsername(user.getUsername())).thenReturn(Optional.of(teacher));
        when(subjectRepository.findAllByTeacher(teacher.getId()))
                .thenReturn(Arrays.asList(
                                MockDataProvider.getSubjectOne(),
                                MockDataProvider.getSubjectTwo()
                        )
                );

        // Invoke the method
        List<Subject> subjects = subjectService.getSubjectsForTeacher(user);

        // Verify interactions and assertions
        verify(teacherRepository).findByUserUsername(user.getUsername());
        verify(subjectRepository).findAllByTeacher(teacher.getId());
        assertEquals(2, subjects.size());
    }

    @Test
    public void testGetSubjectsForTeacherByGroup() {
        // Mock data
        User user = new User();
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        Long groupId = 1L;

        when(teacherRepository.findByUserUsername(any())).thenReturn(Optional.of(teacher));
        when(subjectRepository.findAllByTeacherAndGroup(teacher.getId(), groupId)).thenReturn(Arrays.asList(new Subject(), new Subject()));

        // Invoke the method
        List<Subject> subjects = subjectService.getSubjectsForTeacherByGroup(user, groupId);

        // Verify interactions and assertions
        verify(teacherRepository).findByUserUsername(user.getUsername());
        verify(subjectRepository).findAllByTeacherAndGroup(teacher.getId(), groupId);
        assertEquals(2, subjects.size());
    }

    @Test
    public void testGetSubjectById() {
        // Mock data
        Long subjectId = 1L;
        Subject subject = new Subject();

        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));

        // Invoke the method
        Subject result = subjectService.getSubjectById(subjectId);

        // Verify interactions and assertions
        verify(subjectRepository).findById(subjectId);
        assertNotNull(result);
    }

    @Test
    public void testGetSubjectById_ThrowsException() {
        // Mock data
        Long subjectId = 1L;

        when(subjectRepository.findById(subjectId)).thenReturn(Optional.empty());

        // Invoke and verify the exception
        assertThrows(SubjectNotFoundException.class, () -> subjectService.getSubjectById(subjectId));
        verify(subjectRepository).findById(subjectId);
    }
}
