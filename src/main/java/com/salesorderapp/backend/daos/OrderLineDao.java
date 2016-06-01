package com.salesorderapp.backend.daos;

import java.util.List;

import com.salesorderapp.backend.models.OrderLine;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Shittu on 30/05/2016.
 */

@Repository
@Transactional
public class OrderLineDao extends BaseDao<OrderLine>{

  private static final String ENTITY = "OrderLine";
  public List<OrderLine> getAll(){
    return super.getAll(ENTITY);
  }

  public OrderLine getById(final long code){
    return super.getById(OrderLine.class, code);
  }

  public List<OrderLine> getBySalesCode(final long salesCode){
    return super.getBySalesCode(ENTITY, salesCode);
  }

}
