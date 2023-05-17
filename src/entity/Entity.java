package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, idle;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle hitbox;
    public int hitboxDefaultX, hitboxDefaultY;
    public boolean collisionOn = false;

}
