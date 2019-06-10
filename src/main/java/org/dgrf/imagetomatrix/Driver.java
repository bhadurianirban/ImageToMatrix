package org.dgrf.imagetomatrix;

import java.util.Arrays;
import java.util.OptionalDouble;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

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
        //Double d = new ReadImage(imageFilePath).getRandomWalkMatix().getMatrixMean();
        Double mean = new ReadImage(imageFilePath).getRandomWalkMatix().getMatrixMean();
        RealMatrix d = new ReadImage(imageFilePath).getRandomWalkMatix().getMeanSubtractedMatrix();
        System.out.println("red average"+mean);
        
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
        for (int row = 0;row< d.getRowDimension();row++) {
            double rowV[] = d.getRow(row);
            System.out.println(ArrayUtils.toString(rowV));
        }
    }

}
