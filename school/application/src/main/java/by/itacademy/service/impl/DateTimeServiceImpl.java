package by.itacademy.service.impl;

import by.itacademy.service.DateTimeService;

import java.time.OffsetDateTime;

public class DateTimeServiceImpl implements DateTimeService {

    @Override
    public OffsetDateTime nowOffsetDateTime() {
        return OffsetDateTime.now();
    }
}
