package com.example.demo.service;

import com.example.demo.entity.TrueCallerRequestToken;
import com.example.demo.eventlistener.TokenBlockEvent;
import com.example.demo.service.TrueCallerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@EnableScheduling
@EnableAsync
@Component
public class TrueCallerTokenRequester implements ApplicationEventPublisherAware {
    @Autowired
    private TrueCallerRepository trueCallerTokenRepository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private boolean isReady = false;
    private int MAX_REFRESH = 4;
    private List<TrueCallerRequestToken> activeTokens = new ArrayList<>();

    @PostConstruct
    public void load(){
        System.out.println("loading...");
        Iterator<TrueCallerRequestToken> all = trueCallerTokenRepository.findAll().iterator();
        List<TrueCallerRequestToken> list = new ArrayList<>();
        all.forEachRemaining(list::add);
        System.out.println(list.size()) ;
        for(TrueCallerRequestToken token: list){
            if(token.isActive()){
                activeTokens.add(token);
            }
//
        }
//        while(all.hasNext()){
//            TrueCallerRequestToken token = all.next();
//            System.out.println("token is" + token.toString());
//            if(token.isActive()){
//                activeTokens.add(token);
//            }
//        }
        this.isReady = true;
    }

    public TrueCallerRequestToken request(){
        int currentRefreshRate =0;
        while(activeTokens.isEmpty() && currentRefreshRate < MAX_REFRESH){
            try{
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
            currentRefreshRate++;
        }
        if(activeTokens.isEmpty() )
            return null;
        else{
            return activeTokens.get(0);
            //TODO : save actively used token in a variable and always return that
        }
    }

//    @Scheduled(fixedDelay = 10000)
    public void refresh(){
        Iterable<TrueCallerRequestToken> all = trueCallerTokenRepository.findAll();
        while(all.iterator().hasNext()) {
            TrueCallerRequestToken token = all.iterator().next();
            Date now = new Date(System.currentTimeMillis() - 12*60*60*1000 );
            if(token.getLastBlocked().after(now)){
                activeTokens.add(token);
                //TODO :must sync with repo
            }
        }
    }

    @Async
    @EventListener
    public void handleBlock(TrueCallerRequestToken token){
        activeTokens.remove(token);
        token.setActive(false);
        token.setLastBlocked(new Date(System.currentTimeMillis()));
        trueCallerTokenRepository.save(token);
    }

    public void save(TrueCallerRequestToken tc){
        trueCallerTokenRepository.save(tc);
    }


    public void somefuncPublishEvent(TrueCallerRequestToken token){
        System.out.println("Publishing custom event");
        TokenBlockEvent tokenBlockEvent = new TokenBlockEvent(this ,token);
        applicationEventPublisher.publishEvent(tokenBlockEvent);

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void remove(TrueCallerRequestToken trueCaller) {
        activeTokens.remove(trueCaller);
    }
}
