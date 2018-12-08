import javafx.application.Application;
import javafx.stage.Stage;
import java.awt.event.KeyEvent;

abstract class AbstractFinch extends Application
{
    public abstract void move(KeyEvent keyEvent);

    @Override
    public abstract void start(Stage stage);
}
