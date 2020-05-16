package shootergame.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Laser extends Collidable {
    int speed;
    BufferedImage laserImage;

    public Laser(int x, int y, BufferedImage laserImage, Ship shotFrom, int speed) {
        this.speed = speed;
        this.setX(x);
        this.setY(y);
        this.laserImage = laserImage;
        this.setHitBox(new Rectangle(x, y, this.laserImage.getWidth(), this.laserImage.getHeight()));
        this.owner = shotFrom;
        this.beingHandled = false;
    }

    public void moveForwards() {
        this.setY(this.getY() - speed);
        this.getHitBox().setLocation(this.getX(), this.getY());
    }

    @Override
    public void checkBorder() {
        //do nothing - let it fall off the map
    }

    public void update() {
        moveForwards();
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(this.getX(), this.getY());
        //rotation.rotate(Math.toRadians(angle), this.laserImage.getWidth() / 2.0, this.laserImage.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.laserImage, rotation, null);

    }
}
