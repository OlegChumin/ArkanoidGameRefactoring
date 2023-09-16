package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        // Инициализация переменной gameWon для отслеживания завершения игры.
        boolean gameWon = false;

        // Инициализация переменной currentPlayer для отслеживания текущего игрока.
        char currentPlayer = 'X';

        // Инициализация двумерного массива для игрового поля 3x3.
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        // Создание объекта Scanner для считывания ввода из консоли.
        Scanner scanner = new Scanner(System.in);

        // Начало игрового цикла, который продолжается, пока игра не завершена.
        while (!gameWon) {
            printBoard(board);

            // Вывод приглашения к вводу координат строки игроку.
            System.out.println("Игрок " + currentPlayer + ", введите номер строки (0, 1, 2): ");
            // Считывание введенной строки как целого числа для строки.
            int row = scanner.nextInt();

            // Вывод приглашения к вводу координат столбца игроку.
            System.out.println("Игрок " + currentPlayer + ", введите номер столбца (0, 1, 2): ");
            int column = scanner.nextInt();

            // Проверка на корректность введенных координат и доступности ячейки на игровом поле.
            if (row >= 0 && row < 3 && column >= 0 && column < 3 && board[row][column] == ' ') {
                // Установка символа текущего игрока в выбранной ячейке.
                board[row][column] = currentPlayer; // поставили X
                gameWon = checkWin(board, currentPlayer);
                if (gameWon) {
                    // Вывод сообщения о победе текущего игрока.
                    printBoard(board);
                    System.out.println("Игрок " + currentPlayer + " выиграл!");
                } else {
                    // Смена текущего игрока (X на O или наоборот).
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                // Вывод сообщения об ошибке при некорректном ходе.
                System.out.println("Некорректный ход. Попробуйте еще раз.");
            }
        }
//            gameWon = true; //TODO удалить эту заглушку!

        // Закрытие объекта Scanner.
        scanner.close();
    }

    // Метод для отображения игрового поля в консоли.
    public static void printBoard(char[][] board) {
        System.out.println(" | 0 | 1 | 2");
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < board[0].length; j++) {
                // Вывод символа из ячейки игрового поля.
                System.out.print(board[i][j]);
                if (j < 2) {
                    // Разделитель между столбцами
                    System.out.print(" | ");
                }
            }
            // Переход на новую строку после каждой строки игрового поля.
            System.out.println();
            if (i < 2) {
                // Горизонтальная линия-разделитель между строками игрового поля.
                System.out.println("-------------");
            }
        }
    }

    // Функция для проверки на выигрыш игрока.
    public static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            // Проверка выигрышных комбинаций по горизонтали.
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            // Проверка выигрышных комбинаций по вертикали.
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // Проверка выигрышных комбинаций по диагонали.
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        // Если нет выигрышной комбинации, возвращается false.
        return false;
    }
}
