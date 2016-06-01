package com.salesorderapp.backend.daos;

import java.util.List;

import com.salesorderapp.backend.models.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Shittu on 29/05/2016.
 */

@Repository
@Transactional
public class ProductDao extends BaseDao<Product> {

  private static final String ENTITY = "Product";
  public List<Product> getAll(){
    return super.getAll(ENTITY);
  }

  public Product getById(final long code){
    return super.getById(Product.class, code);
  }
}