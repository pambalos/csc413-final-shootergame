package shootergame.objects.powerups;

import shootergame.objects.Player;

public class Health extends PowerUp {

    public Health(int x, int y) {
        super(x, y, "health");
    }

    @Override
    public void powerUpTank(Player player) {
        player.addLife();
    }
}
