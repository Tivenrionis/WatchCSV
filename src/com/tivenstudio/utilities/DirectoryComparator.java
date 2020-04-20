package com.tivenstudio.utilities;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Michał Wesołowski
 * @version 1.0 <br>
 * Date: 09.04.2020 <br>
 * Time: 16:50 <br>
 * Class name: DirectoryComparator<br>
 * Description: This class is used to compare two different File objects
 * referring to two different directory paths.
 */
public class DirectoryComparator {

    /**
     * This method takes two File objects, each denotes to directory path
     * and returns files not present in second directory.
     * It takes first given directory and checks if the files located there
     * are present in second given directory path.
     *
     * @param dirA - Directory to check if its files are present in <b>dirB</b>
     * @param dirB - Directory to check if its containing files of directory <b>dirA</b>
     * @return Set of File objects representing result of subtracting <b>dirB</b> from <b>dirA</b>.
     */
    public static Set<File> compare(File dirA, File dirB) {
        Set<File> toBeDeleted = new HashSet<>();
        Set<File> filesInDirA = new DirectoryReader(dirA).getFiles();
        Set<File> filesInDirB = new DirectoryReader(dirB).getFiles();

        for (File file : filesInDirA) {
            for (File file1 : filesInDirB) {
                if (file.getName().equals(file1.getName())) {
                    toBeDeleted.add(file);

                }
            }

        }
        filesInDirA.removeAll(toBeDeleted);
        System.out.println("[REMOVED] " + toBeDeleted);
        return filesInDirA;

    }

}
