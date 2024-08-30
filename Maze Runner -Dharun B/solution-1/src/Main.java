import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to Maze runner");
        System.out.println("Please enter the row and column size :");
        int a=sc.nextInt();
        int b=sc.nextInt();
        Mazerunner maze=new Mazerunner(a,b);
        while(true){
            System.out.println("Do you want to insert bombs? 0/1");
            int x=sc.nextInt();
            if(x==1){
                System.out.println("Enter the row and column of bomb:");
                int c=sc.nextInt();
                int d=sc.nextInt();
                maze.putBomb(c,d);
            }
            else break;

        }
        while(true){
            System.out.println("Do you want to insert Monsters? 0/1");
            int x=sc.nextInt();
            if(x==1){
                System.out.println("Enter the row and column of Monster:");
                int c=sc.nextInt();
                int d=sc.nextInt();
                maze.putMonster(c,d);
            }
            else break;

        }
        System.out.println("Enter row and column of Treasure:");
        int e= sc.nextInt();
        int f=sc.nextInt();
        maze.putTreasure(e,f);

        System.out.println("Enter row and column of Adventurer:");
        int s= sc.nextInt();
        int t=sc.nextInt();
        maze.putrunner(s,t);

    }
}