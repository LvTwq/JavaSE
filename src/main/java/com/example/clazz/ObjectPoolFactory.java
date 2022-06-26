package com.example.clazz;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Administrator
 */
public class ObjectPoolFactory {

    /**
     * 对象池————key是对象名，value是实际对象
     */
    private Map<String, Object> objectPool = new HashMap<>();


    public static void main(String[] args) throws Exception {
        ObjectPoolFactory pf = new ObjectPoolFactory();
        pf.initPool("obj.txt");
        System.out.println(pf.getObject("a"));
        System.out.println(pf.getObject("b"));
    }

    private Object getObject(String name) {
        // 从objectPool取出指定的name对应的对象
        return objectPool.get(name);
    }

    /**
     * 根据指定文件初始化对象池
     * @param fileName
     * @throws Exception
     */
    private void initPool(String fileName) throws Exception {
        try (
                FileInputStream fis = new FileInputStream(fileName)) {
            Properties props = new Properties();
            props.load(fis);
            for (String name :
                    props.stringPropertyNames()) {
                // 每取出一对key-value对，就根据value创建一个对象，调用createObject()方法创建对象，并将对象添加到对象池中
                objectPool.put(name, createObject(props.getProperty(name)));
            }
        } catch (Exception ex) {
            System.out.println("读取" + fileName + "异常");
        }

    }

    /**
     * 创建对象
     * @param clazzName 字符串类名
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    private Object createObject(String clazzName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 使用字符串来获取对应的class对象
        Class<?> clazz = Class.forName(clazzName);
        // 使用clazz对应类的默认构造器创建实例
        return clazz.getConstructor().newInstance();
    }
}
