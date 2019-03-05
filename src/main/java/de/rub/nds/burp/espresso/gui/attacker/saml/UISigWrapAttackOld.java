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
package de.rub.nds.burp.espresso.gui.attacker.saml;

import de.rub.nds.burp.espresso.gui.attacker.IAttack;
import de.rub.nds.burp.utilities.Logging;
import de.rub.nds.burp.utilities.listeners.AbstractCodeEvent;
import de.rub.nds.burp.utilities.listeners.CodeListenerController;
import de.rub.nds.burp.utilities.listeners.events.SamlCodeEvent;
import de.rub.nds.burp.utilities.XMLHelper;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import wsattacker.library.schemaanalyzer.SchemaAnalyzer;
import wsattacker.library.schemaanalyzer.SchemaAnalyzerFactory;
import wsattacker.library.signatureWrapping.option.Payload;
import wsattacker.library.signatureWrapping.util.exception.InvalidWeaknessException;
import wsattacker.library.signatureWrapping.util.signature.SignatureManager;
import wsattacker.library.signatureWrapping.xpath.weakness.util.WeaknessLog;
import wsattacker.library.signatureWrapping.xpath.wrapping.WrappingOracle;
import wsattacker.library.xmlutilities.dom.DomUtilities;


/**
 * The Signature Wrapping Attack
 * @author Tim Guenther, Christian Mainka
 * @version 1.0
 */
public class UISigWrapAttackOld extends javax.swing.JPanel implements IAttack {

    private String code = null;
    private CodeListenerController listeners = null;
    private WrappingOracle wrappingOracle;
    private static final SchemaAnalyzer samlSchemaAnalyser = SchemaAnalyzerFactory.getInstance(SchemaAnalyzerFactory.SAML);

    /**
     * Creates new form UISigWrapAttack
     */
    public UISigWrapAttackOld() {
            initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        signatureManager = signatureManager;
        payloadBean = new de.rub.nds.burp.espresso.gui.attacker.util.PayloadBean();
        attackSlider = new javax.swing.JSlider();
        attackNumber = new javax.swing.JTextField();
        attackSliderLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        modifyButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        updateOracle = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        finalPayloadScrollPane = new org.fife.ui.rtextarea.RTextScrollPane();
        finalPayload = new org.fife.ui.rsyntaxtextarea.RSyntaxTextArea();
        payloadComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        attackDescriptionTextArea = new org.fife.ui.rsyntaxtextarea.RSyntaxTextArea();
        rTextScrollPane1 = new org.fife.ui.rtextarea.RTextScrollPane();
        payloadValue = new org.fife.ui.rsyntaxtextarea.RSyntaxTextArea();

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, payloadComboBox, org.jdesktop.beansbinding.ELProperty.create("${selectedItem}"), payloadBean, org.jdesktop.beansbinding.BeanProperty.create("payload"));
        bindingGroup.addBinding(binding);

        attackSlider.setMaximum(0);
        attackSlider.setToolTipText("Choose an attack.");
        attackSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                attackSliderStateChanged(evt);
            }
        });

        attackNumber.setToolTipText("Set the attack manually");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, attackSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), attackNumber, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        attackNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attackNumberActionPerformed(evt);
            }
        });

        attackSliderLabel.setText("(3.) Choose Attack Vector");

        jLabel2.setText("(1.) Configure Payload:");

        modifyButton.setText("Modify");
        modifyButton.setToolTipText("Modify the current Message.");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("(2.) Generate Wrapping Oracle:");

        updateOracle.setText("Update Oracle");
        updateOracle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateOracleActionPerformed(evt);
            }
        });

        jLabel4.setText("(4.) Fine Tune XSW Vector:");

        finalPayloadScrollPane.setLineNumbersEnabled(true);

        finalPayload.setColumns(20);
        finalPayload.setLineWrap(true);
        finalPayload.setRows(5);
        finalPayload.setSyntaxEditingStyle("text/xml");
        finalPayloadScrollPane.setViewportView(finalPayload);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${payloads}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_ONCE, signatureManager, eLProperty, payloadComboBox);
        bindingGroup.addBinding(jComboBoxBinding);

        payloadComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                payloadComboBoxItemStateChanged(evt);
            }
        });

        attackDescriptionTextArea.setEditable(false);
        attackDescriptionTextArea.setBackground(getBackground());
        attackDescriptionTextArea.setColumns(20);
        attackDescriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(attackDescriptionTextArea);

        payloadValue.setColumns(20);
        payloadValue.setLineWrap(true);
        payloadValue.setRows(5);
        payloadValue.setSyntaxEditingStyle("text/xml");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, payloadBean, org.jdesktop.beansbinding.ELProperty.create("${payload.value}"), payloadValue, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        payloadValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                payloadValueKeyReleased(evt);
            }
        });
        rTextScrollPane1.setViewportView(payloadValue);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(attackSliderLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(attackSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(attackNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(modifyButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateOracle))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(finalPayloadScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(payloadComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rTextScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(payloadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rTextScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(updateOracle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(attackNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attackSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attackSliderLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(16, 16, 16)
                .addComponent(finalPayloadScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyButton))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void attackSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_attackSliderStateChanged
        if(attackSlider.getMaximum() > 0){
            int attack = attackSlider.getValue();
            try {
                    Document attackDoc = wrappingOracle.getPossibility(attack);
                    String attackString = DomUtilities.domToString(attackDoc);
                    attackDescriptionTextArea.setText(WeaknessLog.representation());
                    finalPayload.setText(attackString);
            } catch (Exception ex) {
                    Logging.getInstance().log(getClass(), ex);
            }
            attackNumber.setText((new Integer(attack)).toString());
        }
    }//GEN-LAST:event_attackSliderStateChanged

        private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
            Logging.getInstance().log(getClass(), "Wrapping Signature...", Logging.INFO);
            notifyAllTabs(new SamlCodeEvent(this, finalPayload.getText()));
        }//GEN-LAST:event_modifyButtonActionPerformed

        private void payloadComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_payloadComboBoxItemStateChanged
            final int selected = payloadComboBox.getSelectedIndex();
            final Payload p = signatureManager.getPayloads().get(selected);
            payloadBean.setPayload(p);
        }//GEN-LAST:event_payloadComboBoxItemStateChanged

        private void updateOracleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateOracleActionPerformed
            //A workaround for issue #19
            payloadValueKeyReleased(null);
            
            Document samlDoc = signatureManager.getDocument();
            List<Payload> payloadList = signatureManager.getPayloads();
            wrappingOracle = new WrappingOracle(samlDoc, payloadList, samlSchemaAnalyser);
            final int max = wrappingOracle.maxPossibilities();
            attackSlider.setMaximum(max);
            if (max > 0) {
                    attackSlider.setValue(1);
            }
            attackSliderLabel.setText("(3.) Choose Attack Vector (Max: " + max + ")");
            Logging.getInstance().log(getClass(), "Updating the Wrapping Oracle.", Logging.DEBUG);

        }//GEN-LAST:event_updateOracleActionPerformed

        private void payloadValueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_payloadValueKeyReleased
            try {
                if(!payloadValue.getText().isEmpty()){
                    payloadBean.getPayload().setValue(payloadValue.getText());
                }
            } catch(NullPointerException e){
                 Logging.getInstance().log(getClass(), e);
            }
        }//GEN-LAST:event_payloadValueKeyReleased

    private void attackNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attackNumberActionPerformed
        if(attackSlider.getMaximum() > 0){
            int attack = new Integer(attackNumber.getText());
            if(attack >= 0 && attack <= attackSlider.getMaximum()){
                attackSlider.setValue(attack);
            }
        }
    }//GEN-LAST:event_attackNumberActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.fife.ui.rsyntaxtextarea.RSyntaxTextArea attackDescriptionTextArea;
    private javax.swing.JTextField attackNumber;
    private javax.swing.JSlider attackSlider;
    private javax.swing.JLabel attackSliderLabel;
    private org.fife.ui.rsyntaxtextarea.RSyntaxTextArea finalPayload;
    private org.fife.ui.rtextarea.RTextScrollPane finalPayloadScrollPane;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifyButton;
    private de.rub.nds.burp.espresso.gui.attacker.util.PayloadBean payloadBean;
    private javax.swing.JComboBox payloadComboBox;
    private org.fife.ui.rsyntaxtextarea.RSyntaxTextArea payloadValue;
    private org.fife.ui.rtextarea.RTextScrollPane rTextScrollPane1;
    private wsattacker.library.signatureWrapping.util.signature.SignatureManager signatureManager;
    private javax.swing.JToggleButton updateOracle;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    /**
     * Initialise the XML Signature Wrapping Attack.
     */
    private void initXsw() {
        payloadValue.setText(code);
        
        Document doc;
        doc = XMLHelper.stringToDom(code);
        signatureManager = new SignatureManager();
        signatureManager.setDocument(doc);
        
        //Initialize the Payload JCombobox
        List<Payload> payloadList = signatureManager.getPayloads();
        if(payloadList.size() > 0){
            payloadComboBox.setModel(new DefaultComboBoxModel(payloadList.toArray()));

            //Initialize the Payload Value
            payloadValue.setText(signatureManager.getPayloads().get(0).getValue());
            
            updateOracle.setEnabled(true);
            modifyButton.setEnabled(true);
            
            // init. first payload
            try{
                payloadBean.setPayload(payloadList.get(0));
            } catch(IndexOutOfBoundsException e){
                Logging.getInstance().log(getClass(), e);
            }
            
            finalPayload.setText("Modify the Payload (1.) and press Update Oracle (2.).");
        } else {
            attackSlider.setMaximum(0);
            attackDescriptionTextArea.setText("No Attack.");
            finalPayload.setText("No Payload.");
            
            String[] payloadError = {"No Payload found!"};
            payloadComboBox.setModel(new DefaultComboBoxModel(payloadError));
            payloadValue.setText(payloadError[0]);
            
            updateOracle.setEnabled(false);
            modifyButton.setEnabled(false);
        }
        
        //Update the Attack Slider
        attackSlider.setValue(0);
        
        //Update the Attack Number
        attackNumber.setText("0");
        
    }
       
    /**
     * Is called every time new Code is available.
     * @param evt {@link de.rub.nds.burp.utilities.listeners.AbstractCodeEvent} The new source code.
     */
    @Override
    public void setCode(AbstractCodeEvent evt) {
        this.code = evt.getCode();
        initXsw();

        // Disable the ui for further editing after modification.
        if(evt.getSource().equals(this)){

            String[] payloadError = {""};
            payloadComboBox.setModel(new DefaultComboBoxModel(payloadError));
            payloadValue.setText("");
            attackSliderLabel.setText("(3.) Choose Attack Vector");
            attackSlider.setMaximum(0);
            attackDescriptionTextArea.setText("");
            finalPayload.setText("Modifications successfully transmitted.\nSee the Source code or SAMLResponse/Request tab.");

            updateOracle.setEnabled(false);
            modifyButton.setEnabled(false);

        }
    }

    /**
     * Notify all registered listeners with the new code.
     * @param evt The new source code.
     */
    @Override
    public void notifyAllTabs(AbstractCodeEvent evt) {
        if(listeners != null){
            listeners.notifyAll(evt);
        }
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
