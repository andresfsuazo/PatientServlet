package PatientServlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import patientManager.Patient;
import patientManager.PatientCollection;

/**
 * implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private PatientCollection patients;
	private CollectionExtension patients;
	private HashMap<String, String> webUsers;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        patients = new CollectionExtension();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private String loadData() {
    	
		String labelValue="";
		String[] data = patients.toSortedStringArray();
		System.out.println(patients.size());
        
		for(int i=0;i<data.length;i++){
        	String[] temp = data[i].split(",");
        	//System.out.println(data[i]);
        	labelValue+="<tr>";
        	labelValue+="<td>"+temp[0]+"</td>";
        	labelValue+="<td>"+temp[1]+"</td>";
        	labelValue+="<td>"+temp[2]+"</td>";
        	labelValue+="<td>"+temp[3]+"</td>";
        	labelValue+="<td>"+temp[4]+"</td>";
        	labelValue+="</tr>";
       }
        
		return labelValue;
    }
    
    private String loadUsers() {
    	
    	webUsers = new HashMap<String, String>();
    	File file = new File("C:\\Users\\Andres Suazo\\Desktop\\Desktop Folders\\Java Class\\PatientServlet\\PatientWeb\\WebContent\\users.csv");
		
		try{
        	
			Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                String line = input.nextLine();
                String[] pairs = line.split(",");
                webUsers.put(pairs[0], pairs[1]);
            }

            input.close();
        }
        catch (FileNotFoundException exception) {
        	return "File not found!";
        }
		
		
		return "Users Loaded";
    }
    
    private boolean exists(HashMap<String, String> map, String key, String value) {
    	boolean toreturn = false;
    	if(map.get(key) != null) {
    		toreturn = (map.get(key).equals(value));
    	}
        
        return toreturn;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Submit")!=null){
			System.out.println(loadUsers());
			String username = request.getParameter("username");
            String password = request.getParameter("password");
                  
            if(exists(webUsers, username, password)) {
            
            	patients.loadPatients("C:\\Users\\Andres Suazo\\Desktop\\Desktop Folders\\Java Class\\PatientServlet\\PatientWeb\\data.csv");
    			String label="patientData";
    			String labelValue=loadData();
    	        
    			request.setAttribute(label,labelValue); 
    			 	 
    			RequestDispatcher rd=request.getRequestDispatcher("/Home.jsp");
    			rd.forward(request,response);
            }
            else {
            	response.getWriter().append("<br>Denied");
            }
            
		}
		else if(request.getParameter("Remove")!=null)
		{

			String id = request.getParameter("patientID");
			patients.removePatient(id);
			String labelValue = loadData();
			String label="patientData";

			request.setAttribute(label,labelValue); 
			RequestDispatcher rd=request.getRequestDispatcher("/Home.jsp");
			rd.forward(request,response);
			
		}
		else if(request.getParameter("Add")!=null)
		{	
			System.out.println("Add Called");
			//String inputFile = request.getParameter("Select_File");
			Part filePart = request.getPart("Select_File");
			System.out.println("Add Called2");
			System.out.println(filePart);

			//patients.addFromWebFile(inputFile);
			String labelValue = loadData();
			String label="patientData";

			request.setAttribute(label,labelValue); 
			RequestDispatcher rd=request.getRequestDispatcher("/Home.jsp");
			rd.forward(request,response);

		}
		else if(request.getParameter("Edit")!=null)
		{	
			String id = request.getParameter("patientID");
			String result = request.getParameter("result");
			patients.setResultForPatient(id, result);
			String labelValue = loadData();
			String label="patientData";
			
			request.setAttribute(label,labelValue); 
			RequestDispatcher rd=request.getRequestDispatcher("/Home.jsp");
			rd.forward(request,response);
			
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