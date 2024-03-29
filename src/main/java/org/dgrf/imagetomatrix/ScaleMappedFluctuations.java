/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author dgrfi
 */
public class ScaleMappedFluctuations {

    private MatrixScale matrixScale;
    private final List<Double> flucuationsListForAScale;//this is actually the list of mean of squared residuals for a scale
    private double quadraticMeanOfFluctuations;
    private double logOfMatrixScale;
    private double logOfQuadraticMeanOfFluctuations;

    public ScaleMappedFluctuations(MatrixScale matrixScale, List<Double> flucuationsListForAScale) {
        this.matrixScale = matrixScale;
        this.flucuationsListForAScale = flucuationsListForAScale;
    }

    public void setMatrixScale(MatrixScale matrixScale) {
        this.matrixScale = matrixScale;
    }

    public MatrixScale getMatrixScale() {
        return matrixScale;
    }

    public List<Double> getFlucuationsListForAScale() {
        return flucuationsListForAScale;
    }

    public double getQuadraticMeanOfFluctuations() {
        double[] meanResidualSquaredSums = flucuationsListForAScale.stream().mapToDouble(Double::doubleValue).toArray();
        DescriptiveStatistics ds = new DescriptiveStatistics(meanResidualSquaredSums);
        quadraticMeanOfFluctuations = ds.getQuadraticMean();
        return quadraticMeanOfFluctuations;
    }

    public double getLogOfMatrixScale() {
        double area = matrixScale.getArea();
        logOfMatrixScale = LogUtil.logBaseK(area);
        return logOfMatrixScale;
    }

    public double getLogOfQuadraticMeanOfFluctuations() {
        double[] meanResidualSquaredSums = flucuationsListForAScale.stream().mapToDouble(Double::doubleValue).toArray();
        DescriptiveStatistics ds = new DescriptiveStatistics(meanResidualSquaredSums);
        quadraticMeanOfFluctuations = ds.getQuadraticMean();
        logOfQuadraticMeanOfFluctuations = LogUtil.logBaseK(quadraticMeanOfFluctuations);
        return logOfQuadraticMeanOfFluctuations;
    }
    public Double getLogOfQPoweredMeanOfFluctuations(Double q) {
        Double qPoweredMeanOfFluctuations = getQPoweredMeanOfFluctuations(q);
        Double logOfQPoweredMeanOfFluctuations = LogUtil.logBaseK(qPoweredMeanOfFluctuations);
        return logOfQPoweredMeanOfFluctuations;
    }
    public Double getQPoweredMeanOfFluctuations(Double q) {
        Double qRMS;
        List<Double> qPoweredRMS = flucuationsListForAScale.stream().map(mrss -> calcqPower(mrss, q)).collect(Collectors.toList());
        Double meanQPoweredRMS = qPoweredRMS.stream().mapToDouble(a -> a).average().getAsDouble();
        if (q == 0) {
            qRMS = Math.exp(0.5 * meanQPoweredRMS);
        } else {
            qRMS = Math.pow(meanQPoweredRMS, 1 / q);
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
}
