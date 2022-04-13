package com.example.airhockey;

import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

public class PanDiscView
{
    private final double width , height , goalWidth, borderThickness, discRadius,panRadius;
    private double ydisc;
    private double xpan1;
    private double ypan1;
    private double xpan2;
    private double ypan2 ;
    private int player1 , player2 ;
    Text tPlayeTow ,tPlayerOne ;
    FillTransition fTouch;
    MediaPlayer goalMedia ,hura;
    Timeline timer;

    //constructor
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


        double xdisc = xpan1;
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


    public Scene getScene()
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
