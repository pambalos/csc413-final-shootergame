package shootergame.objects.powerups;

import shootergame.objects.Player;

public class MovementBoost extends PowerUp {

    public MovementBoost(int x, int y) {
        super(x, y, "mspeed");
    }

    @Override
    public void powerUpTank(Player player) {
        player.increaseMovementSpeed();
    }
}
