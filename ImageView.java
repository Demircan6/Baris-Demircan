package org.example;





import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage; // Add this import

public class ImageView extends JFrame {
    private JLabel imageLabel;
    private JButton openButton, saveButton, emailButton;
    private JComboBox<String> actionComboBox;

    public ImageView() {
        setTitle("Image Processing Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imageLabel = new JLabel();
        openButton = new JButton("Open Image");
        saveButton = new JButton("Save Image");
        emailButton = new JButton("Send via Email");
        actionComboBox = new JComboBox<>(new String[]{"Black and White", "Noise Filter", "Invert", "Mirror"});

        JPanel controlPanel = new JPanel();
        controlPanel.add(openButton);
        controlPanel.add(actionComboBox);
        controlPanel.add(saveButton);
        controlPanel.add(emailButton);

        add(controlPanel, BorderLayout.SOUTH);
        add(new JScrollPane(imageLabel), BorderLayout.CENTER);
    }

    public void setImage(BufferedImage image) {
        imageLabel.setIcon(new ImageIcon(image));
    }

    public void addOpenButtonListener(ActionListener listener) {
        openButton.addActionListener(listener);
    }

    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void addEmailButtonListener(ActionListener listener) {
        emailButton.addActionListener(listener);
    }

    public void addActionComboBoxListener(ActionListener listener) {
        actionComboBox.addActionListener(listener);
    }

    public String getSelectedAction() {
        return (String) actionComboBox.getSelectedItem();
    }
}

