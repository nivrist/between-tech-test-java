package com.betweentech.application.projections;

import java.math.BigDecimal;
import java.util.Date;

public interface PricesProjection {
    Long getBrand_Id();
    Date getStart_Date();
    Date getEnd_Date();
    Integer getPrice_List();
    Long getProduct_Id();
    Integer getPriority();
    BigDecimal getPrice();
    String getCurr();
}
