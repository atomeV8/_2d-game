package entity;

import main.GamePanel;
import main.KeyHanlder;

import java.awt.*;

public class Player extends Entity{
    KeyHanlder keyHanlder;




    public Player(GamePanel gp, KeyHanlder keyHanlder){
        super(gp);
        this.keyHanlder = keyHanlder;

        hitbox = new Rectangle(10, 20, 22, 22);

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
        }

        collisionOn = false;

        //Collision detection between player and tiles
        gp.CC.checkTile(this);

        //Detection of player touching an item
        int objIndex = gp.CC.checkObject(this, true);
        interactionWithItem(objIndex);

        //Collision detection between player and other entities
        int npcIndex = gp.CC.checkEntity(this, gp.npcs);
        interactionWithNPC(npcIndex);

        //If collisionOn is false and moving is true, the player can move
        if(!collisionOn && moving){
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
            spriteCounter += 11;
        }
    }

    private void interactionWithNPC(int npcIndex) {
        if(npcIndex != 999){
            if(gp.keyHanlder.interactKeyPressed){
                gp.gameState = GamePanel.GameStates.DIALOGUE;
                gp.npcs[npcIndex].speak();
            }
        }
        gp.keyHanlder.interactKeyPressed = false;
    }

    public void interactionWithItem(int itemIndex){
        if(itemIndex != 999){
        }
    }
}
