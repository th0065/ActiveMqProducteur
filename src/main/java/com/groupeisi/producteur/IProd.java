package com.groupeisi.producteur;


import javax.jms.JMSException;

public interface IProd {
	 
	public int setMessage(  String subject ,String mess) throws JMSException;

}
