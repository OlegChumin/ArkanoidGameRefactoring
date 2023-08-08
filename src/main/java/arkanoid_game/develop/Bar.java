package arkanoid_game.develop;

/**
 * Этот класс описывает поведение и отображение игровой панели (платформы) в игре Arkanoid. В коде объявлены параметры
 * панели, методы для ее перемещения, рисования и обработки жизней. Также предоставляются методы для получения границ
 * панели и ее частей.
 *
 * */
import java.awt.*;

public class Bar {
	public static final int Y = 40;  // Вертикальное расположение панели (Bar)
	public static int WIDTH = 30; // Ширина панели (Bar)
	public static int HEIGHT = 10; // Высота панели (Bar)
	public static int SIDE_WIDTH = 20; // Ширина боковых частей панели (Bar)
	public static Color bar_main_color = Color.WHITE; // Основной цвет панели
	public static Color bar_side_color = Color.GRAY;  // Цвет боковых частей панели
	public int lives = 3; // Количество жизней
	public static int default_x = 197; // Начальное положение панели по горизонтали
	public int x = default_x;  // Текущее положение панели по горизонтали
	public int move_speed = 10;  // Скорость движения панели (Bar)

	private ArkanoidGameStart game;

	public Bar(ArkanoidGameStart game) {
		this.game = game;
	}


	// Метод для перемещения панели (Bar)
	void move(int direction) {
		// left & right arrows
		int width_margin = 5;
		if (direction == ListenersHandler.LEFT) {
			if (x > width_margin) {
				x -= move_speed;
			}
		}
		else if (direction == ListenersHandler.RIGHT) {
			if (x < game.getWidth()-(WIDTH+width_margin)) {
				x += move_speed;
			}
		}

	}

	// Метод для уменьшения количества жизней
	public static void looseLive(ArkanoidGameStart game) {
		game.bar.lives--;
		ArkanoidGameStart.start_game = true;
		game.ball.xa = 0;
		game.ball.ya = 0;
		game.ball.x = Ball.default_x;
		game.ball.y = Ball.default_y;
		game.bar.x = Bar.default_x;
		game.text.lives_label.setText(""+game.bar.lives);
		game.text.start_label.setText("Lives: "+game.bar.lives);
		game.speed = ArkanoidGameStart.default_speed;
		game.rewards.stopAllRewards();
	}

	// Метод для рисования панели
	public void paint(Graphics2D g) {
		// Left side
		g.setColor(bar_side_color);
		g.fillRoundRect(x-(SIDE_WIDTH-3), game.getHeight()-Y, SIDE_WIDTH, HEIGHT, 10, 10);
		// Right side
		g.setColor(bar_side_color);
		g.fillRoundRect(x+(WIDTH-3), game.getHeight()-Y, SIDE_WIDTH, HEIGHT, 10, 10);
		// Middle
		g.setColor(bar_main_color);
		g.fillRect(x, game.getHeight()-Y, WIDTH, HEIGHT);
	}

	// Метод для получения верхней координаты панели
	public int getTopY() {
		return game.getHeight() - Y - HEIGHT;
	}

	// Метод для получения границ панели
	public Rectangle getBounds() {
		return new Rectangle(x, game.getHeight()-Y, WIDTH, HEIGHT);
	}

	// Методы для получения боковых границ панели
	public Rectangle getLeftBounds() {
		return new Rectangle(x-20, game.getHeight()-Y, WIDTH-3, HEIGHT);
	}
	public Rectangle getRightBounds() {
		return new Rectangle(x+20, game.getHeight()-Y, WIDTH-3, HEIGHT);
	}
}
