import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TileButton extends JButton {

    private int row;
    private int col;
    private TBoard board;
    private GameView view;

    public TileButton(int row, int col, TBoard board, GameView view) {
        this.row = row;
        this.col = col;
        this.board = board;
        this.view = view;
        this.setText(" ");
        this.setFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 60));
        this.setFocusPainted(false);
        this.setBackground(java.awt.Color.PINK);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK, 3));

        this.addActionListener(e -> controlClick());
    }

    private void controlClick() {
        if (board.isValidMove(row, col)) {
            board.makeMove(row, col);
            this.setText(board.getCurrentPlayerSymbol());

            if (board.isWin(board.getCurrentPlayerSymbol())) {
                view.displayWin(board.getCurrentPlayerSymbol());
            } else if (board.isTie()) {
                view.displayTie();
            }
        }
    }

    public void resetButton() {
        this.setText(" ");
        this.setEnabled(true);
    }

    public void disableButton() {
        this.setEnabled(false);
    }
}