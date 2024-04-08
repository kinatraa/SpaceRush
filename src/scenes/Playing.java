package scenes;

import enemies.Enemy;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.ShipManager;
import objects.Ship;
import ui.MyButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class Playing extends GameScene implements SceneMethods {
    private MyButton bMenu;
    private EnemyManager enemyManager;
    private ShipManager shipManager;
    private ProjectileManager projectileManager;
    private long nowTime, lastTimeUpdate;

    public Playing(Game game) {
        super(game);
        initButtons();
        enemyManager = new EnemyManager(this);
        shipManager = new ShipManager(this);
        projectileManager = new ProjectileManager(this);
    }

    private void initButtons() {
        bMenu = new MyButton("", 600-70, 700-70, 50, 50);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600, 700);
    }

    public void update() {
        nowTime = System.currentTimeMillis();
        if (nowTime - lastTimeUpdate >= 1000) {
            enemyManager.update();
            lastTimeUpdate = nowTime;
        }
        shipManager.update();
        projectileManager.update();
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        drawButtons(g);
        enemyManager.draw(g);
        shipManager.draw(g);
        projectileManager.draw(g);
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

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                // handle up
                break;
            case KeyEvent.VK_DOWN:
                // handle down
                break;
            case KeyEvent.VK_LEFT:
//                System.out.println("left");
                shipManager.getMyShip().move(-10f);
                break;
            case KeyEvent.VK_RIGHT :
//                System.out.println("right");
                shipManager.getMyShip().move(10f);
                break;
        }
    }

    private void resetButtons() {
        bMenu.resetBooleans();
    }

    public void reward(int enemyType) {
    }

    public void setLastTimeUpdate(long lastTimeUpdate) {
        this.lastTimeUpdate = lastTimeUpdate;
    }

    public ShipManager getShipManager() {
        return shipManager;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public boolean checkCollision(Enemy e) {
        if(shipManager.checkCollision(e)) return true;
        return false;
    }

    public ProjectileManager getProjectileManager() {
        return projectileManager;
    }
}
