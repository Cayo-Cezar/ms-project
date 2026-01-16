package com.cz.hr_payroll.resources;

import com.cz.hr_payroll.PaymentService.PaymentService;
import com.cz.hr_payroll.entities.Payment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.utils.FallbackMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.FallbackCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentResources {
    @Autowired
    private PaymentService service;

    @HystrixCommand(fallbackMethod = "getPaymentsAlternative")
    @GetMapping("/{workerid}/days/{days}")
    public ResponseEntity<Payment> getPayments(@PathVariable Long workerid, @PathVariable  Integer days){
        Payment payment = service.getPayment(workerid, days);
        return ResponseEntity.ok(payment);
    }

    public ResponseEntity<Payment> getPaymentsAlternative( Long workerid,  Integer days){
        Payment payment = new Payment("Bra", 400.0, days);
        return ResponseEntity.ok(payment);
    }
}
