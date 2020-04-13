package PatientServlet;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

import patientManager.*;

public class CollectionExtension extends PatientCollection {
	
	public CollectionExtension() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	public void addFromWebFile(File inputFile) {
		System.out.println(inputFile);
	}
	

	
}
