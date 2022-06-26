package com.example.invoke;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author 吕茂陈
 */
public class ObjectPoolFactory {

    /**
     * 对象名-实际对象
     */
    private final Map<String, Object> objectPool = new HashMap<>();

    /**
     * 创建对象
     *
     * @param clazzName 字符串类名
     * @return 对象
     */
    private Object createObject(String clazzName) throws Exception {
        // 根据字符串来获取对应的Class对象
        Class<?> clazz = Class.forName(clazzName);
        // 使用clazz对应类的默认构造器创建实例
        return clazz.getConstructor().newInstance();
    }

    /**
     * 读取属性文件，对属性文件中每个key-value对创建一个Java对象，
     * 其中value是该Java对象的实现类，key是该Java对象放入对象池中的名字
     * @param fileName
     */
    public void initPool(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            Properties props = new Properties();
            props.load(fis);
            // todo 为什么 foreach不行？
//            props.stringPropertyNames().forEach(e -> objectPool.put(e, createObject(props.getProperty(e))));
            for (String e : props.stringPropertyNames()) {
                objectPool.put(e, createObject(props.getProperty(e)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getObject(String name){
        return objectPool.get(name);
    }

    public static void main(String[] args) {
        ObjectPoolFactory pof = new ObjectPoolFactory();
        pof.initPool("obj.txt");
        System.out.println(pof.getObject("a"));
        System.out.println(pof.getObject("b"));
    }

}
