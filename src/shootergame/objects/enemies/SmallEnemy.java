package shootergame.objects.enemies;

import static shootergame.helpers.GameConstants.SCREEN_HEIGHT;

import shootergame.helpers.ResourceLoader;
import shootergame.objects.GameObject;
import shootergame.objects.Laser;
import shootergame.objects.Obstacle;
import shootergame.objects.Player;
import shootergame.objects.Ship;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * If I had more time, this would be one among many enemy types... And they would shoot...
 */
public class SmallEnemy extends Ship implements Obstacle {

    private int spawnTime;
    private Player player;

    public SmallEnemy(int x,int speed, int spawnTime, BufferedImage image, ArrayList<GameObject> gameObjects, Player player) {
        this.setX(x);
        this.setY(0-image.getHeight());
        this.setSpeed(speed);
        this.setImage(image);
        this.setHitBox(new Rectangle(x, this.getY(), this.getImage().getWidth(), this.getImage().getHeight()));
        this.setGameObjects(gameObjects);
        this.spawnTime = spawnTime;
        this.setHealth(3);
        this.player = player;
    }

    @Override
    public void update() {
        this.setY(this.getY()+getSpeed());
        this.getHitBox().setLocation(this.getX(), this.getY());
        if (this.getY() > SCREEN_HEIGHT + this.getImage().getHeight()) {
            player.takeDamage(1);
            getGameObjects().remove(this);
        }
        if (this.getCounter() > this.getLoadTime()) {
            getGameObjects().add(new Laser(this.getX(), this.getY(), ResourceLoader.getResourceImage("enemy1Laser"), this, this.getSpeed()*2));
            this.setCounter(0);
            System.out.println("Should shoot...");
        }
        this.setCounter(this.getCounter()+1);
    }

    @Override
    public int getSpawnTime() {
        return this.spawnTime;
    }
}