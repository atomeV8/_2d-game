package entity;

import main.GamePanel;
import main.KeyHanlder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    KeyHanlder keyHanlder;




    public Player(GamePanel gp, KeyHanlder keyHanlder){
        super(gp);
        this.keyHanlder = keyHanlder;

        hitbox = new Rectangle(10, 20, 28, 28);

        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        setDefaultValues();
        getPlayerImages();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImages(){
        String entityType = "player";
        idle = setup("idle", entityType);
        upAnimation[0] = setup("walk_up_00", entityType);
        upAnimation[1] = setup("walk_up_01", entityType);
        upAnimation[2] = setup("walk_up_02", entityType);
        upAnimation[3] = setup("walk_up_03", entityType);
        upAnimation[4] = setup("walk_up_04", entityType);
        upAnimation[5] = setup("walk_up_05", entityType);
        upAnimation[6] = setup("walk_up_06", entityType);
        rightAnimation[0] = setup("walk_right_00", entityType);
        rightAnimation[1] = setup("walk_right_01", entityType);
        rightAnimation[2] = setup("walk_right_02", entityType);
        rightAnimation[3] = setup("walk_right_03", entityType);
        rightAnimation[4] = setup("walk_right_04", entityType);
        rightAnimation[5] = setup("walk_right_05", entityType);
        rightAnimation[6] = setup("walk_right_06", entityType);
        leftAnimation[0] = setup("walk_left_00", entityType);
        leftAnimation[1] = setup("walk_left_01", entityType);
        leftAnimation[2] = setup("walk_left_02", entityType);
        leftAnimation[3] = setup("walk_left_03", entityType);
        leftAnimation[4] = setup("walk_left_04", entityType);
        leftAnimation[5] = setup("walk_left_05", entityType);
        leftAnimation[6] = setup("walk_left_06", entityType);
        downAnimation[0] = setup("walk_down_00", entityType);
        downAnimation[1] = setup("walk_down_01", entityType);
        downAnimation[2] = setup("walk_down_02", entityType);
        downAnimation[3] = setup("walk_down_03", entityType);
        downAnimation[4] = setup("walk_down_04", entityType);
        downAnimation[5] = setup("walk_down_05", entityType);
        downAnimation[6] = setup("walk_down_06", entityType);
    }
    public void update(){
        moving = false;
        if(keyHanlder.upPressed){
            moving = true;
            direction = "up";
        }
        if(keyHanlder.downPressed){
            moving = true;
            direction = "down";
        }
        if(keyHanlder.rightPressed){
            moving = true;
            direction = "right";
        }
        if(keyHanlder.leftPressed){
            moving = true;
            direction = "left";
        }
        if(!keyHanlder.downPressed && !keyHanlder.upPressed && !keyHanlder.leftPressed && !keyHanlder.rightPressed){
            moving = false;
            direction = "none";
        }

        collisionOn = false;

        gp.CC.checkTile(this);

        int objIndex = gp.CC.checkObject(this, true);
        collisionInteraction(objIndex);

        if(!collisionOn && moving){
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1)
                    spriteNum = 2;
                else
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }

    public void collisionInteraction(int index){
        if(index != 999){
        }
    }
}
