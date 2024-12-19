package anotacao;


import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)//anotação usada em atributos, campos
public @interface TipoChave {
    String value (); //nome da coluna no banco

}
