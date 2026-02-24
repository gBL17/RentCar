package com.study.goal.controllers;

import com.study.goal.services.CarService;
import com.study.goal.domains.requests.CarRequest;
import com.study.goal.domains.responses.CarResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService service;

    public CarController(CarService carService){
        this.service = carService;
    }

    @PostMapping()
    public ResponseEntity<CarResponse> newCar(@RequestBody CarRequest request){
            return ResponseEntity.status( HttpStatus.CREATED )
                    .body( service.newCar( request ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCar(@PathVariable String id){
        return ResponseEntity.status( HttpStatus.OK )
                .body( service.getCar(id) );
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> getCars(){
        return ResponseEntity.ok( service.getCars() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable String id){
        service.deleteCar(id);
        return ResponseEntity.status( HttpStatus.OK )
                .body( "Carro deletado com sucesso" );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable String id, @RequestBody CarRequest car){
        return ResponseEntity.status( HttpStatus.OK )
                .body( service.updateCar( id, car ));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarResponse> editCar(@PathVariable String id, @RequestBody CarRequest incompleteCar) {
        return ResponseEntity.status( HttpStatus.OK )
                .body( service.editCar( id, incompleteCar ));
    }

}