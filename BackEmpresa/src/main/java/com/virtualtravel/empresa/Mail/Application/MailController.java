package com.virtualtravel.empresa.Mail.Application;

import com.virtualtravel.empresa.Auth.Infrastructure.JwtUtil;
import com.virtualtravel.empresa.Booking.Application.Dto.BookingOutputDto;
import com.virtualtravel.empresa.Booking.Domain.BookingEntity;
import com.virtualtravel.empresa.Booking.Domain.BookingMapper;
import com.virtualtravel.empresa.Booking.Domain.BookingService;
import com.virtualtravel.empresa.ErrorHandling.RequiredQueryParamException;
import com.virtualtravel.empresa.Mail.Application.Dto.MailInputDto;
import com.virtualtravel.empresa.Mail.Application.Dto.MailOutputDto;
import com.virtualtravel.empresa.Mail.Domain.MailEntity;
import com.virtualtravel.empresa.Mail.Domain.MailMapper;
import com.virtualtravel.empresa.Mail.Domain.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("empresa/v0/mails")
public record MailController(MailService mailService, JwtUtil jwtUtil, BookingService bookingService) {

    @GetMapping
    public ResponseEntity<?> getMails(@RequestHeader("Authorization") String auth, @RequestParam HashMap<String, Object> params) {
        jwtUtil.getSubject(auth);

        if (!params.containsKey("lowerDate")) {
            throw new RequiredQueryParamException("lowerDate param is required");
        }

        if (!params.containsKey("upperDate")) {
            throw new RequiredQueryParamException("upperDate param is required");
        }

        List<BookingEntity> bookingEntityList = mailService.getSentMails(params);

        List<MailOutputDto> mailOutputDtoList = new ArrayList<>();

        bookingEntityList.forEach(bookingEntity -> {
           mailOutputDtoList.add( MailMapper.MAP.bookingListToMailOutputDto(bookingEntity));
        });

        return ResponseEntity.ok(mailOutputDtoList);


    }

    @PutMapping
    public ResponseEntity<?> resendMail(@RequestHeader("Authorization") String auth, @RequestBody MailInputDto mailInputDto) {
        jwtUtil.getSubject(auth);


        mailInputDto.getEmail();

        BookingEntity bookingEntity  = bookingService.findBookingEntityByEmailIsAndCityIsAndDateIsAndHourIs(mailInputDto.getEmail(), mailInputDto.getCiudadDestino(), mailInputDto.getBookingDate(), mailInputDto.getBookingHour());

       BookingOutputDto output =  BookingMapper.MAP.bookingEntityToOutput(bookingEntity);

        return ResponseEntity.ok(output);
    }


}
