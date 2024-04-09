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
    private int exp = 0, lvl = 1, waveNum = 0, maxExp = 10;

    public Playing(Game game) {
        super(game);
//        initButtons();
        enemyManager = new EnemyManager(this);
        shipManager = new ShipManager(this);
        projectileManager = new ProjectileManager(this);
    }

    private void initButtons() {
        bMenu = new MyButton("", 600 - 70, 700 - 70, 50, 50);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    private void drawInfoPlayer(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Ship Level: " + lvl, 20, 20);
        g.drawString("Exp: " + exp + " / " + maxExp, 130, 20);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600, 700);
    }

    public void update() {
        if (enemyManager.getAmountOfEnemies() <= 0) {
            waveNum++;
            enemyManager.increaseMaxHealth(1);
            enemyManager.spawnEnemy();
        }
        enemyManager.update();
        shipManager.update();
        projectileManager.update();
        if (exp >= maxExp) {
            exp = 0;
            maxExp += 2;
            ++lvl;
            shipManager.getMyShip().increaseDmg(1);
            projectileManager.increaseBulletSpeed(1);
        }
        if(waveNum >= 2) {
            waveNum = 0;
            enemyManager.increaseLines();
            enemyManager.decreaseDelayMove(200);
        }
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        drawInfoPlayer(g);
        enemyManager.draw(g);
        shipManager.draw(g);
        projectileManager.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
//        if (bMenu.getBounds().contains(x, y)) {
//            bMenu.resetBooleans();
//            SetGameState(MENU);
//        }
    }

    @Override
    public void mouseMoved(int x, int y) {
//        bMenu.setMouseOver(false);
//        if (bMenu.getBounds().contains(x, y)) {
//            bMenu.setMouseOver(true);
//        }
    }

    @Override
    public void mousePressed(int x, int y) {
//        bMenu.setMousePressed(false);
//        if (bMenu.getBounds().contains(x, y)) {
//            bMenu.setMousePressed(true);
//        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }


    @Override
    public void mouseDragged(int x, int y) {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                // handle up
                break;
            case KeyEvent.VK_DOWN:
                // handle down
                break;
            case KeyEvent.VK_LEFT:
//                System.out.println("left");
                shipManager.getMyShip().move(-20f);
                break;
            case KeyEvent.VK_RIGHT:
//                System.out.println("right");
                shipManager.getMyShip().move(20f);
                break;
            case KeyEvent.VK_ESCAPE:
                SetGameState(MENU);
                break;
        }
    }

    private void resetButtons() {
//        bMenu.resetBooleans();
    }

    public void reward() {
        ++exp;
    }

    public ShipManager getShipManager() {
        return shipManager;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public boolean checkCollision(Enemy e) {
        if (shipManager.checkCollision(e)) return true;
        return false;
    }

    public ProjectileManager getProjectileManager() {
        return projectileManager;
    }
}
