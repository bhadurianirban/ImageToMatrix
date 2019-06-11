/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author bhaduri
 */
public class RandomWalkMatix {

    private final RealMatrix origMatrix;
    private RealMatrix randomWalkMatrix;
    private RealMatrix meanSubtractedMatrix;
    private Double matrixMean;

    public RandomWalkMatix(Array2DRowRealMatrix origMatrix) {
        this.origMatrix = origMatrix;

    }

    public RandomWalkMatix(double[][] origMatrix) {
        this.origMatrix = new Array2DRowRealMatrix(origMatrix);

    }

    private void prepareMatrixMean() {

        double mat[][] = origMatrix.getData();

        double flatArray[] = Arrays.stream(mat)
                .flatMapToDouble(Arrays::stream)
                .toArray();
        matrixMean = Arrays.stream(flatArray).average().getAsDouble();
        //System.out.println(s);
    }

    private void cumulateMatrix() {
        int columnCount = meanSubtractedMatrix.getColumnDimension();
        int rowCount = meanSubtractedMatrix.getRowDimension();
        //double randomWalkMatrixD[][] = new double[rowCount][columnCount];
        randomWalkMatrix = new Array2DRowRealMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                if (row == 0 && col==0) {
                    //randomWalkMatrixD[row][col] = origMatrix.getEntry(row, col);
                    double cumValue = meanSubtractedMatrix.getEntry(row, col);
                    randomWalkMatrix.setEntry(row, col, cumValue);
                } else if (row == 0 && col!=0) {
                    double cumValue = randomWalkMatrix.getEntry(0, col - 1) + meanSubtractedMatrix.getEntry(row, col);
                    randomWalkMatrix.setEntry(row, col, cumValue);
                } else if (col == 0 && row!=0) {
                    double cumValue = randomWalkMatrix.getEntry(row -1, 0) + meanSubtractedMatrix.getEntry(row, col);
                    randomWalkMatrix.setEntry(row, col, cumValue);
                } else {
                    double cumValue =  randomWalkMatrix.getEntry(row , col - 1)
                                     + randomWalkMatrix.getEntry(row-1 , col)
                                     + meanSubtractedMatrix.getEntry(row, col);
                    randomWalkMatrix.setEntry(row, col, cumValue);
                }

            }

        }
        
    }

    public Double getMatrixMean() {
        prepareMatrixMean();
        return matrixMean;
    }

    public RealMatrix getCumulativeMatrix() {
        prepareMeanSubtractedMatrix();
        cumulateMatrix();
        return randomWalkMatrix;
    }
    private void prepareMeanSubtractedMatrix() {
        prepareMatrixMean();
        meanSubtractedMatrix = origMatrix.scalarAdd(0-matrixMean);
    }

    public RealMatrix getMeanSubtractedMatrix() {
        prepareMeanSubtractedMatrix();
        return meanSubtractedMatrix;
    }
    
    public FQ FQ() {
        prepareMeanSubtractedMatrix();
        cumulateMatrix();
        return new FQ(randomWalkMatrix);
    }
    

}
