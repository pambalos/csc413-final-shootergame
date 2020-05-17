package shootergame.objects.obstacles;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class UnbreakableMeteor extends Meteor {

    public UnbreakableMeteor(int x, BufferedImage image, int spawnTime, int sideSpeed) {
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
        this.setImage(image);
        this.setHitBox(new Rectangle(this.getX(), this.getY(), image.getWidth(), image.getHeight()));
        this.setSpawnTime(spawnTime);
    }

}
