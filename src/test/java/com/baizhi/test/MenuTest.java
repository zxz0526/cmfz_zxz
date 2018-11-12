package com.baizhi.test;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuTest extends BaseTest{
    @Autowired
    private MenuService menuService;
    @Test
    public void queryALl(){
        List<Menu> menus = menuService.queryAll();
        for (Menu menu : menus) {
            System.out.println(menu);
            List<Menu> menus1 = menu.getMenus();
            System.out.println("------------------------");
            for (Menu menu1 : menus1) {
                System.out.println(menu1);
            }
        }
    }
}
