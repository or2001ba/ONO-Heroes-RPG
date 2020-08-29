package Entities.Players;

import Entities.EntitySprite;
import GameStatic.Static;
import Map.TileMap;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayerSprite extends EntitySprite {

    private int shotDirX = 1; // shot direction; starts right as the player starts standing right
    private int manaCost = 2; // this is temporary for all players for only fire attack
    private Player player;
    private Image currentPlayerImg;

    private BufferedImage[] stanceImages = new BufferedImage[6];
    private String[] stance = { "move_right", "move_left", "standing_right", "standing_left", "attack_right",
            "attack_left" };
    private BufferedImage moveRight, moveLeft, standRight, standLeft, attackRight, attackLeft;

    private boolean[] keys = new boolean[500];
    private boolean runningLeft = false, runningRight = false;


    public PlayerSprite(Player player, TileMap map) {
        this.player = player;
        loadImages();// loads the player sprite images
        //x = 100;
        posX = map.getEntityPositionX(player);
        posY = map.getEntityPositionY(player);
        currentPlayerImg = standRight;
        currentPlayerImg = currentPlayerImg.getScaledInstance(Static.playerSpriteWidth, Static.playerSpriteHeight, java.awt.Image.SCALE_SMOOTH);
        this.entity = player;

        this.map = map;

    }

    public TileMap getMap() {
        return map;
    }

    public void setMap(TileMap map) {
        this.map = map;
    }

    public void loadImages() {
        try {
            for (int i = 0; i < this.stanceImages.length; i++)
                stanceImages[i] = ImageIO
                        .read(new File("src\\Entities\\Players\\" + player.getClassName() + "\\Images\\" + stance[i] + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        moveRight = stanceImages[0];
        moveLeft = stanceImages[1];
        standRight = stanceImages[2];
        standLeft = stanceImages[3];
        attackRight = stanceImages[4];
        attackLeft = stanceImages[5];
    }



//    public double getX() {
//        return x;
//    }


    public void setCurrentPlayerImg(Image currentPlayerImg) {
        this.currentPlayerImg= currentPlayerImg;
    }

    public Image getCurrentPlayerImg() {
        return currentPlayerImg;
    }

    public Image getAttackRightImage() {
        return attackRight;
    }

    public Image getAttackLeftImage() {
        return attackLeft;
    }

    public Image getImage() {
        return currentPlayerImg;
    }

    public boolean isRunningLeft() {
        return runningLeft;
    }

    public boolean[] getKeys() {
        return keys;
    }

    public boolean isRunningRight() {
        return runningRight;
    }

    public void setRunningLeft(boolean runningLeft) {
        this.runningLeft = runningLeft;
    }

    public void setRunningRight(boolean runningRight) {
        this.runningRight = runningRight;
    }

    public Player getPlayer () {
        return player;
    }

    public void keyPressed(KeyEvent e) throws InterruptedException {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SHIFT) {
            keys[key] = true;
            if (keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D] || keys[KeyEvent.VK_A]) {
                if (player.getMana() >= manaCost) {
                    if (dirX > 0) {
                        dirX = 4;
                        runningRight = true;
                        runningLeft = false;
                    }
                    else {
                        dirX = -4;
                        runningLeft = true;
                        runningRight = false;
                    }
                    // player.setMana(player.getMana() - manaCost);
                }
            }
        } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            keys[key] = true;
            currentPlayerImg = moveLeft;
            currentPlayerImg = currentPlayerImg.getScaledInstance(Static.playerSpriteMoveWidth, Static.playerSpriteMoveHeight, java.awt.Image.SCALE_SMOOTH);
            shotDirX = -1;
            if (keys[KeyEvent.VK_SHIFT]) {
                if (player.getMana() >= manaCost) {
                    dirX = -4;
                    runningRight = false;
                    // player.setMana(player.getMana() - manaCost);
                }
            } else
                dirX = -1;
        }

        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D ) {
            keys[key] = true;
            currentPlayerImg = moveRight;
            currentPlayerImg = currentPlayerImg.getScaledInstance(Static.playerSpriteMoveWidth, Static.playerSpriteMoveHeight, java.awt.Image.SCALE_SMOOTH);
            shotDirX = 1;
            if (keys[KeyEvent.VK_SHIFT]) {
                if (player.getMana() >= manaCost) {
                    dirX = 4;
                    runningLeft = false;
                    // player.setMana(player.getMana() - manaCost);
                }
            } else
                dirX = 1;

        }

        else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            dirY = 1;

        }

        else if (key == KeyEvent.VK_SPACE) {
            player.basicAttack(); // fire shot
        }
    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT|| key == KeyEvent.VK_A) {
            keys[key] = false;
            dirX = 0;
            shotDirX = -1;
            currentPlayerImg = standLeft;
            currentPlayerImg = currentPlayerImg.getScaledInstance(Static.playerSpriteWidth, Static.playerSpriteHeight, java.awt.Image.SCALE_SMOOTH);
            runningLeft = false;
        }

        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            keys[key] = false;
            dirX = 0;
            shotDirX = 1;
            currentPlayerImg = standRight;
            currentPlayerImg = currentPlayerImg.getScaledInstance(Static.playerSpriteWidth, Static.playerSpriteHeight, java.awt.Image.SCALE_SMOOTH);
            runningRight = false;
        }

        else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            dirY = 0;
        }

        else if (key == KeyEvent.VK_SHIFT) {
            keys[key] = false;
            if (keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D] || keys[KeyEvent.VK_A]) {
                // keys[key] = false;
                if (dirX > 0) {
                    dirX = 1;
                    runningRight = false;
                } else {
                    dirX = -1;
                    runningLeft = false;
                }
            }
        }
    }
}
