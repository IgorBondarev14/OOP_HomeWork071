package viewUser;

import controller.Controller;
import model.Note;
import model.Logger;

import java.util.List;
import java.util.Scanner;

public class View {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        Commands command = Commands.NONE;

        while (true) {
            String com = handInput("\n(Возможные варианты команд: Help, Create, Change, Delete, Search, Read, " +
                    "Exit)\nДля подробной информации выберите Help.\n" +
                    "Введите команду (регистр значения не имеет): ");
            Logger.logger(com);
            command = Commands.valueOf(com.toLowerCase());
            ;
            if (command.equals(Commands.exit)) return;
            try {
                switch (command) {
                    case help:
                        System.out.println("Help   - описание команд\n" +
                                "Create - создание новой записки\nChange - изменение записки\nDelete - удаление " +
                                "записки\nSearch - поиск и просмотр записки\nRead - просмотр всех записок\n" +
                                "Exit - закрытие программы");
                        break;
                    case create:
                        String name = handInput("Название записки: ");
                        Logger.logger(name);
                        String text = handInput("Содержание записки: ");
                        Logger.logger(text);
                        controller.saveNote(new Note(name, text));
                        break;
                    case change:
                        String id = handInput("Какую запись редактировать? Введите номер ID: ");
                        Logger.logger(id);
                        controller.idValidation(id);
                        controller.updateNote(id, createOne());
                        break;
                    case delete:
                        String delId = handInput("Какую запись удалить? Введите номер ID: ");
                        Logger.logger(delId);
                        controller.idValidation(delId);
                        controller.deleteNote(delId);
                        break;
                    case search:
                        String searchId = handInput("Введите id искомой записи: ");
                        Logger.logger(searchId);
                        Note note = controller.readNote(searchId);
                        System.out.println(note);
                        break;
                    case read:
                        final List<Note> notes = controller.readAll();
                        notes.forEach(i -> System.out.println(i + "\n"));
                        break;
                }
            } catch (Exception e) {
                System.out.println("Что-то пошло не так!\n" + e.getMessage());
                ;
            }
        }
    }

    private String handInput(String message) {
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        return scan.nextLine();
    }

    private Note createOne() {
        String name = handInput("Заголовок: ");
        String text = handInput("Текст записки: ");
        Note newNote = new Note(name, text);
        return newNote;
    }
}
