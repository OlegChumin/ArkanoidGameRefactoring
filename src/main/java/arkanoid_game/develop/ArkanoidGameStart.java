package arkanoid_game.develop;

import javax.swing.*;
import java.awt.*;


// Base from: http://www.edu4java.com/en/game/game1.html
@SuppressWarnings("serial")
public class ArkanoidGameStart extends JPanel { //создаем класс и наследуемся от JPanel
    public static final int WIDTH = 410; // неизменяемая переменная, обозначающая ширину игрового поля
    public static final int HEIGHT = 450; // неизменяемая переменная, обозначающая длину игрового поля
    public static int default_speed = 6; //переменная, обозначающая скорость мяча
    public int speed = default_speed; // переменная, обозначающая скорость мяча
    public static boolean paused = false; // переменная, обозначающая на паузе ли игра
    public static boolean start_game = true; // переменная, обозначающая начала ли игра
    public static long time_counter = 0; // переменная, обозначающая таймер времени
    public static int oldballxa = 0; // ??
    public static int oldballya = 0; // ??

    public ArkanoidGameStart() { // конструктор, настраивающий игру
        setLayout(null);
        setVisible(true);
        setBackground(Color.BLACK);
    }

    Ball ball = new Ball(this); // переменная ball класса Ball
    Bar bar = new Bar(this); // переменная bar класса Bar
    Bricks brick = new Bricks(this); // переменная brick класса Bricks
    Rewards rewards = new Rewards(this); // переменная rewards класса Rewards

    ListenersHandler listeners = new ListenersHandler(this); // переменная listeners класса ListenersHandler
    Text text = new Text(this); // переменная text класса text
    Levels levels = new Levels(this); // переменная levels класса Levels

    private void move() {
        ball.move();
    }  // метод отвечающий за движение мяча

    @Override
    public void paint(Graphics g) { // метод, отвечающий за прорисовку игры
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g; // скоздаём переменную g2d класса Graphics 2D
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d); // рисуем мяч
        bar.paint(g2d); // рисуем платформу
        brick.paint(g2d); // рисуем кирпичики
        rewards.paint(g2d); // рисуем награды
        text.paint(g2d); // рисуем текст
    }

    public void gameOver() { // метод, отвечающий за конец игры
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION); // конец игры
        System.exit(ABORT); // остановка программы
    }

    public static void startGame(ArkanoidGameStart game) { // метод, отвечающий за начало игры
        if (start_game) {
            // вывод случайного направления мяча в начале игры
            int xdireccion = (int) Math.floor(Math.random() * 2 + 1);
            game.ball.ya = -1;
            if (xdireccion == 1) {
                game.ball.xa = 1;
            } else if (xdireccion == 2) {
                game.ball.xa = -1;
            }
            start_game = false; // если мяч упал вниз. то игра заканчивается
            game.text.start_label.setText(""); // текст
            game.text.level_label.setText("LEVEL " + Levels.current_level); // текст
        } else {
            if (!paused) { // если игра не в паузе игра будет в паузе
                // Pause the game
                oldballxa = game.ball.xa;
                oldballya = game.ball.ya;
                game.ball.ya = 0;
                game.ball.xa = 0;
                game.text.start_label.setText("Game Paused");
                game.text.start_label.setForeground(Color.RED);
                paused = true;
            } else {
                // выключить паузу
                game.ball.xa = oldballxa;
                game.ball.ya = oldballya;
                game.text.start_label.setText("");
                game.text.start_label.setForeground(Color.GREEN);
                paused = false;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException { // метод main
        JFrame frame = new JFrame("Arkanoid"); // создаём окно игры
        ArkanoidGameStart game = new ArkanoidGameStart();
        frame.getContentPane().add(game); // добавляем игру  в окно
        frame.setSize(WIDTH, HEIGHT); // размер окна
        frame.setVisible(true); // делаем окно видимым
        frame.setLocationRelativeTo(null); // настройка окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // настройка окна
        frame.setResizable(false); // настройка окна

        while (true) {
            System.out.println(paused);
            if (!paused && !start_game) { // если игра не в паузе и она не начата
                game.move(); // вызываем метод move
                game.repaint(); // вызываем метод repaint
                time_counter++; // начинается таймер игры
                if (time_counter % 100 == 0) { // если таймер запущен
                    // Move all the bricks down every 15 seconds
                    if ((time_counter / 100) % 15 == 0) {  // ??
                        for (int i = 0; i < game.brick.bricks.size(); i++) { // ?
                            game.brick.bricks.get(i).y += 10; // ?
                        }
                    }
                    game.rewards.paintReward(); // рисуем награды
                }
                // Manage game speed
                Thread.sleep(game.speed);
            }
        }
    }
}
