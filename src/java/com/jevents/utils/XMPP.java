/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jevents.utils;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.sasl.javax.SASLDigestMD5Mechanism;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;

import java.util.HashMap;

/**
 *
 * @author str2n
 */
public class XMPP {
    public static String Regist(String username, String password, String email) throws Exception {
        XMPPTCPConnection connection;
        XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
        configBuilder.setUsernameAndPassword("admin", "admin");
//		System.out.println(Config.ConfigServerIp);
//		configBuilder.setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.required );
        SASLMechanism mechanism = new SASLDigestMD5Mechanism();
        SASLAuthentication.registerSASLMechanism(mechanism);
        SASLAuthentication.blacklistSASLMechanism("SCRAM-SHA-1");
        SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");
        SASLAuthentication.blacklistSASLMechanism("CRAM-MD5");
        SASLAuthentication.unBlacklistSASLMechanism("PLAIN");
        SASLAuthentication.blacklistSASLMechanism("EXTERNAL");
        configBuilder.setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.disabled);
        configBuilder.setServiceName(Configs.ConfigServerName);
        configBuilder.setHost(Configs.ConfigServerIp);
        configBuilder.setPort(Configs.ConfigServerPort);
        connection = new XMPPTCPConnection(configBuilder.build());

        try {
            connection.connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "ERROR";
        }
        try {
            connection.login();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "ERROR";
        }
        if (username == null || password == null) {
            System.out.println("Null password and user name");
            return "ERROR";
        }

        AccountManager acctmanager = AccountManager.getInstance(connection);
        try {
            HashMap<String, String> attributes = new HashMap<String, String>();
            attributes.put("email", email);
            acctmanager.createAccount(username, password, attributes);
        } catch (XMPPException e) {
            System.out.println(e.getMessage());
            return "ERROR";
        }

        connection.disconnect();
        return "Create Success";
    }

    public static String Login(String username, String password) {
        XMPPTCPConnection connection=null;
        here:if (connection != null && connection.isAuthenticated()) {
            return "Sucess Login";
        } else {
            if (connection == null || !connection.isConnected()) {
                XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
                configBuilder.setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.ifpossible);
                configBuilder.setServiceName(Configs.ConfigServiceName);
                configBuilder.setHost(Configs.ConfigServerIp);
                configBuilder.setPort(Configs.ConfigServerPort);

                connection = new XMPPTCPConnection(configBuilder.build());

                try {
                    connection.connect();
                } catch (Exception e) {
                    System.out.println("failed to connect to server");
                    break here;
                }
            }
            if (username == null || password == null) {
                System.out.println("username or password is empty");
                break here;
            }
            try {
                connection.login(username, password);
            } catch (Exception e) {
                System.out.println("failed to login");
                break here;
            }
            if (!connection.isAuthenticated())
                break here;
            connection.disconnect();
            return "Sucess Login";
        }
        connection.disconnect();
        return "ERROR";
    }
    
}
