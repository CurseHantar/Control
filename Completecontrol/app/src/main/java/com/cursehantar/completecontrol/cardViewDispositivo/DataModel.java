package com.cursehantar.completecontrol.cardViewDispositivo;

public class DataModel {

    public String name;
    String version;
    String marca;
    public int id_;
    int image;

    public DataModel(String name, String version, String marca ,int id_, int image) {
        this.name = name;
        this.version = version;
        this.marca = marca;
        this.id_ = id_;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public int getId_() {
        return id_;
    }

    public int getImage() {
        return image;
    }
}
