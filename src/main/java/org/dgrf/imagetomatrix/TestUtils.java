/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

import java.io.File;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

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

    public static void FAForFolder(File folder) {
        if (folder.isDirectory()) {
            File fileList[] = folder.listFiles();
            for (File file : fileList) {
                FAForFile(file);

            }
        } else if (folder.isFile()) {
            FAForFile(folder);
        }
        

    }

    public static void FAForFile(File imageFile) {

        double hurstRed = new ReadImage(imageFile).getInputMatrix(COLORCCHOICE.RED).getFA(Boolean.TRUE).getScaleRMSLogFit().getSlope();
        double hurstGreen = new ReadImage(imageFile).getInputMatrix(COLORCCHOICE.GREEN).getFA(Boolean.TRUE).getScaleRMSLogFit().getSlope();
        double hurstBlue = new ReadImage(imageFile).getInputMatrix(COLORCCHOICE.BLUE).getFA(Boolean.TRUE).getScaleRMSLogFit().getSlope();
        System.out.println(imageFile.getName() + "," + hurstRed + "," + hurstGreen + "," + hurstBlue);

    }

    public static void ScaleMapFD(File imageFile) {
        List<ScaleMappedFluctuations> ScaleMappedRMSList = new ReadImage(imageFile).getInputMatrix(COLORCCHOICE.RED).getFD(Boolean.FALSE).getScaleMappedRMSList();
        ScaleMappedRMSList.stream().forEach(scmr -> System.out.println(scmr.getMatrixScale().getArea() + "," + scmr.getLogOfQuadraticMeanOfFluctuations()));
    }

    public static void MFWidthForAFile(File imageFile) {

        Double multiFractalSpectrumWidthRed = new ReadImage(imageFile).getInputMatrix(COLORCCHOICE.RED).getFQ(Boolean.FALSE).getMultiFractalSpectrumWidth();
        Double multiFractalSpectrumWidthGreen = new ReadImage(imageFile).getInputMatrix(COLORCCHOICE.GREEN).getFQ(Boolean.FALSE).getMultiFractalSpectrumWidth();
        Double multiFractalSpectrumWidthBlue = new ReadImage(imageFile).getInputMatrix(COLORCCHOICE.BLUE).getFQ(Boolean.FALSE).getMultiFractalSpectrumWidth();

        System.out.println(imageFile.getName() + "," + multiFractalSpectrumWidthRed + "," + multiFractalSpectrumWidthGreen + "," + multiFractalSpectrumWidthBlue);
    }

    public static void RunMFWidth(File folder) {
        if (folder.isDirectory()) {
            File fileList[] = folder.listFiles();
            for (File file : fileList) {
                MFWidthForAFile(file);

            }
        } else if (folder.isFile()) {
            MFWidthForAFile(folder);
        }
        

    }

    public static void MFSpectrumForAFile(File imageFilePath, COLORCCHOICE colorcchoice) {
        List<MultiFractalSpectrum> multiFractalSpectrumList = new ReadImage(imageFilePath).getInputMatrix(colorcchoice).getFQ(Boolean.FALSE).getMultiFractalSpectrumList();
        multiFractalSpectrumList.stream().forEach(mfs -> System.out.println(mfs.getHq() + "," + mfs.getDq()));
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
        linSpaceList.stream().forEach(m -> {

            System.out.println(m);

        });
    }

    public static void testQLinSpace(File imageFile) {
        List<Double> qLinSpaceList = new ReadImage(imageFile).getInputMatrix(COLORCCHOICE.RED).getFQ(Boolean.FALSE).getQLinSpaceList();
        //qLinSpaceList.stream().forEach(System.out::println);
        System.out.println(qLinSpaceList.get(70));
    }

    public static void printCumulativeMatrixForPictureFile(File imageFilePath) {
        RealMatrix cum = new ReadImage(imageFilePath).getInputMatrix(COLORCCHOICE.RED).getCumulativeMatrix(Boolean.TRUE);
        printMatrix(cum);
    }

    public static void DFAForFile(File imageFile) {

        double hurstRed = new ReadImage(imageFile).getInputMatrix(COLORCCHOICE.RED).getFD(Boolean.FALSE).getScaleRMSLogFit().getSlope();
        double hurstGreen = new ReadImage(imageFile).getInputMatrix(COLORCCHOICE.GREEN).getFD(Boolean.FALSE).getScaleRMSLogFit().getSlope();
        double hurstBlue = new ReadImage(imageFile).getInputMatrix(COLORCCHOICE.BLUE).getFD(Boolean.FALSE).getScaleRMSLogFit().getSlope();
        System.out.println(imageFile.getName() + "," + hurstRed + "," + hurstGreen + "," + hurstBlue);

    }

    public static void RunDFA(File folder) {

        if (folder.isDirectory()) {
            File fileList[] = folder.listFiles();
            for (File file : fileList) {
                DFAForFile(file);

            }
        } else if (folder.isFile()) {
            DFAForFile(folder);
        }

    }
}
