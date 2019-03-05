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
package de.rub.nds.burp.espresso.intruder.xsw;

import de.rub.nds.burp.utilities.Logging;
import de.rub.nds.burp.utilities.XMLHelper;
import de.rub.nds.burp.utilities.table.xsw.TableEntry;
import de.rub.nds.burp.utilities.table.xsw.TableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import wsattacker.library.xmlutilities.dom.DomUtilities;

/**
 * @author Nurullah Erinola
 */
public class XSWInputJDialog extends javax.swing.JDialog {

    private String message;
    private Document doc;
    private HashMap<String, String> valuePairs;
    private TableModel tableModel;
    private JTable table;
    private int counter = 0;
    
    /**
     * Creates new form XSWInputJDialog
     * @param message Message to be show
     */
    public XSWInputJDialog(String message) {
        super(new JFrame(), true);
        initComponents();
        // Init variables
        this.message = message;
        doc = XMLHelper.stringToDom(message);
        valuePairs = new HashMap<>();
        // Init table and editor
        initTable();
        initEditor();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonOk = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCurrentValue = new javax.swing.JTextField();
        jTextFieldNewValue = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonAdd = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPaneTable = new javax.swing.JScrollPane();
        jCheckBoxWrapLines = new javax.swing.JCheckBox();
        rTextScrollPane = new org.fife.ui.rtextarea.RTextScrollPane();
        rSyntaxTextArea = new org.fife.ui.rsyntaxtextarea.RSyntaxTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButtonOk.setText("Ok");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jLabel1.setText("Message:");

        jLabel2.setText("Current value:");

        jLabel3.setText("New value:");

        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jLabel4.setText("Values to be replaced:");

        jCheckBoxWrapLines.setSelected(true);
        jCheckBoxWrapLines.setText("Softwraps for long lines");
        jCheckBoxWrapLines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxWrapLinesActionPerformed(evt);
            }
        });

        rTextScrollPane.setLineNumbersEnabled(true);

        rSyntaxTextArea.setEditable(false);
        rSyntaxTextArea.setLineWrap(true);
        rSyntaxTextArea.setCodeFoldingEnabled(true);
        rSyntaxTextArea.setSyntaxEditingStyle("text/xml");
        rTextScrollPane.setViewportView(rSyntaxTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rTextScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                    .addComponent(jButtonOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldCurrentValue)
                    .addComponent(jScrollPaneTable)
                    .addComponent(jTextFieldNewValue)
                    .addComponent(jSeparator1)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jCheckBoxWrapLines)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDelete)))
                        .addGap(0, 600, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rTextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxWrapLines)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCurrentValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNewValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButtonDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonOk)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        ArrayList<String> xPaths = getXPaths(jTextFieldCurrentValue.getText());
        XSWxPathJDialog dialog;
        String selection = null;
        if(xPaths.size() > 1) {
            dialog = new XSWxPathJDialog(xPaths);
            selection = dialog.getSelection();
        } else if (xPaths.size() == 1) {
            selection = xPaths.get(0);
        }
        if (selection != null && !valuePairs.containsKey(selection)) {
            counter++;
            valuePairs.put(selection, jTextFieldNewValue.getText());
            tableModel.addRow(new TableEntry(counter, selection, jTextFieldCurrentValue.getText(), jTextFieldNewValue.getText()));
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        valuePairs.remove((String) tableModel.getValueAt(table.getSelectedRow(), 1));
        tableModel.remove(table.getSelectedRow());
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jCheckBoxWrapLinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxWrapLinesActionPerformed
        if (jCheckBoxWrapLines.isSelected()) {
            rSyntaxTextArea.setLineWrap(true);
        } else {
            rSyntaxTextArea.setLineWrap(false);
        }
    }//GEN-LAST:event_jCheckBoxWrapLinesActionPerformed

    private void initTable() {
        tableModel = new TableModel();
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneTable.setViewportView(table);
        //Enable sorting
        TableRowSorter<TableModel> sorter = new TableRowSorter<>();
        table.setRowSorter(sorter);
        sorter.setModel(tableModel);
    }
    
    private ArrayList<String> getXPaths(String input) {
        ArrayList<String> xPaths = new ArrayList<>();
        // check as node value
        try {
            List nodes = DomUtilities.evaluateXPath(doc, "//*[text()='" + jTextFieldCurrentValue.getText() + "']");
            List nodesPaths = DomUtilities.nodelistToFastXPathList(nodes);
            xPaths.addAll(nodesPaths);
        } catch (XPathExpressionException ex) {
            Logging.getInstance().log(getClass(), "Incorrect XPath", Logging.ERROR);
        }
        // check as attribute value
        try {
            List nodes = DomUtilities.evaluateXPath(doc, "//attribute::*[string()='" + jTextFieldCurrentValue.getText() + "']");
            List nodesPaths = DomUtilities.nodelistToFastXPathList(nodes);
            xPaths.addAll(nodesPaths);
        } catch (XPathExpressionException ex) {
            Logging.getInstance().log(getClass(), "Incorrect XPath", Logging.ERROR);
        }
        return xPaths;
    }
    
    private void initEditor() {
        rSyntaxTextArea.setText(XMLHelper.format(message, 2));
    }
    
    public HashMap<String, String> getValuePairs() {
        return valuePairs;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JCheckBox jCheckBoxWrapLines;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldCurrentValue;
    private javax.swing.JTextField jTextFieldNewValue;
    private org.fife.ui.rsyntaxtextarea.RSyntaxTextArea rSyntaxTextArea;
    private org.fife.ui.rtextarea.RTextScrollPane rTextScrollPane;
    // End of variables declaration//GEN-END:variables
}
