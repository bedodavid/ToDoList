package animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;
public class MoveNode {
  private TranslateTransition translateTransition;

public MoveNode(Node node,float Xposition,float Yposition){
   translateTransition =new TranslateTransition(Duration.millis(50), node);
   translateTransition.setFromX(0f);
   translateTransition.setByX(Xposition);
   translateTransition.setByY(Yposition);
   
           
}  
 public void move(){
  translateTransition.playFromStart();
 }   
}