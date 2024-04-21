package tn.esprit.SkiStationProject.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import tn.esprit.SkiStationProject.entities.Instructor;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.entities.enums.TypeCourse;
import tn.esprit.SkiStationProject.repositories.CourseRepository;
import tn.esprit.SkiStationProject.repositories.InstructorRepository;

@ContextConfiguration(classes = {InstructorServicesImpl.class})
@ExtendWith(SpringExtension.class)
class InstructorServicesImplTest {
    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorServicesImpl instructorServicesImpl;

    /**
     * Method under test: {@link InstructorServicesImpl#addInstructor(Instructor)}
     */
    @Test
    void testAddInstructor() {
        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        when(instructorRepository.save(Mockito.<Instructor>any())).thenReturn(instructor);

        Instructor instructor2 = new Instructor();
        instructor2.setCourses(new HashSet<>());
        instructor2.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor2.setFirstName("Jane");
        instructor2.setLastName("Doe");
        assertSame(instructor, instructorServicesImpl.addInstructor(instructor2));
        verify(instructorRepository).save(Mockito.<Instructor>any());
    }

    /**
     * Method under test: {@link InstructorServicesImpl#addInstructor(Instructor)}
     */
    @Test
    void testAddInstructor2() {
        when(instructorRepository.save(Mockito.<Instructor>any())).thenThrow(new IllegalArgumentException("foo"));

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        assertThrows(IllegalArgumentException.class, () -> instructorServicesImpl.addInstructor(instructor));
        verify(instructorRepository).save(Mockito.<Instructor>any());
    }

    /**
     * Method under test: {@link InstructorServicesImpl#retrieveAllInstructors()}
     */
    @Test
    void testRetrieveAllInstructors() {
        ArrayList<Instructor> instructorList = new ArrayList<>();
        when(instructorRepository.findAll()).thenReturn(instructorList);
        List<Instructor> actualRetrieveAllInstructorsResult = instructorServicesImpl.retrieveAllInstructors();
        assertSame(instructorList, actualRetrieveAllInstructorsResult);
        assertTrue(actualRetrieveAllInstructorsResult.isEmpty());
        verify(instructorRepository).findAll();
    }

    /**
     * Method under test: {@link InstructorServicesImpl#retrieveAllInstructors()}
     */
    @Test
    void testRetrieveAllInstructors2() {
        when(instructorRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> instructorServicesImpl.retrieveAllInstructors());
        verify(instructorRepository).findAll();
    }

    /**
     * Method under test: {@link InstructorServicesImpl#updateInstructor(Instructor)}
     */
    @Test
    void testUpdateInstructor() {
        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        when(instructorRepository.save(Mockito.<Instructor>any())).thenReturn(instructor);

        Instructor instructor2 = new Instructor();
        instructor2.setCourses(new HashSet<>());
        instructor2.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor2.setFirstName("Jane");
        instructor2.setLastName("Doe");
        assertSame(instructor, instructorServicesImpl.updateInstructor(instructor2));
        verify(instructorRepository).save(Mockito.<Instructor>any());
    }

    /**
     * Method under test: {@link InstructorServicesImpl#updateInstructor(Instructor)}
     */
    @Test
    void testUpdateInstructor2() {
        when(instructorRepository.save(Mockito.<Instructor>any())).thenThrow(new IllegalArgumentException("foo"));

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        assertThrows(IllegalArgumentException.class, () -> instructorServicesImpl.updateInstructor(instructor));
        verify(instructorRepository).save(Mockito.<Instructor>any());
    }

    /**
     * Method under test: {@link InstructorServicesImpl#retrieveInstructor(Long)}
     */
    @Test
    void testRetrieveInstructor() {
        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        Optional<Instructor> ofResult = Optional.of(instructor);
        when(instructorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(instructor, instructorServicesImpl.retrieveInstructor(1L));
        verify(instructorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link InstructorServicesImpl#retrieveInstructor(Long)}
     */
    @Test
    void testRetrieveInstructor2() {
        when(instructorRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> instructorServicesImpl.retrieveInstructor(1L));
        verify(instructorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link InstructorServicesImpl#retrieveInstructor(Long)}
     */
    @Test
    void testRetrieveInstructor3() {
        when(instructorRepository.findById(Mockito.<Long>any()))
                .thenThrow(new IllegalArgumentException("no instructor found with this id "));
        assertThrows(IllegalArgumentException.class, () -> instructorServicesImpl.retrieveInstructor(1L));
        verify(instructorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link InstructorServicesImpl#addInstructorAndAssignToCourse(Instructor, Long)}
     */
    @Test
    void testAddInstructorAndAssignToCourse() {
        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        when(instructorRepository.save(Mockito.<Instructor>any())).thenReturn(instructor);

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Instructor instructor2 = new Instructor();
        instructor2.setCourses(new HashSet<>());
        instructor2.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor2.setFirstName("Jane");
        instructor2.setLastName("Doe");
        assertSame(instructor, instructorServicesImpl.addInstructorAndAssignToCourse(instructor2, 1L));
        verify(instructorRepository).save(Mockito.<Instructor>any());
        verify(courseRepository).findById(Mockito.<Long>any());
        assertEquals(1, instructor2.getCourses().size());
    }

    /**
     * Method under test: {@link InstructorServicesImpl#addInstructorAndAssignToCourse(Instructor, Long)}
     */
    @Test
    void testAddInstructorAndAssignToCourse2() {
        when(instructorRepository.save(Mockito.<Instructor>any())).thenThrow(new IllegalArgumentException("foo"));

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        assertThrows(IllegalArgumentException.class,
                () -> instructorServicesImpl.addInstructorAndAssignToCourse(instructor, 1L));
        verify(instructorRepository).save(Mockito.<Instructor>any());
        verify(courseRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link InstructorServicesImpl#addInstructorAndAssignToCourse(Instructor, Long)}
     */
    @Test
    void testAddInstructorAndAssignToCourse3() {
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        assertThrows(IllegalArgumentException.class,
                () -> instructorServicesImpl.addInstructorAndAssignToCourse(instructor, 1L));
        verify(courseRepository).findById(Mockito.<Long>any());
    }
}

