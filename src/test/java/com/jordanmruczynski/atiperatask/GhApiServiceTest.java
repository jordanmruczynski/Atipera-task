package com.jordanmruczynski.atiperatask;

import com.jordanmruczynski.atiperatask.service.GHApiServiceImpl;
import com.jordanmruczynski.atiperatask.service.GitHubApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GhApiServiceTest {

    @Mock
    private GitHubApiClient gitHubApiClient;
    private GHApiServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new GHApiServiceImpl(gitHubApiClient);
    }

    @Test
    void itShouldGetUserRepositories() {
        // Given
        String username = "jordanmruczynski";
        // When
        underTest.getUserRepositories(username);
        // Then
        verify(gitHubApiClient).fetchRepositories(username);
    }
}
