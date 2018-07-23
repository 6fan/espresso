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
package de.rub.nds.burp.utilities;


import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.*;
import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.SAXException;
import org.xml.sax.InputSource;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
/**
 * Help pretty print XML content
 * @author Tim Guenther
 * @version 1.0
 */
public abstract class XMLHelper {

    /**
    * Create an indention to pretty print the XML.
    * Attention: Pretty printed XML doesn't work for requests. 
    * @param input The XML raw data.
    * @param indent The indents width.
    * @return Indented XML or original input if an Exception is thrown internally.
    */

    public static String format(String input, int indent) {
        // javax.xml.transform.Transformer does not keep DTDs and always expands
        // entity references defined in inline DTDs - so we do not pretty-print those
        if (input.toUpperCase().contains("DOCTYPE")) {
            Logging.getInstance().log(XMLHelper.class,"XML contains inline DTD, skip pretty printing", Logging.DEBUG);
            return input;
        }
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET,"");
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, input.startsWith("<?xml") ? "yes" : "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(indent));
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (IllegalArgumentException | TransformerException e) {
            Logging.getInstance().log(XMLHelper.class, e);
            return input;
        }
    }

    public static String docToString(Document doc) {
        try {
            Source docInput = new DOMSource(doc);
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET,"");
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(docInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (IllegalArgumentException | TransformerException e) {
            Logging.getInstance().log(XMLHelper.class, e);
            return "<error>Failed to transform document</error>";
        }
    }    
    
    public static Document stringToDom (String xmlString) {
        try {
            InputSource input = new InputSource(new StringReader(xmlString));
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            documentFactory.setNamespaceAware(true);
            documentFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            Document dom = builder.parse(input);
            return dom;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            Logging.getInstance().log(XMLHelper.class, e);
            return stringToDom("<error>Failed to parse input XML</error>");
        }
    }
    
    public static Node getElementByXPath (Document doc, String xPath) {
        try {
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile(xPath);
            Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
            return node;
        } catch (XPathExpressionException e) {
            Logging.getInstance().log(XMLHelper.class, e);
            return null;
        }
    }
}
