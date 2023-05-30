package com.euleoterio.calculadora.distancia.controllers;

import com.euleoterio.calculadora.distancia.dtos.PathDto;
import com.euleoterio.calculadora.distancia.models.ResponseModel;
import com.euleoterio.calculadora.distancia.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600 )
@RequestMapping("/")
public class AppController {

    @Autowired
    AppService appService;

    @PostMapping
    public ResponseEntity<ResponseModel> getEconomicPath(@RequestBody PathDto path){

        ResponseModel response = appService.execute(path);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
