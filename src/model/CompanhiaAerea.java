package model;

public class CompanhiaAerea implements Identifiable {
    private long id;
    private String nome;
    private String nacionalidade;
    private String email;
    private String senha;

    public CompanhiaAerea() {}

    public CompanhiaAerea(long id, String nome, String nacionalidade, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.email = email;
        this.senha = senha;
    }

    @Override
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
