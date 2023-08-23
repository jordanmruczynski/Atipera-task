package com.jordanmruczynski.atiperatask;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.jordanmruczynski.atiperatask.model.BranchInfo;
import com.jordanmruczynski.atiperatask.model.LastCommitShaInfo;
import com.jordanmruczynski.atiperatask.model.OwnerInfo;
import com.jordanmruczynski.atiperatask.model.RepositoryInfo;
import com.jordanmruczynski.atiperatask.service.GHApiServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static reactor.core.publisher.Mono.when;

public class GhApiServiceTest {

}

