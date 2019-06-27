/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 *
 * @author dgrfi
 */
public class FD extends Fluctuations{

    
    
    private SimpleRegression scaleRMSLogFit;

    public FD(RealMatrix inputMatrix) {
        super(inputMatrix);
    }

    public FD(RealMatrix inputMatrix, int numberOfScales) {
        super(inputMatrix, numberOfScales);
    }
    

    private void prepareScaleRMSLogFit() {
        scaleRMSLogFit = new SimpleRegression(true);
        scaleMappedFluctuationsList.stream().forEach(scm -> scaleRMSLogFit.addData(scm.getLogOfMatrixScale(), scm.getLogOfQuadraticMeanOfFluctuations()));

    }
    public SimpleRegression getScaleRMSLogFit() {
        prepareMatrixScales();
        prepareFluctuations();
        prepareScaleRMSLogFit();
        return scaleRMSLogFit;

    }
}
