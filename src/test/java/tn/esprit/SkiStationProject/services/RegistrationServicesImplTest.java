package tn.esprit.SkiStationProject.services;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.Instructor;
import tn.esprit.SkiStationProject.entities.Registration;
import tn.esprit.SkiStationProject.entities.Skier;
import tn.esprit.SkiStationProject.entities.Subscription;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.entities.enums.TypeCourse;
import tn.esprit.SkiStationProject.entities.enums.TypeSubscription;
import tn.esprit.SkiStationProject.repositories.CourseRepository;
import tn.esprit.SkiStationProject.repositories.InstructorRepository;
import tn.esprit.SkiStationProject.repositories.RegistrationRepository;
import tn.esprit.SkiStationProject.repositories.SkierRepository;

@ContextConfiguration(classes = {RegistrationServicesImpl.class})
@ExtendWith(SpringExtension.class)
class RegistrationServicesImplTest {
    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private InstructorRepository instructorRepository;

    @MockBean
    private RegistrationRepository registrationRepository;

    @Autowired
    private RegistrationServicesImpl registrationServicesImpl;

    @MockBean
    private SkierRepository skierRepository;

    /**
     * Method under test: {@link RegistrationServicesImpl#addRegistrationAndAssignToSkier(Registration, Long)}
     */
    @Test
    void testAddRegistrationAndAssignToSkier() {
        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);

        Registration registration = new Registration();
        registration.setCourse(course);
        registration.setNumWeek(10);
        registration.setSkier(skier);
        when(registrationRepository.save(Mockito.<Registration>any())).thenReturn(registration);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);
        Optional<Skier> ofResult = Optional.of(skier2);
        when(skierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Course course2 = new Course();
        course2.setLevel(1);
        course2.setPrice(10.0f);
        course2.setRegistrations(new HashSet<>());
        course2.setSupport(Support.SKI);
        course2.setTimeSlot(1);
        course2.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription3 = new Subscription();
        subscription3.setEndDate(LocalDate.of(1970, 1, 1));
        subscription3.setPrice(10.0f);
        subscription3.setStartDate(LocalDate.of(1970, 1, 1));
        subscription3.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier3 = new Skier();
        skier3.setCity("Oxford");
        skier3.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier3.setFirstName("Jane");
        skier3.setLastName("Doe");
        skier3.setPistes(new HashSet<>());
        skier3.setRegistrations(new HashSet<>());
        skier3.setSubscription(subscription3);

        Registration registration2 = new Registration();
        registration2.setCourse(course2);
        registration2.setNumWeek(10);
        registration2.setSkier(skier3);
        Registration actualAddRegistrationAndAssignToSkierResult = registrationServicesImpl
                .addRegistrationAndAssignToSkier(registration2, 1L);
        assertSame(registration2, actualAddRegistrationAndAssignToSkierResult);
        assertSame(skier2, actualAddRegistrationAndAssignToSkierResult.getSkier());
        verify(registrationRepository).save(Mockito.<Registration>any());
        verify(skierRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#addRegistrationAndAssignToSkier(Registration, Long)}
     */
    @Test
    void testAddRegistrationAndAssignToSkier2() {
        when(registrationRepository.save(Mockito.<Registration>any())).thenThrow(new IllegalArgumentException("foo"));

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        Optional<Skier> ofResult = Optional.of(skier);
        when(skierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);

        Registration registration = new Registration();
        registration.setCourse(course);
        registration.setNumWeek(10);
        registration.setSkier(skier2);
        assertThrows(IllegalArgumentException.class,
                () -> registrationServicesImpl.addRegistrationAndAssignToSkier(registration, 1L));
        verify(registrationRepository).save(Mockito.<Registration>any());
        verify(skierRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#addRegistrationAndAssignToSkier(Registration, Long)}
     */
    @Test
    void testAddRegistrationAndAssignToSkier3() {
        when(skierRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);

        Registration registration = new Registration();
        registration.setCourse(course);
        registration.setNumWeek(10);
        registration.setSkier(skier);
        assertThrows(IllegalArgumentException.class,
                () -> registrationServicesImpl.addRegistrationAndAssignToSkier(registration, 1L));
        verify(skierRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#assignRegistrationToCourse(Long, Long)}
     */
    @Test
    void testAssignRegistrationToCourse() {
        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);

        Registration registration = new Registration();
        registration.setCourse(course);
        registration.setNumWeek(10);
        registration.setSkier(skier);
        Optional<Registration> ofResult = Optional.of(registration);

        Course course2 = new Course();
        course2.setLevel(1);
        course2.setPrice(10.0f);
        course2.setRegistrations(new HashSet<>());
        course2.setSupport(Support.SKI);
        course2.setTimeSlot(1);
        course2.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);

        Registration registration2 = new Registration();
        registration2.setCourse(course2);
        registration2.setNumWeek(10);
        registration2.setSkier(skier2);
        when(registrationRepository.save(Mockito.<Registration>any())).thenReturn(registration2);
        when(registrationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Course course3 = new Course();
        course3.setLevel(1);
        course3.setPrice(10.0f);
        course3.setRegistrations(new HashSet<>());
        course3.setSupport(Support.SKI);
        course3.setTimeSlot(1);
        course3.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        Optional<Course> ofResult2 = Optional.of(course3);
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        assertSame(registration2, registrationServicesImpl.assignRegistrationToCourse(1L, 1L));
        verify(registrationRepository).save(Mockito.<Registration>any());
        verify(registrationRepository).findById(Mockito.<Long>any());
        verify(courseRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#assignRegistrationToCourse(Long, Long)}
     */
    @Test
    void testAssignRegistrationToCourse2() {
        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);

        Registration registration = new Registration();
        registration.setCourse(course);
        registration.setNumWeek(10);
        registration.setSkier(skier);
        Optional<Registration> ofResult = Optional.of(registration);
        when(registrationRepository.save(Mockito.<Registration>any())).thenThrow(new IllegalArgumentException("foo"));
        when(registrationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Course course2 = new Course();
        course2.setLevel(1);
        course2.setPrice(10.0f);
        course2.setRegistrations(new HashSet<>());
        course2.setSupport(Support.SKI);
        course2.setTimeSlot(1);
        course2.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        Optional<Course> ofResult2 = Optional.of(course2);
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        assertThrows(IllegalArgumentException.class, () -> registrationServicesImpl.assignRegistrationToCourse(1L, 1L));
        verify(registrationRepository).save(Mockito.<Registration>any());
        verify(registrationRepository).findById(Mockito.<Long>any());
        verify(courseRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#assignRegistrationToCourse(Long, Long)}
     */
    @Test
    void testAssignRegistrationToCourse3() {
        when(registrationRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> registrationServicesImpl.assignRegistrationToCourse(1L, 1L));
        verify(registrationRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#assignRegistrationToCourse(Long, Long)}
     */
    @Test
    void testAssignRegistrationToCourse4() {
        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);

        Registration registration = new Registration();
        registration.setCourse(course);
        registration.setNumWeek(10);
        registration.setSkier(skier);
        Optional<Registration> ofResult = Optional.of(registration);
        when(registrationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> registrationServicesImpl.assignRegistrationToCourse(1L, 1L));
        verify(registrationRepository).findById(Mockito.<Long>any());
        verify(courseRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#addRegistrationAndAssignToSkierAndCourse(Registration, Long, Long)}
     */
    @Test
    void testAddRegistrationAndAssignToSkierAndCourse() {
        when(registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(anyInt(),
                Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(3L);

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        Optional<Skier> ofResult = Optional.of(skier);
        when(skierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        Optional<Course> ofResult2 = Optional.of(course);
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        Course course2 = new Course();
        course2.setLevel(1);
        course2.setPrice(10.0f);
        course2.setRegistrations(new HashSet<>());
        course2.setSupport(Support.SKI);
        course2.setTimeSlot(1);
        course2.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);

        Registration registration = new Registration();
        registration.setCourse(course2);
        registration.setNumWeek(10);
        registration.setSkier(skier2);
        assertNull(registrationServicesImpl.addRegistrationAndAssignToSkierAndCourse(registration, 1L, 1L));
        verify(registrationRepository).countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(anyInt(),
                Mockito.<Long>any(), Mockito.<Long>any());
        verify(skierRepository).findById(Mockito.<Long>any());
        verify(courseRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#addRegistrationAndAssignToSkierAndCourse(Registration, Long, Long)}
     */
    @Test
    void testAddRegistrationAndAssignToSkierAndCourse2() {
        when(registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(anyInt(),
                Mockito.<Long>any(), Mockito.<Long>any()))
                .thenThrow(new IllegalArgumentException("No Skieur found with this id : "));

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        Optional<Skier> ofResult = Optional.of(skier);
        when(skierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        Optional<Course> ofResult2 = Optional.of(course);
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        Course course2 = new Course();
        course2.setLevel(1);
        course2.setPrice(10.0f);
        course2.setRegistrations(new HashSet<>());
        course2.setSupport(Support.SKI);
        course2.setTimeSlot(1);
        course2.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);

        Registration registration = new Registration();
        registration.setCourse(course2);
        registration.setNumWeek(10);
        registration.setSkier(skier2);
        assertThrows(IllegalArgumentException.class,
                () -> registrationServicesImpl.addRegistrationAndAssignToSkierAndCourse(registration, 1L, 1L));
        verify(registrationRepository).countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(anyInt(),
                Mockito.<Long>any(), Mockito.<Long>any());
        verify(skierRepository).findById(Mockito.<Long>any());
        verify(courseRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#addRegistrationAndAssignToSkierAndCourse(Registration, Long, Long)}
     */
    @Test
    void testAddRegistrationAndAssignToSkierAndCourse3() {
        when(registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(anyInt(),
                Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(0L);

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        Optional<Skier> ofResult = Optional.of(skier);
        when(skierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        Optional<Course> ofResult2 = Optional.of(course);
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        Course course2 = new Course();
        course2.setLevel(1);
        course2.setPrice(10.0f);
        course2.setRegistrations(new HashSet<>());
        course2.setSupport(Support.SKI);
        course2.setTimeSlot(1);
        course2.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);

        Registration registration = new Registration();
        registration.setCourse(course2);
        registration.setNumWeek(10);
        registration.setSkier(skier2);
        registrationServicesImpl.addRegistrationAndAssignToSkierAndCourse(registration, 1L, 1L);
        verify(registrationRepository).countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(anyInt(),
                Mockito.<Long>any(), Mockito.<Long>any());
        verify(skierRepository).findById(Mockito.<Long>any());
        verify(courseRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#addRegistrationAndAssignToSkierAndCourse(Registration, Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddRegistrationAndAssignToSkierAndCourse4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: No Skieur found with this id : 1
        //       at tn.esprit.SkiStationProject.services.RegistrationServicesImpl.addRegistrationAndAssignToSkierAndCourse(RegistrationServicesImpl.java:57)
        //   See https://diff.blue/R013 to resolve this issue.

        when(registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(anyInt(),
                Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(3L);
        when(skierRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Course course2 = new Course();
        course2.setLevel(1);
        course2.setPrice(10.0f);
        course2.setRegistrations(new HashSet<>());
        course2.setSupport(Support.SKI);
        course2.setTimeSlot(1);
        course2.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);

        Registration registration = new Registration();
        registration.setCourse(course2);
        registration.setNumWeek(10);
        registration.setSkier(skier);
        registrationServicesImpl.addRegistrationAndAssignToSkierAndCourse(registration, 1L, 1L);
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#addRegistrationAndAssignToSkierAndCourse(Registration, Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddRegistrationAndAssignToSkierAndCourse5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: No Skieur found with this id : 1
        //       at tn.esprit.SkiStationProject.services.RegistrationServicesImpl.addRegistrationAndAssignToSkierAndCourse(RegistrationServicesImpl.java:58)
        //   See https://diff.blue/R013 to resolve this issue.

        when(registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(anyInt(),
                Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(3L);

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        Optional<Skier> ofResult = Optional.of(skier);
        when(skierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(courseRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);

        Registration registration = new Registration();
        registration.setCourse(course);
        registration.setNumWeek(10);
        registration.setSkier(skier2);
        registrationServicesImpl.addRegistrationAndAssignToSkierAndCourse(registration, 1L, 1L);
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#numWeeksCourseOfInstructorBySupport(Long, Support)}
     */
    @Test
    void testNumWeeksCourseOfInstructorBySupport() {
        ArrayList<Integer> integerList = new ArrayList<>();
        when(registrationRepository.numWeeksCourseOfInstructorBySupport(Mockito.<Long>any(), Mockito.<Support>any()))
                .thenReturn(integerList);

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        Optional<Instructor> ofResult = Optional.of(instructor);
        when(instructorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Integer> actualNumWeeksCourseOfInstructorBySupportResult = registrationServicesImpl
                .numWeeksCourseOfInstructorBySupport(1L, Support.SKI);
        assertSame(integerList, actualNumWeeksCourseOfInstructorBySupportResult);
        assertTrue(actualNumWeeksCourseOfInstructorBySupportResult.isEmpty());
        verify(registrationRepository).numWeeksCourseOfInstructorBySupport(Mockito.<Long>any(), Mockito.<Support>any());
        verify(instructorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#numWeeksCourseOfInstructorBySupport(Long, Support)}
     */
    @Test
    void testNumWeeksCourseOfInstructorBySupport2() {
        when(registrationRepository.numWeeksCourseOfInstructorBySupport(Mockito.<Long>any(), Mockito.<Support>any()))
                .thenThrow(new IllegalArgumentException("foo"));

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        Optional<Instructor> ofResult = Optional.of(instructor);
        when(instructorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class,
                () -> registrationServicesImpl.numWeeksCourseOfInstructorBySupport(1L, Support.SKI));
        verify(registrationRepository).numWeeksCourseOfInstructorBySupport(Mockito.<Long>any(), Mockito.<Support>any());
        verify(instructorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#numWeeksCourseOfInstructorBySupport(Long, Support)}
     */
    @Test
    void testNumWeeksCourseOfInstructorBySupport3() {
        ArrayList<Integer> integerList = new ArrayList<>();
        when(registrationRepository.numWeeksCourseOfInstructorBySupport(Mockito.<Long>any(), Mockito.<Support>any()))
                .thenReturn(integerList);

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        HashSet<Course> courses = new HashSet<>();
        courses.add(course);

        Instructor instructor = new Instructor();
        instructor.setCourses(courses);
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        Optional<Instructor> ofResult = Optional.of(instructor);
        when(instructorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Integer> actualNumWeeksCourseOfInstructorBySupportResult = registrationServicesImpl
                .numWeeksCourseOfInstructorBySupport(1L, Support.SKI);
        assertSame(integerList, actualNumWeeksCourseOfInstructorBySupportResult);
        assertTrue(actualNumWeeksCourseOfInstructorBySupportResult.isEmpty());
        verify(registrationRepository).numWeeksCourseOfInstructorBySupport(Mockito.<Long>any(), Mockito.<Support>any());
        verify(instructorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#numWeeksCourseOfInstructorBySupport(Long, Support)}
     */
    @Test
    void testNumWeeksCourseOfInstructorBySupport4() {
        ArrayList<Integer> integerList = new ArrayList<>();
        when(registrationRepository.numWeeksCourseOfInstructorBySupport(Mockito.<Long>any(), Mockito.<Support>any()))
                .thenReturn(integerList);

        Course course = new Course();
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Course course2 = new Course();
        course2.setLevel(1);
        course2.setPrice(0.5f);
        course2.setRegistrations(new HashSet<>());
        course2.setSupport(Support.SNOWBOARD);
        course2.setTimeSlot(1);
        course2.setTypeCourse(TypeCourse.COLLECTIVE_ADULT);

        HashSet<Course> courses = new HashSet<>();
        courses.add(course2);
        courses.add(course);

        Instructor instructor = new Instructor();
        instructor.setCourses(courses);
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        Optional<Instructor> ofResult = Optional.of(instructor);
        when(instructorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Integer> actualNumWeeksCourseOfInstructorBySupportResult = registrationServicesImpl
                .numWeeksCourseOfInstructorBySupport(1L, Support.SKI);
        assertSame(integerList, actualNumWeeksCourseOfInstructorBySupportResult);
        assertTrue(actualNumWeeksCourseOfInstructorBySupportResult.isEmpty());
        verify(registrationRepository).numWeeksCourseOfInstructorBySupport(Mockito.<Long>any(), Mockito.<Support>any());
        verify(instructorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#numWeeksCourseOfInstructorBySupport(Long, Support)}
     */
    @Test
    void testNumWeeksCourseOfInstructorBySupport5() {
        when(instructorRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,
                () -> registrationServicesImpl.numWeeksCourseOfInstructorBySupport(1L, Support.SKI));
        verify(instructorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#numWeeksCourseOfInstructorBySupport(Long, Support)}
     */
    @Test
    void testNumWeeksCourseOfInstructorBySupport6() {
        ArrayList<Integer> integerList = new ArrayList<>();
        when(registrationRepository.numWeeksCourseOfInstructorBySupport(Mockito.<Long>any(), Mockito.<Support>any()))
                .thenReturn(integerList);

        Instructor instructor = new Instructor();
        instructor.setCourses(new HashSet<>());
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        Optional<Instructor> ofResult = Optional.of(instructor);
        when(instructorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Integer> actualNumWeeksCourseOfInstructorBySupportResult = registrationServicesImpl
                .numWeeksCourseOfInstructorBySupport(1L, Support.SNOWBOARD);
        assertSame(integerList, actualNumWeeksCourseOfInstructorBySupportResult);
        assertTrue(actualNumWeeksCourseOfInstructorBySupportResult.isEmpty());
        verify(registrationRepository).numWeeksCourseOfInstructorBySupport(Mockito.<Long>any(), Mockito.<Support>any());
        verify(instructorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link RegistrationServicesImpl#numWeeksCourseOfInstructorBySupport(Long, Support)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testNumWeeksCourseOfInstructorBySupport7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: foo
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:177)
        //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1620)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
        //       at tn.esprit.SkiStationProject.services.RegistrationServicesImpl.numWeeksCourseOfInstructorBySupport(RegistrationServicesImpl.java:123)
        //   See https://diff.blue/R013 to resolve this issue.

        when(registrationRepository.numWeeksCourseOfInstructorBySupport(Mockito.<Long>any(), Mockito.<Support>any()))
                .thenReturn(new ArrayList<>());
        Course course = mock(Course.class);
        when(course.getRegistrations()).thenThrow(new IllegalArgumentException("foo"));
        when(course.getSupport()).thenReturn(Support.SKI);
        doNothing().when(course).setLevel(anyInt());
        doNothing().when(course).setPrice(Mockito.<Float>any());
        doNothing().when(course).setRegistrations(Mockito.<Set<Registration>>any());
        doNothing().when(course).setSupport(Mockito.<Support>any());
        doNothing().when(course).setTimeSlot(anyInt());
        doNothing().when(course).setTypeCourse(Mockito.<TypeCourse>any());
        course.setLevel(1);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        HashSet<Course> courses = new HashSet<>();
        courses.add(course);

        Instructor instructor = new Instructor();
        instructor.setCourses(courses);
        instructor.setDateOfHire(LocalDate.of(1970, 1, 1));
        instructor.setFirstName("Jane");
        instructor.setLastName("Doe");
        Optional<Instructor> ofResult = Optional.of(instructor);
        when(instructorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        registrationServicesImpl.numWeeksCourseOfInstructorBySupport(1L, Support.SKI);
    }
}

