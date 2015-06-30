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
import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author gguo
 */
public class Search extends Thread {

    final static Logger logger = Logger.getLogger(Search.class.getName());
    final static String BASEURL = "http://www.bing.com/search?mkt=en-AU&ourmark=1&FORM=RKTNTB&PC=RK01&q=";
    final static String AMPERSAND = "%20";
    final static int keywordMinLength = 1;
    final static int keywordMaxLength = 4;

    public Search(int iteration, ArrayList<String> words, String threadName) {
        super(threadName);
        this.threadName = threadName;
        this.iteration = iteration;
        this.wordList = words;
    }

    public void run() {
        logger.info("Thread " + this.getName());
        for (int i = 0; i < iteration; i++) {
            synchronized (this) {
                if (paused) {
                    try {
                        //LOCK.wait();
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    logger.info("Running " + (i + 1) + " out of " + iteration + " iteration");
                    int keywordLen = Helper.getRandomNumInt(keywordMinLength, keywordMaxLength); //1-4
                    String[] keywords = getDictionaryWords(keywordLen);
                    OpenRandomURL(keywords);
                    try {
                        Thread.sleep(4000);
                        logger.info("Thread " + threadName + " in sleep...4000ms");
                    } catch (InterruptedException ex) {
                        logger.error("Thread " + threadName + " Can't sleep... ", ex);
                    }

                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
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

    public static void OpenRandomURL(String[] keywords) {
        if (Desktop.isDesktopSupported()) {
            try {
                String url = getNewURL(keywords);
                Desktop.getDesktop().browse(new URI(url));
                logger.info(url);

            } catch (IOException ex) {
                logger.error("IOException... ", ex);

            } catch (URISyntaxException ex) {
                logger.error("URISyntaxException... ", ex);
            }
        }
    }

    private static String getNewURL(String[] keywords) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASEURL);
        for (int i = 0; i < keywords.length; i++) {
            sb.append(keywords[i]);
            if (i != keywords.length - 1) {
                sb.append(AMPERSAND);
            }
        }
        return sb.toString();
    }

    private String[] getDictionaryWords(int keywordLen) {
        String[] kws = new String[keywordLen];
        for (int j = 0; j < keywordLen; j++) {
            int index = Helper.getRandomNumInt(0, wordList.size());
            kws[j] = wordList.get(index);
        }
        return kws;
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

    private ArrayList<String> wordList;
    private int iteration;
    private Thread t;
    private String threadName;
    private boolean paused = false;

    private synchronized void openNewWindow() {

        for (int i = 0; i < iteration; i++) {
            while (paused) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    logger.error("Thread " + this.getName() + " InterruptedException ", ex);
                }
            }
            logger.info("Running " + (i + 1) + " out of " + iteration + " iteration");
            int keywordLen = Helper.getRandomNumInt(keywordMinLength, keywordMaxLength); //1-4
            String[] keywords = getDictionaryWords(keywordLen);
            OpenRandomURL(keywords);
            try {
                Thread.sleep(4000);
                logger.info("Thread " + threadName + " in sleep...4000ms");
            } catch (InterruptedException ex) {
                logger.error("Thread " + threadName + " Can't sleep... ", ex);
            }

            notify();
        }
    }
}
