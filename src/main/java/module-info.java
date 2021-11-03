module ms.javafx.drawgraph {
    requires javafx.controls;
    requires javafx.fxml;


    opens ms.javafx.drawgraph to javafx.fxml;
    exports ms.javafx.drawgraph;
}