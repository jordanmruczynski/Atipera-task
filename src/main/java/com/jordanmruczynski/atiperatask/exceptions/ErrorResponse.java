package com.jordanmruczynski.atiperatask.exceptions;

public record ErrorResponse(
        int status,
        String Message) {
}
