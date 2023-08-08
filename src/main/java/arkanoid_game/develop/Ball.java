package arkanoid_game.develop;

// импортируем библиотеки
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Ball {
    public static int DIAMETER = 10; // диаметр мяча
    public static int default_x = 205; // начальная координата x мяча
    public static int default_y = 350; // начальная координата y мяча
    int x = default_x; // текущая координата x мяча
    int y = default_y; // текущая координата y мяча
    int xa = 0; // приращение координаты x мяча
    int ya = 0;// приращение координаты y мяча
    int brick; // кирпич
    int ball_top_position = 0; // верхняя часть мяча
    int ball_bot_position = 0; // нижняя часть мяча
    int ball_right_position = 0; // правая часть мяча
    int ball_left_position = 0; // левая часть мяча
    int brick_top_position = 0; // верхняя часть кирпича
    int brick_bot_position = 0; // нижняя часть кирпича
    int brick_right_position = 0; // правая часть кирпича
    int brick_left_position = 0; // левая часть кирпича

    public static boolean ultraballmode = false; // режим ультра мяч
    public static int ultraball_color = 0; // цвет ультрамяча

    private ArkanoidGameStart game; // переменная game

    public Ball(ArkanoidGameStart game) { // конструктор ball
        this.game = game;
    }

    void move() {
        // Change ball's direction when touches a window border
        if (x + xa <= 0)
            xa *= -1;
        else if (x + xa >= game.getWidth() - DIAMETER)
            xa = -xa;
        else if (y + ya <= Text.menu_bar_height)
            ya = 1;
        else if (y + ya >= game.getHeight() - DIAMETER) {
            if (game.bar.lives == 0)
                game.gameOver();
            else if (game.bar.lives > 0) {
                Bar.looseLive(game);
            }
        }
        // Столкновение с Bar (битой)
        else if (collision()) {
            ya = -1;
            y = game.bar.getTopY() - DIAMETER + 10;
        }
        // Столкновение с кирпичом (блоком)
        else if (brickCollision()) {
            if (ultraballmode) {
                game.brick.bricks.remove(brick);
            } else {
                ball_top_position = y;
                ball_bot_position = y + DIAMETER;
                ball_right_position = x + DIAMETER + 14;
                ball_left_position = x;
                brick_top_position = game.brick.bricks.get(brick).y + 1;
                brick_bot_position = game.brick.bricks.get(brick).y + Bricks.Brick.height - 1;
                brick_right_position = game.brick.bricks.get(brick).x + 14;
                brick_left_position = game.brick.bricks.get(brick).x + Bricks.Brick.width;
                // Change ball's direction
                if ((ball_bot_position == brick_top_position || ball_top_position == brick_bot_position) && ball_right_position != brick_left_position && ball_left_position != brick_right_position) {
                    if (ya == 1)
                        ya = -1;
                    else if (ya == -1)
                        ya = 1;
                } else {
                    if (xa > 0)
                        xa *= -1;
                    else if (xa < 0)
                        xa *= -1;
                }
                // Удалить кирпич если требуется, или удалить 1 удар
                if (game.brick.bricks.get(brick).hits == 0) {
                    game.brick.bricks.remove(brick);
                } else {
                    game.brick.updateHits(brick);
                }
            }
            // Если все кирпичы разбиты то начать следующий новый уровень
            if (game.brick.bricks.size() == 0) {
                Levels.startNewLevel(game);
            }
        }
        // Апдейт положения мяча
        x = x + xa;
        y = y + ya;
    }

    private boolean collision() { // метод для расчета столкновений объектов
        if (game.bar.getBounds().intersects(getBounds())) {
            return true;
        } else if (game.bar.getLeftBounds().intersects(getBounds())) {
            if (xa > 0) {
                xa *= -1;
                if (x * xa < 0 && xa + 1 != 0)
                    xa++;
                if (game.speed < 10)
                    game.speed += 1;
            } else if (xa < 0) {
                if (x * xa < 0 && xa - 1 != 0)
                    xa--;
                if (game.speed > 2)
                    game.speed -= 1;
            }
            return true;
        } else if (game.bar.getRightBounds().intersects(getBounds())) {
            if (xa < 0) {
                xa *= -1;
                if (x * xa > 0 && xa - 1 != 0)
                    xa--;
                if (game.speed < 10)
                    game.speed += 1;
            } else if (xa > 0) {
                if (x * xa > 0 && xa + 1 != 0)
                    xa++;
                if (game.speed > 2)
                    game.speed -= 1;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean brickCollision() { // колизия кирпичей
        for (int i = 0; i < game.brick.bricks.size(); i++) {
            if (game.brick.bricks.get(i).getBounds().intersects(getBounds())) {
                brick = i;
                // Если в кирпиче есть награда, создать награду
                if (game.brick.bricks.get(i).hasReward()) {
                    game.rewards.createReward(game.brick.bricks.get(i).reward_type, game.brick.bricks.get(i).x - 3, game.brick.bricks.get(i).y);
                }
                return game.brick.bricks.get(i).getBounds().intersects(getBounds());
            }
        }
        return false;
    }

    public void paint(Graphics2D g) { // метод который рисует ultraBall
        g.setColor(Color.WHITE);
        if (ultraballmode) {
            if (ultraball_color == 0) {
                g.setColor(Color.RED);
                ultraball_color++;
            } else if (ultraball_color == 1) {
                g.setColor(Color.YELLOW);
                ultraball_color++;
            } else if (ultraball_color == 2) {
                g.setColor(Color.GREEN);
                ultraball_color = 0;
            }
        }
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    // Возврат границ мяча (в виде прямоугольника)
    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}
