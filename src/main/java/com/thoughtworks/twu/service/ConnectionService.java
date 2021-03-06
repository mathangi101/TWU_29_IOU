package com.thoughtworks.twu.service;


import com.thoughtworks.twu.domain.ConnectionDetails;
import com.thoughtworks.twu.persistence.ConnectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionService {

    private ConnectionMapper connectionMapper;

    @Autowired
    public ConnectionService(ConnectionMapper connectionMapper) {
        this.connectionMapper = connectionMapper;
    }

    public void insertConnection(ConnectionDetails connectionDetails) {
        connectionMapper.insertConnection(connectionDetails);
    }

    public ConnectionDetails getConnection(ConnectionDetails connectionDetails) {
        return connectionMapper.getConnection(connectionDetails);
    }

    public ArrayList<String> getAllConnections(String userEmail) {
        return connectionMapper.getAllConnections(userEmail);
    }
}
