package ms.javafx.drawgraph;

import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Node extends Circle {
    private static final int RAD = 30;

    public static boolean nodeClicked = false;
    public static int nodeCnt = 0;
    public static List<Node> selectedNodes = new ArrayList<>(2);

    public Label id;
    public double x, y;
    public boolean selected;

    public Node(double x, double y) {
        super(x, y, RAD);
        this.x = x;
        this.y = y;
        selected = false;

        //set node name
        id = new Label(Integer.toString(nodeCnt++));
        id.setMouseTransparent(true);
        id.setFont(new Font(50));
        id.setLayoutX(x - 10);
        id.setLayoutY(y - 25);

        setBlendMode(BlendMode.MULTIPLY);
        setOpacity(0.5);
        setFill(Color.BLUE);

        setOnMouseClicked(e -> handleNodeClick());
    }

    public void handleNodeClick() {
        nodeClicked = true;
        if (selected) {
            selected = false;
            selectedNodes.remove(this);
            setFill(Color.BLUE);
        } else {
            selected = true;
            selectedNodes.add(this);
            setFill(Color.RED);
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
