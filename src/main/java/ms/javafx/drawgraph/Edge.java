package ms.javafx.drawgraph;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;

public class Edge {
    public Node source, target;
    public double weight;
    public Line line;
    public Label weightLabel;

    public Edge(Node src, Node dest) {
        source = src;
        target = dest;
        weight = Math.sqrt(Math.pow(src.x - dest.x, 2) + Math.pow(src.y - dest.y, 2));

        line = new Line(src.x, src.y, dest.x, dest.y);

        weightLabel = new Label();
        weightLabel.setText(String.format("%.2f", weight));
        weightLabel.setLayoutX(((src.x) + (dest.x)) / 2);
        weightLabel.setLayoutY(((src.y) + (dest.y)) / 2);
    }
}
