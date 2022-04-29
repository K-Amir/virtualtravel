package com.virtualtravel.empresa.Mail.Domain;

import com.virtualtravel.empresa.Booking.Application.Dto.BookingFormInputDto;
import com.virtualtravel.empresa.Booking.Domain.BookingEntity;

import java.util.HashMap;
import java.util.List;

public interface MailService {

    void sendSuccessEmail(BookingEntity bookingEntity);

    void sendCancellationEmail(BookingEntity bookingEntity, String reason);

    MailEntity registerMailEntity(MailEntity mailEntity);

    List<BookingEntity> getSentMails(HashMap<String, Object> params);

}
