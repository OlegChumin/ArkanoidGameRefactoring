package arkanoid_game.develop;

import javax.swing.*;
import java.awt.*;

public class Text {
    public static int menu_bar_height = 25; // Высота меню

    // Метка для начала игры
    JLabel start_label = new JLabel("Level " + Levels.current_level + ", Click To Start", SwingConstants.CENTER);

    // Метка для отображения наград
    JLabel rewards_label = new JLabel("", SwingConstants.CENTER);

    // Метка для текста "Lives:"
    JLabel lives_text_label = new JLabel("|| Lives: ");

    // Метка для отображения количества жизней
    JLabel lives_label = new JLabel("");

    // Метка для отображения уровня
    JLabel level_label = new JLabel("LEVEL " + Levels.current_level, SwingConstants.CENTER);
    Font lfont = new Font("courier", Font.PLAIN, 13); // Шрифт для обычного текста
    Font rwdfont = new Font("courier", Font.BOLD, 17); // Шрифт для текста с наградами
    Font lvlfont = new Font("courier", Font.BOLD, 13); // Шрифт для текста с уровнем

    private ArkanoidGameStart game;


    // Конструктор класса Text
    public Text(ArkanoidGameStart game) {
        this.game = game;
        makeStartLabel();
        makeRewardsLabel();
        makeLivesLabel();
        makeLevelLabel();
    }

    // Настройки для метки начала игры
    void makeStartLabel() {
        start_label.setVisible(true);
        start_label.setBounds(0, 155, ArkanoidGameStart.WIDTH, 100);
        start_label.setFont(lfont);
        start_label.setForeground(Color.GREEN);
        game.add(start_label);
    }

    // Настройки для метки с наградами
    void makeRewardsLabel() {
        rewards_label.setVisible(true);
        rewards_label.setBounds(0, 0, ArkanoidGameStart.WIDTH, 100);
        rewards_label.setFont(rwdfont);
        rewards_label.setForeground(Color.CYAN);
        game.add(rewards_label);
    }


    // Настройки для метки с количеством жизней
    void makeLivesLabel() {
        lives_text_label.setVisible(true);
        lives_label.setVisible(true);
        lives_text_label.setBounds(ArkanoidGameStart.WIDTH - 103, -33, ArkanoidGameStart.WIDTH, 100);
        lives_label.setBounds(ArkanoidGameStart.WIDTH - 22, -33, ArkanoidGameStart.WIDTH, 100);
        lives_text_label.setFont(lfont);
        lives_label.setFont(lfont);
        lives_text_label.setForeground(Color.WHITE);
        lives_label.setForeground(Color.GREEN);
        game.add(lives_text_label);
        lives_label.setText("" + game.bar.lives);
        game.add(lives_label);
    }


    // Настройки для метки с уровнем
    void makeLevelLabel() {
        level_label.setVisible(true);
        level_label.setBounds(0, -33, ArkanoidGameStart.WIDTH, 100);
        level_label.setFont(lvlfont);
        level_label.setForeground(Color.WHITE);
        game.add(level_label);
    }

    // отрисовка текста в игре
    public void paint(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 5, ArkanoidGameStart.WIDTH, 2);
        g.fillRect(0, menu_bar_height, ArkanoidGameStart.WIDTH, 2);
        makeLivesLabel();
    }
}
