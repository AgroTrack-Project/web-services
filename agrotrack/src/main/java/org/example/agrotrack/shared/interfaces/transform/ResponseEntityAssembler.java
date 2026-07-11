package org.example.agrotrack.shared.interfaces.transform;

import org.example.agrotrack.shared.result.ApplicationError;
import org.example.agrotrack.shared.result.Result;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

public final class ResponseEntityAssembler {

    private ResponseEntityAssembler() {}

    public static <T, R> ResponseEntity<?> toResponseEntityFromResult(
            Result<T, ApplicationError> result,
            Function<T, R> successResourceAssembler,
            HttpStatusCode successStatus
    ) {
        return switch (result) {
            case Result.Success<T, ApplicationError> success ->
                    new ResponseEntity<>(successResourceAssembler.apply(success.value()), successStatus);
            case Result.Failure<T, ApplicationError> failure ->
                    ErrorResponseAssembler.toErrorResponseFromApplicationError(failure.error());
        };
    }
}
