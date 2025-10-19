package model;

public class Aeroporto implements Identifiable {
    private long id;
    private String nome;
    private String localizacao;

    private long companhiaId;
    private CompanhiaAerea companhia;

    public Aeroporto() {}

    public Aeroporto(long id, String nome, String localizacao, long companhiaId, CompanhiaAerea companhia) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.companhiaId = companhiaId;
        this.companhia = companhia;
    }

    @Override
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public long getCompanhiaId() { return companhiaId; }
    public void setCompanhiaId(long companhiaId) { this.companhiaId = companhiaId; }

    public CompanhiaAerea getCompanhia() { return companhia; }
    public void setCompanhia(CompanhiaAerea companhia) {
        this.companhia = companhia;
        this.companhiaId = companhia != null ? companhia.getId() : this.companhiaId;
    }
}
