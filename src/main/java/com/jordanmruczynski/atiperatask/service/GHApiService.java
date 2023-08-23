package com.jordanmruczynski.atiperatask.service;

import com.jordanmruczynski.atiperatask.model.RepositoryInfo;
import com.jordanmruczynski.atiperatask.model.ResponseModel;

import java.util.List;

public interface GHApiService {

    List<ResponseModel> getUserRepositories(String username);
}
