package managers;

import enemies.Enemy;
import objects.Ship;
import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShipManager {
    private Playing playing;
    private Ship myShip;
    private Rectangle shipHitbox;
    public ShipManager(Playing playing){
        this.playing = playing;
        spawnShip();
    }

    private void spawnShip() {
        myShip = new Ship(600 / 2 - 32 + 8, 650);
    }

    public void update(){

    }

    public void draw(Graphics g){
        if(myShip.isAlive()){
//            g.setColor(Color.WHITE);
//            g.drawRect((int) myShip.getX(), (int) myShip.getY(), 48, 48);
            g.drawImage(myShip.getShipImg(), (int) myShip.getX(), (int) myShip.getY(), 48, 48, null);
        }
    }

    public Ship getMyShip() {
        return myShip;
    }

    public boolean checkCollision(Enemy e) {
//        if(e.getX() + 8 < myShip.getX() + 48 && e.getX() + 8 + 48 > myShip.getX() &&
//        e.getY() < myShip.getY() + 48 && e.getY() + 8 + 48 > myShip.getY()) return true;
        if(e.getY() + 8 + 48 >= myShip.getY()){
            if((e.getX() + 8 < myShip.getX() && e.getX() + 8 + 48 > myShip.getX()) || (e.getX() + 8 < myShip.getX() + 48 && e.getX() + 8 + 48 > myShip.getX() + 48)){
                return true;
            }
        }
        return false;
    }
}
