package com.example.ph20617_mob201_assignment.XMLParse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParse {
    //1, lấy về 1 tài liệu
    public Document getDocument(String xml) throws IOException, SAXException {
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputSource inputSource = new InputSource();// tạo input
        inputSource.setCharacterStream(new StringReader(xml)); // tạo luồng
        inputSource.setEncoding("UTF-8");// set utf8
        document = builder.parse(inputSource);

        return document;
    }

    //2, Lấy về giá trị node
    public String getValue(Element node, String name) {
        //2.1: lấy danh sách các node có cùng name
        NodeList nodeList = node.getElementsByTagName(name);
        // lấy về text của phần tử đầu tiên
        String kq = getTextNodeValue(nodeList.item(0));
        return kq;
    }

    //3, lấy về text của node
    public String getTextNodeValue(Node n) {
        //3.1 khai báo node con
        Node child;
        if (n != null) {
            if (n.hasChildNodes()) {// nếu có node con
                // đưa vào for để lấy thành phần con
                for (child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
                    // kiểm tra có text node k
                    if (child.getNodeType() == Node.TEXT_NODE) {
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }
}
