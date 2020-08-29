package Map;

import Entities.Entity;
import GameStatic.Static;

import java.util.ArrayList;

public class TileMap {
    private Tile tiles[][];
    private int xMax ;
    private int yMax ;

    public TileMap() {
        xMax = Static.backgroundDesertWidth; // bgImg = bgImg.getScaledInstance(1200, 1000, java.awt.Image.SCALE_SMOOTH);
        yMax = Static.backgroundDesertHeight;
        tiles = new Tile[yMax][xMax];
        for (int i=0; i<yMax;i++)
            for (int j=0;j<xMax;j++)
                tiles[i][j] = new Tile();

    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public int getXMax() {
        return xMax;
    }

    public void setXMax(int xMax) {
        this.xMax = xMax;
    }

    public int getYMax() {
        return yMax;
    }

    public void setYMax(int yMax) {
        this.yMax = yMax;
    }

    public void setEntityPosition(Entity entity,int yPos,int xPos)
    {
        removeEntityPosition(entity);
        tiles[yPos][xPos].addEntity(entity);
        entity.getEntitySprite().setPosX(xPos);
        entity.getEntitySprite().setPosY(yPos);
    }

    public int[] getEntityPosition(Entity entity)
    {
        for(int i=0;i<yMax;i++)
            for (int j=0;j<xMax;j++)
                if(tiles[i][j].getEntities().contains(entity))
                    return new int[]{i, j};
        return new int[]{-1,-1};
    }

    public int getEntityPositionX(Entity entity)
    {
        for(int i=0;i<yMax;i++)
            for (int j=0;j<xMax;j++)
                if(tiles[i][j].getEntities().contains(entity))
                    return j;
        return -1;
    }

    public int getEntityPositionY(Entity entity)
    {
        for(int i=0;i<yMax;i++)
            for (int j=0;j<xMax;j++)
                if(tiles[i][j].getEntities().contains(entity))
                    return i;
        return -1;
    }

    public Boolean removeEntityPosition(Entity entity)
    {
        for(int i=0;i<yMax;i++)
            for(int j=0;j<xMax;j++)
                if(tiles[i][j].removeEntity(entity)) {
                    return true;
                }
        return false;
    }

    public ArrayList<Entity> getEntitiesInPosition(int posY,int posX)
    {
        return this.tiles[posY][posX].getEntities();
    }
}
