using System;

class TicTacToe
{
    static void Main()
    {
        char[,] board = new char[3, 3]
        {
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' }
        };

        char currentPlayer = 'X';
        bool gameWon = false;

        while (!gameWon)
        {
            PrintBoard(board);

            Console.WriteLine($"Игрок {currentPlayer}, введите строку (0, 1, 2): ");
            int row = int.Parse(Console.ReadLine());
            Console.WriteLine($"Игрок {currentPlayer}, введите столбец (0, 1, 2): ");
            int col = int.Parse(Console.ReadLine());

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row, col] == ' ')
            {
                board[row, col] = currentPlayer;
                gameWon = CheckWin(board, currentPlayer);

                if (gameWon)
                {
                    PrintBoard(board);
                    Console.WriteLine($"Игрок {currentPlayer} выиграл!");
                }
                else
                {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
            else
            {
                Console.WriteLine("Некорректный ход. Попробуйте еще раз.");
            }
        }
    }

    static void PrintBoard(char[,] board)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                Console.Write(board[i, j]);
                if (j < 2)
                {
                    Console.Write(" | ");
                }
            }
            Console.WriteLine();
            if (i < 2)
            {
                Console.WriteLine("---------");
            }
        }
    }

    static bool CheckWin(char[,] board, char player)
    {
        for (int i = 0; i < 3; i++)
        {
            if (board[i, 0] == player && board[i, 1] == player && board[i, 2] == player)
            {
                return true;
            }
            if (board[0, i] == player && board[1, i] == player && board[2, i] == player)
            {
                return true;
            }
        }
        if (board[0, 0] == player && board[1, 1] == player && board[2, 2] == player)
        {
            return true;
        }
        if (board[0, 2] == player && board[1, 1] == player && board[2, 0] == player)
        {
            return true;
        }
        return false;
    }
}
