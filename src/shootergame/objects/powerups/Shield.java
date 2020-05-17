package shootergame.objects.powerups;

import shootergame.objects.Player;

public class Shield extends PowerUp {

    public Shield(int x, int y) {
        super(x, y, "shield");
    }

    @Override
    public void powerUpPlayer(Player player) {
        player.resetHealth();
    }
}
