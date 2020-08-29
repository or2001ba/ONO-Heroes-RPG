package Entities.Enemies;

import Entities.Entity;

import java.awt.image.BufferedImage;

public class Enemy extends Entity {
    private EnemySprite enemySprite;


    public Enemy(int hp, int strength, int mana, int speed, int level, String className, String name,
                 BufferedImage img) {
        super(name,hp,strength,speed,level,className,null);
        this.img = img;
    }

    public EnemySprite getEnemySprite() {
        return enemySprite;
    }

    public void setEnemySprite(EnemySprite enemySprite) {
        this.enemySprite = enemySprite;
        this.setEntitySprite(enemySprite);
    }
}
