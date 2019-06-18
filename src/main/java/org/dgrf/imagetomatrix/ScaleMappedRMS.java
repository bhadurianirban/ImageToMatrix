/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

/**
 *
 * @author dgrfi
 */
public class ScaleMappedRMS {
    private MatrixScale matrixScale;
    
    private double RMS;
    private double logOfMatrixScale;
    private double logOfRMS;

    public ScaleMappedRMS(MatrixScale matrixScale, double RMS) {
        this.matrixScale = matrixScale;
        this.RMS = RMS;
    }

    public void setMatrixScale(MatrixScale matrixScale) {
        this.matrixScale = matrixScale;
    }

    public void setRMS(double RMS) {
        this.RMS = RMS;
    }

    public MatrixScale getMatrixScale() {
        return matrixScale;
    }

    public double getRMS() {
        return RMS;
    }

    public double getLogOfMatrixScale() {
        double area = matrixScale.getArea();
        logOfMatrixScale = LogUtil.logBaseK(area);
        return logOfMatrixScale;
    }

    public double getLogOfRMS() {
        logOfRMS = LogUtil.logBaseK(RMS);
        return logOfRMS;
    }
    

    
}
