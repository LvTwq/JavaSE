package com.example.lambda;

@FunctionalInterface
interface Converter {
    /**
     * 将String类型转换为Integer
     * @param from
     * @return
     */
    Integer convert(String from);
}
