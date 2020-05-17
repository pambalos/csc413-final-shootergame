package shootergame.objects.powerups;

import shootergame.objects.Player;

public class Life extends PowerUp {

    public Life(int x, int y) {
        super(x, y, "life");
    }

    @Override
    public void powerUpPlayer(Player player) {
        player.addLife();
    }
}
