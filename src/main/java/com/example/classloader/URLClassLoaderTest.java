package com.example.classloader;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

/**
 * ���� URLClassLoader ����� loadClass() ����������ָ���ࡣ
 * ���ļ�ϵͳ�м���MySQL��������ʹ�ø�������ȡ���ݿ�����
 *
 * @author ��ï��
 */
public class URLClassLoaderTest {
    private static Connection conn;

    public static void main(String[] args) throws Exception {
        System.out.println(getConn(
                "jdbc:mysql://localhost:3306/library?serverTimezone=UTC",
                "root", "root"
        ));
    }

    private static Connection getConn(String url, String user, String pass) throws Exception {
        if (conn == null) {
            URL[] urls = {new URL("file:mysql-connector-java-5.1.47.jar")};
            // ʹ��Ĭ�ϵ� ClassLoader ��Ϊ ��ClassLoader������URLClassLoader
            URLClassLoader myClassLoader = new URLClassLoader(urls);
            // ����MySQL��JDBC������������Ĭ��ʵ��
            Driver driver = (Driver) myClassLoader.loadClass("com.mysql.jdbc.Driver").getConstructor().newInstance();
            // ����һ������JDBC�������Ե� Properties ���󣬲����� user �� password ��������
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", pass);
            // ����Driver �����connect����ȡ�����ݿ�����
            conn = driver.connect(url, props);
        }
        return conn;
    }
}
