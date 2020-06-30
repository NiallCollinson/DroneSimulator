/**
 * 
 */


import java.util.ArrayList;
import javax.swing.JFileChooser;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;


/**
 * @author niallcollinson
 * Sets up drone simulater and displays GUI
 */
public class DroneInterface extends Application {
	private MyCanvas mc;										// canvas for drones to move around in 
	private AnimationTimer timer;								// timer used for animation
	private VBox rtPane;										// vertical box for putting info
	private DroneArena arena;									// sets up what drones are in the canvas and their physics
	private MenuManager menuManager;						

	
	
	
	
	 /**
	  * set up the mouse event - when mouse pressed, put user drone there
	  * @param canvas
	  */
	void setMouseEvents (Canvas canvas) {
	       canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 		// for MOUSE PRESSED event
	    	       new EventHandler<MouseEvent>() {
	    	           @Override
	    	           public void handle(MouseEvent e) {
	    	        	   	arena.setUser(e.getX(), e.getY());	// set user drone at new position 
	  		            	drawWorld();							// redraw world
	  		            	drawStatus();
	    	           }
	    	       });
	}
	
	
	/**
	 * scans the keyboard for input and if valid moves the user drone
	 * @param e is a keyboard event
	 */
	void setKeyboardEvents (KeyEvent e) {
		
		arena.setUserKeyboard(e);				// calls keyboard switch function 
		
		drawWorld();							// redraw world
      	drawStatus();
		      
	}
	

	
	
	
	
	
	
	/**
	 * sets up buttons for the bottom of the game screen 
	 * @return hbox with buttons inside for game simulation
	 */
	private HBox setButtons() {
	    Button btnStart = new Button("Start");					// create button for starting
	    btnStart.setOnAction(new EventHandler<ActionEvent>() {	// now define event when it is pressed
	        @Override
	        public void handle(ActionEvent event) {
	        	timer.start();									// its action is to start the timer
	       }
	    });

	    Button btnStop = new Button("Pause");					// now button for stop
	    btnStop.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	           	timer.stop();									// and its action to stop the timer
	       }
	    });
	    
	    Button btnClear = new Button("Clear Canvas");			// button clears canvas
	    btnClear.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	           	arena.clearCanvas();				 			// calls function that deletes all elemetns in the canvas
	       }
	    });

	    Button btnAdd = new Button("Another Game Drone");		// button that adds a game drone
	    btnAdd.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	           	arena.addDrone();								// calls function that adds drone
	           	drawWorld();									// redraw world
	       }
	    });
	    
	    Button btnAddBlocker = new Button("Another Blocker Drone");	// button that adds a blocker drone
	    btnAddBlocker.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent event) {
	    		arena.addBlockerDrone();						// calls function that adds blocker drone
	    		drawWorld();									// redraw world
	    	}
	    });
	    
	    Button btnAddPredator = new Button("Another Predator Drone"); //button that adds a predator drone
	    btnAddPredator.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override 
	    	public void handle(ActionEvent event) {
	    		arena.addPredatorDrone();						// calls function that adds predator drone 
	    		drawWorld();									// redraws world
	    	}
	    });
	    
	    Button btnAddKiller = new Button("Another Killer Drone"); // button that adds a killer drone
	    btnAddKiller.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override 
	    	public void handle(ActionEvent event) {
	    		arena.addKillerDrone();							// calls function that adds killer drone
	    		drawWorld();									// redraws world
	    	}
	    });
	    
	    Button btnAddFearful = new Button("Another Fearful Drone");	//function that adds a fearful drone 
	    btnAddFearful.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override 
	    	public void handle(ActionEvent event) {
	    		arena.addFearfulDrone();						// calls function that adds fearful drone
	    		drawWorld();									// redraws world 
	    	}
	    });
	    
	    
	    Button btnAddUser = new Button("Another User Drone"); 	// adds another user drone
	    btnAddUser.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override 
	    	public void handle(ActionEvent event) {
	    		arena.newUserDrone();							// creates new user drone
	    		drawWorld();									// redraws world
	    	}
	    });
	    
	    Button btnTargetDrone = new Button("Another Target Drone");	//add a new target drone
	    btnTargetDrone.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override 
	    	public void handle(ActionEvent event) {
	    		arena.addTargetDrone();							// calls function that adds another target drone
	    		drawWorld();									// redraws world
	    	}
	    });
	    
	    
	    
	    
	    return new HBox(new Label("Run: "), btnStart, btnStop, 	    
	    		new Label("Add: "), btnAdd, btnAddBlocker, 
	    		btnAddPredator, btnAddKiller,
	    		btnAddFearful
	    		,new Label("New Game: "), btnAddUser, 
	    		btnTargetDrone, btnClear);						// now add these buttons + labels to a HBox 
	}

	
	
	
	
	
	
	
	
	/**
	 * Show the score .. by writing it at position x,y
	 * @param x displays x position
	 * @param y displays y position 
	 * @param score dipslays score on screen 
	 */
	public void showScore (double x, double y, int score) {
		mc.showText(x, y, Integer.toString(score));
	}
	
	
	
	
	
	
	
	/** 
	 * draw the world with drone in it
	 */
	public void drawWorld () {
	 	mc.clearCanvas();						//clears the drone arena
	 	arena.drawArena(mc);
	}
	
	
	
	
	
	
	
	
	/**
	 * show where drone is, in pane on right
	 */
	public void drawStatus() {
		rtPane.getChildren().clear();					// clear rtpane
		ArrayList<String> allBs = arena.describeAll();
		for (String s : allBs) {
			Label l = new Label(s); 		// turn description into a label
			rtPane.getChildren().add(l);	// add label	
		}	
	
	}
	

	

	
	
	
	
	


	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	/**
	 * method acts as a main function and controls GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// TODO Auto-generated method stub
		primaryStage.setTitle("Drone Simulator ek000636");  			// setting title 
	    BorderPane bp = new BorderPane();								// creates border for the stage
	    bp.setPadding(new Insets(10, 20, 10, 20));						// gives the border some padding 
	

  	    
	    
	    Group root = new Group();										// create group with canvas
	    Canvas canvas = new Canvas( 1000, 700);							// sets up canvas and size of canvas
	    root.getChildren().add( canvas );								// adds canvas to root group 
	    bp.setLeft(root);												// load canvas to left area
	
	    mc = new MyCanvas(canvas.getGraphicsContext2D(), 1000, 700);	// adds 2d graphics and size to my canvas

	    setMouseEvents(canvas);											// set up mouse events

	    
	    arena = new DroneArena(1000, 700);								// set up arena
	    drawWorld();
	    
	    timer = new AnimationTimer() {									// set up timer
	        public void handle(long currentNanoTime) {					// and its action when on
	        		arena.checkDrones();									// check the angle of all drones
		            arena.adjustDrones();								// move all drones
		            drawWorld();										// redraw the world
		            drawStatus();										// indicate where drones are
	        }
	    };

	    rtPane = new VBox();											// set vBox on right to list items
		rtPane.setAlignment(Pos.TOP_LEFT);								// set alignment
		rtPane.setPadding(new Insets(5, 75, 75, 5));					// padding
 		bp.setRight(rtPane);											// add rtPane to borderpane right
		  
	    bp.setBottom(setButtons());										// set bottom pane with buttons

	   
	    
	    
	    StackPane pane = new StackPane();
	    
	    
	    BackgroundImage gameBackground = new BackgroundImage(new 		// sets background image for the game
	    		Image("images/gameBackground.png"), 
      			BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
      			null, BackgroundSize.DEFAULT);
          pane.setBackground(new Background(gameBackground)); 			// adds image to stack pane
	    

        
          
          

          MenuBar menuBar = new MenuBar();						// create main menu
          
          Menu mFile = new Menu("File");							// add File main menu
          MenuItem mExit = new MenuItem("Exit");					// whose sub menu has Exit
          mExit.setOnAction(new EventHandler<ActionEvent>() {
        	  public void handle(ActionEvent t) {					// action on exit is
        		  timer.stop();									// stop timer
        		  System.exit(0);									// exit program
        	  }
          });
          
          MenuItem mSave = new MenuItem("Save File");
          mSave.setOnAction(new EventHandler<ActionEvent>() {
        	  public void handle(ActionEvent t) {	
        		  
        		  try {
        				JFileChooser choose = new JFileChooser(); //create file chooser object
        		
        				int returnVal = choose.showSaveDialog(null);        			
        				if(returnVal == JFileChooser.APPROVE_OPTION) {
        					File selFile = choose.getSelectedFile(); //get file and dir
        					File currDir = choose.getCurrentDirectory();
        					
        					
        					System.out.print("You chose to save into file: " + selFile.getName() + "in the dir " + currDir.getName());
        					
        					FileOutputStream output = new FileOutputStream(selFile);
        					ObjectOutputStream objOut = new ObjectOutputStream(output);
        					
        					
        					objOut.writeObject(arena); //prepare arena
        					
        					
        					objOut.close(); //close file and object 
        					
        					output.close();
        					
        				}
        				
        			}catch(IOException ex) {
        				System.out.println(ex.getMessage());
        			}
        	  }
          });

          
          
          MenuItem mLoad = new MenuItem("Load File");
          
          mLoad.setOnAction(new EventHandler<ActionEvent>() {
        	  public void handle(ActionEvent t) {	
        		  
        		  try {
        				JFileChooser choose = new JFileChooser(); //create file chooser object
        				
        				int returnVal = choose.showOpenDialog(null);
        				
        				if(returnVal == JFileChooser.APPROVE_OPTION) {
        					File selFile = choose.getSelectedFile(); //get file and dir
        					File currDir = choose.getCurrentDirectory();
        					
        					
        					System.out.print("You chose to save into file: " + selFile.getName());
        					
        					FileInputStream input = new FileInputStream(selFile); // create file and object output streams
        					ObjectInputStream objIn = new ObjectInputStream(input);
        					
        					arena = (DroneArena)objIn.readObject();
        					
        					
        					input.close();
        					objIn.close(); //close file and object
        					
        					drawStatus();
        					arena.drawArena(mc);
        					
        					
        					
        				}
        				
        			}catch(IOException ex) {}
        	  }
          });

          
          
          mFile.getItems().addAll(mExit, mSave, mLoad);
          menuBar.getMenus().add(mFile);
          
          bp.setTop(menuBar);
  	  
          
  	    Scene scene = new Scene(pane, 1500, 800);						// creates new scene with stack pane in it
  	    
  	    
  	    
  	    
  	    
  	    
  	
  	    pane.getChildren().addAll(bp);							// adds backbox and border pane to stackpane
	    
	    Scene startScreen; 												// set's start screen
	    Scene login;													// set's login screen 
	    
        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());
        

       
         scene.setOnKeyPressed(e ->{
        	 
        	 
        	setKeyboardEvents(e);
         });
    	 
    	
        
        
       StackPane loadScreen = new StackPane();							// creates new stackpane 
       
       
       
       
       
       BackgroundImage loadBackground = new BackgroundImage(new Image 	// creates new background image 
    		   ("images/spaceBackground.png"),
   			BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, null,		
   			BackgroundSize.DEFAULT);
       loadScreen.setBackground(new Background(loadBackground)); 		// adds image to load background 
       
       
       FileInputStream menuInput = new FileInputStream					// creates new image and finds resource 
    		   ("src/images/Main-Menu.png");
       Image menuHeader = new Image(menuInput);							
       ImageView menuImageView = new ImageView(menuHeader);				// adds image to imageview 
        
		
		
		Button startButton = new Button("START");						// starts the simulation 
		startButton.setOnAction(e -> primaryStage.setScene(scene));
		
		
	
		
	
		
		
		
		
		Button helpButton = new Button("HELP");							// help function 
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override 
	    	public void handle(ActionEvent event) {
	    		
	    		menuManager = new MenuManager();
	    	    menuManager.helpManager();
	    		
	    	}
	    });
		
		
		
		
		
		
		
		Button exitButton = new Button("EXIT");							// exit game
		 exitButton.setOnAction(new EventHandler<ActionEvent>() {
		    	@Override 
		    	public void handle(ActionEvent event) {
		    		
		    		primaryStage.close();
		    		
		    	}
		    });
		  
		    	
		     	
		    	
		
    	VBox layout2 = new VBox(20);									// declares vbox container
    	
    	
    	layout2.setAlignment(Pos.CENTER);
    	layout2.getChildren().addAll(menuImageView, startButton, 		// adds buttons 
    			  helpButton, exitButton);
    	
    	loadScreen.getChildren().addAll(layout2);						// adds vbox to stackpane			
    		
    	startScreen = new Scene(loadScreen, 1500, 1500);				// adds pane to scene
    	
    	
    	Button button1= new Button("Start");							// initial start button 
    	button1.setMinSize(150, 100);
    	button1.setOnAction(e -> primaryStage.setScene(startScreen));   
    	
    	
    	TextField username = new TextField();							// text field for username 
    	username.setMaxWidth(200);										
    	
    	FileInputStream headerInput = new FileInputStream				// adds image for menu hero image
    			("src/images/Drone-Simulator.png");
    	Image header = new Image(headerInput);
    	ImageView headerImageView = new ImageView(header);
    	
    	
    	StackPane layout1 = new StackPane(); 
    	
    	VBox beginnerVbox = new VBox(8);
    	
    	beginnerVbox.setAlignment(Pos.CENTER);
    	beginnerVbox.getChildren().addAll(headerImageView,				// adds nodes to vbox
    			username, button1);

    	
 
    	BackgroundImage background = new BackgroundImage(new			// sets background image
    			Image("images/8bitLoginBackground.png"),
    			BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, null,
    			BackgroundSize.DEFAULT);
    	layout1.setBackground(new Background(background)); 				// adds background image to pane
    	
    	layout1.setAlignment(Pos.CENTER);
    	layout1.getChildren().addAll( beginnerVbox);					// adds vbox to pane
    	login = new Scene(layout1, 1500, 1500);							// adds pane to scene

        primaryStage.setScene(login);									// shows initial screen
        primaryStage.show();											// 	
	  
        

	}

	
	
	
	
	
	
	
	
	/**
	 * launches application 
	 * @param args
	 */
	public static void main(String[] args) {
	    Application.launch(args);			// launch the GUI
	}

}
