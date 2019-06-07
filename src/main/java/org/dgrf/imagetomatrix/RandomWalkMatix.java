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
    private final Array2DRowRealMatrix origMatrix;
    private Array2DRowRealMatrix randomWalkMatrix;
    private Double matrixMean;

    public RandomWalkMatix(Array2DRowRealMatrix origMatrix) {
        this.origMatrix = origMatrix;
        
    }
    private void  prepareMatrixMean () {
        
        double mat[][] = origMatrix.getData();
        
        double flatArray[] = Arrays.stream(mat)
        .flatMapToDouble(Arrays::stream)
        .toArray();
        matrixMean = Arrays.stream(flatArray).average().getAsDouble();
        //System.out.println(s);
    }
    private void cumulateMatrix() {
        
    }
    public Double getMatrixMean() {
        prepareMatrixMean();
        return matrixMean;
    }
    
    private void getCumulativeRowColumnValue(int row,int col) {
        
    }
    
    
}
