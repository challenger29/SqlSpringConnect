package com.example.demo.eventlistener;

import com.example.demo.entity.TrueCallerRequestToken;
import org.springframework.context.ApplicationEvent;

public class TokenBlockEvent extends ApplicationEvent {
    private TrueCallerRequestToken token;
    public TokenBlockEvent(Object source , TrueCallerRequestToken token) {
        super(source);
        this.token = token;
    }

    public TrueCallerRequestToken getToken() {
        return token;
    }
}
