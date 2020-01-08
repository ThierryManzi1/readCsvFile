package readfile2;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 saveCsv empl = new saveCsv();
	        
	        try{
	            //emp.readdata();
	           empl.storeData();
	           empl.displayData();
	          
	        }catch(Exception e){
	            System.out.println(e);
	        }

	}

}
