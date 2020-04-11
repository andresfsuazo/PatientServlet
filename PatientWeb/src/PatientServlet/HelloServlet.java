package PatientServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patientManager.PatientCollection;

/**
 * implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatientCollection patients;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        patients = new PatientCollection();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Submit")!=null)
		{
			String username = request.getParameter("username");
            String password = request.getParameter("password");
                  
            if(true) {
            
    			String label="patientData";
    			String labelValue="";
    			patients.loadPatients("C:\\Users\\Andres Suazo\\Desktop\\Desktop Folders\\Java Class\\PatientServlet\\PatientWeb\\data.csv");
    			String[] data = patients.toSortedStringArray();
    			System.out.println(patients.size());
    	        for(int i=0;i<data.length;i++){
    	        	String[] temp = data[i].split(",");
    	        	System.out.println(data[i]);
    	        	labelValue+="<tr>";
	            	labelValue+="<td>"+temp[0]+"</td>";
	            	labelValue+="<td>"+temp[1]+"</td>";
	            	labelValue+="<td>"+temp[2]+"</td>";
	            	labelValue+="<td>"+temp[3]+"</td>";
	            	labelValue+="<td>"+temp[4]+"</td>";
	            	labelValue+="</tr>";
    	        }
    	        
    			request.setAttribute(label,labelValue); 
    			 	 
    			RequestDispatcher rd=request.getRequestDispatcher("/Home.jsp");
    			rd.forward(request,response);  //forwarded to welcome.jsp
            }
            else {
            	response.getWriter().append("<br>Denied");
            }
            
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("/index.html");
			rd.forward(request,response);  //forwarded to welcome.jsp
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}