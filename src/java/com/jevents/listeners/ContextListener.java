/*
 * Copyright (c) 2006 Sun Microsystems, Inc.  All rights reserved.  U.S.
 * Government Rights - Commercial software.  Government users are subject
 * to the Sun Microsystems, Inc. standard license agreement and
 * applicable provisions of the FAR and its supplements.  Use is subject
 * to license terms.
 *
 * This distribution may include materials developed by third parties.
 * Sun, Sun Microsystems, the Sun logo, Java and J2EE are trademarks
 * or registered trademarks of Sun Microsystems, Inc. in the U.S. and
 * other countries.
 *
 * Copyright (c) 2006 Sun Microsystems, Inc. Tous droits reserves.
 *
 * Droits du gouvernement americain, utilisateurs gouvernementaux - logiciel
 * commercial. Les utilisateurs gouvernementaux sont soumis au contrat de
 * licence standard de Sun Microsystems, Inc., ainsi qu'aux dispositions
 * en vigueur de la FAR (Federal Acquisition Regulations) et des
 * supplements a celles-ci.  Distribue par des licences qui en
 * restreignent l'utilisation.
 *
 * Cette distribution peut comprendre des composants developpes par des
 * tierces parties. Sun, Sun Microsystems, le logo Sun, Java et J2EE
 * sont des marques de fabrique ou des marques deposees de Sun
 * Microsystems, Inc. aux Etats-Unis et dans d'autres pays.
 */


package com.jevents.listeners;

import com.jevents.ActivityDBAO;
import javax.servlet.*;

// Event handler class for handling application scope events
public final class ContextListener implements ServletContextListener {
    private ServletContext context = null;

    // This method gets called when the application is deployed
    public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();

        // Create BookDBAO object and save it as an attribute to
        // ServletContext scope object.
        try {
        	ActivityDBAO activityDB = new ActivityDBAO();
            context.setAttribute("activityDB", activityDB);
        } catch (Exception ex) {
            System.out.println("Couldn't create activity database bean: " +
                ex.getMessage());
        }

        // Save hitCounter and orderCounter attributes in the
        // ServletContext scope object
       
    }

    // This method gets called when the application is undeployed
    public void contextDestroyed(ServletContextEvent event) {
        context = event.getServletContext();

        ActivityDBAO bookDB = (ActivityDBAO) context.getAttribute("activityDB");

        if (bookDB != null) {
            bookDB.remove();
        }

        context.removeAttribute("activityDB");
        
    }
}
