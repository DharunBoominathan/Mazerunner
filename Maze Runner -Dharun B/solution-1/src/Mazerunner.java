import java.util.*;

public class Mazerunner {
    char[][] maze;
    int mrow, mcol;

    public Mazerunner(int row, int col) {
        mrow = row;
        mcol = col;
        this.maze = new char[row][col];
        initialize();
    }

    public void initialize() {
        for (int i = 0; i < mrow; i++) {
            for (int j = 0; j < mcol; j++) {
                maze[i][j] = '0';  // Empty cell
            }
        }
    }

    public void printmaze() {
        System.out.println(" ");
        for (int i = 0; i < mrow; i++) {
            for (int j = 0; j < mcol; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public void putBomb(int row , int col) {
            maze[row - 1][col - 1] = 'B';  // Bombs are triggers, no effect on path
        printmaze();
    }

    public void putTreasure(int row, int col) {
        row--;
        col--;
        if (row >= 0 && row < mrow && col >= 0 && col < mcol) {
            maze[row][col] = 'T';
        } else {
            System.out.println("Invalid row or column data");
        }
        printmaze();
    }

    public void putMonster(int row, int col) {
        row--;
        col--;
        if (row >= 0 && row < mrow && col >= 0 && col < mcol) {
            if (maze[row][col] == 'B') maze[row][col] = '0';
            else maze[row][col] = 'M';
        } else {
            System.out.println("Invalid row or column data");
        }
        printmaze();
    }

    public static class Node implements Comparable<Node> {
        int nrow, ncol;
        int g, h, f;  // g: cost to reach node, h: heuristic cost to target, f: total cost
        Node parent;

        public Node(int row, int col, int g, int h, Node parent) {
            this.nrow = row;
            this.ncol = col;
            this.g = g;
            this.h = h;
            this.f = g + h;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }
    }

    public void putrunner(int row, int col) {
        row--;
        col--;
        if (row >= 0 && row < mrow && col >= 0 && col < mcol) {
            if (maze[row][col] == 'M') {
                System.out.println("Runner cannot be near Monsters");
                return;
            } else {
                maze[row][col] = 'A';
            }
            printmaze();
        } else {
            System.out.println("Invalid row or column data");
            return;
        }

        Node start = new Node(row, col, 0, heuristic(row, col), null);
        int min_Dist = aStarSearch(start);
        if (min_Dist!=-1) {
            System.out.println("\nPath found using A* Search:");
            printmaze();
            System.out.println("\nMin-distance found using A* Search:"+min_Dist);
        } else {
            System.out.println("\nNo path found to the treasure using A* Search.");
        }
    }

    public int aStarSearch(Node node) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<String> closedSet = new HashSet<>();
        openSet.add(node);

        while (!openSet.isEmpty()) {
            Node cur = openSet.poll();
            int curRow = cur.nrow;
            int curCol = cur.ncol;

            if (maze[curRow][curCol] == 'T') {
                markPath(cur);
                return cur.g;
            }

            closedSet.add(curRow + "," + curCol);


            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] d : directions) {
                int newRow = curRow + d[0];
                int newCol = curCol + d[1];

                if (isValidMove(newRow, newCol) && !closedSet.contains(newRow + "," + newCol)) {
                    int newG = cur.g + 1;
                    int newH = heuristic(newRow, newCol);
                    Node node1 = new Node(newRow, newCol, newG, newH, cur);
                    openSet.add(node1);
                }
            }
        }

        return -1;
    }

    public int heuristic(int row, int col) {
        for (int i = 0; i < mrow; i++) {
            for (int j = 0; j < mcol; j++) {
                if (maze[i][j] == 'T') {
                    return Math.abs(i - row) + Math.abs(j - col);                  }
            }
        }
        return Integer.MAX_VALUE;
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < mrow && col >= 0 && col < mcol && maze[row][col] != 'M';
    }

    public void markPath(Node node) {
        Node cur = node;
        while (cur != null) {
            char val = maze[cur.nrow][cur.ncol];
            if (val != 'A' && val != 'T') maze[cur.nrow][cur.ncol] = 'P';
            cur = cur.parent;
        }
        printmaze();
    }


}
