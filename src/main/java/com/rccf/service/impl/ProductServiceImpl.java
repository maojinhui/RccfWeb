package com.rccf.service.impl;

import com.rccf.dao.ProductDao;
import com.rccf.model.ProductDiya;
import com.rccf.model.ProductZhiya;
import com.rccf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    public ProductDiya fincProductdiyaByID(int id) {
        return productDao.findProductDiyaByID(id);
    }

    public ProductZhiya findProductZhiyaByID(int id) {
        return productDao.findProductZhiyaByID(id);
    }
}
