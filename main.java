import java.util.Scanner;
import java.util.Random;

public class Main {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    Random random = new Random();
    char[][] array = {
        { ' ', ' ', ' ' },
        { ' ', ' ', ' ' },
        { ' ', ' ', ' ' }
    };
    boolean running = true;

    System.out.print(
        "It is a tic-tac-toe game what you can play againts computer. \n\n - There is a grid of 3 rows and 3 columns. \n - You need to indicate where you put your sign one after another. \n - The one who will have 3 signs in a row will win. \n - To terminate the game please enter 0. \nYou will start first. \n\n");

    // Looping the game with WHILE loop

    while (running == true) {
      try {

        // Firstly, user enter a row and column
        System.out.print("Please enter a row: ");

        int userRow = scn.nextInt();

        // Checking is user wanna terminate the game by inserting 0
        if (userRow == 0) {
          System.out.print("You terminated the game. Bye!");
          break;
        }

        // Checking if user entered correct numbers between 1 and 3
        if (userRow > 0 && userRow < 4) {
          System.out.print("Please enter a column: ");
          int userCol = scn.nextInt();

          if (userCol == 0) {
            System.out.print("You terminated the game. Bye!");
            break;
          }

          // When user enter a row and column, the program will check if the cell is empty
          if (userCol > 0 && userCol < 4) {
            if (array[userRow - 1][userCol - 1] == ' ') {
              array[userRow - 1][userCol - 1] = 'X';
            } else {
              System.out.print("This entry is already taken. Try again.");
              continue;
            }
          }
        } else {
          System.out.print("You can enter only numbers from 1 to 3. Try again.");
          continue;
        }
      } catch (Exception e) {
        System.out.print("You can enter only numbers. Game terminated.");
        break;
      }

      // Here checking for winning conditions
      running = checkConditionsForUserWin(array);
      if (running == false) {
        System.out.print("Congratulations! You won! 🎉\n");
        printArray(array);
        break;
      }

      running = checkIfCellsFree(array);
      if (running == false) {
        System.out.print("It is a draw! \n");
        printArray(array);
        break;
      }

      // Creating a rondom column and row number by computer
      int computerRow = random.nextInt(3);
      int computerCol = random.nextInt(3);

      // If cell is empty, then computer puts its sign
      if (array[computerRow][computerCol] == ' ') {
        array[computerRow][computerCol] = '0';
        running = checkIfCellsFree(array);
        if (running == false) {
          System.out.print("It is a draw! \n");
          printArray(array);
          break;
        } // if cell is already taken, then we generate another numbers to find a new
          // cell, but first check for availability
      } else {
        running = checkIfCellsFree(array);
        if (running == false) {
          System.out.print("It is a draw! \n");
          printArray(array);
          break;
        }
        while (array[computerRow][computerCol] != ' ') {
          computerRow = random.nextInt(3);
          computerCol = random.nextInt(3);

          System.out.println("Waiting for computer's turn... ");
        }
        array[computerRow][computerCol] = '0';
      }

      // Printing the current array
      printArray(array);

      // Here checking for winning conditions
      running = checkConditionsForComputerWin(array);
      if (running == false) {
        System.out.print("This time Computer won. ☹ \n");
        printArray(array);
        break;
      }

      running = checkIfCellsFree(array);
      if (running == false) {
        System.out.print("It is a draw! \n ");
        printArray(array);
        break;
      }

    }

    scn.close();
  }

  // FUNCTIONS

  public static void printArray(char[][] array) {
    // Printing the current array

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        System.out.print("|" + array[i][j]);
        if (j == 2) {
          System.out.print("|");
        }
      }
      System.out.println();
    }
  }

  public static boolean checkConditionsForUserWin(char[][] array) {
    // Check if there is a horizontal line
    for (int i = 0; i < array.length; i++) {
      if (array[i][0] == 'X' && array[i][1] == 'X' && array[i][2] == 'X') {
        return false;
      }
    }
    // Check if there is a vertical line
    for (int i = 0; i < array.length; i++) {
      if (array[0][i] == 'X' && array[1][i] == 'X' && array[2][i] == 'X') {
        return false;
      }
    }
    // Check if there is a diagonal line
    if (array[0][0] == 'X' && array[1][1] == 'X' && array[2][2] == 'X') {
      return false;
    }
    if (array[0][2] == 'X' && array[1][1] == 'X' && array[2][0] == 'X') {
      return false;
    }
    return true;
  }

  public static boolean checkConditionsForComputerWin(char[][] array) {
    // Check if there is a horizontal line
    for (int i = 0; i < array.length; i++) {
      if (array[i][0] == '0' && array[i][1] == '0' && array[i][2] == '0') {
        return false;
      }
    }

    // Check if there is a vertical line
    for (int i = 0; i < array.length; i++) {
      if (array[0][i] == '0' && array[1][i] == '0' && array[2][i] == '0') {
        return false;
      }
    }

    // Check if there is a diagonal line
    for (int i = 0; i < array.length; i++) {
      if (array[0][0] == '0' && array[1][1] == '0' && array[2][2] == '0') {
        return false;
      }
    }

    // Check if there is a versa diagonal line
    if (array[0][0] == '0' && array[1][1] == '0' && array[2][2] == '0') {
      return false;
    }
    if (array[0][2] == '0' && array[1][1] == '0' && array[2][0] == '0') {
      return false;
    }
    return true;
  }

  // Check if there are free cells
  public static boolean checkIfCellsFree(char[][] array) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (array[i][j] == ' ') {
          return true;
        }
      }
    }
    return false;
  }

}
