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
import org.apache.commons.math3.linear.MatrixUtils;
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

        int columnDimension = image.getWidth();
        int rowDimension = image.getHeight();
        System.out.println("width, height: " + columnDimension + ", " +rowDimension );
        colorMatrix = new Array2DRowRealMatrix(rowDimension,columnDimension);
        if (null != color) {
            switch (color) {
                case RED:
                    for (int col = 0; col < columnDimension; col++) {
                        for (int row = 0; row < rowDimension; row++) {

                            int pixel = image.getRGB(col, row);//note that col is x and row is y 0 0 is the top left position so it is row 0 and col 0
                            int red = (pixel >> 16) & 0xff;
                            colorMatrix.setEntry(row, col, Double.valueOf(red));
                        }
                    }
                    break;
                case GREEN:
                    for (int col = 0; col < columnDimension; col++) {
                        for (int row = 0; row < rowDimension; row++) {

                            int pixel = image.getRGB(col, row);//note that col is x and row is y 0 0 is the top left position so it is row 0 and col 0
                            int green = (pixel >> 8) & 0xff;
                            colorMatrix.setEntry(row, col, Double.valueOf(green));
                        }
                    }
                    break;
                case BLUE:
                    for (int col = 0; col < columnDimension; col++) {
                        for (int row = 0; row < rowDimension; row++) {

                            int pixel = image.getRGB(col, row);//note that col is x and row is y 0 0 is the top left position so it is row 0 and col 0
                            int blue = (pixel) & 0xff;
                            colorMatrix.setEntry(row, col, Double.valueOf(blue));
                        }
                    }
                    break;
                default:
                    break;
            }
        }

    }

    private void printColorMatrix() {

        System.out.println("colum" + colorMatrix.getColumnDimension() + "row " + colorMatrix.getRowDimension());
    }

    private void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
    }

}
