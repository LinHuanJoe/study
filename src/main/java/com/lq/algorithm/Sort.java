package com.lq.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: wheel
 * @Date: 2019/8/9 0009 18:00
 * @Author: lin huan
 * @Description:
 */
public class Sort {

    /**
     * 出现频率最多的 k 个元素 桶排序
     * @param nums
     * @param k
     * @return 时间复杂度O(n) 空间复杂度O(n)
     */
    public static List<Integer> topKFrequent(int[] nums, int k){
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {//高频分组（桶）
            map.put(num,map.getOrDefault(num,0)+1);
        }
        List<Integer>[] lists = new List[nums.length+1];//最差情况 所有都一样

        for (Integer num : map.keySet()) {
            Integer count = map.get(num);
            if (lists[count]==null){
                lists[count] = new ArrayList<>();
            }
            lists[count].add(num);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = lists.length - 1; i >= 0; i--) {
            List<Integer> list = lists[i];
            if (list == null)continue;
            result.addAll(list);
            if (--k==0){//这里处理成第k高频以上的所有元素,如是只要k个元素，就只取k个元素
                break;
            }
        }
        return result;
    }
}
