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
    private final double width, height, goalWidth, borderThickness, discRadius, panRadius;
    private double ydisc;
    private double xpan1;
    private double ypan1;
    private double xpan2;
    private double ypan2 ;
    private final Random random =new Random();
    private int firstPlayerScore, secondPlayerScore;
    Text secondPlayerText, firstPlayerText;
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
    public void set_first_player_score(int score)
    {
        this.firstPlayerScore = score;
    }
    public void set_second_player_score(int score)
    {
        this.secondPlayerScore = score;
    }
    public int get_first_player_score()
    {
        return firstPlayerScore;
    }
    public int get_second_player_score()
    {
        return secondPlayerScore;
    }


    public Scene getScene()
    {
        // scene and draw staff of table
        Group root=new Group();
        Scene scene=new Scene(root, width, height, Color.ALICEBLUE);

        Rectangle rectBound= new Rectangle(0, 0, width, height);
        rectBound.setFill(Color.GREEN);

        Rectangle rectGround =new Rectangle(borderThickness, borderThickness, width - 2*borderThickness , height- 2*borderThickness );
        rectGround.setFill(Color.ALICEBLUE);

        // draw YOUR pan
        Circle yourPan =new Circle(xpan1, ypan1 , panRadius);
        yourPan.setFill(Color.LIGHTGREEN);

        // DRAW PC pan
        Circle pcPan =new Circle(width/2, height/3 , panRadius);
        pcPan.setFill(Color.LIGHTGREEN);


        //draw your goal
        Rectangle goalYou=new Rectangle((width/2) - (goalWidth/2), height - borderThickness, goalWidth, borderThickness);
        draw_goal(goalYou);

        //draw pc goal
        Rectangle goalPc=new Rectangle( (width/2) - (goalWidth/2), 0, goalWidth, borderThickness);
        draw_goal(goalPc);

        // draw your arc
        Arc a=new Arc((width/2), 0, goalWidth, goalWidth, 0, height);
        Arc a1=new Arc(width/2, 0, (goalWidth)- borderThickness , (goalWidth)- borderThickness, panRadius, width);
        a1.setFill(Color.ALICEBLUE);

        //draw pc arc
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
        Circle interCenter =new Circle(width/2, height/2, (width/6) - borderThickness, Color.RED);
        FillTransition f4 = new FillTransition();
        f4.setShape(interCenter);
        f4.setDuration(new Duration(2000));
        f4.setToValue(Color.BLACK);
        f4.setCycleCount(Timeline.INDEFINITE);
        f4.setAutoReverse(true);
        f4.play();

        Circle centerOfCenter = new Circle(width/2, height/2, width/20, Color.GREEN);
        //AUTO CHANG COLOR FOR THIS

        //draw lines / RECTANGLE  half of game ground
        Rectangle line= new Rectangle(0,(height/2), width, borderThickness/2);
        line.setFill(Color.GREEN);

        //add text corner for show  your score and pc score
        secondPlayerText = new Text();
        secondPlayerText.setX(2*borderThickness);
        secondPlayerText.setY((height/2)-borderThickness);
        set_player_text(secondPlayerText, secondPlayerScore);

        firstPlayerText = new Text();
        firstPlayerText.setX(2* borderThickness );
        firstPlayerText.setY((height/2) +9* borderThickness);
        set_player_text(firstPlayerText, firstPlayerScore);


        //move pan by mouse
        yourPan.setOnMouseMoved((MouseEvent event) -> {
            if (event.getY()>= (height/2)+ panRadius)
            {
                yourPan.setCenterX(event.getX());
                yourPan.setCenterY(event.getY());
            }
        });

        //AUTO change color by enter mouse on your pan
        yourPan.setOnMouseEntered((MouseEvent event) -> {

            fTouch= new FillTransition();
            fTouch.setShape(yourPan);
            fTouch.setDuration(new Duration(500));
            fTouch.setToValue(Color.YELLOW);
            fTouch.setCycleCount(Timeline.INDEFINITE);
            fTouch.setAutoReverse(true);
            fTouch.play();
        });

        //auto stop if mouse exit your pan
        yourPan.setOnMouseExited((MouseEvent event) -> {
            fTouch.stop();
        });

        //run game each frame by handle move pan and move disc and logic of game
        timer = new Timeline(new KeyFrame(Duration.millis(18),
                new EventHandler<ActionEvent>() {
                    double x=5; //dx add every sec
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
                            firstPlayerText.setText("" + ++firstPlayerScore);

                            if(firstPlayerScore <10)
                            {
                                String happySoundPath=new File("C:\\Users\\kouro\\OneDrive\\" +
                                        "Documents\\Air\\src\\main\\media\\goal.mp3").toURI().toString();
                                MediaPlayer happySound = new MediaPlayer(new Media(happySoundPath));
                                happySound.setCycleCount(1);
                                happySound.play();
                            }
                            disc.setLayoutX(width/2);//disc back again center ground
                            disc.setLayoutY(height/2);
                            y=0; //move
                            x=0;

                        }

                        //if disc smash to your goal
                        if (disc.getLayoutX()>=(width/2) - (goalWidth/2) && disc.getLayoutX()< width - goalWidth  && disc.getLayoutY() >= height-(borderThickness+ discRadius))
                        {
                            secondPlayerText.setText("" + ++secondPlayerScore);
                            disc.setLayoutX(width/2);
                            disc.setLayoutY(height/2);
                            y=0;
                            x=0;
                        }

                        //if disc smash side
                        if(disc.getLayoutX() <= ( disc.getRadius())  ||
                                disc.getLayoutX() >= (width - disc.getRadius()) ){
                            String sidePath=new File("C:\\Users\\kouro\\OneDrive\\Documents\\Air" +
                                    "\\src\\main\\media\\pop.mp3").toURI().toString();
                            MediaPlayer sideSound = new MediaPlayer(new Media(sidePath));
                            sideSound.setCycleCount(1);
                            sideSound.play();

                            x = -x;
                            if (y==0)
                                x=-x-4;
                            generate_random(random);
                        }

                        //if disc smash side
                        if((disc.getLayoutY() >= (height - disc.getRadius())  ) ||
                                (disc.getLayoutY() <= ( disc.getRadius()))){


                            String sidePath=new File("C:\\Users\\kouro\\OneDrive\\Documents\\Air" +
                                    "\\src\\main\\media\\pop.mp3").toURI().toString();
                            MediaPlayer sideSound = new MediaPlayer(new Media(sidePath));
                            sideSound.setCycleCount(1);
                            sideSound.play();

                            y = -y;

                            Random random =new Random();
                            generate_random(random);

                        }

                        //calculate distance your pan and disc if smash your disc reflex disc
                        double xPanOne , yPanOne ;
                        xPanOne=(yourPan.getCenterX()-disc.getLayoutX())*(yourPan.getCenterX()-disc.getLayoutX());
                        yPanOne=(yourPan.getCenterY()-disc.getLayoutY())*(yourPan.getCenterY()-disc.getLayoutY());

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
                            pcPan.setCenterX(disc.getLayoutX()-6);

                        }

                        //calculate distance pc pan and disc if smash pc disc reflex disc

                        double  xPanTwo , yPanTwo ;
                        xPanTwo=((pcPan.getCenterX()-disc.getLayoutX())*(pcPan.getCenterX()-disc.getLayoutX()));
                        yPanTwo=((pcPan.getCenterY()-disc.getLayoutY())*(pcPan.getCenterY()-disc.getLayoutY()));

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

                        if (firstPlayerScore ==10)
                        {
                            Text wonText=new Text(width/5,height/2, "  YOU WIN !!");
                            set_text_setting(wonText, root);


                            String basePath=new File("C:\\Users\\kouro\\OneDrive\\Documents" +
                                    "\\Air\\src\\main\\media\\you_win.mp3").toURI().toString();
                            MediaPlayer baseMusic = new MediaPlayer(new Media(basePath));
                            baseMusic.setCycleCount(1);
                            baseMusic.play();
                        }

                        if (secondPlayerScore ==10)
                        {
                            Text wonText=new Text(width/5,height/2, " YOU LOSE !!");
                            set_text_setting(wonText, root);
                        }
                    }

                    private void generate_random(Random random2) {
                        i= random2.nextInt(250);
                        j= random2.nextInt(250);
                        k= random2.nextInt(250);

                        center.setFill(Color.rgb(i, j, k));
                        line.setFill(Color.rgb(i, j,k));
                        centerOfCenter.setFill(Color.rgb(i, j, k));
                        rectBound.setFill(Color.rgb(i, j, k));
                        b.setFill(Color.rgb(i, j, k));
                        a.setFill(Color.rgb(i, j, k));
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
        root.getChildren().add(interCenter);
        root.getChildren().add(line);
        root.getChildren().add(centerOfCenter);
        root.getChildren().add(yourPan);
        root.getChildren().add(pcPan);
        root.getChildren().add(disc);
        root.getChildren().add(secondPlayerText);
        root.getChildren().add(firstPlayerText);
        root.getChildren().add(goalPc);
        root.getChildren().add(goalYou);
        return scene;

    }

    private void set_text_setting(Text wonText, Group root) {
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

    private void set_player_text(Text secondPlayerText, int secondPlayerScore) {
        secondPlayerText.setText(""+ secondPlayerScore);
        secondPlayerText.setFill(Color.CORNFLOWERBLUE);
        FillTransition fillTextOne= new FillTransition();
        fillTextOne.setShape(secondPlayerText);
        fillTextOne.setDuration(new Duration(500));
        fillTextOne.setToValue(Color.ALICEBLUE);
        fillTextOne.setCycleCount(Timeline.INDEFINITE);
        fillTextOne.setAutoReverse(true);
        fillTextOne.play();
    }

    private void draw_goal(Rectangle goalYou) {
        goalYou.setFill(Color.RED);
        FillTransition goal = new FillTransition();
        goal.setShape(goalYou);
        goal.setDuration(new Duration(2000));
        goal.setToValue(Color.BLACK);
        goal.setCycleCount(Timeline.INDEFINITE);
        goal.setAutoReverse(true);
        goal.play();
    }
}
