package com.tg04.alienfreeway.controller.menu;

import com.tg04.alienfreeway.controller.Controller;
import com.tg04.alienfreeway.model.menu.Menu;

public abstract class MenuController extends Controller<Menu> {
    public MenuController(Menu model) {
        super(model);
    }
}
