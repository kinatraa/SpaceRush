package enemies;

import managers.EnemyManager;

public class Alien02 extends Enemy{
    public Alien02(float x, float y, int ID, EnemyManager enemyManager) {
        super(x, y, ID, 1, enemyManager);
    }
}
