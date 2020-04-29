//This provides some sort of GUI for the mazes, it was added last minute 
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class GUI extends JFrame{
	String Maze[][];		//Takes the mazes put into the the class
	String Title;			//Takes a title for the window, provides the user with knowledge that the maze did not work
	int length;				//Maze length (in characters)
	int width;				//Maze width (in characters)
	int squareSize = 20;	//Size of each square in blocks
	int loc; 				//used to offset the location of one window from the other
	
	//Constructor which also provides the window
	public GUI(String[][] Maze, String Title, int length, int width, int loc) {
		this.Maze = Maze;
		this.Title = Title;
		this.length = length;
		this.width = width;
		this.loc = loc;
		
		setTitle(Title);												//Tile is passed from the main class, used to tell user if maze has no solution
		setSize(length * squareSize + 60, width * squareSize + 60);		//Sets the size of the window to be slightly larger than the maze itself, no matter the size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					//Ends program when one window is closed
		setLocation(loc * (width * squareSize + 60), loc);				//Used to offset the second window so the appear side-by-side
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.translate(30, 30); //Adds a little white boarder around the maze
		
		for (int i = 0; i < Maze.length; i++) {
			for (int j = 0; j < Maze[i].length; j++) {
				Color color;
				switch (Maze[i][j]) { //Sets different colors to each character of the Maze
					case " " : color = Color.WHITE;  //Open space
						break;
					case "." : color = Color.WHITE;  //Popped point, appears as an open space
						break;
					case "O" : color = Color.YELLOW; //Path of each searching algorithm
						break;
					case "F" : color = Color.RED;	 //End point
						break;
					case "S" : color = Color.GREEN;	 //Start point
						break;
					default : color = Color.BLACK;	 //Walls
						break;
				}
				g.setColor(color);
				g.fillRect(squareSize * j, squareSize * i, squareSize, squareSize);
			}
		}
	}
}
