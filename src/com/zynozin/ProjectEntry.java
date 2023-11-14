package com.zynozin;

import components.DocumentSizeFilter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;

public class ProjectEntry extends JPanel {
    public JTextField[] textFields = new JTextField[2];
    public JTextField titleField;
    public JTextField descriptionField;

    public JPanel entryPanel = new JPanel(new GridLayout(2,1));

    public CheckBox ideaBox = new CheckBox(new ImageIcon("images/idea.png"));
    private final Font projectsFont = Main.getFontforApp(18f, "fonts/Montserrat-Light.ttf");
    private final DefaultStyledDocument titleDoc = new DefaultStyledDocument();
    private final DefaultStyledDocument descDoc = new DefaultStyledDocument();
    private final ProjectDeleter delete;

    private void setFormat() {
        entryPanel.setOpaque(false);
        entryPanel.setForeground(Color.WHITE);
        entryPanel.setFont(projectsFont);
    }

    public ProjectEntry() {
        // Create an empty title textbox
        titleField = new JTextField();
        titleField.setOpaque(true);
        titleField.setBackground(new Color(200, 200, 200));
        titleField.setFont(projectsFont);
        titleField.setCaretColor(Color.WHITE);

        // Attach a document to the title field
        titleDoc.setDocumentFilter(new DocumentSizeFilter(100));
        titleField.setDocument(titleDoc);

        // Create an empty description textbox
        descriptionField = new JTextField();
        descriptionField.setOpaque(true);
        descriptionField.setBackground(new Color(150, 150, 150));
        descriptionField.setFont(projectsFont);
        descriptionField.setCaretColor(Color.WHITE);

        // Attach a document to the description field
        descDoc.setDocumentFilter(new DocumentSizeFilter(600));
        descriptionField.setDocument(descDoc);

        // Put the Labels within the Panel
        entryPanel.add(titleField);
        entryPanel.add(descriptionField);
        setFormat();

        // Add to the textFields Array
        textFields[0] = titleField;
        textFields[1] = descriptionField;

        // Create a deletion button
        delete = new ProjectDeleter(this);


        setOpaque(false);
        setBackground(new Color(37, 37, 37));
        setBorder(new MatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
        setOpaque(true);
        setPreferredSize(new Dimension(900, 30));
        setLayout(new BorderLayout());

        // Add the empty text field
        add(entryPanel, BorderLayout.CENTER);

        // Add Idea Icon
        add(ideaBox, BorderLayout.WEST);

        // Add deletion Icon
        add(delete, BorderLayout.EAST);
    }
}
