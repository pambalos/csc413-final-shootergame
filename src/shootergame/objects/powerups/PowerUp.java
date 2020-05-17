package shootergame.objects.powerups;

import shootergame.helpers.ResourceLoader;
import shootergame.objects.Collidable;
import shootergame.objects.Player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class PowerUp extends Collidable {
    String fileKey;

    public PowerUp(int x, int y, String key) {
        this.setX(x);
        this.setY(y);
        this.setHitBox(new Rectangle(x, y, this.getImage().getWidth(), this.getImage().getHeight()));
        this.fileKey = key;
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(ResourceLoader.getResourceImage(fileKey), this.getX(), this.getY(), null);
    }

    public abstract void powerUpPlayer(Player player);
}

