package com.example.clazz;

import javassist.*;

import java.io.IOException;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/6/21 15:22
 */
public class CreatePerson1 {

    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {
        ClassPool pool = ClassPool.getDefault();

        // 创建一个空类
        CtClass ctClass = pool.makeClass("com.example.clazz.Person1");


        // 新增一个字段 private String name;
        CtField param = new CtField(pool.get("java.lang.String"), "name", ctClass);
        // 访问级别是 private
        param.setModifiers(Modifier.PRIVATE);
        // 初始值是 "xiaoming"
        ctClass.addField(param, CtField.Initializer.constant("lmc"));

        // 生成 getter、setter 方法
        ctClass.addMethod(CtNewMethod.setter("setName", param));
        ctClass.addMethod(CtNewMethod.getter("getName", param));

        // 添加无参的构造函数
        CtConstructor con = new CtConstructor(new CtClass[]{}, ctClass);
        con.setBody("{name = \"xiaohong\";}");
        ctClass.addConstructor(con);

        // 添加有参的构造函数
        con = new CtConstructor(new CtClass[]{pool.get("java.lang.String")}, ctClass);
        // $0=this / $1,$2,$3... 代表方法参数
        con.setBody("{$0.name = $1;}");
        ctClass.addConstructor(con);

        // 创建一个名为printName方法，无参数，无返回值，输出name值
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "printName", new CtClass[]{}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(name);}");
        ctClass.addMethod(ctMethod);

        //这里会将这个创建的类对象编译为.class文件
        ctClass.writeFile("\\code\\JavaSE\\target\\classes");
    }


}
