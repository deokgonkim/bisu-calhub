package com.idatabank.bisu.calhub.dao;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.springframework.stereotype.Repository;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.component.VEvent;

@Repository
public class ICalDAO {
    public List<VEvent> getFullEventUsingUri(String uri) throws IOException, ParserException {
        List<VEvent> events = null;
        
        DefaultHttpClient httpclient = null;
        HttpHost targetHost = null;
        HttpGet httpGet = null;
        HttpResponse response = null;
        AuthCache authCache = null;
        BasicScheme basicAuth = null; 
        BasicHttpContext localcontext = null;
        
        try {
            URL u = new URL(uri);
            
            httpclient = new DefaultHttpClient();
            httpGet = new HttpGet(u.getFile());
            targetHost = new HttpHost(u.getHost(), u.getPort(), u.getProtocol());
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(targetHost.getHostName(), targetHost.getPort()), 
                    new UsernamePasswordCredentials("calhub", "welcome2calhub!"));
            
            authCache = new BasicAuthCache();
            basicAuth = new BasicScheme();
            authCache.put(targetHost, basicAuth);
            
            localcontext = new BasicHttpContext();
            localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
            
            response = httpclient.execute(targetHost, httpGet, localcontext);
            
            CalendarBuilder builder = new CalendarBuilder();
            
            Calendar calendar = builder.build(response.getEntity().getContent());
            
            ComponentList cl = calendar.getComponents();
            
            Iterator<?> it = null;
            
            it = cl.iterator();
            events = new LinkedList<VEvent>();
            while ( it.hasNext() ) {
                Object object = null;
                VEvent event = null;
                object = it.next();
                if ( object instanceof VEvent ) {
                    event = (VEvent)object;
                    events.add(event);
                }
            }
        } finally {
            
        }
        
        return events;
    }
    
    public List<VEvent> getRangedEventUsingUri(String uri, long range) throws IOException, ParserException {
        List<VEvent> events = null;
        Date from = null;
        Date to = null;
        
        DefaultHttpClient httpclient = null;
        HttpHost targetHost = null;
        HttpGet httpGet = null;
        HttpResponse response = null;
        AuthCache authCache = null;
        BasicScheme basicAuth = null; 
        BasicHttpContext localcontext = null;
        
        try {
            URL u = new URL(uri);
            
            long currentTimeMillis = System.currentTimeMillis();
            long timeStartMillis = currentTimeMillis - (range * 24L * 60L * 60L * 1000L);
            long timeEndMillis = currentTimeMillis + (range * 24L * 60L * 60L * 1000L);
            
            from = new Date(timeStartMillis);
            to = new Date(timeEndMillis);
            
            httpclient = new DefaultHttpClient();
            httpGet = new HttpGet(u.getFile());
            targetHost = new HttpHost(u.getHost(), u.getPort(), u.getProtocol());
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(targetHost.getHostName(), targetHost.getPort()), 
                    new UsernamePasswordCredentials("calhub", "welcome2calhub!"));
            
            authCache = new BasicAuthCache();
            basicAuth = new BasicScheme();
            authCache.put(targetHost, basicAuth);
            
            localcontext = new BasicHttpContext();
            localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
            
            response = httpclient.execute(targetHost, httpGet, localcontext);
            
            CalendarBuilder builder = new CalendarBuilder();
            
            Calendar calendar = builder.build(response.getEntity().getContent());
            
            ComponentList cl = calendar.getComponents();
            
            Iterator<?> it = null;
            
            it = cl.iterator();
            events = new LinkedList<VEvent>();
            while ( it.hasNext() ) {
                Object object = null;
                VEvent event = null;
                object = it.next();
                if ( object instanceof VEvent ) {
                    event = (VEvent)object;
//                    System.out.println(String.format("%s : %s - %s \t is in range ? %s", event.getSummary(), event.getStartDate().getDate(), event.getEndDate().getDate(), String.valueOf(event.getStartDate().getDate().after(from) && event.getEndDate().getDate().before(to))));
//                    System.out.println(String.format("%s : %s - isAfter %s", event.getStartDate().getDate(), from.toString(), String.valueOf(event.getStartDate().getDate().after(from))));
//                    System.out.println(String.format("%s : %s - isBefore %s", event.getEndDate().getDate(), to.toString(), String.valueOf(event.getEndDate().getDate().before(to))));
                    if ( event.getStartDate().getDate().after(from) && event.getEndDate().getDate().before(to) ) {
                        events.add(event);
                    }
                }
            }
        } finally {
            
        }
        
        return events;
    }
}
