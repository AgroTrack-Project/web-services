package org.example.agrotrack.payments.infrastructure.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.net.RequestOptions;
import com.stripe.param.PaymentIntentCreateParams;
import org.example.agrotrack.payments.domain.ports.StripeGatewayPort;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripeGatewayAdapter implements StripeGatewayPort {

    @Value("${stripe.secret.key}")
    private String secretKey;

    @Override
    public Result<String, ApplicationError> createPaymentIntent(long amountInCents, String currency, String planType) {
        try {
            var params = PaymentIntentCreateParams.builder()
                    .setAmount(amountInCents)
                    .setCurrency(currency)
                    .addPaymentMethodType("card")
                    .putMetadata("plan_type", planType)
                    .build();

            var options = RequestOptions.builder()
                    .setApiKey(secretKey)
                    .build();

            var intent = PaymentIntent.create(params, options);
            return Result.success(intent.getClientSecret());
        } catch (StripeException e) {
            return Result.failure(ApplicationError.unexpected("Stripe", e.getMessage()));
        }
    }
}
