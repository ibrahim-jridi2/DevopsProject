package tn.esprit.SkiStationProject.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.entities.enums.TypeCourse;
import tn.esprit.SkiStationProject.repositories.CourseRepository;

@ContextConfiguration(classes = {CourseServicesImpl.class})
@ExtendWith(SpringExtension.class)
class CourseServicesImplTest {
    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseServicesImpl courseServicesImpl;

    /**
     * Method under test: {@link CourseServicesImpl#retrieveAllCourses()}
     */
    @Test
    void testRetrieveAllCourses() {
        ArrayList<Course> courseList = new ArrayList<>();
        when(courseRepository.findAll()).thenReturn(courseList);
        List<Course> actualRetrieveAllCoursesResult = courseServicesImpl.retrieveAllCourses();
        assertSame(courseList, actualRetrieveAllCoursesResult);
        assertTrue(actualRetrieveAllCoursesResult.isEmpty());
        verify(courseRepository,times(1)).findAll();
    }

    /**
     * Method under test: {@link CourseServicesImpl#retrieveAllCourses()}
     */
    @Test
    void testRetrieveAllCourses2() {
        when(courseRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> courseServicesImpl.retrieveAllCourses());
        verify(courseRepository).findAll();
    }

    /**
     * Method under test: {@link CourseServicesImpl#addCourse(Course)}
     */
    @Test
    void testAddCourse() {
        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course);

        Course course2 = new Course();
        course2.setLevel(1);
        course2.setPrice(10.0f);
        course2.setRegistrations(new HashSet<>());
        course2.setSupport(Support.SKI);
        course2.setTimeSlot(1);
        course2.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        assertSame(course, courseServicesImpl.addCourse(course2));
        verify(courseRepository).save(Mockito.<Course>any());
    }

    /**
     * Method under test: {@link CourseServicesImpl#addCourse(Course)}
     */
    @Test
    void testAddCourse2() {
        when(courseRepository.save(Mockito.<Course>any())).thenThrow(new IllegalArgumentException("foo"));

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        assertThrows(IllegalArgumentException.class, () -> courseServicesImpl.addCourse(course));
        verify(courseRepository).save(Mockito.<Course>any());
    }

    /**
     * Method under test: {@link CourseServicesImpl#updateCourse(Course)}
     */
    @Test
    void testUpdateCourse() {
        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course);

        Course course2 = new Course();
        course2.setLevel(1);
        course2.setPrice(10.0f);
        course2.setRegistrations(new HashSet<>());
        course2.setSupport(Support.SKI);
        course2.setTimeSlot(1);
        course2.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        assertSame(course, courseServicesImpl.updateCourse(course2));
        verify(courseRepository).save(Mockito.<Course>any());
    }

    /**
     * Method under test: {@link CourseServicesImpl#updateCourse(Course)}
     */
    @Test
    void testUpdateCourse2() {
        when(courseRepository.save(Mockito.<Course>any())).thenThrow(new IllegalArgumentException("foo"));

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        assertThrows(IllegalArgumentException.class, () -> courseServicesImpl.updateCourse(course));
        verify(courseRepository).save(Mockito.<Course>any());
    }

    /**
     * Method under test: {@link CourseServicesImpl#retrieveCourse(Long)}
     */
    @Test
    void testRetrieveCourse() {
        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(course, courseServicesImpl.retrieveCourse(1L));
        verify(courseRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CourseServicesImpl#retrieveCourse(Long)}
     */
    @Test
    void testRetrieveCourse2() {
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> courseServicesImpl.retrieveCourse(1L));
        verify(courseRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CourseServicesImpl#retrieveCourse(Long)}
     */
    @Test
    void testRetrieveCourse3() {
        when(courseRepository.findById(Mockito.<Long>any()))
                .thenThrow(new IllegalArgumentException("no course found with this id "));
        assertThrows(IllegalArgumentException.class, () -> courseServicesImpl.retrieveCourse(1L));
        verify(courseRepository).findById(Mockito.<Long>any());
    }
}

