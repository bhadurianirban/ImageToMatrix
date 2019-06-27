/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 *
 * @author dgrfi
 */
public class FD extends Fluctuations{

    
    private List<ScaleMappedFluctuations> scaleMappedFluctuationsList;
    private SimpleRegression scaleRMSLogFit;

    public FD(RealMatrix inputMatrix) {
        super(inputMatrix);
    }

    public FD(RealMatrix inputMatrix, int numberOfScales) {
        super(inputMatrix, numberOfScales);
    }
    private void prepareFluctuations() {
        scaleMappedFluctuationsList = matrixScales.stream().map(ms -> prepareScaleMappedFluctuations(ms)).collect(Collectors.toList());
    }

    private ScaleMappedFluctuations prepareScaleMappedFluctuations(MatrixScale matrixScale) {
        List<Double> flucuationsListForAScale = prepareFlucuationsListForAScale(matrixScale);
        ScaleMappedFluctuations scaleMappedFluctuations = new ScaleMappedFluctuations(matrixScale, flucuationsListForAScale);
        return scaleMappedFluctuations;
    }

    protected List<Double>  prepareFlucuationsListForAScale(MatrixScale matrixScale) {
        List<SubMatrixCoordinates> subMatrixCoordinatesList = prepareSubMatrixCoordinatesForAScale(matrixScale);
        List<Double> flucuationsListForAScale = subMatrixCoordinatesList.stream().map(m -> prepareMeanResidualSquareForASubmatrix(m)).collect(Collectors.toList());
        return flucuationsListForAScale;
    }
    public List<ScaleMappedFluctuations> getScaleMappedRMSList() {
        prepareMatrixScales();
        prepareFluctuations();
        return scaleMappedFluctuationsList;
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
