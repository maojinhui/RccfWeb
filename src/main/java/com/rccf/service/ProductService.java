package com.rccf.service;


import com.rccf.model.ProductDiya;
import com.rccf.model.ProductZhiya;


public interface ProductService {

    ProductDiya fincProductdiyaByID(int id);

    ProductZhiya findProductZhiyaByID(int id);

}
