package org.dgrf.imagetomatrix;

import java.io.File;
import org.dgrf.cliparser.CLIParser;

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
        //String imageFolderPath = "/home/dgrfi/MEGA/Paper/2DRaash/dancefacegray/";
        //String imageFilePath = "/home/dgrfi/MEGA/Paper/2DRaash/dancefacegray/01-1.jpg";
        //TestUtils.testQLinSpace(imageFilePath);
        //TestUtils.ScaleMapFQ(imageFilePath);
        //TestUtils.MFWidthForAFile(imageFilePath);
        //TestUtils.MFSpectrumForAFile(imageFilePath, COLORCCHOICE.RED);
        //TestUtils.TestWithSMallMatrix();
//        TestUtils.testLinSpace();
//        Double st = -4.9;
//        Double ad = 0.1;
//        Double d= st+ad;
//        System.out.println(d);
//        if (d==4.8) {
//            System.out.println("sss");
//        }
        parseAndExecute(args);
        

    }

    private static void parseAndExecute(String args[]) {
        CLIParser clip = new CLIParser(args);

        String analysisType = clip.switchValue("-a", "dfa");
        if (!analysisType.matches("fa|dfa|mfdfa")) {
            System.out.println("Must specify proper analysis type [fa|dfa|mfdfa].");
            System.exit(0);
        }

        String[] inputFileFolder = clip.targets();
        if (inputFileFolder.length == 0) {
            System.out.println("Must specify the target file or folder.");
            System.exit(0);
        }
        File input = new File(inputFileFolder[0]);
        if (!input.exists()) {
            System.out.println("No file or folder.");
            System.exit(0);
        }
        boolean details = clip.switchPresent("-d");
        switch (analysisType) {
            case "dfa":
                TestUtils.RunDFA(input,details);
                break;
            case "mfdfa":
                TestUtils.RunMFWidth(input,details);
                break;
            case "fa":
                TestUtils.RunFA(input,details);
                break;
            default:
                break;
        }

    }

}
