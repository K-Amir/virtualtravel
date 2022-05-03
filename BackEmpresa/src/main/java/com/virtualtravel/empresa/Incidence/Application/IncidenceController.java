package com.virtualtravel.empresa.Incidence.Application;

import com.virtualtravel.empresa.Booking.Domain.BookingEntity;
import com.virtualtravel.empresa.Booking.Domain.BookingService;
import com.virtualtravel.empresa.ErrorHandling.SuccessDto;
import com.virtualtravel.empresa.Incidence.Application.Dto.IncidenceInputDto;
import com.virtualtravel.empresa.Incidence.Application.Dto.IncidenceOutputDto;
import com.virtualtravel.empresa.Incidence.Domain.IncidenceEntity;
import com.virtualtravel.empresa.Incidence.Domain.IncidenceMapper;
import com.virtualtravel.empresa.Incidence.Domain.IncidenceService;
import com.virtualtravel.empresa.Mail.Domain.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("empresa/v0/incidences")
public record IncidenceController(IncidenceService incidenceService, BookingService bookingService, MailService mailService) {

    @PostMapping
    public ResponseEntity<SuccessDto> createIncidence(@RequestBody @Valid IncidenceInputDto incidenceInputDto){
        IncidenceEntity incidenceEntity = IncidenceMapper.MAPPER.incidenceInputToEntity(incidenceInputDto);
        incidenceService.save(incidenceEntity, incidenceInputDto.getBus_id());
        sendCancellationMails(bookingService.findBookingEntitiesByBusEntity_Id(incidenceInputDto.getBus_id()), incidenceEntity);
        return SuccessDto.send("Incidence registered successfully :(");


    }

    @GetMapping("{id}")
    public ResponseEntity<IncidenceOutputDto>findIncidenceById(@PathVariable int id){
        IncidenceEntity incidenceEntity = incidenceService.findById(id);
        IncidenceOutputDto incidenceOutputDto = IncidenceMapper.MAPPER.incidenceToOutput(incidenceEntity);
        return ResponseEntity.ok(incidenceOutputDto);
    }

    @GetMapping()
    public ResponseEntity<List<IncidenceOutputDto>> findAllIncidences(){
        List<IncidenceEntity> incidenceEntityList = incidenceService.findAll();
        List<IncidenceOutputDto> output = IncidenceMapper.MAPPER.incidenceToOutputList(incidenceEntityList);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SuccessDto> deleteIncidenceById(@PathVariable int id){
        incidenceService.deleteById(id);
        return SuccessDto.send("Incidence with id " + id+" deleted successfully");
    }

    private void sendCancellationMails(List<BookingEntity> bookingEntityList, IncidenceEntity incidenceEntity){
        for (BookingEntity bookingEntity:bookingEntityList) {
            mailService.sendCancellationEmail(bookingEntity, incidenceEntity.getReason());
        }
    }

}
