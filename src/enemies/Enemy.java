package enemies;

import managers.EnemyManager;

import java.awt.*;

public abstract class Enemy {
    protected EnemyManager enemyManager;
    protected float x, y;
    protected Rectangle bounds;
    protected int health, maxHealth;
    protected int ID;
    protected int enemyType;
    protected int lastDir;
    protected boolean alive = true;
    protected int rotate = 0;

    public Enemy(float x, float y, int ID, int enemyType, EnemyManager enemyManager) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        this.enemyManager = enemyManager;
        bounds = new Rectangle((int) x+8, (int) y+8, 48, 48);
        setStartHealth();
    }

    private void setStartHealth() {
        health = 5;
        maxHealth = health;
    }

    public void kill() {
        alive = false;
        health = 0;
    }

    public void hurt(int dmg) {
        this.health -= dmg;
        if (health <= 0) {
            alive = false;
            enemyManager.reward(enemyType);
        }
    }

    public void move() {
        this.y += 70;
    }

    private void updateHitbox() {
        bounds.x = (int) x;
        bounds.y = (int) y;
    }

    public float getHealthBarFloat() {
        return health / (float) maxHealth;
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

    public int getID() {
        return ID;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getLastDir() {
        return lastDir;
    }

    public void setLastDir(int newDir) {
        this.lastDir = newDir;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setBounds() {
        bounds = new Rectangle((int) x+8, (int) y+8, 48, 48);
    }
}
