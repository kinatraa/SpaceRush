package managers;

import enemies.Enemy;
import objects.Projectile;
import objects.Ship;
import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProjectileManager {
    private Playing playing;
    private CopyOnWriteArrayList<Projectile> projectiles = new CopyOnWriteArrayList<>();
    private BufferedImage projImg;
    private int projId = 0;
    private int bulletSpeed = 5;
    private int cooldown = 300;
    private long nowTime, lastFireTime;

    public ProjectileManager(Playing playing){
        this.playing = playing;
        importImgs();
    }

    private void importImgs() {
        try {
            projImg = ImageIO.read(new File("src/images/bullet.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void newProjectile(Ship myShip){
        projectiles.add(new Projectile(myShip.getX(), myShip.getY(), bulletSpeed, myShip.getDmg(), projId++));
    }

    public void update(){
        nowTime = System.currentTimeMillis();
        if (nowTime - lastFireTime >= cooldown) {
            newProjectile(playing.getShipManager().getMyShip());
            lastFireTime = nowTime;
        }
        for (Projectile p : projectiles) {
            if (p.isActive()) {
                p.move();
                if(isProjHittingEnemy(p)) {
                    p.setActive(false);
                }
            }
        }
    }

    private boolean isProjHittingEnemy(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive()) {
                if (e.getBounds().contains(p.getPos().x + 20, p.getPos().y + 10)) {
                    p.setActive(false);
                    e.hurt(p.getDmg());
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(Graphics g){
        for(Projectile p : projectiles){
            if(p.isActive()){
//                g.setColor(Color.RED);
//                g.drawRect((int) (p.getPos().x + 20), (int) p.getPos().y, 6, 12);
                g.drawImage(projImg, (int) p.getPos().x + 20, (int) p.getPos().y, 6, 12,null);
            }
        }
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public void increaseBulletSpeed(int incSpeed) {
        this.bulletSpeed += incSpeed;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setLastFireTime(long lastFireTime) {
        this.lastFireTime = lastFireTime;
    }

}
