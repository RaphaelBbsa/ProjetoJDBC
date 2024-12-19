package anotacao;

import java.lang.annotation.*;


@Documented
@Target(ElementType.TYPE) //anotação pra ser usada em classes
@Retention(RetentionPolicy.RUNTIME) //anotação para mapear a classe com a tabela no banco
public @interface Tabela {
  String value();
}
