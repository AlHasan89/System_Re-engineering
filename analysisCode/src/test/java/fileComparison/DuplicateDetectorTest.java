package fileComparison;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by neilwalkinshaw on 07/11/2017.
 */
public class DuplicateDetectorTest {

    /**
     *  Change the following variable to point to the root directory containing any source files or sub-directories
     *  with source files.
     */
    String srcRootDirectory = "../assertj-core/src";

    @Test
    public void DuplicateTestOnSelf(){
        File root = new File(srcRootDirectory);
        DuplicateDetector dd = new DuplicateDetector(root,"java");
        double[][] scores = dd.fileComparison(true);
        try {
            TablePrinter.printRelations(scores,new File("../dataFiles/3C_CodeDuplication/1- AllClasses/fileComparison.csv"),dd.files);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}