package com.example.annotation.apt;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

/**
 * 通过apt拿到注解和被注解的对象，然后自动生成一些代码，比如@Getter
 * Java8之前使用mirror Api
 *
 * @author 吕茂陈
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"Persistent", "Id", "Property"})
public class AnnotationProcessor extends AbstractProcessor {

    /**
     * @param annotations
     * @param roundEnv    用于获取注解信息，getElementsAnnotatedWith()
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        // 遍历每个被@Persistent修饰的的class文件
        for (Element t : roundEnv.getElementsAnnotatedWith(Persistent.class)) {
            // 获取正在处理的类名
            Name clazzName = t.getSimpleName();
            // 获取类定义前的@Persistent注解
            Persistent per = t.getAnnotation(Persistent.class);
            try (PrintStream ps = new PrintStream(new FileOutputStream(clazzName + ".hbm.xml"))) {
                // 执行输出
                ps.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                ps.println("<!DOCTYPE hibernate-mapping PUBLIC");
                ps.println("<class name=\"" + t);
                // 输出per的table()的值
                ps.println("\" table=\"" + per.table() + "\">");
                // getEnclosedElements()获取该 Element 里定义的所有程序单元
                for (Element f : t.getEnclosedElements()) {
                    // 只处理成员变量上的注解
                    if (f.getKind() == ElementKind.FIELD) {
                        // 获取成员变量定义前的@Id注解
                        Id id = f.getAnnotation(Id.class);
                        // 当@Id注解存在时输出<id.../>
                        if (id != null) {
                            ps.println("    <id name=\""
                                    + f.getSimpleName()
                                    + "\"column=\"" + id.column()
                                    + "\"type=\"" + id.type()
                                    + "\">");
                            ps.println("    <generator class=\""
                                    + id.generator() + "\"/>");
                            ps.println("    </id>");
                        }
                        // 获取成员变量定义前的@Property注解
                        Property p = f.getAnnotation(Property.class);
                        if (p != null) {
                            ps.println("    <property name=\""
                                    + f.getSimpleName()
                                    + "\"column=\"" + p.column()
                                    + "\"type=\"" + p.type()
                                    + "\">");
                            ps.println("    </class>");
                            ps.println("</hibernate-mapping>");

                        }
                        ps.println();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return true;

    }
}
