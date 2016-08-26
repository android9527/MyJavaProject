package com.test.forkjoin;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by chenfeiyue on 16/8/26.
 */
public class TestForkJoin {

    public static void main(String[] args) throws IOException {

        args = new String[]{"/Users/chenfeiyue/Work/MyJavaProject/", "Arduino"};
        File file = new File(args[0]);
        System.out.println(file.getAbsolutePath());
        WordCounter wordCounter = new WordCounter();
        Folder folder = Folder.fromDirectory(file);
        System.out.println(wordCounter.countOccurrencesOnSingleThread(folder, args[1]));
    }
}
