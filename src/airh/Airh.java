package airh;

import javafx.animation.FillTransition;
import static javafx.application.Application.launch;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Arc;
import javafx.scene.text.FontWeight;

public class Airh extends Application {
    
    @Override
    public void start(Stage primaryStage) {
  
         
        
        
        Group root = new Group();
        primaryStage.setTitle("AIR HOCKEY");
        setting set = new setting();
         Circle pan =new  Circle(0, 0, 15, Color.DARKORANGE);
             
        GridPane grid =new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        
        Scene  scene=new Scene(grid, 400, 400, Color.ALICEBLUE);
        grid.add(pan, 0, 0);
       


        
        
        
        Text scenetitle = new Text("AIR HOCKEY");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label NameLab = new Label("Your Name");
        grid.add(NameLab, 0, 1);
        TextField name = new TextField();
        grid.add(name, 1, 1);
        
        String baseMusic=new File("src\\airh\\oceana.mp3").toURI().toString();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(baseMusic));
        mediaPlayer.setCycleCount(Timeline.INDEFINITE);
        mediaPlayer.play();
        
        

        
        Button btn = new Button("               Start                 ");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 2);
        
        Button btn1 = new Button("Read Rules");
        HBox hbBtn1= new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 0, 2);
        
        Button btnMute = new Button("MUTE");
        HBox btnMute1= new HBox(10);
        btnMute1.setAlignment(Pos.BOTTOM_RIGHT);
        btnMute1.getChildren().add(btnMute);
        grid.add(btnMute1, 0, 3);
        
        
        primaryStage.setScene(scene); //add scene from setting method
        primaryStage.show();
        
        
        btn.setOnAction((event) -> {
            

            primaryStage.setScene(set.setting());
            mediaPlayer.stop();
            
        });
        
        btn1.setOnAction((event) -> {
            Label rule = new Label ("Hi \n Move Your Mouse \n On Your Pan And\nMove Your Pan ! \n Hit The Disc \n And Try To Goal \n No Time \n If Your Score \n Will Be 10 \n YOU WIN! \n CLICK On Start \n LET'S DO THIS!");
            grid.add(rule, 2, 0);

        });
        btnMute.setOnAction((event) -> {
             mediaPlayer.stop();
     
        });
 
        
        
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
class PanDiscView 
{
    private final double width , height , goalWidth, borderThickness, discRadius,panRadius;
    private double xdisc , ydisc , xpan1 , ypan1 , xpan2 ,ypan2 ;
    private int player1 , player2 ;
    Text tPlayeTow ,tPlayerOne ;
    FillTransition fTouch;
    MediaPlayer goalMedia ,hura;
    Timeline timer;
    
    public PanDiscView ( double width, double height, double goalWidth, double borderThickness, double discRadius, double panRadius )
    {
        this.width=width;
        this.height=height;
        this.goalWidth=goalWidth;
        this.borderThickness=borderThickness;
        this.discRadius=discRadius;
        this.panRadius=panRadius;
 
    }


    public void setPan1Position(double x, double y)
    {
        this.xpan1=x;
        this.ypan1=y;
        
    }
    public void setPan2Position(double x, double y)
    {
        this.xpan2=x;
        this.ypan2=y;
        
    }
        public void setDiscPosition(double x, double y)
    {
        
       
       this.xdisc=xpan1;
       this.ydisc=ypan1 ;
        
    }
    public void setPlayer1Score(int score)
    {
        this.player1=score;
    }
    public void setPlayer2Score(int score)
    {
        this.player2=score;
    }
    public double getplaye1score()
    {
        return player1;
    }

 
    public javafx.scene.Scene getScene()
    {
        

        

        
        // scene and draw  staff of table 
        Group root=new Group();
        Scene scene=new Scene(root, width, height, Color.ALICEBLUE);
        
        Rectangle rectBound= new Rectangle(0, 0, width, height);
        rectBound.setFill(Color.GREEN);
        
        Rectangle rectGround =new Rectangle(borderThickness, borderThickness, width - 2*borderThickness , height- 2*borderThickness );
        rectGround.setFill(Color.ALICEBLUE);
        
        // draw YOUR pans
        Circle panYou =new Circle(xpan1, ypan1 , panRadius);
        panYou.setFill(Color.LIGHTGREEN);
        // DRAW PC pan
        Circle panPC =new Circle(width/2, height/3 , panRadius);
        panPC.setFill(Color.LIGHTGREEN);
        

        //draw  your goal 

        Rectangle goalYou=new Rectangle((width/2) - (goalWidth/2), height - borderThickness, goalWidth, borderThickness);
        goalYou.setFill(Color.RED);
        FillTransition fGoalY = new   FillTransition();
        fGoalY.setShape(goalYou);
        fGoalY.setDuration(new Duration(2000));
        fGoalY.setToValue(Color.BLACK);
        fGoalY.setCycleCount(Timeline.INDEFINITE);
        fGoalY.setAutoReverse(true);
        fGoalY.play();
        
        //draw pc goal
        
        Rectangle goalPc=new Rectangle( (width/2) - (goalWidth/2), 0, goalWidth, borderThickness);
        goalPc.setFill(Color.RED);
        FillTransition fGoalP = new   FillTransition();
        fGoalP.setShape(goalPc);
        fGoalP.setDuration(new Duration(2000));
        fGoalP.setToValue(Color.BLACK);
        fGoalP.setCycleCount(Timeline.INDEFINITE);
        fGoalP.setAutoReverse(true);
        fGoalP.play();
               
        
        Arc a=new Arc((width/2), 0, goalWidth, goalWidth, 0, height);
        Arc a1=new Arc(width/2, 0, (goalWidth)- borderThickness , (goalWidth)- borderThickness, panRadius, width);
        a1.setFill(Color.ALICEBLUE);
        
        Arc b=new  Arc(width/2, height, goalWidth, goalWidth, 0, height);
        Arc b1=new  Arc(width/2, height, (goalWidth)- borderThickness, (goalWidth)- borderThickness, panRadius, width);
        b1.setFill(Color.ALICEBLUE);
        //draw disc
        
        Circle disc = new Circle(discRadius);
        disc.setFill(Color.DARKORANGE);
        disc.relocate(0, height/2);
        
        
       // draw center circle
       Circle center=new Circle(width/2, height/2, width/6, Color.GREEN);
       //auto chang color 

       
       // draw into circle of circle
       Circle intocenter =new Circle(width/2, height/2, (width/6) - borderThickness, Color.RED);
        FillTransition f4 = new   FillTransition();
        f4.setShape(intocenter);
        f4.setDuration(new Duration(2000));
        f4.setToValue(Color.BLACK);
        f4.setCycleCount(Timeline.INDEFINITE);
        f4.setAutoReverse(true);
        f4.play();
       
       Circle centerofcenter = new Circle(width/2, height/2, width/20, Color.GREEN);
       //AUTO CHANG COLOR FOR THIS

        
       
        
        //draw lines / RECTANGLE  half of game ground
        
        Rectangle line= new Rectangle(0,(height/2), width, borderThickness/2);
        line.setFill(Color.GREEN);

        
        //add text  couner for show  your score and pc score
        
        tPlayeTow = new Text();
        tPlayeTow.setX(2*borderThickness);
        tPlayeTow.setY((height/2)-borderThickness);
        tPlayeTow.setFont(Font.loadFont("file:src/airh/STENCIL.ttf",80 ));
        tPlayeTow.setText(""+player2);
        tPlayeTow.setFill(Color.CORNFLOWERBLUE);
        FillTransition fillTextOne= new FillTransition();
        fillTextOne.setShape(tPlayeTow);
        fillTextOne.setDuration(new Duration(500));
        fillTextOne.setToValue(Color.ALICEBLUE);
        fillTextOne.setCycleCount(Timeline.INDEFINITE);
        fillTextOne.setAutoReverse(true);
        fillTextOne.play();
        
        tPlayerOne = new Text();
        tPlayerOne.setX(2* borderThickness );
        tPlayerOne.setY((height/2) +9* borderThickness);
        tPlayerOne.setFont(Font.loadFont("file:src/airh/STENCIL.ttf",80 ));
        tPlayerOne.setText("" + player1 );
        tPlayerOne.setFill(Color.CORNFLOWERBLUE);
        FillTransition fillTextTwo= new FillTransition();
        fillTextTwo.setShape(tPlayerOne);
        fillTextTwo.setDuration(new Duration(500));
        fillTextTwo.setToValue(Color.ALICEBLUE);
        fillTextTwo.setCycleCount(Timeline.INDEFINITE);
        fillTextTwo.setAutoReverse(true);
        fillTextTwo.play();
      
    

        
        //move pan by mouse
     
            panYou.setOnMouseMoved((MouseEvent event) -> {
                if (event.getY()>= (height/2)+ panRadius)
                {
                    
                    
                    panYou.setCenterX(event.getX());
                    panYou.setCenterY(event.getY());
                }
                });

            
            //AUTO change color by enter mouse on your pan
            panYou.setOnMouseEntered((MouseEvent event) -> {
                
                fTouch= new FillTransition();
                fTouch.setShape(panYou);
                fTouch.setDuration(new Duration(500));
                fTouch.setToValue(Color.YELLOW);
                fTouch.setCycleCount(Timeline.INDEFINITE);
                fTouch.setAutoReverse(true);
                fTouch.play();
                });
                       
                

            
            //auto stop if mouse exit your pan
            panYou.setOnMouseExited((MouseEvent event) -> {
                fTouch.stop();
                });
            
        //run game each frim by handle move pan and move disc and logic of game
         timer = new Timeline(new KeyFrame(Duration.millis(18),
            new EventHandler<ActionEvent>() {
            double x=5; //dx add evry sec
            double y=5 ; //dy add every sec
            int i ,j ,k;


            
            
            @Override
            
            public void handle(ActionEvent t) {
                
                
                
                disc.setLayoutX(disc.getLayoutX()+x);
                disc.setLayoutY(disc.getLayoutY()+y);//move disc
                if (x>16 || y>16 )
                {
                    x=x-4;
                    y=-4;
                }
                if(x<-16 || y<-16)
                {
                    x=x+4;
                    y=y+4;
                }
                
                //if disc smash to pc goal 
                  if (disc.getLayoutX()>=(width/2) - (goalWidth/2) && disc.getLayoutX()< width - goalWidth  && disc.getLayoutY() <=borderThickness+ discRadius)
                    {
                        tPlayerOne.setText("" + ++player1);
                        
                        if(player1<10)
                        {
                        String huraMusic=new File("src\\airh\\goal.mp3").toURI().toString();
                        MediaPlayer mediaPlayer1 = new MediaPlayer(new Media(huraMusic));
                        mediaPlayer1.setCycleCount(1);
                        mediaPlayer1.play();
                        }
                        disc.setLayoutX(width/2);//disc back again center ground 
                        disc.setLayoutY(height/2);
                        y=0; //move 
                        x=0;

                                

                    }
                  
                  //if disc smash to your goal
                  if (disc.getLayoutX()>=(width/2) - (goalWidth/2) && disc.getLayoutX()< width - goalWidth  && disc.getLayoutY() >= height-(borderThickness+ discRadius))
                  {
                        tPlayeTow.setText("" + ++player2);
                        
                        disc.setLayoutX(width/2);
                        disc.setLayoutY(height/2);
                        y=0;
                        x=0;
                        
                  }
                  
                    //if disc smash side
                    if(disc.getLayoutX() <= ( disc.getRadius())  ||
                        disc.getLayoutX() >= (width - disc.getRadius()) ){

                        
                       String side=new File("src\\airh\\pop.mp3").toURI().toString();
                       MediaPlayer mediaP = new MediaPlayer(new Media(side));
                       mediaP.setCycleCount(1);
                       mediaP.play();
                          
                          
                      x = -x;
                      if (y==0)
                          x=-x-4;

                      
                        Random random =new Random();
                        i=random.nextInt(250);
                        j=random.nextInt(250);
                        k=random.nextInt(250);
                        
                        center.setFill(Color.rgb(i, j, k));
                        line.setFill(Color.rgb(i, j,k));
                        centerofcenter.setFill(Color.rgb(i, j, k));
                        rectBound.setFill(Color.rgb(i, j, k));
                        b.setFill(Color.rgb(i, j, k));
                        a.setFill(Color.rgb(i, j, k));

                                

                        
                }
                  
                  //if disc smash side
                   if((disc.getLayoutY() >= (height - disc.getRadius())  ) ||
                        (disc.getLayoutY() <= ( disc.getRadius()))){

                      
                       String side=new File("src\\airh\\pop.mp3").toURI().toString();
                       MediaPlayer mediaP = new MediaPlayer(new Media(side));
                       mediaP.setCycleCount(1);
                       mediaP.play();
                       
                       
                       
                       
                       
                       y = -y;

                        
                        Random random =new Random();
                        i=random.nextInt(250);
                        j=random.nextInt(250);
                        k=random.nextInt(250);
                        center.setFill(Color.rgb(i, j, k));
                        line.setFill(Color.rgb(i, j,k));
                        centerofcenter.setFill(Color.rgb(i, j, k));
                        rectBound.setFill(Color.rgb(i, j, k));
                        b.setFill(Color.rgb(i, j, k));
                        a.setFill(Color.rgb(i, j, k));
 
                        
                }
                   
                   //calculate distance your pan and disc if smash your disc reflex disc
                   double xPanOne , yPanOne ;
                   xPanOne=(panYou.getCenterX()-disc.getLayoutX())*(panYou.getCenterX()-disc.getLayoutX());
                   yPanOne=(panYou.getCenterY()-disc.getLayoutY())*(panYou.getCenterY()-disc.getLayoutY());
                   
                   if(Math.sqrt(Math.abs(xPanOne+yPanOne))<(panRadius+discRadius))
                   {
                       Random rand= new Random();
                       int ran=rand.nextInt(2)+1;
                       x=-ran*x-3;
                       y=-ran*y-5;
                       if(x<-12 )
                           x=x+4;
                       if(y<-10)
                           y=y+3;
                       if (x>12)
                           x=x-4;
                       if(y>12)
                           y=y-3;

                       
                   }
                   
                   
                   
                  if ( disc.getLayoutY()< (height/2) && disc.getLayoutX()>=(width/2) - (goalWidth/2) && disc.getLayoutX()< width - goalWidth   )
                   {
                    panPC.setCenterX(disc.getLayoutX()-6);

                   }


                   
                   //calculate distance pc pan and disc if smash pc disc reflex disc

                   double  xPanTwo , yPanTwo ;
                   xPanTwo=((panPC.getCenterX()-disc.getLayoutX())*(panPC.getCenterX()-disc.getLayoutX()));
                   yPanTwo=((panPC.getCenterY()-disc.getLayoutY())*(panPC.getCenterY()-disc.getLayoutY()));
                  
                 if(Math.sqrt(Math.abs(xPanTwo+yPanTwo))<=((panRadius+discRadius)))
                 {
                       Random rand= new Random();
                       int ran=rand.nextInt(2)+1;
                       x=-ran*x-5;
                       y=-ran*y-3;
                       
                       if (x>12)
                           x=x-4;
                       if(y>12)
                           y=y-3;
                       if(x<-12 )
                           x=x+4;
                       if(y<-10)
                           y=y+3;
                      
                      
                   }
                    

                  
                 

                   if (player1==10)
                   {
                      Text wonText=new Text(width/5,height/2, "  YOU WIN !!");
                      wonText.setFont(Font.loadFont("file:src/airh/ALGER.ttf",50));
                      wonText.setFill(Color.CRIMSON);
                      FillTransition fWonTEXT =new FillTransition();
                     fWonTEXT.setShape(wonText);
                     fWonTEXT.setDuration(new Duration(200));
                     fWonTEXT.setToValue(Color.DEEPPINK);
                     fWonTEXT.setCycleCount(Timeline.INDEFINITE);
                     fWonTEXT.setAutoReverse(true);
                     fWonTEXT.play();
                      root.getChildren().add(wonText);
                      timer.stop();
                      
                      
                      String baseMusic=new File("src\\airh\\you_win.mp3").toURI().toString();
                      MediaPlayer mediaPlayer = new MediaPlayer(new Media(baseMusic));
                      mediaPlayer.setCycleCount(1);
        
                       mediaPlayer.play();
                      
 
            
                   }
                   
                   if (player2==10)
                   {
                        Text wonText=new Text(width/5,height/2, " YOU LOSE !!");
                      wonText.setFont(Font.loadFont("file:src/airh/ALGER.ttf",50));
                      wonText.setFill(Color.CRIMSON);
                      FillTransition fWonTEXT =new FillTransition();
                     fWonTEXT.setShape(wonText);
                     fWonTEXT.setDuration(new Duration(200));
                     fWonTEXT.setToValue(Color.DEEPPINK);
                     fWonTEXT.setCycleCount(Timeline.INDEFINITE);
                     fWonTEXT.setAutoReverse(true);
                     fWonTEXT.play();
                      root.getChildren().add(wonText);
                      timer.stop();   
                   }
            

            }
            
         
            
                }));
     

        
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();       

         // add 

        root.getChildren().add(rectBound);
        
        root.getChildren().add(rectGround);  
        root.getChildren().add(a);
        root.getChildren().add(a1); 
        root.getChildren().add(b); 
        root.getChildren().add(b1); 

        root.getChildren().add(center);
        root.getChildren().add(intocenter);
        root.getChildren().add(line);
        root.getChildren().add(centerofcenter);
        root.getChildren().add(panYou);
        root.getChildren().add(panPC);      
        root.getChildren().add(disc);
        root.getChildren().add(tPlayeTow);
        root.getChildren().add(tPlayerOne);
        root.getChildren().add(goalPc);
        root.getChildren().add(goalYou);
        return scene;
        
    }
    
  
   
    
    
}
class setting 
{
    public  Scene setting ( )
    {
        PanDiscView panDiscView=new PanDiscView(400 ,600, 150, 7, 15, 30);
        panDiscView.setPan1Position(100, 500);
        panDiscView.setPan2Position( 200, 100 );
        panDiscView.setDiscPosition( 100, 500 );
        return( panDiscView.getScene());
    }
}