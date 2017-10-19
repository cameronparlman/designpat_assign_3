package airportSecurityState.airportStates;
import airportSecurityState.airport.Airport;


public class Low_Risk implements AirportStateI{
	Airport airport;

	/*Constructor */
	public Low_Risk(Airport newAirport){
		airport = newAirport;
	}


	/*check if item is prohibited at the airport*/
	public void checkItem(String item_in){
		if(airport.get_prohibited_items().contains(item_in.toLowerCase())){
			airport.increment_total_prohibited();
			airport.update_averages();
		}	
	}


	/*PARSE A NEW PASSENGER */  // could use a helper object? 
	@Override
	public void newPassenger(String passenger){
		String delims = "[:;]+";
		String[] tokens = passenger.split(delims);
		int day=0;
		String item = "";

		try{ //parse the day of the traveller 
			day = Integer.parseInt(tokens[1]);
			item = tokens[8];
		}
		catch(NumberFormatException exception){
			System.err.print("Number Format Exception, " + exception.getMessage());
			exception.printStackTrace();	
			System.exit(1);
		}
		
		//check if a new day 
		if(day != airport.getDay()){
			airport.newDay(day);
		}
		checkItem(item);
		assess_risk();
	}	
	
		
	/* determin if change of state is necessary */
	@Override 
	public void assess_risk(){
		int avg_traffic = airport.get_avg_traffic();
		int avg_prohibited_items = airport.get_avg_prohibited_items();
	
		if(avg_traffic >= 8 || avg_prohibited_items >= 2){
			//airport.setHighRisk();
		}		
		else if(avg_traffic >= 4 || avg_prohibited_items >1){
			//airport.setModerateRisk();
		}
		else{
			//airport.setLowRisk();
		}


	}
	

	@Override
	public String toString(){return "low_risk";}


}
