package objects;

import java.awt.geom.Point2D;

public class Projectile {
    private Point2D.Float pos;
    private int id, dmg;
    private float speed;
    private boolean active = true;

    public Projectile(float x, float y, float speed, int dmg, int id){
        pos = new Point2D.Float(x, y);
        this.speed = speed;
        this.dmg = dmg;
        this.id = id;
    }

    public void move(){
        pos.y -= speed;
        if(pos.y < -12) active = false;
    }

    public Point2D.Float getPos() {
        return pos;
    }

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    public int getDmg() {
        return dmg;
    }

    public int getId() {
        return id;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
