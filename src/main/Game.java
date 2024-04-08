package main;

import scenes.GameOver;
import scenes.Menu;
import scenes.Playing;

import javax.swing.*;

public class Game extends JFrame implements Runnable {
    private final double FPS_SET = 60.0;
    private final double UPS_SET = 60.0;
    private Thread gameThread;
    private Render render;
    private GameScreen gameScreen;
    private Menu menu;
    private Playing playing;
    private GameOver gameOver;

    public Game(){
        initClasses();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Space Rush");
        add(gameScreen);
        start();
        updateGame();
        pack();
        setVisible(true);
        gameScreen.initInputs();
    }

    private void initClasses() {
        gameScreen = new GameScreen(this);
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        gameOver = new GameOver(this);
    }

    private void updateGame() {
        switch (GameStates.gameState){
            case MENU:
                break;
            case PLAYING:
                playing.update();
                break;
        }
    }

    private void start() {
        gameThread = new Thread(this) {
        };
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1e9 / FPS_SET;
        double timePerUpdate = 1e9 / UPS_SET;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;
        while (true) {
            // Render
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = System.nanoTime();
                frames++;
            }
            // Update
            if (System.nanoTime() - lastUpdate >= timePerUpdate) {
                updateGame();
                lastUpdate = System.nanoTime();
                updates++;
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
//                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public GameOver getGameOver() {
        return gameOver;
    }
}
