package com.groupeisi.controller;

import java.io.IOException;
import java.util.List;

import javax.jms.JMSException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.groupeisi.producteur.IProd;
import com.groupeisi.producteur.ProdImpl;



/**
 * Servlet implementation class Controller
 */
@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	private IProd iprod;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 this.getServletContext().getRequestDispatcher("/ecrit.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String objet = request.getParameter("objet");
		String message = request.getParameter("message");
		
		if(objet!=null && message != null ) {
			int result=0;
			ProdImpl prod = null;
				try {
					prod = new ProdImpl();
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			 try {
				 result = prod.setMessage(objet, message);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(result==1) {
				 request.setAttribute("success", "message envoyer avec succès !!!");
				 this.getServletContext().getRequestDispatcher("/ecrit.jsp").forward(request, response); 
			 }
		}
	}

}
