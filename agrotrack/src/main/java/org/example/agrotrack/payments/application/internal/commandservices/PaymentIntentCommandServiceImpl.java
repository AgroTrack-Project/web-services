package org.example.agrotrack.payments.application.internal.commandservices;

import org.example.agrotrack.payments.application.commandservices.PaymentIntentCommandService;
import org.example.agrotrack.payments.domain.model.commands.CreatePaymentIntentCommand;
import org.example.agrotrack.payments.domain.model.valueobjects.PlanPaymentAmount;
import org.example.agrotrack.payments.domain.ports.StripeGatewayPort;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;

@Service
public class PaymentIntentCommandServiceImpl implements PaymentIntentCommandService {

    private final StripeGatewayPort stripeGateway;

    public PaymentIntentCommandServiceImpl(StripeGatewayPort stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public Result<String, ApplicationError> handle(CreatePaymentIntentCommand command) {
        var planAmount = PlanPaymentAmount.from(command.planType());
        if (planAmount.isEmpty()) {
            return Result.failure(ApplicationError.validationError(
                    "planType",
                    "Plan '%s' does not require payment".formatted(command.planType())
            ));
        }
        var plan = planAmount.get();
        return stripeGateway.createPaymentIntent(plan.getAmountInCents(), plan.getCurrency(), command.planType());
    }
}
