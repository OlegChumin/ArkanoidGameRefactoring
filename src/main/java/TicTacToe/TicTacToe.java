package TicTacToe;

public class TicTacToe {
    public static void main(String[] args) {
        // Инициализация переменной gameWon для отслеживания завершения игры.
        boolean gameWon = false;

        // Инициализация двумерного массива для игрового поля 3x3.
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        // Начало игрового цикла, который продолжается, пока игра не завершена.
        while (!gameWon) {
            printBoard(board);
            gameWon = true;
        }
    }

    // Метод для отображения игрового поля в консоли.
    public static void printBoard(char[][] board) {
        System.out.println(" | 0 | 1 | 2");
        System.out.println("------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < board[1].length; j++) {
                // Вывод символа из ячейки игрового поля.
                System.out.print(board[i][j]);
                if (j < 2) {
                    // Разделитель между столбцами
                    System.out.print(" | ");
                }
            }
            // Переход на новую строку после каждой строки игрового поля.
            System.out.println();
            if (i < 2 ) {
                // Горизонтальная линия-разделитель между строками игрового поля.
                System.out.println("------------");
            }
        }
    }
}
