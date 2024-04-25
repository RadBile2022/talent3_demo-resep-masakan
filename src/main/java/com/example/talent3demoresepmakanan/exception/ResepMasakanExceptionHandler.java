package com.example.talent3demoresepmakanan.exception;


import com.example.talent3demoresepmakanan.common.ResepMasakanConstant;
import com.example.talent3demoresepmakanan.dto.ErrorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ResepMasakanExceptionHandler  extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(String code, String engMessage, String idnMessage, String message, HttpStatus httpStatus) {
        try {
            var errorDetail = ErrorDetail.builder()
                    .errorCode(code)
                    .engMessage(engMessage)
                    .idnMessage(idnMessage)
                    .message(message)
                    .build();
            return new ResponseEntity<>(errorDetail, httpStatus);
        } catch (Exception e) {
            return exception(e);
        }
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> dataNotFoundException(DataNotFoundException e) {
        log.info("Handling dataNotFoundException");
        return buildResponseEntity(ResepMasakanConstant.DATA_NOT_FOUND_ERROR_CODE,
                "Data not found",
                "Data tidak ditemukan",
                e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exception(Exception e) {
        log.error("Exception is UNCAUGHT, details : ", e);
        ErrorDetail errorDetail = ErrorDetail.builder().errorCode("0000").message(e.getMessage()).engMessage("Something went wrong").idnMessage("Terjadi kesalahan").build();
        return new ResponseEntity(errorDetail, HttpStatus.BAD_REQUEST);
    }


}
