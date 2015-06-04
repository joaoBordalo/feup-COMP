package bgl2sdl;

import gen.XMLParser;
import gen.XMLParserBaseListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import org.antlr.v4.runtime.misc.NotNull;

//para confirmar campos:
//https://msdn.microsoft.com/en-us/library/cc526978.aspx

//TODO ver se recebe efectivamente floats em vez de strings em lat/lon/alt
//TODO confirmar se nos required, todas as keys sao diferentes (nao haver atributos dups)

public class Listener extends XMLParserBaseListener {
	XMLParser parser;
	Map<String,Map<String,String>> airportAtts = new HashMap<String,Map<String, String>>(); //<nomeElemento,valorElemento>
	Map<String,String> airportElems = new HashMap<String, String>();
	
	Map<String,Map<String,String>> fuelAtts = new HashMap<String,Map<String, String>>();
	
	Map<String, Map<String, String>> towerAtts= new HashMap<String,Map<String, String>>();  
	
	Map<String,Map<String,String>> runwayAtts = new HashMap<String,Map<String, String>>();
	Map<String,String> runwayElems = new HashMap<String, String>();
	
	Map<String,Map<String,String>> ilsAtts = new HashMap<String,Map<String, String>>();
	Map<String,String> ilsElems = new HashMap<String, String>();
	
	Map<String, Map<String, String>> markingsAtts= new HashMap<String,Map<String, String>>();  
	Map<String, Map<String, String>> lightsAtts= new HashMap<String,Map<String, String>>();  
	Map<String, Map<String, String>> offsetThresholdAtts = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> approachLighstsAtts = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> vasiAtts = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> glideSlopeAtts = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> blastPadAtts = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> runwayStartAtts = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> overrunAtts = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> taxiwayPointAtts = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> taxiwayParkingAtts = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> taxiNameAtts = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> taxiwayPathAtts = new HashMap<String, Map<String, String>>();

	
	Vector<String> airportAttNames; // para verificar se os nomes dos atributos sao corretos
	Vector<String> fuelAttNames;
	Vector<String> towerAttNames;
	Vector<String> runwayAttNames;
	Vector<String> markingsAttNames;
	Vector<String> lightsAttNames;
	Vector<String> offsetThresholdAttNames;
	Vector<String> approachLighstsAttNames;
	Vector<String> vasiAttNames;
	Vector<String> ilsAttNames;
	Vector<String> glideSlopeAttNames;
	Vector<String> runwayStartAttNames;
	Vector<String> blastPadAttNames;
	Vector<String> overrunAttNames;
	Vector<String> taxiwayPointAttNames;
	Vector<String> taxiwayParkingAttNames;
	Vector<String> taxiwayParkingTypeValues;
	Vector<String> taxiwayParkingNameValues;
	Vector<String> taxiNameAttNames;
	Vector<String> taxiwayPathNames;
	Vector<String> taxiwayPathTypeValues;
	Vector<String> taxiwayPathSurfaceValues;
	
	Vector<String> airportRequired; // para verificar se os nomes dos atributos sao corretos
	Vector<String> fuelRequired;
	Vector<String> towerRequired ;
	Vector<String> runwayRequired;
	Vector<String> markingsRequired;
	Vector<String> lightsRequired;
	Vector<String> offsetThresholdRequired;
	Vector<String> approachLighstsRequired;
	Vector<String> vasiRequired;
	Vector<String> vasiTypeValues;
	Vector<String> ilsRequired;
	Vector<String> glideSlopeRequired;
	Vector<String> runwayStartRequired;
	Vector<String> blastPadRequired;
	Vector<String> overrunRequired;
	Vector<String> taxiwayPointRequired;
	Vector<String> taxiwayParkingRequired;
	Vector<String> taxiNameRequired;
	Vector<String> taxiwayPathRequired;
	
	
	
	
	
	int actualAirportIndex=0;
	int actualFuelIndex =0;
	int actualTowerIndex=0;
	int actualRunwayIndex=0;
	int actualMarkingsIndex=0;
	int actualLightsIndex=0;
	int actualOffsetThresholdIndex=0;
	int actualApproachLightsIndex=0;
	int actualVasiIndex=0;
	int actualIlsIndex=0;
	int actualGlideSlopeIndex=0;
	int actualRunwayStartIndex=0;
	int actualBlastPadIndex=0;
	int actualOverrunIndex=0;
	int actualTaxiwayPointIndex=0;
	int actualTaxiwayParkingIndex=0;
	int actualTaxiNameIndex=0;
	int actualTaxiwayPathindex=0;
	
	public Listener(XMLParser parser)
	{
		this.parser= parser;
		
		airportRequired= new Vector<String>();
		airportRequired.add("lat");
		airportRequired.add("lon");
		airportRequired.add("alt");
		airportRequired.add("ident");
		airportRequired.add("airportTestRadius");
		airportRequired.add("trafficScalar");
		
		
		airportAttNames= new Vector<String>();
		airportAttNames.add("region");
		airportAttNames.add("country");
		airportAttNames.add("state");
		airportAttNames.add("city");
		airportAttNames.add("name");
		airportAttNames.add("lat");
		airportAttNames.add("lon");
		airportAttNames.add("alt");
		airportAttNames.add("magvar");
		airportAttNames.add("ident");
		airportAttNames.add("airportTestRadius");
		airportAttNames.add("trafficScalar");
		
		fuelAttNames= new Vector<String>();
		fuelAttNames.add("type");
		fuelAttNames.add("availability");
		
		towerRequired= new Vector<String>();
		towerRequired.add("lat");
		towerRequired.add("lon");
		towerRequired.add("alt");
		
		
		towerAttNames= new Vector<String>();
		towerAttNames.add("lat");
		towerAttNames.add("lon");
		towerAttNames.add("alt");
		
		
		runwayRequired= new Vector<String>();
		runwayRequired.add("lat");
		runwayRequired.add("lon");
		runwayRequired.add("alt");
		runwayRequired.add("surface");
		runwayRequired.add("heading");
		runwayRequired.add("length");
		runwayRequired.add("width");
		runwayRequired.add("number");
		//runwayRequired.add("primaryDesignator");
		//runwayRequired.add("secondaryDesignator");
		//runwayRequired.add("primaryMarkingBias");
		//runwayRequired.add("secondaryMarkingBias");
		
		runwayAttNames= new Vector<String>();
		runwayAttNames.add("lat");
		runwayAttNames.add("lon");
		runwayAttNames.add("alt");
		runwayAttNames.add("surface");
		runwayAttNames.add("heading");
		runwayAttNames.add("length");
		runwayAttNames.add("width");
		runwayAttNames.add("number");
		runwayAttNames.add("designator");
		runwayAttNames.add("primaryDesignator");
		runwayAttNames.add("secondaryDesignator");
		runwayAttNames.add("patternAltitude");
		runwayAttNames.add("primaryTakeoff");
		runwayAttNames.add("primaryLanding");
		runwayAttNames.add("primaryPattern");
		runwayAttNames.add("secondaryTakeoff");
		runwayAttNames.add("secondaryLanding");
		runwayAttNames.add("secondaryPattern");
		runwayAttNames.add("primaryMarkingBias");
		runwayAttNames.add("secondaryMarkingBias");
		
		
		markingsRequired = new Vector<String>();
		markingsRequired.add("alternateThreshold");
		markingsRequired.add("alternateTouchdown");
		markingsRequired.add("alternateFixedDistance");
		markingsRequired.add("alternatePrecision");
		markingsRequired.add("leadingZeroIdent");
		markingsRequired.add("noThresholdEndArrows");
		markingsRequired.add("edges");
		markingsRequired.add("threshold");
		markingsRequired.add("fixedDistance");
		markingsRequired.add("touchdown");
		markingsRequired.add("dashes");
		markingsRequired.add("ident");
		markingsRequired.add("precision");
		markingsRequired.add("edgePavement");
		markingsRequired.add("singleEnd");
		markingsRequired.add("primaryClosed");
		markingsRequired.add("secondaryClosed");
		markingsRequired.add("primaryStol");
		markingsRequired.add("secondaryStol");
		
		markingsAttNames = new Vector<String>();
		markingsAttNames.add("alternateThreshold");
		markingsAttNames.add("alternateTouchdown");
		markingsAttNames.add("alternateFixedDistance");
		markingsAttNames.add("alternatePrecision");
		markingsAttNames.add("leadingZeroIdent");
		markingsAttNames.add("noThresholdEndArrows");
		markingsAttNames.add("edges");
		markingsAttNames.add("threshold");
		markingsAttNames.add("fixedDistance");
		markingsAttNames.add("touchdown");
		markingsAttNames.add("dashes");
		markingsAttNames.add("ident");
		markingsAttNames.add("precision");
		markingsAttNames.add("edgePavement");
		markingsAttNames.add("singleEnd");
		markingsAttNames.add("primaryClosed");
		markingsAttNames.add("secondaryClosed");
		markingsAttNames.add("primaryStol");
		markingsAttNames.add("secondaryStol");	
		
		lightsRequired = new Vector<String>();
		lightsRequired.add("center");
		lightsRequired.add("edge");
		lightsRequired.add("centerRed");
		
		lightsAttNames = new Vector<String>();
		lightsAttNames.add("center");
		lightsAttNames.add("edge");
		lightsAttNames.add("centerRed");
		
		
		offsetThresholdRequired = new Vector<String>();
		offsetThresholdRequired.addElement("end");
		offsetThresholdRequired.addElement("length");
	
		
		offsetThresholdAttNames = new Vector<String>();
		offsetThresholdAttNames.addElement("end");
		offsetThresholdAttNames.addElement("length");
		offsetThresholdAttNames.addElement("width");
		offsetThresholdAttNames.addElement("surface");
		
		approachLighstsAttNames = new Vector<String>();
		approachLighstsAttNames.addElement("end");
		approachLighstsAttNames.addElement("system");
		approachLighstsAttNames.addElement("strobes");
		approachLighstsAttNames.addElement("reil");
		approachLighstsAttNames.addElement("touchdown");
		approachLighstsAttNames.addElement("endLights");
		
		approachLighstsRequired = new Vector<String>();
		approachLighstsRequired.addElement("end");
		
		vasiAttNames = new Vector<String>();
		vasiAttNames.add("end");
		vasiAttNames.add("type");
		vasiAttNames.add("side");
		vasiAttNames.add("biasX");
		vasiAttNames.add("biasZ");
		vasiAttNames.add("spacing");
		vasiAttNames.add("pitch");
		
		vasiRequired = new Vector<String>();
		vasiRequired.add("end");
		vasiRequired.add("type");
		vasiRequired.add("side");
		vasiRequired.add("biasX");
		vasiRequired.add("biasZ");
		vasiRequired.add("spacing");
		vasiRequired.add("pitch");
		
		vasiTypeValues= new Vector<String>();
		vasiTypeValues.add("PAPI2");
		vasiTypeValues.add("PAPI4");
		vasiTypeValues.add("PVASI");
		vasiTypeValues.add("TRICOLOR");
		vasiTypeValues.add("TVASI");
		vasiTypeValues.add("VASI21");
		vasiTypeValues.add("VASI22");
		vasiTypeValues.add("VASI23");
		vasiTypeValues.add("VASI31");
		vasiTypeValues.add("VASI32");
		vasiTypeValues.add("VASI33");
		vasiTypeValues.add("BALL");
		vasiTypeValues.add("APAP");
		vasiTypeValues.add("PANELS");
		
		ilsAttNames = new Vector<String>();
		ilsAttNames.add("lat");
		ilsAttNames.add("lon");
		ilsAttNames.add("alt");
		ilsAttNames.add("heading");
		ilsAttNames.add("frequency");
		ilsAttNames.add("end");
		ilsAttNames.add("range");
		ilsAttNames.add("magvar");
		ilsAttNames.add("ident");
		ilsAttNames.add("width");
		ilsAttNames.add("name");
		ilsAttNames.add("backCourse");
		
		
		glideSlopeAttNames = new Vector<String>();
		glideSlopeAttNames.add("lat");
		glideSlopeAttNames.add("lon");
		glideSlopeAttNames.add("alt");
		glideSlopeAttNames.add("pitch");
		glideSlopeAttNames.add("range");
		
		runwayStartAttNames= new Vector<String>();
		runwayStartAttNames.add("type");
		runwayStartAttNames.add("lat");
		runwayStartAttNames.add("lon");
		runwayStartAttNames.add("alt");
		runwayStartAttNames.add("heading");
		runwayStartAttNames.add("end");
		
		blastPadRequired = new Vector<String>();
		blastPadRequired.add("end");
		blastPadRequired.add("length");
		
		blastPadAttNames = new Vector<String>();
		blastPadAttNames.add("end");
		blastPadAttNames.add("length");
		blastPadAttNames.add("width");
		blastPadAttNames.add("surface");
		
		overrunAttNames= new Vector<String>();
		overrunAttNames.add("end");
		overrunAttNames.add("length");
		overrunAttNames.add("width");
		overrunAttNames.add("surface");
		
		taxiwayPointAttNames= new Vector<String>();
		taxiwayPointAttNames.add("index");
		taxiwayPointAttNames.add("type");
		taxiwayPointAttNames.add("orientation");
		taxiwayPointAttNames.add("lat");
		taxiwayPointAttNames.add("lon");
		taxiwayPointAttNames.add("biasX");
		taxiwayPointAttNames.add("biasZ");
		
		
		taxiwayParkingAttNames= new Vector<String>();
		taxiwayParkingAttNames.add("index");
		taxiwayParkingAttNames.add("lat");
		taxiwayParkingAttNames.add("lon");
		taxiwayParkingAttNames.add("biasX");
		taxiwayParkingAttNames.add("biasZ");
		taxiwayParkingAttNames.add("heading");
		taxiwayParkingAttNames.add("radius");
		taxiwayParkingAttNames.add("type");
		taxiwayParkingAttNames.add("name");
		taxiwayParkingAttNames.add("number");
		taxiwayParkingAttNames.add("airlineCodes");
		taxiwayParkingAttNames.add("pushBack");
		taxiwayParkingAttNames.add("teeOffset1");
		taxiwayParkingAttNames.add("teeOffset2");
		taxiwayParkingAttNames.add("teeOffset3");
		taxiwayParkingAttNames.add("teeOffset4");
		
		taxiwayParkingTypeValues= new Vector<String>();
		taxiwayParkingTypeValues.add("NONE");
		taxiwayParkingTypeValues.add("DOCK_GA");
		taxiwayParkingTypeValues.add("FUEL");
		taxiwayParkingTypeValues.add("GATE_HEAVY");
		taxiwayParkingTypeValues.add("GATE_MEDIUM");
		taxiwayParkingTypeValues.add("GATE_SMALL");
		taxiwayParkingTypeValues.add("RAMP_CARGO");
		taxiwayParkingTypeValues.add("RAMP_GA");
		taxiwayParkingTypeValues.add("RAMP_GA_LARGE");
		taxiwayParkingTypeValues.add("RAMP_GA_MEDIUM");
		taxiwayParkingTypeValues.add("RAMP_GA_SMALL");
		taxiwayParkingTypeValues.add("RAMP_MIL_CARGO");
		taxiwayParkingTypeValues.add("RAMP_MIL_COMBAT");
		taxiwayParkingTypeValues.add("VEHICLE");
		
		taxiwayParkingNameValues= new Vector<String>();
		taxiwayParkingNameValues.add("PARKING");
		taxiwayParkingNameValues.add("DOCK");
		taxiwayParkingNameValues.add("GATE");
		for(Character tmp='A';tmp<='Z';tmp++)
		{
			taxiwayParkingNameValues.add("GATE_"+tmp);
		}
		taxiwayParkingNameValues.add("NONE");
		taxiwayParkingNameValues.add("N_PARKING");
		taxiwayParkingNameValues.add("NE_PARKING");
		taxiwayParkingNameValues.add("NW_PARKING");
		taxiwayParkingNameValues.add("SE_PARKING");
		taxiwayParkingNameValues.add("S_PARKING");
		taxiwayParkingNameValues.add("SW_PARKING");
		taxiwayParkingNameValues.add("W_PARKING");
		taxiwayParkingNameValues.add("E_PARKING");
		
		taxiNameAttNames= new Vector<String>();
		taxiNameAttNames.add("index");
		taxiNameAttNames.add("name");
		
		taxiwayPathNames = new Vector<String>();
		taxiwayPathNames.add("type");
		taxiwayPathNames.add("start");
		taxiwayPathNames.add("end");
		taxiwayPathNames.add("width");
		taxiwayPathNames.add("weightLimit");
		taxiwayPathNames.add("surface");
		taxiwayPathNames.add("drawSurface");
		taxiwayPathNames.add("drawDetail");
		taxiwayPathNames.add("centerLine");
		taxiwayPathNames.add("centerLineLighted");
		taxiwayPathNames.add("leftEdge");
		taxiwayPathNames.add("leftEdgeLighted");
		taxiwayPathNames.add("rightEdge");
		taxiwayPathNames.add("rightEdgeLighted");
		taxiwayPathNames.add("number");
		taxiwayPathNames.add("designator");
		taxiwayPathNames.add("name");
		
		taxiwayPathTypeValues = new Vector<String>();
		taxiwayPathTypeValues.add("RUNWAY");
		taxiwayPathTypeValues.add("PARKING");
		taxiwayPathTypeValues.add("TAXI");
		taxiwayPathTypeValues.add("PATH");
		taxiwayPathTypeValues.add("CLOSED");
		taxiwayPathTypeValues.add("VEHICLE");
		
		taxiwayPathSurfaceValues = new Vector<String>();
		taxiwayPathSurfaceValues.add("ASPHALT");
		taxiwayPathSurfaceValues.add("BITUMINOUS");
		taxiwayPathSurfaceValues.add("BRICK");
		taxiwayPathSurfaceValues.add("CLAY");
		taxiwayPathSurfaceValues.add("CEMENT");
		taxiwayPathSurfaceValues.add("CONCRETE");
		taxiwayPathSurfaceValues.add("CORAL");
		taxiwayPathSurfaceValues.add("DIRT");
		taxiwayPathSurfaceValues.add("GRASS");
		taxiwayPathSurfaceValues.add("GRAVEL");
		taxiwayPathSurfaceValues.add("ICE");
		taxiwayPathSurfaceValues.add("MACADAM");
		taxiwayPathSurfaceValues.add("OIL_TREATED");
		taxiwayPathSurfaceValues.add("PLANKS");
		taxiwayPathSurfaceValues.add("SAND");
		taxiwayPathSurfaceValues.add("SHALE");
		taxiwayPathSurfaceValues.add("SNOW");
		taxiwayPathSurfaceValues.add("STEEL_MATS");
		taxiwayPathSurfaceValues.add("TARMAC");
		taxiwayPathSurfaceValues.add("UNKNOWN");
		taxiwayPathSurfaceValues.add("WATER");

		
	}
	

	
	@Override public void exitAttribute(@NotNull XMLParser.AttributeContext ctx) 
	{ 
		String attName=ctx.attributeName().getText();
		String attValue = ctx.attributeValue().getText();
		//AirportAtts.get(airportIndex).put(attName, attValue);
		//System.out.println("att name: "+ attName + " att val: " + attValue);
		
	}
	
	@Override 
	public void enterDocument(@NotNull XMLParser.DocumentContext ctx) 
	{ 
		//System.out.println("entrei no documento!!");
	}
	
	
	
	@Override 
	public void enterAirportElement(@NotNull XMLParser.AirportElementContext ctx) 
	{ 
		/*
		 * airport attributes:
		 * region? string 48
		 * country? string 48
		 * state? string	48
		 * city? string 48
		 * name? strin 48
		 * lat+ float | (gr-min-seg) , -90<=gr<=90
		 * lon+ float | (gr-min-seg) , -180<=gr<=180
		 * alt+ float m/f default: m
		 * magvar? float -360<=magvar<=360 deafult 0
		 * ident+ string 4
		 * airportTestRadius+ float(m|f|n)
		 * traficScalar+ float 0.01<= traficScaler<=1
		 */	
		
		actualAirportIndex++;
		
		
	}

	
	@Override 
	public void exitAirportElement(@NotNull XMLParser.AirportElementContext ctx) 
	{ 
	
		int counter= 0;
		
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		
		for(XMLParser.AttributeContext aCtx : ctx.attribute())
		{
			counter++;
			
			String attName=aCtx.attributeName().getText();
			String [] attReturn;
			
			//validar nome de atributo e verificar ordem
			if(airportAttNames.contains(attName) )
			{
				
				switch (attName) {
				case "lat":
					
					
					    if(AttributesChecker.lat(aCtx))
						m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					
					
					break;
					
				case "lon":
					
					if(AttributesChecker.lon(aCtx))
					m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					
					
					break;
					
				case "alt":
		
					
						String altString = AttributesChecker.alt(aCtx);
						m.put(aCtx.attributeName().getText(), altString);
						
	
				break;
				
				case "ident":
					
					
					if(AttributesChecker.ident(aCtx))
					m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					
					
				break;
					
				case "airportTestRadius":
					
					//ir buscar ultimo char
					
					if(AttributesChecker.atr(aCtx))
					m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					
					
					break;
					
				case "trafficScalar":
					
					if(AttributesChecker.trafficscalar(aCtx))
					m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					
					
					break;
				case "magvar":
					
					if(AttributesChecker.magvar(aCtx))
					m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
						
						
					break;
				default:
					
					if(AttributesChecker.stringchecker(aCtx))
					m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					break;
				}
				
			}
			else
			{
				
				System.out.println("Line "+aCtx.getStart().getLine()+": Wrong attribute name or wrong order in airport. used: "+ aCtx.attributeName().getText());
				return;
			}
			
			
		}
		  for (String s: airportRequired)
	        {
	          if(!m.containsKey(s))
	          {
	        	  System.out.println("The required attribute "+s+" in element Airport not found");
	        	  return;
	          }
	        }
	
		airportAtts.put("AIRPORT"+actualAirportIndex,m);
		
		//TODO - erro aqui
		//System.out.println(airportAtts.get(m.get("ident")).keySet());
		//System.out.println(airportAtts.get(m.get("ident")).values());
		
	}

	
	@Override public void enterServicesElement(@NotNull XMLParser.ServicesElementContext ctx) 
	{ 
		
	}
	
	@Override public void exitServicesElement(@NotNull XMLParser.ServicesElementContext ctx)
	{ 
		/*Map<String, String> m = new LinkedHashMap<String, String>();
		
		for(XMLParser.FuelElementContext fCtx : ctx.)
		{
			
		}
		
		airportElems.add(m)*/
		
		//aqui se calhar devia linkado os services ao airport ou entao liknar directo o que ta dentro dos services.
	}

	@Override public void enterServicesElements(@NotNull XMLParser.ServicesElementsContext ctx) { }
	
	@Override public void exitServicesElements(@NotNull XMLParser.ServicesElementsContext ctx) 
	{
		
	}
	
	@Override public void enterFuelElement(@NotNull XMLParser.FuelElementContext ctx) {
		/*
		 * fuel attributes:
		 * type+ string (73|87|100|130|145|MOGAS|JET|JETA|JETA1|JETAP|JETB|JET4|JET5|UNKNOWN)
		 * availability+ string (YES|NO|UNKNOWN|PRIOR_REQUEST)
		 */	
		
		actualFuelIndex++;
	}
	
	@Override public void exitFuelElement(@NotNull XMLParser.FuelElementContext ctx) 
	{ 

		Map<String, String> m = new LinkedHashMap<String, String>();
		
		for(XMLParser.AttributeContext fCtx : ctx.attribute())
		{
			String attName=fCtx.attributeName().getText();
			String[] attReturn;
			if(fuelAttNames.contains(attName))
			{
				switch (attName) {
				case "type":
					attReturn=AttributesChecker.typefuel(fCtx);
					m.put(attReturn[0], attReturn[1]);
					break;
					
				case "availability":
					attReturn=AttributesChecker.availability(fCtx);
					m.put(attReturn[0], attReturn[1]);
					break;

				default:
					System.out.println("Line "+fCtx.getStart().getLine()+": Wrong fuel attribute name. use only type or availability.");
					return;
					//break;
				}
			}
			
		}
		
		fuelAtts.put("fuel"+actualFuelIndex, m);
		airportElems.put("fuel"+actualFuelIndex, "AIRPORT"+actualAirportIndex);
	}
	
	@Override public void enterTowerElement(@NotNull XMLParser.TowerElementContext ctx) {
		/*
		 * tower attributes:
		 * lat+ float | (gr-min-seg) , -90<=gr<=90
		 * lon+ float | (gr-min-seg) , -180<=gr<=180
		 * alt+ float m/f default: m
		 */
		actualTowerIndex++;
	}

	@Override public void exitTowerElement(@NotNull XMLParser.TowerElementContext ctx) { 
		
		
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext tCtx : ctx.attribute())
		{
			
			
			String attName=tCtx.attributeName().getText();
			
			//validar nome de atributo e verificar ordem
			if(towerAttNames.contains(attName))
			{
				
				switch (attName) {
				case "lat":
					
					if(AttributesChecker.lat(tCtx))
					m.put(tCtx.attributeName().getText(), tCtx.attributeValue().getText());
					
					break;
					
				case "lon":
					
					
					if(AttributesChecker.lon(tCtx))
					m.put(tCtx.attributeName().getText(), tCtx.attributeValue().getText());
					
					break;
					
				case "alt":
		
					String altString = AttributesChecker.alt(tCtx);
					m.put(tCtx.attributeName().getText(), altString);
	
				break;
				
				
				default:
					
					break;
				}
				
			}
			else
			{
				
				System.out.println("Line "+tCtx.getStart().getLine()+": Wrong attribute name. used: "+ tCtx.attributeName().getText());
				return;
			}
			
			
		}
		  for (String s: towerRequired)
	        {
	          if(!m.containsKey(s))
	          {
	        	  System.out.println("The required attribute "+s+" in element Tower not found");
	        	  return;
	          }
	        }
			towerAtts.put("TOWER"+actualTowerIndex, m);
			airportElems.put("TOWER"+actualTowerIndex, "AIRPORT"+actualAirportIndex);

		
		
	}
	
	@Override public void enterRunwayElement(@NotNull XMLParser.RunwayElementContext ctx) {
//TODO confirmar o number: 00-36
		/*
		 * runway attributes
		 * lat+ float | (gr-min-seg) , -90<=gr<=90
		 * lon+ float | (gr-min-seg) , -180<=gr<=180
		 * alt+ float m/f default: m
		 * surface+ string (ASPHALT|BITUMINOUS|BRICK|CLAY|CEMENT|CONCRETE|CORAL|DIRT|GRASS|GRAVEL|ICE|MACADAM|OIL_TREATED, PLANKS|SAND|SHALE|SNOW|STEEL_MATS|TARMAC|UNKNOWN|WATER)
		 * heading+ float 0<=heading<=360
		 * length+ float m/f default: m
		 * width+ float m/f default: m
		 * number+ string (00 to 36 | EAST|NORTH|NORTHEAST|NORTHWEST|SOUTH|SOUTHEAST|SOUTHWEST|WEST)
		 * designator? string (NONE|C|CENTER|L|LEFT|R|RIGHT|W|WATER|A|B)
		 * primaryDesignator? string =designator
		 * secondaryDesignator? string =designator
		 * patternAltitude? float m/f default: m
		 * primaryTakeoff? string (TRUE/YES|FALSE/NO) default:TRUE
		 * primaryLanding? string (TRUE|FALSE) default:TRUE
		 * primaryPattern? string (LEFT|RIGHT) default:LEFT
		 * secondaryTakeoff? string (TRUE|FALSE) default:TRUE
		 * secondaryLanding? string (TRUE|FALSE) default:TRUE
		 * secondaryPattern? string (LEFT|RIGHT) default:LEFT
		 */
		actualRunwayIndex++;
	}

	@Override public void exitRunwayElement(@NotNull XMLParser.RunwayElementContext ctx) { 
		

		
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext rCtx : ctx.attribute())
		{
			
			String attName=rCtx.attributeName().getText();
			
			//validar nome de atributo
			if(runwayAttNames.contains(attName))
			{
				
				switch (attName) {
				case "lat":
					
					if(AttributesChecker.lat(rCtx))
					m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					
					break;
					
				case "lon":
					
					if(AttributesChecker.lon(rCtx))
					m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					
					break;
					
				case "alt":
		
					String altString = AttributesChecker.alt(rCtx);
					m.put(rCtx.attributeName().getText(), altString);
					

				break;

				case "surface"://req
					
					if(AttributesChecker.rwsurface(rCtx))
					m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					
					break;
					
				case "heading"://req
					
					if(AttributesChecker.heading(rCtx))
					m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());

					break;
					
					
				case "length"://req
					
					String lenghtString = AttributesChecker.alt(rCtx);
					m.put(rCtx.attributeName().getText(), lenghtString);
					
					break;
					
				case "width"://req
					
					String widthString = AttributesChecker.alt(rCtx);
					m.put(rCtx.attributeName().getText(), widthString);
					
					break;
					
				case "number"://req
					
					if(AttributesChecker.number(rCtx))
					m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					
					break;
					
				case "designator":
					
					if(AttributesChecker.designator(rCtx))
					m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					m.put("secondaryDesignator", rCtx.attributeValue().getText() );
					
					
					break;
				
				case "primaryDesignator":
					
					if(AttributesChecker.designator(rCtx))
					m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					
					
					break;
					
				case "patternAltitude":
					
					String patternString = AttributesChecker.alt(rCtx);
					m.put(rCtx.attributeName().getText(), patternString);
					
					break;
					
				case "primaryTakeoff":
					
					String ptString = AttributesChecker.trueorfalse(rCtx);
					m.put(rCtx.attributeName().getText(), ptString);
					
					break;
					
				case "primaryLanding":
					String plString = AttributesChecker.trueorfalse(rCtx);
					m.put(rCtx.attributeName().getText(), plString);
					break;
					
				case "primaryPattern":
					
					String ppString = AttributesChecker.leftorright(rCtx);
					m.put(rCtx.attributeName().getText(), ppString);
					
					break;
					
				case "secondaryTakeoff":
					String stString = AttributesChecker.trueorfalse(rCtx);
					m.put(rCtx.attributeName().getText(), stString);
					break;
					
				case "secondaryLanding":
					String slString = AttributesChecker.trueorfalse(rCtx);
					m.put(rCtx.attributeName().getText(), slString);
					
					break;
					
				case "secondaryPattern":
					String spString = AttributesChecker.leftorright(rCtx);
					m.put(rCtx.attributeName().getText(), spString);
					break;
					
				case "primaryMarkingBiaas":
					String pmbString = AttributesChecker.markingbias(rCtx);
					m.put(rCtx.attributeName().getText(), pmbString);
					break;
					
				case "secondaryMarkingBias":
					String smbString = AttributesChecker.markingbias(rCtx);
					m.put(rCtx.attributeName().getText(), smbString);
					break;

				default:
					m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					break;
				}
				
			}
			else
			{
				
				System.out.println("Line "+rCtx.getStart().getLine()+": Wrong attribute name. used: "+ rCtx.attributeName().getText());
				return;
			}
			
			
		}
		for (String s: runwayRequired)
        {
          if(!m.containsKey(s))
          {
        	  System.out.println("The required attribute "+s+" in element Runway not found");
        	  return;
          }
        }
		
		runwayAtts.put("RUNWAY"+actualRunwayIndex, m);
		airportElems.put("RUNWAY"+actualRunwayIndex, "AIRPORT"+actualAirportIndex);
	}

	@Override public void enterRunwayElements(@NotNull XMLParser.RunwayElementsContext ctx) 
	{ 
		
	/*(nesta ordem)
	 * Markings
	 * Lights
	 * OffsetThreshold
	 * BlastPad
	 * Overun
	 * ApporachLights
	 * VASI
	 * ILS
	 * RunwayStart
	 */
		//TODO FALTA na gramatica: BlastPad, Overun, RunwayStart
	}
	
	@Override public void exitRunwayElements(@NotNull XMLParser.RunwayElementsContext ctx) 
	{
		
	}
	
	@Override public void enterMarkingsElement(@NotNull XMLParser.MarkingsElementContext ctx) 
	{ 
		actualMarkingsIndex++;
		
	}

	@Override public void exitMarkingsElement(@NotNull XMLParser.MarkingsElementContext ctx) { 
		int requiredCounter = 0;
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext mCtx : ctx.attribute())
		{
			String attName=mCtx.attributeName().getText();
			
			//validar nome de atributo e verificar ordem
			if(markingsAttNames.contains(attName))
			{
				String mString = AttributesChecker.trueorfalse(mCtx);
				m.put(mCtx.attributeName().getText(), mString);
			}	
			else
			{
			System.out.println("Line "+mCtx.getStart().getLine()+": Wrong argument name. used:"+ attName+ ". use "+ markingsAttNames);	
			}
		}
		
			for (String s: markingsRequired)
	        {
	          if(!m.containsKey(s))
	          {
	        	  System.out.println("The required attribute "+s+" in element Markings not found");
	        	  return;
	          }
	        }
			markingsAtts.put("MARKINGS"+actualMarkingsIndex, m);
			runwayElems.put("MARKINGS"+actualMarkingsIndex, "RUNWAY"+actualRunwayIndex);
		
	}

	@Override public void enterLightsElement(@NotNull XMLParser.LightsElementContext ctx) { 
		/*
		 * lights attributes:
		 * center+ string (NONE (default)|LOW|MEDIUM|HIGH)
		 * edge+ string (NONE (default)|LOW|MEDIUM|HIGH)
		 * centerRed+ string (TRUE|FALSE)
		 */
		
		actualLightsIndex++;
	}

	@Override public void exitLightsElement(@NotNull XMLParser.LightsElementContext ctx) { 
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		
		for(XMLParser.AttributeContext lCtx : ctx.attribute())
		{
			
			
			String attName=lCtx.attributeName().getText();
			
			//validar nome de atributo e verificar ordem
			if(lightsAttNames.contains(attName))
			{
				
				switch (attName) {	
				
				case "center":
					
					String ctString = AttributesChecker.lights(lCtx);
					m.put(lCtx.attributeName().getText(), ctString);
					
					break;
					
				case "edge":
					
					String edString = AttributesChecker.lights(lCtx);
					m.put(lCtx.attributeName().getText(), edString);
					
					break;
					
				case "centerRed":
					
					String crString = AttributesChecker.trueorfalse(lCtx);
					m.put(lCtx.attributeName().getText(), crString);
					break;
				
				default:
					
					break;
				}
				
			}
			else
			{
				
				System.out.println("Line "+lCtx.getStart().getLine()+": Wrong attribute name. used: "+ lCtx.attributeName().getText());
				return;
			}
			
			
		}
		
		for (String s: lightsRequired)
        {
          if(!m.containsKey(s))
          {
        	  System.out.println("The required attribute "+s+" in element Lights not found");
        	  return;
          }
        }
			
			lightsAtts.put("LIGHTS"+actualLightsIndex, m);
			airportElems.put("LIGHTS"+actualLightsIndex, "RUNWAY"+actualRunwayIndex);
		
	}

	@Override public void enterOffsetThresholdElement(@NotNull XMLParser.OffsetThresholdElementContext ctx) { 
		
		/*
		 * offsetThreshold attributes
		 * end+ string (PRIMARY|SECONDARY)
		 * length+ float m/f default: m
		 * width? float m/f default: m
		 * surface? string (ASPHALT|BITUMINOUS|BRICK|CLAY|CEMENT|CONCRETE|CORAL|DIRT|GRASS|GRAVEL|ICE|MACADAM|OIL_TREATED, PLANKS|SAND|SHALE|SNOW|STEEL_MATS|TARMAC|UNKNOWN|WATER) 
		 */
		
		actualOffsetThresholdIndex++;
		
	}

	@Override public void exitOffsetThresholdElement(@NotNull XMLParser.OffsetThresholdElementContext ctx) { 
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		
		for(XMLParser.AttributeContext otCtx : ctx.attribute())
		{
			String attName=otCtx.attributeName().getText();
			
			//validar nome de atributo e verificar ordem
			if(offsetThresholdAttNames.contains(attName))
			{
				
				switch (attName) {	

				case "end":
					
					if(AttributesChecker.end(otCtx))
					m.put(otCtx.attributeName().getText(), otCtx.attributeValue().getText());
					
					break;

				case "length"://req
					
					String lenghtString = AttributesChecker.alt(otCtx);
					m.put(otCtx.attributeName().getText(), lenghtString);
					
					break;

				case "width":
					
					String widthString = AttributesChecker.alt(otCtx);
					m.put(otCtx.attributeName().getText(), widthString);
					
					break;

				case "surface":
					
					if(AttributesChecker.otsurface(otCtx))
					m.put(otCtx.attributeName().getText(), otCtx.attributeValue().getText());
					
					break;

				default:
					break;
				}

			}
			else
			{				
				System.out.println("Line "+otCtx.getStart().getLine()+": Wrong attribute name. used: "+ otCtx.attributeName().getText());
				return;
			}
			
			
		}
		  for (String s: offsetThresholdRequired)
	        {
	          if(!m.containsKey(s))
	          {
	        	  System.out.println("The required attribute "+s+" in element offsetThreshold not found");
	        	  return;
	          }
	        }
		
			offsetThresholdAtts.put("OFFSETTHRESHOLD"+actualOffsetThresholdIndex, m);
			airportElems.put("OFFSETTHRESHOLD"+actualOffsetThresholdIndex, "RUNWAY"+actualRunwayIndex);
		
	}

	@Override public void enterApproachLightsElement(@NotNull XMLParser.ApproachLightsElementContext ctx) {
		
		/*
		 * approachlights attributes
		 * end+ string (PRIMARY, SECONDARY)
		 * system? string (NONE|ALSF1|ALSF2|CALVERT|CALVERT2|MALS|MALSF|MALSR|ODALS|RAIL|SALS|SALSF|SSALF|SSALR|SSALS)
		 * strobes? integer >0 default:0
		 * reil? integer (TRUE|FALSE)
		 * touchdown? integer (TRUE|FALSE)
		 * endLights? integer (TRUE|FALSE)
		 */
		
		actualApproachLightsIndex++;
	}

	@Override public void exitApproachLightsElement(@NotNull XMLParser.ApproachLightsElementContext ctx) {
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		
		for(XMLParser.AttributeContext alCtx : ctx.attribute())
		{
			String attName=alCtx.attributeName().getText();
			
			//validar nome de atributo e verificar ordem
			if(approachLighstsAttNames.contains(attName))
			{
				
				switch (attName) {	

				case "end":
					if(AttributesChecker.end(alCtx))
						m.put(alCtx.attributeName().getText(), alCtx.attributeValue().getText());
					break;
					
				case "system":
					
					if(AttributesChecker.system(alCtx))
						m.put(alCtx.attributeName().getText(), alCtx.attributeValue().getText());
					
					break;
					
				case "strobes":
					
					String stString = AttributesChecker.integerchecker(alCtx);
					m.put(alCtx.attributeName().getText(), stString);
					
					break;
					
				case "reil":
					String rlString = AttributesChecker.trueorfalse(alCtx);
					m.put(alCtx.attributeName().getText(), rlString);
					break;
					
				case "touchdown":
					String tdString = AttributesChecker.trueorfalse(alCtx);
					m.put(alCtx.attributeName().getText(), tdString);
					break;
					
				case "endLights":
					String endString = AttributesChecker.trueorfalse(alCtx);
					m.put(alCtx.attributeName().getText(), endString);
					break;
					
					
				default:
					break;
				}

			}
			else
			{				
				System.out.println("Line "+alCtx.getStart().getLine()+": Wrong attribute name. used: "+ alCtx.attributeName().getText());
				return;
			}
			
			
		}
		
		  for (String s: approachLighstsRequired)
	        {
	          if(!m.containsKey(s))
	          {
	        	  System.out.println("The required attribute "+s+" in element approachLighsts not found");
	        	  return;
	          }
	        }
		
			approachLighstsAtts.put("APPROACHLIGHTS"+actualApproachLightsIndex, m);
			airportElems.put("APPROACHLIGHTS"+actualApproachLightsIndex, "RUNWAY"+actualRunwayIndex);
		
	}

	@Override public void enterVasiElement(@NotNull XMLParser.VasiElementContext ctx) 
	{ 
		actualVasiIndex++;
	}

	@Override public void exitVasiElement(@NotNull XMLParser.VasiElementContext ctx) 
	{ 
		Map<String, String> m = new LinkedHashMap<String, String>();
		
		for(XMLParser.AttributeContext vCtx : ctx.attribute())
		{
			String attName=vCtx.attributeName().getText();
			
			//validar nome de atributo e verificar ordem
			if(vasiAttNames.contains(attName))
			{
				String attVal = vCtx.attributeValue().getText();
				
				switch (attName) {
				case "end":
					if(AttributesChecker.end(vCtx))
						m.put(vCtx.attributeName().getText(), vCtx.attributeValue().getText());
					break;
				case "type":
					
					if(!vasiTypeValues.contains(attVal))
					{
						System.out.println("Line "+vCtx.getStart().getLine()+": Wrong type vaule. must be one of "+ vasiTypeValues);
						return;
					}
					else
					{
						m.put(attName, attVal);
						
					}
					
					break;
				case "side":
					
					String sideString = AttributesChecker.leftorright(vCtx);
					m.put(attName, sideString);
					
					break;
				case "biasX":
					
					String biasXString = AttributesChecker.alt(vCtx);
					m.put(attName, biasXString);
					
					
					break;
				case "biasZ":
					
					String biasZString = AttributesChecker.alt(vCtx);
					m.put(attName, biasZString);
					
					break;
				case "spacing":
					
					if(AttributesChecker.end(vCtx))
					{
					String spacingString = AttributesChecker.alt(vCtx);
					m.put(attName, spacingString);
					}
					
					
					
					break;
				case "pitch":
					
					if(AttributesChecker.end(vCtx))
					m.put(attName, vCtx.attributeName().getText());
						
					break;
				default:
					System.out.println("ups");
					break;
				}
			}
			else
			{
				System.out.println("Line "+vCtx.getStart().getLine()+": Wrong attribute name. used: "+ attName +". use only: " + vasiAttNames);
				return;
			}
		}
		for (String s: vasiRequired)
        {
          if(!m.containsKey(s))
          {
        	  System.out.println("The required attribute "+s+" in element vasi not found");
        	  return;
          }
        }
		
		
		vasiAtts.put("VASI"+actualVasiIndex, m);
		runwayElems.put("VASI"+actualVasiIndex, "RUNWAY"+actualRunwayIndex);
		
	}
	
	@Override public void enterIlsElement(@NotNull XMLParser.IlsElementContext ctx) { 
		/*
		 * ils attributes
		 * lat+ float | (gr-min-seg) , -90<=gr<=90
		 * lon+ float | (gr-min-seg) , -180<=gr<=180
		 * alt+ float m/f default: m
		 * heading+ float 0<=heading<=360			
		 * frequency+ float 108.0<=frequency<=136.992
		 * end+ string (PRIMARY|SECONDARY)
		 * range? int m/n default: m , not specified: 27n
		 * magvar+ float -360<=range<=360 default: 0	
		 * ident+ string 5
		 * width? float 0<=width<=360
		 * name? string 48
		 * backCourse? string (TRUE|FALSE)
		 */
		actualIlsIndex++;
	}

	@Override public void exitIlsElement(@NotNull XMLParser.IlsElementContext ctx) { 

		int requiredCounter = 0;

		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext ilsCtx : ctx.attribute())
		{

			String attName=ilsCtx.attributeName().getText();

			//validar nome de atributo
			if(ilsAttNames.contains(attName))
			{

				switch (attName) {
				
				case "lat":
					
					if(ilsCtx.attributeValue().getText().split("-").length==3)
					{
						String[] attValue = ilsCtx.attributeValue().getText().split("-");
						
						if(Integer.parseInt(attValue[0])<-90 || Integer.parseInt(attValue[0])>90)
						{
							System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue[0]);
							return;
						}
						
					}
					try
					{
					if(Float.parseFloat(ilsCtx.attributeValue().getText())<-90.0 || Float.parseFloat(ilsCtx.attributeValue().getText())>90.0)
					{
						System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + ilsCtx.attributeValue().getText());
						return;
					}
					}
					catch(NumberFormatException e)
					{
						System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + ilsCtx.attributeValue().getText());
						return;
					}
					
					m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
					requiredCounter++;
					break;
					
				case "lon":
					
					if(ilsCtx.attributeValue().getText().split("-").length==3)
					{
						String[] attValue = ilsCtx.attributeValue().getText().split("-");
						
						if(Integer.parseInt(attValue[0])<-180 || Integer.parseInt(attValue[0])>180)
						{
							System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue[0]);
							return;
						}
						
					}	
					
					try
					{
					if(Float.parseFloat(ilsCtx.attributeValue().getText())<-180 || Float.parseFloat(ilsCtx.attributeValue().getText())>180)
					{
						System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + ilsCtx.attributeValue().getText());
						return;
					}
					}
					catch(NumberFormatException e)
					{
						System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + ilsCtx.attributeValue().getText());
						return;
					}
					m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
					requiredCounter++;
					break;
					
				case "alt":
		
					boolean def = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character altUnits = new Character (ilsCtx.attributeValue().getText().charAt(ilsCtx.attributeValue().getText().length()-1));
					if(!altUnits.equals('F') && !altUnits.equals('M'))
					{
							System.out.println("Line "+ilsCtx.getStart().getLine()+": warning: invalid alt units in ils. using default (M)");
							def=true;								
					}
					
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(ilsCtx.attributeValue().getText().substring(0, ilsCtx.attributeValue().getText().length()-1)+"M");
						m.put(ilsCtx.attributeName().getText(), value);
						requiredCounter++;
					}
					else
					{
						m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
						requiredCounter++;
					}
	
				break;
					
				case "heading"://req
					

						String attValue = ilsCtx.attributeValue().getText();
						
						if(Float.parseFloat(attValue)<0 || Float.parseFloat(attValue)>360)
						{
							System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue);
							return;
						}else{
							m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
							requiredCounter++;
						}

					break;
					
				case "frequency":
					
					String freqValue = ilsCtx.attributeValue().getText();
					
					if(Float.parseFloat(freqValue)<108.0 || Float.parseFloat(freqValue)>136.992)
					{
						System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + freqValue);
						return;
					}else{
						m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
						requiredCounter++;
					}					
					break;
					
				case "end":
					String[] endOptions = {"PRIMARY","SECONDARY"};
					if(!Arrays.asList(endOptions).contains(ilsCtx.attributeValue().getText())){

						System.out.println("Line "+ilsCtx.getStart().getLine()+": Wrong ils end: " + ilsCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(endOptions));
						return;
					}else{
						m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;
					
				case "range":
					m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
					
					break;//TODO cofirmar ^ (assumo que recebe sempre um numero, ou numero+N)
					
				case "magvar":
					
					String magvarValue = ilsCtx.attributeValue().getText();
					
					if(Float.parseFloat(magvarValue)<-360.0 || Float.parseFloat(magvarValue)>360)
					{
						System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + magvarValue + ". Must be -360 to 360. Put default: 0.0");
						m.put(ilsCtx.attributeName().getText(), "0.0");
						requiredCounter++;
					}else{
						m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
						requiredCounter++;
					}
					
					break;
					
				case "ident":
					if(ilsCtx.attributeValue().getText().length()>4)
					{
						String identValue= ilsCtx.attributeValue().getText();
						System.out.println("Line "+ilsCtx.getStart().getLine()+": ILS ident value too long: " + identValue + ". Max 4 Chars!");
						return;
					}
					m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
					requiredCounter++;
					break;
					
				case "width":
					String widthValue = ilsCtx.attributeValue().getText();
					
					if(Float.parseFloat(widthValue)<0 || Float.parseFloat(widthValue)>360)
					{
						System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + widthValue + ". Must be 0 to 360.");
						return;
					}else{
						m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
					}					
					break;
					
				case "name":
					if(ilsCtx.attributeValue().getText().length()>48)
					{
						String nameValue= ilsCtx.attributeValue().getText();
						System.out.println("Line "+ilsCtx.getStart().getLine()+": ILS ident value too long: " + nameValue + ". Max 48 Chars!");
						return;
					}
					m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
					break;
					
				case "backCourse":
					String[] backCourseOptions = {"TRUE","FALSE"};
					if(!Arrays.asList(backCourseOptions).contains(ilsCtx.attributeValue().getText())){

						System.out.println("Line "+ilsCtx.getStart().getLine()+": Wrong ils backCourse: " + ilsCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(backCourseOptions));
						return;
					}else{
						m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
					}
					break;
					
				default:
					break;

				}

			}
			else
			{

				System.out.println("Line "+ilsCtx.getStart().getLine()+": Wrong attribute name. used: "+ ilsCtx.attributeName().getText());
				return;
			}


		}
		//TODO confirmar: se nao tiver range, adiciono o default 27N
		if(!m.containsKey("range")){
			System.out.println("ILS range not defined. Put default 27N");
			m.put("range", "27N");
			requiredCounter++;
		}
		if(requiredCounter!=8)
		{
			System.out.println("Wrong number of required attributes in ils. must be 8: lat, lon, alt, heading, frequency, end, magvar, ident");
			return;
		}

		ilsAtts.put("ILS"+actualIlsIndex, m);
		airportElems.put("ILS"+actualIlsIndex, "AIRPORT"+actualAirportIndex);

	}

	
	
	@Override public void enterIlsElements(@NotNull XMLParser.IlsElementsContext ctx) { }
	
	
	

	
	@Override public void exitIlsElements(@NotNull XMLParser.IlsElementsContext ctx) { }
	
	@Override public void enterGlideSlopeElement(@NotNull XMLParser.GlideSlopeElementContext ctx) 
	{ 
		actualGlideSlopeIndex++;
	}

	@Override public void exitGlideSlopeElement(@NotNull XMLParser.GlideSlopeElementContext ctx) 
	{ 
		int requiredCounter = 0;

		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext gsCtx : ctx.attribute())
		{

			String attName=gsCtx.attributeName().getText();
			String attValue=gsCtx.attributeValue().getText();

			//validar nome de atributo
			if(glideSlopeAttNames.contains(attName))
			{

				switch (attName) {
				
				case "lat":
					
					if(AttributesChecker.lat(gsCtx))
						m.put(attName, attValue);
					
					break;
					
				case "lon":
					
					if(AttributesChecker.lon(gsCtx))
						m.put(attName, attValue);
					break;
					
				case "alt":
					
					String altString=AttributesChecker.alt(gsCtx);
					m.put(attName, altString);
					
	
				break;
				case "pitch":
					
					if(AttributesChecker.heading(gsCtx));
					m.put(attName, attValue);
					
				break;
				case "range":
					
					String rangeString=AttributesChecker.rangeGS(gsCtx);
					m.put(attName, rangeString);
					
					break; 	
				default:
					break;
				}
			}
		}
		
		
		
		for (String s: glideSlopeRequired)
        {
          if(!m.containsKey(s))
          {
        	  System.out.println("The required attribute "+s+" in element glideSlope not found");
        	  return;
          }
        }
		
		glideSlopeAtts.put("GLIDESLOPE"+actualGlideSlopeIndex, m);
		runwayElems.put("GLIDESLOPE"+actualGlideSlopeIndex, "RUNWAY"+actualRunwayIndex);
	}
	
	@Override public void enterBlastPadElement(@NotNull XMLParser.BlastPadElementContext ctx) { 
		
		/*
		 * blastpad attributes
		 * end+ string (PRIMARY|SECONDARY)
		 * length+ float m/f default: m
		 * width? float m/f default: m
		 * surface? string (ASPHALT|BITUMINOUS|BRICK|CLAY|CEMENT|CONCRETE|CORAL|DIRT|GRASS|GRAVEL|ICE|MACADAM|OIL_TREATED, PLANKS|SAND|SHALE|SNOW|STEEL_MATS|TARMAC|UNKNOWN|WATER)
		 */
		
		actualBlastPadIndex++;
	}

	@Override public void exitBlastPadElement(@NotNull XMLParser.BlastPadElementContext ctx) {
		Map<String, String> m = new LinkedHashMap<String, String>();
		
		for(XMLParser.AttributeContext bpCtx : ctx.attribute())
		{
			String attName=bpCtx.attributeName().getText();
			
			//validar nome de atributo e verificar ordem
			if(blastPadAttNames.contains(attName))
			{
				
				switch (attName) {	

				case "end":
					
					if(AttributesChecker.end(bpCtx))
					m.put(bpCtx.attributeName().getText(), bpCtx.attributeValue().getText());
					
					break;

				case "length"://req
					
					String lenghtString = AttributesChecker.alt(bpCtx);
					m.put(bpCtx.attributeName().getText(), lenghtString);
					
					break;

				case "width":
					
					String widthString = AttributesChecker.alt(bpCtx);
					m.put(bpCtx.attributeName().getText(), widthString);
					
					break;

				case "surface":
					
					if(AttributesChecker.otsurface(bpCtx))
					m.put(bpCtx.attributeName().getText(), bpCtx.attributeValue().getText());
					
					break;

				default:
					break;
				}

			}
			else
			{				
				System.out.println("Line "+bpCtx.getStart().getLine()+": Wrong attribute name. used: "+ bpCtx.attributeName().getText());
				return;
			}
			
			
		}
		  for (String s: blastPadRequired)
	        {
	          if(!m.containsKey(s))
	          {
	        	  System.out.println("The required attribute "+s+" in element blastPad not found");
	        	  return;
	          }
	        }
		
			
		blastPadAtts.put("BLASTPAD"+actualBlastPadIndex, m);
		airportElems.put("BLASTPAD"+actualBlastPadIndex, "AIRPORT"+actualAirportIndex);



	}

	@Override public void enterRunwayStartElement(@NotNull XMLParser.RunwayStartElementContext ctx) 
	{ 
		actualRunwayStartIndex++;
	}

	@Override public void exitRunwayStartElement(@NotNull XMLParser.RunwayStartElementContext ctx) 
	{ 
		int requiredCounter = 0;

		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext rsCtx : ctx.attribute())
		{

			String attName=rsCtx.attributeName().getText();

			//validar nome de atributo
			if(runwayStartAttNames.contains(attName))
			{

				switch (attName) {
				
				case "type":
					String attVal = rsCtx.attributeValue().getText();
					
					if(!attVal.equals("RUNWAY"))
					{
						System.out.println("Line "+rsCtx.getStart().getLine()+": Wrong argument value for type in runwayStart. must be RUNWAY");
					}
					else
					{
						m.put(attName, attVal);
					}
					
					break;
					
				case "lat":
					
					if(rsCtx.attributeValue().getText().split("-").length==3)
					{
						String[] attValue = rsCtx.attributeValue().getText().split("-");
						
						if(Integer.parseInt(attValue[0])<-90 || Integer.parseInt(attValue[0])>90)
						{
							System.out.println("Line "+rsCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue[0]);
							return;
						}
						
					}
					
					try
					{
					if(Float.parseFloat(rsCtx.attributeValue().getText())<-90.0 || Float.parseFloat(rsCtx.attributeValue().getText())>90.0)
					{
						System.out.println("Line "+rsCtx.getStart().getLine()+": invalid " + attName + " value : " + rsCtx.attributeValue().getText());
						return;
					}
					}
					catch(NumberFormatException e)
					{
						System.out.println("Line "+rsCtx.getStart().getLine()+": invalid " + attName + " value : " + rsCtx.attributeValue().getText());
						return;
					}
					
					m.put(rsCtx.attributeName().getText(), rsCtx.attributeValue().getText());
					requiredCounter++;
					break;
					
				case "lon":
					
					if(rsCtx.attributeValue().getText().split("-").length==3)
					{
						String[] attValue = rsCtx.attributeValue().getText().split("-");
						
						if(Integer.parseInt(attValue[0])<-180 || Integer.parseInt(attValue[0])>180)
						{
							System.out.println("Line "+rsCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue[0]);
							return;
						}
						
					}	
					
					try
					{
					if(Float.parseFloat(rsCtx.attributeValue().getText())<-180 || Float.parseFloat(rsCtx.attributeValue().getText())>180)
					{
						System.out.println("Line "+rsCtx.getStart().getLine()+": invalid " + attName + " value : " + rsCtx.attributeValue().getText());
						return;
					}
					}
					catch(NumberFormatException e)
					{
						System.out.println("Line "+rsCtx.getStart().getLine()+": invalid " + attName + " value : " + rsCtx.attributeValue().getText());
						return;
					}
					m.put(rsCtx.attributeName().getText(), rsCtx.attributeValue().getText());
					requiredCounter++;
					break;
					
				case "alt":
		
					boolean def = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character altUnits = new Character (rsCtx.attributeValue().getText().charAt(rsCtx.attributeValue().getText().length()-1));
					if(!altUnits.equals('F') && !altUnits.equals('M'))
					{
							System.out.println("Line "+rsCtx.getStart().getLine()+": warning: invalid alt units in ils. using default (M)");
							def=true;								
					}
					
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(rsCtx.attributeValue().getText().substring(0, rsCtx.attributeValue().getText().length()-1)+"M");
						m.put(rsCtx.attributeName().getText(), value);
						requiredCounter++;
					}
					else
					{
						m.put(rsCtx.attributeName().getText(), rsCtx.attributeValue().getText());
						requiredCounter++;
					}
	
				break;
				
				case "heading":
					
					String attValue = rsCtx.attributeValue().getText();
					
					if(Float.parseFloat(attValue)<0 || Float.parseFloat(attValue)>360)
					{
						System.out.println("Line "+rsCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue);
						return;
					}else{
						m.put(rsCtx.attributeName().getText(), rsCtx.attributeValue().getText());
						requiredCounter++;
					}
					
					break;
					
				case "end":
					String[] endOptions = {"PRIMARY","SECONDARY"};
					if(!Arrays.asList(endOptions).contains(rsCtx.attributeValue().getText())){

						System.out.println("Line "+rsCtx.getStart().getLine()+": Wrong offsetThreshold end: " + rsCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(endOptions));
						return;
					}else{
						m.put(rsCtx.attributeName().getText(), rsCtx.attributeValue().getText());
						
					}
					break;
				default:
					break;
				}
			}
			else
			{

				System.out.println("Line "+rsCtx.getStart().getLine()+": Wrong attribute name. used: "+ rsCtx.attributeName().getText());
				return;
			}
		}
		
		if(requiredCounter!=4)
		{
			System.out.println("Wrong number of needed arguments. must be at least this 4: "+ runwayStartAttNames);
			return;
		}
		else
		{
			runwayStartAtts.put("RUNWAYSTART"+actualRunwayStartIndex, m);
			runwayElems.put("RUNWAYSTART"+actualRunwayStartIndex, "RUNWAY"+actualRunwayIndex);
		}
		
		
	}
	
	@Override public void enterOverrunElemnt(@NotNull XMLParser.OverrunElemntContext ctx) 
	{
		actualOverrunIndex++;
	}

	@Override public void exitOverrunElemnt(@NotNull XMLParser.OverrunElemntContext ctx) 
	{ 
Map<String, String> m = new LinkedHashMap<String, String>();
		
		for(XMLParser.AttributeContext oCtx : ctx.attribute())
		{
			String attName=oCtx.attributeName().getText();
			
			//validar nome de atributo e verificar ordem
			if(overrunAttNames.contains(attName))
			{
				
				switch (attName) {	

				case "end":
					
					if(AttributesChecker.end(oCtx))
					m.put(oCtx.attributeName().getText(), oCtx.attributeValue().getText());
					
					break;

				case "length"://req
					
					String lenghtString = AttributesChecker.alt(oCtx);
					m.put(oCtx.attributeName().getText(), lenghtString);
					
					break;

				case "width":
					
					String widthString = AttributesChecker.alt(oCtx);
					m.put(oCtx.attributeName().getText(), widthString);
					
					break;

				case "surface":
					
					if(AttributesChecker.otsurface(oCtx))
					m.put(oCtx.attributeName().getText(), oCtx.attributeValue().getText());
					
					break;

				default:
					break;
				}

			}
			else
			{				
				System.out.println("Line "+oCtx.getStart().getLine()+": Wrong attribute name. used: "+ oCtx.attributeName().getText());
				return;
			}
			
			
		}
		  for (String s: overrunRequired)
	        {
	          if(!m.containsKey(s))
	          {
	        	  System.out.println("The required attribute "+s+" in element overrun not found");
	        	  return;
	          }
	        }
		
			
		overrunAtts.put("OVERUN"+actualBlastPadIndex, m);
		airportElems.put("OVERUN"+actualBlastPadIndex, "AIRPORT"+actualAirportIndex);
	}
	
	@Override public void enterTaxiwayPointElement(@NotNull XMLParser.TaxiwayPointElementContext ctx) { 
		/*
		 * taxiwayPoint attributes
		 * index+ int 0<=index<=3999 (unique on airport)
		 * type+ string (NORMAL|HOLD_SHORT|ILS_HOLD_SHORT|HOLD_SHORT_NO_DRAW|ILS_HOLD_SHORT_NO_DRAW)
		 * orientation? string (FORWARD|REVERSE)
		 * lat+ float | (gr-min-seg) , -90<=gr<=90
		 * lon+ float | (gr-min-seg) , -180<=gr<=180
		 * or
		 * biasX+ float default: m
		 * biasZ+ float default: m
		 * (can only have one pair lat-lon or bias)
		 */
		
		actualTaxiwayPointIndex++;
	}

	@Override public void exitTaxiwayPointElement(@NotNull XMLParser.TaxiwayPointElementContext ctx) { 
		
		int requiredCounter = 0;
		int latlonCounter = 0;
		int biasCounter = 0;
		boolean def=false;

		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext twpCtx : ctx.attribute())
		{

			String attName = twpCtx.attributeName().getText();
			String attVal = twpCtx.attributeValue().getText();
			//validar nome de atributo
			if(taxiwayPointAttNames.contains(attName))
			{

				switch (attName) {
				case "index":
					
					if(Integer.parseInt(attVal)<0 || Integer.parseInt(attVal)>3999)
					{
						System.out.println("Line "+twpCtx.getStart().getLine()+": invalid " + attName + " value : " + attVal +". Must be int 0 to 3999 and unique.");
						return;
					}else{
						m.put(twpCtx.attributeName().getText(), twpCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;
					
				case "type":
					
					String[] typeOptions = {"NORMAL","HOLD_SHORT","ILS_HOLD_SHORT","HOLD_SHORT_NO_DRAW","ILS_HOLD_SHORT_NO_DRAW"};
					if(!Arrays.asList(typeOptions).contains(twpCtx.attributeValue().getText())){

						System.out.println("Line "+twpCtx.getStart().getLine()+": Wrong waypoint type: " + twpCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(typeOptions));
						return;
					}else{
						m.put(twpCtx.attributeName().getText(), twpCtx.attributeValue().getText());
						requiredCounter++;
					}
					
					break;
					
				case "orientation":
					
					String[] orientationOptions = {"FORWARD", "REVERSE"};
					if(!Arrays.asList(orientationOptions).contains(twpCtx.attributeValue().getText())){

						System.out.println("Line "+twpCtx.getStart().getLine()+": Wrong waypoint orientation: " + twpCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(orientationOptions));
						return;
					}else{
						m.put(twpCtx.attributeName().getText(), twpCtx.attributeValue().getText());
					}
					
					break;
					
				case "lat":
					
					if(attVal.split("-").length==3)
					{
						String[] attValue = attVal.split("-");

						if(Float.parseFloat(attValue[0])<-90 || Float.parseFloat(attValue[0])>90)
						{
							System.out.println("Line "+twpCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue[0] + " in taxiwaypoint");
							return;
						}

					}
					
					try
					{
					if(Float.parseFloat(twpCtx.attributeValue().getText())<-90.0 || Float.parseFloat(twpCtx.attributeValue().getText())>90.0)
					{
						System.out.println("Line "+twpCtx.getStart().getLine()+": invalid " + attName + " value : " + twpCtx.attributeValue().getText());
						return;
					}
					}
					catch(NumberFormatException e)
					{
						System.out.println("Line "+twpCtx.getStart().getLine()+": invalid " + attName + " value : " + twpCtx.attributeValue().getText());
						return;
					}
					m.put(attName, attVal);
					latlonCounter++;
					break;
					
				case "lon":
					
					if(attVal.split("-").length==3)
					{
						String[] attValue = attVal.split("-");
						
						if(Float.parseFloat(attValue[0])<-180 || Float.parseFloat(attValue[0])>180)
						{
							System.out.println("Line "+twpCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue[0]+ " in taxiwaypoint");
							return;
						}
						
					}
					
					try
					{
					if(Float.parseFloat(twpCtx.attributeValue().getText())<-180 || Float.parseFloat(twpCtx.attributeValue().getText())>180)
					{
						System.out.println("Line "+twpCtx.getStart().getLine()+": invalid " + attName + " value : " + twpCtx.attributeValue().getText());
						return;
					}
					}
					catch(NumberFormatException e)
					{
						System.out.println("Line "+twpCtx.getStart().getLine()+": invalid " + attName + " value : " + twpCtx.attributeValue().getText());
						return;
					}
					m.put(attName, attVal);
					latlonCounter++;					
					break;
					
				case "biasX":

					def=false;


					//ultimo char so attvalue
					Character biasXUnits = new Character (attVal.charAt(attVal.length()-1));
					if(!biasXUnits.equals('F') && !biasXUnits.equals('M'))
					{
						System.out.println("Line "+twpCtx.getStart().getLine()+": warning: invalid biasX units in taxiwaypoint. using default (M)");
						def=true;								
					}

					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attVal.substring(0, attVal.length()-1)+"M");
						m.put(attName, value);
						biasCounter++;
					}
					else
					{
						m.put(attName, attVal);
						biasCounter++;
					}

					break;
				case "biasZ":
					
					def=false;


					//ultimo char so attvalue
					Character biasZUnits = new Character (attVal.charAt(attVal.length()-1));
					if(!biasZUnits.equals('F') && !biasZUnits.equals('M'))
					{
						System.out.println("Line "+twpCtx.getStart().getLine()+": warning: invalid biasZ units in taxiwaypoint. using default (M)");
						def=true;								
					}

					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attVal.substring(0, attVal.length()-1)+"M");
						m.put(attName, value);
						biasCounter++;
					}
					else
					{
						m.put(attName, attVal);
						biasCounter++;
					}
					
					break;
					
				default:
					break;
				}
			}
			else
			{

				System.out.println("Line "+twpCtx.getStart().getLine()+": Wrong attribute name. used: "+ twpCtx.attributeName().getText());
				return;
			}
		}//TODO testar
		if(((latlonCounter != 2) && (biasCounter != 2)) ||
				((latlonCounter == 2) && (biasCounter != 0))||
				((biasCounter == 2) && (latlonCounter!= 0)))
		{
			System.out.println("Choose one pair of attributes lat/lon or biasX/biasZ");
			return;
		}
		if(requiredCounter != 2){
			System.out.println("Wrong number of required arguments. Use at least " + taxiwayPointAttNames.get(0)+ " and " + taxiwayPointAttNames.get(1));
			return;
		}
		else
		{
			taxiwayPointAtts.put("TAIWAYPOINT"+actualTaxiwayPointIndex, m);
			airportElems.put("TAIWAYPOINT"+actualTaxiwayPointIndex, "AIRPORT"+actualAirportIndex);
		}

	}
	
	@Override public void enterTaxiwayParkingElement(@NotNull XMLParser.TaxiwayParkingElementContext ctx) 
	{ 
		actualTaxiwayParkingIndex++;
	}
	
	@Override public void exitTaxiwayParkingElement(@NotNull XMLParser.TaxiwayParkingElementContext ctx) 
	{ 
		int requiredCounter = 0;
		int latlonCounter = 0;
		int biasCounter = 0;
		boolean def=false;
		Integer val;
		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext tpCtx : ctx.attribute())
		{

			String attName=tpCtx.attributeName().getText();

			//validar nome de atributo
			if(taxiwayParkingAttNames.contains(attName))
			{
				String attVal = tpCtx.attributeValue().getText();
				switch (attName) {
				case "index":
					
					 val= Integer.parseInt(attVal);
					
					if(val<0 || val>3999)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong attribute value for index in taxiwayParking. Used " +val+ " must be between 0 and 3999.");
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					break;
					case "lat":
					
					if(attVal.split("-").length==3)
					{
						String[] attValue = attVal.split("-");
						
						if(Integer.parseInt(attValue[0])<-90 || Integer.parseInt(attValue[0])>90)
						{
							System.out.println("Line "+tpCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue[0] + " in taxiwayParking");
							return;
						}
						
					}
					
					try
					{
					if(Float.parseFloat(tpCtx.attributeValue().getText())<-90.0 || Float.parseFloat(tpCtx.attributeValue().getText())>90.0)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": invalid " + attName + " value : " + tpCtx.attributeValue().getText());
						return;
					}
					}
					catch(NumberFormatException e)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": invalid " + attName + " value : " + tpCtx.attributeValue().getText());
						return;
					}
					
					m.put(attName, attVal);
					latlonCounter++;
					break;
					
				case "lon":
					
					if(attVal.split("-").length==3)
					{
						String[] attValue = attVal.split("-");
						
						if(Integer.parseInt(attValue[0])<-180 || Integer.parseInt(attValue[0])>180)
						{
							System.out.println("Line "+tpCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue[0]+ " in taxiwayParking");
							return;
						}
						
					}		
					
					try
					{
					if(Float.parseFloat(tpCtx.attributeValue().getText())<-180 || Float.parseFloat(tpCtx.attributeValue().getText())>180)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": invalid " + attName + " value : " + tpCtx.attributeValue().getText());
						return;
					}
					}
					catch(NumberFormatException e)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": invalid " + attName + " value : " + tpCtx.attributeValue().getText());
						return;
					}
					m.put(attName, attVal);
					latlonCounter++;
					break;
					
				case "biasX":
					
					def=false;
					
					
					//ultimo char so attvalue
					Character biasXUnits = new Character (attVal.charAt(attVal.length()-1));
					if(!biasXUnits.equals('F') && !biasXUnits.equals('M'))
					{
							System.out.println("Line "+tpCtx.getStart().getLine()+": warning: invalid biasX units in taxiwayParking. using default (M)");
							def=true;								
					}
					
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attVal.substring(0, attVal.length()-1)+"M");
						m.put(attName, value);
						biasCounter++;
					}
					else
					{
						m.put(attName, attVal);
						biasCounter++;
					}
					
					break;
				case "biasZ":
					
					def=false;
					
					
					//ultimo char so attvalue
					Character biasZUnits = new Character (attVal.charAt(attVal.length()-1));
					if(!biasZUnits.equals('F') && !biasZUnits.equals('M'))
					{
							System.out.println("Line "+tpCtx.getStart().getLine()+": warning: invalid biasZ units in taxiwayParking. using default (M)");
							def=true;								
					}
					
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attVal.substring(0, attVal.length()-1)+"M");
						m.put(attName, value);
						biasCounter++;
					}
					else
					{
						m.put(attName, attVal);
						biasCounter++;
					}
					
					break;
				case "heading"://req
							
					if(Float.parseFloat(attVal)<0 || Float.parseFloat(attVal)>360)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": invalid " + attName + " value : " + attVal + " in taxywayParking");
						return;
					}else{
						m.put(attName, attVal);
						requiredCounter++;
					}

					break;
				case "radius":
					
					if(attVal.endsWith("M"))
						
					{
						attVal=attVal.substring(0, attVal.length()-1);
					}
					
					if(Float.parseFloat(attVal)<0 || Float.parseFloat(attVal)>360)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": invalid " + attName + " value : " + attVal + " in taxywayParking");
						return;
					}else{
						m.put(attName, attVal);
						requiredCounter++;
					}
					break;
				case "type":
					
					if(!taxiwayParkingTypeValues.contains(attVal))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong type value in taxywayParking. must be one of "+ taxiwayParkingTypeValues);
						return;
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					break;
				case "name":
					
					if(!taxiwayParkingNameValues.contains(attVal))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong name value in taxywayParking. must be one of "+ taxiwayParkingNameValues);
						return;
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					break;
				case "number":
					
					val= Integer.parseInt(attVal);
					
					if(val<0 || val>3999)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong attribute value for number in taxiwayParking. Used " +val+ " must be between 0 and 3999.");
						return;
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					break;
				case "airplaneCodes":
					
					int validCounter=0;
					String[] attValue= attVal.split(" ,");
					for(String s : attValue)
					{
						if (s.length()!=3)
						{
							System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong code size for airplaneCodes. each element cointains 3 capital chars.");
						}
						else
						{
							validCounter++;
						}
					}
					
					if(validCounter!=attValue.length)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Warning: not using airplaneCodes in taxyparking");
					}
					else
					{
						m.put(attName, attVal);
						
					}
					break;
				case "pushBack":
					
					if(!attVal.equals("NONE") && !attVal.equals("BOTH"))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for pushback in taxiwayParking. Used: " + attVal + "acepts NONE or BOTH");
						return;
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					break;
				case "teeOffset1":
					
					if(Float.parseFloat(attVal)<0.1 || Float.parseFloat(attVal)>50.0)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for "+ attName+ ". used: " + attVal+". Must be between 0.1 and 50.0");
					}
					else
					{
						m.put(attName, attVal);
					}
					
					break;
				case "teeOffset2":
					if(Float.parseFloat(attVal)<0.1 || Float.parseFloat(attVal)>50.0)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for "+ attName+ ". used: " + attVal+". Must be between 0.1 and 50.0");
					}
					else
					{
						m.put(attName, attVal);
					}
					break;
				case "teeOffset3":
					if(Float.parseFloat(attVal)<0.1 || Float.parseFloat(attVal)>50.0)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for "+ attName+ ". used: " + attVal+". Must be between 0.1 and 50.0");
					}
					else
					{
						m.put(attName, attVal);
					}
					break;
				case "teeOffset4":
					if(Float.parseFloat(attVal)<0.1 || Float.parseFloat(attVal)>50.0)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for "+ attName+ ". used: " + attVal+". Must be between 0.1 and 50.0");
					}
					else
					{
						m.put(attName, attVal);
					}
					break;
				default:
					break;
				}
			}
		}
		
		if(((latlonCounter != 2) && (biasCounter != 2)) ||
				((latlonCounter == 2) && (biasCounter != 0))||
				((biasCounter == 2) && (latlonCounter!= 0)))
		{
			System.out.println("Wrong combination of attributes in taxywayParking. Use only lat/lon or biasX/biasZ");
			return;
		}
		
		if(requiredCounter!=7)
		{
			System.out.println("Wrong Number of required Arguments in taxiwayParking.");
		}
		else
		{
			taxiwayParkingAtts.put("TAXIWAYPARKING"+actualTaxiwayParkingIndex, m);
			airportElems.put("TAXIWAYPARKING"+actualTaxiwayParkingIndex, "AIRPORT"+actualAirportIndex);
		}
	}
	
	@Override public void enterTaxiNameElement(@NotNull XMLParser.TaxiNameElementContext ctx) { 
		
		/*
		 * taxiname attributes
		 * index+ int 0<=index<=255
		 * name+ string 8 (can be null)
		 */
		actualTaxiNameIndex++;
	}

	@Override public void exitTaxiNameElement(@NotNull XMLParser.TaxiNameElementContext ctx) { 
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		int requiredCounter = 0;
		for(XMLParser.AttributeContext tnCtx : ctx.attribute())
		{
			String attName=tnCtx.attributeName().getText();
			String attVal=tnCtx.attributeValue().getText();
			//validar nome de atributo e verificar ordem
			if(taxiNameAttNames.contains(attName))
			{

				switch (attName) {	

				case "index":
					
					 int val= Integer.parseInt(attVal);
					
					if(val<0 || val>255)
					{
						System.out.println("Line "+tnCtx.getStart().getLine()+": Wrong attribute value for index in taxiName. Used " +val+ " must be between 0 and 255.");
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					break;

				case "name":
					if(attVal.length()>8)
					{
						System.out.println("Line "+tnCtx.getStart().getLine()+": taxiName name value too long: " + attVal + ". Max 8 Chars!");
						return;
					}
					m.put(attName, attVal);
					requiredCounter++;
					break;

				default:
					break;
				}
			}else
			{
				System.out.println("Line "+tnCtx.getStart().getLine()+": Wrong attribute name. used: "+ attName);
				return;
			}
		}
		if(requiredCounter != 2){
			System.out.println("Wrong number of required arguments. Use " + taxiNameAttNames.get(0)+ " and " + taxiNameAttNames.get(1));
			return;
		}
		else
		{
			taxiNameAtts.put("TAXINAME"+actualTaxiNameIndex, m);
			airportElems.put("TAXINAME"+actualTaxiNameIndex, "AIRPORT"+actualAirportIndex);
		}

		
	}
	
	@Override public void enterTaxiwayPathElement(@NotNull XMLParser.TaxiwayPathElementContext ctx) 
	{ 
		actualTaxiwayPathindex++;
	}

	@Override public void exitTaxiwayPathElement(@NotNull XMLParser.TaxiwayPathElementContext ctx) 
	{ 
		int requiredCounter = 0;
		boolean def=false;
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext tpCtx : ctx.attribute())
		{

			String attName=tpCtx.attributeName().getText();

			//validar nome de atributo
			if(taxiwayPathNames.contains(attName))
			{
				String attVal = tpCtx.attributeValue().getText();
				switch (attName) {
				case "type":
					
					if(!taxiwayPathTypeValues.contains(attVal)){
						
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong taxiwayPath type: " + attVal + ". Expected: " + taxiwayPathTypeValues);
						return;
						
					}else{
						m.put(attName, attVal);
						requiredCounter++;
					}
					break;
				case "start":
					
					try
					{
						Integer.parseInt(attVal);
					} 
					catch(NumberFormatException e) 
					{ 
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong start value at taxyway path element");
				        return; 
				    } 
					catch(NullPointerException e) 
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong start value at taxyway path element");
				        return;
					}
					
					m.put(attName, attVal);
					requiredCounter++;

					break;
				case "end":	
					
					try
					{
						Integer.parseInt(attVal);
					} 
					catch(NumberFormatException e) 
					{ 
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong end value at taxyway path element");
				        return; 
				    } 
					catch(NullPointerException e) 
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong end value at taxyway path element");
				        return;
					}
					
					m.put(attName, attVal);
					requiredCounter++;
					break;
				
				case "width":
					 def = false;
					
					//ultimo char so attvalue
					Character widthUnits = new Character (attVal.charAt(attVal.length()-1));
					if(!widthUnits.equals('F') && !widthUnits.equals('M'))
					{
							System.out.println("Line "+tpCtx.getStart().getLine()+": warning: invalid width units in taxiwayPath. using default (M)");
							def=true;								
					}
					
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attVal.substring(0, attVal.length()-1)+"M");
						m.put(attName, value);
						requiredCounter++;
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					break;
				case "weightLimit":	
					 def = false;
									
					try
					{
						Float.parseFloat(attVal);
					}
					catch(NumberFormatException e)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong taxiwayPath weightLimit: " + attVal + ". Expected: floating point value" );
						return;
					}
					
						m.put(attName, attVal);
						requiredCounter++;
					
					break;
					case "surface":
						if(!taxiwayPathSurfaceValues.contains(attVal))
						{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong taxiwayPath surface: " + attVal + ". Expected: " + taxiwayPathSurfaceValues);
						return;
						}
						else
						{
						m.put(attName, attVal);
						requiredCounter++;
					}
					break;
				case "drawSurface":	
					
					if(!attVal.equals("TRUE") && !attVal.equals("FALSE"))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for attribute "+attName +"in taxiwayPath. used: "+  attVal+ ". must be TRUE or FALSE");
						return;
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					break;
				case "drawDetail":	
					
					if(!attVal.equals("TRUE") && !attVal.equals("FALSE"))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for attribute "+attName +"in taxiwayPath. used: "+  attVal+ ". must be TRUE or FALSE");
						return;
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					break;
				case "centerLine":

					if(!attVal.equals("TRUE") && !attVal.equals("FALSE"))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for attribute "+attName +"in taxiwayPath. used: "+  attVal+ ". must be TRUE or FALSE. defaulting to FALSE");
						m.put(attName, "FALSE");
						
					}
					else
					{
						m.put(attName, attVal);
					}
					break;
				case "centerLineLighted":
					if(!attVal.equals("TRUE") && !attVal.equals("FALSE"))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for attribute "+attName +"in taxiwayPath. used: "+  attVal+ ". must be TRUE or FALSE. defaulting to FALSE");
						m.put(attName, "FALSE");
						
					}
					else
					{
						m.put(attName, attVal);
					}
					break;
				case "leftEdge":
					
					if(!attVal.equals("NONE") && !attVal.equals("SOLID") &&
							!attVal.equals("DASHED") && !attVal.equals("SOLID_DASHED"))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for attribute "+attName +"in taxiwayPath. used: "+  attVal+ ". must one of NONE, SOLID, DASHED or SOLID_DASHED. defaulting to NONE");
						m.put(attName, "NONE");
						
					}
					else
					{
						m.put(attName, attVal);
					}
					break;
				case "leftEdgeLighted":
					if(!attVal.equals("TRUE") && !attVal.equals("FALSE"))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for attribute "+attName +"in taxiwayPath. used: "+  attVal+ ". must be TRUE or FALSE. defaulting to FALSE");
						m.put(attName, "FALSE");
						
					}
					else
					{
						m.put(attName, attVal);
					}
					break;
				case "rightEdge":
					if(!attVal.equals("NONE") && !attVal.equals("SOLID") &&
							!attVal.equals("DASHED") && !attVal.equals("SOLID_DASHED"))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for attribute "+attName +"in taxiwayPath. used: "+  attVal+ ". must one of NONE, SOLID, DASHED or SOLID_DASHED. defaulting to NONE");
						m.put(attName, "NONE");
						
					}
					else
					{
						m.put(attName, attVal);
					}
					break;
				case "rightEdgeLighted":
					if(!attVal.equals("TRUE") && !attVal.equals("FALSE"))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for attribute "+attName +"in taxiwayPath. used: "+  attVal+ ". must be TRUE or FALSE. defaulting to FALSE");
						m.put(attName, "FALSE");
						
					}
					else
					{
						m.put(attName, attVal);
					}
					break;
				case "number":	
					
					String[] numberOptions = {"EAST","NORTH","NORTHEAST","NORTHWEST","SOUTH","SOUTHEAST","SOUTHWEST","WEST"};


					if(!Arrays.asList(numberOptions).contains(tpCtx.attributeValue().getText()) &&
							(Integer.parseInt(tpCtx.attributeValue().getText()) < 00 || Integer.parseInt(tpCtx.attributeValue().getText()) > 36)){
						
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong taxywayPath number: " + tpCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(numberOptions) + ",or a int from 00 to 36");
						return;
						
					}else{
						m.put(tpCtx.attributeName().getText(), tpCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;
				case "designator":
					String[] designatorOptions = {"NONE","C","CENTER","L","LEFT","R","RIGHT","W","WATER","A","B"};
					if(!Arrays.asList(designatorOptions).contains(tpCtx.attributeValue().getText())){
						
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong taxywayPath designator. used: " + tpCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(designatorOptions));
						return;
						
					}else{
						m.put(tpCtx.attributeName().getText(), tpCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;
					
				case "name":
					
					if(Integer.parseInt(attVal)<0 || Integer.parseInt(attVal)>255)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong taxywayPath name value. used: " + tpCtx.attributeValue().getText() + ". Expected: between 0 and 255");
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					
					break;
					default:
						break;
				}
			}
		}
		
		if(requiredCounter!=11)
		{
			System.out.println("counter manhoso:" + requiredCounter);
			System.out.println("Wrong taxywayPath required arguments. Expected: " + taxiwayPathNames);
		}
		else
		{
			taxiwayPathAtts.put("TAXIWAYPATH"+actualTaxiwayPathindex, m);
			airportElems.put("TAXIWAYPATH"+actualTaxiwayPathindex, "AIRPORT"+actualAirportIndex);
		}
	}
}
