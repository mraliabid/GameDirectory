package com.app.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestMessagingSvc {


    @Autowired
    RestTemplate restTemplate;

    public <T> T sendGetRequest(String url, Class<T> responseType) {

        T resp = null;
        try {
            resp = restTemplate.getForObject(url, responseType);
        }catch (Exception e){

        }
        return resp;
    }


    public <E, T> T sendPostRequest(E request, String url, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return sendRequest(headers, request, url, responseType, HttpMethod.POST);
    }

    public <E, T> T sendRequest(HttpHeaders headers, E request, String url, Class<T> responseType, HttpMethod method) {
        HttpEntity<E> responseEntity = new HttpEntity<>(request, headers);

        T resp = null;
        try {
            resp = restTemplate.exchange(url, method, responseEntity, responseType).getBody();
        }catch (Exception e){

        }
        return resp;

    }
}