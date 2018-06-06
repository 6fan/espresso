/**
 * EsPReSSO - Extension for Processing and Recognition of Single Sign-On Protocols.
 * Copyright (C) 2015 Tim Guenther and Christian Mainka
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package de.rub.nds.burp.espresso.gui;

import burp.IBurpExtenderCallbacks;
import javax.swing.JTabbedPane;

/**
 * The main window, the parent window for all tabs.
 * @author Tim Guenther
 * @version 1.0
 */
public class UIMain extends JTabbedPane{
    private IBurpExtenderCallbacks callbacks;
    
    //GUI
    private UIHistory history;
    private UIOptions options;
    private UIHelp help;

    /**
     * Construct the main UI.
     * @param callbacks {@link burp.IBurpExtenderCallbacks}.
     */
    public UIMain(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        initComponents();
        
    }
    /**
     * 
     * @return Get the history tab.
     */
    public UIHistory getHistory(){
        return history;
    }

    /**
     * 
     * @return Get the help tab.
     */
    public UIHelp getHelp() {
        return help;
    }

    /**
     * 
     * @return Get the options tab. 
     */
    public UIOptions getOptions() {
        return options;
    }
    
    private void initComponents(){
        //register all components on the extension tab
        //sso history
        history = new UIHistory(callbacks);
        //options
        options = new UIOptions(callbacks);
        //help
        help = new UIHelp();

        this.addTab("SSO History", history);
        this.addTab("Options", options);
        this.addTab("Help", help);

        // customize ui components
        callbacks.customizeUiComponent(this);
    }   
}
