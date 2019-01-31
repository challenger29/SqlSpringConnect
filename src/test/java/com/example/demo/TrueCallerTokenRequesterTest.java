package com.example.demo;

import com.example.demo.service.TrueCallerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TrueCallerTokenRequesterTest {
    private TrueCallerRepository trueCallerTokenRepository;


    @Before
    public void setUp() throws Exception {
        trueCallerTokenRepository = Mockito.mock(TrueCallerRepository.class);
    }

    @Test
    public void load() {
    }

    @Test
    public void refresh() {
    }

    @Test
    public void handleBlock() {
    }

    @Test
    public void somefuncPublishEvent() {
    }

    @Test
    public void remove() {
    }
}