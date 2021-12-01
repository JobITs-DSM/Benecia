package com.jobits.dsm.benecia.global.error.exception;

import com.jobits.dsm.benecia.global.error.ErrorAttribute;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    private final ErrorAttribute errorAttribute;

    public GlobalException(ErrorAttribute errorAttribute) {
        super(errorAttribute.getMessage());
        this.errorAttribute = errorAttribute;
    }

}
