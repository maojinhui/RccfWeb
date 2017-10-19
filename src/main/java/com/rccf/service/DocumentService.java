package com.rccf.service;

import com.rccf.model.EmployeeDocuments;

public interface DocumentService {

    EmployeeDocuments findDocumentById(int id);

    EmployeeDocuments findDocumentByeID(String eid);


}
