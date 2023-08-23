package com.jordanmruczynski.atiperatask.service;

import com.jordanmruczynski.atiperatask.model.BranchInfo;
import com.jordanmruczynski.atiperatask.model.RepositoryInfo;
import com.jordanmruczynski.atiperatask.model.ResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GHApiServiceImpl implements GHApiService {

    private final GitHubApiClient gitHubApiClient;

    public GHApiServiceImpl(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }

    @Override
    public List<ResponseModel> getUserRepositories(String username) {
        List<RepositoryInfo> userRepositories = gitHubApiClient.fetchRepositories(username);

        return userRepositories.stream()
                .map(repositoryInfo -> {
                    List<BranchInfo> branches = gitHubApiClient.fetchBranches(username, repositoryInfo.name());
                    return new ResponseModel(repositoryInfo.name(), repositoryInfo.owner().login(), branches);
                })
                .collect(Collectors.toList());

    }
}
