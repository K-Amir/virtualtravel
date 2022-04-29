package com.virtualtravel.web.ErrorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;


@AllArgsConstructor
@Data
public class SuccessDto {
    private String message;
    private int statusCode = 200;
    public static ResponseEntity<SuccessDto> send(String message){
        return ResponseEntity.ok().body(new SuccessDto(message,200));
    }
}
