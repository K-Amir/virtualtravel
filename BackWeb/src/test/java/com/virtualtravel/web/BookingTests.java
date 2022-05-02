package com.virtualtravel.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualtravel.web.Bookings.Application.BookingController;
import com.virtualtravel.web.Bookings.Application.Dto.BookingFormInputDto;
import com.virtualtravel.web.Bookings.Domain.BookingRegistryEntity;
import com.virtualtravel.web.Bookings.Domain.BookingRegistryService;
import com.virtualtravel.web.Bookings.Domain.CompanyFeignClient;
import com.virtualtravel.web.Bus.Domain.BusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
@RunWith(SpringRunner.class)
public class BookingTests {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingRegistryService bookingRegistryService;

    @MockBean
    private BusService busService;

    @MockBean
    private CompanyFeignClient companyFeignClient;




    @Test
    public void registerBooking() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        BookingFormInputDto mockBook =
                BookingFormInputDto.builder()
                        .city("Barcelona")
                        .date(formatter.parse("02-04-2022"))
                        .email("test@gmx.es")
                        .hour("19:00")
                        .phone("00000000")
                        .name("Hola")
                        .build();

        mockMvc.perform(post("/api/v0/bookings")
                .content(objectMapper.writeValueAsString(mockBook))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}
