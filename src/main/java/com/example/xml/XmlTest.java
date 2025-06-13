package com.example.xml;

import com.example.json.JackJsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.*;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/10/17 17:02
 */
@Slf4j
public class XmlTest {

    @Test
    public void bean2xml() {
        RangeDto rangeDto = new RangeDto("dnsId", "all", "", List.of("1", "lvmc"));
        String s = bean2Str(rangeDto);
        System.out.println(s);
    }

    @Test
    public void xml2bean() {
        String xml = "<range>\n" +
                "    <dnsId>dnsId</dnsId>\n" +
                "    <effectiveScope>all</effectiveScope>\n" +
                "    <serverId></serverId>\n" +
                "    <strList>1</strList>\n" +
                "    <strList>lvmc</strList>\n" +
                "</range>";
        System.out.println(str2Bean(xml, RangeDto.class));
    }


    public static String bean2Str(Object obj) {
        try {
            StringWriter writer = new StringWriter();
            JAXB.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            log.error("对象转字符串失败", e);
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    public static <T> T str2Bean(String str, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(str);
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            log.error("{} 转对象失败", str, e);
        }
        return null;
    }

    public static String listBean2Str(List<Object> list) {
        try {
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(list.get(0).getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            writer.write("<root>\n");
            for (Object obj : list) {
                marshaller.marshal(obj, writer);
            }
            writer.write("\n</root>");

            return writer.toString();
        } catch (Exception e) {
            log.error("对象列表转字符串失败", e);
        }
        return "";
    }


    @Test
    public void xml2List() {
        String str = "<root>\n" +
                "    <item>\n" +
                "        <id>1</id>\n" +
                "        <name>Item 1</name>\n" +
                "    </item>\n" +
                "    <item>\n" +
                "        <id>2</id>\n" +
                "        <name>Item 2</name>\n" +
                "    </item>\n" +
                "    <item>\n" +
                "        <id>3</id>\n" +
                "        <name>Item 3</name>\n" +
                "    </item>\n" +
                "</root>";
        Root<Item> root = str2Bean(str, Root.class);
        System.out.println(JackJsonUtil.obj2String(root));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
//    @XmlRootElement(name = "item")
    public static class Item {
        private int id;
        private String name;
    }


    public static <T> String convertListToXml(List<T> list, Class<T> clazz) {
        try {
            // 创建包装类
            ItemsWrapper<T> wrapper = new ItemsWrapper<>(list);

            // 创建 JAXBContext
            JAXBContext jaxbContext = JAXBContext.newInstance(wrapper.getClass());

            // 创建 Marshaller
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // 格式化输出

            // 将对象转换为 XML 字符串
            StringWriter sw = new StringWriter();
            marshaller.marshal(wrapper, sw);

            // 返回 XML 字符串
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // 创建 Person 对象列表
        List<Item> persons = new ArrayList<>();
        persons.add(new Item(30,"Alice"));
        persons.add(new Item(25, "Bob"));

        // 调用通用方法转换为 XML
        String xmlOutput = convertListToXml(persons, Item.class);
        System.out.println(xmlOutput);
    }


    public static XMLReader getXmlReader() {
        try {
            final XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            reader.setFeature("http://xml.org/sax/features/namespaces", true);
            reader.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
            reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
            reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            return reader;
        } catch (final Exception e) {
            throw new RuntimeException("Unable to create XMLReader", e);
        }
    }


    public static String getTextForElement(final String xmlAsString, final String element) {
        final XMLReader reader = getXmlReader();
        final StringBuilder builder = new StringBuilder();

        final DefaultHandler handler = new DefaultHandler() {

            private boolean foundElement = false;

            @Override
            public void startElement(final String uri, final String localName, final String qName,
                                     final Attributes attributes) throws SAXException {
                if (localName.equals(element)) {
                    this.foundElement = true;
                }
            }

            @Override
            public void endElement(final String uri, final String localName, final String qName) throws SAXException {
                if (localName.equals(element)) {
                    this.foundElement = false;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (this.foundElement) {
                    builder.append(ch, start, length);
                }
            }
        };

        reader.setContentHandler(handler);
        reader.setErrorHandler(handler);

        try {
            reader.parse(new InputSource(new StringReader(xmlAsString)));
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

        return builder.toString();
    }


    @Test
    public void testXml() {
        String attr = getTextForElement("<?xml version=\"1.0\" encoding=\"UTF-8\"?><message><head><version>1.0</version><serviceType>AuthenService</serviceType><messageState>true</messageState><messageCode>A00F000A</messageCode><messageDesc>证书[CN=杨军,T=test10,O=AHAU,C=CN]未生效，证书有效时间为2025-01-08 14:19:10到2026-01-08 14:19:10。</messageDesc></head><body></body></message>", "messageDesc");
        System.out.println(attr);
        String username = Arrays.stream(attr.split(","))
                .filter(e -> StringUtils.contains(e, "T="))
                .findAny()
                .map(e -> StringUtils.remove(e, "T=").trim())
                .orElse("");
        System.out.println(username);
    }
}
