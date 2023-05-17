package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects(){
        gp.objs[0] = new OBJ_Key(gp);
        gp.objs[0].worldX = 23 * gp.tileSize;
        gp.objs[0].worldY = 7 * gp.tileSize;

        gp.objs[1] = new OBJ_Key(gp);
        gp.objs[1].worldX = 23 * gp.tileSize;
        gp.objs[1].worldY = 40 * gp.tileSize;

        gp.objs[2] = new OBJ_Key(gp);
        gp.objs[2].worldX = 37 * gp.tileSize;
        gp.objs[2].worldY = 7 * gp.tileSize;

        gp.objs[3] = new OBJ_Door(gp);
        gp.objs[3].worldX = 10 * gp.tileSize;
        gp.objs[3].worldY = 11 * gp.tileSize;

        gp.objs[4] = new OBJ_Door(gp);
        gp.objs[4].worldX = 8 * gp.tileSize;
        gp.objs[4].worldY = 28 * gp.tileSize;

        gp.objs[5] = new OBJ_Door(gp);
        gp.objs[5].worldX = 12 * gp.tileSize;
        gp.objs[5].worldY = 22 * gp.tileSize;

        gp.objs[6] = new OBJ_Chest(gp);
        gp.objs[6].worldX = 10 * gp.tileSize;
        gp.objs[6].worldY = 7 * gp.tileSize;

        gp.objs[7] = new OBJ_Boots(gp);
        gp.objs[7].worldX = 37 * gp.tileSize;
        gp.objs[7].worldY = 42 * gp.tileSize;

    }
}
