package entity;

import main.GamePanel;

import java.util.Random;

public class Npc_OldMan extends Entity{
    public Npc_OldMan(GamePanel gp){
        super(gp);

        direction = "none";
        speed = 1;

        getImages();
        setDialogue();
        getNumberOfSprites();
    }

    public void getImages(){
        String entityType = "npc";
        idle = setup("oldman_down_1", entityType);
        upAnimation[0] = setup("oldman_up_1", entityType);
        upAnimation[1] = setup("oldman_up_2", entityType);
        downAnimation[0] = setup("oldman_down_1", entityType);
        downAnimation[1] = setup("oldman_down_2", entityType);
        leftAnimation[0] = setup("oldman_left_1", entityType);
        leftAnimation[1] = setup("oldman_left_2", entityType);
        rightAnimation[0] = setup("oldman_right_1", entityType);
        rightAnimation[1] = setup("oldman_right_2", entityType);
    }
    public void setDialogue(){
        dialogues[0] = "Howdy son!";
        dialogues[1] = "What a beautiful day innit?";
        dialogues[2] = "Sometimes I wonder if I shouldn't go \nfind that treasure myself...";
        dialogues[3] = "AYAYA AYAYA AYAYA";
    }
    @Override
    public void setAction(){

        actionLockTime++;

        if(actionLockTime == actionInterval){
            Random random = new Random();
            actionInterval = random.nextInt(100) + 50; //sets interval to a time between 50 and 149
            int i = random.nextInt(100) + 1; //pick a number from 1 to 100 (100 included)

            if(i <= 25){
                direction = "up";
            }else if(i <= 50){
                direction = "down";
            }else if(i <= 75){
                direction = "left";
            }else{
                direction = "right";
            }
            actionLockTime = 0;
        }
    }

    public void speak(){
        super.speak();
    }
}
