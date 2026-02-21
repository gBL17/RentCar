package com.study.goal.domains.responses;

public record CarResponse(
        String model,
        String licensePlate,
        String manufacturingDate
) {
    @Override
    public String toString() {
        return "New Car Added: \n" +
                "model='" + model + ',' + '\n'+
                "licensePlate='" + licensePlate + ',' + "\n" +
                "manufacturingDate='" + manufacturingDate + ',' + "\n" +
                '}';
    }
}
