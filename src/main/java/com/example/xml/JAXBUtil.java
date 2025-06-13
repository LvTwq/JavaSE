package com.example.xml;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/10/28 11:30
 */
@Slf4j
@UtilityClass
public class JAXBUtil {

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


    public static <T> String listBean2Str(List<T> list) {
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
}
