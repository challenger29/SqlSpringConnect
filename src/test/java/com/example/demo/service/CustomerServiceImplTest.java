package com.example.demo.service;

import com.example.demo.entity.TrueCallerRequestToken;
import com.example.demo.entity.TrueCallerRespDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.Date;

import static org.junit.Assert.*;

public class CustomerServiceImplTest {
    CustomerServiceImpl customerService;
    TrueCallerTokenRequester trueCallerTokenRequester;
    TrueCallerRequestToken tokenActive;
    TrueCallerRequestToken tokenInactive;

    @Before
    public void setUp() throws Exception {
        trueCallerTokenRequester = Mockito.mock(TrueCallerTokenRequester.class);
        tokenActive  = new TrueCallerRequestToken("lS5c502970e134a50001035749FWDMEclV1VVGs3JztS32Hn" , "929068435" ,  "929068435",true, new Date(System.currentTimeMillis() - 24*4*60*60*1000 ));
        tokenInactive  = new TrueCallerRequestToken("lS59b111e58bd18b0766ce0ba6XDxXUCVdQIkCzbWJ8uul87" , "616267609" ,  "616267609",true, new Date(System.currentTimeMillis() - 24*4*60*60*1000 ));
        customerService = new CustomerServiceImpl(trueCallerTokenRequester , new RestTemplateBuilder());

    }

    @Test
    public void fetchTruecallerDetails() {
        String phoneNo = "919620473259";
        Mockito.when(trueCallerTokenRequester.request()).thenReturn(tokenInactive);
        TrueCallerRespDto response = customerService.fetchTruecallerDetails(phoneNo);
        System.out.println(response.toString());
//        assertEquals(response.getData());

    }
}