package org.example;

import java.awt.image.BufferedImage;
import java.util.Random;

public class NoiseFilterProcessor extends ImageProcessor {
    @Override
    public BufferedImage process(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Random random = new Random();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int noise = random.nextInt(50) - 25;
                int r = Math.min(255, Math.max(0, ((rgb >> 16) & 0xFF) + noise));
                int g = Math.min(255, Math.max(0, ((rgb >> 8) & 0xFF) + noise));
                int b = Math.min(255, Math.max(0, (rgb & 0xFF) + noise));
                int newRgb = (r << 16) | (g << 8) | b;
                result.setRGB(x, y, newRgb);
            }
        }
        return result;
    }
}

