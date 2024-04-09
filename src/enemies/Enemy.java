package enemies;

import managers.EnemyManager;

import java.awt.*;

public abstract class Enemy {
    protected EnemyManager enemyManager;
    protected float x, y;
    protected Rectangle bounds;
    protected int health;
    protected int enemyType;
    protected boolean alive = true;

    public Enemy(float x, float y, int enemyType, EnemyManager enemyManager) {
        this.x = x;
        this.y = y;
        this.enemyType = enemyType;
        this.enemyManager = enemyManager;
        bounds = new Rectangle((int) x+8, (int) y+8, 48, 48);
        setStartHealth();
    }

    private void setStartHealth() {
        health = enemyManager.getMaxHealth();
    }

    public void kill() {
        alive = false;
        health = 0;
    }

    public void hurt(int dmg) {
        this.health -= dmg;
        if (health <= 0) {
            alive = false;
            enemyManager.decreaseAmountOfEnemies();
            enemyManager.reward();
        }
    }

    public void move() {
        this.y += 70;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setBounds() {
        bounds = new Rectangle((int) x+8, (int) y+8, 48, 48);
    }
}
