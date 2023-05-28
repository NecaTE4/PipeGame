//Selin Aydýn 150120061
//Necati Koçak 150120053
// CLASSIN AMACI
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Scanner;

import javafx.animation.PathTransition;
import javafx.application.Application;

import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;


import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;

enum Position {  // It is defined to understand the position of the previous tile. We used them inside the gameEnded method.
	TOP, LEFT, RIGHT, DOWN
};

public class GameEnterenceScreen extends Application { 
	ArrayList<Button> buttonList = new ArrayList<>(); //•	The data field buttonList whose data type is ArrayList<Button> is created for holding all
	//the Button objects in the entrance screen of the game(Date field mainScene). 
	ArrayList<String> moveType = new ArrayList<>();
	int selectedRow, selectedColumn = 0; //These variables are created for row and column indexes of the initial tile where the dragging starts. We set them equal to zero.
	int numberOfUnlockedLevels; // We defined the variable for locking the buttons in a right way.
	static int countOfSteps =0;  // We assigned our number of steps as static and integer and set them equal to zero.
	Label stepCounter;  // We have assigned a label to show our number of steps.
	Background bg;  // We have defined the names of the variables of the backgrounds we use in our game.
	Background bg2;  // We have defined the names of the variables of the backgrounds we use in our buttons.
	Rectangle[][] boardGrid ; // boardGrid whose type is a two-dimensional array of Rectangle class is to construct the background
	//of the board which is a 4x4 square matrix. 
	GridPane boardPane1; //boardPane1 whose type is GridPane is the current pane of the level.
	Properties[][] board1; //board1 whose data type is a two-dimensional array of Properties class is to be able 
	//to access all the tiles in the game board separately.
	Scene mainScene ; //mainScene whose type is Scene is created for the entrance screen of the game.
	Circle ball = new Circle(20, new ImagePattern(new Image("ball.png"))); // We describe the ball in our game and enter its picture.
	
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
//•	We have standard start method whose parameters is window type Stage and return type is void and accessibility is public.
		
		FlowPane pane = new FlowPane(30, 30); //we created pane of the mainScene.
		pane.setPadding(new Insets(50, 50, 250, 100)); //We arranged properties of the pane.

		Text title = new Text("Welcome to PipePuzzle Game");  // We wrote the opening message in our main menu
		title.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50)); //Font adjustments were made for our opening message.
		title.setFill(Color.DARKCYAN); //We fill the message with the color of dark cyan.
		pane.getChildren().add(title); // We added it to pane.

		BorderPane root = new BorderPane(); //We create a borderPane named root to obtain a page layout.
		root.setCenter(title); //We set message to the center.
		root.setBottom(pane); //We set pane to the bottom.

		 mainScene = new Scene(root, 1280, 720); // We created our main scene.

		primaryStage.setTitle("Pipe Game"); // Added a title to the stage.
		primaryStage.setScene(mainScene); // We set the mainScene we created to Stage.
		Image image1 = new Image("bg..jpg"); //We add the image that we will use as the background.
		BackgroundImage bgfill = new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true)); //We assign our background to the bgfill variable.
		bg = new Background(bgfill); // We assign bgfill to the bg variable we created.
		root.setBackground(bg); // We set bg as background of root.
		
		Image image2 = new Image("buttonbg.jpg"); //We add the image that we will use as the button's background.
		BackgroundImage bgfill2 = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, new BackgroundSize(100, 45, true, true, true, true)); //We assign our background to the bgfill2 variable.
		bg2 = new Background(bgfill2); // We assign bgfill2 to the bg2 variable we created.
		
		for (int i = 0; i < 6; i++) { // We create 6 level buttons with the for loop.
			buttonList.add(new Button("Level " + (i + 1))); // We write the name of the buttons.
			pane.getChildren().add(buttonList.get(i)); //We added buttons to the pane.
			buttonList.get(i).setPrefSize(150, 50); // We adjust the size of the buttons.
			buttonList.get(i).setBackground(bg2); // We set the background of the buttons.

		}
		
		// --------------------------------------------------------------------------------------------------------------------------------------
		buttonList.get(0).setOnAction(e -> { // We set action to button of level1.
			try {
				MapLoad(1,primaryStage,mainScene); // We invoked mapLoad method for level1. 
			} catch (FileNotFoundException e1) { // to catch non-existing file.
				
			}
			
		});		
		
		// --------------------------------------------------------------------------------------------------------------------------------------
		buttonList.get(1).setOnAction(e -> { // We set action to button of level2.
			if (numberOfUnlockedLevels >= 1) { // We checked if the previous levels is completed or not.
				try {
					primaryStage.setScene(MapLoad(2, primaryStage, mainScene)); // We invoked mapLoad method for level2.
				} catch (FileNotFoundException e1) {  // to catch non-existing file.
					
					e1.printStackTrace();
				}
			}
		});
		
		// --------------------------------------------------------------------------------------------------------------------------------------
		buttonList.get(2).setOnAction(e -> { // We set action to button of level3.
			if (numberOfUnlockedLevels >= 2) { // We checked if the previous levels is completed or not.
				try {
					primaryStage.setScene(MapLoad(3, primaryStage, mainScene)); // We invoked mapLoad method for level3.
				} catch (FileNotFoundException e1) {  // to catch non-existing file.
					
					e1.printStackTrace();
				}
			}
		});
		// --------------------------------------------------------------------------------------------------------------------------------------
		buttonList.get(3).setOnAction(e -> {  // We set action to button of level4.
			if (numberOfUnlockedLevels >= 3) { // We checked if the previous levels is completed or not.
				try {
					primaryStage.setScene(MapLoad(4, primaryStage, mainScene)); // We invoked mapLoad method for level4.
				} catch (FileNotFoundException e1) {  // to catch non-existing file.
					
					e1.printStackTrace();
				}
			}
		});
		// --------------------------------------------------------------------------------------------------------------------------------------
		buttonList.get(4).setOnAction(e -> {  // We set action to button of level5.
			if (numberOfUnlockedLevels >= 4) { // We checked if the previous levels is completed or not.
				try {
					primaryStage.setScene(MapLoad(5, primaryStage, mainScene)); // We invoked mapLoad method for level5.
				} catch (FileNotFoundException e1) {  // to catch non-existing file.
					
					e1.printStackTrace();
				}
			}
		});
		// --------------------------------------------------------------------------------------------------------------------------------------
		buttonList.get(5).setOnAction(e -> {  // We set action to button of level6.
			if (numberOfUnlockedLevels >= 5) { // We checked if the previous levels is completed or not.
				try {
					primaryStage.setScene(MapLoad(6, primaryStage, mainScene)); // We invoked mapLoad method for level6.
				} catch (FileNotFoundException e1) {  // to catch non-existing file.
					
					e1.printStackTrace();
				}
			}
		});
		
		// ----------------------------------------------------------------------------------------------------------------------------------------------
		primaryStage.show(); //to close and show primaryStage.
	}
	  
	
	public Scene MapLoad(int levelOrder,Stage primaryStage,Scene mainScene) throws FileNotFoundException { // We wrote our method that loads the game's maps.
		
		boardGrid = new Rectangle[4][4]; // We created boardGrid we declared before.
		boardPane1 = map(boardGrid); // We created boardPane1 we declared before.
		boardPane1.setBackground(bg); //We set the background of the boardPane1.
		board1 = generateBoard("src/Level "+levelOrder+".txt", // We invoked generateBoard method and assign the object that the method returns to board1. 
				boardPane1, boardGrid);
	    GridPane.setMargin(ball, new Insets(25));
		boardPane1.getChildren().add(ball); //We added ball to boardPane1.
		//We arranged the ball's coordinates.
		
	    Button buttonNextLevel1 = new Button("Next Level");  //We define our NextLevel button and write its name.
		buttonNextLevel1.setPrefSize(100, 40); // We adjust the size of the Next Level button.
		stepCounter = new Label(); // We create the stepCounter label that will count the steps.
		stepCounter.setScaleX(2); // We adjust the size of stepCounter according to X.
		stepCounter.setScaleY(2); // We adjust the size of stepCounter according to Y.
		stepCounter.setTranslateX(178); // We set the position of stepCounter relative to X.
		stepCounter.setTranslateY(375); // We set the position of stepCounter relative to Y.
		stepCounter.setBackground(bg2); //We set the background of step counter.
		GridPane.setConstraints(buttonNextLevel1, 3, 10); //We set the column and row index of the NextLevel.
		boardPane1.getChildren().addAll(buttonNextLevel1,stepCounter); //We add nextLevel button and step counter to boardPane1.
		buttonNextLevel1.setBackground(bg2);  //We set the background of NextLevel1 button.
	    Button buttonMainMenu1 = new Button("Main Menu");  //We define our MainMenu button and write its name.
		buttonMainMenu1.setPrefSize(100, 40);   // We adjust the size of the MainMenu button.
		buttonMainMenu1.setBackground(bg2);  //We set the background of MainMenu1.
		GridPane.setConstraints(buttonMainMenu1, 0, 10);   //We set the column and row index of the MainMenu1.
		boardPane1.getChildren().add(buttonMainMenu1);  //We add MainMenu1 button and step counter to boardPane1.
		
		Scene scene1 = new Scene(boardPane1); //We created our level scene by adding pane to the scene.
		
		primaryStage.setScene(scene1); //We added scene to primaryStage.

		stepCounter.setText("Steps: "+countOfSteps); //We set the text of the counter.
		buttonMainMenu1.setOnAction(e -> { 
			countOfSteps = 0; //We set counter to zero when main menu button has pressed.
			stepCounter.setText("Steps: "+countOfSteps);
			primaryStage.setScene(mainScene); //We set mainScene when main menu button has pressed.
		});
			
		buttonNextLevel1.setOnAction(e -> { // We set action to next level button.
			if (gameEnded(board1)) {  //We checked whether game is ended or not by invoking gameEnded method.
				try {
					countOfSteps = 0; //We set counter to zero when next level button has pressed.
					stepCounter.setText("Steps: "+countOfSteps);
				//	animationBall();
					primaryStage.setScene(MapLoad(levelOrder+1, primaryStage, mainScene)); //We invoked mapLoad method to be able to show next level screen.
				} catch (FileNotFoundException e1) { //to catch non-existing files.	
					e1.printStackTrace();
				}
				numberOfUnlockedLevels = levelOrder; // We assign the value of levelOrder to numberOfUnlockedLevels.
			}
		});		
		
		return scene1; // The method returns Scene object.
	}
	
	public Properties[][] generateBoard(String path, GridPane boardPane, Rectangle[][] boardGrid)
			throws FileNotFoundException { //This method reads input files and fill the tiles with corresponding images.
		Properties[][] board = new Properties[4][4]; //We created a board array to hold properties.
		SettingPipe x = new SettingPipe(); //We created SettingPipe object to invoke setPipe method.

		File file = new File(path); //We created file object.
		Scanner input = new Scanner(file); //We added file object to scanner object.

		String[] splitedList = new String[(int) file.length()]; //We created an array of String.
		String line = "";
		while (input.hasNext()) {
			line = line + input.nextLine() + ","; //We let write words in input file side by side.
			splitedList = line.split(","); //We separated them by comma.

		}

		int y = 0; 
		try {
			for (int i = 0; i < 16; i++) {
				boardGrid[i % 4][i / 4].setFill(new ImagePattern(x.setPipe(splitedList, y))); //We fill rectangles with corresponding
				//images by using setPipe method
				board[i % 4][i / 4] = new Properties(splitedList[y], splitedList[y + 1], splitedList[y + 2]);
                //We assign properties to tiles.
				setDraggable(boardGrid[i % 4][i / 4], boardPane); //We invoke setDraggable and setSlot methods.
				setSlot(boardGrid[i % 4][i / 4], boardPane, board, boardGrid);

				y = y + 4; 
			}
		}  

		catch (Exception ex) {
		}
		return board;
	}

	public GridPane map(Rectangle[][] boardGrid) throws FileNotFoundException {
       //This method is for generating background of level screens which is 4x4 square matrix.
		GridPane boardPane = new GridPane(); //We created boardPane.

		boardPane.setPadding(new Insets(100, 200, 100, 200)); //We arranged it.
		boardPane.setVgap(0);
		boardPane.setHgap(0);

		int y = 0;
		try {
			for (int i = 0; i < 16; i++) {

				boardGrid[i % 4][i / 4] = new Rectangle(100, 100, Color.WHITE); //We created two dimensional Rectangle array.
				boardGrid[i % 4][i / 4].setStroke(Color.BLACK);
				boardPane.add(boardGrid[i % 4][i / 4], i % 4, i / 4); //We added array to the boardPane.

				y = y + 4;
			}

		} catch (Exception ex) {

		}

		return boardPane;

	}

	public void makeMove(int x0, int y0, int x1, int y1, Properties[][] board) {
		Properties temp = board[y0][x0]; //We created temp object to keep properties of first clicked tile.
		board[y0][x0] = board[y1][x1]; //We changed properties of tiles where the dragging occurs.
		board[y1][x1] = temp; 

	}

	private void setDraggable(Rectangle r, GridPane boardPane) {
		r.setOnDragDetected((e -> { 
			selectedColumn = boardPane.getColumnIndex(r); //We got the column index of first clicked tile.
			selectedRow = boardPane.getRowIndex(r); //We got the row index of first clicked tile.

			r.startFullDrag(); //We started dragging.
			e.consume();
		}));
	}

	private void setSlot(Rectangle r, GridPane boardPane, Properties[][] board, Rectangle[][] boardGrid) {
		r.setOnMouseDragReleased(e -> {
			int y1 = (int) boardPane.getColumnIndex(r); //We got the column index of last clicked tile.
			int x1 = (int) boardPane.getRowIndex(r); //We got the row index of last clicked tile.

			int x0 = selectedRow; //Assigning column and row index values to the variables x0 and y0.
			int y0 = selectedColumn;

			if (isLegal(x0, y0, x1, y1, board) == true) { //We invoked isLegal method.
				makeMove(x0, y0, x1, y1, board); // invoking makeMove method.
				exchangeRectangles(x0, y0, x1, y1, boardPane, boardGrid); // invoking exchangeRectangle method.
				countOfSteps++; //We increased move counter by one.
				stepCounter.setText("Steps: "+countOfSteps); //We added counter.
			}
			e.consume();
		});
	}

	public boolean isLegal(int x0, int y0, int x1, int y1, Properties[][] board) {
		if (board[y0][x0].featureOfCells.equals("Starter") || board[y0][x0].featureOfCells.equals("End")
				|| board[y0][x0].featureOfCells.equals("PipeStatic")) {
			//We checked some properties of tiles to understand whether they are movable or not.
			return false;
		} else {
			if ((board[y1][x1].styleOfCells.equals("Free") )
					&& (Math.abs(y1 - y0) == 0 && Math.abs(x1 - x0) == 1
							|| Math.abs(y1 - y0) == 1 && Math.abs(x1 - x0) == 0)) {
				//We checked the position of tiles(they should be side by side).
				return true;
			} else {
				return false;
			}
		}
	}

	public void exchangeRectangles(int x0, int y0, int x1, int y1, GridPane boardPane, Rectangle[][] boardGrid) {

		boardPane.getChildren().remove(boardGrid[y0][x0]); //removing first clicked tile.
		boardPane.getChildren().remove(boardGrid[y1][x1]); //removing last clicked tile.

		boardPane.add(boardGrid[y0][x0], y1, x1); //adding first clicked tile to the row and column index of last clicked tile.
		boardPane.add(boardGrid[y1][x1], y0, x0);  //adding last clicked tile to the row and column index of first clicked tile.

		Rectangle rect = boardGrid[y0][x0]; //changing rectangles.
		boardGrid[y0][x0] = boardGrid[y1][x1];
		boardGrid[y1][x1] = rect;
	}

	public boolean gameEnded(Properties[][] board) {
		//This method checks whether level is ended or not.
		int row = 0, col = 0; //keeps the row and column values of initial tile.
		int x = 1;
		int y = 2;
		int row1, col1; //keeps the row and column values of last tile.

		boolean result = true;
       //Nested for loops for searching starting tile.
		for (row = 0; row <= 3; row++) {
			for (col = 0; col <= 3; col++) {
				if (board[col][row].featureOfCells.equals("Starter")) {

					x = col; //column value of the starting tile
					y = row; //row value of the starting tile
				}

			}

		}
		col = x;
		row = y;
      
		if (board[col][row].styleOfCells.equals("Vertical")) { //checks starter tile is vertical or not. If 
			//it is vertical, increases row value by one while column value remains the same.

			col1 = col;
			row1 = row + 1;
			moveType.add("Down");

		} else {

			col1 = col + 1;
			row1 = row;
			moveType.add("Right");
		}
		int a = 10;

		while (a >= 0) {
			a--;
			
			Position positionEntered; //We declared positionEntered variable whose data type is Position.
			int deltaX = col1 - col; // deltaX variable keeps the difference of column values.
			int deltaY = row1 - row; // deltaY variable keeps the difference of row values.
			
			if (deltaX != 0) {
				if (deltaX == 1) {
					//checks the situation where col1 is greater than col and assign RIGHT to the positionEntered 
					positionEntered = Position.RIGHT;
				} else { //checks the situation where col is greater than col1 and assign LEFT to the positionEntered 
					
					positionEntered = Position.LEFT;
				}
			} else {
				if (deltaY == 1) {
					////checks the situation where row1 is greater than row and assign DOWN to the positionEntered 
					positionEntered = Position.DOWN;
				} else {
					//checks the situation where row is greater than row1 and assign TOP to the positionEntered
					positionEntered = Position.TOP;
				}

			}
			
			Properties currentCell = board[col1][row1]; //We defined currentCell object whose type is Properties to keep properties of current tile.
			
			col = col1;
			row = row1;

			if (currentCell.featureOfCells.equals("Pipe") || currentCell.featureOfCells.equals("PipeStatic")) {
				//checking future of currentCell 
				if (currentCell.styleOfCells.equals("00")) {
					//checking type of current cell. In this case we checked TOP and LEFT position.
					if (positionEntered == Position.TOP) {
						//If it is entered from top, increase the row value by one.
						row1 = row + 1;
						col1 = col;
						moveType.add("Down");
					} else if (positionEntered == Position.LEFT) {
						//If it is entered from left, increase the col value by one.
						row1 = row;
						col1 = col + 1;
						moveType.add("Right");
					}

				}
              
				else if (currentCell.styleOfCells.equals("01")) {
					//checking type of current cell. In this case we checked DOWN and RIGHT position.
					if (positionEntered == Position.RIGHT) {
						//If it is entered from right, decrease the row value by one.
						row1 = row - 1;
						col1 = col;
						moveType.add("Up");
					} else if (positionEntered == Position.DOWN) {
						//If it is entered from down, increase the col value by one.
						row1 = row;
						col1 = col + 1;
						moveType.add("Right");
					}

				}
 
				else if (currentCell.styleOfCells.equals("10")) {
					//checking type of current cell. In this case we checked DOWN and LEFT position.
					if (positionEntered == Position.DOWN) {
						//If it is entered from down, decrease the row value by one.
						row1 = row - 1;
						col1 = col;
						moveType.add("Up");
					} else if (positionEntered == Position.LEFT) {
						//If it is entered from left, increase the col value by one.
						row1 = row;
						col1 = col + 1;
						moveType.add("Right");
					}
				} else if (currentCell.styleOfCells.equals("11")) {
					//checking type of current cell. In this case we checked DOWN and RIGHT position.
					if (positionEntered == Position.DOWN) {
						//If it is entered from down, decrease the row value by one.
						row1 = row - 1;
						col1 = col;
						moveType.add("Up");
					} else if (positionEntered == Position.RIGHT) {
						//If it is entered from top, decrease the col value by one.
						row1 = row;
						col1 = col - 1;
						moveType.add("Left");

					}
				} else if (currentCell.styleOfCells.equals("Horizontal")) {
					//If type of the pipe is horizontal, search for LEFT and RIGHT positions.
					if (positionEntered == Position.LEFT) {
						//If it is entered from left, increase the col value by one.
						col1 = col + 1;
						moveType.add("Right");
					} else if (positionEntered == Position.RIGHT) {
						//If it is entered from right, decrease the col value by one.
						col1 = col - 1;
						moveType.add("Left");
					}
				} else if (currentCell.styleOfCells.equals("Vertical")) {
                    //If type of the pipe is vertical, search for TOP and DOWN positions.
					if (positionEntered == Position.TOP) {
						//If it is entered from top, decrease the row value by one.
						row1 = row - 1;
						moveType.add("Up");
					} else if (positionEntered == Position.DOWN) {
						//If it is entered from down, increase the row value by one.
						row1 = row + 1;
						moveType.add("Down");
					}
				}

			}

			else if (currentCell.featureOfCells.equals("End")) {
				//Lastly, we searched end pipe.
				if (positionEntered == Position.LEFT && currentCell.styleOfCells.equals("Horizontal")) {
				//If end pipe is horizontal and previous tile entered from left, game is over.
					break;
				} else if (positionEntered == Position.TOP && currentCell.styleOfCells.equals("Vertical")) {
					//If end pipe is vertical and previous tile entered from top, game is over.
					break;
				} else {
                   //otherwise, return false
					result = false;
					break;
				}
			} else {
				// if none of if-else statements is executed, return result as false
				result = false;
				break;
			}
		}

animationBall();
		return result; //return result

	}
	
	public void animationBall() {
	     
		Path path = new Path(); 
			
		path.getElements().add(new MoveTo(ball.getCenterX(),ball.getCenterY())); 
	 	 path.getElements().add(new ClosePath());
	    

	      for (int i = 0; i < moveType.size(); i++) {
	 		 if (moveType.get(i).equals("Up")) {
	 		
	 		      path.getElements().add(new VLineTo(ball.getCenterY() -100)); 
	 		     
	 		      ball.setCenterX(ball.getCenterX());
	 		      ball.setCenterY(ball.getCenterY() -100);
	 		

	 	   }else if (moveType.get(i).equals("Down")) {
	 		 
	 		      path.getElements().add(new VLineTo(ball.getCenterY() +100)); 
	 		 
	 		      ball.setCenterX(ball.getCenterX());
	 		      ball.setCenterY(ball.getCenterY()+100);
	 		     

	 	   }else if (moveType.get(i).equals("Right")) {
	 		 
	 		      path.getElements().add(new HLineTo(ball.getCenterX() +100)); 
	 		   
	 		      ball.setCenterX(ball.getCenterX()+100);
	 		      ball.setCenterY(ball.getCenterY());
	 		    

	 	   }else if (moveType.get(i).equals("Left")) {
	 		 
	 		      path.getElements().add(new HLineTo(ball.getCenterX() - 100)); 
	 		     
	 		      ball.setCenterX(ball.getCenterX()-100);
	 		      ball.setCenterY(ball.getCenterY());
	 		    

	 	   }
	 	}   
	      PathTransition pathTransition = new PathTransition(); 
	      
	      pathTransition.setCycleCount(1);
	    
	      pathTransition.setNode(ball); 
	     
	      //Setting the duration of the path transition 
	      pathTransition.setDuration(Duration.millis(5000)); 
	      
	           //Setting the path 
	      pathTransition.setPath(path);  
	     
	      
	      //Playing the animation 
	      pathTransition.play(); 
	      
	         
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
