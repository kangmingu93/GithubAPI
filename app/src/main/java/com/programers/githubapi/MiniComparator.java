package com.programers.githubapi;

import java.util.Comparator;

public class MiniComparator implements Comparator<ReposVO> {
    @Override
    public int compare(ReposVO o1, ReposVO o2) {
        double firstValue = Double.parseDouble(o1.getStargazers_count());
        double secondValue = Double.parseDouble(o2.getStargazers_count());

        if (firstValue > secondValue) {
            return -1;
        } else if (firstValue < secondValue) {
            return 1;
        } else {
            return 0;
        }
    }
}
