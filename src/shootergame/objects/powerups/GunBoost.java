package shootergame.objects.powerups;

import shootergame.objects.Player;

public class GunBoost extends PowerUp {

    public GunBoost(int x, int y) {
        super(x, y, "gunboost");
    }

    @Override
    public void powerUpTank(Player player) {
        player.decreaseLoadTime();
    }
}
