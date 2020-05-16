package shootergame.helpers;

import shootergame.objects.obstacles.BreakableMeteor;
import shootergame.objects.Laser;
import shootergame.objects.Collidable;
import shootergame.objects.GameObject;
import shootergame.objects.Player;
import shootergame.objects.obstacles.Obstacle;
import shootergame.objects.powerups.PowerUp;

import java.util.ArrayList;

public class CollisionHandler {

    /**
     * Do a bunch of collision handling here - decided to implement all collision logic here, then objects will use functional methods to be affected
     * @param toRemove if something needs to be removed from the game, add it to this list and it will be removed... nice and easy-like
     * @param object1 gameobj 1
     * @param object2 gameobj 2
     */
    public void handleCollision(ArrayList<GameObject> toRemove, GameObject object1, GameObject object2) {
        if (object1 instanceof Laser) {
            if (!(object2 instanceof PowerUp)) toRemove.add(object1);

            if (object2 instanceof Player) {
                Player player = (Player)object2;
                player.takeDamage(1);
                System.out.println("took damage");

                if (player.getHealth() < 1) {
                    //instead of fully removing the player, i need to respawn and minus one life
                    System.out.println("player destroyed");
                    player.loseLife();
                    player.respawn();
                    if (player.getLives() < 1) {
                        //Call
                        System.out.println("Game Over");
                        toRemove.add(object2);
                    }
                }
            }

            if (object2 instanceof BreakableMeteor) {
                BreakableMeteor wall = (BreakableMeteor)object2;
                wall.takeDamage(20);
                if (wall.getHealth() < 1) {
                    toRemove.add(object2);
                }
            }
        } //End of obj1 bullet - all bullet coll's handled
        if (object1 instanceof Player) {
            Player player = (Player) object1;

            if (object2 instanceof Obstacle) { //if its a wall, bounce back a bit
                player.repel();
            }

            if (object2 instanceof Player) {
                player.repel();
                ((Player)object2).repel();
            }

            if (object2 instanceof BreakableMeteor) {
                BreakableMeteor wall = (BreakableMeteor)object2;
                wall.takeDamage(1);
                if (wall.getHealth() < 1) {
                    toRemove.add(object2);
                }
            }

            if (object2 instanceof PowerUp) {
                ((PowerUp)object2).powerUpTank(player);
                toRemove.add(object2);
            }
        }
    }

    /**
     * Checks a bunch of things to see if these two objects collided and if so, whether it should be handled
     * @param object1 first collidable
     * @param object2 second collidable
     * @return true if collision should be handled
     */
    public boolean collisionCompare(GameObject object1, GameObject object2) {
        return object1 instanceof Collidable &&
                object2 instanceof Collidable &&
                ((Collidable)object1).getHitBoxBounds().intersects(((Collidable)object2).getHitBoxBounds()) &&
                object1 != ((Collidable) object2).getOwner() &&
                object2 != ((Collidable) object1).getOwner();
    }
}
