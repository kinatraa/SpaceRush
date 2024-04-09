package managers;

import enemies.*;
import objects.Ship;
import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static main.GameStates.GAME_OVER;
import static main.GameStates.SetGameState;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImgs;
    private CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<>();
    private Random rand = new Random();
    private int lines = 1;
    private long nowTime, lastTimeUpdate;
    private int amountOfEnemies = 0, delayMove = 3000;
    private int maxHealth = 5;

    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs();
    }

    private void loadEnemyImgs() {
        enemyImgs = new BufferedImage[7];
        BufferedImage sprites = null;
        try {
            sprites = ImageIO.read(new File("src/images/enemies.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 7; i++) {
            enemyImgs[i] = sprites.getSubimage(192 * i, 0, 192, 192);
        }
    }

    public void update() {
//        System.out.println(amountOfEnemies);
        nowTime = System.currentTimeMillis();
        if (nowTime - lastTimeUpdate >= delayMove) {
            for (Enemy e : enemies) {
                if (e.isAlive()) {
                    updateEnemyMove(e);
                    if (playing.checkCollision(e)) {
                        SetGameState(GAME_OVER);
                        return;
                    }
                    updateHitbox(e);
                }
            }
            lastTimeUpdate = nowTime;
        }
    }

    private void updateHitbox(Enemy e) {
        e.setBounds();
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                drawEnemy(e, g);
            }
        }
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()], (int) e.getX(), (int) e.getY(), 64, 64, null);
    }

    private void updateEnemyMove(Enemy e) {
        e.move();
        if (e.getY() >= 700){
            e.kill();
            decreaseAmountOfEnemies();
        }
    }

    public void spawnEnemy() {
        amountOfEnemies = 7 * lines;
        int x = 25;
        int y = 10;
        int xOffset = 80;
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < 7; j++) {
                addEnemy(rand.nextInt(7), x + j * xOffset, y);
            }
            y -= 70;
        }
    }

    public void addEnemy(int enemyType, int x, int y) {
        switch (enemyType) {
            case 0:
                enemies.add(new Alien01(x, y, this));
                break;
            case 1:
                enemies.add(new Alien02(x, y, this));
                break;
            case 2:
                enemies.add(new Alien03(x, y, this));
                break;
            case 3:
                enemies.add(new Alien04(x, y, this));
                break;
            case 4:
                enemies.add(new Alien05(x, y, this));
                break;
            case 5:
                enemies.add(new Alien06(x, y, this));
                break;
            case 6:
                enemies.add(new Alien07(x, y, this));
                break;
        }
    }

    public CopyOnWriteArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void reward() {
        playing.reward();
    }

    public void setLastTimeUpdate(long lastTimeUpdate) {
        this.lastTimeUpdate = lastTimeUpdate;
    }

    public void increaseLines() {
        lines++;
    }

    public int getAmountOfEnemies() {
        return amountOfEnemies;
    }

    public void decreaseAmountOfEnemies() {
        amountOfEnemies--;
    }

    public void decreaseDelayMove(int descDelay){
        delayMove -= descDelay;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void increaseMaxHealth(int incHealth) {
        maxHealth += incHealth;
    }
}
