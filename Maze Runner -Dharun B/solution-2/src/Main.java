// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int[][] bomb={{3,2},{5,6}};
        Mazerunner maze=new Mazerunner(6,6,bomb);
        maze.putMonster(3,1);
        maze.putMonster(3,2);
        maze.putMonster(3,3);
        maze.putMonster(3,4);
        maze.putTreasure(6,6);
        maze.putrunner(1,1);
    }
}