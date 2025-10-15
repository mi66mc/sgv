package model;

import java.util.Objects;

public class ConfiguracaoDeCompanhia implements Identifiable {
    private Integer id;
    private Boolean passageiroCancelar;

    private Integer companhiaId;
    private CompanhiaAerea companhia;

    public ConfiguracaoDeCompanhia() {}

    public ConfiguracaoDeCompanhia(Integer id, Boolean passageiroCancelar, Integer companhiaId, CompanhiaAerea companhia) {
        this.id = id;
        this.passageiroCancelar = passageiroCancelar;
        this.companhiaId = companhiaId;
        this.companhia = companhia;
    }

    @Override
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Boolean getPassageiroCancelar() { return passageiroCancelar; }
    public void setPassageiroCancelar(Boolean passageiroCancelar) { this.passageiroCancelar = passageiroCancelar; }

    public Integer getCompanhiaId() { return companhiaId; }
    public void setCompanhiaId(Integer companhiaId) { this.companhiaId = companhiaId; }

    public CompanhiaAerea getCompanhia() { return companhia; }
    public void setCompanhia(CompanhiaAerea companhia) {
        this.companhia = companhia;
        this.companhiaId = companhia != null ? companhia.getId() : this.companhiaId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
