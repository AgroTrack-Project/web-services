package org.example.agrotrack.farming.application.queryservices;

import org.example.agrotrack.farming.domain.model.aggregates.Plot;
import org.example.agrotrack.farming.domain.model.queries.ListPlotsQuery;
import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;

import java.util.List;

/**
 * Application-layer port for plot reads. Kept separate from {@link
 * org.example.agrotrack.farming.application.commandservices.PlotCommandService} to follow
 * the project's CQRS split between reads and writes.
 */
public interface PlotQueryService {

    Result<List<Plot>, ApplicationError> handle(ListPlotsQuery query);
}
