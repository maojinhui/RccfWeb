package com.rccf.service;


import com.rccf.model.ProductDiya;
import com.rccf.model.ProductZhiya;
import com.rccf.model.RAgency;


public interface ProductService {

    ProductDiya fincProductdiyaByID(int id);

    ProductZhiya findProductZhiyaByID(int id);

//    RAgency getAgencyByName(String name);



}
