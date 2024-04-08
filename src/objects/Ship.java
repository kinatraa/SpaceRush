package objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ship {
    private float x, y;
    private int dmg, tier;
    private boolean live;
    private BufferedImage shipImg;
    private Rectangle shipHitbox;
    public Ship(int x, int y){
        this.x = x;
        this.y = y;
        tier = 1;
        dmg = 1;
        live = true;
        shipHitbox = new Rectangle(x, y, 48, 48);
        loadShipImg();
    }

    private void loadShipImg() {
        try {
            shipImg = ImageIO.read(new File("src/images/ship.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void move(float xSpeed){
        x += xSpeed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getTier() {
        return tier;
    }

    public int getDmg() {
        return dmg;
    }

    public boolean isAlive(){
        return live;
    }

    public BufferedImage getShipImg() {
        return shipImg;
    }

    public Rectangle getShipHitbox() {
        return shipHitbox;
    }
}
