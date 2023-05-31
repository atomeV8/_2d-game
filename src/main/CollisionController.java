package main;

import entity.Entity;

public class CollisionController {
    private final GamePanel gp;

    public CollisionController(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.hitbox.x;
        int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
        int entityTopWorldY = entity.worldY + entity.hitbox.y;
        int entityBottomWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;


        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
            }
            default -> {
                tileNum1 = 0;
                tileNum2 = 0;
            }
        }
        if(gp.tileManager.tilesTypes[tileNum1].collision || gp.tileManager.tilesTypes[tileNum2].collision){
            entity.collisionOn = true;
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;

        for(int i = 0; i < gp.objs.length; i++){
           if(gp.objs[i] != null){
               entity.hitbox.x = entity.worldX + entity.hitbox.x;
               entity.hitbox.y = entity.worldY + entity.hitbox.y;

               gp.objs[i].hitbox.x = gp.objs[i].worldX + gp.objs[i].hitbox.x;
               gp.objs[i].hitbox.y = gp.objs[i].worldY + gp.objs[i].hitbox.y;

               switch (entity.direction) {
                   case "up" -> {
                       entity.hitbox.y -= entity.speed;
                       if (entity.hitbox.intersects(gp.objs[i].hitbox)) {
                           if(gp.objs[i].collision){
                               entity.collisionOn = true;
                           }
                           if(player){
                               index = i;
                           }
                       }
                   }
                   case "down" -> {
                       entity.hitbox.y += entity.speed;
                       if (entity.hitbox.intersects(gp.objs[i].hitbox)) {
                           if(gp.objs[i].collision){
                               entity.collisionOn = true;
                           }
                           if(player){
                               index = i;
                           }
                       }
                   }
                   case "left" -> {
                       entity.hitbox.x -= entity.speed;
                       if (entity.hitbox.intersects(gp.objs[i].hitbox)) {
                           if(gp.objs[i].collision){
                               entity.collisionOn = true;
                           }
                           if(player){
                               index = i;
                           }
                       }
                   }
                   case "right" -> {
                       entity.hitbox.x += entity.speed;
                       if (entity.hitbox.intersects(gp.objs[i].hitbox)) {
                           if(gp.objs[i].collision){
                               entity.collisionOn = true;
                           }
                           if(player){
                               index = i;
                           }
                       }
                   }
               }
               entity.hitbox.x = entity.hitboxDefaultX;
               entity.hitbox.y = entity.hitboxDefaultY;
               gp.objs[i].hitbox.x = gp.objs[i].hitboxDefaultX;
               gp.objs[i].hitbox.y = gp.objs[i].hitboxDefaultY;
           }
        }

        return  index;
    }

    public int checkEntity(Entity entity, Entity[] targets){
        int index = 999;

        for(int i = 0; i < targets.length; i++){
            if(targets[i] != null){
                entity.hitbox.x = entity.worldX + entity.hitbox.x;
                entity.hitbox.y = entity.worldY + entity.hitbox.y;

                targets[i].hitbox.x = targets[i].worldX + targets[i].hitbox.x;
                targets[i].hitbox.y = targets[i].worldY + targets[i].hitbox.y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.hitbox.y -= entity.speed;
                        if (entity.hitbox.intersects(targets[i].hitbox)) {
                                entity.collisionOn = true;
                                index = i;
                        }
                    }
                    case "down" -> {
                        entity.hitbox.y += entity.speed;
                        if (entity.hitbox.intersects(targets[i].hitbox)) {
                                entity.collisionOn = true;
                                index = i;
                        }
                    }
                    case "left" -> {
                        entity.hitbox.x -= entity.speed;
                        if (entity.hitbox.intersects(targets[i].hitbox)) {
                                entity.collisionOn = true;
                                index = i;
                        }
                    }
                    case "right" -> {
                        entity.hitbox.x += entity.speed;
                        if (entity.hitbox.intersects(targets[i].hitbox)) {
                                entity.collisionOn = true;
                                index = i;
                        }
                    }
                }
                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                targets[i].hitbox.x = targets[i].hitboxDefaultX;
                targets[i].hitbox.y = targets[i].hitboxDefaultY;
            }
        }

        return  index;
    }

    public void checkPlayer(Entity entity){
        if(gp.player != null){
            entity.hitbox.x = entity.worldX + entity.hitbox.x;
            entity.hitbox.y = entity.worldY + entity.hitbox.y;

            gp.player.hitbox.x = gp.player.worldX + gp.player.hitbox.x;
            gp.player.hitbox.y = gp.player.worldY + gp.player.hitbox.y;

            switch (entity.direction) {
                case "up" -> {
                    entity.hitbox.y -= entity.speed;
                    if (entity.hitbox.intersects(gp.player.hitbox)) {
                        entity.collisionOn = true;
                    }
                }
                case "down" -> {
                    entity.hitbox.y += entity.speed;
                    if (entity.hitbox.intersects(gp.player.hitbox)) {
                        entity.collisionOn = true;
                    }
                }
                case "left" -> {
                    entity.hitbox.x -= entity.speed;
                    if (entity.hitbox.intersects(gp.player.hitbox)) {
                        entity.collisionOn = true;
                    }
                }
                case "right" -> {
                    entity.hitbox.x += entity.speed;
                    if (entity.hitbox.intersects(gp.player.hitbox)) {
                        entity.collisionOn = true;
                    }
                }
            }
            entity.hitbox.x = entity.hitboxDefaultX;
            entity.hitbox.y = entity.hitboxDefaultY;
            gp.player.hitbox.x = gp.player.hitboxDefaultX;
            gp.player.hitbox.y = gp.player.hitboxDefaultY;
        }
    }
}
