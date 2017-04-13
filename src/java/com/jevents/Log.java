/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jevents;

/**
 *
 * @author FENG0
 */
public class Log {
    
    static boolean debug = true;
    
    public static void out(String tag, Object x) {
        if (debug)
            System.out.println(tag + "    " + x.toString());
    }
}
