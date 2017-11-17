package com.test.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class GetQuestionsTask extends RecursiveTask<List> {

    //参数map
    private Map map;
    //参数list==只放题型
    private List questionTypeList;

    public GetQuestionsTask(List questionTypeList) {
        this.questionTypeList = questionTypeList;
    }

    @Override
    protected List compute() {
        System.out.println(questionTypeList.size());
        List list = new ArrayList();
        if (questionTypeList.size() < 2) {
            // 抽题
            list = getQuestions(questionTypeList);
        } else {
            int size = questionTypeList.size();
            int mid = size / 2;
            GetQuestionsTask task1 = new GetQuestionsTask(
                    questionTypeList.subList(0, mid));
            GetQuestionsTask task2 = new GetQuestionsTask(
                    questionTypeList.subList(mid, size));
            invokeAll(task1, task2);
            try {
                list = groupResults(task1.get(), task2.get());
            } catch (InterruptedException | ExecutionException e) {

                e.printStackTrace();
            }
        }

        return list;
    }

    //合并抽题结果
    private List groupResults(List list1, List list2) {
        System.out.println(Thread.currentThread().getName() + "开始合并结果......");
        // 合并返回结果
        List list = new ArrayList();
        list.addAll(list1);
        list.addAll(list2);
        System.out.println(Thread.currentThread().getName() + "合并结果结束......");
        return list;
    }

    // 抽题
    private List getQuestions(List questTypeList) {
        List list = new ArrayList();
        for (int i = 0; i < questTypeList.size(); i++) {
            System.out.println(Thread.currentThread().getName() + "开始抽题......" + questionTypeList.get(i).toString());
            //假数据，向list中放试题
            list.add("0");
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "抽题结束..." + questionTypeList.get(i).toString());
        }
        return list;
    }

}