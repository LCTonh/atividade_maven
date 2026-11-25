package br.com.mvc.model;

public class Cafe {

    private Long id;
    private String nome;
    private String endereco;
    private String bairro;
    private String tipo_cafe_principal;

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCafePrincipal() {
        return tipo_cafe_principal;
    }

    public void setCafePrincipal(String tipo_cafe_principal) {
        this.tipo_cafe_principal = tipo_cafe_principal;
    }
}
