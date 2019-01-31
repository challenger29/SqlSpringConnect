package com.example.demo;

import com.example.demo.entity.LeadDto;
import com.example.demo.entity.TrueCallerRequestToken;
import com.example.demo.service.CustomerServiceImpl;
import com.example.demo.service.TrueCallerTokenRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/truecaller")
public class TrueCallerController {
    
    @Autowired
    TrueCallerTokenRequester trueCallerTokenRequester;

    @Autowired
    CustomerServiceImpl service;


    @PostMapping(value = "/onboard")
    public ResponseEntity<LeadDto> onboardLead() {
//        LeadDto leadResponse = leadService.onboardLead(leadDto);
//        return ResponseEntity.status(HttpStatus.OK).body(leadResponse);
        System.out.println("came here");
        TrueCallerRequestToken request = trueCallerTokenRequester.request();
//            trueCallerTokenRequester.somefuncPublishEvent(request);

            service.fetchTruecallerDetails("919620473259");
        return null;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<TrueCallerRequestToken> add(@RequestBody TrueCallerRequestToken token){
        System.out.println("Came in save");
        token.setLastBlocked(new Date(System.currentTimeMillis() - 24*60*60*1000*2));
        trueCallerTokenRequester.save(token);
        System.out.println("saved");
        return null;
    }
}
