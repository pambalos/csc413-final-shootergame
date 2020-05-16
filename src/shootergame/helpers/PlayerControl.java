/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootergame.helpers;


import shootergame.objects.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PlayerControl implements KeyListener {

    private Player player;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;
    private int health, lives;

    
    public PlayerControl(Player player, int up, int down, int left, int right, int shoot) {
        this.player = player;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
        this.health = 5;
        this.lives = 2;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == up) {
            this.player.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.player.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.player.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.player.toggleRightPressed();
        }
        if (keyPressed == shoot) {
            this.player.toggleShootPressed();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == up) {
            this.player.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.player.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.player.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.player.unToggleRightPressed();
        }
        if (keyReleased == shoot) {
            this.player.unToggleShootPressed();
        }
    }
}
