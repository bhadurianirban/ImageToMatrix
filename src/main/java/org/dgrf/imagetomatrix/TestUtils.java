/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

/**
 *
 * @author dgrfi
 */
public class TestUtils {

    public static void printMatrix(RealMatrix d) {
        for (int row = 0; row < d.getRowDimension(); row++) {
            double rowV[] = d.getRow(row);
            System.out.println(ArrayUtils.toString(rowV));
        }
    }
    public static void TestWithSMallMatrix() {
    double[][] a = {
            {1, 2, 3, 6},
            {4, 5, 6, 9},
            {7, 2, 1, 2},};
        double mean = new RandomWalkMatix(a).getMatrixMean(Boolean.FALSE);
        System.out.println(mean);
        RealMatrix d = new RandomWalkMatix(a).getMeanSubtractedMatrix(Boolean.FALSE);
        TestUtils.printMatrix(d);
        System.out.println("===");
        RealMatrix e = new RandomWalkMatix(a).getCumulativeMatrix(Boolean.FALSE);
        TestUtils.printMatrix(e);
    }
    public static void testMultiRegression() {
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
// weight
        double[] y = new double[]{21,20,16,22,41,44,21,36,41,25};
// height, waist
        double[][] x = new double[10][];
        x[0] = new double[]{2,4};
        x[1] = new double[]{3,3};
        x[2] = new double[]{4,1};
        x[3] = new double[]{1,5};
        x[4] = new double[]{9,6};
        x[5] = new double[]{6,9};
        x[6] = new double[]{2,4};
        x[7] = new double[]{5,7};
        x[8] = new double[]{6,8};
        x[9] = new double[]{7,2};
        regression.newSampleData(y, x);

        //double[] coe = regression.estimateRegressionParameters();
        //for (double p : coe) {
        //    System.out.println(p);
       // }
        System.out.println(regression.calculateRSquared());
    }
}
