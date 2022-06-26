package com.example.repeatcode;


import lombok.Data;

@Data
@BankAPI(url = "/bank/createUser", desc = "创建用户接口")
public class CreateUserAPI {

    @BankAPIField(order = 1,type = "S", length = 10)
    private String name;

    @BankAPIField(order = 2, type = "S", length = 18)
    private String identity;

    /**
     * 注意这里的order需要按照API表格中的顺序
     */
    @BankAPIField(order = 4, type = "S", length = 11)
    private String mobile;

    @BankAPIField(order = 3, type = "N", length = 5)
    private int age;
}
