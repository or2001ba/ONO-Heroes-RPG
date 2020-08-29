package Entities.Players;

import Entities.Enemies.Enemy;
import Entities.Enemies.EnemySprite;
import Entities.Enemies.Zombie.Zombie;
import Entities.Players.Samurai.Samurai;
import Entities.Players.Samurai.Shot;
import GameStatic.Static;
import Map.TileMap;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameScreen extends JPanel implements ActionListener, Runnable {
    private PlayerSprite playerSprite;
    private EnemySprite enemySprite;
    private Image bgImg; // background
    private Timer time;
    private Thread jumpAnimation;
    private Player player;
    private Enemy enemy;
    private boolean stopAnimation = false;
    private double regenMana;
    private BufferedImage hpIcon = null, mpIcon= null, moneyIcon= null,runSmokeRight=null,runSmokeLeft=null;
    private int count = 0;

    private TileMap map;





    public static void startGame(JFrame frame, Player player) throws IOException {
        JFrame Gameframe = frame;

        System.out.println(Static.backgroundDesertWidth +" "+Static.backgroundDesertHeight);

        Gameframe.add(new GameScreen(player));

        Gameframe.setTitle("2-D Test Game");
        Gameframe.setSize(Static.backgroundDesertWidth, Static.backgroundDesertHeight);
        Gameframe.setResizable(false);
        Gameframe.setVisible(true);
        Gameframe.setLocationRelativeTo(null);
        Gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public GameScreen(Player player) throws IOException {
        map = new TileMap();
        this.player = player;
        this.enemy = new Zombie("Zombie");
        playerSprite = new PlayerSprite(player,map);
        this.player.setPlayerSprite(playerSprite);

        enemySprite = new EnemySprite(enemy,map);
        this.enemy.setEnemySprite(enemySprite);

        map.setEntityPosition(player,Static.playerFirstPosY,Static.playerFirstPosX);
        map.setEntityPosition(enemy,Static.enemyFirstPosY,Static.enemyFirstPosX);





        player.initLevelsXpArray();
        try {
            runSmokeLeft = ImageIO.read(new File("src/Images/runSmokeLeft.png"));
            runSmokeRight = ImageIO.read(new File("src/Images/runSmokeRight.png"));
            hpIcon = ImageIO.read(new File("src/Images/hpIcon.png"));
            mpIcon = ImageIO.read(new File("src/Images/mpIcon.png"));
            moneyIcon = ImageIO.read(new File("src/Images/moneyIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // call this when player kills an enemy
        int xpReward = 15; // should be: xpReward = (enemy.getXpRward);
        player.levelManager(xpReward);

        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon("src/Images/Background_desert.jpg");
        bgImg = i.getImage();
        bgImg = bgImg.getScaledInstance(Static.backgroundDesertWidth, Static.backgroundDesertHeight, java.awt.Image.SCALE_SMOOTH);
        time = new Timer(2, this); // sets the delay between each jumpAnimation
        time.start();


    }

    //makes the Samurai shot image move
    //remove the shot from the array when the shot reached its maximum distance
    public void samuraiShots() throws InterruptedException {
        Samurai samurai= (Samurai) (playerSprite.getPlayer());
        ArrayList<Shot> Shots = samurai.getShots();
        for (int i = 0; i < Shots.size(); i++) {

//            Shot shot = (Shot) Shots.get(i);// This is how to get a current element in an arrayList
//            if (shot.getVisible() == true) {
//                shot.move();
//            } else
//                Shots.remove(i);
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (playerSprite.getPlayer() instanceof Samurai) {
            try {
                samuraiShots();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        try {
            playerSprite.move();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        //EnemySprite.move();
        repaint();
    }

    // the main graphics drawing loop
    public void paint(Graphics g) {
        if (playerSprite.getDirY() == 1 && stopAnimation == false) {
            jumpAnimation = new Thread(this);
            jumpAnimation.start();
            stopAnimation = true;
        }

        //regenerates player mana over time
        count++;
        if (count == 100) {
            count = 0;
            if (player.getMana() < 100) {
                player.setMana(player.getMana() + 1);
            }
        }

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        //drawing the background image
        g2d.drawImage(bgImg, 0, 0, null);

        //drawing the player image
        g2d.drawImage(playerSprite.getImage(), (int) playerSprite.getPosX(), (int) playerSprite.getPosY(), null);
        g2d.drawImage(enemySprite.getImage(), (int) enemySprite.getPosX(), (int) enemySprite.getPosY(), null);


        //drawing basic attacks
        //if player is Samurai, uses the shots as a basic attack
        if(playerSprite.getPlayer() instanceof Samurai)
        {
            playerSprite.getPlayer().setPlayerSprite(playerSprite);
            ArrayList<Shot> Shots = ((Samurai) (playerSprite.getPlayer())).getShots();

            for (int i = 0; i < Shots.size(); i++) {
                Shot shot = (Shot) Shots.get(i);// This is how to get a current element in an arrayList
                g2d.drawImage(shot.getImage(), (int) shot.getPosX(), (int) shot.getPosY(), null);
            }

            g2d.drawString("Ammo left: " + ((Samurai) playerSprite.getPlayer()).getAmmo(), Static.calculateProportionWidth(800), Static.calculateProportionWidth(50));
        }

        //drawing smoke images when running right/left
        if (playerSprite.isRunningLeft()) {
            g2d.drawImage(runSmokeLeft,
                    (int) playerSprite.getPosX()+Static.calculateProportionWidth(100), (int) playerSprite.getPosY()+10,
                    Static.calculateProportionWidth(170),
                    Static.calculateProportionHeight(100),
                    null);

        }

        else if (playerSprite.isRunningRight()) {
            g2d.drawImage(runSmokeRight,
                    (int) playerSprite.getPosX()-Static.calculateProportionWidth(100), (int) playerSprite.getPosY()+10,
                    Static.calculateProportionWidth(170),
                    Static.calculateProportionHeight(100),
                    null);
        }



        // player ammo
        g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        g2d.setColor(Color.black);


        // player hp bar
        g2d.drawImage(hpIcon, Static.calculateProportionWidth(2), Static.calculateProportionWidth(27), Static.calculateProportionWidth(20), Static.calculateProportionWidth(20), null);
        g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        g2d.drawString("HP: ", Static.calculateProportionWidth(20), Static.calculateProportionWidth(45));
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRoundRect(Static.calculateProportionWidth(60), Static.calculateProportionWidth(15), Static.calculateProportionWidth(150), Static.calculateProportionWidth(40), 15, 15);
        g2d.setColor(Color.green);
        g2d.fillRoundRect(Static.calculateProportionWidth(60), Static.calculateProportionWidth(15), Static.calculateProportionWidth(150) * player.getHp() / player.getMaxHp(), Static.calculateProportionWidth(40), 20, 20);
        g2d.setColor(Color.black);
        g2d.drawString(player.getHp() + " / " + player.getMaxHp(), Static.calculateProportionWidth(70), Static.calculateProportionWidth(40));

        // player mana bar
        g2d.drawImage(mpIcon, Static.calculateProportionWidth(2), Static.calculateProportionWidth(77), Static.calculateProportionWidth(18), Static.calculateProportionWidth(18), null);
        g2d.drawString("MP:", Static.calculateProportionWidth(22), Static.calculateProportionWidth(90));
        g2d.setColor(new Color(0, 0, 205));
        g2d.fillRoundRect(Static.calculateProportionWidth(60), Static.calculateProportionWidth(65), Static.calculateProportionWidth(150), Static.calculateProportionWidth(40), 15, 15);
        g2d.setColor(new Color(65, 105, 225));
        g2d.fillRoundRect(Static.calculateProportionWidth(60), Static.calculateProportionWidth(65), Static.calculateProportionWidth(150) * player.getMana() / player.getMaxMana(), Static.calculateProportionWidth(40), 15, 15);
        g2d.setColor(Color.black);
        g2d.drawString(player.getMana() + " / " + player.getMaxMana(), Static.calculateProportionWidth(70), Static.calculateProportionWidth(90));



        // player xp bar
        g2d.drawString("LVL: " + player.getLevel(), Static.calculateProportionWidth(5), Static.calculateProportionWidth(140));
        g2d.setColor(new Color(138, 43, 226));
        g2d.fillRoundRect(Static.calculateProportionWidth(70), Static.calculateProportionWidth(115), Static.calculateProportionWidth(150), Static.calculateProportionWidth(40), 15, 15);
        g2d.setColor(new Color(153, 50, 204));
        g2d.fillRoundRect(Static.calculateProportionWidth(70), Static.calculateProportionWidth(115), player.getXp() * 2, Static.calculateProportionWidth(40), 15,15);
        g2d.setColor(Color.black);
        g2d.drawString(player.getXp() + " / " + player.getlevelUpXp(), Static.calculateProportionWidth(80), Static.calculateProportionWidth(140));

        // player money
        g2d.drawImage(moneyIcon, Static.calculateProportionWidth(60), Static.calculateProportionWidth(165), Static.calculateProportionWidth(20), Static.calculateProportionWidth(20), null);
        g2d.setColor(Color.black);
        g2d.drawString("Money:   " + player.getMoney(), Static.calculateProportionWidth(5), Static.calculateProportionWidth(180));

        // player name above the playerSprite image
        g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g2d.setColor(Color.black);
        g2d.drawString(player.getName(), (int) playerSprite.getPosX(), (int) playerSprite.getPosY() - Static.calculateProportionWidth(20));

    }

    private class AL extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            playerSprite.keyReleased(e);
            //EnemySprite.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            try {
                playerSprite.keyPressed(e);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            //EnemySprite.keyPressed(e);
        }
    }

    boolean isJumping = true;
    boolean stoppedJumping = false;

    // the jumpAnimation thread cycle
    // makes the player jump and fall back down when reached max height
    // checks if the player is walking/sprinting while jumping to not display smoke images in the air
    public void cycle() {
        boolean sprint = playerSprite.getKeys()[KeyEvent.VK_SHIFT];
        if (isJumping) {
            playerSprite.setPosY(playerSprite.getPosY() - 2);
            if (playerSprite.isRunningRight())
                playerSprite.setRunningRight(false);
            else if (playerSprite.isRunningLeft())
                playerSprite.setRunningLeft(false);
        }
        if (playerSprite.getPosY() <= Static.calculateProportionHeight(550))
            isJumping = false;
        if (!isJumping && playerSprite.getPosY() <= Static.calculateProportionHeight(650)) {
            playerSprite.setPosY(playerSprite.getPosY() + 2);
            if (playerSprite.getPosY() == Static.calculateProportionHeight(650)) {
                stoppedJumping = true;
                if (!playerSprite.isRunningRight() && sprint)
                    playerSprite.setRunningRight(true);
                else if (!playerSprite.isRunningLeft() && sprint)
                    playerSprite.setRunningLeft(true);
            }
        }
    }

    //runs the jumpAnimation thread and calls cycle() function when the player is in the air
    public void run() {
        while (!stoppedJumping) {
            cycle();
            int sleep = 10;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

        }
        stoppedJumping = false; // set to false so player can jump again after falling
        isJumping = true; // set to true so player can jump again after falling
        stopAnimation = false; // set to false so JumpAnimation can start again after the end of the loop
    }

}