package com.groupeisi.producteur;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ProdImpl implements IProd{
	private  ConnectionFactory connectionFactory;
	private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private Connection connection;
	private  Session session;
	private  Destination destination;
	private  MessageProducer producer;
	private TextMessage message;
	  public ProdImpl() throws JMSException{
		 
		  this.connectionFactory = new ActiveMQConnectionFactory(url);
		  this.connection = connectionFactory.createConnection();
		  this.session = connection.createSession(false,
		                Session.AUTO_ACKNOWLEDGE);
	  }
	
	public int setMessage(String subject ,String mess) throws JMSException  {
		    if(subject!=null && mess!= null) {
		    
	        connection.start();
	         destination = session.createQueue(subject);
	         producer = session.createProducer(destination);
	         message = session.createTextMessage( mess);
	        if(message!=null) {
	        	 producer.send(message);
	        	 return 1;
	        }
	       
		    }
	        connection.close();
		return 0;
	}

}
