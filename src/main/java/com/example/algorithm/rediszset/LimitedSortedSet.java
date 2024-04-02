package com.example.algorithm.rediszset;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author 吕茂陈
 * @description 用Java实现类似redis的sorted set数据结构，但是增加总容量限制，如果超过容量，则删除score最小的元素，并且支持查询score在某个区间的元素
 * @date 2024/1/5 14:42
 */
public class LimitedSortedSet<T> {

    private final int capacity;
    private final TreeMap<Double, TreeSet<T>> sortedSet;

    public LimitedSortedSet(int capacity) {
        this.capacity = capacity;
        this.sortedSet = new TreeMap<>();
    }

    public void add(T element, double score) {
        if (sortedSet.size() >= capacity) {
            removeMinScoreElement();
        }

        sortedSet.computeIfAbsent(score, k -> new TreeSet<>()).add(element);
    }

    public void remove(T element) {
        for (TreeSet<T> set : sortedSet.values()) {
            set.remove(element);
        }
        cleanEmptySets();
    }

    public void printElements() {
        System.out.println("Elements in the Sorted Set: " + sortedSet);
    }

    public void printElementsByScoreRange(double minScore, double maxScore) {
        Map<Double, TreeSet<T>> subMap = sortedSet.subMap(minScore, true, maxScore, true);
        System.out.println("Elements in the Score Range [" + minScore + ", " + maxScore + "]: " + subMap);
    }

    private void removeMinScoreElement() {
        if (!sortedSet.isEmpty()) {
            double minScore = sortedSet.firstKey();
            TreeSet<T> minScoreSet = sortedSet.get(minScore);
            T minScoreElement = minScoreSet.first();
            minScoreSet.remove(minScoreElement);
            cleanEmptySets();
        }
    }

    private void cleanEmptySets() {
        sortedSet.values().removeIf(TreeSet::isEmpty);
    }

    public static void main(String[] args) {
        LimitedSortedSet<String> sortedSet = new LimitedSortedSet<>(5);


        sortedSet.add("Two", 2.0);
        sortedSet.add("Three", 3.2);
        sortedSet.add("One", 1.5);
        sortedSet.add("One", 1.5);

        sortedSet.printElements(); // Output: Elements in the Sorted Set: {1.5=[One], 2.0=[Two], 3.2=[Three]}

        sortedSet.add("Four", 4.8);
        sortedSet.add("Five", 5.3);

        sortedSet.printElements(); // Output: Elements in the Sorted Set: {1.5=[One], 2.0=[Two], 3.2=[Three], 4.8=[Four], 5.3=[Five]}

        sortedSet.add("Six", 0.9);

        sortedSet.printElements(); // Output: Elements in the Sorted Set: {2.0=[Two], 3.2=[Three], 4.8=[Four], 5.3=[Five], 0.9=[Six]}

        sortedSet.printElementsByScoreRange(2.0, 4.8); // Output: Elements in the Score Range [2.0, 4.8]: {2.0=[Two], 3.2=[Three], 4.8=[Four]}
    }
}