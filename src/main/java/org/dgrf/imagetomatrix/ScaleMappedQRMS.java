/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.util.List;

/**
 *
 * @author dgrfi
 */
public class ScaleMappedQRMS {
    private MatrixScale matrixScale;
    
    private List<Double> qRMS;
    private double logOfMatrixScale;
    private double logOfRMS;

    public ScaleMappedQRMS(MatrixScale matrixScale, List<Double> qRMS) {
        this.matrixScale = matrixScale;
        this.qRMS = qRMS;
    }

    public void setMatrixScale(MatrixScale matrixScale) {
        this.matrixScale = matrixScale;
    }


    public MatrixScale getMatrixScale() {
        return matrixScale;
    }

    

    public double getLogOfMatrixScale() {
        double area = matrixScale.getArea();
        logOfMatrixScale = LogUtil.logBaseK(area);
        return logOfMatrixScale;
    }

    public double getLogOfRMS() {
        //logOfRMS = LogUtil.logBaseK(RMS);
        return logOfRMS;
    }
    

    
}
