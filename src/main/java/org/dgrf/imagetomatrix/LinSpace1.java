package org.dgrf.imagetomatrix;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LinSpace1 {

    private final Double start;
    private final Double end;
    private final int totalCount;
    private Double step;
    private Double[] linSpaceList;

    public LinSpace1(Double start, Double end, int totalCount) {
        this.start = start;
        this.end = end;
        this.totalCount = totalCount;
        calcLinSpaceList();
    }

    private void calcLinSpaceList() {
        linSpaceList = new Double[totalCount];
        BigDecimal bigStart = new BigDecimal(start);
        BigDecimal bigEnd = new BigDecimal(end);
        BigDecimal bigDivision = new BigDecimal(totalCount - 1);
        BigDecimal bigStep = bigEnd.subtract(bigStart).divide(bigDivision, 16, RoundingMode.HALF_UP);
        //Double step = (end - start)/(totalCount-1);
        BigDecimal linValue = new BigDecimal(start);
        linSpaceList[0] = linValue.doubleValue();
        for (int i = 1; i < totalCount; i++) {
            linValue = linValue.add(bigStep);
            linSpaceList[i] = linValue.doubleValue();
        }
        step = bigStep.doubleValue();
    }

    public Double[] getLinSpaceList() {

        return linSpaceList;
    }

    public Double getLinSpaceElement(int i) {
        return linSpaceList[i];
    }

    public Double getStep() {
        return this.step;
    }

    public Double getStart() {
        return this.start;
    }

    public Double getEnd() {
        return this.end;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

}
