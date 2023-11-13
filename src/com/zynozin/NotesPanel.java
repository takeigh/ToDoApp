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
    }

    // Method to create a new note sheet
    private void createNoteSheet(String sheetName) {
        NotesArea newNotesArea = new NotesArea();
        noteSheets.put(sheetName, newNotesArea);
        noteSheetSelector.addItem(sheetName);

        // Set the first created note sheet as the current one
        if (noteSheets.size() == 1) {
            selectNoteSheet(sheetName);
        }
    }

    public void writeSavedElements() throws IOException {
        BufferedReader notes = new BufferedReader(new FileReader("files/notes/notes1.txt"));
        String notesLine = notes.readLine();
        try {
            StringBuilder sb = new StringBuilder();
            while (notesLine != null) {
                sb.append(notesLine);
                sb.append(System.lineSeparator());
                notesLine = notes.readLine();
            }
            this.noteSheets.get(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            notes.close();
        }
    }

    // Method to switch between note sheets
    private void updateNoteSheet() {
        String selectedSheet = (String) noteSheetSelector.getSelectedItem();
        selectNoteSheet(selectedSheet);
    }

    // Method to select a specific note sheet
    private void selectNoteSheet(String sheetName) {
        NotesArea selectedNotes = noteSheets.get(sheetName);
        modernScrollPane.setViewportView(selectedNotes);
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


