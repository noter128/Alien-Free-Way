package com.tg04.alienfreeway.model.menu;

import java.util.List;

public abstract class Menu {
    protected List<String> entries;
    private int currentEntry = 0;

    public Menu(List<String> entries) {
        this.entries = entries;
    }

    public void nextEntry() {
        currentEntry = (currentEntry + 1) % entries.size();
    }

    public void previousEntry() {
        currentEntry = (currentEntry - 1 + entries.size()) % entries.size();
    }

    public String getEntry(int index) {
        return entries.get(index);
    }

    public boolean isSelected(int index) {
        return currentEntry == index;
    }

    public int getSelectedIndex() {
        return currentEntry;
    }

    public int getNumberEntries() {
        return entries.size();
    }

}
