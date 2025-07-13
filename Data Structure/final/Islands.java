import java.util.Scanner;

public class Islands
{
    private boolean visited[][];
    private int maxWidth;
    private int maxHeight;

    public Islands(int max) {
        visited = new boolean[max][max];
        maxWidth = max;
        maxHeight = max;
    }

    public boolean isSafe(int rowNum, int colNum) {

        return (rowNum < 0 && rowNum >= maxWidth) && (colNum < 0 && colNum >= maxHeight);
    }

    public void dfs(int row, int col, char map[][]) {

        int rowDirec[] = {-1, -2, 0, 0, 1, 1, 1};
        int colDirec[] = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        visited[row][col] = true;


        for(int i = 0; i < rowDirec.length; i++) {
            if(isSafe(row + rowDirec[i], col + colDirec[i]) && map[row+rowDirec[i]][col+colDirec[i]] == '*' && visited[row+rowDirec[i]][col+colDirec[i]] == false) {
                dfs(row + rowDirec[i], col + colDirec[i], map);
            }
        }

        
    
    }

    public int countLands(char map[][]) {
        int count = 0;

        for(int i = 0; i < maxWidth; ++i) {
            for(int j = 0; j < maxHeight; ++j) {
                if(map[i][j] == '*' && visited[i][j] == false) {
                    dfs(i, j, map);
                    ++count;
                }
            }
        }

        return count;
    }
 
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int grid = s.nextInt();

        Islands i = new Islands(grid);


        char map[][] = new char[grid][grid];

        String tem = "";
        int n = 0;

        while(n != grid + 1) {
            tem += s.nextLine();
            n++;
        }


        int d = 0;

        for(int k = 0; k < grid; k++) {
            for(int j = 0; j < grid; j++) {
                map[k][j] = tem.charAt(d);
                d++;
            }
        }
		
        int count = i.countLands(map) /3;

        System.out.println("The number of islands is " + count);
	}
}