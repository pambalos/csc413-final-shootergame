package shootergame.objects.enemies;

import shootergame.objects.GameObject;
import shootergame.objects.Ship;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SmallEnemy extends Ship {

    private int spawnTime;

    public SmallEnemy(int x,int speed, int spawnTime, BufferedImage image, ArrayList<GameObject> gameObjects) {
        this.setX(x);
        this.setY(-100);
        this.setSpeed(speed);
        this.setImage(image);
        this.setHitBox(new Rectangle(x, this.getY(), this.getImage().getWidth(), this.getImage().getHeight()));
        this.setGameObjects(gameObjects);
        this.spawnTime = spawnTime;
    }
}