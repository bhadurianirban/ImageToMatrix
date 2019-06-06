/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

/**
 *
 * @author bhaduri
 */
public class RandomWalkMatix {
    private Array2DRowRealMatrix origMatrix;
    private Array2DRowRealMatrix randomWalkMatrix;
    private Double matrixMean;

    public RandomWalkMatix(Array2DRowRealMatrix origMatrix) {
        this.origMatrix = origMatrix;
    }
    private void getMatrixMean () {
        int rowCount = origMatrix.getRowDimension();
        int colCount = origMatrix.getRowDimension();
        double mat[][] = origMatrix.getData();
        
        Double sum=0.0;
        for (int row=0;row<rowCount;row++) {
            double rowData[]=origMatrix.getRow(row);
            //sum = Double.valueOf(Arrays.stream(mat).
        }
    }
    
}
