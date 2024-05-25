package org.example;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import jakarta.mail.MessagingException;
import javax.imageio.ImageIO;

public class ImageController {
    private ImageModel model;
    private ImageView view;

    public ImageController(ImageModel model, ImageView view) {
        this.model = model;
        this.view = view;

        view.addOpenButtonListener(new OpenButtonListener());
        view.addSaveButtonListener(new SaveButtonListener());
        view.addEmailButtonListener(new EmailButtonListener());
        view.addActionComboBoxListener(new ActionComboBoxListener());
    }

    class OpenButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage image = ImageIO.read(fileChooser.getSelectedFile());
                    model.setImage(image);
                    view.setImage(image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
                try {
                    ImageIO.write(model.getImage(), "png", new File(fileChooser.getSelectedFile().getPath() + ".png"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class EmailButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = JOptionPane.showInputDialog(view, "Enter email address:");
            if (email != null && !email.isEmpty()) {
                try {
                    File tempFile = File.createTempFile("image", ".png");
                    ImageIO.write(model.getImage(), "png", tempFile);
                    Main.EmailSender.sendEmail(email, "Processed Image", "Please find the attached image.", tempFile);
                } catch (IOException | MessagingException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(view, "Failed to send email.");
                }
            }
        }
    }

    class ActionComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String action = view.getSelectedAction();
            ImageProcessor processor;
            switch (action) {
                case "Black and White":
                    processor = new BlackAndWhiteProcessor();
                    break;
                case "Noise Filter":
                    processor = new NoiseFilterProcessor();
                    break;
                case "Invert":
                    processor = new InvertProcessor();
                    break;
                case "Mirror":
                    processor = new MirrorProcessor();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + action);
            }
            model.applyProcessor(processor);
            view.setImage(model.getImage());
        }
    }
}
