package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.config.SwaggerConfig;
import com.endava.tmd.BookProject.models.WaitingList;
import com.endava.tmd.BookProject.services.WaitingListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Api(tags = {SwaggerConfig.WAITING_LIST_TAG})
@RequestMapping("/waitingList")
public class WaitingListController {

    @Autowired
    private WaitingListService waitingListService;

    @ApiOperation(value = "Find all entries from the waiting list", notes = "Returns all the waiting list entries")
    @RequestMapping(method = RequestMethod.GET)
    public List<WaitingList> getAllWaitingListEntries(){
        return waitingListService.getAllWaitingListEntries();
    }
}
