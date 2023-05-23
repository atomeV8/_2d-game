package entity;

import main.GamePanel;
import main.KeyHanlder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    KeyHanlder keyHanlder;

<<<<<<< Updated upstream
    public final int screenX, screenY;
    boolean moving = false;

    public int nbKeys = 0;
=======


>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
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

=======
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
>>>>>>> Stashed changes
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

        int objIndex = gp.CC.checkObject(this, true);
        collisionInteraction(objIndex);

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

    public void collisionInteraction(int index){
        if(index != 999){
<<<<<<< Updated upstream
            String objectName = gp.objs[index].name;

            switch(objectName){
                case "Key":
                    nbKeys++;
                    gp.objs[index] = null;
                    gp.playSFX(1);
                    gp.ui.showMessage("You got a KEY!!!");
                    break;
                case "Door":
                    if(nbKeys > 0){
                        gp.objs[index] = null;
                        nbKeys--;
                        gp.ui.showMessage("You opened a DOOR!!!");
                    }else{
                        gp.ui.showMessage("You need a key to open a door");
                    }
                    break;
                case "Boots":
                    speed += 2;
                    gp.objs[index] = null;
                    gp.ui.showMessage("You equipped RUNNING BOOTS!!!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    //gp.stopMusic();
                    //gp.playSFX(3); //The sound of the end
                    break;
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
=======
        }
    }
>>>>>>> Stashed changes
}
