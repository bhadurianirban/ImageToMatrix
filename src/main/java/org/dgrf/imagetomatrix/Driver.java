package org.dgrf.imagetomatrix;

import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

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
        String imageFilePath = "/home/dgrfi/MEGA/DGRFFractal/testdata/DFA2D/radha.jpg";
        //String outFilePath = "/home/dgrfi/MEGA/DGRFFractal/testdata/DFA2D/radhaGreen.csv";
        //new ReadImage(imageFilePath).writeMatrixToFile(outFilePath,COLORCCHOICE.GREEN);

//        Double mean = new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.BLUE).getMatrixMean(Boolean.FALSE);
//        System.out.println(mean);
//        RealMatrix d = new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.BLUE).getMeanSubtractedMatrix(Boolean.FALSE);
//        TestUtils.printMatrix(d);
//        System.out.println("+++++++++++++++++++++++++++++++");
//        RealMatrix c = new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.BLUE).getCumulativeMatrix(Boolean.FALSE);
//        TestUtils.printMatrix(c);
        //Double mean = new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.RED).getMatrixMean();
        new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.BLUE).FQMeanSubtrated(Boolean.TRUE).getFD();
        
        


    }

    

}
