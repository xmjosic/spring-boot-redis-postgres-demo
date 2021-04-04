package hr.xmjosic.redisdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("On application event is OK");
        cacheManager.getCacheNames().parallelStream().forEach(n -> {
            System.out.println(n);
        });
    }
}
