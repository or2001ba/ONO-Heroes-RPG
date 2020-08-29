package Entities;

import GameStatic.Static;
import Map.TileMap;

public class EntitySprite {
    protected int dirX, dirY; // player movement directions
    protected int firstImagePos, secondImagePos;
    //private double x; // player position after passing the second image ending line at x=1400
    protected int posX;
    protected int posY; // player position on the screen
    protected Entity entity;

    protected TileMap map;



    public void move() throws InterruptedException {
        posX += dirX;
        //too fast for tile array , may be not
        if(posX >= 0 && posX< Static.backgroundDesertWidth - Static.playerSpriteWidth)
            map.setEntityPosition(this.entity,posY,posX);
        else {
            if(posX < 0)
                posX = 0;
            else posX = Static.backgroundDesertWidth -1 - Static.playerSpriteWidth;
        }
    }



    public int getDirX() {
        return dirX;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public TileMap getMap() {
        return map;
    }

    public void setMap(TileMap map) {
        this.map = map;
    }
}
