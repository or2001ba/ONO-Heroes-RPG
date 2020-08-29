package GameStatic;

import java.awt.*;

public class Static {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // the screen height
    public static int screenHeight = screenSize.height;

    // the screen width
    public static int screenWidth = screenSize.width;

    public static double originalScreenHeight = 1080;
    public static double originalScreenWidth = 1920;

    public static int playerFirstPosX =calculateProportionWidth( 150);
    public static int playerFirstPosY = calculateProportionHeight(650);
    public static int enemyFirstPosX =calculateProportionWidth( 200);
    public static int enemyFirstPosY = calculateProportionHeight(650);



    public static int gamePanelWidth = calculateProportionWidth(1200);
    public static int gamePanelHeight = calculateProportionHeight(1000);

    public static int backgroundDesertWidth = calculateProportionWidth(1200);
    public static int backgroundDesertHeight = calculateProportionHeight(1000);

    public static int shotWidth = calculateProportionWidth(50);
    public static int shotHeight = calculateProportionHeight(50);

    //stand sprite images width and height
    public static int playerSpriteWidth = calculateProportionWidth(140);
    public static int playerSpriteHeight = calculateProportionWidth(100);

    public static int zombieSpriteWidth = calculateProportionWidth(140);
    public static int zombieSpriteHeight = calculateProportionWidth(100);

    //move sprite images width and height
    public static int playerSpriteMoveWidth = calculateProportionWidth(170);
    public static int playerSpriteMoveHeight = calculateProportionHeight(100);

    public static int zombieSpriteMoveWidth = calculateProportionWidth(170);
    public static int zombieSpriteMoveHeight = calculateProportionHeight(100);

    //calculates the proportion according to the screen size
    //used in every graphic drawn in the game to fit all screen sizes
    public static int calculateProportionWidth(int originalSize)
    {
        return (int) (screenWidth*(originalSize/originalScreenWidth));
    }

    public static int calculateProportionHeight(int originalSize)
    {
        return (int) (screenHeight*(originalSize/originalScreenHeight));
    }


}

