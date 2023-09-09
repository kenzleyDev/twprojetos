package br.com.treinaweb.twprojetos.api.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerApi extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErro> handleEntityNotFoundException(EntityNotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiErro apiErro = new ApiErro(
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                LocalDateTime.now(),
                exception.getLocalizedMessage()
        );
        return new ResponseEntity<>(apiErro, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<CampoErro> erros = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            CampoErro campoErro = new CampoErro(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            );

            erros.add(campoErro);
        });

        ValidationApiError validationApiError = new ValidationApiError(
                status.value(),
                status.getReasonPhrase(),
                LocalDateTime.now(),
                "Houveram erros de validação",
                erros
        );

        return new ResponseEntity<>(validationApiError, status);
    }
}
