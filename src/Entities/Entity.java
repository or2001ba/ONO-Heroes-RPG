package Entities;

import java.awt.image.BufferedImage;

public class Entity {
    protected String name;
    protected int hp;
    protected int strength;
    protected int speed;
    protected int level;
    public String className;
    protected BufferedImage img;
    protected EntitySprite entitySprite;

    public Entity(String name, int hp, int strength, int speed, int level, String className, EntitySprite entitySprite) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.speed = speed;
        this.level = level;
        this.className = className;
        this.entitySprite = entitySprite;
    }

    public EntitySprite getEntitySprite() {
        return entitySprite;
    }

    public void setEntitySprite(EntitySprite entitySprite) {
        this.entitySprite = entitySprite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
}
