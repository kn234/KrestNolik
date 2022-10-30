package KrestNolik;

import java.util.Random;
import java.util.Scanner;

public class KrestNolik {
    public static int SIZE = 5;
    public static int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static int DOTS_TO_PRE_WIN = DOTS_TO_WIN - 1;
    public static int VAR_TO_WIN = 0;

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (myCheckWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            myAiTurn();
            printMap();
            if (myCheckWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    public static void setVarToWin(int varToWin) {
        VAR_TO_WIN = varToWin;
    }

    public static boolean myCheckWin(char symb) {
        int countChar1 = 0;
        int countChar2 = 0;
        int countChar3 = 0;
        int countChar4 = 0;

        for (int i = 0; i <= DOTS_TO_WIN - 1; i++) {
            for (int j = 0; j <= DOTS_TO_WIN - 1; j++) {
                if (map[i][j] == symb) {
                    countChar1 += 1;
                } else {
                    countChar1 = 0;
                }
                if (map[j][i] == symb) {
                    countChar2 += 1;
                } else {
                    countChar2 = 0;
                }
                if (map[j][j] == symb) {
                    countChar3 += 1;
                } else {
                    countChar3 = 0;
                }
                if (map[j][SIZE - 1 - j] == symb) {
                    countChar4 += 1;
                } else {
                    countChar4 = 0;
                }
            }
            if (countChar3 == DOTS_TO_WIN || countChar4 == DOTS_TO_WIN || countChar1 == DOTS_TO_WIN || countChar2 == DOTS_TO_WIN) {
                return true;
            }
        }
        return false;
    }

    public static boolean myCheckPreWin(char symb) {
        int countChar1 = 0;
        int countChar2 = 0;
        int countChar3 = 0;
        int countChar4 = 0;

        for (int i = 0; i <= SIZE - 1; i++) {
            for (int j = 0; j <= SIZE - 1; j++) {
                if (map[i][j] == symb) {
                    countChar1 += 1;
                    if (countChar1 == DOTS_TO_PRE_WIN) {
                        setVarToWin(1);
                        return true;
                    }
                } else {
                    countChar1 = 0;
                }
                if (map[j][i] == symb) {
                    countChar2 += 1;
                    if (countChar2 == DOTS_TO_PRE_WIN) {
                        setVarToWin(2);
                        return true;
                    }
                } else {
                    countChar2 = 0;
                }
                if (map[j][j] == symb) {
                    countChar3 += 1;
                    if (countChar3 == DOTS_TO_PRE_WIN) {
                        setVarToWin(3);
                        return true;
                    }

                } else {
                    countChar3 = 0;
                }
                if (map[j][SIZE - 1 - j] == symb) {
                    countChar4 += 1;
                    if (countChar4 == DOTS_TO_PRE_WIN) {
                        setVarToWin(4);
                        return true;
                    }
                } else {
                    countChar4 = 0;
                }
            }

        }
        return false;
    }

    @Deprecated
    public static boolean checkWin(char symb) {
        if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static void myAiTurn() {
        int x, y;
        System.out.println(myCheckPreWin(DOT_X));
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
            if (myCheckPreWin(DOT_X)) {
                if (VAR_TO_WIN == 1) {
                    for (int i = 0; i < SIZE; i++) {
                        for (int j = 0; j < SIZE; j++) {
                            if (j < SIZE - 1 && map[i][j + 1] == 'X' && map[i][j] != 'X' && map[i][j] != 'O') {
                                x = j;
                                y = i;
                            } else if (j > 0 && map[i][j - 1] == 'X' && map[i][j] != 'X' && map[i][j] != 'O') {
                                x = j;
                                y = i;
                            }
                        }
                    }
                } else if (VAR_TO_WIN == 2) {
                    for (int i = 0; i < SIZE; i++) {
                        for (int j = 0; j < SIZE; j++) {
                            if (i < SIZE - 1 && map[i + 1][j] == 'X' && map[i][j] != 'X' && map[i][j] != 'O') {
                                x = j;
                                y = i;
                            } else if (i > 0 && map[i - 1][j] == 'X' && map[i][j] != 'X' && map[i][j] != 'O') {
                                x = j;
                                y = i;
                            }
                        }
                    }
                } else if (VAR_TO_WIN == 3) {
                    for (int i = 0; i < SIZE; i++) {
                        if (i < SIZE - 1 && map[i + 1][i + 1] == 'X' && map[i][i] != 'X' && map[i][i] != 'O') {
                            x = i;
                            y = i;
                        } else if (i > 0 && map[i - 1][i - 1] == 'X' && map[i][i] != 'X' && map[i][i] != 'O') {
                            x = i;
                            y = i;
                        }
                    }
                } else if (VAR_TO_WIN == 4) {
                    for (int i = 0; i < SIZE; i++) {
                        if (i > 0 && map[i - 1][SIZE - i + 1 - 1] == 'X' && map[i][SIZE - i - 1] != 'X' && map[i][SIZE - i - 1] != 'O') {
                            x = SIZE - i - 1;
                            y = i;
                        } else if (i < SIZE - 1 && map[i + 1][SIZE - i - 1 - 1] == 'X' && map[i][SIZE - i - 1] != 'X' && map[i][SIZE - i - 1] != 'O') {
                            x = SIZE - i - 1;
                            y = i;
                        }
                    }
                }
            }
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    @Deprecated
    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}