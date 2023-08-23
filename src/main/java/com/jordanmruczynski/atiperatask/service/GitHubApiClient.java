package com.jordanmruczynski.atiperatask.service;

import com.jordanmruczynski.atiperatask.configuration.Config;
import com.jordanmruczynski.atiperatask.exceptions.types.ResourceNotFoundException;
import com.jordanmruczynski.atiperatask.model.BranchInfo;
import com.jordanmruczynski.atiperatask.model.RepositoryInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class GitHubApiClient {

    private final Config config;
    private final RestTemplate restTemplate;

    public GitHubApiClient(Config config, RestTemplate restTemplate) {
        this.config = config;
        this.restTemplate = restTemplate;
    }

    public List<RepositoryInfo> fetchRepositories(String username) {
        String url = config.getGithubApiUrl() + "/users/" + username + "/repos";
        try {
            RepositoryInfo[] repositories = restTemplate.getForObject(url, RepositoryInfo[].class);
            return Arrays.asList(repositories);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException("No repositories found for user: " + username);
        }
    }

    public List<BranchInfo> fetchBranches(String username, String repositoryName) {
        String url = config.getGithubApiUrl() + "/repos/" + username + "/" + repositoryName + "/branches";
        BranchInfo[] branches = restTemplate.getForObject(url, BranchInfo[].class);
        return Arrays.asList(branches);
    }
}
