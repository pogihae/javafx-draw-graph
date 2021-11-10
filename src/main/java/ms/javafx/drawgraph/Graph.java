package ms.javafx.drawgraph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<String, List<Edge>> adj;

    public Graph() {
        adj = new HashMap<>();

        for(EdgeFx e : EdgeFx.edgeList) {
            String src = e.source.id.getText();
            String dest = e.source.id.getText();

            Edge edge = new Edge(src, dest, e.weight);

            adj.getOrDefault(src, new LinkedList<>())
                    .add(edge);
            adj.getOrDefault(dest, new LinkedList<>())
                    .add(edge);
        }
    }

    private static class Edge {
        String srcId, destId;
        double weight;

        Edge(String src, String dest, double weight) {
            this.srcId = src;
            this.destId = dest;
            this.weight = weight;
        }
    }
}
