package arkanoid_game.develop;

import java.awt.event.KeyEvent; //эта строка импортирует класс KeyEvent из java.awt.event KeyEvent
// отвечает за обработку событий клавиатуры
import java.awt.event.KeyListener; //эта строка импортирует интерфейс KeyListener из java.awt.event
import java.awt.event.MouseEvent; //эта строка импортирует класс MouseEvent из пакета java.awt.event
import java.awt.event.MouseListener; //эта строка импортирует MouseListener из пакета java.awt.event
import java.awt.event.MouseMotionListener; //эта строка импортирует интерфейс MouseMotionListener из пакета java.awt.event

public class ListenersHandler { //эта строка объявляет класс ListenersHandler
    private ArkanoidGameStart game; //эта строка объявляет приватную переменную game типа ArkanoidGameStart
    public static final int LEFT = 37; //эта строка объявляет публичную статическую LEFT и инициализирует ее значением 37
    //LEFT представляет код клавиши: стрелка на лево
    public static final int RIGHT = 39; //эта строка объявляет публичную статическую RIGHT и инициализирует ее значением 39
    //RIGHT представляет код клавиши стрелка на право
    int old_width = 0; //эта строка объявляет переменную old_width типа int и инициализирует ее значением 0

    public ListenersHandler(ArkanoidGameStart game) { //эта строка объявляет конструктор класса ListenersHandler
        // Конструктор принимает параметр game типа ArkanoidGameStart и выполняет инициализацию объекта ListenersHandler
        KeyListener listener = new MyKeyListener(); //Эта строка создает экземпляр класса MyKeyListener
        // и присваивает его переменной listener типа KeyListener
        MouseMotionListener mmlistener = new MyMouseMotionListener(); //Эта строка создает экземпляр класса
        // MyMouseMotionListener
        // и присваивает его переменной mmlistener типа MouseMotionListener
        MouseListener mlistener = new MyMouseListener(); //Эта строка создает экземпляр класса MyMouseListener
        // и присваивает его переменной mlistener типа MouseListener
        game.addKeyListener(listener); //Эта строка добавляет объект listener в
        // качестве слушателя событий клавиатуры к игре game
        game.addMouseMotionListener(mmlistener); //Эта строка добавляет объект mmlistener в
        // качестве слушателя движений мыши к игре game
        game.addMouseListener(mlistener); //Эта строка добавляет объект mlistener
        //в качестве слушателя событий мыши к игре game
        game.setFocusable(true); //Эта строка устанавливает фокус на игровое окно
        //чтобы оно могло принимать события клавиатуры и мыши
        this.game = game; //Эта строка присваивает переменной game значение переданное в конструкторе
    }

    public class MyKeyListener implements KeyListener { //Эта строка объявляет внутренний класс MyKeyListener
        //который реализует интерфейс KeyListener для обработки событий клавиатуры
        @Override //Эта строка указывает что методы ниже переопределяют методы из интерфейса KeyListener
        public void keyTyped(KeyEvent e) {
        } // Этот метод вызывается при нажатии и отпускании клавиши клавиатуры

        @Override
        public void keyPressed(KeyEvent e) { //Этот метод вызывается при нажатии клавиши клавиатуры
            if (e.getKeyCode() == LEFT) { //Эта строка проверяет нажата ли клавиша стрелка на влево
                game.bar.move(LEFT); //Эта строка вызывает метод move()
                // объекта game.bar с параметром LEFT чтобы передвинуть игровую платформу влево
            } else if (e.getKeyCode() == RIGHT) { // Эта строка проверяет нажата ли клавиша стрелка на вправо
                game.bar.move(RIGHT); //Эта строка вызывает метод move()
                // объекта game.bar с параметром RIGHT чтобы передвинуть игровую платформу вправо
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        } //Этот метод вызывается при отпускании клавиши клавиатуры
    }

    public class MyMouseMotionListener implements MouseMotionListener { // Эта строка объявляет внутренний класс
        //MyMouseMotionListener
        //который реализует интерфейс MouseMotionListener для обработки движений мыши
        @Override
        public void mouseDragged(MouseEvent e) {
        } // Этот метод вызывается при перетаскивании мыши с зажатой кнопкой

        @Override
        public void mouseMoved(MouseEvent e) { //Этот метод вызывается при движении мыши
            game.bar.x = e.getX() - (Bar.WIDTH / 2); //Эта строка задает новое значение свойству x игровой платформы game.bar
            //на основе значения координаты X мыши и ширины игровой платформы
        }
    }

    public class MyMouseListener implements MouseListener { //Эта строка объявляет внутренний класс MyMouseListener
        // который реализует интерфейс MouseListener для обработки событий мыши

        @Override //Эта строка указывает что методы ниже переопределяют методы из интерфейса MouseListener
        public void mouseClicked(MouseEvent e) { //Этот метод вызывается при щелчке кнопкой мыши
            ArkanoidGameStart.startGame(game); //Эта строка вызывает статический метод startGame() класса ArkanoidGameStart
            // который запускает игру с передачей ему ссылки на экземпляр игры game
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        } //Этот метод вызывается когда мышь входит в область компонента

        @Override
        public void mouseExited(MouseEvent e) {
        } //Этот метод вызывается когда мышь выходит из области компонента

        @Override
        public void mousePressed(MouseEvent e) {
        } //Этот метод вызывается при нажатии кнопки мыши

        @Override
        public void mouseReleased(MouseEvent e) {
        } //Этот метод вызывается при отпускании кнопки мыши
    }
}