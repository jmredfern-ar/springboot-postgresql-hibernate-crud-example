package com.redfern.springbootpostgresql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderitem")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	//THIS WILL BE YOUR FOREIGN KEY
	//private long order_id;
	
	@ManyToOne
	private Order order;
	
	@Column(name="product_id")
	private long product_id;
	
	@Column(name="quantity")
	private int quantity;

}
