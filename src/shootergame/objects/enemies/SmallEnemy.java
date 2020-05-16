package shootergame.objects.enemies;

import static shootergame.helpers.GameConstants.SCREEN_HEIGHT;

import shootergame.helpers.PlayerManager;
import shootergame.objects.GameObject;
import shootergame.objects.Obstacle;
import shootergame.objects.Ship;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SmallEnemy extends Ship implements Obstacle {

    private int spawnTime;
    private PlayerManager playerManager;

    public SmallEnemy(int x,int speed, int spawnTime, BufferedImage image, ArrayList<GameObject> gameObjects, PlayerManager playerManager) {
        this.setX(x);
        this.setY(-100);
        this.setSpeed(speed);
        this.setImage(image);
        this.setHitBox(new Rectangle(x, this.getY(), this.getImage().getWidth(), this.getImage().getHeight()));
        this.setGameObjects(gameObjects);
        this.spawnTime = spawnTime;
        this.setHealth(3);
        this.playerManager = playerManager;
    }

    @Override
    public void update() {
        this.setY(this.getY()+getSpeed());
        this.getHitBox().setLocation(this.getX(), this.getY());
        if (this.getY() > SCREEN_HEIGHT + this.getImage().getHeight()) {
            playerManager.takePlayerDamage();
            getGameObjects().remove(this);
        }
    }

    @Override
    public int getSpawnTime() {
        return this.spawnTime;
    }
}