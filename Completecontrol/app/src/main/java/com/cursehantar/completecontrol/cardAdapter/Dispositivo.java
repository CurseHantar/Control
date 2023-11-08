package com.cursehantar.completecontrol.cardAdapter;

public class Dispositivo {
    public String nombreCell;
    public String numeroCell;
    public String marcaCell;
    public int imagen;

    public Dispositivo(String nombreCell, String numeroCell, String marcaCell, int imagen) {
        this.nombreCell = nombreCell;
        this.numeroCell = numeroCell;
        this.marcaCell = marcaCell;
        this.imagen = imagen;
    }


    public String getNombreCell() {
        return nombreCell;
    }

    public void setNombreCell(String nombreCell) {
        this.nombreCell = nombreCell;
    }

    public String getNumeroCell() {return numeroCell;
    }

    public void setNumeroCell(String numeroCell) {
        this.numeroCell = numeroCell;
    }

    public String getMarcaCell() {
        return marcaCell;
    }

    public void setMarcaCell(String marcaCell) {
        this.marcaCell = marcaCell;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

}
