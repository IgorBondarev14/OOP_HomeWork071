package controller;

import model.Logger;
import model.Note;
import model.Storage;

import java.util.List;

public class Controller {
    private final Storage storage;

    public Controller(Storage storage) {
        this.storage = storage;
    }

    public void saveNote(Note note) throws Exception {
        checkNote(note);
        storage.CreateNote(note);
    }

    public Note readNote(String id) throws Exception {
        List<Note> notes = storage.getAllNotes();
        for (Note n : notes) {
            if (n.getId().equals(id)) {
                return n;
            }
        }
        Logger.logger("Искомый id не найден");
        throw new Exception("Искомый id не найден");
    }

    public List<Note> readAll() {
        List<Note> result = storage.getAllNotes();
        return result;
    }

    public void updateNote(String id, Note newNote) throws Exception {
        idValidation(id);
        newNote.setId(id);
        checkNoteId(newNote);
        storage.updateNote(newNote);
    }

    private static void checkNote(Note note) throws Exception {
        if (note.getName().equals(" ")) {
            Logger.logger("Название записки отсутсвует");
            throw new Exception("Название записки отсутсвует");
        }
        if (note.getText().equals(" ")) {
            Logger.logger("Содержание записки пустое");
            throw new Exception("Содержание записки пустое");
        }
    }

    private void checkNoteId(Note note) throws Exception {
        if (note.getId().isEmpty())
            throw new Exception("Искомый id не найден");
        checkNote(note);
    }

    public void idValidation(String inputId) throws Exception {
        List<Note> notes = storage.getAllNotes();
        for (Note n : notes) {
            if (n.getId().equals(inputId))
                return;
        }
        Logger.logger("Искомый id не найден");
        throw new Exception("Искомый id не найден");
    }

    public void deleteNote(String delId) {
        storage.deleteNote(delId);
    }
}

