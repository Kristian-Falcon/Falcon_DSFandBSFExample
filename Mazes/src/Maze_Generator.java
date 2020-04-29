//This class generates a maze, performs DFS and BFS algorithms, converts mazes to strings and has some debugging classes
public class Maze_Generator {
	String[][] Maze;			//The maze that will be solved with DFS
	boolean[][] Visited;		//The 2d array to determine if a spot in the Maze array has been visited or not; used for DFS
	String[][] Maze2;			//Copy of the maze for BSF
	boolean[][] Visited2;		//Copy of the visited for BSF
	int[][] Distance;			//only for BSF, determines how far each point is from the start
	int startx;					//The x coordinate of the starting point
	int starty;					//The y coordinate of the starting point
	int finishx;				//The x coordinate of the ending point
	int finishy;				//The y coordinate of the ending point
	boolean hasStart = false;	//Does the Maze have a start? 
	boolean hasFinish = false;	//Does the Maze have an end? 
	boolean solution = true;	//Does the Maze have a path from the start to the end? 

	//Constructor generates the mazes
	public Maze_Generator(int a, int b, double c) {
		Maze = new String[b][a]; //'b' determines length while 'a' determines height. 'c' is for density
		
		//This creates an empty room
		for (int i = 0; i < Maze.length; i++) {
			for (int j = 0; j < Maze[i].length; j++) {
				//The following if/else surrounds the maze with a wall. Outer will consist of capital X's while the inner wall consists of lower case x's
				if (i == 0 || i == Maze.length - 1 || j == 0 || j == Maze[i].length - 1)
					Maze[i][j] = "X";
				else
					Maze[i][j] = " ";
			}
		}

		//This adds walls, though not too efficient with so many for-loops, but it is the only way I got it to work.
		for (int i = 0; i < Maze.length; i++) {
			for (int j = 0; j < Maze[i].length; j++) {
				//The following creates random points throughout the maze to start the wall.
				if (!(Maze[i][j].equals("X")) && (Math.random()) <= c)
					Maze[i][j] = "x";

				//The following extends the walls from the point. There is a 50% chance it will.
				if (!(Maze[i][j].equals("X")) && i > 0 && i < Maze.length - 1 && j > 0 && j < Maze[i].length - 1 && !(Maze[i + 1][j].equals(null)) && !(Maze[i][j + 1].equals(null))) {
					if ((Maze[i - 1][j].equals("x") || Maze[i + 1][j].equals("x") || Maze[i][j - 1].equals("x") || Maze[i][j + 1].equals("x")) && Math.random() <= 0.50)
						Maze[i][j] = "x";
				}
			}
		}

		//Adds the Start and Finish points in open spots
		while (!hasStart || !hasFinish) {
			if (!hasStart) {
				if (Maze[starty][startx].equals(" ")) { //Will create a start at this point if there is an open space
					Maze[starty][startx] = "S";
					hasStart = true;
				} else {								//Otherwise, it looks at a new, random location
					startx = (int) (Math.random() * (Maze.length));
					starty = (int) (Math.random() * (Maze[startx].length));
				}
			}

			if (!hasFinish) {
				if (Maze[finishy][finishx].equals(" ")) {	//Will create a start at this point if there is an open space
					Maze[finishy][finishx] = "F";
					hasFinish = true;
				} else {									//Otherwise, it looks at a new, random location
					finishx = (int) (Math.random() * (Maze.length));
					finishy = (int) (Math.random() * (Maze[finishx].length));
				}
			}
		}

		//Creates the visited arrays
		Visited = new boolean[b][a];
		for (int i = 0; i < Visited.length; i++) {
			for (int j = 0; j < Visited[i].length; j++) {
				//The following if/else surrounds the maze with a wall
				if (Maze[i][j].equalsIgnoreCase("X") || Maze[i][j].equals("S"))
					Visited[i][j] = true;
				else
					Visited[i][j] = false;
			}
		}

		//Copies maze for BFS
		Maze2 = new String[b][a];
		for (int i = 0; i < Maze.length; i++) {
			for (int j = 0; j < Maze[i].length; j++ ) {
				Maze2[i][j] = Maze[i][j];
			}
		}
		
		//Copies the visited 2D array for BFS
		Visited2 = new boolean[b][a];
		for (int i = 0; i < Visited.length; i++) {
			for (int j = 0; j < Visited[i].length; j++ ) {
				Visited2[i][j] = Visited[i][j];
			}
		}
		
		//Creates the Distance array only for BFS to use
		Distance = new int[b][a];
	}

	//Searches maze using a stack, depth first search
	public void DFS() {
		boolean done = false;
		//x & y both represent the point, their initial values are the starting point's coordinates 
		int x = startx;
		int y = starty;
		//Two stacks are created for the x and y. So long as I reference one immediately after the other, there should be no integrity issue
		Stack<Integer> stackx = new Stack<>();
		Stack<Integer> stacky = new Stack<>();

		//I kept all sys-outs if you wish to see the console and my means of debugging 
		System.out.println("Depth first search:");
		while (!done) { //Big loop which takes up most of the method
			//The following if/else ladder checks the surrounding points to see if they have been visited in the visited array
			if (Visited[y - 1][x] == false) {//checks north
				stackx.push(x); 				//Pushes both x and y coordinates into the stack
				stacky.push(y);
				--y; 							//Moves the point to the checked location
				Visited[y][x] = true;			//Marks the current location as being visited
				if (Maze[y][x].equals("F")) {	//If the current location happens to be the end, a path was found! 
					done = true;
					System.out.println("Successfully found a path!");
				} else							//Otherwise, the point will be marked as part of the path
					Maze[y][x] = "O";			
			} else if (Visited[y + 1][x] == false) {//checks south
				stackx.push(x);
				stacky.push(y);
				++y;
				Visited[y][x] = true;
				if (Maze[y][x].equals("F")) {
					done = true;
					System.out.println("Successfully found a path!");
				} else
					Maze[y][x] = "O";
			} else if (Visited[y][x + 1] == false) {//checks east
				stackx.push(x);
				stacky.push(y);
				++x;
				Visited[y][x] = true;
				if (Maze[y][x].equals("F")) {
					done = true;
					System.out.println("Successfully found a path!");
				} else
					Maze[y][x] = "O";
			} else if (Visited[y][x - 1] == false) {//checks checks west
				stackx.push(x);
				stacky.push(y);
				--x;
				Visited[y][x] = true;
				if (Maze[y][x].equals("F")) {
					done = true;
					System.out.println("Successfully found a path!");
				} else
					Maze[y][x] = "O";
			} else {										//If all surrounding points are marked as visited
				if (stackx.isEmpty() && stacky.isEmpty()) {	//Check to see if the stack is empty							
					System.out.println("There is no solution.");
					solution = false; 						//If the stack is empty, then there is no solution
					done = true;
				} else {									//If the stack is not empty, then then pop the stack until it is
					Maze[y][x] = ".";						//For debugging purposes, the popped points are marked as dots
					x = stackx.pop();
					y = stacky.pop();
				}
			}
		}
		System.out.println(DFSToString());
	}

	//searches the maze using a queue, breadth first search
	public void BFS() {
		boolean done = false;
		int x = startx;							//x & y both represent the point
		int y = starty;
		int counter = 0;						//The counter serves several purposes that I point out every time it is used
		Queue<Integer> qx = new Queue<>();		//Queues for the x and y coordinates, used to find a path to the end 
		Queue<Integer> qy = new Queue<>();
		Queue<Integer> finalx = new Queue<>();	//A second set of queues to mark out the final path after it has been found
		Queue<Integer> finaly = new Queue<>();
		
		Distance[y][x] = counter; //starting point has a distance of zero, obviously
		qx.enqueue(x);	//Adds starting point to the queue
		qy.enqueue(y);
		
		System.out.println("Breadth search first:");
		while (!done) { //Big loop that makes sure everything runs until it is done
			if (qx.isEmpty() && qy.isEmpty()) {//If the queue is empty, then there is no solution
				solution = false; 
				System.out.println("There is no solution");
				done = true;
			} else { //If the queue is not empty, then the following happens
				counter++; 
				do { //I never thought I would actually ever use a do/while, but here I am...
					x = qx.dequeue();//dequeues these coordinates to search its surroundings, needs to be done at least once to avoid infinite loop
					y = qy.dequeue();
					if (Maze2[y][x].equals("F")) {//If coordinates are at the end point, then a path is made 
						System.out.println("Successfully found a path!");
						counter--;
						while (counter != 0) {//Enqueues points into new queues to creates the final path
							//The following if/else ladder checks surrounding points to find positions that have a distance 1 less than the current position
							if (Distance[y-1][x] == counter) {//Checks North
								y--; 				//Moves north
								finalx.enqueue(x);	//Enqueues points
								finaly.enqueue(y);
								counter--;			
							} else if (Distance[y+1][x] == counter) {//Checks South
								y++;
								finalx.enqueue(x);
								finaly.enqueue(y);
								counter--;
							} else if (Distance[y][x+1] == counter) {//Checks East
								x++;
								finalx.enqueue(x);
								finaly.enqueue(y);
								counter--;
							} else if (Distance[y][x-1] == counter) {//Checks West
								x--;
								finalx.enqueue(x);
								finaly.enqueue(y);
								counter--;
							} else 
								counter--;	//This prevents an infinite loops in case conditions just so happen to not be met.  
								
						}
						while (!finalx.isEmpty() && !finaly.isEmpty()) {//Dequeues all of them to draw a path
							Maze2[finaly.dequeue()][finalx.dequeue()] = "O"; //O is used to represent the path
						}
						done = true;
					} else { //If new point is not the final point, the following happens
						//The following if/else ladder checks the surrounding points to see if they have been visited. If not...
						if (Visited2[y - 1][x] == false) {//Checks north
							qx.enqueue(x);					//Enqueues the checked point
							qy.enqueue(y - 1);
							Visited2[y - 1][x] = true;		//Marks that point as being visited
							Distance[y - 1][x] = counter;	//The distance of that point is what the counter happened to be
						}
						if (Visited2[y + 1][x] == false) {//Checks south
							qx.enqueue(x);
							qy.enqueue(y + 1);
							Visited2[y + 1][x] = true;
							Distance[y + 1][x] = counter;
						}
						if (Visited2[y][x + 1] == false) {//Checks east
							qx.enqueue(x + 1);
							qy.enqueue(y);
							Visited2[y][x + 1] = true;
							Distance[y][x + 1] = counter;
						}
						if (Visited2[y][x - 1] == false) {//Checks west
							qx.enqueue(x - 1);
							qy.enqueue(y);
							Visited2[y][x - 1] = true;
							Distance[y][x - 1] = counter;
						}
					}
				} while((qy.peek() != null || qx.peek() != null) && Distance[qy.peek()][qx.peek()] == counter-1); //Other end of do/while loop
			}
		}
		System.out.println(BFSToString()); // Prints for console debugging
	}

	//The next two methods get the maze arrays
	public String[][] getDFS() {
		return Maze;
	}
	
	public String[][]getBFS(){
		return Maze2;
	}
	
	//This method is use to inform the user if there is no solution (check the window title)
	public boolean hasSolution() {
		return solution;
	}
	
	//For debugging
	public String getStart() {
		String StartCoord = startx + " " + starty;
		return StartCoord;
	}

	//For debugging
	public String getFinish() {
		String FinishCoord = finishx + " " + finishy;
		return FinishCoord;
	}

	//Prints the maze, is called during DFS searches
	public String DFSToString() {
		String Out = new String("");

		for (int i = 0; i < Maze.length; i++) {
			for (int j = 0; j < Maze[i].length; j++) {
				Out = Out + " " + Maze[i][j];
			}
			Out = Out + "\n";
		}
		return Out;
	}
	
	//Prints the maze, is called during BFS searches
	public String BFSToString() {
		String Out = new String("");

		for (int i = 0; i < Maze2.length; i++) {
			for (int j = 0; j < Maze2[i].length; j++) {
				Out = Out + " " + Maze2[i][j];
			}
			Out = Out + "\n";
		}
		return Out;
	}

	//Prints the visited 2d array for debugging purposes
	public String VisitedString() {
		String Out = new String("");
		int num;

		for (int i = 0; i < Visited.length; i++) {
			for (int j = 0; j < Visited[i].length; j++) {
				num = Visited[i][j] ? 1 : 0;
				Out = Out + " " + num;
			}
			Out = Out + "\n";
		}
		return Out;
	}

	//Prints the distance 2D array for debugging purposes
	public String DistanceString() {
		String Out = new String("");

		for (int i = 0; i < Distance.length; i++) {
			for (int j = 0; j < Distance[i].length; j++) {
				Out = Out + " " + Distance[i][j];
			}
			Out = Out + "\n";
		}
		return Out;
	}
}
