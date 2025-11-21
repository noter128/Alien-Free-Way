package com.tg04.alienfreewaytesting.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.gui.LanternaGUI;
import com.tg04.alienfreeway.model.Position;
import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.model.game.elements.PowerUpType;
import com.tg04.alienfreeway.model.game.elements.ShotType;
import com.tg04.alienfreeway.model.game.elements.enemies.BasicEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LanternaGUITest {

    private Screen screen;
    private TextGraphics textGraphics;
    private LanternaGUI gui;

    @BeforeEach
    public void setUp(){
        screen = mock(Screen.class);
        textGraphics = mock(TextGraphics.class);

        when(screen.newTextGraphics()).thenReturn(textGraphics);
        when(screen.getTerminalSize()).thenReturn(new TerminalSize(80, 24));
        gui = new LanternaGUI(screen);
    }

    @Test
    public void testDrawPlayer() {
        gui.drawPlayer(new Position(10, 5));

        verify(textGraphics).setForegroundColor(TextColor.Factory.fromString("#FFD700"));
        verify(textGraphics).putString(10, 5, ">8]");
    }

    @Test
    public void testDrawEnemy() {
        BasicEnemy enemy = new BasicEnemy(15, 10);
        gui.drawEnemy(new Position(15, 10), enemy);

        verify(textGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        verify(textGraphics).putString(15, 10, "@");
    }

    @Test
    public void testDrawText() {
        gui.drawText(new Position(5, 5), "Hello World", "#FFFFFF");

        verify(textGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(textGraphics).putString(5, 5, "Hello World");
    }

    @Test
    public void testDrawBackground() {
        gui.drawBackground(80, 24, 10, 500, "LASER", 2000, true);

        verify(textGraphics).putString(5, 1, "Health: 10");
        verify(textGraphics).putString(22, 1, "Wallet: 500");
        verify(textGraphics).putString(40, 1, "Weapon: LASER");
        verify(textGraphics).putString(62, 1, "Score: 2000");
        verify(textGraphics).putString(58, 22, "Extra Life: Activated");
    }

    @Test
    public void testDrawPowerUp() {
        gui.drawPowerUp(new Position(3, 3), PowerUpType.HEAL);

        verify(textGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        verify(textGraphics).putString(3, 3, "HEAL");
    }

    @Test
    public void testDrawShot() {
        gui.drawShot(new Position(7, 8), ShotType.MISSILE);

        verify(textGraphics).setForegroundColor(TextColor.Factory.fromString("#FF4500"));
        verify(textGraphics).putString(7, 8, ">");
    }

    @Test
    public void testClear() {
        gui.clear();

        verify(screen).clear();
    }

    @Test
    public void testRefresh() throws IOException {
        gui.refresh();

        verify(screen).refresh();
    }

    @Test
    public void testClose() throws IOException {
        gui.close();

        verify(screen).close();
    }

    @Test
    void testDrawPlayerOutOfBounds() {
        gui.drawPlayer(new Position(100, 50));

        verify(textGraphics, never()).putString(anyInt(), anyInt(), anyString());
    }

    @Test
    void testDrawEnemyWithUnrecognizedType() {
        Enemy unknownEnemy = mock(Enemy.class);
        gui.drawEnemy(new Position(10, 10), unknownEnemy);

        verify(textGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(textGraphics).putString(10, 10, "?");
    }

    @Test
    void testDrawShotOutOfBounds() {
        gui.drawShot(new Position(-1, -1), ShotType.BULLET);

        verify(textGraphics, never()).putString(anyInt(), anyInt(), anyString());
    }

    @Test
    void testDrawBackgroundWithMinimalData() {
        gui.drawBackground(5, 3, 0, 0, "NONE", 0, false);

        verify(textGraphics, times(5)).putString(anyInt(), eq(0), eq("#"));
        verify(textGraphics, times(5)).putString(anyInt(), eq(2), eq("#"));

        verify(textGraphics).putString(5, 1, "Health: 0");
        verify(textGraphics).putString(22, 1, "Wallet: 0");
        verify(textGraphics).putString(40, 1, "Weapon: NONE");
        verify(textGraphics).putString(62, 1, "Score: 0");
        verify(textGraphics).putString(58, 1, "Extra Life: Disabled");
    }

    @Test
    void testDrawTitleWithHighlightWithoutHighlight() {
        gui.drawTitleWithHighlight(new Position(5, 5), "Alien Freeway", "#FFFFFF", "#FFD700");

        verify(textGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(textGraphics).putString(5, 5, "Alien Freeway");
    }

    @Test
    void testDrawBox() {
        gui.drawBox(new Position(5, 5), 10, 3, "#FFFFFF");

        verify(textGraphics).putString(5, 5, "+--------+");
        verify(textGraphics).putString(5, 7, "+--------+");

        verify(textGraphics).putString(5, 6, "|        |");
    }

    @Test
    void testGetNextActionWithArrowKey() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowUp));

        GUI.ACTION action = gui.getNextAction();

        assertEquals(LanternaGUI.ACTION.UP, action);
    }

    @Test
    void testGetNextActionWithUnknownKey() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.Tab));

        GUI.ACTION action = gui.getNextAction();

        assertEquals(LanternaGUI.ACTION.NONE, action);
    }

    @Test
    void testGetNextActionWithCharacterKey() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke('q', false, false));

        GUI.ACTION action = gui.getNextAction();

        assertEquals(LanternaGUI.ACTION.QUIT, action);
    }

    @Test
    void testGetNextActionWithNullKey() throws IOException {
        when(screen.pollInput()).thenReturn(null);

        GUI.ACTION action = gui.getNextAction();

        assertEquals(LanternaGUI.ACTION.NONE, action);
    }

    @Test
    void testDrawCharacterAtEdge() {
        gui.drawPlayer(new Position(79, 23));

        verify(textGraphics).setForegroundColor(TextColor.Factory.fromString("#FFD700"));
        verify(textGraphics).putString(79, 23, ">8]");
    }
}
