package com.cursehantar.completecontrol.menuItemOptions.optionsCard;

public class Config {

    public String nameOption;
    public String descOption;
    public int imgOption;


    public Config(String nameOption, String descOption,int imgOption) {
        this.nameOption = nameOption;
        this.descOption = descOption;
        this.imgOption = imgOption;
    }

    public String getNameOption() {
        return nameOption;
    }

    public void setNameOption(String nameOption) {
        this.nameOption = nameOption;
    }

    public String getDescOption() {
        return descOption;
    }

    public void setDescOption(String descOption) {
        this.descOption = descOption;
    }

    public int getImgOption() {
        return imgOption;
    }

    public void setImgOption(int imgOption) {
        this.imgOption = imgOption;
    }
}
