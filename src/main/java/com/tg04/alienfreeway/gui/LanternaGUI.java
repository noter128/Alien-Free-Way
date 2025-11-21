package com.tg04.alienfreeway.gui;

import com.tg04.alienfreeway.model.Position;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.tg04.alienfreeway.model.game.elements.Enemy;
import com.tg04.alienfreeway.model.game.elements.PowerUpType;
import com.tg04.alienfreeway.model.game.elements.ShotType;
import com.tg04.alienfreeway.model.game.elements.enemies.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {
    private final Screen screen;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }
    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        return terminalFactory.createTerminal();
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/synemono.ttf");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    @Override
    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        if (keyStroke.getKeyType() == KeyType.EOF) return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return ACTION.QUIT;

        if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        if (keyStroke.getKeyType() == KeyType.ArrowRight) return ACTION.RIGHT;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
        if (keyStroke.getKeyType() == KeyType.ArrowLeft) return ACTION.LEFT;

        if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.SELECT;

        if (keyStroke.getKeyType() == KeyType.Escape) return ACTION.PAUSE;

        return ACTION.NONE;
    }

    @Override
    public void drawPlayer(Position position) {
        drawCharacter(position.getX(), position.getY(), ">8]", "#FFD700");
    }

    @Override
    public void drawEnemy(Position position, Enemy enemy) {
        String color = getColorForEnemy(enemy);
        String symbol = getSymbolForEnemy(enemy);
        drawCharacter(position.getX(), position.getY(), symbol, color);
    }

    private String getColorForEnemy(Enemy enemy) {
        if (enemy instanceof BasicEnemy) {
            return "#FF0000";
        } else if (enemy instanceof ArmoredEnemy) {
            return "#808080";
        } else if (enemy instanceof FastEnemy) {
            return "#FFA500";
        } else if (enemy instanceof HealingEnemy) {
            return "#00FF00";
        } else if (enemy instanceof StealthEnemy) {
            return ((StealthEnemy) enemy).isVisible() ? "#8A2BE2" : "#000000";
        } else if (enemy instanceof ZigZagEnemy) {
            return "#00FFFF";
        }
        return "#FFFFFF";
    }

    private String getSymbolForEnemy(Enemy enemy) {
        if (enemy instanceof BasicEnemy) {
            return "@";
        } else if (enemy instanceof ArmoredEnemy) {
            return "&";
        } else if (enemy instanceof FastEnemy) {
            return "<";
        } else if (enemy instanceof HealingEnemy) {
            return "+";
        } else if (enemy instanceof StealthEnemy) {
            return "~";
        } else if (enemy instanceof ZigZagEnemy) {
            return "$";
        }
        return "?";
    }


    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY(), text);
    }

    private void drawCharacter(int x, int y, String c, String color) {
        if (x >= 0 && x < screen.getTerminalSize().getColumns() &&
                y >= 0 && y < screen.getTerminalSize().getRows()) {
            TextGraphics tg = screen.newTextGraphics();
            tg.setForegroundColor(TextColor.Factory.fromString(color));
            tg.putString(x, y, String.valueOf(c));
        }
    }

    @Override
    public void drawShot(Position position, ShotType shotType) {
        String color;
        String character;

        switch (shotType) {
            case BULLET:
                color = "#FFD700";
                character = "-";
                break;
            case MISSILE:
                color = "#FF4500";
                character = ">";
                break;
            case LASER:
                color = "#00FFFF";
                character = "===========";
                break;
            case POWER_SHOT:
                color = "#FF0000";
                character = "*";
                break;
            default:
                color = "#FFFFFF";
                character = "->";
                break;
        }

        drawCharacter(position.getX(), position.getY(), character, color);
    }

    @Override
    public void drawPowerUp(Position position, PowerUpType type) {
        String color;
        String symbol;

        switch (type) {
            case DOUBLE_SHOT_SPEED:
                color = "#00FFFF";
                symbol = "SHOT";
                break;
            case TEMPORARY_IMMUNITY:
                color = "#FFD700";
                symbol = "IMMUN";
                break;
            case DOUBLE_MONEY:
                color = "#00FF00";
                symbol = "CASH";
                break;
            case SLOW_ENEMIES:
                color = "#808080";
                symbol = "SLOW";
                break;
            case HEAL:
                color = "#FF0000";
                symbol = "HEAL";
                break;
            default:
                color = "#FFFFFF";
                symbol = "??";
                break;
        }

        drawCharacter(position.getX(), position.getY(), symbol, color);
    }

    @Override
    public void drawBackground(int width, int height, int health, int wallet, String ShotTypeTitle, int score, boolean hasExtraLife) {
        for (int x = 0; x < width; x++) {
            drawCharacter(x, 0, "#", "#FFFFFF");
            drawCharacter(x, height - 1, "#", "#FFFFFF");
        }

        drawText(new Position(5, 1), "Health: " + health, "#FF0000");
        drawText(new Position(22, 1), "Wallet: " + wallet, "#00FF00");
        drawText(new Position(40, 1), "Weapon: " + ShotTypeTitle, "#00FFFF");
        drawText(new Position(62, 1), "Score: " + score, "#FFFF00");


        int middleY = height / 2;
        for (int x = 0; x < width; x += 2) {
            drawCharacter(x, middleY, "-", "#FFFFFF");
        }

        String extraLifeText = hasExtraLife ? "Activated" : "Disabled";
        drawText(new Position(58, height - 2), "Extra Life: " + extraLifeText, "#FFD700");
    }

    @Override
    public void drawTitleWithHighlight(Position position, String title, String color1, String color2) {
        TextGraphics tg = screen.newTextGraphics();
        int x = position.getX();
        int y = position.getY();

        if (title.contains("FREE")) {
            String[] parts = title.split("FREE", 2);

            tg.setForegroundColor(TextColor.Factory.fromString(color1));
            tg.putString(x, y, parts[0]);

            tg.setForegroundColor(TextColor.Factory.fromString(color2));
            tg.putString(x + parts[0].length(), y, "FREE");

            if (parts.length > 1) {
                tg.setForegroundColor(TextColor.Factory.fromString(color1));
                tg.putString(x + parts[0].length() + "FREE".length(), y, parts[1]);
            }
        } else {
            tg.setForegroundColor(TextColor.Factory.fromString(color1));
            tg.putString(x, y, title);
        }
    }



    @Override
    public void drawBox(Position topLeft, int width, int height, String color) {
        String horizontalLine = "+" + new String(new char[width - 2]).replace('\0', '-') + "+";
        String sideSpace = "|" + new String(new char[width - 2]).replace('\0', ' ') + "|";

        drawText(topLeft, horizontalLine, color);

        for (int i = 1; i < height - 1; i++) {
            drawText(new Position(topLeft.getX(), topLeft.getY() + i), sideSpace, color);
        }

        drawText(new Position(topLeft.getX(), topLeft.getY() + height - 1), horizontalLine, color);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    public void close() throws IOException {
        screen.close();
    }
}
