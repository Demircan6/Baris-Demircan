package org.example;


import java.awt.image.BufferedImage;

public class ImageModel {
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    // Image processing methods
    public void applyProcessor(ImageProcessor processor) {
        image = processor.process(image);
    }
}
