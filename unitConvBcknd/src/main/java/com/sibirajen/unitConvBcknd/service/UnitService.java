package com.sibirajen.unitConvBcknd.service;

import com.sibirajen.unitConvBcknd.model.Request;
import com.sibirajen.unitConvBcknd.model.Response;
import org.springframework.stereotype.Service;

@Service
public class UnitService {
    public static Response getResponse(Request request) {
        try {
            double result = UnitConvertor.getResult(request);
            return new Response(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return  new Response();
        }
    }
}
