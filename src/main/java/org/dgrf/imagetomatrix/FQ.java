/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.util.ArrayList;
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
    private List<MatrixScale> matrixScales;
    private int numberOfScales;

    public FQ(RealMatrix inputMatrix) {
        this.inputMatrix = inputMatrix;
        this.columnScaleMax = Double.valueOf(inputMatrix.getColumnDimension());
        this.rowScaleMax = Double.valueOf(inputMatrix.getRowDimension());
        this.columnScaleMin = this.rowScaleMin = 16.0;
        numberOfScales = 19;
    }

    private void prepareColumnScales() {
        Double columnExponentMin = LogUtil.logBaseK(columnScaleMin);
        Double columnExponentMax = LogUtil.logBaseK(columnScaleMax);
        
        Double rowExponentMin = LogUtil.logBaseK(rowScaleMin);
        Double rowExponentMax = LogUtil.logBaseK(rowScaleMax);
        
        LinSpace colomnExpLinSpace = new LinSpace(columnExponentMin, columnExponentMax, numberOfScales);
        LinSpace rowExpLinSpace = new LinSpace(rowExponentMin, rowExponentMax, numberOfScales);
        
        matrixScales = new ArrayList<>();
        for (int expCounter = 0; expCounter < numberOfScales; expCounter++) {
            int columnScaleSize = (int) Math.round(Math.pow(2, colomnExpLinSpace.getLinSpaceElement(expCounter)));
            int rowScaleSize = (int) Math.round(Math.pow(2, rowExpLinSpace.getLinSpaceElement(expCounter)));
            MatrixScale matrixScale = new MatrixScale(columnScaleSize, rowScaleSize);
            matrixScales.add(matrixScale);
            //System.out.println(columnScaleSize+" "+rowScaleSize);
        }
    }
    public List<MatrixScale> getScales() {
        prepareColumnScales();
        return matrixScales;
    }

}
