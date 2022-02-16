package com.igornoroc.product.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Error {
    private String errorCode;
    private String errorMessage;
}
