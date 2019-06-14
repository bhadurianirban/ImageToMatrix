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

        //Double d = new ReadImage(imageFilePath).getRandomWalkMatix().getMatrixMean();
        //Double mean = new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.RED).getMatrixMean();
        new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.BLUE).FQ(Boolean.TRUE).getFD();
        double[][] a = {
            {1, 2, 3, 6},
            {4, 5, 6, 9},
            {7, 2, 1, 2},};
        //double mean = new RandomWalkMatix(a).getMatrixMean();
        //System.out.println(mean);
        //RealMatrix d = new RandomWalkMatix(a).getCumulativeMatrix();
        //RealMatrix d = new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.RED).getCumulativeMatrix(Boolean.TRUE);
        //TestUtils.printMatrix(d);


    }

    

}
