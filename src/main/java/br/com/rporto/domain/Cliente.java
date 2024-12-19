package br.com.rporto.domain;

import anotacao.ColunaTabela;
import anotacao.Tabela;
import anotacao.TipoChave;

@Tabela("TB_CLIENTE") //Posso usar essas anotações para ver identificar o nome de uma tabela, ou listar todas as colunas mapeadas com @colunatabela
/**
 * ex de codigo: Field [] campos = Cliente.class.getDeclaredFields();
 *      for( campo : campos ) {
 *          if( campo.isAnnotationPresent(ColunaTabela.class){
 *              ColunaTabela coluna = campo.getAnnotation(ColunaTabela.class);
 *              sout (coluna.dbName + coluna.setJavaName);
 *          }
 *      }
 */


public class Cliente {
    @ColunaTabela(dbName = "id", setJavaName = "setId")
    private Long id;

    @ColunaTabela(dbName = "nome", setJavaName = "setNome")
    private String nome;

    @TipoChave("getCpf") //usado para indicar que é chave primaria, que no caso será getCpf
    @ColunaTabela(dbName = "cpf", setJavaName = "setCpf")
    private Long cpf;

    @ColunaTabela(dbName = "tel", setJavaName = "setTel")
    private Long tel;

    @ColunaTabela(dbName = "endereco", setJavaName = "setEndereco")
    private String endereco;

    @ColunaTabela(dbName = "cidade", setJavaName = "setCidade")
    private String cidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
