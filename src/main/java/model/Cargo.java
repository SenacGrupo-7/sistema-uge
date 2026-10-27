package model;

public class Cargo {

    private Short idCargo;
    private String nomeCargo;

    public Short getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Short idCargo) {
        this.idCargo = idCargo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    @Override
    public String toString() {
        return idCargo + " - " + nomeCargo;
    }
}