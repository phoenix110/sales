package com.company.sales.web.order;

import com.company.sales.entity.OrderLine;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.sales.entity.Order;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import java.math.BigDecimal;

import javax.inject.Inject;

public class OrderEdit extends AbstractEditor<Order> {

    @Inject
    private CollectionDatasource<OrderLine, java.util.UUID> linesDs;

    @java.lang.Override
    public void init(java.util.Map<java.lang.String, java.lang.Object> params) {

        linesDs.addCollectionChangeListener(e -> calculateAmount());
    }

    public void calculateAmount(){
       BigDecimal amount = BigDecimal.ZERO;
       for(OrderLine l : linesDs.getItems())
           amount = amount.add(l.getProduct().getPrice().multiply(l.getQuantity()));

        getItem().setAmount(amount);
    }
}