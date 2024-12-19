package com.chat.avisutilisateur.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
public class Model{

    private String message;
    private int statusCode;
    private Instant date;
}
