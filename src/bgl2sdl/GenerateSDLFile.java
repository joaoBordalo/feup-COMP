package bgl2sdl;

import java.io.FileWriter;
import java.io.IOException;



import java.util.Map;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Namespace;




public class GenerateSDLFile {
	// Label:
	//// what is a function means an element, non-function means atribute
	//// BoO = BaseOfOperations 
	//// Loc = Location
	//// Avail = Availability
	//// Airp = Airport
	//// Heli = Helipad
	//// Runw = Runway
	private Document doc;
	private Namespace namespace;
	private int idBoO;
	private Listener listener;

	public static void  main(String[] args){

		GenerateSDLFile sdlFile = new GenerateSDLFile();
		sdlFile.createFile();

	}



	//Constructor
	public GenerateSDLFile()
	{
		doc = new Document();
		namespace = Namespace.getNamespace("dcs:scenario");
		idBoO=1;
	}

	public GenerateSDLFile(Listener listener)
	{
		doc = new Document();
		namespace = Namespace.getNamespace("dcs:scenario");
		idBoO=1;
		this.listener = listener;
	}

	public void createFile()
	{
		try {

			writeScenario();

			// create file	
			XMLOutputter xmlOutput = new XMLOutputter();
			String filename = "file.sdl";
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.setFormat(xmlOutput.getFormat().setExpandEmptyElements(true));
			xmlOutput.output(doc, new FileWriter(filename));

			System.out.println("File Created!");

		} catch (IOException io) {
			System.out.println(io.getMessage());
		}	
	}

	//generic function

	public Element coordinates(Map<String,String> m)
	{
		Element coordinates = new Element("coordinates", namespace);

		//create latitude
		Element latitude = new Element("latitude",namespace);
		latitude.setText(m.get("lat"));
		coordinates.addContent(latitude);

		//create longitude
		Element longitude = new Element("longitude",namespace);
		longitude.setText(m.get("lon"));
		coordinates.addContent(longitude);


		//create altitude
		Element altitude = new Element("altitude",namespace);
		System.out.println(m.get("alt"));
		String altAtt = m.get("alt").substring(0, m.get("alt").length()-1);

		altitude.setAttribute("measured", "amsl");
		altitude.setText(altAtt);

		coordinates.addContent(altitude);

		return coordinates;
	}

	public void length(String len, String unit)
	{

	}

	public void width(String wid, String unit)
	{

	}

	//////////////////////////////////////

	public void writeScenario()
	{

		Element scenario = new Element("scenario",namespace);

		scenario.addNamespaceDeclaration(Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance"));
		scenario.addNamespaceDeclaration(Namespace.getNamespace("xsd", "http://www.w3.org/2001/XMLSchema"));

		doc.setRootElement(scenario);

		writeBases();


	}


	////////////////////////////////////////////////////////////////////////////////
	////////////////                Inside Senario                  ////////////////
	////////////////////////////////////////////////////////////////////////////////

	public void writeBases()
	{
		Element bases = new Element("bases",namespace);
		for(int i = 0; i < listener.airportAtts.size();i++)
		{
			bases.addContent(writeBaseofOperations());
			incIdBoO();
		}
		bases.addContent(writeController());
		bases.addContent(writeAgentType());
		bases.addContent(writeNoFlyAreas());

		doc.getRootElement().addContent(bases);


	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////                  Inside Bases                  ////////////////
	////////////////////////////////////////////////////////////////////////////////

	// what is a function means an element, non-function means atribute
	// insert id: <baseOfOperations id="b1">
	public Element writeBaseofOperations()
	{
		//create element baseOfOperations
		Element baseOfOperations = new Element("baseOfOperations",namespace);
		baseOfOperations.setAttribute("id", "b"+idBoO);


		//create name
		String key= "AIRPORT" + idBoO;
		Element nameAtt = new Element("name",namespace);
		nameAtt.setText(listener.airportAtts.get(key).get("name"));
		baseOfOperations.addContent(nameAtt);

		//create mobility
		baseOfOperations.addContent(writeBoOMobility());

		//create description
		Element description = new Element("description",namespace);
		description.setText("XXX Description XXX");
		baseOfOperations.addContent(description);

		//create history

		Element history = new Element("history",namespace);
		history.setText("XXX History XXX");
		baseOfOperations.addContent(history);


		baseOfOperations.addContent(writeBoOContactPerson());
		baseOfOperations.addContent(writeBoOLocation());
		baseOfOperations.addContent(writeBoOAvailability());
		baseOfOperations.addContent(writeBoOAirport());

		return baseOfOperations;

	}

	// no elements and atributes, simply empty :  <controllers> </controllers>
	public Element writeController()
	{
		return new Element("controller",namespace);
	}

	// no elements and atributes, simply empty :  <agentTypes> </agentTypes>
	public Element writeAgentType()
	{
		return new Element("agentType",namespace);
	}

	// no elements and atributes, simply empty :  <noFlyAreas> </noFlyAreas>
	public Element writeNoFlyAreas()
	{
		return new Element("noFlyAreas",namespace);
	}


	////////////////////////////////////////////////////////////////////////////////
	////////////////            Inside BaseOfOperations             ////////////////
	////////////////////////////////////////////////////////////////////////////////

	// BoO = BaseOfOperations 

	public Element writeBoOMobility()
	{
		Element mobility = new Element("mobility",namespace);
		mobility.setAttribute("water", "true");
		mobility.setAttribute("underwater", "false");
		mobility.setAttribute("land", "true");
		mobility.setAttribute("air", "true");
		return mobility;
	}

	public Element writeBoOContactPerson()
	{
		Element contactPerson = new Element("contactPerson",namespace);

		//create name
		Element name = new Element("name",namespace);
		name.setText("XXX Name XXX");
		contactPerson.addContent(name);

		//create title
		Element title = new Element("title",namespace);
		name.setText("XXX Title XXX");
		contactPerson.addContent(title);

		//create instituition
		Element instituition = new Element("instituition",namespace);
		name.setText("XXX Instituition XXX");
		contactPerson.addContent(instituition);

		//create position
		Element position = new Element("position",namespace);
		name.setText("XXX Position XXX");
		contactPerson.addContent(position);

		//create address
		Element address = new Element("address",namespace);
		name.setText("XXX Address XXX");
		contactPerson.addContent(address);

		//create zipCode
		Element zipCode = new Element("zipCode",namespace);
		name.setText("XXX ZipCode XXX");
		contactPerson.addContent(zipCode);

		//create city
		Element city = new Element("city",namespace);
		name.setText("XXX City XXX");
		contactPerson.addContent(city);

		//create stateDistrcRegion
		Element stateDistrcRegion = new Element("stateDistrcRegion",namespace);
		name.setText("XXX StateDistrcRegion XXX");
		contactPerson.addContent(stateDistrcRegion);

		//create country
		Element country = new Element("country",namespace);
		name.setText("XXX Country XXX");
		contactPerson.addContent(country);

		//create telephone
		Element telephone = new Element("telephone",namespace);
		name.setText("XXX Telephone XXX");
		contactPerson.addContent(telephone);

		//cellphone
		Element cellphone = new Element("cellphone",namespace);
		name.setText("XXX Cellphone XXX");
		contactPerson.addContent(cellphone);

		//fax
		Element fax = new Element("fax",namespace);
		name.setText("XXX Fax XXX");
		contactPerson.addContent(fax);

		//email
		Element email = new Element("email",namespace);
		name.setText("XXX Email XXX");
		contactPerson.addContent(email);

		return contactPerson;
	}

	public Element writeBoOLocation()
	{
		Element location = new Element("location",namespace);

		//create address
		Element address = new Element("address",namespace);
		address.setText("XXX Address XXX");
		location.addContent(address);

		//create zipCode
		Element zipCode = new Element("zipCode",namespace);
		zipCode.setText("XXX ZipCode XXX");
		location.addContent(zipCode);

		//create city
		String key="AIRPORT"+idBoO;
		Element cityAtt = new Element("city",namespace);
		cityAtt.setText(listener.airportAtts.get(key).get("city"));
		location.addContent(cityAtt);

		//create stateDistrictRegion
		Element stateDistrictRegionAtt = new Element("stateDistrictRegion",namespace);

		if(listener.airportAtts.get(key).get("state")!=null)
			stateDistrictRegionAtt.setText(listener.airportAtts.get(key).get("state"));
		else if(listener.airportAtts.get(key).get("district")!=null)
			stateDistrictRegionAtt.setText(listener.airportAtts.get(key).get("district"));
		else if(listener.airportAtts.get(key).get("region")!=null)
			stateDistrictRegionAtt.setText(listener.airportAtts.get(key).get("region"));

		location.addContent(stateDistrictRegionAtt);

		//create country
		Element countryAtt = new Element("country",namespace);
		countryAtt.setText(listener.airportAtts.get(key).get("country"));
		location.addContent(countryAtt);

		location.addContent(coordinates(listener.airportAtts.get(key)));

		return location;
	}

	public Element writeBoOAvailability()
	{
		Element availability = new Element("availability",namespace);
		availability.setAttribute("available","always");

		return availability;
	}

	public Element writeBoOAirport()
	{
		Element airport= new Element("airport",namespace);
		
		//createname
		String key= "AIRPORT" + idBoO;
		Element nameAtt = new Element("name",namespace);
		nameAtt.setText(listener.airportAtts.get(key).get("name"));
		airport.addContent(nameAtt);
		
		//create description
		Element description = new Element("description",namespace);
		description.setText("XXX Description XXX");
		airport.addContent(description);
		
		//create ContactPerson
		airport.addContent(writeBoOContactPerson());
		
		//create Location
		airport.addContent(writeBoOLocation());
		
		String ident = listener.airportAtts.get(key).get("ident");
		
		//create IATA
		if(ident.length()==3)
		{
			Element IATA = new Element("IATA",namespace);
			IATA.setText(ident);
			airport.addContent(IATA);
		}
		//create ICAO
		else if(ident.length()==4)
		{
			Element ICAO = new Element("ICAO",namespace);
			ICAO.setText(ident);
			airport.addContent(ICAO);
		}
		
		//create magVar
		Element magVar = new Element("magVar",namespace);
		magVar.setText(listener.airportAtts.get(key).get("magvar"));
		airport.addContent(magVar);
		
		//<helipads>
		//airport.addContent(writeAirpHelipad());
		//</helipads>
		
		//<runways>
		//writeAirpRunway();
		//</runways>
		
		//<taxiways>
		//writeAirpTaxiway();
		//</taxiways>
		
		//<parkings>
		//writeAirpParking();
		//</parkings>
		
		//<hangars>
		//writeAirpHangar();
		//</hangars>
		
		//<utilities>
		//writeAirpUtility();
		//</utilities>
		
		return airport;
	}


	////////////////////////////////////////////////////////////////////////////////
	////////////////                Inside Location                 ////////////////
	////////////////////////////////////////////////////////////////////////////////	

	// Loc = Location
	public void writeLocCoordinates()
	{
		//latitude
		//longitude
		//altitude // example: <altitude measured="amsl">47</altitude>
	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////            Inside Availability                 ////////////////
	////////////////////////////////////////////////////////////////////////////////

	// Avail = Availability
	public void writeAvailTimeslot()
	{
		//timeSlotRecurrenceGroup 
		//every (optional)
		//repeat (optional)
		//fromDate (optional)
		//toDate (optional)
		//startDate
		//startTime
		//endDate
		//endTime
	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////                 Inside Airport                 ////////////////
	////////////////////////////////////////////////////////////////////////////////

	//Airp = Airport
	//multiple helipads
	//insert id: <helipad id="h1">
	public void writeAirpHelipad()
	{
		//designation
		//surface
		//coordinates(String lat, String lon, String alt, String mesure);
		//writeHeliRadius();

	}

	//multiple runways
	//insert id: <runway id="r1">
	public void writeAirpRunway()
	{
		//coordinates(String lat, String lon, String alt, String mesure);
		//length(String len, String unit) example: <length lengthUnit="Meter">2434</length>
		//width(String wid, String unit) example: <width lengthUnit="Meter">60.96</width>
		//surface 
		//writeRunwBaseEnd();
		//writeRunwReciprocalEnd();
	}

	public void writeAirpTaxiway()
	{

	}

	public void writeAirpParking()
	{

	}

	public void writeAirpHangar()
	{

	}

	public void writeAirpUtility()
	{

	}


	////////////////////////////////////////////////////////////////////////////////
	////////////////                Inside Helipad                  ////////////////
	////////////////////////////////////////////////////////////////////////////////

	// Heli = Helipad
	public void writeHeliCoordinates()
	{

	}

	public void writeHeliRadius()
	{

	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////                 Inside Runway                  ////////////////
	////////////////////////////////////////////////////////////////////////////////

	//Runw = Runway
	public void writeRunwBaseEnd()
	{

	}

	public void writeRunwReciprocalEnd()
	{

	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////                 Other Methods                  ////////////////
	////////////////////////////////////////////////////////////////////////////////

	public void incIdBoO()
	{
		idBoO++;
	}


}
