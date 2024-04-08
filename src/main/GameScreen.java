package main;

import input.KeyBoardListener;
import input.MouseListener;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private Game game;
    private Dimension screenSize;
    private MouseListener mouseListener;
    private KeyBoardListener keyBoardListener;
    public GameScreen(Game game){
        this.game = game;
        setPanelSize();
    }

    public void initInputs() {
        mouseListener = new MouseListener(game);
        keyBoardListener = new KeyBoardListener(game);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addKeyListener(keyBoardListener);
        requestFocus();
    }

    private void setPanelSize() {
        screenSize = new Dimension(600, 700);
        setMinimumSize(screenSize);
        setMaximumSize(screenSize);
        setPreferredSize(screenSize);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.getRender().render(g);
    }
}
