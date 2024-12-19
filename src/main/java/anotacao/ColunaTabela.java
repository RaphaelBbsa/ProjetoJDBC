package anotacao;


import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColunaTabela {
    String dbName();

    String setJavaName();
}
