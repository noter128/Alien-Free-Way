package com.tg04.alienfreeway.controller.menu;

import com.tg04.alienfreeway.Game;
import com.tg04.alienfreeway.gui.GUI;
import com.tg04.alienfreeway.model.game.elements.ShotType;
import com.tg04.alienfreeway.model.menu.ShopMenu;
import com.tg04.alienfreeway.model.game.elements.Player;
import com.tg04.alienfreeway.sound.SoundPlayer;
import com.tg04.alienfreeway.states.MidGameMenuState;
import com.tg04.alienfreeway.viewer.menu.MenuViewer;
import com.tg04.alienfreeway.viewer.menu.ShopMenuViewer;

import java.io.IOException;

public class ShopMenuController extends MenuController {
    private final Player player;
    private final MenuViewer menuViewer;

    public ShopMenuController(ShopMenu model, Player player) {
        this(model, player, new ShopMenuViewer(model, player));
    }

    public ShopMenuController(ShopMenu model, Player player, MenuViewer menuViewer) {
        super(model);
        this.player = player;
        this.menuViewer = menuViewer;
    }

    public void executeSelectedOption(Game game) {
        ShopMenu menu = (ShopMenu) getModel();
        int selectedIndex = menu.getSelectedIndex();

        switch (selectedIndex) {
            case 0:
                if (buyItem(game, 700, "Weapon Laser purchased!")) {
                    player.setTemporaryWeapon(ShotType.LASER);
                }
                break;
            case 1:
                if (buyItem(game, 1000, "Weapon Missile purchased!")) {
                    player.setTemporaryWeapon(ShotType.MISSILE);
                }
                break;
            case 2:
                if (buyItem(game, 1500, "Weapon Power Shot purchased!")) {
                    player.setTemporaryWeapon(ShotType.POWER_SHOT);
                }
                break;
            case 3:
                buyItem(game, 10000, "Extra Life purchased!");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private boolean buyItem(Game game, int cost, String successMessage) {
        if (player.getWallet() >= cost) {
            player.deductWallet(cost);
            if(successMessage.equals("Extra Life purchased!")){
                player.buyExtraLife();
            }
            System.out.println(successMessage);
            SoundPlayer.playSound("sounds/buy_item.wav");
            game.setState(game.getPreviousState());
            return true;
        } else {
            System.out.println("Not enough coins!");
            return false;
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        switch (action) {
            case UP:
                SoundPlayer.playSound("sounds/menu_navigate.wav");
                getModel().previousEntry();
                break;
            case DOWN:
                SoundPlayer.playSound("sounds/menu_navigate.wav");
                getModel().nextEntry();
                break;
            case SELECT:
                SoundPlayer.playSound("sounds/menu_select.wav");
                executeSelectedOption(game);
                break;
            case PAUSE:
                SoundPlayer.playSound("sounds/menu_pause.wav");
                game.setState(new MidGameMenuState(player), false);
                break;
            default:
                break;
        }
    }

    @Override
    public void render(GUI gui) throws IOException {
        if (menuViewer != null) {
            menuViewer.draw(gui);
        }
    }
}

