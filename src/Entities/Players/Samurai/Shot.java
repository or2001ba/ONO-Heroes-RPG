package Entities.Players.Samurai;

import Entities.Entity;
import Entities.EntitySprite;
import Entities.Players.Player;
import Entities.Players.PlayerSprite;
import GameStatic.Static;
import Map.TileMap;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Shot extends EntitySprite implements  Runnable{
    private double distance;
    private Image img;
    private PlayerSprite playerSprite;
    private boolean visible; // sets weather THIS Shot is visible or not
    private Timer time;


    public Shot(int startX, int startY, int dirX, Player player, int dirY, PlayerSprite playerSprite, TileMap map) throws InterruptedException {
        ImageIcon newShot;
        this.dirX = dirX;
        if (this.dirX == 1)
            newShot = new ImageIcon("src\\Entities\\Players\\" + player.getClassName() + "\\Images\\shot_right.png");
        else
            newShot = new ImageIcon("src\\Entities\\Players\\" + player.getClassName() + "\\Images\\shot_left.png");
        img = newShot.getImage();
        img = img.getScaledInstance(Static.shotWidth, Static.shotHeight, java.awt.Image.SCALE_SMOOTH);
        posX = startX;
        posY = startY;
        distance=Static.backgroundDesertWidth/4;
        visible = true;
        this.dirY=dirY;
        this.playerSprite =playerSprite;
        //System.out.print(dirY);
        System.out.print(playerSprite.getPosY());
        this.entity = new Entity("Samurai Shot",100,100,1,1,"Shot",this);
        this.map = map;
        this.map.setEntityPosition(this.entity,posY,posX);

        Thread threadR = new Thread(this);

        threadR.start();
    }

    public void run() {
        for (int i = 0; i < distance; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                super.move();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public boolean getVisible() {
        return visible;
    }


    public Image getImage() {
        return img;
    }

//    // Just like the move class in PlayerSprite class...
//    public void move() {
//        if (dirX == 1) {
//            posX = posX + 2;
//            posY +=dirY;
//            distance +=2;
//            if (distance > Static.calculateProportionHeight(500) || posY >Static.calculateProportionHeight(720) || posY < playerSprite.getPosY()-Static.calculateProportionHeight(30))
//                visible = false;
//
//        } else {
//            posX = posX - 2;
//            posY +=dirY;
//            distance +=2;
//            if (distance > Static.calculateProportionHeight(500)  || posY >Static.calculateProportionHeight(720) || posY < playerSprite.getPosY()-Static.calculateProportionHeight(30))
//                visible = false;
//        }
//    }



}