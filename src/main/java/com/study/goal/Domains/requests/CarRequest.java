package com.study.goal.domains.requests;

public record CarRequest(
        String model,
        String licensePlate,
        String manufacturingDate
) {}
