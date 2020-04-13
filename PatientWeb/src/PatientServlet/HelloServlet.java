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
    
    //Loads patient collection data
    //Creates a string with html tags to create a table filled with patient data to display
    private String loadData() {
    	
		String labelValue="";
		String[] data = patients.toSortedStringArray();
		System.out.println(patients.size());
        
		//Sorround each value with html tags for table rows
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
    
    //Loads username and password pairs from csv file
    private String loadUsers() {
    	
    	webUsers = new HashMap<String, String>();
    	File file = new File(this.getServletContext().getRealPath("/users.csv"));
    	//Read file line by line and add value pairs to user hashmap
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
    
    //Utility function to check if entered credentials are in hashmap
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
			
			//Get username and password text entries
			String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            //If user is authenticated, load main page
            if(exists(webUsers, username, password)) {
            
            	//Load patients from data file
            	patients.loadPatients(this.getServletContext().getRealPath("/data.csv"));
    			String label="patientData";
    			
    			//Create table to be displayed with patient data
    			String labelValue=loadData();
    	        
    			request.setAttribute(label,labelValue); 
    			
    			//Go to main page
    			RequestDispatcher rd=request.getRequestDispatcher("/Home.jsp");
    			rd.forward(request,response);
            }
            //Go back to login
            else {
            	RequestDispatcher rd=request.getRequestDispatcher("/index.html");
    			rd.forward(request,response);  //forwarded to login
            }
            
		}
		//When remove patient is pressed
		else if(request.getParameter("Remove")!=null)
		{
			//Get current id in spinner
			String id = request.getParameter("patientID");
			patients.removePatient(id);
			//Recreate data table with changes
			String labelValue = loadData();
			String label="patientData";

			request.setAttribute(label,labelValue); 
			RequestDispatcher rd=request.getRequestDispatcher("/Home.jsp");
			rd.forward(request,response);
			
		}
		//When add patient is pressed
		//Not finished
		else if(request.getParameter("Add")!=null)
		{	
			System.out.println("Add Called");
			//String inputFile = request.getParameter("Select_File");
			Part filePart = request.getPart("Select_File");
			System.out.println("Add Called2");
			System.out.println(filePart);

			//patients.addFromWebFile(inputFile);
			
			//Recreate data table with changes
			String labelValue = loadData();
			String label="patientData";

			request.setAttribute(label,labelValue); 
			RequestDispatcher rd=request.getRequestDispatcher("/Home.jsp");
			rd.forward(request,response);

		}
		//When edit patient is pressed
		else if(request.getParameter("Edit")!=null)
		{	
			//Get current id in spinner
			String id = request.getParameter("patientID");
			//Get the selected radio button (CR or DP)
			String result = request.getParameter("result");
			
			//Set the patients result and reload data table
			patients.setResultForPatient(id, result);
			String labelValue = loadData();
			String label="patientData";
			
			request.setAttribute(label,labelValue); 
			RequestDispatcher rd=request.getRequestDispatcher("/Home.jsp");
			rd.forward(request,response);
			
		}
		//If submit is not recognized, go back to login
		//Used by logout button
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