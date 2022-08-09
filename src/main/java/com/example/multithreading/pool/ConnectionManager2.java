package com.example.multithreading.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 吕茂陈
 * @date 2022/03/26 15:12
 */
public class ConnectionManager2 {

    /**
     * 不需要将connect进行共享
     * 因为各个线程对connect变量的访问实际上是没有依赖关系的，即 一个线程不需要关心其他线程是否对这个connect进行了修改
     */
    private Connection connect = null;

    public Connection openConnection() {
        if (connect == null) {
            try {
                connect = DriverManager.getConnection("", "", "");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connect;
    }

    public void closeConnection() {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}


class Dao {


    /**
     * 由于每次都在方法内部创建连接，那么线程之间自然不存在线程安全问题
     * 但这样非常影响性能，由于在方法中频繁开启合关闭数据库连接
     */
    public void insert() {
        ConnectionManager2 connectionManager = new ConnectionManager2();
        Connection connection = connectionManager.openConnection();

        // 使用connection进行操作

        connectionManager.closeConnection();
    }
}
