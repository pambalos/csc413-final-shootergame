package shootergame.objects.obstacles;

import static shootergame.helpers.GameConstants.SCREEN_WIDTH;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BreakableMeteor extends Meteor {
    private int health;

    public BreakableMeteor(int x, BufferedImage image, int spawnTime, int sideSpeed) {
        Random random = new Random();
        this.setSpeed(1);
        int dir = random.nextInt(2 - 1 + 1) + 1;
        if (dir == 1) {
            this.setSpeed(1);
        } else {
            this.setSpeed(-1);
        }
        this.setSideSpeed(sideSpeed);
        this.setY(-100);
        this.setX(x);
        this.health = 50;
        this.setImage(image);
        this.setHitBox(new Rectangle(this.getX(), this.getY(), image.getWidth(), image.getHeight()));
        this.setSpawnTime(spawnTime);
    }

    public void takeDamage(int i) {
        this.health -= i;
    }

    public int getHealth() {
        return health;
    }
}
