package dasturlash.uz.handler;

import dasturlash.uz.exceptions.*;
import dasturlash.uz.exceptions.auth_related.ForbiddenException;
import dasturlash.uz.exceptions.auth_related.InvalidTokenException;
import dasturlash.uz.exceptions.auth_related.TokenExpiredException;
import dasturlash.uz.exceptions.auth_related.UnauthorizedException;
import dasturlash.uz.exceptions.card_related.CardNotFoundException;
import dasturlash.uz.exceptions.card_related.InsufficientBalanceException;
import dasturlash.uz.exceptions.card_related.InvalidCardStatusException;
import dasturlash.uz.exceptions.client_related.ClientNotFoundException;
import dasturlash.uz.exceptions.client_related.DuplicatePassportException;
import dasturlash.uz.exceptions.client_related.InvalidPassportNumberFormatException;
import dasturlash.uz.exceptions.company_related.*;
import dasturlash.uz.exceptions.profile_related.ProfileExistsException;
import dasturlash.uz.exceptions.profile_related.ProfileNotFoundException;
import dasturlash.uz.exceptions.profile_related.ProfileStatusException;
import dasturlash.uz.exceptions.transfer_related.InvalidTransferStatusException;
import dasturlash.uz.exceptions.transfer_related.TransferNotAllowedException;
import dasturlash.uz.exceptions.transfer_related.TransferNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = new LinkedList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    // Authentication related exceptions - 401
    @ExceptionHandler({
            UnauthorizedException.class,
            TokenExpiredException.class,
            InvalidTokenException.class
    })
    public ResponseEntity<?> handleUnauthorized(RuntimeException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }

    // Permission related exceptions - 403
    @ExceptionHandler({
            ForbiddenException.class,
            TransferNotAllowedException.class,
    })
    public ResponseEntity<?> handleForbidden(RuntimeException e) {
        return ResponseEntity.status(403).body(e.getMessage());
    }

    // Not found exceptions - 404
    @ExceptionHandler({
            CardNotFoundException.class,
            TransferNotFoundException.class,
            ClientNotFoundException.class,
            CompanyNotFoundException.class,
            ProfileNotFoundException.class

    })
    public ResponseEntity<?> handleNotFound(RuntimeException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    // Bad request exceptions - 400
    @ExceptionHandler({
            CompanyExistsException.class,
            IllegalArgumentException.class,
            InvalidPasswordException.class,
            InvalidCardStatusException.class,
            InsufficientBalanceException.class,
            InvalidTransferStatusException.class,
            DuplicatePassportException.class,
            DuplicateCompanyCodeException.class,
            CompanyStatusException.class,
            InvalidBankCodeException.class,
            ProfileExistsException.class,
            ProfileStatusException.class,
            InvalidPassportNumberFormatException.class
    })
    public ResponseEntity<?> handleBadRequest(RuntimeException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    // Server error - 500
    @ExceptionHandler(SomethingWentWrongException.class)
    public ResponseEntity<?> handleInternalError(SomethingWentWrongException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
}