/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.util.List;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author dgrfi
 */
public class FQ {
    private RealMatrix inputMatrix;
    private Double columnScaleMax;
    private Double rowScaleMax;
    private Double columnScaleMin;
    private Double rowScaleMin;
    private List<Double> columnScales;
    private int numberOfScales;
    public FQ(RealMatrix inputMatrix) {
        this.inputMatrix = inputMatrix;
        this.columnScaleMax = Double.valueOf(inputMatrix.getColumnDimension());
        this.rowScaleMax = Double.valueOf(inputMatrix.getRowDimension());
        this.columnScaleMin = this.rowScaleMin = 16.0;
    }
    private void prepareColumnScales () {
        
    }
    
    
    
    
}
