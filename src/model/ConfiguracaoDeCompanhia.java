package model;

public class ConfiguracaoDeCompanhia implements Identifiable {
    private long id;
    private Boolean passageiroCancelar;

    private long companhiaId;
    private CompanhiaAerea companhia;

    public ConfiguracaoDeCompanhia() {}

    public ConfiguracaoDeCompanhia(long id, Boolean passageiroCancelar, long companhiaId, CompanhiaAerea companhia) {
        this.id = id;
        this.passageiroCancelar = passageiroCancelar;
        this.companhiaId = companhiaId;
        this.companhia = companhia;
    }

    @Override
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Boolean getPassageiroCancelar() { return passageiroCancelar; }
    public void setPassageiroCancelar(Boolean passageiroCancelar) { this.passageiroCancelar = passageiroCancelar; }

    public long getCompanhiaId() { return companhiaId; }
    public void setCompanhiaId(long companhiaId) { this.companhiaId = companhiaId; }

    public CompanhiaAerea getCompanhia() { return companhia; }
    public void setCompanhia(CompanhiaAerea companhia) {
        this.companhia = companhia;
        this.companhiaId = companhia != null ? companhia.getId() : this.companhiaId;
    }
}
