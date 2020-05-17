package shootergame.objects.obstacles;

import java.awt.image.BufferedImage;

public class BreakableMeteor extends Meteor {
    private int health;

    public BreakableMeteor(int x, BufferedImage image, int spawnTime, int sideSpeed) {
        super(x, image, spawnTime, sideSpeed);
        this.health = 50;
    }

    public void takeDamage(int i) {
        this.health -= i;
    }

    public int getHealth() {
        return health;
    }
}
