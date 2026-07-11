package org.example.agrotrack.payments.interfaces.rest.transform;

import org.example.agrotrack.payments.domain.model.commands.CreatePaymentIntentCommand;
import org.example.agrotrack.payments.interfaces.rest.resource.CreatePaymentIntentResource;

public class CreatePaymentIntentCommandFromResourceAssembler {

    private CreatePaymentIntentCommandFromResourceAssembler() {}

    public static CreatePaymentIntentCommand toCommandFromResource(CreatePaymentIntentResource resource) {
        return new CreatePaymentIntentCommand(resource.planType());
    }
}
