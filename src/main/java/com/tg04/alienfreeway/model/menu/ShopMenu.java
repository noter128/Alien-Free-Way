package com.tg04.alienfreeway.model.menu;

import java.util.Arrays;

public class ShopMenu extends MidGameMenu {
    public ShopMenu() {
        super();
        super.entries = Arrays.asList(
                "Laser - 700 coins",
                "Missile - 1000 coins",
                "Power Shot - 1500 coins",
                "Extra Life - 10 000 coins"
        );
    }

    @Override
    public boolean isSelectedSpecific(String action) {
        return action.equalsIgnoreCase("weapon1") ? isSelected(0) :
                action.equalsIgnoreCase("weapon2") ? isSelected(1) :
                        action.equalsIgnoreCase("weapon3") ? isSelected(2) :
                                action.equalsIgnoreCase("extralife") && isSelected(3);
    }
}
