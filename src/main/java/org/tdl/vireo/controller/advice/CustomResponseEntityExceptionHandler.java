package org.tdl.vireo.controller.advice;

import static edu.tamu.weaver.response.ApiStatus.ERROR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.tdl.vireo.exception.SwordDepositException;

import edu.tamu.weaver.response.ApiResponse;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
    @ResponseBody
    public ApiResponse handleMultipartException(MultipartException exception) {
        logger.debug("File size limit exceeded", exception);
        return new ApiResponse(ERROR, "File size limit exceeded");
    }

    @ExceptionHandler(SwordDepositException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public ApiResponse handleSwordDepositException(SwordDepositException exception) {
        logger.debug(exception.getMessage(), exception);
        return new ApiResponse(ERROR, exception.getMessage());
    }

}
