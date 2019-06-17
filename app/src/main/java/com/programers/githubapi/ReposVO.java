package com.programers.githubapi;

import lombok.Data;

@Data
public class ReposVO {
    private String name;
    private String description;
    private String stargazers_count;
}
