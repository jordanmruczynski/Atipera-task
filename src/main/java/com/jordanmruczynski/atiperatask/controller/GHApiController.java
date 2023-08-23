package com.jordanmruczynski.atiperatask.controller;

import com.jordanmruczynski.atiperatask.exceptions.types.InvalidAcceptHeaderException;
import com.jordanmruczynski.atiperatask.exceptions.types.ResourceNotFoundException;
import com.jordanmruczynski.atiperatask.model.ResponseModel;
import com.jordanmruczynski.atiperatask.service.GHApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("api/v1/atiperatask")
public class GHApiController {

    private final GHApiService ghApiService;

    public GHApiController(GHApiService ghApiService) {
        this.ghApiService = ghApiService;
    }

    @RequestMapping(path = "{username}", method = RequestMethod.GET, produces = "application/json")
    public Flux<ResponseModel> getUserRepositories(@PathVariable("username") String username, @RequestHeader("Accept") String acceptHeader) {
        if ("application/xml".equals(acceptHeader)) {
            throw new InvalidAcceptHeaderException("XML format is not supported");
        }
        return ghApiService.getUserRepositories(username);
    }
}
