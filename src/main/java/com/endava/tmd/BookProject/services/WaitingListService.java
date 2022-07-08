package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.WaitingList;
import com.endava.tmd.BookProject.repositories.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingListService {

    @Autowired
    private WaitingListRepository waitingListRepository;

    public List<WaitingList> getAllWaitingListEntries(){
        return waitingListRepository.findAll();
    }
}
