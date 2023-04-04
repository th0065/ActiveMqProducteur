# ActiveMqProducteur

Configuration:

On copie les données dans lib de activeMq et les place dans WEB-INF/lib

Ensuite on a crée une interface IProd et on l'a implementé comme tel:


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

Dans le controller : 


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


Demarrage:

On demarre activeMq ensuite on demarre le projet sur le serveur ici Wildfy.





