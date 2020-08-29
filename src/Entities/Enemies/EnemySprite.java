package Entities.Enemies;

import Entities.EntitySprite;
import GameStatic.Static;
import Map.TileMap;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EnemySprite extends EntitySprite {

    private Enemy enemy;
    private Image currentEnemyImg;

    private BufferedImage[] stanceImages = new BufferedImage[2];
    private String[] stance = { "move_right", "move_left"};
    private BufferedImage moveRight, moveLeft;
    private boolean[] keys = new boolean[500];


    public EnemySprite(Enemy enemy, TileMap map) {
        this.enemy = enemy;
        loadImages();// loads the player sprite images
        posY = map.getEntityPositionY(enemy);
        posX = map.getEntityPositionX(enemy);
        currentEnemyImg = moveRight;
        currentEnemyImg = currentEnemyImg.getScaledInstance(Static.zombieSpriteWidth, Static.zombieSpriteHeight, java.awt.Image.SCALE_SMOOTH);
        //System.out.printf(" x: " + x + " posX: " + posX);
        this.entity = enemy;

        this.map = map;
    }


    private void loadImages() {
        try {
            for (int i = 0; i < this.stanceImages.length; i++)
                stanceImages[i] = ImageIO
                        .read(new File("src\\Entities\\Enemies\\" + enemy.getClassName() + "\\Images\\" + stance[i] + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        moveRight = stanceImages[0];
        moveLeft = stanceImages[1];
    }






    public Image getImage() {
        return currentEnemyImg;
    }


    public boolean[] getKeys() {
        return keys;
    }




    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            dirX = -1;
            keys[key] = true;
            currentEnemyImg = moveLeft;
            currentEnemyImg = currentEnemyImg.getScaledInstance(Static.zombieSpriteMoveWidth, Static.zombieSpriteMoveHeight, java.awt.Image.SCALE_SMOOTH);
        }


        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D ) {
            dirX = 1;
            keys[key] = true;
            currentEnemyImg = moveRight;
            currentEnemyImg = currentEnemyImg.getScaledInstance(Static.zombieSpriteMoveWidth, Static.zombieSpriteMoveHeight, java.awt.Image.SCALE_SMOOTH);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT|| key == KeyEvent.VK_A) {
            keys[key] = false;
            dirX = 0;
        }
        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            keys[key] = false;
            dirX = 0;
        }}}
