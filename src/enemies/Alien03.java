package enemies;

import managers.EnemyManager;

public class Alien03 extends Enemy{
    public Alien03(float x, float y, int ID, EnemyManager enemyManager) {
        super(x, y, ID, 2, enemyManager);
    }
}
