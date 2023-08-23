package com.jordanmruczynski.atiperatask.model;

public record BranchInfo(
        String name,
        LastCommitShaInfo commit
) {
}
