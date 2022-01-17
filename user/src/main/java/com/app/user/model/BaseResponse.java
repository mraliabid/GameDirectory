package com.app.user.model;

public class BaseResponse {

    private Integer responseCode;
    private String responseText;

    public BaseResponse() {
    }

    public BaseResponse(Integer responseCode, String responseText) {
        this.responseCode = responseCode;
        this.responseText = responseText;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "responseCode=" + responseCode +
                ", responseText='" + responseText + '\'' +
                '}';
    }
}
