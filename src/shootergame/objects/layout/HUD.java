package shootergame.objects.layout;

import static shootergame.helpers.GameConstants.HUD_OFFSET_X;
import static shootergame.helpers.GameConstants.HUD_OFFSET_Y;

import shootergame.objects.GameObject;
import shootergame.objects.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class HUD extends GameObject {
    private Player player; //used to track which player this hud belongs to

    public HUD(Player player, int printX, int printY) {
        this.setX(printX);
        this.setY(printY);
        this.player = player;
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.CYAN);
        Font font = new Font("Serif", Font.PLAIN, 18);
        g2.setFont(font);
        g2.drawString("Life: " + player.getHealth(), this.getX(), this.getY()); //Draw health count
        g2.drawString("Lives: " + player.getLives(), this.getX(), this.getY() + 30); //Draw Life Count
    }

}
