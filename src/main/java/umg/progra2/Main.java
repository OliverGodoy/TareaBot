package umg.progra2;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import umg.progra2.botTelegram.tareaBot;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    try{
        TelegramBotsApi botApi = new TelegramBotsApi(DefaultBotSession.class);
        tareaBot mibot = new tareaBot();

        botApi.registerBot(mibot);

        System.out.println("El bot se está ejecutando!!!");
        System.out.println("Para consultar: /hola");

    }catch(Exception e){
        System.out.println("Error al instanciar Telegram " + e.getMessage());
    }

    }
}