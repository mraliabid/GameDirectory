package com.app.user.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class CommonUtil {

    public static <T> ResponseEntity<T>  getResponseEntity(Integer responseCode, T response){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(!ObjectUtils.isEmpty(responseCode)){
            status = HttpStatus.valueOf(responseCode);
        }
        return new ResponseEntity<T>(response, status);
    }

}
