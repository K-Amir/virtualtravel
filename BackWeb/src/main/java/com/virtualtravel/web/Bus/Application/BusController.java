package com.virtualtravel.web.Bus.Application;


import com.virtualtravel.web.Bus.Application.Dto.BusInputDto;
import com.virtualtravel.web.Bus.Application.Dto.BusOutputDto;
import com.virtualtravel.web.Bus.Domain.BusEntity;
import com.virtualtravel.web.Bus.Domain.BusMapper;
import com.virtualtravel.web.Bus.Domain.BusService;
import com.virtualtravel.web.ErrorHandling.SuccessDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/web/buses")
public record BusController(BusService busService) {

    @PostMapping()
    public ResponseEntity<SuccessDto> saveBus(@RequestBody BusInputDto busInputDto){
        BusEntity busEntity = BusMapper.MAP.inputDtoToEntity(busInputDto);
        busService.createBus(busEntity);
        return SuccessDto.send("Bus created successfully :) !");
    }

    @GetMapping
    public ResponseEntity<List<BusOutputDto>> findAllBuses(){
       List<BusEntity> busEntityList = busService.findAllBuses();
       List<BusOutputDto> busOutputDtoList = BusMapper.MAP.inputListToOutput(busEntityList);
       return ResponseEntity.ok(busOutputDtoList);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<SuccessDto> deleteBusById(@PathVariable int id){
        busService.deleteBusById(id);
        return SuccessDto.send("Bus with id " + id  +" deleted successfully");
    }

    @GetMapping("{id}")
    public ResponseEntity<BusOutputDto> findBusById(@PathVariable int id){
        BusEntity busEntity = busService.findBusById(id);
        BusOutputDto busOutputDto = BusMapper.MAP.entityToOutput(busEntity);
        return  ResponseEntity.ok(busOutputDto);
    }

}
