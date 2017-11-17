package com.test.forkjoin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by chenfeiyue on 16/8/26.
 */
public class TestForkJoin {

    public static void main(String[] args) throws IOException {

//        args = new String[]{"/Users/chenfeiyue/Work/MyJavaProject/", "Arduino"};
//        File file = new File(args[0]);
//        System.out.println(file.getAbsolutePath());
//        WordCounter wordCounter = new WordCounter();
//        Folder folder = Folder.fromDirectory(file);
//        System.out.println(wordCounter.countOccurrencesOnSingleThread(folder, args[1]));

        //该池的线程数量不会超过0*7fff个（32767）
        //池中维护着ForkJoinWorkerThread对象数组，数组大小由parallelism属性决定，parallelism默认为处理器个数
        ForkJoinPool pool = new ForkJoinPool();
        ArrayList arrayList = new ArrayList(10);
        for (int i = 0; i < 10; i++) {
            arrayList.add(i + "");
        }
        GetQuestionsTask task = new GetQuestionsTask(arrayList);
        pool.execute(task);
        // 试题列表=task.get()
        try {
            List finalList = task.get();
            System.out.println("最终结果个数：" + finalList.size());
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {

            e.printStackTrace();
        }
    }
}
