package com.example.algorithm.rediszset;

import cn.hutool.core.date.DateUtil;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/1/5 16:18
 */
public class SimpleLimitedSortedSet<T> {
    private final int capacity;
    private final TreeMap<Double, T> sortedSet;

    public SimpleLimitedSortedSet(int capacity) {
        this.capacity = capacity;
        this.sortedSet = new TreeMap<>();
    }

    public void add(T element, double score) {
        if (sortedSet.size() >= capacity) {
            removeMinScoreElement();
        }
        sortedSet.put(score, element);
    }

    public void remove(T element) {
        sortedSet.values().remove(element);
        cleanEmptyMapEntries();
    }

    public void printElements() {
        System.out.println("Elements in the Sorted Set: " + sortedSet);
    }

    private void removeMinScoreElement() {
        if (!sortedSet.isEmpty()) {
            double minScore = sortedSet.firstKey();
            sortedSet.remove(minScore);
        }
    }

    private void cleanEmptyMapEntries() {
        sortedSet.values().removeIf(Objects::isNull);
    }

    public void printElementsByScoreRange(double minScore, double maxScore) {
        Map<Double, T> subMap = sortedSet.subMap(minScore, true, maxScore, true);
        System.out.println("Elements in the Score Range [" + minScore + ", " + maxScore + "]: " + subMap);
    }

    public static void main(String[] args) {
        SimpleLimitedSortedSet<String> sortedSet = new SimpleLimitedSortedSet<>(5);

        sortedSet.add("One", 1.5);
        sortedSet.add("Two", 2.0);
        sortedSet.add("Three", 3.2);

        sortedSet.printElements(); // Output: Elements in the Sorted Set: {1.5=One, 2.0=Two, 3.2=Three}

        sortedSet.add("Four", 4.8);
        sortedSet.add("Five", 5.3);

        sortedSet.printElements(); // Output: Elements in the Sorted Set: {1.5=One, 2.0=Two, 3.2=Three, 4.8=Four, 5.3=Five}

        sortedSet.add("Six", 0.9);
        sortedSet.add("sev", DateUtil.currentSeconds());

        sortedSet.printElements(); // Output: Elements in the Sorted Set: {2.0=Two, 3.2=Three, 4.8=Four, 5.3=Five, 0.9=Six}

        sortedSet.printElementsByScoreRange(2.0, DateUtil.currentSeconds());
    }
}
