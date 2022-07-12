package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.models.WaitingList;
import com.endava.tmd.BookProject.services.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/waitingList")
public class WaitingListController {

    @Autowired
    private WaitingListService waitingListService;

    @RequestMapping(method = RequestMethod.GET)
    public List<WaitingList> getAllWaitingListEntries(){
        return waitingListService.getAllWaitingListEntries();
    }
}
