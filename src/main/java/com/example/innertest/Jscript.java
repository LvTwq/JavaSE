package com.example.innertest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.script.*;

/**
 * @author 吕茂陈
 * @description
 * @date 2022/12/8 14:54
 */
@Slf4j
public class Jscript {


    @Test
    public void test01() {
        // 获得脚本引擎
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        // 进行脚本编译
        String script = "function process(){\n" +
                "var a=10\n" +
                "var b=3\n" +
                "return a*b-c\n" +
                "}\n" +
                "process()";
        try {
            CompiledScript compiledScript = ((Compilable) scriptEngine).compile(script);
            // 绑定Java的参数
            SimpleBindings bindings = new SimpleBindings();
            bindings.put("c", 5);
            // 执行并打印结果
            Object eval = compiledScript.eval(bindings);
            log.info("{}", eval);
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }
}
