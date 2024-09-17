package org.itstep.msk.app.exeption;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ExceptionMessagePayload {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private final ZonedDateTime dateTime;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Throwable throwable;
    private final HttpStatus status;
    private final String message;
}
