package enemies;

import managers.EnemyManager;

public class Alien01 extends Enemy{
    public Alien01(float x, float y, int ID, EnemyManager enemyManager) {
        super(x, y, ID, 0, enemyManager);
    }
}
