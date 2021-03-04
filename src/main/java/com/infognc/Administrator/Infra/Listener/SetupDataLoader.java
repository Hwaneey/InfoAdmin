package com.infognc.Administrator.Infra.Listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener {

    private boolean alreadySetup = false;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
    if (alreadySetup){
        return;
    }
    alreadySetup = true;
    }

}
