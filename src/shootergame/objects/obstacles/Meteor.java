package shootergame.objects.obstacles;

import shootergame.helpers.ResourceLoader;
import shootergame.objects.Collidable;
import shootergame.objects.obstacles.BreakableMeteor;
import shootergame.objects.obstacles.UnbreakableMeteor;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Meteor extends Collidable {
    private int vx, vy, angle, speed; //randomize angle to between 20 & 150

    @Override
    public void update() {

    }

    public void setAngle(int angle) {
        this.angle = angle;

    }
}
