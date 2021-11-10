package ms.javafx.drawgraph;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CanvasController implements Initializable {
    @FXML private Group canvasGrp;
    @FXML private Button finBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        canvasGrp.setOnMouseClicked(this::handleCanvasClick);
        finBtn.setOnAction(e -> handleBtnDone());
    }

    public void handleCanvasClick(MouseEvent e) {
        //left click on existed node
        if (NodeFx.isPrimaryClicked) {
            if(NodeFx.selectedNodes.size() == 2) {
                drawEdge();
            }
            return;
        }
        //right click on existed node
        if(NodeFx.isSecondaryClicked && NodeFx.toRemove != null) {
            remove();
            return;
        }
        //right click on empty space
        if(e.getButton() == MouseButton.SECONDARY) {
            return;
        }
        //left click on empty space
        //draw node
        NodeFx node = new NodeFx(e.getX(), e.getY());
        canvasGrp.getChildren().add(node);
        canvasGrp.getChildren().add(node.id);
    }

    public void handleBtnDone() {
        Graph graph = new Graph();
        System.out.println("Make Graph");
    }

    private void drawEdge() {
        System.out.println("Draw Edge");

        NodeFx src = NodeFx.selectedNodes.get(0);
        NodeFx dest = NodeFx.selectedNodes.get(1);
        EdgeFx edge = new EdgeFx(src, dest);

        canvasGrp.getChildren().add(edge.weightLabel);
        canvasGrp.getChildren().add(edge.line);

        NodeFx.clearSelected();

        NodeFx.isPrimaryClicked = false;
    }

    private void remove() {
        //remove node
        canvasGrp.getChildren().remove(NodeFx.toRemove);
        canvasGrp.getChildren().remove(NodeFx.toRemove.id);

        //remove edge
        List<EdgeFx> removedEdgeList = EdgeFx.edgeList.stream()
                .filter(edge -> edge.source.id == NodeFx.toRemove.id || edge.target.id == NodeFx.toRemove.id)
                .toList();
        for(EdgeFx ef : removedEdgeList) {
            canvasGrp.getChildren().remove(ef.weightLabel);
            canvasGrp.getChildren().remove(ef.line);
            EdgeFx.edgeList.remove(ef);
        }

        NodeFx.isSecondaryClicked = false;
        NodeFx.toRemove = null;
    }
}
