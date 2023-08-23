package com.jordanmruczynski.atiperatask;

import com.jordanmruczynski.atiperatask.configuration.Config;
import com.jordanmruczynski.atiperatask.exceptions.types.ResourceNotFoundException;
import com.jordanmruczynski.atiperatask.model.BranchInfo;
import com.jordanmruczynski.atiperatask.model.LastCommitShaInfo;
import com.jordanmruczynski.atiperatask.model.OwnerInfo;
import com.jordanmruczynski.atiperatask.model.RepositoryInfo;
import com.jordanmruczynski.atiperatask.service.GitHubApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class GitHubApiClientTest {

    @Mock
    private Config config;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GitHubApiClient underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(config.getGithubApiUrl()).thenReturn("https://api.github.com");
    }

    @Test
    void testFetchRepositories() {
        String username = "testUser";

        RepositoryInfo[] mockRepos = {new RepositoryInfo("nameRepo", new OwnerInfo("ownerLoginRepo"), false), new RepositoryInfo("nameRepo2", new OwnerInfo("ownerLoginRepo2"), false)};
        when(restTemplate.getForObject(anyString(), eq(RepositoryInfo[].class))).thenReturn(mockRepos);

        List<RepositoryInfo> result = underTest.fetchRepositories(username);

        assertEquals("The size of the result list is not correct", 2, result.size());

        verify(restTemplate).getForObject("https://api.github.com/users/testUser/repos", RepositoryInfo[].class);
    }

    @Test
    void testFetchRepositoriesNotFound() {
        String username = "testUser";
        when(restTemplate.getForObject(anyString(), eq(RepositoryInfo[].class)))
                .thenThrow(new ResourceNotFoundException("No repositories found for user: " + username));

        assertThrows(ResourceNotFoundException.class, () -> underTest.fetchRepositories(username));
    }

    @Test
    void testFetchBranches() {
        String username = "testUser";
        String repoName = "testRepo";
        BranchInfo[] mockBranches = {new BranchInfo("master", new LastCommitShaInfo("2hf7272e7r342f")), new BranchInfo("develop", new LastCommitShaInfo("adbc627239s"))};
        when(restTemplate.getForObject(anyString(), eq(BranchInfo[].class))).thenReturn(mockBranches);

        List<BranchInfo> result = underTest.fetchBranches(username, repoName);

        assertEquals("The size of the result list is not correct", 2, result.size());
        verify(restTemplate).getForObject("https://api.github.com/repos/testUser/testRepo/branches", BranchInfo[].class);
    }

}
