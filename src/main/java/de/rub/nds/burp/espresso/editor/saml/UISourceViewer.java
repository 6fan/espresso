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
package de.rub.nds.burp.espresso.editor.saml;

import burp.IBurpExtenderCallbacks;
import de.rub.nds.burp.utilities.Logging;
import de.rub.nds.burp.utilities.XMLHelper;
import de.rub.nds.burp.utilities.listeners.AbstractCodeEvent;
import de.rub.nds.burp.utilities.listeners.ICodeListener;
import de.rub.nds.burp.utilities.listeners.CodeListenerController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 * Source Code Viewer.
 * @author Tim Guenther
 * @version 1.0
 */
public class UISourceViewer extends JPanel implements ICodeListener{
    private String sourceCode = "<error>Something went wrong during init.!</error>";
    private String codeStyle = SyntaxConstants.SYNTAX_STYLE_XML;
    private RSyntaxTextArea textArea;
    private CodeListenerController listeners = null;
    private JCheckBox checkBox;
    private RTextScrollPane sp;

    private IBurpExtenderCallbacks callbacks;
    private boolean wrapLines;
    /**
     * Create a new Source Code Viewer.
     * @param sourceCode The Code that should be highlighted.
     * @param codeStyle The kind of highlighting.
     */
    public UISourceViewer(IBurpExtenderCallbacks callbacks, String sourceCode, String codeStyle) {
        super(new BorderLayout());
        this.callbacks = callbacks;
        this.sourceCode = sourceCode;
        this.codeStyle = codeStyle;
        initComponent();
    }
    
    /**
     * Create a new Source Code Viewer.
     */
    public UISourceViewer(IBurpExtenderCallbacks callbacks){
        this.callbacks = callbacks;
        initComponent();
    }
    
    private void initComponent(){
        textArea = new RSyntaxTextArea(20, 60);
        textArea.setSyntaxEditingStyle(codeStyle);
        textArea.setText(sourceCode);
        textArea.setCodeFoldingEnabled(true);
        wrapLines = Boolean.valueOf(callbacks.loadExtensionSetting("wrapLinesInSourceViewer"));
        if (wrapLines) {
            textArea.setLineWrap(true);
        } else {
            textArea.setLineWrap(false);
        }
        sp = new RTextScrollPane(textArea);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);        
        checkBox = new JCheckBox("Softwraps for long lines");
        checkBox.setSelected(false);
        checkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                checkBoxActionPerformed(ae);
            }
        });
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)        
            .addGroup(layout.createSequentialGroup()
                .addComponent(checkBox))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(sp)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(checkBox))
        );
    }
    
    private void checkBoxActionPerformed(ActionEvent evt) {
        if(checkBox.isSelected()) {
            textArea.setLineWrap(true);
            sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            callbacks.saveExtensionSetting("wrapLinesInSourceViewer", "true");
        } else {
            textArea.setLineWrap(false);
            sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
            callbacks.saveExtensionSetting("wrapLinesInSourceViewer", "false");
        }
    }
    
    /**
     * Set the source code and highlighting.
     * @param sourceCode The Code that should be highlighted.
     * @param codeStyle The kind of highlighting.
     */
    public void setText(String sourceCode, String codeStyle){
        this.sourceCode = sourceCode;
        this.codeStyle = codeStyle;
        textArea.setSyntaxEditingStyle(codeStyle);
        textArea.setEditable(false);
        textArea.setText(sourceCode);
        this.updateUI();
    }
    
    /**
     * Set the source code and highlighting.
     * @param sourceCode The Code that should be highlighted.
     * @param indent The indent level. (Default = 2).
     */
    public void setPrettyXML(String sourceCode, int indent){
        sourceCode = XMLHelper.format(sourceCode, indent);
        setText(sourceCode, SyntaxConstants.SYNTAX_STYLE_XML);
    }

    /**
     * Set enable or disable this component.
     * @param enabled True to enable, false to disable the component.
     */
    @Override
    public void setEnabled(boolean enabled){
        textArea.setEnabled(enabled);
        if(enabled){
           textArea.setText(sourceCode);
        } else {
           textArea.setText("");
        }
    }
    
    /**
     * Is called every time new Code is available.
     * @param evt {@link de.rub.nds.burp.utilities.listeners.AbstractCodeEvent} The new source code.
     */
    @Override
    public void setCode(AbstractCodeEvent evt) {
        setPrettyXML(evt.getCode(), 2);
    }

    /**
     * Set the listener for the editor.
     * @param listeners {@link de.rub.nds.burp.utilities.listeners.CodeListenerController}
     */
    @Override
    public void setListener(CodeListenerController listeners) {
        this.listeners = listeners;
        this.listeners.addCodeListener(this);
    }
}
