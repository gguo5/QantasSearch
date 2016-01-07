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
import java.util.Random;
import org.apache.log4j.Logger;


/**
 *
 * @author gguo
 */
public class rootFn {
    /**
     * @param args the command line arguments
     */
    
    final static Logger logger = Logger.getLogger(rootFn.class.getName());
    
    public static void main(String[] args) throws IOException, URISyntaxException {
        // TODO code application logic here
        int maxRun = 200;
        if (Desktop.isDesktopSupported()) {
            int start = 820;//820
            for (int i = 0; i <= maxRun; i++) {
                
                String url = getNewURLSeq(start++);
                Desktop.getDesktop().browse(new URI(url));
                logger.info(url);
                try {
                    Thread.sleep(9000);
                } catch (InterruptedException ex) {
                    
                }

            }
            // Desktop.getDesktop().browse(new URI("http://www.bing.com/search?mkt=en-AU&ourmark=1&FORM=RKTNTB&PC=RK01&q=candy%20crush%20237"));

        }
    }

    private static String getNewURL() {
        String baseURL = "http://www.bing.com/search?mkt=en-AU&ourmark=1&FORM=RKTNTB&PC=RK01&q=candy%20crush%20saga%20level%20";
        String newURL = baseURL + getRandomNum();
        return newURL;
        
    }
    
    
    private static String getNewURLSeq(int seq) {
        String baseURL = "http://www.bing.com/search?mkt=en-AU&ourmark=1&FORM=RKTNTB&PC=RK01&q=candy%20crush%20saga%20level%20";
        String newURL = baseURL + seq;
        return newURL;

    }

    private static String getRandomNum() {
        int min = 811, max = 1024;
        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return String.valueOf(randomNum);
    }

}
