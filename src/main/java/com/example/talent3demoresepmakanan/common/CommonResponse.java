package com.example.talent3demoresepmakanan.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommonResponse<T> {

    private Integer statusCode;
    private String message;
    private T data;
    private PagingResponse paging;


    public static CommonResponse toSave(String model) {
        return CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("save " + model + " telah sukses")
                .build();
    }

    public static CommonResponse toFindAll(String model, Object data) {
        return CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("find all " + model + " telah sukses")
                .data(data)
                .build();
    }

    public static CommonResponse toFindById(String model, Object data) {
        return CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("find one " + model + " telah sukses")
                .data(data)
                .build();
    }

    public static CommonResponse toUpdate(String model) {
        return CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("find all " + model + " telah sukses")
                .build();
    }

    public static CommonResponse toDelete(String model) {
        return CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("find all " + model + " telah sukses")
                .build();
    }
}
/*
     TODO : Inovasi 1 untuk CommonResponse, tetapi menurut saya menyelisihi aturan :
            SRP (Single Responsibility Principle) dimana
    public static CommonResponse toResponse(Integer number,String model){
        String msg ="";
        Integer code = 0;
        switch (number){
            case 1:
                msg = Common.SAVE_MSG;
                code = HttpStatus.CREATED.value();
                break;
            case 2:
                msg = Common.UPDATE_MSG;
                code = HttpStatus.OK.value();
                break;
            case 3:
                msg = Common.DELETE_MSG;
                code = HttpStatus.OK.value();
                break;
        }

        return  CommonResponse.builder()
                .statusCode(code)
                .message(msg + model + " sukses")
                .build();
    }
*/
