package shootergame.objects.powerups;

import shootergame.helpers.ResourceLoader;
import shootergame.objects.Collidable;
import shootergame.objects.Obstacle;
import shootergame.objects.Player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class PowerUp extends Collidable implements Obstacle {
    private String fileKey;
    private int spawnTime;

    public PowerUp(int x, int spawnTime, String key) {
        this.setX(x);
        this.setY(0-ResourceLoader.getResourceImage(key).getHeight());
        this.spawnTime = spawnTime;
        this.setSpeed(2);
        this.fileKey = key;
        this.setHitBox(new Rectangle(this.getX(), this.getY(), ResourceLoader.getResourceImage(fileKey).getWidth(), ResourceLoader.getResourceImage(fileKey).getHeight()));
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(ResourceLoader.getResourceImage(fileKey), this.getX(), this.getY(), null);
    }

    @Override
    public void update() {
        this.setY(this.getY() + this.getSpeed());
        this.getHitBox().setLocation(this.getX(), this.getY());
    }

    public abstract void powerUpPlayer(Player player);

    @Override
    public int getSpawnTime() {
        return spawnTime;
    }
}

