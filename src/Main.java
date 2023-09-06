import views.MyTerminalApp;

/**
 * Classe principale dell'applicazione.
 * Questa classe contiene il metodo `main` che avvia l'applicazione.
 */
public class Main {
    /**
     * Il metodo principale che avvia l'applicazione.
     * Crea un'istanza di `MyTerminalApp` e avvia il suo metodo `start`.
     *
     * @param args Gli argomenti passati dalla riga di comando (non utilizzati in questo caso).
     */
    public static void main(String[] args) {
        // Crea un'istanza di MyTerminalApp
        MyTerminalApp myTerminalApp = new MyTerminalApp();

        // Avvia l'applicazione chiamando il metodo start
        myTerminalApp.start();
    }
}
