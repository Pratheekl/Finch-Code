import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.awt.*;

/*
    how to set up dependency with finch:
        1.Download generic finch software from the finch website
        2.have the .zip extracted for finch software
        3.create a new project in intellij
        4.go to file - project structure
        5.Go to modules
        6.Find finch.jar
        7.Select finch.jar then press the pencil to the right
        8.Press + and select the javadoc folder and press ok
        9.Press Apply then Ok
    Dependency has now been set up, your finch should connect with the computer to intellij!

 */
public class KeyListener extends Application
{
    private static final Finch finch = new Finch();
    private static final int maxSpeed = 255;
    private static final int slowSpeed = 100;

    public static final Color pink = new Color(255, 51, 255);

    public static void main(String[] args)
    {
        finch.setLED(pink);  // initially pink
        launch(args);   // so it begins
        System.exit(0); // after pressing esc this will occur
    }

    /**
     *
     * @param e
     * @return the keycode of the keyboard input
     */
    public static KeyCode keyPressed(KeyEvent e)
    {
        KeyCode keyCode = e.getCode();
        return keyCode;
    }

    /**
     * this method houses our finch's ability to:
     *                                 move and change color
     * @param e
     */

    public static void move(KeyEvent e)
    {
        //max movement speed
        if(keyPressed(e) == KeyCode.W)  // forward
            finch.setWheelVelocities(maxSpeed, maxSpeed);
        else if(keyPressed(e) == KeyCode.A)  // left
            finch.setWheelVelocities(-maxSpeed,maxSpeed);
        else if(keyPressed(e) == KeyCode.S)  // backwards
            finch.setWheelVelocities(-maxSpeed,-maxSpeed);
        else if(keyPressed(e) == KeyCode.D)  // right
            finch.setWheelVelocities(maxSpeed,-maxSpeed);

        //about half speed movement - more accurate turns available
        if(keyPressed(e) == KeyCode.KP_UP)  // forward
            finch.setWheelVelocities(slowSpeed, slowSpeed);
        else if(keyPressed(e) == KeyCode.KP_LEFT)  // left
            finch.setWheelVelocities(-slowSpeed,slowSpeed);
        else if(keyPressed(e) == KeyCode.KP_DOWN)  // backwards
            finch.setWheelVelocities(-slowSpeed,-slowSpeed);
        else if(keyPressed(e) == KeyCode.KP_RIGHT)  // right
            finch.setWheelVelocities(slowSpeed,-slowSpeed);

        //changes the color of the finch
        if(keyPressed(e) == KeyCode.P)  // pink
            finch.setLED(Color.pink);
        else if(keyPressed(e) == KeyCode.R)  // Red
            finch.setLED(Color.red);
        else if(keyPressed(e) == KeyCode.H)  // white
            finch.setLED(Color.white);
        else if(keyPressed(e) == KeyCode.L)  // green
            finch.setLED(Color.green);
        /*add more colors:
        else if(keyPressed(e) == KeyCode.)
            finch.setLED();
         */

        //applies "brakes" to the finch, it can still be used after pressing this
        if(keyPressed(e) == KeyCode.B || keyPressed(e) == KeyCode.BACK_SPACE)
            finch.setWheelVelocities(0,0);

        //stops the finch and makes you have to unplug and replug it and rerun the code(basically e-stop)
        if(keyPressed(e) == KeyCode.ESCAPE)
            finch.quit();
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("finchMoving");
        primaryStage.setScene(new Scene(new Pane(), 100, 100));
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, KeyListener::move);
        primaryStage.show();
    }
}
