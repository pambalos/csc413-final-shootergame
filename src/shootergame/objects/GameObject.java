package shootergame.objects;

import static shootergame.helpers.GameConstants.SCREEN_HEIGHT;
import static shootergame.helpers.GameConstants.SCREEN_WIDTH;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class GameObject {

    private int x, y, speed;
    private BufferedImage image;

    public void checkBorder() {
        if (this.getX() < 0) {
            this.setX(0);
        }
        if (this.getX() >= SCREEN_WIDTH - 60) {
            this.setX(SCREEN_WIDTH - 60);
        }
        if (this.getY() < 10) {
            this.setY(10);
        }
        if (this.getY() >= SCREEN_HEIGHT - 30) {
            this.setY(SCREEN_HEIGHT - 30);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(this.getX(), this.getY());
        //rotation.rotate(Math.toRadians(angle), this.objectImage.getWidth() / 2.0, this.objectImage.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.getImage(), rotation, null);
    }

    /**
     * default do nothing update allows for individual unique behavior
     */
    public void update() {
        this.setY(this.getY() + speed);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
