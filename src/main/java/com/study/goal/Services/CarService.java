package com.study.goal.services;

import com.study.goal.domains.Car;
import com.study.goal.domains.requests.CarRequest;
import com.study.goal.domains.responses.CarResponse;
import com.study.goal.exceptions.ResourceNotFoundException;
import com.study.goal.repositories.CarRepository;
import com.study.goal.mappers.CarMapper;
import com.study.goal.utils.DataFormatter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository repository;
    private final DataFormatter formatter;
    private final CarMapper mapper;

    public CarService( DataFormatter formatter, CarMapper mapper, CarRepository repository ){
        this.repository = repository;
        this.formatter = formatter;
        this.mapper = mapper;
    }

    public CarResponse newCar( CarRequest car ){
        return mapper.map(
                repository.insert( new Car(car.model(), car.licensePlate(), formatter.parse( car.manufacturingDate() ))));
    }

    public CarResponse getCar(String id) {
        return repository.findById(id).map(mapper::map)
                .orElseThrow(() -> new ResourceNotFoundException( "User not found with id: " + id ) );
    }

    public List<CarResponse> getCars() {
        return mapper.map( repository.findAll());
    }

    public void deleteCar( String id ){
        repository.deleteById( id );
    }

    public CarResponse updateCar( String id, CarRequest newCar ) {
        repository.findById(id).orElseThrow( () -> new ResourceNotFoundException( "User not found with id: " + id ) );

        return mapper.map(
                repository.save(
                        new Car( newCar.model() , newCar.licensePlate(), formatter.parse( newCar.manufacturingDate() ) ))) ;
    }

    public CarResponse editCar(String id, CarRequest request) {
        Car car = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException( "User not found with id: " + id));

        if (request.model() != null){
            car.setModel(request.model());
        }
        if (request.licensePlate() != null){
            car.setLicensePlate(request.licensePlate());
        }
        if (request.manufacturingDate() != null){
            car.setManufacturingDate(formatter.parse(request.manufacturingDate()));
        }

        return mapper.map(repository.save(car));
    }
}
