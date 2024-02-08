package com.coursehub.service;

import com.coursehub.dto.request.BookingDto;
import com.coursehub.exception.ApiException;
import com.coursehub.model.Booking;
import com.coursehub.model.Course;
import com.coursehub.model.User;
import com.coursehub.repositories.BookingRepository;
import com.coursehub.repositories.CourseRepository;
import com.coursehub.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }




    public void bookCourse(BookingDto bookingDto){

        User user = userRepository.findById(bookingDto.getUser()).orElseThrow( () -> new ApiException("User not found", HttpStatus.NOT_FOUND));
        Course course = courseRepository.findById(bookingDto.getCourse()).orElseThrow( () -> new ApiException("Course not found", HttpStatus.NOT_FOUND));
        log.info("Product received by userId -: " + bookingDto.getUser());

        boolean alreadyExists = bookingRepository.existsByUserAndCourse(user.getUserId(), course);
        if (alreadyExists) {
            throw new ApiException("Course has been booked", HttpStatus.ALREADY_REPORTED);
        }

        Booking booking = Booking.builder()
                .course(course)
                .user(bookingDto.getUser())
                .totalCost(bookingDto.getTotalCost())
                .bookingDateTime(LocalDateTime.now())
                .build();

    }
}
