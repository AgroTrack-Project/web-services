package org.example.agrotrack.payments.interfaces.rest.transform;

import org.example.agrotrack.payments.interfaces.rest.resource.PaymentIntentCreatedResource;

public class PaymentIntentCreatedResourceAssembler {

    private PaymentIntentCreatedResourceAssembler() {}

    public static PaymentIntentCreatedResource toResource(String clientSecret) {
        return new PaymentIntentCreatedResource(clientSecret);
    }
}
