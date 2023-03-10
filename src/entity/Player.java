package entity;

import main.GamePanel;
import main.KeyHanlder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHanlder keyHanlder;

    public Player(GamePanel gamePanel, KeyHanlder keyHanlder){
        this.gamePanel = gamePanel;
        this.keyHanlder = keyHanlder;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
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
        if(keyHanlder.upPressed == true){
            direction = "up";
            y -= speed;
            spriteCounter++;
        }
        if(keyHanlder.downPressed == true){
            direction = "down";
            y += speed;
            spriteCounter++;
        }
        if(keyHanlder.rightPressed == true){
            direction = "right";
            x += speed;
            spriteCounter++;
        }
        if(keyHanlder.leftPressed == true){
            direction = "left";
            x -= speed;
            spriteCounter++;
        }
        if(spriteCounter > 10){
            if(spriteNum == 1)
                spriteNum = 2;
            else
                spriteNum = 1;
            spriteCounter = 0;
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
        graphics.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
