import java.io.*;
import java.util.*;

public class Main {
    static Set<Character>[][] cell = new Set[3][3];
    static Set<Character>[] rows = new Set[9];
    static Set<Character>[] columns = new Set[9];
    static char sudoku[][] = new char[9][9];
    static boolean func(int x, int y) {
        if(y>=9) {
            x++;
            y = 0;
        }
        if(x==9 && y == 0) {
            return true;
        }
        if(sudoku[x][y] != '0') {
            return func(x, y + 1);
        }
        for (char i = 0; i < 9; i++) {
            char c = (char)('1' + i);
            if(cell[x/3][y/3].contains(c)) continue;
            if(rows[x].contains(c)) continue;
            if(columns[y].contains(c)) continue;
            cell[x/3][y/3].add(c);
            rows[x].add(c);
            columns[y].add(c);
            sudoku[x][y] = c;
            if(!func(x,y+1)) {
                cell[x/3][y/3].remove(c);
                rows[x].remove(c);
                columns[y].remove(c);
                sudoku[x][y] = '0';
                continue;
            }
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cell[i][j] = new HashSet<>();
            }
        }
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            columns[i] = new HashSet<>();
        }
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                char c = line.charAt(j);
                sudoku[i][j] = c;
                cell[i/3][j/3].add(c);
                rows[i].add(c);
                columns[j].add(c);
            }
        }
        func(0,0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <9; j++) {
                System.out.print(sudoku[i][j]);
            }
            System.out.println();
        }
    }
}
