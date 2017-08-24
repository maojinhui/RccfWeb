package com.rccf.dao;

import com.rccf.model.ProductDiya;
import com.rccf.model.ProductZhiya;

public interface ProductDao {

    ProductDiya findProductDiyaByID(int id);

    ProductZhiya findProductZhiyaByID(int id);

}
