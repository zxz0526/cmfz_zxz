package com.baizhi.entity;

import java.util.List;

public class Menu {

    private String id;
    private String name;
    private String iconCls;
    private String href;
    private String children;
    private List<Menu> menus;
    private Menu menu;
    public Menu() {
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", href='" + href + '\'' +
                ", children='" + children + '\'' +
                ", menus=" + menus +
                ", menu=" + menu +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu(String id, String name, String iconCls, String href, String children, List<Menu> menus, Menu menu) {
        this.id = id;
        this.name = name;
        this.iconCls = iconCls;
        this.href = href;
        this.children = children;
        this.menus = menus;
        this.menu = menu;
    }
}
