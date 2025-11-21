package com.tg04.alienfreeway.model.menu;

import java.util.Arrays;
import java.util.Locale;

public class GameOverMenu extends Menu {
    public GameOverMenu() {
        super(Arrays.asList("Use Extra Life", "Restart", "Quit"));
    }

    public boolean isSelectedSpecific(String action) {
        String lowerAction = action.toLowerCase(Locale.ROOT);
        switch (lowerAction) {
            case "use extra life":
                return isSelected(0);
            case "restart":
                return isSelected(1);
            case "quit":
                return isSelected(2);
            default:
                return false;
        }
    }
}
