package org.example.agrotrack.payments.domain.model.valueobjects;

import java.util.Arrays;
import java.util.Optional;

public enum PlanPaymentAmount {
    BASIC(3900L, "pen"),
    PRO(8500L, "pen"),
    ENTERPRISE(14900L, "pen");

    private final long amountInCents;
    private final String currency;

    PlanPaymentAmount(long amountInCents, String currency) {
        this.amountInCents = amountInCents;
        this.currency = currency;
    }

    public long getAmountInCents() { return amountInCents; }
    public String getCurrency() { return currency; }

    public static Optional<PlanPaymentAmount> from(String planType) {
        return Arrays.stream(values())
                .filter(p -> p.name().equalsIgnoreCase(planType))
                .findFirst();
    }
}
