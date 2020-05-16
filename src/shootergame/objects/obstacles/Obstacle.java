package shootergame.objects.obstacles;

import shootergame.helpers.ResourceLoader;
import shootergame.objects.Collidable;
import shootergame.objects.obstacles.BreakableMeteor;
import shootergame.objects.obstacles.UnbreakableMeteor;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Obstacle extends Collidable {

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (this instanceof UnbreakableMeteor) {
            g2.drawImage(ResourceLoader.getResourceImage("unbreakableWall"), this.getX(), this.getY(), null);
        } else if (this instanceof BreakableMeteor) {
            if (((BreakableMeteor) this).health == 50) {
                g2.drawImage(ResourceLoader.getResourceImage("breakableWall"), this.getX(), this.getY(), null);
            } else if (((BreakableMeteor) this).health < 50) {
                g2.drawImage(ResourceLoader.getResourceImage("damagedWall"), this.getX(), this.getY(), null);
            }
        }
    }

}
