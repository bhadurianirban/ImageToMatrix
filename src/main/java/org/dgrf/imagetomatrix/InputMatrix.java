/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author bhaduri
 */
public class InputMatrix {

    private RealMatrix origMatrix;
    private RealMatrix randomWalkMatrix;
    private RealMatrix meanSubtractedMatrix;
    private Double matrixMean;
    private Double matrixMaxValue;

    public InputMatrix(RealMatrix origMatrix) {
        this.origMatrix = origMatrix;

    }

    public InputMatrix(double[][] origMatrix) {
        this.origMatrix = new Array2DRowRealMatrix(origMatrix);

    }

    private void prepareMatrixMean() {

        double mat[][] = origMatrix.getData();

        double flatArray[] = Arrays.stream(mat)
                .flatMapToDouble(Arrays::stream)
                .toArray();
        matrixMean = Arrays.stream(flatArray).average().getAsDouble();

        //System.out.println(s);
    }
    private void cumulateMatrix() {
        int columnCount = origMatrix.getColumnDimension();
        int rowCount = origMatrix.getRowDimension();
        //get vector/array for each column means
        double[] fullColumnMeanArray = new double[columnCount];
        Array2DRowRealMatrix columnMeanSubtractedMatrix = new Array2DRowRealMatrix(rowCount, columnCount);
        for (int col =0;col<columnCount;col++) {
            double[] fullColumn = origMatrix.getColumn(col);
            double fullColumnMean = Arrays.stream(fullColumn).average().getAsDouble();
            double[] columnMeanSubtracted = Arrays.stream(fullColumn).map(i -> i - fullColumnMean).toArray();
            columnMeanSubtractedMatrix.setColumn(col, columnMeanSubtracted);
            fullColumnMeanArray[col] = fullColumnMean;
        }
        //get vector/array for each row means
        double[] fullRowMeanArray = new double[rowCount];
        Array2DRowRealMatrix rowMeanSubtractedMatrix = new Array2DRowRealMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            double[] fullRow = origMatrix.getRow(row);
            double fullRowMean = Arrays.stream(fullRow).average().getAsDouble();
            double[] rowMeanSubtracted = Arrays.stream(fullRow).map(i -> i - fullRowMean).toArray();
            rowMeanSubtractedMatrix.setRow(row, rowMeanSubtracted);
            fullRowMeanArray[row] = fullRowMean;
        }
        randomWalkMatrix = new Array2DRowRealMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                //double[] fullColumn = origMatrix.getColumn(col);
                //double[] fullRow = origMatrix.getRow(row);
                double fullRowMean = fullRowMeanArray[row];
                double fullColumnMean = fullColumnMeanArray[col];
                
                double[] columnTillThisPositionMeanSubtracted = Arrays.copyOfRange(columnMeanSubtractedMatrix.getColumn(col), 0, row + 1);
                double sumColumnValuesTillThisPosition = Arrays.stream(columnTillThisPositionMeanSubtracted).sum();

                
                double[] rowTillThisPositionMeanSubtracted = Arrays.copyOfRange(rowMeanSubtractedMatrix.getRow(row), 0, col + 1);
                double sumRowValuesTillThisPosition = Arrays.stream(rowTillThisPositionMeanSubtracted).sum();
                double cumValue = sumRowValuesTillThisPosition + sumColumnValuesTillThisPosition;
//                if (row == 1 && col == 1) {
//                    System.out.println("fullColumnMean " + fullColumnMean + "fullRowMean " + fullRowMean);
//                    
//                    System.out.println("rowTillThisPositionMeanSubtracted " + ArrayUtils.toString(rowTillThisPositionMeanSubtracted));
//                    System.out.println("columnTillThisPositionMeanSubtracted " + ArrayUtils.toString(columnTillThisPositionMeanSubtracted));
//                    System.out.println("sumColumnValuesTillThisPosition " + sumColumnValuesTillThisPosition + "sumRowValuesTillThisPosition" + sumRowValuesTillThisPosition);
//                }
                randomWalkMatrix.setEntry(row, col, cumValue);

            }

        }

    }

    private void cumulateMatrixRigorous() {
        int columnCount = origMatrix.getColumnDimension();
        int rowCount = origMatrix.getRowDimension();
        
        randomWalkMatrix = new Array2DRowRealMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                double[] fullColumn = origMatrix.getColumn(col);
                double[] fullRow = origMatrix.getRow(row);
                double fullColumnMean = Arrays.stream(fullColumn).average().getAsDouble();
                double fullRowMean = Arrays.stream(fullRow).average().getAsDouble();
                double[] columnTillThisPosition = Arrays.copyOfRange(fullColumn, 0, row + 1);
                double[] columnTillThisPositionMeanSubtracted = Arrays.stream(columnTillThisPosition).map(i -> i - fullColumnMean).toArray();
                double sumColumnValuesTillThisPosition = Arrays.stream(columnTillThisPositionMeanSubtracted).sum();

                double[] rowTillThisPosition = Arrays.copyOfRange(fullRow, 0, col + 1);
                double[] rowTillThisPositionMeanSubtracted = Arrays.stream(rowTillThisPosition).map(i -> i - fullRowMean).toArray();
                double sumRowValuesTillThisPosition = Arrays.stream(rowTillThisPositionMeanSubtracted).sum();
                double cumValue = sumRowValuesTillThisPosition + sumColumnValuesTillThisPosition;
                randomWalkMatrix.setEntry(row, col, cumValue);
//                if (row == 0 && col == 0) {
//
//                    double cumValue = origMatrix.getEntry(row, col) - fullColumnMean - fullRowMean;
//                    randomWalkMatrix.setEntry(row, col, cumValue);
//                } else if (row == 0 && col != 0) {
//                    double[] columnTillThisPosition = Arrays.copyOfRange(fullColumn, 0, col+1);
//                    double[] columnTillThisPositionMeanSubtracted = Arrays.stream(columnTillThisPosition).map(i -> i - fullColumnMean).toArray();
//                    double sumColumnValuesTillThisPosition = Arrays.stream(columnTillThisPositionMeanSubtracted).sum();
//                    double cumValue = sumColumnValuesTillThisPosition;
//                    randomWalkMatrix.setEntry(row, col, cumValue);
//                } else if (col == 0 && row != 0) {
//                    double[] rowTillThisPosition = Arrays.copyOfRange(fullRow, 0, row+1);
//                    double[] rowTillThisPositionMeanSubtracted = Arrays.stream(rowTillThisPosition).map(i -> i - fullRowMean).toArray();
//                    double sumRowValuesTillThisPosition = Arrays.stream(rowTillThisPositionMeanSubtracted).sum();
//                    double cumValue = sumRowValuesTillThisPosition;
//                    randomWalkMatrix.setEntry(row, col, cumValue);
//                } else {
                
//                    if (row == 2 && col == 3) {
//                        System.out.println("fullColumnMean " + fullColumnMean+"fullRowMean "+fullRowMean);
//                        System.out.println("rowTillThisPosition " + ArrayUtils.toString(rowTillThisPosition));
//                        System.out.println("columnTillThisPosition " + ArrayUtils.toString(columnTillThisPosition));
//                        System.out.println("rowTillThisPositionMeanSubtracted " + ArrayUtils.toString(rowTillThisPositionMeanSubtracted));
//                        System.out.println("columnTillThisPositionMeanSubtracted " + ArrayUtils.toString(columnTillThisPositionMeanSubtracted));
//                        System.out.println("sumColumnValuesTillThisPosition " + sumColumnValuesTillThisPosition + "sumRowValuesTillThisPosition" + sumRowValuesTillThisPosition);
//                    }

                
//                }

            }

        }

    }
//    private void cumulateMatrix() {
//        int columnCount = meanSubtractedMatrix.getColumnDimension();
//        int rowCount = meanSubtractedMatrix.getRowDimension();
//        //double randomWalkMatrixD[][] = new double[rowCount][columnCount];
//        randomWalkMatrix = new Array2DRowRealMatrix(rowCount, columnCount);
//        for (int row = 0; row < rowCount; row++) {
//            for (int col = 0; col < columnCount; col++) {
//                if (row == 0 && col == 0) {
//                    //randomWalkMatrixD[row][col] = origMatrix.getEntry(row, col);
//                    double cumValue = meanSubtractedMatrix.getEntry(row, col);
//                    randomWalkMatrix.setEntry(row, col, cumValue);
//                } else if (row == 0 && col != 0) {
//                    double cumValue = randomWalkMatrix.getEntry(0, col - 1) + meanSubtractedMatrix.getEntry(row, col);
//                    randomWalkMatrix.setEntry(row, col, cumValue);
//                } else if (col == 0 && row != 0) {
//                    double cumValue = randomWalkMatrix.getEntry(row - 1, 0) + meanSubtractedMatrix.getEntry(row, col);
//                    randomWalkMatrix.setEntry(row, col, cumValue);
//                } else {
//                    double cumValue = randomWalkMatrix.getEntry(row, col - 1)
//                            + randomWalkMatrix.getEntry(row - 1, col)
//                            + meanSubtractedMatrix.getEntry(row, col);
//                    randomWalkMatrix.setEntry(row, col, cumValue);
//                }
//
//            }
//
//        }
//
//    }

    private void prepareMeanSubtractedMatrix() {
        meanSubtractedMatrix = origMatrix.scalarAdd(0 - matrixMean);
    }

    private void prepareNormalisedMatrix() {
        double mat[][] = origMatrix.getData();

        double flatArray[] = Arrays.stream(mat)
                .flatMapToDouble(Arrays::stream)
                .toArray();

        matrixMaxValue = Arrays.stream(flatArray).max().getAsDouble();
        origMatrix = origMatrix.scalarMultiply(1 / matrixMaxValue);
    }

    public Double getMatrixMean(Boolean normalised) {
        if (normalised) {
            prepareNormalisedMatrix();
        }
        prepareMatrixMean();
        return matrixMean;
    }

    public RealMatrix getNormalisedMatrix() {

        prepareNormalisedMatrix();
        return this.origMatrix;
    }

    public RealMatrix getMeanSubtractedMatrix(Boolean normalised) {

        if (normalised) {
            prepareNormalisedMatrix();
        }
        prepareMatrixMean();
        prepareMeanSubtractedMatrix();
        return meanSubtractedMatrix;
    }

    public RealMatrix getCumulativeMatrix(Boolean normalised) {

        if (normalised) {
            prepareNormalisedMatrix();
        }
        //prepareMatrixMean();
        //prepareMeanSubtractedMatrix();
        cumulateMatrix();
        
        return randomWalkMatrix;
    }

    public FD getCumulative(Boolean normalised) {

        return new FD(getCumulativeMatrix(normalised));
    }

    public FD getMeanSubtrated(Boolean normalised, int numberOfScales) {

        if (normalised) {
            prepareNormalisedMatrix();
        }
        prepareMatrixMean();
        prepareMeanSubtractedMatrix();
        //cumulateMatrix();
        return new FD(meanSubtractedMatrix);
    }

    public FD getMeanSubtrated(Boolean normalised) {

        if (normalised) {
            prepareNormalisedMatrix();
        }
        prepareMatrixMean();
        prepareMeanSubtractedMatrix();
        //cumulateMatrix();
        return new FD(meanSubtractedMatrix);
    }
}
