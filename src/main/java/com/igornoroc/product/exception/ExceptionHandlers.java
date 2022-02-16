package com.igornoroc.product.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler({AuthenticationException.class, AdminNotFoundException.class})
    public ResponseEntity<?> errorAuthentication() {
        log.warn("invalid admin login or password");
        return ResponseEntity.badRequest().body("login or password is invalid");
    }

    @ExceptionHandler({
            ProductNotFoundException.class,
            ProductHaveAnotherLanguageOrPriceTypeException.class,
            MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> notFoundProduct() {
        log.error("ProductNotFoundException: product not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(
                "404",
                "product not found, please check request data"));
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductForAdminNotFoundException.class)
    public void adminNotFoundProduct() {
        log.error("AdminService(method: findById) : product not found");
    }
}
