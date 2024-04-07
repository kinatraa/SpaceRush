package scenes;

import main.Game;
import ui.MyButton;

import java.awt.*;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class Playing extends GameScene implements SceneMethods {
    private MyButton bMenu;

    public Playing(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        bMenu = new MyButton("", 20, 20, 50, 50);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    public void update() {

    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.resetBooleans();
            SetGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        bMenu.setMousePressed(false);
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
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
        bMenu.resetBooleans();
    }
}
