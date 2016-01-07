/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.qantassearch.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.log4j.Logger;

/**
 *
 * @author gguo
 */
public class SearchCandy extends Thread {

    final static Logger logger = Logger.getLogger(SearchCandy.class.getName());
    final static String BASEURL = "http://www.bing.com/search?mkt=en-AU&ourmark=1&FORM=RKTNTB&PC=RK01&q=";
    final static String AMPERSAND = "%20";
    
    final static String CANDY_CRUSH = "candy%20crush%20saga%20level%20";
    
    private boolean running =true;

    public SearchCandy(int startIndex, int repeat, String threadName) {
        super(threadName);
        this.threadName = threadName;
        this.startIndex = startIndex;
        this.repeat = repeat;
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
                    OpenRandomURL(startIndex++);
                    try {
                        Thread.sleep(7000);
                        logger.info("Thread " + threadName + " in sleep...7000ms");
                    } catch (InterruptedException ex) {
                        logger.error("Thread " + threadName + " Can't sleep... ", ex);
                        running = false;
                    }
                      } else {
                    logger.debug("break hit");
                    break;}
                //}
           
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
    }
    
    public static void OpenRandomURL(int gameNo) {
        if (Desktop.isDesktopSupported()) {
            try {
                String url = getNewURL(gameNo);
                Desktop.getDesktop().browse(new URI(url));
                logger.info(url);

            } catch (IOException ex) {
                logger.error("IOException... ", ex);

            } catch (URISyntaxException ex) {
                logger.error("URISyntaxException... ", ex);
            }
        }
    }

    private static String getNewURL(int gameNo) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASEURL).append(CANDY_CRUSH).append(gameNo);
        return sb.toString();
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
