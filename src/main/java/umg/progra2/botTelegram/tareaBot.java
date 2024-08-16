package umg.progra2.botTelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class tareaBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "Legion123_bot";
    }

    @Override
    public String getBotToken() {
        return "7372534321:AAG_8D-keAf6RGgEQ5KcnX9dyg1UH2sVi40";
    }

    @Override
    public void onUpdateReceived(Update update) {

        String nombre = update.getMessage().getFrom().getFirstName();
        String apellido = update.getMessage().getFrom().getLastName();
        String nickName = update.getMessage().getFrom().getUserName();
        String numeroCarnet = "0905-23-10816";
        String semestre = "4to Semestre";

        // Tipo de cambio de Euros a Quetzales
        double tipoCambio = 8.47;

        // Lista de chat IDs de tus compañeros
        List<Long> chatIds = List.of(1642696107L,6659791208L,6956666969L,1732022105L);

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM");
        String fecha = fechaActual.format(formatter);

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if(message_text.toLowerCase().equals("/info")){
                sendText(chat_id,"Número de carnet: "+numeroCarnet+
                        "\nNombre: "+nombre+
                        "\nApellido: "+apellido+
                        "\nSemestre: "+semestre);
            }else if(message_text.toLowerCase().equals("/progra")){
                String comentario = "La clase de Programación Orientada a Objetos es fundamental "
                        + "para desarrollar habilidades en el diseño de software utilizando "
                        + "conceptos como herencia, polimorfismo y encapsulamiento. ¡Es una de las bases "
                        + "para convertirse en un desarrollador sólido!";
                sendText(chat_id, comentario);
            }
            else if(message_text.equalsIgnoreCase("/hola")){
                sendText(chat_id, "Hola, " + nombre + ". Hoy es " + fecha);
            }else if(message_text.startsWith("/cambio")){
                try {
                    String[] parts = message_text.split(" ");
                    if (parts.length == 2) {
                        double euros = Double.parseDouble(parts[1]);
                        double quetzales = euros * tipoCambio;
                        String respuesta = String.format("Son %.2f quetzales.", quetzales);
                        sendText(chat_id, respuesta);
                    } else {
                        sendText(chat_id, "Por favor, proporciona la cantidad de Euros. Ejemplo: /cambio 50");
                    }
                } catch (NumberFormatException e) {
                    sendText(chat_id, "Cantidad de Euros inválida. Por favor, ingresa un número válido.");
                }
            }else if (message_text.startsWith("/grupal")) {
                String[] parts = message_text.split(" ", 2);
                if (parts.length == 2) {
                    String mensajeGrupal = parts[1];

                    // Enviar el mensaje a cada chat ID en la lista
                    for (Long id : chatIds) {
                        sendText(id, mensajeGrupal);
                    }
                } else {
                    sendText(chat_id, "Por favor, proporciona un mensaje después del comando. Ejemplo: /grupal Hola a todos");
                }
            }

            System.out.println("User id: " + chat_id + " Message: " + message_text);
            System.out.println("Hola " +nombre+" "+apellido);
            System.out.println("Enviaste:"+message_text);
        }
    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }


}
