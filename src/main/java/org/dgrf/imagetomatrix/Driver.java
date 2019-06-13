package org.dgrf.imagetomatrix;

import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dgrfi
 */
public class Driver {

    public static void main(String args[]) {
        String imageFilePath = "/home/dgrfi/MEGA/DGRFFractal/testdata/DFA2D/radha.jpg";
        //String outFilePath = "/home/dgrfi/MEGA/DGRFFractal/testdata/DFA2D/radhaGreen.csv";
        //new ReadImage(imageFilePath).writeMatrixToFile(outFilePath,COLORCCHOICE.GREEN);

        //Double d = new ReadImage(imageFilePath).getRandomWalkMatix().getMatrixMean();
        //Double mean = new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.RED).getMatrixMean();
        //RealMatrix d = new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.RED).getMeanSubtractedMatrix();
        //List<MatrixScale> matrixScales = new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.RED).FQ().getScales();
        //matrixScales.stream().forEach(m -> System.out.println(m.getColumnScaleSize()+" "+m.getRowScaleSize()));
        new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.RED).FQ().getFD();
        //testMultiRegression();
        //System.out.println("red average"+mean);

//        Double scaleMax = 1920.0;
//        Double scaleMin = 16.0;
//        int ScaleNumber = 19;
//        Double exponentMin = LogUtil.logBaseK(scaleMin);
//        Double exponentMax = LogUtil.logBaseK(scaleMax);
//        LinSpace expLinSpace = new LinSpace(exponentMin, exponentMax, ScaleNumber);
//        for (int expCounter = 0; expCounter < expLinSpace.getTotalCount(); expCounter++) {
//            int sliceSize = (int) Math.round(Math.pow(2, expLinSpace.getLinSpaceElement(expCounter)));
//            System.out.println(sliceSize);
//        }
        double[][] a = {
            {1, 2, 3, 6},
            {4, 5, 6, 9},
            {7, 2, 1, 2},};
//        double flatArray[] = Arrays.stream(a)
//        .flatMapToDouble(Arrays::stream)
//        .toArray();
//        double s = Arrays.stream(flatArray).average().getAsDouble();
//        System.out.println("Numbers = " + s);
        //RealMatrix d = new RandomWalkMatix(a).getRandomWalkMatrix();

    }

    public static void testMultiRegression() {
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
// weight
        double[] y = new double[]{21,20,16,22,41,44,21,36,41,25};
// height, waist
        double[][] x = new double[10][];
        x[0] = new double[]{2,4};
        x[1] = new double[]{3,3};
        x[2] = new double[]{4,1};
        x[3] = new double[]{1,5};
        x[4] = new double[]{9,6};
        x[5] = new double[]{6,9};
        x[6] = new double[]{2,4};
        x[7] = new double[]{5,7};
        x[8] = new double[]{6,8};
        x[9] = new double[]{7,2};
        regression.newSampleData(y, x);

        //double[] coe = regression.estimateRegressionParameters();
        //for (double p : coe) {
        //    System.out.println(p);
       // }
        System.out.println(regression.calculateRSquared());
    }

}
