package model;

public class Aeronave implements Identifiable {
    private long id;
    private String nome;
    private Double peso;
    private long qtdAssentosTotal;

    private long companhiaId;
    private CompanhiaAerea companhia;

    public Aeronave() {}

    public Aeronave(long id, String nome, Double peso, long qtdAssentosTotal, long companhiaId, CompanhiaAerea companhia) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.qtdAssentosTotal = qtdAssentosTotal;
        this.companhiaId = companhiaId;
        this.companhia = companhia;
    }

    @Override
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public long getQtdAssentosTotal() { return qtdAssentosTotal; }
    public void setQtdAssentosTotal(long qtdAssentosTotal) { this.qtdAssentosTotal = qtdAssentosTotal; }

    public long getCompanhiaId() { return companhiaId; }
    public void setCompanhiaId(long companhiaId) { this.companhiaId = companhiaId; }

    public CompanhiaAerea getCompanhia() { return companhia; }
    public void setCompanhia(CompanhiaAerea companhia) {
        this.companhia = companhia;
        this.companhiaId = companhia != null ? companhia.getId() : this.companhiaId;
    }
}
