package com.example.multithreading.threadlocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 吕茂陈
 * @date 2022/03/26 15:03
 */
public class ConnectionManager3 {

    /**
     * 在每个线程中对改变量创建一个副本，即每个线程内部有会有一个这个变量，线程之间互不影响
     * 既不存在线程安全问题，也不会影响执行性能
     * 唯一要注意的是，由于在每个线程中都创建了副本，要考虑它占用了更多的内存
     */
    private static final ThreadLocal<Connection> dbConnectionLocal = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection("", "", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    });


    public Connection getConnection() {
        return dbConnectionLocal.get();
    }


}
