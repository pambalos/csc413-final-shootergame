package shootergame.objects.obstacles;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BreakableMeteor extends Meteor {
    int health;

    public BreakableMeteor(int x, BufferedImage image) {
        this.setX(x);
        this.setY(-100);
        this.health = 50;
        this.setImage(image);
        this.setHitBox(new Rectangle(this.getX(), this.getY(), image.getWidth(), image.getHeight()));
    }

    public void takeDamage(int i) {
        this.health -= i;
    }

    public int getHealth() {
        return health;
    }
}
