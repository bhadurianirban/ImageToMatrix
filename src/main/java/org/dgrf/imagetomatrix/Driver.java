package org.dgrf.imagetomatrix;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dgrfi
 */
public class Driver {

    public static void main(String args[]) {
        String imageFilePath = "/home/bhaduri/MEGA/DGRFFractal/testdata/DFA2D/radha.jpg";

        //TestUtils.printMatrix(d);
        
        //String tagoreImageFilePath = "/home/bhaduri/MEGA/DGRFFractal/testdata/DFA2D/Tagore.jpg";
        //String outFilePath = "/home/dgrfi/MEGA/DGRFFractal/testdata/DFA2D/radhaGreen.csv";
        //new ReadImage(imageFilePath).writeMatrixToFile(outFilePath,COLORCCHOICE.GREEN);

//        Double mean = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.BLUE).getMatrixMean(Boolean.FALSE);
//        System.out.println(mean);
//        RealMatrix d = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.BLUE).getMeanSubtractedMatrix(Boolean.FALSE);
//        TestUtils.printMatrix(d);
//        System.out.println("+++++++++++++++++++++++++++++++");
//        RealMatrix c = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.BLUE).getCumulativeMatrix(Boolean.FALSE);
//        TestUtils.printMatrix(c);
        //Double mean = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.RED).getMatrixMean();
        //System.out.println(new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.BLUE).getMeanSubtrated(Boolean.TRUE).getScaleRMSLogFit().getSlope());
        //System.out.println(new ReadImage(tagoreImageFilePath).getInputMatrix(COLORCCHOICE.BLUE).getMeanSubtrated(Boolean.TRUE).getScaleRMSLogFit().getSlope());
        //String imageFolderPath = "/home/dgrfi/Pictures/dance";
        //TestUtils.calculateForFolder(imageFolderPath);
        
        TestUtils.testLinSpace();
    }

    

}
