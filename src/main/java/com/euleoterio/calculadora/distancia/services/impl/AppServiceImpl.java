package com.euleoterio.calculadora.distancia.services.impl;

import com.euleoterio.calculadora.distancia.dtos.PathDto;
import com.euleoterio.calculadora.distancia.dtos.Paths;
import com.euleoterio.calculadora.distancia.models.ResponseModel;
import com.euleoterio.calculadora.distancia.services.AppService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AppServiceImpl implements AppService {
    @Override
    public ResponseModel execute(PathDto path) {

        int aux = 0;
        List<ResponseModel> responsePaths = new ArrayList<>();

        do {
            ResponseModel response = new ResponseModel();
            response.setBestPath("");
            response.setCost(0.0);
            ResponseModel responseAux = new ResponseModel();
            String position = path.getStart();
            List<Paths> paths = path.getPaths();

            for (int i = aux; i < paths.size(); i++) {
                Paths p = paths.get(i);
                List<Paths> auxPaths = path.getPaths();

                String[] points = p.getPath().split("|");
                String start = points[0];
                String endFirst = points[2];

                if (position.compareToIgnoreCase(start) == 0) {
                    response = calculate(response, p);
                    position = endFirst;
                    responseAux.setBestPath(response.getBestPath());
                    responseAux.setCost(response.getCost());

                    for (int j = i; j < auxPaths.size(); j++) {
                        points = auxPaths.get(j).getPath().split("|");
                        start = points[0];
                        String end = points[2];

                        if (position.compareToIgnoreCase(start) == 0) {
                            response = calculate(response, auxPaths.get(j));
                            position = end;
                            auxPaths.remove(j);

                            if (path.getEnd().compareToIgnoreCase(position) == 0) {
                                if (response.getCost() != 0.0)
                                    responsePaths.add(response);
                                position = endFirst;
                                j = 0;
                                response = responseAux;
                            }
                        }
                    }

                    if (path.getEnd().compareToIgnoreCase(position) == 0) {
                        if (response.getCost() != 0.0)
                            responsePaths.add(response);
                        break;
                    }
                }
            }
            aux++;
        } while (aux < path.getPaths().size());

        responsePaths.sort(Comparator.comparing(ResponseModel::getCost));
        responsePaths.get(0).setBestPath(responsePaths.get(0).getBestPath());

        return responsePaths.get(0);
    }

    private ResponseModel calculate(ResponseModel response, Paths p) {
        if (response.getBestPath().compareToIgnoreCase("") != 0)
            response.setBestPath(response.getBestPath() + "-");
        response.setBestPath(response.getBestPath() + p.getPath());
        response.setCost(response.getCost() + (p.getDistance() * 1.5 + p.getExtraCost()));

        return response;
    }
}
