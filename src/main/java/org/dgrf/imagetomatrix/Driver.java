package org.dgrf.imagetomatrix;

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
        String imageFilePath = "/home/dgrfi/Pictures/DSC04988.JPG.jpeg";
        ReadImage readImage = new ReadImage(imageFilePath);
    }
    
}
