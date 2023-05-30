package com.euleoterio.calculadora.distancia.services;

import com.euleoterio.calculadora.distancia.dtos.PathDto;
import com.euleoterio.calculadora.distancia.models.ResponseModel;

public interface AppService {

    ResponseModel execute(PathDto path);
}
