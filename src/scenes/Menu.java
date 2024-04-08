package scenes;

import main.Game;
import ui.MyButton;

import java.awt.*;

import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {
    private MyButton bPlaying, bQuit;

    public Menu(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 200;
        int h = w / 3;
        int x = 600 / 2 - w / 2;
        int y = 200;
        int yOffset = 125;
        bPlaying = new MyButton("Play", x, y, w, h);
        bQuit = new MyButton("Quit", x, y + yOffset, w, h);
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bQuit.draw(g);
    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.resetBooleans();
            getGame().getPlaying().setLastTimeUpdate(System.currentTimeMillis());
            getGame().getPlaying().getProjectileManager().setLastFireTime(System.currentTimeMillis());
            SetGameState(PLAYING);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.resetBooleans();
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bQuit.setMouseOver(false);
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMouseOver(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        bPlaying.setMousePressed(false);
        bQuit.setMousePressed(false);
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void resetButtons() {
        bPlaying.resetBooleans();
        bQuit.resetBooleans();
    }
}
