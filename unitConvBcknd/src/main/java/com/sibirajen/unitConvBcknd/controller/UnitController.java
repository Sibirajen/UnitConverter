package com.sibirajen.unitConvBcknd.controller;

import com.sibirajen.unitConvBcknd.model.Request;
import com.sibirajen.unitConvBcknd.model.Response;
import com.sibirajen.unitConvBcknd.service.UnitService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UnitController {
    @PostMapping("/")
    public Response getData(@RequestBody Request request){
        System.out.println(request);
        return UnitService.getResponse(request);
    }
}
