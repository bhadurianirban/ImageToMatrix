/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 *
 * @author dgrfi
 */
public class FQ extends Fluctuations{

    private List<ScaleMappedQRMS> scaleMappedQRMSList;
    private List<Double> qLinSpqceList;

    public FQ(RealMatrix inputMatrix) {
        super(inputMatrix);
    }

    public FQ(RealMatrix inputMatrix, int numberOfScales) {
        super(inputMatrix, numberOfScales);
    }

    private void prepareQLinSpaceList() {
        LinSpace linSpace = new LinSpace(-5.0, 5.0, 101);
        this.qLinSpqceList = linSpace.getLinSpaceList();
    }

//    private void prepareFQ() {
//        scaleMappedQRMSList = matrixScales.stream().map(ms -> prepareScaleMappedRMS(ms)).collect(Collectors.toList());
//    }

    private void prepareFQ() {
        
    }
    private ScaleMappedQRMS prepareScaleMappedRMS(MatrixScale matrixScale) {
        List<Double> meanResidualSquaredSumQRMS = prepareMeanResidualQPowerForAScale(matrixScale);
        ScaleMappedQRMS scaleMappedqRMS = new ScaleMappedQRMS(matrixScale, meanResidualSquaredSumQRMS);
        return scaleMappedqRMS;
    }

    private List<Double> prepareMeanResidualQPowerForAScale(MatrixScale matrixScale) {
        List<SubMatrixCoordinates> subMatrixCoordinatesList = prepareSubMatrixCoordinatesForAScale(matrixScale);

        List<Double> meanResidualSquaredSumList = subMatrixCoordinatesList.stream().map(m -> prepareMeanResidualSquareForASubmatrix(m)).collect(Collectors.toList());
        List<Double> meanResidualSquaredSumQRMS = getQRMSValue(meanResidualSquaredSumList);
        return meanResidualSquaredSumQRMS;
    }

    

    private List<Double> getQRMSValue(List<Double> meanResidualSquaredSumList) {
        
        List<Double> FQ = qLinSpqceList.stream().map(q -> getQPoweredMean(q, meanResidualSquaredSumList)).collect(Collectors.toList());
        return FQ;
    }

    private Double getQPoweredMean(Double q, List<Double> meanResidualSquaredSumList) {
        Double qRMS;
        List<Double> qPoweredRMS = meanResidualSquaredSumList.stream().map(mrss->calcqPower(mrss, q)).collect(Collectors.toList());
        Double meanQPoweredRMS = qPoweredRMS.stream().mapToDouble(a -> a).average().getAsDouble();
        if (q == 0) {
            qRMS = Math.exp(0.5 * meanQPoweredRMS);
        } else {
            qRMS = Math.pow(meanQPoweredRMS, 1/q);
        }

        return qRMS;
    }

    private Double calcqPower(Double rms, Double q) {
        if (q == 0) {
            if (rms == 0) {
                return 0.0;
            } else {
                Double qPower = Math.log(Math.pow(rms, 2));
                return qPower;
            }
        } else {
            if (rms == 0) {
                return 0.0;
            } else {
                Double qPower = Math.pow(rms, q);
                return qPower;
            }
        }

    }

    public List<ScaleMappedQRMS> getScaleMappedQRMSList() {
        prepareMatrixScales();
        prepareQLinSpaceList();
        prepareFQ();
        return scaleMappedQRMSList;
    }

    public List<Double> getQLinSpaceList() {
        prepareQLinSpaceList();
        return qLinSpqceList;
    }
}
