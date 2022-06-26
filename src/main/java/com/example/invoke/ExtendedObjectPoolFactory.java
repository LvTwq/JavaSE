package com.example.invoke;


import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class ExtendedObjectPoolFactory {

    /**
     * 对象名-实际对象
     */
    private final Map<String, Object> objectPool = new HashMap<>();

    private final Properties config = new Properties();

    /**
     * 从指定属性文件中初始化Properties对象
     *
     * @param fileName
     */
    public void init(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            config.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
     * 读取属性文件，对属性文件中每个key-value对创建一个Java对象
     */
    public void initPool() {
        // 每取出一个key-value对，如果key中不包含百分号，表明是根据value来创建一个对象，调用createObject创建对象，并将对象添加到对象池中
        config.stringPropertyNames().stream().filter(e -> !e.contains("%")).forEach(e -> {
            try {
                objectPool.put(e, createObject(config.getProperty(e)));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    public Object getObject(String name) {
        // 从 objectPool 中取出指定name对应的对象
        return objectPool.get(name);
    }

    public void initProperty() {
        config.stringPropertyNames().stream().filter(e -> e.contains("%"))
                .forEach(e ->
                {
                    // 将配置文件中的key按%分割
                    String[] objAndProp = e.split("%");
                    // 取出调用setter方法的参数值
                    Object target = getObject(objAndProp[0]);
                    // 获取setter方法名：set + ”首字母“ + 剩下部分
                    String mtdName = "set" + objAndProp[1].substring(0, 1).toUpperCase()
                            + objAndProp[1].substring(1);
                    // 通过target的获取它的实现类对应的class对象
                    Class<?> targetClass = target.getClass();
                    try {
                        // 获取希望调用的setter方法
                        Method mtd = targetClass.getMethod(mtdName, String.class);
                        // 通过Method的invoke方法执行setter方法，将config的值作为调用setter方法的参数
                        mtd.invoke(target, config.getProperty(e));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                });
    }

    public static void main(String[] args) throws Exception {
        ExtendedObjectPoolFactory epf = new ExtendedObjectPoolFactory();
        epf.init("spFilePath/extObj.txt");
        epf.initPool();
        epf.initProperty();
        log.info("{}", epf.getObject("a"));
    }
}
