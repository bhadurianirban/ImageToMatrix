/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

/**
 *
 * @author bhaduri
 */
public class MatrixScale {
    private int columnScaleSize;
    private int rowScaleSize;

    public MatrixScale(int columnScaleSize, int rowScaleSize) {
        this.columnScaleSize = columnScaleSize;
        this.rowScaleSize = rowScaleSize;
    }
    
    public double getArea() {
        double area = Double.valueOf(columnScaleSize*rowScaleSize);
        return area;
    }
    public int getColumnScaleSize() {
        return columnScaleSize;
    }

    public void setColumnScaleSize(int columnScaleSize) {
        this.columnScaleSize = columnScaleSize;
    }

    public int getRowScaleSize() {
        return rowScaleSize;
    }

    public void setRowScaleSize(int rowScaleSize) {
        this.rowScaleSize = rowScaleSize;
    }
    
}
