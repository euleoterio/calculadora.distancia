package com.euleoterio.calculadora.distancia.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PathDto {

    private String start;
    private String end;
    private List<Paths> paths;
}
