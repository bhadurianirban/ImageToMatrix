package org.dgrf.imagetomatrix;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class LinSpace {

    private final Double start;
    private final Double end;
    private final int numberOfScales;
    private Double distanceInBetween;
    private List<Double> linSpaceList;

    public LinSpace(Double start, Double end, int numberOfScales) {
        this.start = start;
        this.end = end;
        this.numberOfScales = numberOfScales;
        
    }

    private void calcLinSpaceList() {
        linSpaceList = new ArrayList<>();
        Double totalDistance = end-start;
        distanceInBetween = totalDistance / (numberOfScales - 1);
        Double linSpace = start;
        linSpaceList.add(linSpace);
        for (int i = 1; i < numberOfScales; i++){
            linSpace = linSpace + distanceInBetween;
            linSpaceList.add(linSpace);
        }
        
    }

    public List<Double> getLinSpaceList() {
        calcLinSpaceList();
        return linSpaceList;
    }

    

}
