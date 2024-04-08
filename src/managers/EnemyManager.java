package managers;

import enemies.*;
import objects.Ship;
import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import static main.GameStates.GAME_OVER;
import static main.GameStates.SetGameState;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImgs;
    private CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<>();
    public EnemyManager(Playing playing){
        this.playing = playing;
        loadEnemyImgs();
        spawnEnemy();
    }

    private void loadEnemyImgs() {
        enemyImgs = new BufferedImage[7];
        BufferedImage sprites = null;
        try {
            sprites = ImageIO.read(new File("src/images/enemies.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i < 7; i++){
            enemyImgs[i] = sprites.getSubimage(192*i, 0, 192, 192);
        }
    }

    public void update(){
        for(Enemy e : enemies){
            if(e.isAlive()){
                if(playing.checkCollision(e)){
                    SetGameState(GAME_OVER);
                    return;
                }
                updateEnemyMove(e);
                updateHitbox(e);
            }
        }
    }

    private void updateHitbox(Enemy e) {
        e.setBounds();
    }

    public void draw(Graphics g){
        for(Enemy e : enemies){
            if(e.isAlive()){
                drawEnemy(e, g);
            }
        }
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()], (int)e.getX(), (int) e.getY(), 64, 64, null);
    }

    private void updateEnemyMove(Enemy e) {
        e.move();
        if(e.getY() >= 700) e.kill();
    }

    private void spawnEnemy(){
        int x = 25;
        int y = 10;
        int xOffset = 80;
        addEnemy(0, x + 0*xOffset, y);
        addEnemy(1, x + 1*xOffset, y);
        addEnemy(2, x + 2*xOffset, y);
        addEnemy(3, x + 3*xOffset, y);
        addEnemy(4, x + 4*xOffset, y);
        addEnemy(5, x + 5*xOffset, y);
        addEnemy(6, x + 6*xOffset, y);
    }

    public void addEnemy(int enemyType, int x, int y){
        switch (enemyType){
            case 0:
                enemies.add(new Alien01(x, y, 0, this));
                break;
            case 1:
                enemies.add(new Alien02(x, y, 0, this));
                break;
            case 2:
                enemies.add(new Alien03(x, y, 0, this));
                break;
            case 3:
                enemies.add(new Alien04(x, y, 0, this));
                break;
            case 4:
                enemies.add(new Alien05(x, y, 0, this));
                break;
            case 5:
                enemies.add(new Alien06(x, y, 0, this));
                break;
            case 6:
                enemies.add(new Alien07(x, y, 0, this));
                break;
        }
    }

    public CopyOnWriteArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void reward(int enemyType) {
        playing.reward(enemyType);
    }
}
