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
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 *
 * @author dgrfi
 */
public class FQ {

    private final RealMatrix inputMatrix;
    private final Double columnScaleMax;
    private final Double rowScaleMax;
    private final Double columnScaleMin;
    private final Double rowScaleMin;
    private List<MatrixScale> matrixScales;
    private List<ScaleMappedRMS> scaleMappedRMSList;

    private final int numberOfScales;
    private SimpleRegression scaleRMSLogFit;

    public FQ(RealMatrix inputMatrix) {
        this.inputMatrix = inputMatrix;
        this.columnScaleMax = Double.valueOf(inputMatrix.getColumnDimension());
        this.rowScaleMax = Double.valueOf(inputMatrix.getRowDimension());
        this.columnScaleMin = this.rowScaleMin = 16.0;
        numberOfScales = 19;
    }

    public FQ(RealMatrix inputMatrix, int numberOfScales) {
        this.inputMatrix = inputMatrix;
        this.columnScaleMax = Double.valueOf(inputMatrix.getColumnDimension());
        this.rowScaleMax = Double.valueOf(inputMatrix.getRowDimension());
        this.columnScaleMin = this.rowScaleMin = 16.0;
        this.numberOfScales = numberOfScales;
    }

    private void prepareMatrixScales() {
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
        prepareMatrixScales();
        return matrixScales;
    }

    private void prepareFD() {

        //for (MatrixScale matrixScale : matrixScales) {
        //for test begin
        //MatrixScale matrixScale = matrixScales.get(1);
        //for test end
        scaleMappedRMSList = matrixScales.stream().map(ms -> prepareScaleMappedRMS(ms)).collect(Collectors.toList());

        //double meanResidualSquaredSumRMS = prepareMeanResidualSquareListForAScale(matrixScale);
        //System.out.println("----"+meanResidualSquaredSumRMS);
    }

    private ScaleMappedRMS prepareScaleMappedRMS(MatrixScale matrixScale) {
        double meanResidualSquaredSumRMS = prepareMeanResidualSquareListForAScale(matrixScale);
        ScaleMappedRMS scaleMappedRMS = new ScaleMappedRMS(matrixScale, meanResidualSquaredSumRMS);
        return scaleMappedRMS;
    }

    private double prepareMeanResidualSquareListForAScale(MatrixScale matrixScale) {
        List<SubMatrixCoordinates> subMatrixCoordinatesList = prepareSubMatrixCoordinatesForAScale(matrixScale);

        List<Double> meanResidualSquaredSumList = subMatrixCoordinatesList.stream().map(m -> prepareMeanResidualSquareListForASubmatrix(m)).collect(Collectors.toList());
        double meanResidualSquaredSumRMS = getRMSValue(meanResidualSquaredSumList);
        return meanResidualSquaredSumRMS;
    }

    private double prepareMeanResidualSquareListForASubmatrix(SubMatrixCoordinates subMatrixCoordinates) {
        //here there are two independant variables which are the coordinates of the matrix so for x variables k = 2
        //Let us declare an 2D array of 2 columns
        //If start col and start row is 0,0 and end col and end row is 3,3 we will get a 2D array like this with 16 pairs
        // {0,0}
        // {0,1}
        // {0,2}
        // {0,3}
        // {1,0}
        // {1,1}
        //....
        // {3,3}
        int startRow = subMatrixCoordinates.getStartRow();
        int endRow = subMatrixCoordinates.getEndRow();
        int startColumn = subMatrixCoordinates.getStartColumn();
        int endColumn = subMatrixCoordinates.getEndColumn();
        int width = endColumn - startColumn + 1;
        int height = endRow - startRow + 1;
        int totalObservations = width * height;
        //System.out.println("totalObservations "+totalObservations);
        double x[][] = new double[totalObservations][2];
        double y[] = new double[totalObservations];
        int observationNumber = 0;
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startColumn; col <= endColumn; col++) {
                x[observationNumber][0] = row;
                x[observationNumber][1] = col;
                y[observationNumber] = inputMatrix.getEntry(row, col);
                //System.out.println("y "+y[observationNumber]+" "+ArrayUtils.toString(x[observationNumber]));
                observationNumber++;
            }
        }
        OLSMultipleLinearRegression multipleLinearRegression = new OLSMultipleLinearRegression();
        multipleLinearRegression.newSampleData(y, x);
        multipleLinearRegression.setNoIntercept(false);

        Double residualSquaredSum;

        residualSquaredSum = multipleLinearRegression.calculateResidualSumOfSquares();

        //System.out.println(ArrayUtils.toString(residuals));
        double meanResidualSquaredSum = residualSquaredSum / totalObservations;
        //System.out.println(meanResidualSquaredSum);
        return meanResidualSquaredSum;
    }

    private double getRMSValue(List<Double> meanResidualSquaredSumList) {
        double[] meanResidualSquaredSums = meanResidualSquaredSumList.stream().mapToDouble(Double::doubleValue).toArray();
        DescriptiveStatistics ds = new DescriptiveStatistics(meanResidualSquaredSums);
        double rms = ds.getQuadraticMean();
        return rms;
    }

    public List<ScaleMappedRMS> getScaleMappedRMSList() {
        prepareMatrixScales();
        prepareFD();
        return scaleMappedRMSList;
    }

    private void prepareScaleRMSLogFit() {
        scaleRMSLogFit = new SimpleRegression(true);
        scaleMappedRMSList.stream().forEach(scm -> scaleRMSLogFit.addData(scm.getLogOfMatrixScale(), scm.getLogOfRMS()));

    }

    public SimpleRegression getScaleRMSLogFit() {
        prepareMatrixScales();
        prepareFD();
        prepareScaleRMSLogFit();
        return scaleRMSLogFit;

    }

    private List<SubMatrixCoordinates> prepareSubMatrixCoordinatesForAScale(MatrixScale matrixScale) {
        int columnScaleSize = matrixScale.getColumnScaleSize();
        int rowScaleSize = matrixScale.getRowScaleSize();
        int columnCount = inputMatrix.getColumnDimension();
        int rowCount = inputMatrix.getRowDimension();
        int noOfColumnSlices = columnCount / columnScaleSize;
        int noOfRowSlices = rowCount / rowScaleSize;
        List<Integer> startColIndexes = new ArrayList<>();
        List<Integer> endColIndexes = new ArrayList<>();

        for (int scaleCounter = 0; scaleCounter < noOfColumnSlices; scaleCounter++) {
            int startColIndex = columnScaleSize * scaleCounter;
            int endColIndex = startColIndex + columnScaleSize - 1;
            startColIndexes.add(startColIndex);
            endColIndexes.add(endColIndex);
        }
        List<Integer> startRowIndexes = new ArrayList<>();
        List<Integer> endRowIndexes = new ArrayList<>();

        for (int scaleCounter = 0; scaleCounter < noOfRowSlices; scaleCounter++) {
            int startRowIndex = rowScaleSize * scaleCounter;
            int endRowIndex = startRowIndex + rowScaleSize - 1;
            startRowIndexes.add(startRowIndex);
            endRowIndexes.add(endRowIndex);
        }
        List<SubMatrixCoordinates> subMatrixCoordinatesList = new ArrayList<>();
        int numOfPartitions = 0;
        for (int colSliceCounter = 0; colSliceCounter < noOfColumnSlices; colSliceCounter++) {
            for (int rowSliceCounter = 0; rowSliceCounter < noOfRowSlices; rowSliceCounter++) {
                int startCol = startColIndexes.get(colSliceCounter);
                int startRow = startRowIndexes.get(rowSliceCounter);
                int endCol = endColIndexes.get(colSliceCounter);
                int endRow = endRowIndexes.get(rowSliceCounter);
                SubMatrixCoordinates subMatrixCoordinates = new SubMatrixCoordinates(startRow, endRow, startCol, endCol);
                subMatrixCoordinatesList.add(subMatrixCoordinates);
                numOfPartitions++;
            }
        }
        return subMatrixCoordinatesList;
    }

}
