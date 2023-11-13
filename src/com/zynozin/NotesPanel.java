package com.zynozin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotesPanel extends JPanel {
    private final int WIDTH = 1120;
    private final int HEIGHT = 425;
    private ModernScrollPane modernScrollPane;
    JComboBox<String> noteSheetSelector;
    Map<String, NotesArea> noteSheets;  // Use NotesArea instead of JTextArea
    public static List<ListOfItems> lastNotesSave = new ArrayList<>();

    public NotesPanel() {
        noteSheets = new HashMap<>();
        noteSheetSelector = new JComboBox<>();
        modernScrollPane = new ModernScrollPane(new NotesArea());
        modernScrollPane.setBorder(new MatteBorder(3, 3, 3, 3, Color.darkGray));

        setBackground(new Color(37, 37, 37));
        setOpaque(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new FlowLayout());

        // Add a JComboBox to select note sheets
        noteSheetSelector.addActionListener(e -> updateNoteSheet());
        add(noteSheetSelector, BorderLayout.NORTH);
        add(modernScrollPane, BorderLayout.CENTER);

        // Create the default note sheet
        createNoteSheet("Sheet 1");
        createNoteSheet("Sheet 2");
        createNoteSheet("Sheet 3");
        createNoteSheet("Sheet 4");
        createNoteSheet("Sheet 5");
        createNoteSheet("Sheet 6");
        createNoteSheet("Sheet 7");


        loadAllNoteSheets();

    }

    // Method to create a new note sheet
    public void createNoteSheet(String sheetName) {
        NotesArea newNotesArea = new NotesArea();
        noteSheets.put(sheetName, newNotesArea);
        noteSheetSelector.addItem(sheetName);

        // Set the first created note sheet as the current one
        if (noteSheets.size() == 1) {
            selectNoteSheet(sheetName);
        }
    }

    // New method to save a specific note sheet to a file
    public void saveNoteSheet(String sheetName) throws IOException {
        PrintWriter writer = new PrintWriter("files/notes/" + sheetName + ".txt");
        try {
            writer.write(noteSheets.get(sheetName).getText());
        } finally {
            writer.close();
        }
    }
    // New method to load a specific note sheet from a file
    public void loadNoteSheet(String sheetName) throws IOException {
        BufferedReader notes = new BufferedReader(new FileReader("files/notes/" + sheetName + ".txt"));
        StringBuilder sb = new StringBuilder();
        try {
            String notesLine = notes.readLine();
            while (notesLine != null) {
                sb.append(notesLine);
                sb.append(System.lineSeparator());
                notesLine = notes.readLine();
            }
        } finally {
            notes.close();
        }
        noteSheets.get(sheetName).setText(sb.toString());
    }

    // New method to save all note sheets
    public void saveAllNoteSheets() {
        for (String sheetName : noteSheets.keySet()) {
            try {
                saveNoteSheet(sheetName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // New method to load all note sheets
    public void loadAllNoteSheets() {
        for (String sheetName : noteSheets.keySet()) {
            try {
                loadNoteSheet(sheetName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Method to switch between note sheets
    public void updateNoteSheet() {
        String selectedSheet = (String) noteSheetSelector.getSelectedItem();
        selectNoteSheet(selectedSheet);
    }

    // Method to select a specific note sheet
    public void selectNoteSheet(String sheetName) {
        NotesArea selectedNotes = noteSheets.get(sheetName);
        modernScrollPane.setViewportView(selectedNotes);
    }
    // New method to get the size of note sheets
    public int getNoteSheetsSize() {
        return noteSheets.size();
    }

    // New method to check if a note sheet exists
    public boolean doesNoteSheetExist(String sheetName) {
        return noteSheets.containsKey(sheetName);
    }

    // New method to get the currently selected note sheet
    public String getSelectedNoteSheet() {
        return (String) noteSheetSelector.getSelectedItem();
    }

    public void writeSavedElements() throws IOException {
        // Save all note sheets before reading from the file
        saveAllNoteSheets();

        // Load the content of all note sheets from the file
        loadAllNoteSheets();
    }
    public class NotesArea extends JTextArea {
        private Font notesFont = Main.getFontforApp(18f, "fonts/Montserrat-Light.ttf");

        public NotesArea() {
            this.setBackground(new Color(20, 20, 20));
            this.setLineWrap(true);
            this.setCaretColor(Color.WHITE);
            this.setWrapStyleWord(true);
            this.setOpaque(true);
            this.setBounds(20, 20, 1000, 1000);
            this.setPreferredSize(new Dimension(1000, 455));
            this.setBorder(new EmptyBorder(7, 5, 0, 5));
            this.setFont(notesFont);
            this.setForeground(Color.WHITE);
        }
    }

}


