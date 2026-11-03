package com.ticketflow.customerservice.controllers;

import com.ticketflow.customerservice.helper.InstanceHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class LoadBalancerTestController {

    private final InstanceHelper instanceHelper;

    public LoadBalancerTestController(InstanceHelper instanceHelper) {
        this.instanceHelper = instanceHelper;
    }

    @GetMapping("/instance")
    public String test() {
        return instanceHelper.identifyInstance();
    }
}