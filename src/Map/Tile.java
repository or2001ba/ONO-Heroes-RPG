package Map;

import Entities.*;
import Entities.Enemies.Enemy;
import Entities.Players.Player;

import java.util.ArrayList;

public class Tile {
    private ArrayList<Entity> entities;

    public Tile(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public Tile() {
        entities = new ArrayList<>();
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public Boolean thereIsEnemy()
    {
        for (Entity e:entities)
            if(e instanceof Enemy)
                return true;
        return false;
    }

    public Boolean thereIsPlayer()
    {
        for (Entity e:entities)
            if(e instanceof Player)
                return true;
        return false;
    }

    public Boolean isEmpty()
    {
        return entities.size() == 0;
    }

    public Boolean removeEntity(Entity entity)
    {
        return entities.remove(entity);
    }

    public Boolean thereIsEntity(Entity entity)
    {
        return entities.contains(entity);
    }

    public void addEntity(Entity entity)
    {
        this.entities.add(entity);
//        if(this.entities.size()>1)
//            for(Entity e:entities)
//                System.out.println(e.className);
    }
}


