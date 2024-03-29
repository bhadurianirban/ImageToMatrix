/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.imagetomatrix;

/**
 *
 * @author dgrfi
 */
public class SubMatrixCoordinates {

    private int startRow;
    private int endRow;
    private int startColumn;
    private int endColumn;

    public SubMatrixCoordinates(int startRow, int endRow, int startColumn, int endColumn) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.startColumn = startColumn;
        this.endColumn = endColumn;
    }
    
    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }
    
    public String toString () {
        String s = this.startColumn+","+this.startRow+","+this.endColumn+","+this.endRow;
        return s;
    }
}
