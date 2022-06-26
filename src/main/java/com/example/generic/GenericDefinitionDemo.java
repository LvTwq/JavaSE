package com.example.generic;

public class GenericDefinitionDemo<T> {

    /**
     *
     * @param string
     * @param alibaba
     * @param <String>  String出现在尖括号里，就不是java.lang.String，而仅仅是一个代号
     * @param <T>
     * @param <Alibaba>
     * @return
     */
    static <String, T, Alibaba> String get(String string, Alibaba alibaba) {
        return string;
    }

    public static void main(String[] args) {
        Integer first = 222;
        Long second = 333L;
        // first 不是java.lang.String类型，而是泛型标识符；second
        Integer result = get(first, second);
        System.out.println(result);
    }
}
