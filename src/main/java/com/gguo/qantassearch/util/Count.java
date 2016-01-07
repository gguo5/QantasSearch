/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.qantassearch.util;

import com.gguo.qantassearch.gui.AppTest;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;


/**
 *
 * @author gguo
 */
public class Count extends Thread {

    final static Logger logger = Logger.getLogger(SearchCandy.class.getName());
    final static String BASEURL = "http://www.bing.com/search?mkt=en-AU&ourmark=1&FORM=RKTNTB&PC=RK01&q=";
    final static String AMPERSAND = "%20";
    
    final static String CANDY_CRUSH = "candy%20crush%20saga%20level%20";
    
    private boolean running =true;
    private final AppTest app;

    public Count(int startIndex, int repeat, String threadName, AppTest app) {
        super(threadName);
        this.threadName = threadName;
        this.startIndex = startIndex;
        this.repeat = repeat;
        this.app = app;
    }

    public void run() {
        logger.info("Running thread " + this.getName());
        for (int i = 0; i < repeat; i++) {         
            synchronized (this) {   
                if(running){
                while (paused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } //else {
                    logger.info("Running " + (i + 1) + " out of " + repeat + " iteration");
                    //OpenRandomURL(startIndex++);
                    app.setTextFieldText(startIndex++);
                    
                    try {
                        logger.info("Thread " + threadName + " in sleep...1000ms");
                        Thread.sleep(9000);
                        //TimeUnit.SECONDS.sleep(1);
                       } catch (InterruptedException ex) {
                        logger.error("Thread " + threadName + " Can't sleep... ", ex);
                        running = false;
                    }
           
                } else {
                    logger.debug("break hit");
                    break;}
           
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            }
            
        }
        
          logger.info("Thread " + threadName + " exiting.");
    }

   

    public void start() {
        logger.info("starting thread: " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    
    public void stopExecuting() {
        this.running = false;
         if (paused)
            interrupt();
        try {
            join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
 

  

//    public synchronized void setPaused(boolean paused) {
//        this.paused = paused;
//        notify();
//    }

     public void setPaused() {
        synchronized(this) {
            paused = !paused;
            notifyAll();
        }
    }

    private int repeat;
    private int startIndex;
    private Thread t;
    private String threadName;
    private boolean paused = false;

//    private synchronized void openNewWindow() {
//
//        for (int i = 0; i < startIndex; i++) {
//            while (paused) {
//                try {
//                    wait();
//                } catch (InterruptedException ex) {
//                    logger.error("Thread " + this.getName() + " InterruptedException ", ex);
//                }
//            }
//            logger.info("Running " + (i + 1) + " out of " + startIndex + " iteration");
//            int keywordLen = Helper.getRandomNumInt(keywordMinLength, keywordMaxLength); //1-4
//            String[] keywords = getDictionaryWords(keywordLen);
//            OpenRandomURL(keywords);
//            try {
//                Thread.sleep(4000);
//                logger.info("Thread " + threadName + " in sleep...4000ms");
//            } catch (InterruptedException ex) {
//                logger.error("Thread " + threadName + " Can't sleep... ", ex);
//            }
//
//            notify();
//        }
//    }
}
