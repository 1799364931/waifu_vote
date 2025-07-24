package com.example.galgame_vote.pojo;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;
    
    public ResponseMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseMessage<T> error(T data, String message, Integer code) {
        return new ResponseMessage<>(code, message, data);
    }

    //接口请求成功
    public static <T> ResponseMessage<T> success(T data, String message) {
        return new ResponseMessage<T>(HttpStatus.OK.value(), message, data);
    }

    public static <T> ResponseMessage<T> fail(T data, String message) {
        return new ResponseMessage<T>(HttpStatus.EXPECTATION_FAILED.value(), message, data);
    }

}