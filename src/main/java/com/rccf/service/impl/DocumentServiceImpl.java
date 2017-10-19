package com.rccf.service.impl;

import com.rccf.dao.DocumentDao;
import com.rccf.model.EmployeeDocuments;
import com.rccf.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {


    @Autowired
    DocumentDao dao;

    public EmployeeDocuments findDocumentById(int id) {
        return dao.findDocumentById(id);
    }

    public EmployeeDocuments findDocumentByeID(String eid) {
        return dao.findDocumentByeID(eid);
    }
}
