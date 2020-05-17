package shootergame.objects;

import static shootergame.helpers.GameConstants.SCREEN_HEIGHT;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Laser extends Collidable {

    public Laser(int x, int y, BufferedImage laserImage, Ship shotFrom, int speed) {
        this.setSpeed(speed);
        this.setX(x);
        this.setY(y);
        this.setImage(laserImage);
        this.setHitBox(new Rectangle(x, y, this.getImage().getWidth(), this.getImage().getHeight()));
        this.owner = shotFrom;
        this.beingHandled = false;
    }

    public void move() {
        this.setY(this.getY() + this.getSpeed());
        this.getHitBox().setLocation(this.getX(), this.getY());
    }

    public boolean isOutOfBounds() {
        return (
                this.getY() < -100 || this.getY() > SCREEN_HEIGHT + this.getImage().getHeight()
                );
    }

    @Override
    public void checkBorder() {
        //do nothing - let it fall off the map
    }

    public void update() {
        move();
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(this.getX(), this.getY());
        //rotation.rotate(Math.toRadians(angle), this.laserImage.getWidth() / 2.0, this.laserImage.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.getImage(), rotation, null);

    }
}
