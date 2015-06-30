/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.qantassearch.util;

import java.util.Random;
import org.apache.log4j.Logger;

/**
 *
 * @author gguo
 */
public class Helper {
    final static Logger logger = Logger.getLogger(Helper.class.getName());
     public static String getRandomNum() {
        int min = 1, max = 1024;
        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return String.valueOf(randomNum);
    }
     
      public static String getRandomNumStr(int min, int max) {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return String.valueOf(randomNum);
    }
      
       public static int getRandomNumInt(int min, int max) {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
}
