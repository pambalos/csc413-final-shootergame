package shootergame.objects.obstacles;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class UnbreakableMeteor extends Meteor {

    public UnbreakableMeteor(int x, BufferedImage image, int spawnTime) {
        this.setX(x);
        this.setY(-100);
        this.setImage(image);
        this.setHitBox(new Rectangle(this.getX(), this.getY(), image.getWidth(), image.getHeight()));
        this.setSpawnTime(spawnTime);
    }

}
