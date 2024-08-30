import java.util.LinkedList;
import java.util.Queue;

public class Mazerunner {
    char[][] maze;
    int[][] bombs;
    int mrow,mcol;
    public Mazerunner(int row,int col,int[][] bombs){
        mrow=row;
        mcol=col;
        this.maze=new char[row][col];
        this.bombs=bombs;
        initialize();
    }
 public void initialize(){
        for(int i=0;i<mrow;i++){
            for(int j=0;j<mcol;j++){
                maze[i][j]='0';
            }
        }
        putBomb(bombs);
    }
    public void printmaze(){
        System.out.println(" ");
        for(int i=0;i<mrow;i++){
            for(int j=0;j<mcol;j++){
                System.out.print(maze[i][j]);
            }
            System.out.println(" ");
        }
    }
    public void putBomb(int[][]bombs){
        for(int[] bomb:bombs){
            maze[bomb[0]-1][bomb[1]-1]='B';
        }
        printmaze();
    }
    public void putTreasure(int row,int col){
        row--;
        col--;
        if(row>=0 && row<mrow && col>=0 && col<mcol){
            maze[row][col]='T';
        }
        else System.out.print("Invalid row or column data");
        printmaze();
    }
    public boolean isTreasure(){
        int count=0;
        for(int i=0;i<mrow;i++){
            for(int j=0;j<mcol;j++){
                if(maze[i][j]=='T') count++;
            }
        }
        if(count==1) return true;
        else return false;
    }
    public void putMonster(int row,int col){
        row--;
        col--;
        if(row>=0 && row<mrow && col>=0 && col<mcol){
            if(maze[row][col]!='B')
                maze[row][col]='M';
            else maze[row][col]='0';
        }
        else System.out.print("Invalid row or column data");
        printmaze();
    }
   public static class Node{
        int nrow,ncol,steps;
        Node prev;
        public Node(int row,int col,int steps,Node prev){
            nrow=row;
            ncol=col;
            this.steps=steps;
            this.prev=prev;
        }
   }
   public void putrunner(int row,int col){
        row--;
        col--;
        if(row>=0 && row<mrow & col>=0 && col<mcol){
            if(maze[row][col]=='M') System.out.print("Runner cannot be near Monsters");
            else maze[row][col]='A';
            printmaze();
        }
        else{
            System.out.print("Invalid row or column data");
            return;
        }
        Node node=new Node(row,col,0,null);
        int mindist=min_dist(node);
        System.out.printf("%n The min distance is %d",mindist);

    }
    public int  min_dist(Node node) {
        if(!isTreasure()) {
            System.out.print("No/more than one tresure found");
            return -1;
        }
        boolean[][] visited = new boolean[mrow][mcol];
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        visited[node.nrow][node.ncol] = true;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int[] d : directions) {
                int row = cur.nrow + d[0];
                int col = cur.ncol + d[1];

                if (row >= 0 && row < mrow && col >= 0 && col < mcol && maze[row][col] != 'M' && !visited[row][col]) {
                    if (maze[row][col] == 'T') {
                        min_path(cur);
                        return cur.steps+1;
                    }

                    Node node1 = new Node(row, col, cur.steps + 1, cur);
                    queue.add(node1);
                    visited[row][col] = true;
                }
            }
        }
        return -1;
    }
    public void min_path(Node node){
        Node cur=node;
        while(cur!=null){
            char val=maze[cur.nrow][cur.ncol];
            if(val!='A' && val!='T') maze[cur.nrow][cur.ncol]='P';
            cur=cur.prev;
        }
        printmaze();
    }




}
