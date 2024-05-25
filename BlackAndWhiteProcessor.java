package org.example;

import java.awt.image.BufferedImage;

public class BlackAndWhiteProcessor extends ImageProcessor {
    @Override
    public BufferedImage process(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                result.setRGB(x, y, rgb);
            }
        }
        return result;
    }
}

