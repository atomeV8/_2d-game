package entity;

import main.GamePanel;
import main.KeyHanlder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHanlder keyHanlder;

    public final int screenX, screenY;
    boolean moving = false;

    public Player(GamePanel gp, KeyHanlder keyHanlder){
        this.gp = gp;
        this.keyHanlder = keyHanlder;

        hitbox = new Rectangle(10, 20, 28, 28);

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        moving = false;
        if(keyHanlder.upPressed == true){
            moving = true;
            direction = "up";
        }
        if(keyHanlder.downPressed == true){
            moving = true;
            direction = "down";
        }
        if(keyHanlder.rightPressed == true){
            moving = true;
            direction = "right";
        }
        if(keyHanlder.leftPressed == true){
            moving = true;
            direction = "left";
        }

        collisionOn = false;

        gp.CC.checkTile(this);

        if(collisionOn == false && moving){
            switch(direction){
                case"up": worldY -= speed; break;
                case"down": worldY += speed; break;
                case"left": worldX -= speed; break;
                case"right": worldX += speed; break;
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

    public void draw(Graphics2D graphics){
//        graphics.setColor(Color.white);
//        graphics.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1)
                    image = up1;
                else if(spriteNum == 2)
                    image = up2;
                break;
            case "down":
                if(spriteNum == 1)
                    image = down1;
                else if(spriteNum == 2)
                    image = down2;
                break;
            case "left":
                if(spriteNum == 1)
                    image = left1;
                else if(spriteNum == 2)
                    image = left2;
                break;
            case "right":
                if(spriteNum == 1)
                    image = right1;
                else if(spriteNum == 2)
                    image = right2;
                break;
        }
        graphics.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
