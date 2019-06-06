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
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import static org.dgrf.imagetomatrix.COLORCCHOICE.BLUE;
import static org.dgrf.imagetomatrix.COLORCCHOICE.GREEN;
import static org.dgrf.imagetomatrix.COLORCCHOICE.RED;

/**
 *
 * @author dgrfi
 */
enum COLORCCHOICE {
    RED,
    GREEN,
    BLUE
}

public class ReadImage {

    private final String imageFilePath;
    private BufferedImage image;
    private Array2DRowRealMatrix colorMatrix;
    public ReadImage(String imageFilePath) {
        this.imageFilePath = imageFilePath;
        readImage();
    }

    private void readImage() {
        try {
            // the line that reads the image file
            image = ImageIO.read(new File(imageFilePath));
            getClourMatrix(RED);
            printColorMatrix();
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

    private void getClourMatrix(COLORCCHOICE color) {

        int w = image.getWidth();
        int h = image.getHeight();
        System.out.println("width, height: " + w + ", " + h);
        colorMatrix = new Array2DRowRealMatrix(w, h);
        if (null != color) switch (color) {
            case RED:
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        
                        int pixel = image.getRGB(j, i);
                        int red = (pixel >> 16) & 0xff;
                        colorMatrix.setEntry(j, i, Double.valueOf(red));
                    }
                }   break;
            case GREEN:
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        
                        int pixel = image.getRGB(i, j);
                        int green = (pixel >> 8) & 0xff;
                        colorMatrix.setEntry(j, i, Double.valueOf(green));
                    }
                }   break;
            case BLUE:
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        
                        int pixel = image.getRGB(i, j);
                        int blue = (pixel) & 0xff;
                        colorMatrix.setEntry(j, i, Double.valueOf(blue));
                    }
                }   break;
            default:
                break;
        }

    }

    private void printColorMatrix() {
        
        System.out.println(colorMatrix.toString());
    }

    private void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
    }

    
}
