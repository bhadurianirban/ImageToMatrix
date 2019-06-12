package org.dgrf.imagetomatrix;

import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
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
        //String outFilePath = "/home/dgrfi/MEGA/DGRFFractal/testdata/DFA2D/radhaGreen.csv";
        //new ReadImage(imageFilePath).writeMatrixToFile(outFilePath,COLORCCHOICE.GREEN);
        
        //Double d = new ReadImage(imageFilePath).getRandomWalkMatix().getMatrixMean();
        //Double mean = new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.RED).getMatrixMean();
        //RealMatrix d = new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.RED).getMeanSubtractedMatrix();
        //List<MatrixScale> matrixScales = new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.RED).FQ().getScales();
        //matrixScales.stream().forEach(m -> System.out.println(m.getColumnScaleSize()+" "+m.getRowScaleSize()));
        new ReadImage(imageFilePath).randomWalkMatix(COLORCCHOICE.RED).FQ().getFD();
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
    

}
