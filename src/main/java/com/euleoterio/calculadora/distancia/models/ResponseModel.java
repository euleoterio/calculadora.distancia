package com.euleoterio.calculadora.distancia.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "root")
public class ResponseModel {

    private String bestPath;
    private Double cost;
}
