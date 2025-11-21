package com.tg04.alienfreeway.model.menu;

import java.util.Arrays;
import java.util.Locale;

public class MidGameMenu extends Menu {
    public MidGameMenu() {
        super(Arrays.asList("Resume", "Shop", "Restart", "Quit"));
    }

    public boolean isSelectedSpecific(String action) {
        String lowerAction = action.toLowerCase(Locale.ROOT);
        switch (lowerAction) {
            case "resume":
                return isSelected(0);
            case "shop":
                return isSelected(1);
            case "restart":
                return isSelected(2);
            case "quit":
                return isSelected(3);
            default:
                return false;
        }
    }
}


