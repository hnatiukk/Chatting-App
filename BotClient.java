package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class BotClient extends Client{
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public static void main(String[] args) {
        new BotClient().run();
    }

    public class BotSocketThread extends Client.SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String text = null;
            String[] array = null;
            try {
                array = message.split(": ");
                text = array[1];
            } catch (Exception e) {
                return;
            }
            Calendar date = Calendar.getInstance();
            switch (text) {
                case "дата":
                    BotClient.this.sendTextMessage("Информация для " + array[0] + ": " + new SimpleDateFormat("d.MM.YYYY").format(date.getTime()));
                    break;
                case "день":
                    BotClient.this.sendTextMessage("Информация для " + array[0] + ": " + new SimpleDateFormat("d").format(date.getTime()));
                    break;
                case "месяц":
                    BotClient.this.sendTextMessage("Информация для " + array[0] + ": " + new SimpleDateFormat("MMMM").format(date.getTime()));
                    break;
                case "год":
                    BotClient.this.sendTextMessage("Информация для " + array[0] + ": " + new SimpleDateFormat("YYYY").format(date.getTime()));
                    break;
                case "время":
                    BotClient.this.sendTextMessage("Информация для " + array[0] + ": " + new SimpleDateFormat("H:mm:ss").format(date.getTime()));
                    break;
                case "час":
                    BotClient.this.sendTextMessage("Информация для " + array[0] + ": " + new SimpleDateFormat("H").format(date.getTime()));
                    break;
                case "минуты":
                    BotClient.this.sendTextMessage("Информация для " + array[0] + ": " + new SimpleDateFormat("m").format(date.getTime()));
                    break;
                case "секунды":
                    BotClient.this.sendTextMessage("Информация для " + array[0] + ": " + new SimpleDateFormat("s").format(date.getTime()));
                    break;
            }
        }
    }
}
