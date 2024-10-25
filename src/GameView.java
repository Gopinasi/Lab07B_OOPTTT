import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private static final int ROW = 3;
    private static final int COL = 3;
    private TileButton[][] buttons = new TileButton[ROW][COL];
    private TBoard board;
    private JLabel statusLabel;

    public GameView() {
        board = new TBoard();
        display();
    }

    private void display() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Title Panel
        JPanel topPanel = new JPanel();
        JLabel title = new JLabel("Play Tic Tac Toe", JLabel.CENTER);
        title.setFont(new Font("Monospaced", Font.BOLD, 36));
        topPanel.add(title);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Grid Panel
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(ROW, COL));
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                buttons[row][col] = new TileButton(row, col, board, this);
                gridPanel.add(buttons[row][col]);
            }
        }
        mainPanel.add(gridPanel, BorderLayout.CENTER);

        // Status Panel
        statusLabel = new JLabel("Current Player: X", JLabel.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        JPanel controlPanel = new JPanel();
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        controlPanel.add(quitButton);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSize(600, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void displayWin(String player) {
        JOptionPane.showMessageDialog(this, "Player " + player + " won!");
        disableAllButtons();
        if (getYNConfirm("Do you want to play again?")) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    public void displayTie() {
        JOptionPane.showMessageDialog(this, "It's a tie!");
        disableAllButtons();
        if (getYNConfirm("Do you want to play again?")) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private boolean getYNConfirm(String message) {
        int result = JOptionPane.showConfirmDialog(this, message, "Confirm", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    private void resetGame() {
        board.createGridPanel();
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                buttons[row][col].resetButton();
            }
        }
        statusLabel.setText("Current Player: X");
    }

    private void disableAllButtons() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                buttons[row][col].disableButton();
            }
        }
    }
}
