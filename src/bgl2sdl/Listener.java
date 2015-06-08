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
	Map<String, Map<String, String>> helipadAtts = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> jetwayAtts = new HashMap<String, Map<String, String>>();

	
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
	Vector<String> helipadSurfaceValues;
	Vector<String> jetwayNames;
	Vector<String> runwayAliasNames;
	
	
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
	Vector<String> helipadNames;
	Vector<String> helipadRequired;
	Vector<String> jetwayRequired;
	Vector<String> runwayAliasRequired;
	
	
	
	
	
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
	int actualHelipadindex=0;
	int actualJetwayindex=0;
	int actualRunwayAliasindex=0;
	
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
		
		ilsRequired = new Vector<String>();
		ilsRequired.add("lat");
		ilsRequired.add("lon");
		ilsRequired.add("alt");
		ilsRequired.add("heading");
		ilsRequired.add("frequency");
		ilsRequired.add("end");
		ilsRequired.add("magvar");
		ilsRequired.add("ident");
		
		
		glideSlopeAttNames = new Vector<String>();
		glideSlopeAttNames.add("lat");
		glideSlopeAttNames.add("lon");
		glideSlopeAttNames.add("alt");
		glideSlopeAttNames.add("pitch");
		glideSlopeAttNames.add("range");
		
		glideSlopeRequired = new Vector<String>();
		glideSlopeRequired.add("lat");
		glideSlopeRequired.add("lon");
		glideSlopeRequired.add("alt");
		glideSlopeRequired.add("pitch");
		glideSlopeRequired.add("range");
		
		runwayStartAttNames= new Vector<String>();
		runwayStartAttNames.add("type");
		runwayStartAttNames.add("lat");
		runwayStartAttNames.add("lon");
		runwayStartAttNames.add("alt");
		runwayStartAttNames.add("heading");
		runwayStartAttNames.add("end");
		
		runwayStartRequired= new Vector<String>();
		runwayStartRequired.add("lat");
		runwayStartRequired.add("lon");
		runwayStartRequired.add("alt");
		runwayStartRequired.add("heading");

		
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
		
		overrunRequired= new Vector<String>();
		overrunRequired.add("end");
		overrunRequired.add("length");
		
		taxiwayPointAttNames= new Vector<String>();
		taxiwayPointAttNames.add("index");
		taxiwayPointAttNames.add("type");
		taxiwayPointAttNames.add("orientation");
		taxiwayPointAttNames.add("lat");
		taxiwayPointAttNames.add("lon");
		taxiwayPointAttNames.add("biasX");
		taxiwayPointAttNames.add("biasZ");
		
		taxiwayPointRequired= new Vector<String>();
		taxiwayPointRequired.add("index");
		taxiwayPointRequired.add("type");
		taxiwayPointRequired.add("lat");
		taxiwayPointRequired.add("lon");
		taxiwayPointRequired.add("biasX");
		taxiwayPointRequired.add("biasZ");
		
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
		
		taxiwayParkingRequired= new Vector<String>();
		taxiwayParkingRequired.add("index");
		taxiwayParkingRequired.add("lat");
		taxiwayParkingRequired.add("lon");
		taxiwayParkingRequired.add("biasX");
		taxiwayParkingRequired.add("biasZ");
		taxiwayParkingRequired.add("heading");
		taxiwayParkingRequired.add("radius");
		taxiwayParkingRequired.add("type");
		taxiwayParkingRequired.add("name");
		taxiwayParkingRequired.add("number");
		taxiwayParkingRequired.add("pushBack");
		taxiwayParkingRequired.add("teeOffset1");
		taxiwayParkingRequired.add("teeOffset2");
		taxiwayParkingRequired.add("teeOffset3");
		taxiwayParkingRequired.add("teeOffset4");
		
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
		
		taxiwayPathNames = new Vector<String>();
		taxiwayPathNames.add("type");
		taxiwayPathNames.add("start");
		taxiwayPathNames.add("end");
		taxiwayPathNames.add("width");
		taxiwayPathNames.add("weightLimit");
		taxiwayPathNames.add("surface");
		taxiwayPathNames.add("drawSurface");
		taxiwayPathNames.add("drawDetail");
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

		helipadNames = new Vector<String>();
		helipadNames.add("lat");
		helipadNames.add("lon");
		helipadNames.add("alt");
		helipadNames.add("surface");
		helipadNames.add("heading");
		helipadNames.add("lenght");
		helipadNames.add("width");
		helipadNames.add("type");
		helipadNames.add("closed");
		helipadNames.add("transparent");
		helipadNames.add("red");
		helipadNames.add("green");
		helipadNames.add("blue");
		
		helipadRequired = new Vector<String>();
		helipadRequired.add("lat");
		helipadRequired.add("lon");
		helipadRequired.add("alt");
		helipadRequired.add("surface");
		helipadRequired.add("heading");
		helipadRequired.add("lenght");
		helipadRequired.add("width");
		helipadRequired.add("type");
		
		jetwayNames = new Vector<String>();
		jetwayNames.add("gateName");
		jetwayNames.add("parkingNumber");
		
		jetwayRequired = new Vector<String>();
		jetwayRequired.add("gateName");
		jetwayRequired.add("parkingNumber");
		
		runwayAliasNames = new Vector<String>();
		runwayAliasNames.add("number");
		runwayAliasNames.add("designator");
		
		runwayAliasRequired = new Vector<String>();
		runwayAliasRequired.add("number");
		runwayAliasRequired.add("designator");
		
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
			String attVal=ilsCtx.attributeValue().getText();

			//validar nome de atributo
			if(ilsAttNames.contains(attName))
			{

				switch (attName) {
				
				case "lat":
					
					if(AttributesChecker.lat(ilsCtx))
						m.put(attName, attVal);
					break;
					
				case "lon":
					
					if(AttributesChecker.lon(ilsCtx))
						m.put(attName, attVal);
					break;
					
				case "alt":
		
					String altString = AttributesChecker.alt(ilsCtx);
					m.put(attName, altString);
	
				break;
					
				case "heading"://req
					
					if(AttributesChecker.heading(ilsCtx))
						m.put(attName, attVal);

					break;
					
				case "frequency":
					
					if(AttributesChecker.freuquency(ilsCtx))
						m.put(attName, attVal);
										
					break;
					
				case "end":
					
					if(AttributesChecker.end(ilsCtx))
						m.put(attName, attVal);
					
					break;
					
				case "range":
					String rangeString = AttributesChecker.rangeGS(ilsCtx);
					m.put(attName, rangeString);
					
					break;//TODO cofirmar ^ (assumo que recebe sempre um numero, ou numero+N)
					
				case "magvar":
					
					if(AttributesChecker.magvar(ilsCtx))
					m.put(attName, attVal);
					
					break;
					
				case "ident":
					if(AttributesChecker.ident(ilsCtx))
						m.put(attName, attVal);
					break;
					
				case "width":
					
					if(AttributesChecker.heading(ilsCtx))
						m.put(attName, attVal);
					
					break;
					
				case "name":
					if(AttributesChecker.stringchecker(ilsCtx))
						m.put(attName, attVal);
					
					break;
					
				case "backCourse":
					
					String bcString = AttributesChecker.trueorfalse(ilsCtx);
					m.put(attName, bcString);
					
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
		for (String s: ilsRequired)
        {
          if(!m.containsKey(s))
          {
        	  System.out.println("The required attribute "+s+" in element ils not found");
        	  return;
          }
        }

		ilsAtts.put("ILS"+actualIlsIndex, m);
		airportElems.put("ILS"+actualIlsIndex, "AIRPORT"+actualAirportIndex);

	}

	
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
		
	
		int latlonCounter = 0;
		int biasCounter = 0;
	

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
					
					if(AttributesChecker.index(twpCtx))
					m.put(attName, attVal);
					
					break;
					
				case "type":
					
					if(AttributesChecker.typeTWP(twpCtx))
					m.put(attName, attVal);
					
					break;
					
				case "orientation":
					
					if(AttributesChecker.orientation(twpCtx))
					m.put(attName, attVal);
					
					break;
					
				case "lat":
				
					if(AttributesChecker.lat(twpCtx))
					{
					m.put(attName, attVal);
					latlonCounter++;
					}
					
					break;
					
				case "lon":
					
					if(AttributesChecker.lon(twpCtx))
					{
					m.put(attName, attVal);
					latlonCounter++;
					}
					break;
					
				case "biasX":

					String biasxString = AttributesChecker.alt(twpCtx);
					m.put(attName, biasxString);
					biasCounter++;
					
					break;
				case "biasZ":
					
					String biaszString = AttributesChecker.alt(twpCtx);
					m.put(attName, biaszString);
					biasCounter++;
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
		
		
		for (String s: taxiwayPointRequired)
        {
          if(!m.containsKey(s))
          {
        	  System.out.println("The required attribute "+s+" in element taxiwaypoint not found");
        	  return;
          }
        }
	
	
			taxiwayPointAtts.put("TAIWAYPOINT"+actualTaxiwayPointIndex, m);
			airportElems.put("TAIWAYPOINT"+actualTaxiwayPointIndex, "AIRPORT"+actualAirportIndex);
		

	}
	
	@Override public void enterTaxiwayParkingElement(@NotNull XMLParser.TaxiwayParkingElementContext ctx) 
	{ 
		actualTaxiwayParkingIndex++;
	}
	
	@Override public void exitTaxiwayParkingElement(@NotNull XMLParser.TaxiwayParkingElementContext ctx) 
	{ 
		
		int latlonCounter = 0;
		int biasCounter = 0;
	
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
					
					if(AttributesChecker.index(tpCtx))
						m.put(attName, attVal);
					 
					break;
					case "lat":
					
					if(AttributesChecker.lat(tpCtx))
					{
						m.put(attName, attVal);
						latlonCounter++;
					}
					
					
					break;
					
				case "lon":
					
					if(AttributesChecker.lon(tpCtx))
					{
						m.put(attName, attVal);
						latlonCounter++;
					}
					
					break;
					
				case "biasX":
					
						String biasxString= AttributesChecker.alt(tpCtx);
						m.put(attName, biasxString);
						biasCounter++;
					
					
					break;
				case "biasZ":
					
					String biaszString= AttributesChecker.alt(tpCtx);
					m.put(attName, biaszString);
					biasCounter++;
					
					break;
				case "heading"://req
							
					if(AttributesChecker.heading(tpCtx))
						m.put(attName, attVal);
					

					break;
				case "radius":
					
					if(AttributesChecker.radius(tpCtx))
					{
						m.put(attName, attVal);
						
					}
					
					
					break;
				case "type":
					
					if(AttributesChecker.typeTWPA(tpCtx, taxiwayParkingTypeValues))
					m.put(attName, attVal);
					
					break;
				case "name":
					
					if(AttributesChecker.typeTWPA(tpCtx, taxiwayParkingNameValues))
					m.put(attName, attVal);
					
					break;
				case "number":
					
					if(AttributesChecker.index(tpCtx))
					m.put(attName, attVal);
						
					
					break;
				case "airplaneCodes":
					
					if(AttributesChecker.airlineCodes(tpCtx))
						m.put(attName, attVal);
					break;
				case "pushBack":
					
					if(AttributesChecker.pushback(tpCtx))
					m.put(attName, attVal);
					
					break;
				case "teeOffset1":
					
					if(AttributesChecker.offset(tpCtx))
						m.put(attName, attVal);
					
					break;
				case "teeOffset2":
					
					if(AttributesChecker.offset(tpCtx))
						m.put(attName, attVal);
					
					break;
				case "teeOffset3":
					
					if(AttributesChecker.offset(tpCtx))
						m.put(attName, attVal);
					
					break;
				case "teeOffset4":
					
					if(AttributesChecker.offset(tpCtx))
						m.put(attName, attVal);
					
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
		
		
		for (String s: taxiwayParkingRequired)
        {
          if(!m.containsKey(s))
          {
        	  System.out.println("The required attribute "+s+" in element taxiwayparking not found");
        	  return;
          }
        }
	
		
			taxiwayParkingAtts.put("TAXIWAYPARKING"+actualTaxiwayParkingIndex, m);
			airportElems.put("TAXIWAYPARKING"+actualTaxiwayParkingIndex, "AIRPORT"+actualAirportIndex);
		
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
		
		for(XMLParser.AttributeContext tnCtx : ctx.attribute())
		{
			String attName=tnCtx.attributeName().getText();
			String attVal=tnCtx.attributeValue().getText();
			//validar nome de atributo e verificar ordem
			if(taxiNameAttNames.contains(attName))
			{

				switch (attName) {	

				case "index":
					
					if(AttributesChecker.indexTN(tnCtx))
						m.put(attName, attVal);
					break;

				case "name":
					if(AttributesChecker.nameTN(tnCtx))
						m.put(attName, attVal);
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
		for (String s: taxiNameRequired)
        {
          if(!m.containsKey(s))
          {
        	  System.out.println("The required attribute "+s+" in element taxiname not found");
        	  return;
          }
        }
		
			taxiNameAtts.put("TAXINAME"+actualTaxiNameIndex, m);
			airportElems.put("TAXINAME"+actualTaxiNameIndex, "AIRPORT"+actualAirportIndex);
		

		
	}
	
	@Override public void enterTaxiwayPathElement(@NotNull XMLParser.TaxiwayPathElementContext ctx) 
	{ 
		actualTaxiwayPathindex++;
	}

	@Override public void exitTaxiwayPathElement(@NotNull XMLParser.TaxiwayPathElementContext ctx) 
	{ 
		
		
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
					
					if(AttributesChecker.typeTP(tpCtx, taxiwayPathTypeValues))
						m.put(attName, attVal);
					break;
				case "start":
					
					if(AttributesChecker.startend(tpCtx))
						m.put(attName, attVal);

					break;
				case "end":	
					
					if(AttributesChecker.startend(tpCtx))
						m.put(attName, attVal);
					break;
				
				case "width":
					
					String widthString = AttributesChecker.alt(tpCtx);
					m.put(attName, attVal);
					
					break;
				case "weightLimit":	
					
					if(AttributesChecker.isfloat(tpCtx))
						m.put(attName, attVal);
					
					break;
					case "surface":
						if(AttributesChecker.surfaceTP(tpCtx, taxiwayPathSurfaceValues))
							m.put(attName, attVal);
							
					
					break;
				case "drawSurface":	
					
					String dsString = AttributesChecker.trueorfalse(tpCtx);
					m.put(attName, dsString);
					
					
					break;
				case "drawDetail":	
					
					String ddString = AttributesChecker.trueorfalse(tpCtx);
					m.put(attName, ddString);
					
					break;
				case "centerLine":

					String clString = AttributesChecker.trueorfalse(tpCtx);
					m.put(attName, clString);
					
					break;
				case "centerLineLighted":
					String cllString = AttributesChecker.trueorfalse(tpCtx);
					m.put(attName, cllString);
					break;
				case "leftEdge":
					
					String leString = AttributesChecker.rightedge(tpCtx);
					m.put(attName, leString);
					
					break;
				case "leftEdgeLighted":
					String lelString = AttributesChecker.trueorfalse(tpCtx);
					m.put(attName, lelString);
					break;
				case "rightEdge":
					
					String reString = AttributesChecker.rightedge(tpCtx);
					m.put(attName, reString);
					
					break;
				case "rightEdgeLighted":
					String relString = AttributesChecker.trueorfalse(tpCtx);
					m.put(attName, relString);
					break;
				case "number":	
					
					if(AttributesChecker.number(tpCtx))
						m.put(attName, attVal);
					break;
				case "designator":
					if(AttributesChecker.number(tpCtx))
					m.put(attName, attVal);
					break;
					
				case "name":
					
					if(AttributesChecker.indexTN(tpCtx))
						m.put(attName, attVal);
					
					break;
					default:
						break;
				}
			}
		}
		
		for (String s: taxiwayPathRequired)
        {
          if(!m.containsKey(s))
          {
        	  System.out.println("The required attribute "+s+" in element taxiwaypath not found");
        	  return;
          }
        }
		
			taxiwayPathAtts.put("TAXIWAYPATH"+actualTaxiwayPathindex, m);
			airportElems.put("TAXIWAYPATH"+actualTaxiwayPathindex, "AIRPORT"+actualAirportIndex);
		
	}

	@Override public void enterHelipadElement(@NotNull XMLParser.HelipadElementContext ctx) 
	{ 
		actualHelipadindex++;
	}
	
	@Override public void exitHelipadElement(@NotNull XMLParser.HelipadElementContext ctx) 
	{
		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext hCtx : ctx.attribute())
		{

			String attName=hCtx.attributeName().getText();

			//validar nome de atributo
			if(helipadNames.contains(attName))
			{
				String attVal = hCtx.attributeValue().getText();
				switch (attName) {
				case "lat":
					
					if(AttributesChecker.lat(hCtx))
						m.put(attName, attVal);
					break;
				case "lon":
					
					if(AttributesChecker.lon(hCtx))
						m.put(attName, attVal);

					break;
				case "alt":	
					
					String altString= AttributesChecker.alt(hCtx);
						m.put(attName, altString);
					break;
				
				case "surface":
					// os mesmos de taxywaypathvalues
					if(AttributesChecker.surfaceTP(hCtx, taxiwayPathSurfaceValues))
						m.put(attName, attVal);
					
					break;
				case "heading":	
					
					if(AttributesChecker.heading(hCtx))
					m.put(attName, attVal);
				
					break;
				case "lenght":	
					
					String lenghtString= AttributesChecker.alt(hCtx);
					m.put(attName, lenghtString);
					
					
					break;
				case "width":	
					
					String widthString = AttributesChecker.trueorfalse(hCtx);
					m.put(attName, widthString);
					
					break;
				case "type":

					if(AttributesChecker.typeHL(hCtx))
						m.put(attName, attVal);
					
					break;
				case "closed":
					String clsString = AttributesChecker.trueorfalse(hCtx);
					m.put(attName, clsString);
					break;
				case "transparent":
					
					String trpString = AttributesChecker.trueorfalse(hCtx);
					m.put(attName, trpString);
				
					break;
					
				case "red":
					//mesmo de indexTN
					
					if(AttributesChecker.indexTN(hCtx))
						m.put(attName, attVal);
					
					break;
				case "green":
					//mesmo de indexTN
					
					if(AttributesChecker.indexTN(hCtx))
						m.put(attName, attVal);
					break;
				case "blue":	
					//mesmo de indexTN
					
					if(AttributesChecker.indexTN(hCtx))
						m.put(attName, attVal);
				
					default:
						break;
				}
			}
	}

	for (String s: helipadRequired)
    {
      if(!m.containsKey(s))
      {
    	  System.out.println("The required attribute "+s+" in element helipad not found");
    	  return;
      }
    }
	
		helipadAtts.put("HELIPAD"+actualHelipadindex, m);
		airportElems.put("HELIPAD"+actualHelipadindex, "AIRPORT"+actualAirportIndex);
	}

	

@Override public void enterJetwayElement(@NotNull XMLParser.JetwayElementContext ctx) 
{ 
	actualJetwayindex++;
}

@Override public void exitJetwayElement(@NotNull XMLParser.JetwayElementContext ctx)  
{
	Map<String, String> m = new LinkedHashMap<String, String>();
	for(XMLParser.AttributeContext jCtx : ctx.attribute())
	{

		String attName=jCtx.attributeName().getText();

		//validar nome de atributo
		if(jetwayNames.contains(attName))
		{
			String attVal = jCtx.attributeValue().getText();
			switch (attName) {
			case "gateName":
				// o mesmo de taxiwatparkingtypevalues
				if(AttributesChecker.typeTWPA(jCtx, taxiwayParkingTypeValues))
					m.put(attName, attVal);
				break;
			case "parkingNumber":
				
				String pnString = AttributesChecker.integerchecker(jCtx);
					m.put(attName, pnString);

				break;
			
				default:
					break;
			}
		}
}

for (String s: jetwayRequired)
{
  if(!m.containsKey(s))
  {
	  System.out.println("The required attribute "+s+" in element jetway not found");
	  return;
  }
}

	jetwayAtts.put("HELIPAD"+actualJetwayindex, m);
	airportElems.put("HELIPAD"+actualJetwayindex, "AIRPORT"+actualAirportIndex);
}

@Override public void enterRunwayAliasElement(@NotNull XMLParser.RunwayAliasElementContext ctx) 
{ 
	actualRunwayAliasindex++;
}

@Override public void exitRunwayAliasElement(@NotNull XMLParser.RunwayAliasElementContext ctx) 
{
	Map<String, String> m = new LinkedHashMap<String, String>();
	for(XMLParser.AttributeContext rwaCtx : ctx.attribute())
	{

		String attName=rwaCtx.attributeName().getText();

		//validar nome de atributo
		if(jetwayNames.contains(attName))
		{
			String attVal = rwaCtx.attributeValue().getText();
			switch (attName) {
			case "number":
				
				if(AttributesChecker.number(rwaCtx))
					m.put(attName, attVal);
				break;
			case "designator":
				
				if(AttributesChecker.designator(rwaCtx))
					m.put(attName, attVal);

				break;
			
				default:
					break;
			}
		}
}

for (String s: runwayAliasRequired)
{
  if(!m.containsKey(s))
  {
	  System.out.println("The required attribute "+s+" in element runwayalias not found");
	  return;
  }
}

jetwayAtts.put("RUNWAYALIAS"+actualRunwayAliasindex, m);
airportElems.put("RUNWAYALIAS"+actualRunwayAliasindex, "AIRPORT"+actualAirportIndex);
}
}
