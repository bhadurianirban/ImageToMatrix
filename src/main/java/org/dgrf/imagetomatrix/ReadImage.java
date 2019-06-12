/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;


/**
 *
 * @author dgrfi
 */


public class ReadImage {

    private final String imageFilePath;
    private BufferedImage image;
    private RealMatrix colorMatrix;
    private RandomWalkMatix randomWalkMatix;

    public ReadImage(String imageFilePath) {
        this.imageFilePath = imageFilePath;
        
    }

    private void readImage(COLORCCHOICE color) {
        try {
            // the line that reads the image file
            image = ImageIO.read(new File(imageFilePath));
            prepareClourMatrix(color);
            //printColorMatrix();
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

    private void prepareClourMatrix(COLORCCHOICE color) {

        int columnDimension = image.getWidth();
        int rowDimension = image.getHeight();
        //System.out.println("width, height: " + columnDimension + ", " +rowDimension );
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

    
    public RandomWalkMatix randomWalkMatix(COLORCCHOICE colorcchoice) {
        readImage(colorcchoice);
        randomWalkMatix = new RandomWalkMatix(colorMatrix);
        return randomWalkMatix;
    }

    private void printColorMatrix() {

        System.out.println("colum" + colorMatrix.getColumnDimension() + "row " + colorMatrix.getRowDimension());
    }
    public void writeMatrixToFile (String fileName,COLORCCHOICE colorcchoice) {
        readImage(colorcchoice);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName);
            for (int row = 0;row< colorMatrix.getRowDimension();row++) {
                double rowV[] = colorMatrix.getRow(row);
                writer.println(ArrayUtils.toString(rowV));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadImage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
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
