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
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author gguo
 */
public class Search {
    final static Logger logger = Logger.getLogger(Search.class.getName());
    final static String BASEURL = "http://www.bing.com/search?mkt=en-AU&ourmark=1&FORM=RKTNTB&PC=RK01&q=";
    final static String AMPERSAND = "%20";
    public static void OpenRandomURL(String[] keywords) {
        if (Desktop.isDesktopSupported()) {         
                try {         
                    String url = getNewURL(keywords);
                    Desktop.getDesktop().browse(new URI(url));
                    logger.info(url);
                    
                } catch (IOException ex) {
                 logger.error("IOException... " , ex);
                    
                } catch (URISyntaxException ex) {
                 logger.error("URISyntaxException... " , ex);
            }
        }
    }
    
    

    private static String getNewURL(String[] keywords) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASEURL);
        for (int i = 0; i< keywords.length; i++){
        sb.append(keywords[i]);
        if(i!=keywords.length-1){
        sb.append(AMPERSAND);
        }
        }
        return sb.toString();
    }
    
}
