package com.study.goal.controllers;

import com.study.goal.domains.responses.CarResponse;
import com.study.goal.repositories.CarRepository;
import com.study.goal.services.CarService;
import com.study.goal.domains.requests.CarRequest;
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
import java.time.format.DateTimeParseException;


@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService service;
    private final CarRepository repository;

    public CarController(CarService carService, CarRepository carRepository){
        this.service = carService;
        this.repository = carRepository;
    }

    @PostMapping()
    public ResponseEntity<Object> newCar(@RequestBody CarRequest request){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    service.newCar(request.model(), request.licensePlate(), request.manufacturingDate())
            );
        } catch (DateTimeParseException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: O formato da data deve ser dd-MM-yyyy.");
        } catch (IllegalArgumentException e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro nos dados: " + e.getMessage());
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCar(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    service.getCar(id)
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getCars(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    service.getCars()
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCar(@RequestBody String id){
        try {
            service.deleteCar(id);

            return ResponseEntity.status(HttpStatus.OK).body("Carro deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable String id, @RequestBody CarRequest car){
        try{
            CarResponse response =  service.updateCar(id, car);

            return ResponseEntity.status(HttpStatus.OK).body("Carro editado com sucesso \n" + response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> editCar(@PathVariable String id, @RequestBody CarRequest incompleteCar){
        try {
            CarResponse response = service.editCar(id, incompleteCar);

            return ResponseEntity.status(HttpStatus.OK).body("Carro atualizado com sucesso \n" + response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @GetMapping("/tudo")
    public ResponseEntity<Object> daOsId(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
