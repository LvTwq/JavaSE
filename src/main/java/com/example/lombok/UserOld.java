package com.example.lombok;

/**
 * @author Jam Fang  https://www.jianshu.com/u/0977ede560d4
 * @version 创建时间：2019.12.5 14:24
 */
public class UserOld {
    private Integer id;
    private String userName;
    private String password;
    private String phone;
    private String email;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
