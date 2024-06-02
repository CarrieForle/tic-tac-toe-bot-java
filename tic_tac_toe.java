import java.util.Scanner;
import java.util.Random;

public class tic_tac_toe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(
                " _____  _  ____      _____  ____  ____      _____  ____  _____\n/__ __\\/ \\/   _\\    /__ __\\/  _ \\/   _\\    /__ __\\/  _ \\/  __/\n  / \\  | ||  /  _____ / \\  | / \\||  /  _____ / \\  | / \\||  \\  \n  | |  | ||  \\__\\____\\| |  | |-|||  \\__\\____\\| |  | \\_/||  /_ \n  \\_/  \\_/\\____/      \\_/  \\_/ \\|\\____/      \\_/  \\____/\\____\\");

        System.out.println("\nWelcome to Tic-Tac-Toe(井字遊戲). Choose the option.\n");
        System.out.println("(1) Play singeplayer mode (Easy) (You v.s. computer)");
        System.out.println("(2) Play singeplayer mode (Hard) (You v.s. computer)");
        System.out.println("(3) Play 2-player mode (You v.s. your friend)");
        System.out.println("(4) How to play tic-tac-toe");
        System.out.println("(5) Quit");
        System.out.print("\nInput your choice > ");

        int choice = sc.nextInt();

        while (choice < 1 || choice > 5) {
            System.out.printf("(%d) is not a valid option.\nInput your choice > ", choice);
            choice = sc.nextInt();
        }

        System.out.println();

        while (choice == 4) {
            System.out.println("1 | 2 | 3 You and another player get to mark a position");
            System.out.println("--------- on a 3x3 square as show on the left.");
            System.out.println("4 | 5 | 6 A player marks their position by inputting the");
            System.out.println("--------- corressponding position number.");
            System.out.println("7 | 8 | 9 On the player's turn, they must mark a position");
            System.out.println("and they cannot mark an already marked position.");
            System.out.println();
            System.out.println("The first player to draw a line with their mark horizontally, vertically or diagnoally wins.");
            System.out.println("If none of the players can draw a line, i.e., it's not possible on the board, the game draws.");
            System.out.println();
            System.out.println("For example:");
            System.out.println("O | O | X    O | O | X");
            System.out.println("---------    ---------");
            System.out.println("X | X | O    X | X | O");
            System.out.println("---------    ---------");
            System.out.println("X | O | O    O | O | X");
            System.out.println("  X wins       Draws  ");
            System.out.println();
            System.out.println("(1) Play singeplayer mode (Easy) (You v.s. computer)");
            System.out.println("(2) Play singeplayer mode (Hard) (You v.s. computer)");
            System.out.println("(3) Play 2-player mode (You v.s. your friend)");
            System.out.println("(4) How to play tic-tac-toe");
            System.out.println("(5) Quit");
            System.out.print("\nInput your choice > ");

            choice = sc.nextInt();
        }

        if (choice != 5) {
            play(sc, choice, 0, 0);
        }
    }

    static void play(Scanner sc, int playmode, int player1_score, int player2_score) {
        String[] position_names = {
                "Top Left",
                "Top",
                "Top Right",
                "MIddle Left",
                "Center",
                "Middle Right",
                "Bottom Left",
                "Bottom",
                "Bottom Right"
        };

        int board_data = 0;
        Random rd = new Random();

        boolean is_player2_playing = (rd.nextInt(2) == 1);

        if (player1_score != 0 || player1_score != 0) {
            System.out.printf("\n%d - %d\n", player1_score, player2_score);
        }

        for (int i = 0; i < 9 && !is_lined(board_data); i++) {
            System.out.println();
            print_board_big(board_data, 1);
            System.out.println();

            String player_name;

            if (is_player2_playing) {
                if (playmode == 1) {
                    player_name = "Martin";
                } else if (playmode == 2) {
                    player_name = "Magnus Carlson";
                } else {
                    player_name = "Player 2";
                }
            } else {
                if (playmode == 1 || playmode == 2) {
                    player_name = "Player";
                } else {
                    player_name = "Player 1";
                }
            }

            if ((playmode == 1 || playmode == 2) && is_player2_playing) {
                int position;

                if (playmode == 1) {
                    position = get_stupid_ai_position_choice(board_data, rd);
                } else {
                    position = get_smart_ai_position_choice(board_data, rd);
                }

                board_data = update_board(board_data, position, is_player2_playing);

                System.out.printf("%s plays %s.\n", player_name, position_names[position]);

                is_player2_playing = !is_player2_playing;

                continue;
            }

            System.out.printf("%s plays. Input your position > ", player_name);

            int position = sc.nextInt();

            while (true) {
                if (position < 1 || position > 9) {
                    System.out.printf(
                            "\n%d is not a valid position. Position must be an integer in range [1, 9].\nInput your position > ",
                            position);
                    position = sc.nextInt();
                } else if (is_filled(board_data, position - 1)) {
                    System.out.printf(
                            "\n%s is already occupied. Please take another position.\nInput your position > ",
                            position_names[position - 1]);
                    position = sc.nextInt();
                } else {
                    break;
                }
            }

            position--;

            board_data = update_board(board_data, position, is_player2_playing);

            is_player2_playing = !is_player2_playing;
        }

        System.out.println();
        print_board_big(board_data, 1);
        System.out.println();

        if (!is_lined(board_data)) {
            System.out.println("Game draws!");
        } else {
            is_player2_playing = !is_player2_playing;

            String player_name;

            if (is_player2_playing) {
                if (playmode == 1) {
                    player_name = "Martin";
                } else if (playmode == 2) {
                    player_name = "Magnus Carlson";
                } else {
                    player_name = "Player 2";
                }
            } else {
                if (playmode == 1 || playmode == 2) {
                    player_name = "Player";
                } else {
                    player_name = "Player 1";
                }
            }

            System.out.printf("%s wins!\n", player_name);
        }

        System.out.print(
                "Do you want to play again? Or go back to main menu? Input your choice ('y' for playing again; 'n' for going back to menu) > ");

        String choice = sc.next();

        while (choice.length() != 1 || choice.charAt(0) != 'y' && choice.charAt(0) != 'n') {
            System.out.print("\nYou may only enter 'y' or 'n'.\nInput your choice > ");
            choice = sc.next();
        }

        if (choice.charAt(0) == 'y') {
            play(sc, playmode, player1_score, player2_score);
        } else {
            main(new String[] {});
        }
    }

    // 1st bit: empty = 0, filled = 1
    // 2nd bit: player1 = 0, player2 = 1
    // 1,2 top left
    // 3,4 top middle and so on

    static void print_board(int board_data) {
        char[] player_symbols = { 'O', 'X' };

        for (int i = 0; i < 9; i++) {
            if ((board_data >> (i * 2) & 1) == 1) {
                System.out.print(player_symbols[board_data >> (i * 2 + 1) & 1]);
            } else {
                System.out.print(" ");
            }

            if (i % 3 == 2) {
                if (i != 8) {
                    System.out.println("\n-----");
                } else {
                    System.out.println();
                }
            } else {
                System.out.print("|");
            }
        }
    }

    static void print_board_big(int board_data, int margin) {
        char[] player_symbols = { 'O', 'X' };

        for (int y = 0; y < 3; y++) {
            for (int i = 0; i < margin; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < margin * 2 + 1; k++) {
                        System.out.print(' ');
                    }

                    if (j != 2) {
                        System.out.print('|');
                    }
                }

                System.out.println();
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < margin; j++) {
                    System.out.print(' ');
                }

                if (is_filled(board_data, y * 3 + i)) {
                    System.out.print(player_symbols[board_data >> ((y * 3 + i) * 2 + 1) & 1]);
                } else {
                    System.out.print(' ');
                }

                for (int j = 0; j < margin; j++) {
                    System.out.print(' ');
                }

                if (i != 2) {
                    System.out.print('|');
                }
            }

            System.out.println();

            for (int i = 0; i < margin; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < margin * 2 + 1; k++) {
                        System.out.print(' ');
                    }

                    if (j != 2) {
                        System.out.print('|');
                    }
                }

                System.out.println();
            }

            if (y != 2) {
                for (int i = 0; i < (margin * 2 + 1) * 3 + 3; i++) {
                    System.out.print('-');
                }

                System.out.println();
            }
        }
    }

    static int update_board(int board_data, int position, boolean is_player2) {
        int player = 0;

        if (is_player2) {
            player = 1;
        }

        int updated_data = (player << 1) + 1;

        board_data |= updated_data << (position * 2);

        return board_data;
    }

    static boolean is_lined(int board_data) {
        for (int i = 0; i < 3; i++) {
            if (is_filled(board_data, i * 3) &&
                    is_filled(board_data, i * 3 + 1) &&
                    is_filled(board_data, i * 3 + 2) &&
                    get_player_from_position(board_data, i * 3) == get_player_from_position(board_data, i * 3 + 1) &&
                    get_player_from_position(board_data, i * 3 + 1) == get_player_from_position(board_data,
                            i * 3 + 2)) {
                return true;
            } else if (is_filled(board_data, i) &&
                    is_filled(board_data, i + 3) &&
                    is_filled(board_data, i + 6) &&
                    get_player_from_position(board_data, i) == get_player_from_position(board_data, i + 3) &&
                    get_player_from_position(board_data, i + 3) == get_player_from_position(board_data, i + 6)) {
                return true;
            }
        }

        if (is_filled(board_data, 0) &&
                is_filled(board_data, 4) &&
                is_filled(board_data, 8) &&
                get_player_from_position(board_data, 0) == get_player_from_position(board_data, 4) &&
                get_player_from_position(board_data, 4) == get_player_from_position(board_data, 8)) {
            return true;
        }

        if (is_filled(board_data, 2) &&
                is_filled(board_data, 4) &&
                is_filled(board_data, 6) &&
                get_player_from_position(board_data, 2) == get_player_from_position(board_data, 4) &&
                get_player_from_position(board_data, 4) == get_player_from_position(board_data, 6)) {
            return true;
        }

        return false;
    }

    static boolean is_filled(int board_data, int position) {
        return (board_data >> (position * 2) & 1) == 1;
    }

    static boolean get_player_from_position(int board_data, int position) {
        return (board_data >> (position * 2 + 1) & 1) == 1;
    }

    static int get_stupid_ai_position_choice(int board_data, Random rd) {
        int position = rd.nextInt(9);

        while ((board_data >> (position * 2) & 1) == 1) {
            position = rd.nextInt(9);
        }

        return position;
    }

    static int get_smart_ai_position_choice(int board_data, Random rd) {
        if (!is_filled(board_data, 4)) {
            return 4;
        }

        int position = has_potential_lined(board_data, false);

        if (position != -1) {
            return position;
        }

        position = has_potential_lined(board_data, false);

        if (position != -1) {
            return position;
        }

        int[] possible_positions = new int[7]; // Not 9 because the first 2 plays are determined.

        int possible_position_length = 0;

        for (int i = 0; i < 9; i++) {
            if (is_filled(board_data, i)) {
                continue;
            }

            if (has_potential_lined(board_data | (3 << (i * 2)), true) != -1) {
                possible_positions[possible_position_length++] = i;
            }
        }

        if (possible_position_length > 0) {
            return possible_positions[rd.nextInt(possible_position_length)];
        } else {
            return get_stupid_ai_position_choice(board_data, rd);
        }
    }

    static int has_potential_lined(int board_data, boolean is_player2) {
        for (int i = 0; i < 9; i++) {
            if (is_filled(board_data, i) && get_player_from_position(board_data, i) == is_player2) {
                for (int j = 0; j < 9; j++) {
                    if (i == j) {
                        continue;
                    }

                    if (is_filled(board_data, j) && get_player_from_position(board_data, j) == is_player2) {
                        for (int k = 0; k < 9; k++) {
                            if (k == i || k == j) {
                                continue;
                            }

                            if (!is_filled(board_data, k)) {
                                int assuming_board_data = 0;

                                assuming_board_data |= 1 << (i * 2);
                                assuming_board_data |= 1 << (j * 2);
                                assuming_board_data |= 1 << (k * 2);

                                if (is_lined(assuming_board_data)) {
                                    return k;
                                }
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }
}