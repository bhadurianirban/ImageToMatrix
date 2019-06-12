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
public class ScaleMappedFD {
    private MatrixScale matrixScale;
    private Double xCoeff;
    private Double yCoeff;
    private Double intercept;
    private Double rSquare;

    public ScaleMappedFD(MatrixScale matrixScale, Double xCoeff, Double yCoeff, Double intercept, Double rSquare) {
        this.matrixScale = matrixScale;
        this.xCoeff = xCoeff;
        this.yCoeff = yCoeff;
        this.intercept = intercept;
        this.rSquare = rSquare;
    }

    public MatrixScale getMatrixScale() {
        return matrixScale;
    }

    public void setMatrixScale(MatrixScale matrixScale) {
        this.matrixScale = matrixScale;
    }


    public Double getxCoeff() {
        return xCoeff;
    }

    public void setxCoeff(Double xCoeff) {
        this.xCoeff = xCoeff;
    }

    public Double getyCoeff() {
        return yCoeff;
    }

    public void setyCoeff(Double yCoeff) {
        this.yCoeff = yCoeff;
    }

    public Double getIntercept() {
        return intercept;
    }

    public void setIntercept(Double intercept) {
        this.intercept = intercept;
    }

    public Double getrSquare() {
        return rSquare;
    }

    public void setrSquare(Double rSquare) {
        this.rSquare = rSquare;
    }
    
    public String toString() {
        String s = "Column Scale:"+matrixScale.getColumnScaleSize()
                +"Row Scale:"+matrixScale.getRowScaleSize()
                +"xCoeff:"+this.xCoeff
                +"yCoeff:"+this.yCoeff;
        return s;
    }
}
