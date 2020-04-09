package com.tivenstudio.utilities;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class DirectoryComparator {


    public DirectoryComparator() {
    }

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
