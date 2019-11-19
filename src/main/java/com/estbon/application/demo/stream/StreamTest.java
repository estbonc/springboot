package com.estbon.application.demo.stream;


import com.estbon.application.demo.bean.TestBean;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liushuaishuai
 * @version 1.0
 * @date 2018/6/5 16:03
 * @description
 */
public class StreamTest {


    public static void main(String[] args) {
        List<Integer> b = Lists.newArrayList(1, 2, 3);

        List<TestBean> a = Lists.newArrayList();
        a.add(new TestBean(1, 4));
        a.add(new TestBean(1, 3));
        a.add(new TestBean(1, 2));
        a.add(new TestBean(2, 4));
        a.add(new TestBean(2, 444));
        a.add(new TestBean(2, 2));

        List<TestBean> result = Lists.newArrayList();

        //按paperId 分组
        Map<Integer, List<TestBean>> collect = a.stream().collect(Collectors.toMap(TestBean::getId, value -> Lists.newArrayList(value),
                (List<TestBean> oldValueList, List<TestBean> newValueList) -> {
                    oldValueList.addAll(newValueList);
                    return oldValueList;
                }
        ));
        System.out.println(collect);

        Map<Integer, List<TestBean>> collect2 = a.stream().collect(Collectors.groupingBy(TestBean::getId));
        System.out.println(collect2);
        for (Map.Entry<Integer, List<TestBean>> entry : collect.entrySet()) {
            TestBean t = new TestBean();
            t.setId(entry.getKey());
            int sum = entry.getValue().stream().mapToInt(TestBean::getCount).sum();

            t.setCount(sum);
            result.add(t);
        }

        System.out.println(result);

    }


}
