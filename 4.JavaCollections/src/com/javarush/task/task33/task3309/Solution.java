package com.javarush.task.task33.task3309;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.Arrays;
import javax.xml.bind.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* 
Комментарий внутри xml
*/

public class Solution {

    public static String toXmlWithComment(Object obj, String tagName, String comment)
            throws ParserConfigurationException, JAXBException, IOException, SAXException, TransformerException {
        Writer writer = new StringWriter();

        JAXBContext.newInstance(obj.getClass())
                .createMarshaller()
                .marshal(obj, writer);

        final Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(writer.toString())));
        final NodeList nodes = document.getElementsByTagName(tagName);

        for (int i = 0, len = nodes.getLength(); i < len; i++) {
            final Node node = nodes.item(i);
            final Comment DOMComment = document.createComment(comment);

            if (node.getNodeName().equals(tagName)) {
                node.getParentNode().insertBefore(DOMComment, node);
            }
        }

        writer = new StringWriter();
        final Transformer transformer = TransformerFactory.newInstance()
                .newTransformer();
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.transform(new DOMSource(document), new StreamResult(writer));
        return writer.toString();
    }

    public static void main(String[] args)
            throws JAXBException, ParserConfigurationException, IOException, SAXException, TransformerException {
        System.out.println(
                toXmlWithComment(
                        shop(), "secretData", "FaBaaaZunga!!!"
                )
        );
    }

    public static Shop shop() throws JAXBException {
        final String xmlData =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + "<shop>\n"
                        + "    <goods>\n" + "        <names>S1</names>\n"
                        + "        <names>S2</names>\n" + "    </goods>\n"
                        + "    <count>12</count>\n" + "    <profit>123.4</profit>\n"
                        + "    <secretData>String1</secretData>\n"
                        + "    <secretData>String2</secretData>\n"
                        + "    <secretData>String3</secretData>\n"
                        + "    <secretData>String4</secretData>\n"
                        + "    <secretData>String5</secretData>\n" + "</shop>";
        final StringReader reader = new StringReader(xmlData);

        return (Shop) JAXBContext.newInstance(Shop.class)
                .createUnmarshaller()
                .unmarshal(reader);
    }

    @XmlRootElement
    @XmlType
    public static class Shop {

        public Goods goods;
        public int count;
        public double profit;
        public String[] secretData;

        @Override
        public String toString() {
            return "Shop{" + "goods=" + goods + ", count=" + count + ", profit=" + profit
                    + ", secretData=" + Arrays.toString(secretData) + '}';
        }

        private static class Goods {

            public List<String> names;

            public Goods() {
            }

            @Override
            public String toString() {
                return "Good{" + "names=" + names + '}';
            }
        }

    }
}
