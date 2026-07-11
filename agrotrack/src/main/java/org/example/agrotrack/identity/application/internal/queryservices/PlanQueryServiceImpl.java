package org.example.agrotrack.identity.application.internal.queryservices;

import org.example.agrotrack.identity.application.queryservices.PlanQueryService;
import org.example.agrotrack.identity.domain.model.aggregates.Plan;
import org.example.agrotrack.identity.domain.model.queries.ListPlansQuery;
import org.example.agrotrack.identity.domain.repositories.PlanRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanQueryServiceImpl implements PlanQueryService {

    private final PlanRepository repository;

    public PlanQueryServiceImpl(PlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<List<Plan>, ApplicationError> handle(ListPlansQuery query) {
        return Result.success(repository.findAll());
    }
}
