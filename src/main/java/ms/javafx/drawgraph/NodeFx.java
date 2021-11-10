package ms.javafx.drawgraph;

import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class NodeFx extends Circle {
    private static final int RAD = 30;

    public static boolean isPrimaryClicked = false;
    public static boolean isSecondaryClicked = false;
    public static NodeFx toRemove = null;

    public static int nodeCnt = 0;
    public static List<NodeFx> selectedNodes = new ArrayList<>(2);

    public Label id;
    public double x,y;
    public boolean selected;

    public NodeFx(double x, double y) {
        super(x,y,RAD);
        this.x = x;
        this.y = y;
        selected = false;

        //set node name
        id = new Label(Integer.toString(nodeCnt++));
        id.setOnMouseClicked(this::handleNodeClick);
        id.setFont(new Font(30));
        id.setLayoutX(x-10);
        id.setLayoutY(y-20);

        setBlendMode(BlendMode.MULTIPLY);
        setOpacity(0.5);
        setFill(Color.CORAL);

        setOnMouseClicked(this::handleNodeClick);
    }

    public void handleNodeClick(MouseEvent e) {
        if(e.getButton() == MouseButton.PRIMARY) {
            isPrimaryClicked = true;
            if(selected) {
                selected = false;
                selectedNodes.remove(this);
                setFill(Color.BLUE);
            }
            else {
                selected = true;
                selectedNodes.add(this);
                setFill(Color.RED);
            }
        }
        else if(e.getButton() == MouseButton.SECONDARY) {
            isSecondaryClicked = true;
            toRemove = this;
        }

    }

    public static void clearSelected() {
        selectedNodes.get(0).unselect();
        selectedNodes.get(1).unselect();
        selectedNodes.clear();
    }

    public void unselect() {
        this.setFill(Color.BLUE);
        selected = false;
    }
}
