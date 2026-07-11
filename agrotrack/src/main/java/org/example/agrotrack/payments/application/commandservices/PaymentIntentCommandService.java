package org.example.agrotrack.payments.application.commandservices;

import org.example.agrotrack.payments.domain.model.commands.CreatePaymentIntentCommand;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

public interface PaymentIntentCommandService {
    Result<String, ApplicationError> handle(CreatePaymentIntentCommand command);
}
