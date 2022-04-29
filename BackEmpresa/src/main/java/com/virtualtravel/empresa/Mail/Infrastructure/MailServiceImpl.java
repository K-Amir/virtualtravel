package com.virtualtravel.empresa.Mail.Infrastructure;


import com.virtualtravel.empresa.Booking.Application.Dto.BookingFormInputDto;
import com.virtualtravel.empresa.Booking.Domain.BookingEntity;
import com.virtualtravel.empresa.Booking.Domain.BookingService;
import com.virtualtravel.empresa.Incidence.Domain.IncidenceEntity;
import com.virtualtravel.empresa.Mail.Domain.MailEntity;
import com.virtualtravel.empresa.Mail.Domain.MailService;
import com.virtualtravel.empresa.Mail.Infrastructure.Jpa.MailJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Component
@AllArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    private JavaMailSender javaMailSender;
    private MailJpaRepository mailRepo;
    @PersistenceContext
    private EntityManager entityManager;

    public void sendSuccessEmail(BookingEntity bookingEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(bookingEntity.getEmail());
        message.setFrom("virtual.travel.info@gmail.com");
        message.setSubject("Good news ! Your booking to " + bookingEntity.getCity() + " on " + formatDate(bookingEntity.getDate()) + " at " + bookingEntity.getHour() + " has been registered successfully.");
        message.setText("Thanks for trusting in VirtualTravel! \nIn case of any incidence or cancellation you will be informed as well too");

        registerMailEntity(MailEntity.builder()
                .mailTo(bookingEntity.getEmail())
                .mailFrom("virtual.travel.info@gmail.com")
                .mailMessage("Thanks for trusting in VirtualTravel! \nIn case of any incidence or cancellation you will be informed as well too")
                .build());

        javaMailSender.send(message);
    }


    @Override
    public void sendCancellationEmail(BookingEntity bookingEntity, String reason) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("virtual.travel.info@gmail.com");
        message.setTo(bookingEntity.getEmail());
        message.setSubject("Bad news :C... Your booking to " + bookingEntity.getCity() + "  on " + formatDate(bookingEntity.getDate()) + " at " + bookingEntity.getHour() + " has been cancelled.");
        message.setText(reason);

        registerMailEntity(MailEntity.builder()
                .mailTo(bookingEntity.getEmail())
                .mailFrom("virtual.travel.info@gmail.com")
                .mailMessage(reason)
                .build());

        javaMailSender.send(message);
    }

    @Override
    public MailEntity registerMailEntity(MailEntity mailEntity) {
        return mailRepo.save(mailEntity);
    }

    @Override
    public List<BookingEntity> getSentMails(HashMap<String, Object> params) {
        List<MailEntity> mailEntityList = mailRepo.findAll();
        List<BookingEntity> bookingEntityList = new ArrayList<>();

        mailEntityList.forEach((email) -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BookingEntity> query = cb.createQuery(BookingEntity.class);
            Root<BookingEntity> root = query.from(BookingEntity.class);
            List<Predicate> predicates = new ArrayList<>();
            params.forEach((key, value) -> {
                switch (key) {
                    case "lowerDate":
                        try {
                            predicates.add(cb.greaterThanOrEqualTo(root.get("date"), (Date) new SimpleDateFormat("ddMMyyyy").parse((String) value)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "upperDate":
                        try {
                            predicates.add(cb.lessThan(root.get("date"), (Date) new SimpleDateFormat("ddMMyyyy").parse((String) value)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "lowerHour":
                        predicates.add(cb.greaterThanOrEqualTo(root.get("hour"), (String) value));
                        break;
                    case "upperHour":
                        predicates.add(cb.lessThan(root.get("hour"), (String) value));
                        break;

                }
            });
            predicates.add(cb.equal(root.get("email"), email.getMailTo()));

            query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

            try {
                BookingEntity bookingEntity = entityManager.createQuery(query).getSingleResult();
                bookingEntityList.add(bookingEntity);
            } catch (Exception e) {
                log.warn(e.getMessage());
            }


        });

        return bookingEntityList;
    }


    public String formatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}
