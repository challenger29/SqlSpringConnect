package com.example.demo.service;

import com.example.demo.entity.TrueCallerRequestToken;
import com.example.demo.entity.TrueCallerRespDto;
import com.sun.tools.internal.ws.wsdl.framework.DuplicateEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CustomerServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private HttpHeaders headers;

    private TrueCallerTokenRequester trueCallerTokenRequester;

    private final int MAX_TRIES = 5;

    private final RestTemplate restTemplate;

    public CustomerServiceImpl(TrueCallerTokenRequester trueCallerTokenRequester, RestTemplateBuilder restTemplateBuilder) {
//        this.headers = headers;
        this.trueCallerTokenRequester = trueCallerTokenRequester;
        this.restTemplate = restTemplateBuilder.setConnectTimeout(10000)
                .setReadTimeout(20000).build();
    }

    public TrueCallerRespDto fetchTruecallerDetails(String phoneNo) {
        log.info("Fetching details from truecaller for customer");
        System.out.println("Fetching details from truecaller for customer");
        TrueCallerRequestToken trueCaller = null;
        try {
            trueCaller = trueCallerTokenRequester.request();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int currentTries = 0 ;
        if(trueCaller == null){
            trueCallerTokenRequester.refresh();
        }
        while(trueCaller != null && currentTries < MAX_TRIES){
            //https://search5.truecaller.com/v2/search?q=918904524527&countryCode='IN'&type=4&placement=SEARCHRESULTS,HISTORY,DETAILS&clientId=1&myNumber=lS5c502970e134a50001035749FWDMEclV1VVGs3JztS32Hn&registerId=929068435&encoding=json HTTP/1.1
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://search5.truecaller.com/v2/search").path("/")
                    .queryParam("countryCode", "IN")
                    .queryParam("type","4")
                    .queryParam("placement","SEARCHRESULTS,HISTORY,DETAILS")
                    .queryParam("encoding","json HTTP/1.1")
                    .queryParam("myNumber", trueCaller.getMyNumber())
                    .queryParam("registerId" , trueCaller.getRegisterId())
                    .queryParam("q", phoneNo)
                    .queryParam("clientId" , 1);
            ResponseEntity<TrueCallerRespDto> response = null;
            try {
                HttpEntity<String> request = new HttpEntity<>(new String(), headers);
                response =   restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, TrueCallerRespDto.class);
                System.out.println(response);
                if(response.getStatusCode().equals(HttpStatus.OK))
                    return response.getBody();
                else if (response.getStatusCode().equals(HttpStatus.FORBIDDEN)){
                    trueCallerTokenRequester.remove(trueCaller);
                    trueCallerTokenRequester.handleBlock(trueCaller);
                    trueCaller = trueCallerTokenRequester.request();
                }

            } catch (HttpClientErrorException ex) {
                handleFailure("truecallersearchapi", ex);
            } catch (Exception ex) {
                log.error("Failed to fetch truecaller details: " + ex.getMessage());
                System.out.println("in exception");
                if (response != null && response.getStatusCode().equals(HttpStatus.FORBIDDEN)){
                    trueCallerTokenRequester.remove(trueCaller);
                    trueCallerTokenRequester.handleBlock(trueCaller);
                    trueCaller = trueCallerTokenRequester.request();
                }
            }

            currentTries++;
        }
        return null;
    }


    private void handleFailure(String operation, HttpClientErrorException ex) throws DuplicateEntityException {
        log.error("Operation: [{}] Received error status: [{}], message: [{}] from Customer Service. Error: [{}]", operation,
                ex.getRawStatusCode(), ex.getLocalizedMessage(), ex.getResponseBodyAsString());
        if (ex.getStatusCode() == HttpStatus.CONFLICT) {
            System.out.println("conflict came");
//            throw new DuplicateEntityException(ex.getMessage());
        } else if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            System.out.println("not found");
//            throw new ResourceNotFoundException(ex.getMessage());
        } else if (ex.getStatusCode() == HttpStatus.FORBIDDEN) {
            System.out.println("forbidden error");
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

}
