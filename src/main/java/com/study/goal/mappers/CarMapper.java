package com.study.goal.mappers;

import com.study.goal.domains.Car;
import com.study.goal.domains.responses.CarResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarMapper {
    public CarResponse map(Car car){
        return new CarResponse(
                car.getModel(),
                car.getLicensePlate(),
                car.getManufacturingDate().toString()
        );
    }

    public List<CarResponse> map(List<Car> cars){
        List<CarResponse> result = new ArrayList<>();

        for (Car car : cars) {
            result.add(
                new CarResponse(
                        car.getModel(),
                        car.getLicensePlate(),
                        car.getManufacturingDate().toString()
                )
            );
        }

        return result;
    }

}
