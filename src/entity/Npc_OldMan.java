package entity;

import main.GamePanel;

public class Npc_OldMan extends Entity{
    public Npc_OldMan(GamePanel gp){
        super(gp);

        direction = "none";
        speed = 1;

        getImages();
    }

    public void getImages(){
        String entityType = "npc";
        upAnimation[0] = setup("oldman_up_1", entityType);
        upAnimation[1] = setup("oldman_up_2", entityType);
        downAnimation[0] = setup("oldman_down_1", entityType);
        downAnimation[1] = setup("oldman_down_2", entityType);
        leftAnimation[0] = setup("oldman_left_1", entityType);
        leftAnimation[1] = setup("oldman_left_2", entityType);
        rightAnimation[0] = setup("oldman_right_1", entityType);
        rightAnimation[1] = setup("oldman_right_2", entityType);
    }
}
