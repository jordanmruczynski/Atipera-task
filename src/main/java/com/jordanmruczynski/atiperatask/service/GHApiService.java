package com.jordanmruczynski.atiperatask.service;

import com.jordanmruczynski.atiperatask.model.RepositoryInfo;
import com.jordanmruczynski.atiperatask.model.ResponseModel;
import reactor.core.publisher.Flux;

import java.util.List;

public interface GHApiService {

    Flux<ResponseModel> getUserRepositories(String username);
}
