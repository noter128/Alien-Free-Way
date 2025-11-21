package com.tg04.alienfreewaytesting.model.menu;

import com.tg04.alienfreeway.model.menu.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private MainMenu mainMenu;
    private GameOverMenu gameOverMenu;
    private MidGameMenu midGameMenu;
    private ShopMenu shopMenu;

    @BeforeEach
    public void setUp() {
        mainMenu = new MainMenu();
        gameOverMenu = new GameOverMenu();
        midGameMenu = new MidGameMenu();
        shopMenu = new ShopMenu();
    }

    @Test
    public void testMainMenuNavigation() {
        assertEquals("Play", mainMenu.getEntry(0));
        assertEquals("Quit", mainMenu.getEntry(1));

        assertTrue(mainMenu.isSelected(0));
        mainMenu.nextEntry();
        assertTrue(mainMenu.isSelected(1));
        mainMenu.nextEntry();
        assertTrue(mainMenu.isSelected(0));

        mainMenu.previousEntry();
        assertTrue(mainMenu.isSelected(1));
    }

    @Test
    public void testMainMenuSpecificSelection() {
        assertTrue(mainMenu.isSelectedSpecific("Play"));
        assertFalse(mainMenu.isSelectedSpecific("Quit"));

        mainMenu.nextEntry();
        assertFalse(mainMenu.isSelectedSpecific("Play"));
        assertTrue(mainMenu.isSelectedSpecific("Quit"));
    }

    @Test
    public void testGameOverMenuNavigation() {
        assertEquals("Use Extra Life", gameOverMenu.getEntry(0));
        assertEquals("Restart", gameOverMenu.getEntry(1));
        assertEquals("Quit", gameOverMenu.getEntry(2));

        assertTrue(gameOverMenu.isSelected(0));
        gameOverMenu.nextEntry();
        assertTrue(gameOverMenu.isSelected(1));
        gameOverMenu.nextEntry();
        assertTrue(gameOverMenu.isSelected(2));
        gameOverMenu.nextEntry();
        assertTrue(gameOverMenu.isSelected(0));
    }

    @Test
    public void testGameOverMenuSpecificSelection() {
        assertTrue(gameOverMenu.isSelectedSpecific("Use Extra Life"));
        assertFalse(gameOverMenu.isSelectedSpecific("Restart"));

        gameOverMenu.nextEntry();
        assertTrue(gameOverMenu.isSelectedSpecific("Restart"));
        assertFalse(gameOverMenu.isSelectedSpecific("Quit"));

        gameOverMenu.nextEntry();
        assertTrue(gameOverMenu.isSelectedSpecific("Quit"));
    }

    @Test
    public void testMidGameMenuNavigation() {
        assertEquals("Resume", midGameMenu.getEntry(0));
        assertEquals("Shop", midGameMenu.getEntry(1));
        assertEquals("Restart", midGameMenu.getEntry(2));
        assertEquals("Quit", midGameMenu.getEntry(3));

        assertTrue(midGameMenu.isSelected(0));
        midGameMenu.nextEntry();
        assertTrue(midGameMenu.isSelected(1));
        midGameMenu.nextEntry();
        assertTrue(midGameMenu.isSelected(2));
        midGameMenu.nextEntry();
        assertTrue(midGameMenu.isSelected(3));
        midGameMenu.nextEntry();
        assertTrue(midGameMenu.isSelected(0));
    }

    @Test
    public void testMidGameMenuSpecificSelection() {
        assertTrue(midGameMenu.isSelectedSpecific("Resume"));
        assertFalse(midGameMenu.isSelectedSpecific("Shop"));

        midGameMenu.nextEntry();
        assertTrue(midGameMenu.isSelectedSpecific("Shop"));
        assertFalse(midGameMenu.isSelectedSpecific("Restart"));

        midGameMenu.nextEntry();
        assertTrue(midGameMenu.isSelectedSpecific("Restart"));
        midGameMenu.nextEntry();
        assertTrue(midGameMenu.isSelectedSpecific("Quit"));
    }

    @Test
    public void testShopMenuNavigation() {
        assertEquals("Laser - 700 coins", shopMenu.getEntry(0));
        assertEquals("Missile - 1000 coins", shopMenu.getEntry(1));
        assertEquals("Power Shot - 1500 coins", shopMenu.getEntry(2));
        assertEquals("Extra Life - 10 000 coins", shopMenu.getEntry(3));

        assertTrue(shopMenu.isSelected(0));
        shopMenu.nextEntry();
        assertTrue(shopMenu.isSelected(1));
        shopMenu.nextEntry();
        assertTrue(shopMenu.isSelected(2));
        shopMenu.nextEntry();
        assertTrue(shopMenu.isSelected(3));
        shopMenu.nextEntry();
        assertTrue(shopMenu.isSelected(0));
    }

    @Test
    public void testShopMenuSpecificSelection() {
        assertTrue(shopMenu.isSelectedSpecific("weapon1"));
        assertFalse(shopMenu.isSelectedSpecific("weapon2"));

        shopMenu.nextEntry();
        assertTrue(shopMenu.isSelectedSpecific("weapon2"));
        assertFalse(shopMenu.isSelectedSpecific("weapon3"));

        shopMenu.nextEntry();
        assertTrue(shopMenu.isSelectedSpecific("weapon3"));
        shopMenu.nextEntry();
        assertTrue(shopMenu.isSelectedSpecific("extralife"));
    }
}
