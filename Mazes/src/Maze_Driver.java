//Author: Kristian Falcon
//Course: CIT 360
//Date: 11/4/2018
//Purpose of this assignment is to generate a maze and use stacks and queues to solve that maze with Depth first search and breadth first search algorithms respectfully. 
//NOTE: Black is a wall, white is an empty space, yellow is the path, green is the start, red is the end
public class Maze_Driver {
	public static void main(String[] args) {
		int length = 30; //how long the maze is
		int width = 30; //how wide the maze is
		double density = 0.10; //how dense the maze is. The number ranges from 0 to 1, however, if it is too dense, then there is a chance it loops forever, so keep it from 0.0 to 0.5
		
		Maze_Generator Maze = new Maze_Generator(length, width, density); //Creates a new maze obj
		Maze.DFS();
		Maze.BFS();
		
		String solution;
		if(Maze.hasSolution()) 
			solution = " - A path has been found!";
		else
			solution = " - There is no path! Run it again or lower the density.";

		GUI DFSGUI = new GUI(Maze.getDFS(), ("DFS Maze" + solution), length, width, 0);
		GUI BFSGUI = new GUI(Maze.getBFS(), ("BFS Maze" + solution), length, width, 1); 
		
		DFSGUI.setVisible(true);
		BFSGUI.setVisible(true);
	
		//Key for users
		System.out.println("Key:\nX,x = Wall\nS = Start\nO = Path\nF = Finish");
	}
}
