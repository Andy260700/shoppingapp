package com.arpannandi.shoppingapp.listener;

import org.springframework.context.annotation.Bean;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

//@WebListener
public class SessionBehaviourTracking implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HashSet<String> phrases = new HashSet<>();
        double minPrice = 0;
        double maxPrice = Integer.MAX_VALUE;
        System.out.println("=====================================================");
        se.getSession().setAttribute("phrases", phrases);
        se.getSession().setAttribute("lower_limit", minPrice);
        se.getSession().setAttribute("upper_limit", maxPrice);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HashSet<String> phrases = (HashSet<String>) se.getSession().getAttribute("phrases");
        phrases.clear();
    }
}

//public class SessionBehaviourTracking {
//    @Bean
//    public HttpSessionListener httpSessionListener(){
//        return  new HttpSessionListener() {
//            @Override
//            public void sessionCreated(HttpSessionEvent se) {
//                HashSet<String> phrases = new HashSet<>();
//                double minPrice = 0;
//                double maxPrice = Integer.MAX_VALUE;
//                System.out.println("=====================================================");
//                se.getSession().setAttribute("phrases", phrases);
//                se.getSession().setAttribute("lower_limit", minPrice);
//                se.getSession().setAttribute("upper_limit", maxPrice);
//            }
//
//            @Override
//            public void sessionDestroyed(HttpSessionEvent se) {
//                HashSet<String> phrases = (HashSet<String>) se.getSession().getAttribute("phrases");
//                phrases.clear();
//            }
//        };
//    }
//}
