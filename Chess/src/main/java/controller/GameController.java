package controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class GameController {

    @FXML
    private GridPane rowIdentifier;

    @FXML
    private GridPane chessBoard;

    @FXML
    private GridPane columnIdentifier;

    private final int numOfTiles = 8;

    @FXML
    private void initialize() {
        createGameView();
    }

    /**
     * Add tiles to the gridpanes (rowIdentifier, columnIdentifier, chessboard)
     */
    private void createGameView() {
        String[] columnIdentifierText = {"A", "B", "C", "D", "E", "F", "G", "H"};
        setGridConstraints(0);

        for(int i = 0; i < numOfTiles; i++) {
            addTileToRowIdentifier((numOfTiles - i) + "", i);
            addTileToColumnIdentifier(columnIdentifierText[i], i);
            for(int j = 0; j < numOfTiles; j++) {
                addTileToChessboard(i, j);
            }
            setGridConstraints(1);
        }

    }

    /**
     * Set all row- and column- constraints for the gridpanes (rowIdentifier, columnIdentifier, chessboard)
     * @param i
     */
    private void setGridConstraints(int i) { //TODO - Switch cases are not object oriented programming (As my old java teacher told us)
        int percent = 100;
        switch(i) {
            case 0:
                rowIdentifier.getColumnConstraints().add(createColumnConstraints(percent));
                columnIdentifier.getRowConstraints().add(createRowConstraints(percent));
                break;
            case 1:
                percent /= numOfTiles;
                rowIdentifier.getRowConstraints().add(createRowConstraints(percent));
                columnIdentifier.getColumnConstraints().add(createColumnConstraints(percent));
                chessBoard.getColumnConstraints().add(createColumnConstraints(percent));
                chessBoard.getRowConstraints().add(createRowConstraints(percent));
                break;
        }
    }

    /**
     * Create rowconstraints with given double value as percent height
     * @param percentHeight
     * @return RowConstraints
     */
    private RowConstraints createRowConstraints(double percentHeight) {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(percentHeight);
        return rowConstraints;
    }

    /**
     * Create columnconstraints with given double value as percent width
     * @param percentWidth
     * @return ColumnConstraints
     */
    private ColumnConstraints createColumnConstraints(double percentWidth) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(percentWidth);
        return columnConstraints;
    }

    /**
     * Create tile (Label) and add it to row identifier
     * @param text
     * @param position
     */
    private void addTileToRowIdentifier(String text, int position) {
        Label tile = new Label(text);
        tile.prefWidthProperty().bind(rowIdentifier.widthProperty());
        tile.setAlignment(Pos.CENTER);
        rowIdentifier.add(tile, 0, position);
    }

    /**
     * Create tile (Label) and add it to column identifier
     * @param text
     * @param position
     */
    private void addTileToColumnIdentifier(String text, int position) {
        Label tile = new Label(text);
        tile.prefWidthProperty().bind(columnIdentifier.widthProperty().divide(numOfTiles));
        tile.setAlignment(Pos.CENTER);
        columnIdentifier.add(tile, position, 0);
    }

    /**
     * Create tile (StackPane) and add it to chessboard
     * @param rowPos
     * @param colPos
     */
    private void addTileToChessboard(int rowPos, int colPos) {
        StackPane tile = new StackPane();
        chessBoard.add(tile, colPos, rowPos);
    }
}
