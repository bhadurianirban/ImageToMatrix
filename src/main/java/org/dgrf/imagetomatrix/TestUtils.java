/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.util.Precision;
import static org.dgrf.imagetomatrix.TestUtils.ScaleMapFD;

/**
 *
 * @author dgrfi
 */
public class TestUtils {

    public static void printMatrix(RealMatrix d) {
        for (int row = 0; row < d.getRowDimension(); row++) {
            double rowV[] = d.getRow(row);
            System.out.println(ArrayUtils.toString(rowV));
        }
    }

    public static void TestWithSMallMatrix() {
        double[][] a = {
            {1, 2, 3, 6},
            {4, 5, 6, 9},
            {7, 2, 1, 2},};
        //double mean = new InputMatrix(a).getMatrixMean(Boolean.FALSE);
        //System.out.println(mean);
        //RealMatrix d = new InputMatrix(a).getMeanSubtractedMatrix(Boolean.FALSE);
        TestUtils.printMatrix(new Array2DRowRealMatrix(a));
        System.out.println("===");
        RealMatrix e = new InputMatrix(a).getCumulativeMatrix(Boolean.FALSE);
        TestUtils.printMatrix(e);
    }

    public static void testMultiRegression() {
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
// weight
        double[] y = new double[]{21, 20, 16, 22, 41, 44, 21, 36, 41, 25};
// height, waist
        double[][] x = new double[10][];
        x[0] = new double[]{2, 4};
        x[1] = new double[]{3, 3};
        x[2] = new double[]{4, 1};
        x[3] = new double[]{1, 5};
        x[4] = new double[]{9, 6};
        x[5] = new double[]{6, 9};
        x[6] = new double[]{2, 4};
        x[7] = new double[]{5, 7};
        x[8] = new double[]{6, 8};
        x[9] = new double[]{7, 2};
        regression.newSampleData(y, x);

        //double[] coe = regression.estimateRegressionParameters();
        //for (double p : coe) {
        //    System.out.println(p);
        // }
        System.out.println(regression.calculateRSquared());
    }

    public static void FAForFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            System.out.println("not a folder path");
        } else {
            File fileList[] = folder.listFiles();
            for (File file : fileList) {
                String imageFilePath = file.getAbsolutePath();
                String imageFileName = file.getName();
                double hurstRed = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.RED).getMeanSubtrated(Boolean.TRUE).getScaleRMSLogFit().getSlope();
                double hurstGreen = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.GREEN).getMeanSubtrated(Boolean.TRUE).getScaleRMSLogFit().getSlope();
                double hurstBlue = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.BLUE).getMeanSubtrated(Boolean.TRUE).getScaleRMSLogFit().getSlope();
                System.out.println(imageFileName + "," + hurstRed + "," + hurstGreen + "," + hurstBlue);
            }
        }
    }

    public static void FAForFile(String imageFilePath) {
        File imageFile = new File(imageFilePath);
        double hurstRed = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.RED).getMeanSubtrated(Boolean.TRUE).getScaleRMSLogFit().getSlope();
        double hurstGreen = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.GREEN).getMeanSubtrated(Boolean.TRUE).getScaleRMSLogFit().getSlope();
        double hurstBlue = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.BLUE).getMeanSubtrated(Boolean.TRUE).getScaleRMSLogFit().getSlope();
        System.out.println(imageFile.getName() + "," + hurstRed + "," + hurstGreen + "," + hurstBlue);

    }
    
    public static void ScaleMapFD(String imageFilePath) {
        List<ScaleMappedRMS> ScaleMappedRMSList  = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.RED).getCumulative(Boolean.FALSE).getScaleMappedRMSList();
        ScaleMappedRMSList.stream().forEach(scmr-> System.out.println(scmr.getLogOfMatrixScale()+","+scmr.getLogOfRMS()));
    }

    public static void testLinSpace() {
//        Double columnExponentMin = LogUtil.logBaseK(16);
//        Double columnExponentMax = LogUtil.logBaseK(320);
//       
//
//        LinSpace colomnExpLinSpace1 = new LinSpace(columnExponentMin, columnExponentMax, 19);
//        for (int i = 0; i < 19; i++) {
//
//            int columnScaleSize = (int) Math.round(Math.pow(2, colomnExpLinSpace1.getLinSpaceList().get(i)));
//            System.out.println(colomnExpLinSpace1.getLinSpaceList().get(i) + "," + columnScaleSize);
//        }
        LinSpace linSpace = new LinSpace(-5.0, 5.0, 101);
        List<Double> linSpaceList = linSpace.getLinSpaceList();
        linSpaceList.stream().forEach(m-> {
               
                System.out.println(m);
                
        });
    }
    public static void printCumulativeMatrixForPictureFile(String imageFilePath) {
        RealMatrix cum = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.RED).getCumulativeMatrix(Boolean.TRUE);
        printMatrix(cum);
    }
    public static void DFAForFile(String imageFilePath) {
        File imageFile = new File(imageFilePath);
        double hurstRed = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.RED).getCumulative(Boolean.FALSE).getScaleRMSLogFit().getSlope();
        double hurstGreen = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.GREEN).getCumulative(Boolean.FALSE).getScaleRMSLogFit().getSlope();
        double hurstBlue = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.BLUE).getCumulative(Boolean.FALSE).getScaleRMSLogFit().getSlope();
        System.out.println(imageFile.getName() + "," + hurstRed + "," + hurstGreen + "," + hurstBlue);

    }
    
    public static void DFAForFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            System.out.println("not a folder path");
        } else {
            File fileList[] = folder.listFiles();
            for (File file : fileList) {
                String imageFilePath = file.getAbsolutePath();
                String imageFileName = file.getName();
                double hurstRed = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.RED).getCumulative(Boolean.TRUE).getScaleRMSLogFit().getSlope();
                double hurstGreen = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.GREEN).getCumulative(Boolean.TRUE).getScaleRMSLogFit().getSlope();
                double hurstBlue = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.BLUE).getCumulative(Boolean.TRUE).getScaleRMSLogFit().getSlope();
                System.out.println(imageFileName + "," + hurstRed + "," + hurstGreen + "," + hurstBlue);
            }
        }
    }
}
