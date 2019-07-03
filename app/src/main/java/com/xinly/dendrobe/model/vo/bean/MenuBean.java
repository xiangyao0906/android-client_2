package com.xinly.dendrobe.model.vo.bean;

/**
 * Created by zm on 2019-07-03.
 */
public class MenuBean {
    private String name;
    private int icon;
    private int type;

    public MenuBean(int type, String name, int icon) {
        this.name = name;
        this.icon = icon;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
