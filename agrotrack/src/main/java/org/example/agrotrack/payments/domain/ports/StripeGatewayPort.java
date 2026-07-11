package org.example.agrotrack.payments.domain.ports;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

public interface StripeGatewayPort {
    Result<String, ApplicationError> createPaymentIntent(long amountInCents, String currency, String planType);
}
