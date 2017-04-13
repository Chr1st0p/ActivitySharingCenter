/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jevents.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 *
 * @author str2n
 */
public class GeoCoding {
    
    private static final String APIURL = "https://maps.googleapis.com/maps/api/geocode/xml?";
    private static final String APIKEY = "AIzaSyCQRdBdpYgywbjfZQnxK3FomK85OPxUQTI";
    
    public static Location GetLatLng(String address){
        
        Location loc=null;
        String encodedURL = (APIURL+"address="+address+"&key="+APIKEY).replace(" ", "+");
        try {
            URL url = new URL(encodedURL);
            HttpsURLConnection con =(HttpsURLConnection) url.openConnection();
            InputStream ins = con.getInputStream();
            InputStreamReader insr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(insr);
            
            String line;
            final StringBuilder sb = new StringBuilder();
            while ((line = in.readLine())!=null) {
                sb.append(line).append("\n");
            }
            String response = sb.toString();
            if (response!=null) {
                Document doc = DocumentHelper.parseText(response);
                Element root = doc.getRootElement();
                Element status = root.element("status");
                if ("OK".equals(status.getText())) {
                    Element location = root.element("result").element("geometry").element("location");
                    Element lat = location.element("lat");
                    Element lng = location.element("lng");
                    loc=new Location(Double.valueOf(lat.getText()), Double.valueOf(lng.getText()));
                }else{
                    System.out.println("Something wrong with API key or URL, Status: "+status.getText());
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(GeoCoding.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GeoCoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return loc;
    }
    
    public static String GetAddress(Location loc){
        
        String address = null;
        String encodedURL= (APIURL+"latlng="+loc.getLat()+","+loc.getLng()+"&language=en&result_type=street_address&key="+APIKEY).replaceAll(" ", "+");
        try {
            URL url = new URL(encodedURL);
            HttpsURLConnection con =(HttpsURLConnection) url.openConnection();
            InputStream ins = con.getInputStream();
            InputStreamReader insr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(insr);
            
            String line;
            final StringBuilder sb = new StringBuilder();
            while ((line = in.readLine())!=null) {
                sb.append(line).append("\n");
            }
            String response = sb.toString();
            if (response!=null) {
                Document doc = DocumentHelper.parseText(response);
                Element root = doc.getRootElement();
                Element status = root.element("status");
                if ("OK".equals(status.getText())) {
                    Element addressElement = root.element("result").element("formatted_address");
                    address=addressElement.getText();
                    System.out.println(address);
                }else{
                    System.out.println("Something wrong with API Key or URL, Status: "+status.getText());
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(GeoCoding.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GeoCoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return address;
    }
    
}
