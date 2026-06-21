package org.example.agrotrack.farming.application.queryservices;

import org.example.agrotrack.farming.domain.model.aggregates.Plot;
import org.example.agrotrack.farming.domain.model.queries.GetPlotByIdQuery;
import org.example.agrotrack.farming.domain.model.queries.ListPlotsQuery;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

import java.util.List;

public interface PlotQueryService {

    Result<List<Plot>, ApplicationError> handle(ListPlotsQuery query);

    Result<Plot, ApplicationError> handle(GetPlotByIdQuery query);
}
