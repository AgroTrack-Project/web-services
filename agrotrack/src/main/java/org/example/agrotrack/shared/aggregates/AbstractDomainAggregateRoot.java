package org.example.agrotrack.shared.aggregates;

import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Collection;

public abstract class AbstractDomainAggregateRoot<T extends AbstractDomainAggregateRoot<T>>
        extends AbstractAggregateRoot<T> {

    protected void registerDomainEvent(Object domainEvent) {
        super.registerEvent(domainEvent);
    }

    @Override
    public Collection<Object> domainEvents() {
        return super.domainEvents();
    }

    @Override
    public void clearDomainEvents() {
        super.clearDomainEvents();
    }
}
