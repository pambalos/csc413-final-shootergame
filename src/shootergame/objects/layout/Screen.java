package shootergame.objects.layout;

import static shootergame.helpers.GameConstants.INFO_OFFSET_X;
import static shootergame.helpers.GameConstants.INFO_OFFSET_Y;
import static shootergame.helpers.GameConstants.SCREEN_HEIGHT;
import static shootergame.helpers.GameConstants.SCREEN_WIDTH;

import shootergame.objects.GameObject;
import shootergame.objects.Player;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Screen extends GameObject {
    BufferedImage world, screenImage;
    int printX, printY;
    Player player;

    public Screen(BufferedImage world, Player player, int printX, int printY) {
        this.world = world;
        this.player = player;
        this.setX(calculateXFromTank());
        this.setY(calculateYFromTank());
        this.printX = printX;
        this.printY = printY;
        //this.checkBorder();
        this.screenImage = world;
        //this.screenImage = world.getSubimage(this.getX(), this.getY(), SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    private int calculateXFromTank() {
        return player.getX()-SCREEN_WIDTH/4;
    }

    private int calculateYFromTank() {
        return player.getY()-SCREEN_HEIGHT/2;
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(screenImage, 0, 0, null);

        Font font = new Font("Serif", Font.BOLD, 20);
        g2.setFont(font);
        g2.drawString("Health: " + player.getHealth(), this.getX()+INFO_OFFSET_X, this.getY()+INFO_OFFSET_Y); //Draw health count
        g2.drawString("Lives: " + player.getLives(), this.getX()+INFO_OFFSET_X, this.getY()+INFO_OFFSET_Y + 30); //Draw Life Count
    }

    @Override
    public void update() {
        this.screenImage = world;
        //this.screenImage = world.getSubimage(this.getX(), this.getY(), SCREEN_WIDTH, SCREEN_HEIGHT);
    }
}
