/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author dgrfi
 */
public class ReadImage {

    String imageFilePath;

    public ReadImage(String imageFilePath) {
        this.imageFilePath = imageFilePath;
        readImage();
    }

    private void readImage() {
        try {
            // the line that reads the image file
            BufferedImage image = ImageIO.read(new File(imageFilePath));

            int w = image.getWidth();
            int h = image.getHeight();
            System.out.println("width, height: " + w + ", " + h);

//            for (int i = 0; i < h; i++) {
//                for (int j = 0; j < w; j++) {
//                    System.out.println("x,y: " + j + ", " + i);
//                    int pixel = image.getRGB(j, i);
//                    printPixelARGB(pixel);
//                    System.out.println("");
//                }
//            }
        } catch (IOException e) {
            // log the exception
            // re-throw if desired
        }
    }

    private void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
    }
}
