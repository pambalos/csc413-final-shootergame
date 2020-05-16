package shootergame.objects.obstacles;

import java.awt.Rectangle;

public class UnbreakableMeteor extends Obstacle {

    public UnbreakableMeteor(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.setHitBox(new Rectangle(x, y, 30, 30));
    }

}
