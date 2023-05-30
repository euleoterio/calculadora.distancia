package com.euleoterio.calculadora.distancia.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Paths {

    private String path;
    private Double distance;
    private Double extraCost;
}
