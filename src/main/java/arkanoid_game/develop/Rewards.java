package arkanoid_game.develop;

import java.awt.*; // импортируем графическую библиотеку
import java.util.ArrayList; // бибилиотека для применения динамического массива


public class Rewards {
	// Статическая переменная для времени действия ультрагранатов (ультраболов)
    public static int ultraball_time = 10;

	// Начальные значения для различных видов наград
    public int default_reward_ultraball_num = 3;
    public int default_reward_bigball_num = 3;
    public int default_reward_smallball_num = 2;
    public int default_reward_bigbar_num = 3;
    public int default_reward_smallbar_num = 2;
    public int default_reward_extralive_num = 1;

	// Переменные для отслеживания количества каждой награды
    int reward_ultraball_num = default_reward_ultraball_num;
    int reward_bigball_num = default_reward_bigball_num;
    int reward_smallball_num = default_reward_smallball_num;
    int reward_bigbar_num = default_reward_bigbar_num;
    int reward_smallbar_num = default_reward_smallbar_num;
    int reward_extralive_num = default_reward_extralive_num;


	// Вложенный класс для генерации случайных чисел
    private class Random {
        int num = (int) Math.floor(Math.random() * (game.brick.bricks.size() - 1) + 1);
		/**
		 *
		 Этот код представляет собой объявление внутреннего (вложенного) класса Random, который содержит переменную num.
		 Данная переменная инициализируется значением, полученным из выражения Math.random() * (game.brick.bricks.size() - 1) + 1.

		 Math.random(): Это вызов метода random() из класса Math, который генерирует случайное число в диапазоне [0, 1),
		 то есть включая 0, но исключая 1.

		 game.brick.bricks.size(): Это предполагает, что у вас есть объект game, у которого есть поле brick,
		 предположительно являющееся экземпляром какого-то класса. Внутри этого объекта есть поле bricks, которое, как
		 я могу предположить, является каким-то списком или коллекцией. Вызов size() на этом списке возвращает
		 количество элементов в нем.

		 (game.brick.bricks.size() - 1): Здесь вы вычитаете 1 из размера списка. Это, предположительно, сделано для того,
		 чтобы учесть индексацию с 0, так как индексы элементов в списке начинаются с 0.

		 Math.floor(...) + 1: Здесь используется Math.floor(...), чтобы округлить результат в меньшую сторону, а затем
		 добавляется 1, чтобы получить индекс элемента, который будет случайно выбран из вашего списка.
		 * */
    }


    private ArkanoidGameStart game;

    // Вложенный класс для представления награды
    public class Reward {
        int x;
        int y;
        int width;
        int height;
        String type;
        boolean reward_brick_on = false;
        boolean reward_on = false;
    }


    Reward current_reward = new Reward(); //Это создание нового объекта типа Reward
    public static ArrayList<Reward> current_rewards = new ArrayList<Reward>();
    // Это объявление статического списка current_rewards типа ArrayList, который будет хранить объекты класса Reward.

    // конструктор класса
    public Rewards(ArkanoidGameStart game) {
        this.game = game;
    }

    // Метод для создания всех наград в начале уровня
    public void createAllRewards() {
        // Create rewards at startLevel. We set random positions of the rewards at each level.
        while (reward_ultraball_num > 0) {
            Random random = new Random(); // Создание разных видов наград с учетом их количества
            if (game.brick.bricks.get(random.num).reward_type == "") {
                // Проверка, чтобы награда не создавалась в клетке с другой наградой

                game.brick.bricks.get(random.num).reward_type = "UltraBall";


                reward_ultraball_num--;   // Уменьшение количества наград после создания
            }
        }
        while (reward_bigball_num > 0) {
            Random random = new Random();
            if (game.brick.bricks.get(random.num).reward_type == "") {
                game.brick.bricks.get(random.num).reward_type = "BigBall";
                reward_bigball_num--;
            }
        }
        while (reward_smallball_num > 0) {
            Random random = new Random();
            if (game.brick.bricks.get(random.num).reward_type == "") {
                game.brick.bricks.get(random.num).reward_type = "SmallBall";
                reward_smallball_num--;
            }
        }
        while (reward_bigbar_num > 0) {
            Random random = new Random();
            if (game.brick.bricks.get(random.num).reward_type == "") {
                game.brick.bricks.get(random.num).reward_type = "BigBar";
                reward_bigbar_num--;
            }
        }
        while (reward_smallbar_num > 0) {
            Random random = new Random();
            if (game.brick.bricks.get(random.num).reward_type == "") {
                game.brick.bricks.get(random.num).reward_type = "SmallBar";
                reward_smallbar_num--;
            }
        }
        while (reward_extralive_num > 0) {
            Random random = new Random();
            if (game.brick.bricks.get(random.num).reward_type == "") {
                game.brick.bricks.get(random.num).reward_type = "ExtraLive";
                reward_extralive_num--;
            }
        }
        reward_ultraball_num = default_reward_ultraball_num;
        reward_bigball_num = default_reward_bigball_num;
        reward_smallball_num = default_reward_smallball_num;
        reward_bigbar_num = default_reward_bigbar_num;
        reward_smallbar_num = default_reward_smallbar_num;
        reward_extralive_num = default_reward_extralive_num;
    }


    // Метод для создания награды
    public void createReward(String type, int x, int y) {
        Reward reward = new Reward(); // Создание объекта награды
        reward.type = type;
        reward.x = x;
        reward.y = y;
        current_reward = reward;
        current_reward.reward_brick_on = true;

        // Установка параметров награды в зависимости от ее типа
        if (type == "UltraBall") {
            reward.width = Bricks.Brick.width + 6;
            reward.height = Bricks.Brick.height;
        } else if (type == "BigBall") {
            reward.width = Bricks.Brick.width + 5;
            reward.height = Bricks.Brick.width + 5;
        } else if (type == "SmallBall") {
            reward.width = Bricks.Brick.width - 8;
            reward.height = Bricks.Brick.width - 8;
        } else if (type == "BigBar" || type == "SmallBar") {
            reward.width = Bricks.Brick.width + 5;
            reward.height = Bricks.Brick.height - 2;
        } else if (type == "ExtraLive") {
            reward.width = Bricks.Brick.width + 6;
            reward.height = Bricks.Brick.height;
        }
        current_rewards.add(current_reward); // Добавление награды в список текущих наград
    }

    // Метод для активации награды
    public void startReward(String type) {
        ArkanoidGameStart.time_counter = 0;
        // Обработка различных видов наград и их влияние на игру
        for (int i = 0; i < current_rewards.size(); i++) {
            if (current_rewards.get(i).type == "UltraBall" && type == current_rewards.get(i).type) {
                current_rewards.get(i).reward_brick_on = false;
                current_rewards.get(i).reward_on = true;
                Ball.ultraballmode = true;
                ultraball_time = 10;
            } else if (current_rewards.get(i).type == "BigBall" && type == current_rewards.get(i).type) {
                current_rewards.remove(current_rewards.get(i));
                if (Ball.DIAMETER == 5) {
                    Ball.DIAMETER += 5;
                } else {
                    Ball.DIAMETER += 10;
                }
            } else if (current_rewards.get(i).type == "SmallBall" && type == current_rewards.get(i).type) {
                current_rewards.remove(current_rewards.get(i));
                if (Ball.DIAMETER <= 10 && Ball.DIAMETER > 5) {
                    Ball.DIAMETER -= 5;
                } else if (Ball.DIAMETER > 10) {
                    Ball.DIAMETER -= 10;
                }
            } else if (current_rewards.get(i).type == "BigBar" && type == current_rewards.get(i).type) {
                current_rewards.remove(current_rewards.get(i));
                if (Bar.WIDTH == 15) {
                    Bar.WIDTH += 15;
                    Bar.SIDE_WIDTH += 5;
                    Bar.bar_main_color = Color.WHITE;
                    Bar.bar_side_color = Color.GRAY;
                } else {
                    Bar.WIDTH += 15;
                    Bar.bar_main_color = Color.CYAN;
                    Bar.bar_side_color = Color.BLUE;
                }
            } else if (current_rewards.get(i).type == "SmallBar" && type == current_rewards.get(i).type) {
                current_rewards.remove(current_rewards.get(i));
                if (Bar.WIDTH == 30) {
                    Bar.WIDTH -= 15;
                    Bar.SIDE_WIDTH -= 5;
                    Bar.bar_main_color = Color.CYAN;
                    Bar.bar_side_color = Color.BLUE;
                } else if (Bar.WIDTH == 45) {
                    Bar.WIDTH -= 15;
                    Bar.bar_main_color = Color.WHITE;
                    Bar.bar_side_color = Color.GRAY;
                } else if (Bar.WIDTH > 15) {
                    Bar.WIDTH -= 15;
                    Bar.bar_main_color = Color.CYAN;
                    Bar.bar_side_color = Color.BLUE;
                }
            } else if (current_rewards.get(i).type == "ExtraLive" && type == current_rewards.get(i).type) {
                current_rewards.remove(current_rewards.get(i));
                game.bar.lives++;
            }
        }
    }

    // Метод для отключения награды
    public void stopReward(String type) {
        if (type == "UltraBall") {
            for (int i = 0; i < current_rewards.size(); i++) {
                if (type == current_rewards.get(i).type) {
                    Ball.ultraballmode = false;
                    current_rewards.get(i).reward_on = false;
                    game.text.rewards_label.setText("");
                    current_rewards.remove(current_rewards.get(i));
                }
            }
        } else if (type == "BigBall" || type == "SmallBall") {
            Ball.DIAMETER = 10;
        } else if (type == "BigBar" || type == "SmallBar") {
            Bar.WIDTH = 30;
            Bar.SIDE_WIDTH = 20;
            Bar.bar_main_color = Color.WHITE;
            Bar.bar_side_color = Color.GRAY;
        }
    }

    // Метод для отключения всех наград
    public void stopAllRewards() {
        stopReward("UltraBall");
        stopReward("BigBall");
        stopReward("SmallBall");
        stopReward("BigBar");
        stopReward("SmallBar");
    }

    // Метод для отрисовки награды на игровом поле
    public void paintBrickReward(Graphics2D g) {
        for (int i = 0; i < current_rewards.size(); i++) {
            if (current_rewards.get(i).type == "UltraBall" && current_rewards.get(i).reward_brick_on) {
                if (Ball.ultraball_color == 0) {
                    g.setColor(Color.RED);
                    Ball.ultraball_color++;
                } else if (Ball.ultraball_color == 1) {
                    g.setColor(Color.YELLOW);
                    Ball.ultraball_color++;
                } else if (Ball.ultraball_color == 2) {
                    g.setColor(Color.GREEN);
                    Ball.ultraball_color = 0;
                }
                g.fillRoundRect(current_rewards.get(i).x, current_rewards.get(i).y, current_rewards.get(i).width, current_rewards.get(i).height, 10, 10);
            } else if (current_rewards.get(i).type == "BigBall" && current_rewards.get(i).reward_brick_on) {
                g.setColor(Color.WHITE);
                g.fillRoundRect(current_rewards.get(i).x, current_rewards.get(i).y, current_rewards.get(i).width, current_rewards.get(i).height, 100, 100);
            } else if (current_rewards.get(i).type == "SmallBall" && current_rewards.get(i).reward_brick_on) {
                g.setColor(Color.WHITE);
                g.fillRoundRect(current_rewards.get(i).x, current_rewards.get(i).y, current_rewards.get(i).width, current_rewards.get(i).height, 100, 100);
            } else if (current_rewards.get(i).type == "BigBar" && current_rewards.get(i).reward_brick_on) {
                g.setColor(Color.CYAN);
                g.fillRoundRect(current_rewards.get(i).x, current_rewards.get(i).y, current_rewards.get(i).width, current_rewards.get(i).height, 10, 10);
                g.setFont(new Font("SansSerif", Font.BOLD, 10));
                g.setColor(Color.WHITE);
                g.drawString("<        >", current_rewards.get(i).x + -7, current_rewards.get(i).y + 7);
            } else if (current_rewards.get(i).type == "SmallBar" && current_rewards.get(i).reward_brick_on) {
                g.setColor(Color.CYAN);
                g.fillRoundRect(current_rewards.get(i).x, current_rewards.get(i).y, current_rewards.get(i).width, current_rewards.get(i).height, 10, 10);
                g.setFont(new Font("SansSerif", Font.BOLD, 10));
                g.setColor(Color.WHITE);
                g.drawString(">        <", current_rewards.get(i).x + -7, current_rewards.get(i).y + 7);
            } else if (current_rewards.get(i).type == "ExtraLive" && current_rewards.get(i).reward_brick_on) {
                g.setColor(Color.PINK);
                g.fillRoundRect(current_rewards.get(i).x, current_rewards.get(i).y, 8, 8, 100, 100);
                g.fillRoundRect(current_rewards.get(i).x + 8, current_rewards.get(i).y, 8, 8, 100, 100);
                int xpoints[] = {current_rewards.get(i).x, current_rewards.get(i).x + 8, current_rewards.get(i).x + 16};
                int ypoints[] = {current_rewards.get(i).y + 4, current_rewards.get(i).y + 15, current_rewards.get(i).y + 4};
                int npoints = 3;
                g.fillPolygon(xpoints, ypoints, npoints);
            }
        }
    }

    // Метод для отображения активных наград и их времени действия
    public void paintReward() {
        for (int i = 0; i < current_rewards.size(); i++) {
            if (current_rewards.get(i).reward_on) {
                if (current_rewards.get(i).type == "UltraBall") {
                    if (ultraball_time == 0) {
                        stopReward("UltraBall");
                    }
                    if (ultraball_time > 0) {
                        game.text.rewards_label.setText("" + ultraball_time);
                        ultraball_time--;
                    }
                }
            }
        }
    }

    // Метод для отрисовки награды на экране
    public void paint(Graphics2D g) {
        for (int i = 0; i < current_rewards.size(); i++) {
            if (current_rewards.get(i).reward_brick_on) {
                if (!barCollision(i)) {
                    paintBrickReward(g);
                    if (current_rewards.get(i).y == ArkanoidGameStart.HEIGHT) {
                        current_rewards.remove(current_rewards.get(i));
                    } else {
                        current_rewards.get(i).y++;
                    }
                } else if (barCollision(i))
                    startReward(current_rewards.get(i).type);
            }
        }
    }

    // Метод для получения границ награды
    public Rectangle getBounds(int i) {
        return new Rectangle(current_rewards.get(i).x, current_rewards.get(i).y, current_rewards.get(i).width, current_rewards.get(i).height);
    }

    // Метод для определения столкновения награды с баром
    private boolean barCollision(int i) {
        // Проверка наличия столкновения награды с баром
        if (game.bar.getBounds().intersects(getBounds(i))) {
            return true;
        } else if (game.bar.getLeftBounds().intersects(getBounds(i))) {
            return true;
        } else if (game.bar.getRightBounds().intersects(getBounds(i))) {
            return true;
        } else {
            return false;
        }
    }
}
