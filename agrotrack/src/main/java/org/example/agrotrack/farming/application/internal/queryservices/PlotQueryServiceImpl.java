package org.example.agrotrack.farming.application.internal.queryservices;

import org.example.agrotrack.farming.application.queryservices.PlotQueryService;
import org.example.agrotrack.farming.domain.model.aggregates.Plot;
import org.example.agrotrack.farming.domain.model.queries.ListPlotsQuery;
import org.example.agrotrack.farming.domain.repositories.PlotRepository;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlotQueryServiceImpl implements PlotQueryService {

    private final PlotRepository repository;

    public PlotQueryServiceImpl(PlotRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Result<List<Plot>, ApplicationError> handle(ListPlotsQuery query) {
        String userId = query.userId();

        if (userId != null && !userId.isBlank()) {
            return Result.success(repository.findByUserId(userId.trim()));
        }

        return Result.success(repository.findAll());
    }
}
