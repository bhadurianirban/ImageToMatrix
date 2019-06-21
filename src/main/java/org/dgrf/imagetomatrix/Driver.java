package org.dgrf.imagetomatrix;

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
        //String imageFilePath = "/home/bhaduri/MEGA/DGRFFractal/testdata/DFA2D/radha.jpg";
        String imageFolderPath = "/home/bhaduri/MEGA/Paper/2DRaash/dancegray";
        String imageFilePath = "/home/bhaduri/MEGA/Paper/2DRaash/dancefacegray/21-1.jpg";
        
        TestUtils.DFAForFolder(imageFolderPath);
        //TestUtils.TestWithSMallMatrix();
        
        
    }

    

}
