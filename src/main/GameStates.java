package main;

public enum GameStates {
    MENU, PLAYING, GAME_OVER;
    public static GameStates gameState = MENU;
    public static void SetGameState(GameStates state) {
        gameState = state;
    }
}
