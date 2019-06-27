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
public class FQ extends Fluctuations {

   
    private List<Double> qLinSpqceList;
    private Double linSpaceDistanceInBetween;
    private List<MultiFractalSpectrum> multiFractalSpectrumList;
    private Double multiFractalSpectrumWidth;
    public FQ(RealMatrix inputMatrix) {
        super(inputMatrix);
    }

    public FQ(RealMatrix inputMatrix, int numberOfScales) {
        super(inputMatrix, numberOfScales);
    }

    private void prepareQLinSpaceList() {
        LinSpace linSpace = new LinSpace(-5.0, 5.0, 101);
        this.qLinSpqceList = linSpace.getLinSpaceList();
        this.linSpaceDistanceInBetween = linSpace.getDistanceInBetween();
    }

//    private void prepareFQ() {
//        scaleMappedQRMSList = matrixScales.stream().map(ms -> prepareScaleMappedRMS(ms)).collect(Collectors.toList());
//    }
    private void prepareFQ() {
        prepareQLinSpaceList();
        prepareMatrixScales();
        prepareFluctuations();
        //now fluctuations are there for all scales in List<ScaleMappedFluctuations> scaleMappedFluctuationsList
        //if there are 19 scales the list size will be 19
        //for FD we have calculated the quadratic mean of the fluctuations for each 19 scales and got 19 quadratic means
        //here for each q we need to calculate the q powered mean of the fluctuations
        //for example to calculate qpowered mean for q=2.0 
        //List<Double> qPoweredMeanForAScale = scaleMappedFluctuationsList.stream().map(scmf -> scmf.getLogOfQPoweredMeanOfFluctuations(2.0)).collect(Collectors.toList());
        //Hq
        List<Double> tqList = qLinSpqceList.stream().map(q -> prepareTq(q)).collect(Collectors.toList());
        List<Double> hqList = new ArrayList<>();
        int prevQCounter;
        for (int qCounter = 1; qCounter < tqList.size(); qCounter++) {
            prevQCounter = qCounter - 1;
            Double hq = (tqList.get(qCounter) - tqList.get(prevQCounter)) / linSpaceDistanceInBetween;
            hqList.add(hq);
        }
        List<Double> dqList = new ArrayList<>();
        for (int i = 0; i < (qLinSpqceList.size() - 1); i++) {
            Double q = qLinSpqceList.get(i);
            Double hq = hqList.get(i);
            Double tq = tqList.get(i);
            Double dq = (q * hq) - tq;
            dqList.add(dq);
        }
        this.multiFractalSpectrumList = new ArrayList<>();
        for (int i=0;i<hqList.size();i++) {
            MultiFractalSpectrum multiFractalSpectrum = new MultiFractalSpectrum(hqList.get(i), dqList.get(i));
            multiFractalSpectrumList.add(multiFractalSpectrum);
        }
        Double minHq = hqList.stream().mapToDouble(a->a).min().getAsDouble();
        Double maxHq = hqList.stream().mapToDouble(a->a).max().getAsDouble();
        this.multiFractalSpectrumWidth = maxHq - minHq;
        
        
    }

    private Double prepareTq(Double q) {

        SimpleRegression scaleQPoweredMeanFit = new SimpleRegression(true);
        scaleMappedFluctuationsList.stream().forEach(scmf -> scaleQPoweredMeanFit.addData(scmf.getLogOfMatrixScale(), scmf.getLogOfQPoweredMeanOfFluctuations(q)));
        Double Hq = scaleQPoweredMeanFit.getSlope();
        Double Tq = (Hq * q) - 1;
        return Tq;
    }

    public List<Double> getQLinSpaceList() {
        prepareQLinSpaceList();
        return qLinSpqceList;
    }

    public List<MultiFractalSpectrum> getMultiFractalSpectrumList() {
        prepareFQ();
        return multiFractalSpectrumList;
    }

    public Double getMultiFractalSpectrumWidth() {
        prepareFQ();
        return multiFractalSpectrumWidth;
    }

    
}
