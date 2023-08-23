package com.jordanmruczynski.atiperatask.model;

public record RepositoryInfo(
        String name,
        OwnerInfo owner,
        boolean fork) {
}
