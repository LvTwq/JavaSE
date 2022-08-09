package com.example.multithreading.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 吕茂陈
 * @date 2022/03/26 15:06
 */
public class ConnectionManager1 {

    /**
     * 到底需不需要将connect变量进行共享？
     */
    private static Connection connect = null;

    /**
     * 有线程安全问题
     * 1、有可能在 openConnection 中多次创建connect
     * 2、由于connect是共享变量，有可能一个线程使用connect进行数据库操作，另一个线程调用closeConnection关闭连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection openConnection() throws SQLException {
        if (connect == null) {
            connect = DriverManager.getConnection("", "", "");
        }
        return connect;
    }

    public static void closeConnection() throws SQLException {
        if (connect != null) {
            connect.close();
        }
    }
}
