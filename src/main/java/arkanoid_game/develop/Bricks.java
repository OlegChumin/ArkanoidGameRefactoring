package arkanoid_game.develop; //импортируем библиотеки

import java.awt.*;
import java.util.ArrayList;

//создаём класс bricks
public class Bricks {
    public ArrayList<ArrayList<Brick>> brickRows = new ArrayList<ArrayList<Brick>>();
    public ArrayList<Brick> bricks = new ArrayList<Brick>();

    //создаём переменную game типа ArkanoidGameStart
    private ArkanoidGameStart game;

    public Bricks(ArkanoidGameStart game) {
        this.game = game;
    }

    //создаём класс brick
    public static class Brick {
        public static int width = 15;
        public static int height = 10;
        int x = 15;
        int y = 50;
        //цвет кирпича
        Color color = Color.BLACK;

        //прочность кирпича
        int hits = 1;
        String reward_type = "";
        int reward_num = (int) Math.floor(Math.random() * hits + 1);

        public boolean hasReward() {
            if (reward_type != "" && reward_num == hits) {
                return true;
            }
            return false;
        }

        //создаём положение кирпича по высоте
        public int getTopY() {
            return y - height;
        }

        // создаем габаритный прямоугольник кирпича (блока)
        public Rectangle getBounds() {
            return new Rectangle(x, y, width, height);
        }
    }

    //задаем цвета при получении урона кирпичём ,проще говоря каждый слой прочности имеет свой цвет
    void updateHits(int brick) {
        int hits = bricks.get(brick).hits;
        switch (hits) {
            case 1:
                bricks.get(brick).color = Color.GREEN;
                break;
            case 2:
                bricks.get(brick).color = Color.YELLOW;
                break;
            case 3:
                bricks.get(brick).color = Color.LIGHT_GRAY;
                break;
            case 4:
                bricks.get(brick).color = Color.GRAY;
                break;
            case 5:
                bricks.get(brick).color = Color.DARK_GRAY;
                break;
        }
        bricks.get(brick).hits -= 1;
    }

    @SuppressWarnings("static-access")
    public void paint(Graphics2D g) {
        for (int i = 0; i < game.brick.brickRows.size(); i++) {
            for (int j = 0; j < game.brick.brickRows.get(i).size(); j++) {
                g.setColor(game.brick.brickRows.get(i).get(j).color);
                g.fillRect(game.brick.brickRows.get(i).get(j).x, game.brick.brickRows.get(i).get(j).y, game.brick.brickRows.get(i).get(j).width, game.brick.brickRows.get(i).get(j).height);
            }
        }
    }
}
