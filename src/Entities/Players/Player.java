package Entities.Players;

import Entities.Entity;

import java.awt.image.BufferedImage;

public class Player extends Entity {
    private int maxHp;
    private int mana;
    private int maxMana;
    private int xp;
    private int levelUpXp; // xp needed for level up
    private int [] levelsXp = new int [10]; // levels xp array required for level up
    private int money;

    public PlayerSprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(PlayerSprite playerSprite) {
        this.playerSprite = playerSprite;
        this.setEntitySprite(playerSprite);
    }

    private PlayerSprite playerSprite;





    public Player(int hp,int maxHp, int strength, int mana,int maxMana, int speed,
                  String name, String className,int xp,int levelUpXp, int money, BufferedImage img) {
        super(name,hp,strength,speed,1,className,null);


        this.maxHp= maxHp;
        this.mana = mana;
        this.maxMana=maxMana;
        this.money=money;
        this.xp = 0;
        this.levelUpXp=51;
//		int [] levelsXp,
//		this.levelsXp=levelsXp;
        this.img = img;

    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }


    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }


    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }



    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money=money;
    }

    public int getlevelUpXp() {
        return levelUpXp;
    }

    public void setlevelUpXp(int levelUpXp) {
        this.levelUpXp=levelUpXp;
    }

    //******fix formula not working*********
    public void initLevelsXpArray () {
        levelUpXp = 10; //xp needed for level 2
        levelsXp[0]=levelUpXp;
        int i;
        for (i=1; i<=levelsXp.length-1; i++) {
            levelUpXp*=2; //1/6*(Level-1)*(Level)*(1.1*(2*Level-1)+150)-(Level-1)/2+Level/20
            levelsXp[i]=levelUpXp;
        }
    }

    public void levelManager(int xpReward){
        xp+=xpReward;
        if (xp >= levelsXp[level-1])
            playerLevelUp();
    }

    public void playerLevelUp() {
        xp = xp % levelsXp[level-1];
        level++;
        levelUpXp=levelsXp[level];

    }

    public void basicAttack() throws InterruptedException {
        return;
    }


}
