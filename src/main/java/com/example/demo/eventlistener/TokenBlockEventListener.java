package com.example.demo.eventlistener;

import com.example.demo.service.TrueCallerRepository;
import com.example.demo.entity.TrueCallerRequestToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenBlockEventListener implements ApplicationListener<TokenBlockEvent> {

    @Autowired
    TrueCallerRepository trueCallerRepository;

    @Override
    public void onApplicationEvent(TokenBlockEvent tokenBlockEvent) {
        System.out.println("In event handler");
        TrueCallerRequestToken token = tokenBlockEvent.getToken();
        token.setActive(false);
        token.setLastBlocked(new Date(System.currentTimeMillis()));
        trueCallerRepository.save(token);
        System.out.println("changes modified");

    }
}
