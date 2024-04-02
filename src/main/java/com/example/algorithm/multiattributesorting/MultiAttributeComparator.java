package com.example.algorithm.multiattributesorting;

import java.util.Comparator;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/7/21 17:57
 */
public class MultiAttributeComparator implements Comparator<DataDemo> {

    private final double createTimeWeight;
    private final double priceWeight;
    private final double regionWeight;

    public MultiAttributeComparator(double createTimeWeight, double priceWeight, double regionWeight) {
        this.createTimeWeight = createTimeWeight;
        this.priceWeight = priceWeight;
        this.regionWeight = regionWeight;
    }

    @Override
    public int compare(DataDemo data1, DataDemo data2) {
        double score1 = calculateScore(data1);
        double score2 = calculateScore(data2);

        return Double.compare(score2, score1);
    }

    private double calculateScore(DataDemo data) {
        // 计算每个属性的得分
        double createTimeScore = data.getCreateTime();
        double priceScore = data.getPrice();
        double regionScore = data.getRegion();

        // 将得分乘以权重，得到加权得分
        double weightedCreateTimeScore = createTimeScore * createTimeWeight;
        double weightedPriceScore = priceScore * priceWeight;
        double weightedRegionScore = regionScore * regionWeight;

        // 将多个属性的加权得分相加，得到总得分

        return weightedCreateTimeScore + weightedPriceScore + weightedRegionScore;
    }
}