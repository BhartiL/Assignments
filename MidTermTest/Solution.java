import java.util.ArrayList;

/**
 * Problem-5 Extra Credit A Maze is given as N*N binary matrix of blocks where
 * source block is the upper left most block i.e., maze[0][0] and destination
 * block is lower rightmost block i.e., maze[N­1][N­1]. A rat starts from source
 * and has to reach destination. The rat can move only in two directions:
 * forward and down. In the maze matrix, 0 means the block is dead end and 1
 * means the block can be used in the path from source to destination. Your
 * function should take the maze as input and return an arrayList of the
 * resulting path. If no path is found return empty list.
 *
 */

class Cell { // class Cell
	int x;
	int y;

	Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}

}

public class Solution { // class Solution

	public ArrayList<Cell> findPath(int[][] maze) {
		ArrayList<Cell> path = new ArrayList<Cell>();
		//if maze is null or empty return empty path
		if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return path;
		}
		int row = maze.length;
		int col = maze[0].length;
		//if first cell or last cell have zero, return empty path
		if (maze[0][0] == 0 || maze[row - 1][col - 1] == 0) {
			return path;
		}
		//find path to reach last cell recursively starting from first cell
		findPathRecursive(maze, path, 0, 0);
		return path;
	}

	// recursively check available paths from a cell
	private boolean findPathRecursive(int[][] maze, ArrayList<Cell> path, int x, int y) {
		if (!isInBound(maze, x, y) || maze[x][y] == 0) {
			return false;
		}
		path.add(new Cell(x, y));
		if (x == maze.length - 1 && y == maze.length - 1) {
			return true;
		}
		// check downward path using recursion
		// You can combine downward and forward path check using || into one if check
		if (findPathRecursive(maze, path, x, y + 1)) {
			return true;
		}
		// check forward path by recursion
		if (findPathRecursive(maze, path, x + 1, y)) {
			return true;
		}
		// remove the path if it is blocked before last cell
		path.remove(path.size() - 1);
		return false;
	}

	// check if x and y are in bounds of maze
	private boolean isInBound(int[][] maze, int x, int y) {
		return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
	}

	public static void main(String[] args) {
		int[][] maze = { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 0, 0 }, { 1, 1, 1, 1 } };
		// multiple routes: { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1,
		// 1 } };
		// only 1 route: { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 0, 0 }, { 1, 1, 1, 1
		// } };
		// no route: { { 1, 0, 0, 0 }, { 0, 1, 0, 1 }, { 0, 1, 0, 0 }, { 1, 1, 1, 1 } };
		Solution sol = new Solution();
		ArrayList<Cell> path = sol.findPath(maze);
		if (path.isEmpty())
			System.out.println("No path found!");
		else {
			System.out.println("Escape route for Rat : ");
			for (Cell p : path)
				System.out.println(p.toString());
		}
	}

}
