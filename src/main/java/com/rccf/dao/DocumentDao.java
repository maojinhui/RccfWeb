package com.rccf.dao;

import com.rccf.model.EmployeeDocuments;

public interface DocumentDao {

    EmployeeDocuments findDocumentById(int id);

    EmployeeDocuments findDocumentByeID(String eid);

}
