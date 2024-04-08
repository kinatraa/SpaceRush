package scenes;

import main.Game;

import java.awt.*;

public class GameOver extends GameScene implements SceneMethods{
    private Game game;
    public GameOver(Game game){
        super(game);
        this.game = game;
    }
    @Override
    public void render(Graphics g) {
        g.setFont(new Font("LucidaSans", Font.BOLD, 60));
        g.setColor(Color.RED);
        g.drawString("GAME OVER!", 100, 300);
    }

    @Override
    public void mouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {

    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
