package entity;

import main.GamePanel;
import main.KeyHanlder;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    KeyHanlder keyHanlder;

    BufferedImage[] rightAnimation = new BufferedImage[7];
    BufferedImage[] leftAnimation = new BufferedImage[7];

    public int indexImage = 0;

    public final int screenX, screenY;
    boolean moving = false;

    public int nbKeys = 0;

    public Player(GamePanel gp, KeyHanlder keyHanlder){
        this.gp = gp;
        this.keyHanlder = keyHanlder;

        hitbox = new Rectangle(10, 20, 28, 28);

        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;

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
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        idle = setup("idle");
        rightAnimation[0] = setup("walk_right_00");
        rightAnimation[1] = setup("walk_right_01");
        rightAnimation[2] = setup("walk_right_02");
        rightAnimation[3] = setup("walk_right_03");
        rightAnimation[4] = setup("walk_right_04");
        rightAnimation[5] = setup("walk_right_05");
        rightAnimation[6] = setup("walk_right_06");
        leftAnimation[0] = setup("walk_left_00");
        leftAnimation[1] = setup("walk_left_01");
        leftAnimation[2] = setup("walk_left_02");
        leftAnimation[3] = setup("walk_left_03");
        leftAnimation[4] = setup("walk_left_04");
        leftAnimation[5] = setup("walk_left_05");
        leftAnimation[6] = setup("walk_left_06");
    }

    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try{
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/" + imageName + ".png")));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
            return scaledImage;
        }catch (IOException e){
            e.printStackTrace();
        }
        return scaledImage;
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
            String objectName = gp.objs[index].name;

            switch (objectName) {
                case "Key" -> {
                    nbKeys++;
                    gp.objs[index] = null;
                    gp.playSFX(1);
                    gp.ui.showMessage("You got a KEY!!!");
                }
                case "Door" -> {
                    if (nbKeys > 0) {
                        gp.objs[index] = null;
                        nbKeys--;
                        gp.ui.showMessage("You opened a DOOR!!!");
                    } else {
                        gp.ui.showMessage("You need a key to open a door");
                    }
                }
                case "Boots" -> {
                    speed += 2;
                    gp.objs[index] = null;
                    gp.ui.showMessage("You equipped RUNNING BOOTS!!!");
                }
                case "Chest" -> gp.ui.gameFinished = true;

                //gp.stopMusic();
                //gp.playSFX(3); //The sound of the end
            }
        }
    }

    public void draw(Graphics2D graphics){
//        graphics.setColor(Color.white);
//        graphics.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
        BufferedImage image = null;
        indexImage++;

        switch (direction) {
            case "up" -> {
                if(moving && !collisionOn){
                    if (spriteNum == 1)
                        image = up1;
                    else if (spriteNum == 2)
                        image = up2;
                }
                else{
                    image = idle;
                }
            }
            case "down" -> {
                if(moving && !collisionOn){
                    if (spriteNum == 1)
                        image = down1;
                    else if (spriteNum == 2)
                        image = down2;
                }
                else{
                    image = idle;
                }
            }
            case "left" -> {
                if(indexImage >= leftAnimation.length){
                    indexImage = 0;
                }
                if(moving && !collisionOn){
                    image = leftAnimation[indexImage];
                }
                else{
                    image = idle;
                }
            }
            case "right" -> {
                if (indexImage >= rightAnimation.length) {
                    indexImage = 0;
                }
                if (moving && !collisionOn) {
                    image = rightAnimation[indexImage];
                } else {
                    image = idle;
                }
            }
        }
        graphics.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
