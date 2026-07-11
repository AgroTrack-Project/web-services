package org.example.agrotrack.payments.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.agrotrack.payments.application.commandservices.PaymentIntentCommandService;
import org.example.agrotrack.payments.interfaces.rest.resource.CreatePaymentIntentResource;
import org.example.agrotrack.payments.interfaces.rest.transform.CreatePaymentIntentCommandFromResourceAssembler;
import org.example.agrotrack.payments.interfaces.rest.transform.PaymentIntentCreatedResourceAssembler;
import org.example.agrotrack.shared.interfaces.transform.ResponseEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment-intents", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Stripe payment intent creation for paid plans")
public class PaymentIntentsController {

    private final PaymentIntentCommandService paymentIntentCommandService;

    public PaymentIntentsController(PaymentIntentCommandService paymentIntentCommandService) {
        this.paymentIntentCommandService = paymentIntentCommandService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreatePaymentIntentResource resource) {
        var command = CreatePaymentIntentCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = paymentIntentCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                PaymentIntentCreatedResourceAssembler::toResource,
                HttpStatus.CREATED
        );
    }
}
