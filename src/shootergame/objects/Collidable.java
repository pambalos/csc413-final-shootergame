package shootergame.objects;

import java.awt.Rectangle;

public abstract class Collidable extends GameObject {

    Rectangle hitBox;

    boolean beingHandled;

    public GameObject getOwner() {
        return owner;
    }

    GameObject owner;

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public Rectangle getHitBoxBounds() {
        return this.hitBox.getBounds();
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }

}
