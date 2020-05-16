package shootergame.objects.obstacles;

import java.awt.Rectangle;

public class BreakableMeteor extends Obstacle {
    int health;

    public BreakableMeteor(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.health = 50;
        this.setHitBox(new Rectangle(x, y, this.getImage().getWidth(), this.getImage().getHeight()));
    }

    public void takeDamage(int i) {
        this.health -= i;
    }

    public int getHealth() {
        return health;
    }
}
