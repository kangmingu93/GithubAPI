package com.programers.githubapi;

import lombok.Data;

@Data
public class ReposVO implements Comparable<ReposVO>{
    private String name;
    private String description;
    private String stargazers_count;

    @Override
    public int compareTo(ReposVO o) {
        if (Integer.parseInt(this.stargazers_count) > Integer.parseInt(o.stargazers_count)) {
            return -1;
        } else if (Integer.parseInt(this.stargazers_count) < Integer.parseInt(o.stargazers_count)) {
            return 1;
        }
        return 0;
    }
}
