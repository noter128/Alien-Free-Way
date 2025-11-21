package com.tg04.alienfreeway.model.menu;

import java.util.Arrays;

public class MainMenu extends Menu {
    public MainMenu() {
        super(Arrays.asList("Play", "Quit"));
    }

    public boolean isSelectedSpecific(String action) {
        return action.equalsIgnoreCase("play") ? isSelected(0) : isSelected(1);
    }
}
