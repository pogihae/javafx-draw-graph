package ms.javafx.drawgraph;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CanvasController implements Initializable {
    @FXML private Button finBtn;
    @FXML private Group canvasGrp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        canvasGrp.setOnMouseClicked(this::handleCanvasClick);
        finBtn.setOnMouseClicked(e -> makeGraph());
    }

    public void handleCanvasClick(MouseEvent e) {
        if (Node.nodeClicked) {

            //draw edge
            if (Node.selectedNodes.size() == 2) {
                System.out.println("Draw Edge");

                Node src = Node.selectedNodes.get(0);
                Node dest = Node.selectedNodes.get(1);
                Edge edge = new Edge(src, dest);

                canvasGrp.getChildren().add(edge.weightLabel);
                canvasGrp.getChildren().add(edge.line);

                Node.clearSelected();
            }

            //non-empty space clicked
            Node.nodeClicked = false;
            return;
        }

        //draw node
        Node node = new Node(e.getX(), e.getY());
        canvasGrp.getChildren().add(node);
        canvasGrp.getChildren().add(node.id);
    }

    public void makeGraph() {
    }
}
