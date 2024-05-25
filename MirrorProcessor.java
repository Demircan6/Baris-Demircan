package org.example;

import java.awt.image.BufferedImage;

public class MirrorProcessor extends ImageProcessor {
    @Override
    public BufferedImage process(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                result.setRGB(image.getWidth() - 1 - x, y, image.getRGB(x, y));
            }
        }
        return result;
    }
}

