package bgl2sdl;

import gen.XMLParser;
import gen.XMLParserBaseListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;

import org.antlr.v4.runtime.misc.NotNull;

//para confirmar campos:
//https://msdn.microsoft.com/en-us/library/cc526978.aspx

//TODO ver se recebe efectivamente floats em vez de strings em lat/lon/alt
//TODO confirmar se nos required, todas as keys sao diferentes (nao haver atributos dups)

public class Listener extends XMLParserBaseListener {
	public boolean parsingOK;
	XMLParser parser;
	AttributeChecker semanticChecker;
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
	Map<String, Map<String, String>> runwayAliasPathAtts = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> helipadAtts = new HashMap<String, Map<String, String>>();

	
	Map<String, Integer> airportAttNames; // para verificar se os nomes dos atributos sao corretos
	Map<String, Integer> fuelAttNames;
	Map<String, Integer> towerAttNames;
	Map<String, Integer> runwayAttNames;
	Vector<String> markingsAttNames;
	Vector<String> lightsAttNames;
	Vector<String> offsetThresholdAttNames;
	Vector<String> approachLighstsAttNames;
	Vector<String> vasiAttNames;
	Vector<String> vasiTypeValues;
	Vector<String> ilsAttNames;
	Vector<String> glideSlopeAttNames;
	Vector<String> runwayStartAttNames;
	Vector<String> runwayAliasAttNames;
	Vector<String> blastPadAttNames;
	Vector<String> overrunAttNames;
	Vector<String> taxiwayPointAttNames;
	Vector<String> taxiwayParkingAttNames;
	Vector<String> helipadAttNames;
	Vector<String> taxiwayParkingTypeValues;
	Vector<String> taxiwayParkingNameValues;
	Vector<String> taxiNameAttNames;
	Vector<String> taxiwayPathNames;
	Vector<String> taxiwayPathTypeValues;
	Vector<String> taxiwayPathSurfaceValues;
	Vector<String> trueOrFalse;
	Vector<String> primaryOrSecondary;
	Vector<String> leftOrRight;
	
	
	
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
	int actualHelipadIndex=0;
	int actualRunwayAliasIndex = 0;
	
	public Listener(XMLParser parser)
	{
		this.parsingOK=true;
		this.parser= parser;
		this.semanticChecker = new AttributeChecker();
		
		airportAttNames= new HashMap<String, Integer>();
		airportAttNames.put("region", 1);
		airportAttNames.put("country", 2);
		airportAttNames.put("state", 3);
		airportAttNames.put("city", 4);
		airportAttNames.put("name", 5);
		airportAttNames.put("lat", 6);
		airportAttNames.put("lon", 7);
		airportAttNames.put("alt", 8);
		airportAttNames.put("magvar", 9);
		airportAttNames.put("ident", 10);
		airportAttNames.put("airportTestRadius", 11);
		airportAttNames.put("trafficScalar", 12);
		
		fuelAttNames= new HashMap<String, Integer>();
		fuelAttNames.put("type", 1);
		fuelAttNames.put("availability", 2);
		
		towerAttNames= new HashMap<String, Integer>();
		towerAttNames.put("lat", 1);
		towerAttNames.put("lon", 2);
		towerAttNames.put("alt", 3);
		
		runwayAttNames= new HashMap<String, Integer>();
		runwayAttNames.put("lat", 1);
		runwayAttNames.put("lon", 2);
		runwayAttNames.put("alt", 3);
		runwayAttNames.put("surface", 4);
		runwayAttNames.put("heading", 5);
		runwayAttNames.put("length", 6);
		runwayAttNames.put("width", 7);
		runwayAttNames.put("number", 8);
		runwayAttNames.put("designator", 9);
		runwayAttNames.put("primaryDesignator", 10);
		runwayAttNames.put("secondaryDesignator", 11);
		runwayAttNames.put("patternAltitude", 12);
		runwayAttNames.put("primaryTakeoff", 13);
		runwayAttNames.put("primaryLanding", 14);
		runwayAttNames.put("primaryPattern", 15);
		runwayAttNames.put("secondaryTakeoff", 16);
		runwayAttNames.put("secondaryLanding", 17);
		runwayAttNames.put("secondaryPattern", 18);
		
		runwayAliasAttNames= new Vector<String>();
		runwayAliasAttNames.add("number");
		runwayAliasAttNames.add("designator");
		
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
		
		lightsAttNames = new Vector<String>();
		lightsAttNames.add("center");
		lightsAttNames.add("edge");
		lightsAttNames.add("centerRed");
		
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
		
		vasiAttNames = new Vector<String>();
		vasiAttNames.add("end");
		vasiAttNames.add("type");
		vasiAttNames.add("side");
		vasiAttNames.add("biasX");
		vasiAttNames.add("biasZ");
		vasiAttNames.add("spacing");
		vasiAttNames.add("pitch");
		
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
		
		helipadAttNames= new Vector<String>();
		helipadAttNames.add("lat");
		helipadAttNames.add("lon");
		helipadAttNames.add("alt");
		helipadAttNames.add("surface");
		helipadAttNames.add("heading");
		helipadAttNames.add("length");
		helipadAttNames.add("with");
		helipadAttNames.add("type");
		helipadAttNames.add("closed");
		helipadAttNames.add("transparent");
		helipadAttNames.add("red");
		helipadAttNames.add("green");
		helipadAttNames.add("blue");
		
		
		trueOrFalse= new Vector<String>();
		trueOrFalse.add("TRUE");
		trueOrFalse.add("FALSE");
		
		primaryOrSecondary= new Vector<String>();
		primaryOrSecondary.add("PRIMARY");
		primaryOrSecondary.add("SECONDARY");
		
		leftOrRight= new Vector<String>();
		leftOrRight.add("LEFT");
		leftOrRight.add("RIGHT");
		
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
		int requiredCounter = 0;
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext aCtx : ctx.attribute())
		{
			counter++;
			
			String attName=aCtx.attributeName().getText();
			String attValue= aCtx.attributeValue().getText();
			
			//validar nome de atributo e verificar ordem
			if(airportAttNames.containsKey(attName) && airportAttNames.get(attName)>=counter)
			{
				
				switch (attName) {
				case "lat":
					if(semanticChecker.checkLatitude(attValue, aCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
					{
						parsingOK=false;
					}
					
					break;
				case "lon":
					
					if(semanticChecker.checkLongitude(attValue, aCtx))
					{
					m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					requiredCounter++;
					}
					else
					{
						parsingOK=false;
					}
					break;
					
				case "alt":
					
					boolean def = false;
					
					if(semanticChecker.checkAltitude(attValue, aCtx, def))
					{
						if(def==true)
						{
							//valor antigo com as unidades por defeito M
							String value = new String(attValue.substring(0, attValue.length()-1)+"M");
							m.put(attName, value);
							requiredCounter++;
						}
						else
						{
							m.put(attName, attValue);
							requiredCounter++;
						}
					}
					else
					{
						parsingOK=false;
					}
					
				break;
				
				case "ident":
					
					//
					if(semanticChecker.checkIdent(attValue, aCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;
				break;
					
				case "airportTestRadius":
					
					//ir buscar ultimo char
					if(semanticChecker.checkAirportTestRadius(attValue, aCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}					
					else
						parsingOK=false;
					
					break;
					
				case "trafficScalar":
					
					
					if(semanticChecker.checkTrafficScalar(attValue, aCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;
					
					break;
				default:
					
					m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					break;
				}
				
			}
			else
			{
				
				System.out.println("Line "+aCtx.getStart().getLine()+": Wrong attribute name or wrong order in airport. used: "+ aCtx.attributeName().getText());
				parsingOK=false;
				return;
			}
			
			
		}
		if(requiredCounter!=6)
		{
			System.out.println("Wrong number of required attributes. must be 6: lat, lon, alt, ident, airportTestRadius, trafficScalar");
			parsingOK=false;
			
		}
		else
		{
		airportAtts.put("AIRPORT"+actualAirportIndex,m);
		}

	}

	
	@Override public void enterServicesElement(@NotNull XMLParser.ServicesElementContext ctx) 
	{ 
		
	}
	
	@Override public void exitServicesElement(@NotNull XMLParser.ServicesElementContext ctx)
	{ 
		
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
			String attValue=fCtx.attributeValue().getText();
			
			if(fuelAttNames.containsKey(attName))
			{
				switch (attName) {
				case "type":
					String[] typeOptions = {"73","87","100","130","145","MOGAS","JET","JETA","JETA1","JETAP","JETB","JET4","JET5","UNKNOWN"};
					
						
					if(semanticChecker.checkOptions(attValue, typeOptions, fCtx))
					{
						m.put(attName, attValue);
					}
					
					break;
					
				case "availability":
					String[] availabilityOptions = {"YES","NO","UNKNOWN","PRIOR_REQUEST"};
					if(semanticChecker.checkOptions(attValue, availabilityOptions, fCtx))
					{
						m.put(attName, attValue);
					}
					break;

				default:
					System.out.println("Line "+fCtx.getStart().getLine()+": Wrong fuel attribute name. use only type or availability.");
					parsingOK=false;
					
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
		
		int requiredCounter=0;
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext tCtx : ctx.attribute())
		{
			
			
			String attName=tCtx.attributeName().getText();
			String attValue=tCtx.attributeValue().getText();
			
			//validar nome de atributo e verificar ordem
			if(towerAttNames.containsKey(attName))
			{
				switch (attName) {
				case "lat":
					
				if (semanticChecker.checkLatitude(attValue, tCtx)) {
					m.put(attName, attValue);		
					requiredCounter++;
				}
				else
					parsingOK=false;
				
					break;
					
				case "lon":
					
					if (semanticChecker.checkLongitude(attValue, tCtx)) {
						m.put(attName, attValue);		
						requiredCounter++;
					}
					else
						parsingOK=false;
					
					break;
					
				case "alt":
		
					boolean def = false;
					if(semanticChecker.checkAltitude(attValue, tCtx, def))
					{
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(tCtx.attributeValue().getText().substring(0, tCtx.attributeValue().getText().length()-1)+"M");
						m.put(attName, value);
					}
					else
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					}
					else
						parsingOK=false;
					
	
				break;
				
				default:
					
					break;
				}
				
			}
			else
			{
				
				System.out.println("Line "+tCtx.getStart().getLine()+": Wrong attribute name. used: "+ attName);
				parsingOK=false;
				
			}
			
		}
		if(requiredCounter != 3){
			System.out.println("Wrong number of required attributes. must be 3: lat, lon, alt");
			parsingOK=false;
			
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
		
		int requiredCounter = 0;
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext rCtx : ctx.attribute())
		{
			
			String attName=rCtx.attributeName().getText();
			String attValue=rCtx.attributeValue().getText();
			
			//validar nome de atributo
			if(runwayAttNames.containsKey(attName))
			{
				
				switch (attName) {
				case "lat":
					
					
					if (semanticChecker.checkLatitude(attValue, rCtx)) {
						m.put(attName, attValue);		
						requiredCounter++;
					}
					else
						parsingOK=false;
					
					break;
					
				case "lon":
					
					if(semanticChecker.checkLongitude(attValue, rCtx))
					{
					m.put(attName, attValue);
					requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "alt":
		
					boolean def = false;
					
					if(semanticChecker.checkAltitude(attValue, rCtx, def))
					{
						if(def==true)
						{
							//valor antigo com as unidades por defeito M
							String value = new String(attValue.substring(0, attValue.length()-1)+"M");
							m.put(attName, value);
							requiredCounter++;
						}
						else
						{
							m.put(attName, attValue);
							requiredCounter++;
						}
					}
					else
						parsingOK=false;
					
				break;

				case "surface"://req
					String[] surfaceOptions = {"ASPHALT","BITUMINOUS","BRICK","CLAY","CEMENT","CONCRETE","CORAL","DIRT","GRASS","GRAVEL","ICE","MACADAM","OIL_TREATED, PLANKS","SAND","SHALE","SNOW","STEEL_MATS","TARMAC","UNKNOWN","WATER"};
					if(semanticChecker.checkOptions(attValue, surfaceOptions, rCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "heading"://req
					
					if(semanticChecker.checkHeading(attValue, rCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;

					break;
					
					
				case "length"://req
					
					boolean def1 = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character lengthUnits = new Character (rCtx.attributeValue().getText().charAt(rCtx.attributeValue().getText().length()-1));
					if(!lengthUnits.equals('F') && !lengthUnits.equals('M'))
					{
							System.out.println("Line "+rCtx.getStart().getLine()+": warning: invalid alt units in runway. using default (M)");
							def1=true;								
					}
					
					if(def1==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(rCtx.attributeValue().getText().substring(0, rCtx.attributeValue().getText().length()-1)+"M");
						m.put(rCtx.attributeName().getText(), value);
						requiredCounter++;
					}
					else
					{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
						requiredCounter++;
					}
					
					break;
					
				case "width"://req
					boolean def2 = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character widthUnits = new Character (rCtx.attributeValue().getText().charAt(rCtx.attributeValue().getText().length()-1));
					if(!widthUnits.equals('F') && !widthUnits.equals('M'))
					{
							System.out.println("Line "+rCtx.getStart().getLine()+": warning: invalid alt units in runway. using default (M)");
							def2=true;								
					}
					
					if(def2==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(rCtx.attributeValue().getText().substring(0, rCtx.attributeValue().getText().length()-1)+"M");
						m.put(rCtx.attributeName().getText(), value);
						requiredCounter++;
					}
					else
					{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
						requiredCounter++;
					}
					
					break;
					
				case "number"://req
					String[] numberOptions = {"EAST","NORTH","NORTHEAST","NORTHWEST","SOUTH","SOUTHEAST","SOUTHWEST","WEST"};


					if(!Arrays.asList(numberOptions).contains(rCtx.attributeValue().getText()) &&
							(Integer.parseInt(rCtx.attributeValue().getText()) < 00 || Integer.parseInt(rCtx.attributeValue().getText()) > 36)){
						
						System.out.println("Line "+rCtx.getStart().getLine()+": Wrong runway number: " + rCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(numberOptions) + ",or a int from 00 to 36");
						parsingOK=false;
						
						
					}else{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;
					
				case "designator":
					String[] designatorOptions = {"NONE","C","CENTER","L","LEFT","R","RIGHT","W","WATER","A","B"};
					if(!Arrays.asList(designatorOptions).contains(rCtx.attributeValue().getText())){
						
							
						System.out.println("Line "+rCtx.getStart().getLine()+": Wrong runway surface: " + rCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(designatorOptions));
						parsingOK=false;
					
						
					}else{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
						m.put("secondaryDesignator", rCtx.attributeValue().getText());	//se designator estiver definido, secondary é igual
					}
					break;
					
				case "patternAltitude":
					boolean def3 = false;
					
					//ultimo char so attvalue
					Character patternAltUnits = new Character (rCtx.attributeValue().getText().charAt(rCtx.attributeValue().getText().length()-1));
					if(!patternAltUnits.equals('F') && !patternAltUnits.equals('M'))
					{
							System.out.println("Line "+rCtx.getStart().getLine()+": warning: invalid alt units in runway. using default (M)");
							
							def3=true;								
					}
					
					if(def3==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(rCtx.attributeValue().getText().substring(0, rCtx.attributeValue().getText().length()-1)+"M");
						m.put(rCtx.attributeName().getText(), value);
					}
					else
					{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					}
					break;
					
				case "primaryTakeoff":
					String[] primaryTakeoffOptions = {"TRUE","YES","FALSE","NO"};
					if(!Arrays.asList(primaryTakeoffOptions).contains(rCtx.attributeValue().getText())){

						System.out.println("Line "+rCtx.getStart().getLine()+": Wrong runway primaryTakeoff: " + rCtx.attributeValue().getText() + ". Used default 'TRUE'");
						m.put(rCtx.attributeName().getText(), "TRUE");
					}else{
						if(rCtx.attributeValue().getText().equals("TRUE") || rCtx.attributeValue().getText().equals("YES")){
							m.put(rCtx.attributeName().getText(), "TRUE");
						}else{
							m.put(rCtx.attributeName().getText(), "FALSE");
						}
					}
					break;
					
				case "primaryLanding":
					String[] primaryLandingOptions = {"TRUE","FALSE"};
					if(!Arrays.asList(primaryLandingOptions).contains(rCtx.attributeValue().getText())){

						System.out.println("Line "+rCtx.getStart().getLine()+": Wrong runway primaryLanding: " + rCtx.attributeValue().getText() + ". Used default 'TRUE'");
						m.put(rCtx.attributeName().getText(), "TRUE");
					}else{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					}
					break;
					
				case "primaryPattern":
					String[] primaryPatternOptions = {"LEFT","RIGHT"};
					if(!Arrays.asList(primaryPatternOptions).contains(rCtx.attributeValue().getText())){

						System.out.println("Wrong runway primaryPattern: " + rCtx.attributeValue().getText() + ". Used default 'LEFT'");
						m.put(rCtx.attributeName().getText(), "LEFT");
					}else{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					}
					break;
					
				case "secondaryTakeoff":
					String[] secondaryTakeoffOptions = {"TRUE","FALSE"};
					if(!Arrays.asList(secondaryTakeoffOptions).contains(rCtx.attributeValue().getText())){

						System.out.println("Line "+rCtx.getStart().getLine()+": Wrong runway secondaryTakeoff: " + rCtx.attributeValue().getText() + ". Used default 'TRUE'");
						m.put(rCtx.attributeName().getText(), "TRUE");
					}else{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					}
					break;
					
				case "secondaryLanding":
					String[] secondaryLandingOptions = {"TRUE","FALSE"};
					if(!Arrays.asList(secondaryLandingOptions).contains(rCtx.attributeValue().getText())){

						System.out.println("Line "+rCtx.getStart().getLine()+": Wrong runway secondaryLanding: " + rCtx.attributeValue().getText() + ". Used default 'TRUE'");
						m.put(rCtx.attributeName().getText(), "TRUE");
					}else{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					}
					break;
					
				case "secondaryPattern":
					String[] secondaryPatternOptions = {"LEFT","RIGHT"};
					if(!Arrays.asList(secondaryPatternOptions).contains(rCtx.attributeValue().getText())){

						System.out.println("Line "+rCtx.getStart().getLine()+": Wrong runway secondaryPattern: " + rCtx.attributeValue().getText() + ". Used default 'LEFT'");
						m.put(rCtx.attributeName().getText(), "LEFT");
					}else{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					}
					break;

				default:
					m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					break;
				}
				
			}
			else
			{
				
				System.out.println("Line "+rCtx.getStart().getLine()+": Wrong attribute name. used: "+ rCtx.attributeName().getText());
				parsingOK=false;
			}
			
			
		}
		if(requiredCounter!=8)
		{
			System.out.println("Wrong number of required attributes. must be 8: lat, lon, alt, surface, heading, length, width, number");
			parsingOK=false;
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
			String attValue= mCtx.attributeValue().getText();
			//validar nome de atributo e verificar ordem
			if(markingsAttNames.contains(attName))
			{
				
				if(semanticChecker.checkOptions(attValue, trueOrFalse, mCtx))
				{
					m.put(attName, attValue);
					requiredCounter++;
				}
			}
			else
			{
			System.out.println("Line "+mCtx.getStart().getLine()+": Wrong argument name. used:"+ attName+ ". use "+ markingsAttNames);
			parsingOK=false;
			}
		}
		
		if(requiredCounter!=19)
		{
			System.out.println("Wrong number of arguments in markings");
			parsingOK=false;
		}
		else
		{
			markingsAtts.put("MARKINGS"+actualMarkingsIndex, m);
			runwayElems.put("MARKINGS"+actualMarkingsIndex, "RUNWAY"+actualRunwayIndex);
		}
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
		int requiredCounter = 0;
		for(XMLParser.AttributeContext lCtx : ctx.attribute())
		{
			
			String attName=lCtx.attributeName().getText();
			String attValue= lCtx.attributeValue().getText();
			//validar nome de atributo e verificar ordem
			if(lightsAttNames.contains(attName))
			{
				
				switch (attName) {	
				
				case "center":
					String[] centerOptions = {"NONE","LOW", "MEDIUM", "HIGH"};
					if(!semanticChecker.checkOptions(attValue, centerOptions, lCtx)){

						System.out.println("Used default 'NONE'");
						m.put(attName, "NONE");
						requiredCounter++;
					}else{
						m.put(attName, attValue);
						requiredCounter++;
					}
					break;
					
				case "edge":
					String[] edgeOptions = {"NONE","LOW", "MEDIUM", "HIGH"};
					if(!semanticChecker.checkOptions(attValue, edgeOptions, lCtx)){

						System.out.println("Used default 'NONE'");
						m.put(attName, "NONE");
						requiredCounter++;
					}else{
						m.put(attName, attValue);
						requiredCounter++;
					}
					break;
					
				case "centerRed":
					
					if(semanticChecker.checkOptions(attValue, trueOrFalse, lCtx)){
					
						m.put(lCtx.attributeName().getText(), lCtx.attributeValue().getText());
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
				
				default:
					
					break;
				}
				
			}
			else
			{
				
				System.out.println("Line "+lCtx.getStart().getLine()+": Wrong attribute name. used: "+ lCtx.attributeName().getText());
				parsingOK=false;
			}
			
			
		}
		if(requiredCounter!=3)
		{
			System.out.println("Wrong number of required attributes. must be 3: center, edge, centerRed");
			parsingOK=false;
		}
		else
		{
			lightsAtts.put("LIGHTS"+actualLightsIndex, m);
			airportElems.put("LIGHTS"+actualLightsIndex, "RUNWAY"+actualRunwayIndex);
		}
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
		int requiredCounter = 0;
		for(XMLParser.AttributeContext otCtx : ctx.attribute())
		{
			String attName=otCtx.attributeName().getText();
			String attValue=otCtx.attributeValue().getText();
			
			//validar nome de atributo e verificar ordem
			if(offsetThresholdAttNames.contains(attName))
			{
				
				switch (attName) {	

				case "end":
					String[] endOptions = {"PRIMARY","SECONDARY"};
					if(semanticChecker.checkOptions(attValue, endOptions, otCtx)){

						m.put(otCtx.attributeName().getText(), otCtx.attributeValue().getText());
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;

				case "length"://req
					boolean def = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character lengthUnits = new Character (otCtx.attributeValue().getText().charAt(attValue.length()-1));
					if(!lengthUnits.equals('F') && !lengthUnits.equals('M'))
					{
							System.out.println("Line "+otCtx.getStart().getLine()+": warning: invalid length units in offsetthreshold. using default (M)");
							def=true;								
					}
					
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(otCtx.attributeValue().getText().substring(0, otCtx.attributeValue().getText().length()-1)+"M");
						m.put(attName, value);
						requiredCounter++;
					}
					else
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					break;

				case "width":
					boolean def1 = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character widthUnits = new Character (otCtx.attributeValue().getText().charAt(otCtx.attributeValue().getText().length()-1));
					if(!widthUnits.equals('F') && !widthUnits.equals('M'))
					{
							System.out.println("Line "+otCtx.getStart().getLine()+": warning: invalid width units in offsetthreshold. using default (M)");
							def1=true;								
					}
					
					if(def1==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attValue.substring(0, otCtx.attributeValue().getText().length()-1)+"M");
						m.put(attName, value);
					}
					else
					{
						m.put(attName, attValue);
					}
					break;

				case "surface":
					String[] surfaceOptions = {"ASPHALT","BITUMINOUS","BRICK","CLAY","CEMENT","CONCRETE","CORAL","DIRT","GRASS","GRAVEL","ICE","MACADAM","OIL_TREATED, PLANKS","SAND","SHALE","SNOW","STEEL_MATS","TARMAC","UNKNOWN","WATER"};
					if(semanticChecker.checkOptions(attValue, surfaceOptions, otCtx))
					{
						m.put(attName, attValue);
						
					}
					else
						parsingOK=false;
					break;

				default:
					break;
				}
			}
			else
			{				
				System.out.println("Line "+otCtx.getStart().getLine()+": Wrong attribute name. used: "+ attName);
				parsingOK=false;
			}
		}
		if(requiredCounter!=2)
		{
			System.out.println("Wrong number of required attributes. must be 2: end, length");
			parsingOK=false;
		}
		else
		{
			offsetThresholdAtts.put("OFFSETTHRESHOLD"+actualOffsetThresholdIndex, m);
			airportElems.put("OFFSETTHRESHOLD"+actualOffsetThresholdIndex, "RUNWAY"+actualRunwayIndex);
		}
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
		int requiredCounter = 0;
		for(XMLParser.AttributeContext alCtx : ctx.attribute())
		{
			String attName=alCtx.attributeName().getText();
			String attValue=alCtx.attributeValue().getText();
			
			//validar nome de atributo e verificar ordem
			if(approachLighstsAttNames.contains(attName))
			{
				
				switch (attName) {	

				case "end":
					String[] endOptions = {"PRIMARY","SECONDARY"};
					if(semanticChecker.checkOptions(attValue, endOptions, alCtx)){

						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "system":
					String[] systemOptions = {"NONE","ALSF1","ALSF2","CALVERT","CALVERT2","MALS","MALSF","MALSR","ODALS","RAIL","SALS","SALSF","SSALF","SSALR","SSALS"};
					if(semanticChecker.checkOptions(attValue, systemOptions, alCtx)){
						m.put(attName, attValue);
					}
				
					break;
					
				case "strobes":
					
					if(!(Integer.parseInt(attValue) >= 0 )){
						System.out.println("Line "+alCtx.getStart().getLine()+": Wrong approachLights strobes value: " + alCtx.attributeValue().getText() + ". Put default: 0");
						m.put(attName, "0");
					}else{
						m.put(attName, attValue);
					}
					break;
					
				case "reil":
					String[] reilOptions = {"TRUE","FALSE"};
					if(semanticChecker.checkOptions(attValue, reilOptions, alCtx)){
						m.put(attName, attValue);
					}
					break;
					
				case "touchdown":
					
					if(semanticChecker.checkOptions(attValue, trueOrFalse, alCtx)){
						
						m.put(attName, attValue);
					}
					break;
					
				case "endLights":
					
					if(semanticChecker.checkOptions(attValue, trueOrFalse, alCtx)){
					
						m.put(attName, attValue);
					}
					break;
					
				default:
					break;
				}

			}
			else
			{				
				System.out.println("Line "+alCtx.getStart().getLine()+": Wrong attribute name. used: "+ alCtx.attributeName().getText());
				parsingOK=false;
			}
		}
		if(requiredCounter!=1)
		{
			System.out.println("Wrong number of required attributes. must be 1: end");
			parsingOK=false;
		}
		else
		{
			approachLighstsAtts.put("APPROACHLIGHTS"+actualApproachLightsIndex, m);
			airportElems.put("APPROACHLIGHTS"+actualApproachLightsIndex, "RUNWAY"+actualRunwayIndex);
		}
	}

	@Override public void enterVasiElement(@NotNull XMLParser.VasiElementContext ctx) 
	{ 
		actualVasiIndex++;
	}

	@Override public void exitVasiElement(@NotNull XMLParser.VasiElementContext ctx) 
	{ 
		Map<String, String> m = new LinkedHashMap<String, String>();
		int requiredCounter = 0;
		boolean defBiasX =false;
		boolean defBiasZ = false;
		boolean defSpacing= false;
		for(XMLParser.AttributeContext vCtx : ctx.attribute())
		{
			String attName=vCtx.attributeName().getText();
			String attValue=vCtx.attributeValue().getText();
			
			//validar nome de atributo e verificar ordem
			if(vasiAttNames.contains(attName))
			{
				String attVal = vCtx.attributeValue().getText();
				
				switch (attName) {
				case "end":
					if(semanticChecker.checkOptions(attValue, primaryOrSecondary, vCtx))
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
				case "type":
					
					if(!vasiTypeValues.contains(attVal))
					{
						System.out.println("Line "+vCtx.getStart().getLine()+": Wrong type vaule. must be one of "+ vasiTypeValues);
						parsingOK=false;
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					
					break;
				case "side":
					if(semanticChecker.checkOptions(attVal, leftOrRight, vCtx))
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					else
						parsingOK=false;
					
					break;
				case "biasX":
					
					defBiasX=false;
					
					//ultimo char so attvalue
					Character biasXUnits = new Character (attVal.charAt(attVal.length()-1));
					if(!biasXUnits.equals('F') && !biasXUnits.equals('M'))
					{
							System.out.println("Line "+vCtx.getStart().getLine()+": warning: invalid biasX units in vasi. using default (M)");
							defBiasX=true;								
					}
					
					if(defBiasX==true)
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
				case "biasZ":
					
					defBiasZ=false;
					
					//ultimo char so attvalue
					Character biasZUnits = new Character (attVal.charAt(attVal.length()-1));
					if(!biasZUnits.equals('F') && !biasZUnits.equals('M'))
					{
							System.out.println("Line "+vCtx.getStart().getLine()+": warning: invalid biasZ units in vasi. using default (M)");
							defBiasX=true;								
					}
					
					if(defBiasZ==true)
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
				case "spacing":
					
					defSpacing=false;
					
					//ultimo char so attvalue
					Character spacingUnits = new Character (attVal.charAt(attVal.length()-1));
					if(!spacingUnits.equals('F') && !spacingUnits.equals('M'))
					{
							System.out.println("Line "+vCtx.getStart().getLine()+": warning: invalid spacing units in vasi. using default (M)");
							defBiasX=true;								
					}
					
					if(Integer.parseInt(attVal)<0)
					{
						System.out.println("Line "+vCtx.getStart().getLine()+": Wrong spacing value. value must be greater or equal to 0");
						parsingOK=false;
					}
					
					if(defSpacing==true)
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
				case "pitch":
					
					if(Float.parseFloat(attVal)<0.0 || Float.parseFloat(attVal)>10.0)
					{
						System.out.println("Line "+vCtx.getStart().getLine()+": Wrong value for pitch atribute. mute be <10.0 and >0.0");
						parsingOK=false;
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					
					break;
				default:
					System.out.println("ups");
					break;
				}
			}
			else
			{
				System.out.println("Line "+vCtx.getStart().getLine()+": Wrong attribute name. used: "+ attName +". use only: " + vasiAttNames);
				parsingOK=false;
			}
		}
		if(requiredCounter!=7)
		{
			System.out.println("Wrong number of atributes. must be 7 valid: "+ vasiAttNames);
			parsingOK=false;
		}
		else
		{
		vasiAtts.put("VASI"+actualVasiIndex, m);
		runwayElems.put("VASI"+actualVasiIndex, "RUNWAY"+actualRunwayIndex);
		}
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
			String attValue=ilsCtx.attributeValue().getText();
			//validar nome de atributo
			if(ilsAttNames.contains(attName))
			{

				switch (attName) {
				
				case "lat":
						
					if (semanticChecker.checkLatitude(attValue, ilsCtx)) {
						m.put(attName, attValue);		
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "lon":
					
					if(semanticChecker.checkLongitude(attValue, ilsCtx))
					{
					m.put(attName, attValue);
					requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "alt":
		
					boolean def = false;
					
					if(semanticChecker.checkAltitude(attValue, ilsCtx, def))
					{
						if(def==true)
						{
							//valor antigo com as unidades por defeito M
							String value = new String(attValue.substring(0, attValue.length()-1)+"M");
							m.put(attName, value);
							requiredCounter++;
						}
						else
						{
							m.put(attName, attValue);
							requiredCounter++;
						}
					}
					
				break;
					
				case "heading"://req
						
					if(semanticChecker.checkHeading(attValue, ilsCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "frequency":
					
					
					
					if(Float.parseFloat(attValue)<108.0 || Float.parseFloat(attValue)>136.992)
					{
						System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue);
						return;
					}else{
						m.put(attName, attValue);
						requiredCounter++;
					}					
					break;
					
				case "end":
					
					if(semanticChecker.checkOptions(attValue, primaryOrSecondary, ilsCtx)){

						m.put(attName,attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "range":
					m.put(attName,attValue);
					
					break;//TODO cofirmar ^ (assumo que recebe sempre um numero, ou numero+N)
					
				case "magvar":
					
					
					
					if(Float.parseFloat(attValue)<-360.0 || Float.parseFloat(attValue)>360)
					{
						System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue + ". Must be -360 to 360. Put default: 0.0");
						m.put(attName, "0.0");
						requiredCounter++;
					}else{
						m.put(attName,attValue);
						requiredCounter++;
					}
					
					break;
					
				case "ident":
					if(attValue.length()>4)
					{
						
						System.out.println("Line "+ilsCtx.getStart().getLine()+": ILS ident value too long: " + attValue + ". Max 4 Chars!");
						parsingOK=false;
						
					}
					else
					{
					m.put(attName,attValue);
					requiredCounter++;
					}
					break;
					
				case "width":
					
					
					if(Float.parseFloat(attValue)<0 || Float.parseFloat(attValue)>360)
					{
						System.out.println("Line "+ilsCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue + ". Must be 0 to 360.");
						parsingOK=false;
						
					}else{
						m.put(attName,attValue);
					}					
					break;
					
				case "name":
					if(attValue.length()>48)
					{
						
						System.out.println("Line "+ilsCtx.getStart().getLine()+": ILS ident value too long: " + attValue + ". Max 48 Chars!");
						parsingOK=false;
					}
					else
					{
					m.put(attName,attValue);
					}
					break;
					
				case "backCourse":
					
					if(semanticChecker.checkOptions(attValue, trueOrFalse, ilsCtx)){
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
				parsingOK=false;
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
			parsingOK=false;
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
								
					if (semanticChecker.checkLatitude(attValue, gsCtx)) {
						m.put(attName, attValue);		
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "lon":
					
					if(semanticChecker.checkLongitude(attValue, gsCtx))
					{
					m.put(attName, attValue);
					requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "alt":
		
					boolean def = false;
					
					if(semanticChecker.checkAltitude(attValue, gsCtx, def))
					{
						if(def==true)
						{
							//valor antigo com as unidades por defeito M
							String value = new String(attValue.substring(0, attValue.length()-1)+"M");
							m.put(attName, value);
							requiredCounter++;
						}
						else
						{
							m.put(attName, attValue);
							requiredCounter++;
						}
					}
					
				break;
				case "pitch":
					
					Float attVal = Float.parseFloat(attValue);
					if(attVal<0 || attVal>360)
					{
						System.out.println("Line "+gsCtx.getStart().getLine()+": Wrong pitch value in glide scope. must be non negative and not greater than 360");
						parsingOK=false;
						
					}
					else
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
				break;
				case "range":
					m.put(attName, attValue);
					requiredCounter++;
					
					break; 	
				default:
					break;
				}
			}
		}
		
		if(!m.containsKey("range")){
			System.out.println("GlideSlope range not defined. Put default 27N");
			m.put("range", "27N");
			requiredCounter++;
		}
		else
			parsingOK=false;
		
		if(requiredCounter!=5)
		{
			System.out.println("Wrong number of arguments in GlideSlope. must be 5: "+ glideSlopeAttNames);
			parsingOK=false;
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

		int requiredCounter=0;

		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext bpCtx : ctx.attribute())
		{

			String attName=bpCtx.attributeName().getText();
			String attValue=bpCtx.attributeValue().getText();

			//validar nome de atributo e verificar ordem
			if(blastPadAttNames.contains(attName))
			{

				switch (attName) {
				
				case "end":
					
					
					if(semanticChecker.checkOptions(attValue, primaryOrSecondary, bpCtx))
							{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
					

				case "length":

					boolean def = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);

					//ultimo char so attvalue
					Character lengthUnits = new Character (bpCtx.attributeValue().getText().charAt(bpCtx.attributeValue().getText().length()-1));
					if(!lengthUnits.equals('F') && !lengthUnits.equals('M'))
					{
						System.out.println("Line "+bpCtx.getStart().getLine()+": warning: invalid length units in blastpad. using default (M)");
						def=true;								
					}

					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(bpCtx.attributeValue().getText().substring(0, bpCtx.attributeValue().getText().length()-1)+"M");
						m.put(attName, value);
					}
					else
					{
						m.put(attName, attValue);
						requiredCounter++;
					}

					break;
					
				case "width":
					
					boolean def2 = false;
					

					//ultimo char so attvalue
					Character widthUnits = new Character (bpCtx.attributeValue().getText().charAt(bpCtx.attributeValue().getText().length()-1));
					if(!widthUnits.equals('F') && !widthUnits.equals('M'))
					{
						System.out.println("Line "+bpCtx.getStart().getLine()+": warning: invalid width units in blastpad. using default (M)");
						def=true;								
					}

					if(def2==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attValue.substring(0, attValue.length()-1)+"M");
						m.put(attName, value);
					}
					else
					{
						m.put(attName, attValue);
					}
					
					break;

				case "surface":
					
					String[] surfaceOptions = {"ASPHALT","BITUMINOUS","BRICK","CLAY","CEMENT","CONCRETE","CORAL","DIRT","GRASS","GRAVEL","ICE","MACADAM","OIL_TREATED, PLANKS","SAND","SHALE","SNOW","STEEL_MATS","TARMAC","UNKNOWN","WATER"};
					if(semanticChecker.checkOptions(attValue, surfaceOptions, bpCtx))
					{
						m.put(attName, attValue);
					}
					break;
					
				default:

					break;
				}

			}
			else
			{

				System.out.println("Line "+bpCtx.getStart().getLine()+": Wrong attribute name. used: "+ bpCtx.attributeName().getText());
				parsingOK=false;
			}


		}
		if(requiredCounter != 2){
			System.out.println("Wrong number of required attributes. must be 2: end, length");
			parsingOK=false;
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
			String attValue=rsCtx.attributeValue().getText();

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
					
					
					if (semanticChecker.checkLatitude(attValue, rsCtx)) {
						m.put(attName, attValue);		
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "lon":
					
					if(semanticChecker.checkLongitude(attValue, rsCtx))
					{
					m.put(attName, attValue);
					requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "alt":
		
					boolean def = false;
					
					if(semanticChecker.checkAltitude(attValue, rsCtx, def))
					{
						if(def==true)
						{
							//valor antigo com as unidades por defeito M
							String value = new String(attValue.substring(0, attValue.length()-1)+"M");
							m.put(attName, value);
							requiredCounter++;
						}
						else
						{
							m.put(attName, attValue);
							requiredCounter++;
						}
					}
					
				break;
				
				case "heading":
					
					
					if(semanticChecker.checkHeading(attValue, rsCtx))
					{
						m.put(attName, attValue);
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
				parsingOK=false;
			}
		}
		
		if(requiredCounter!=4)
		{
			System.out.println("Wrong number of needed arguments. must be at least this 4: "+ runwayStartAttNames);
			parsingOK=false;
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
		int requiredCounter = 0;
		for(XMLParser.AttributeContext oCtx : ctx.attribute())
		{
			String attName=oCtx.attributeName().getText();
			String attValue=oCtx.attributeValue().getText();
			
			//validar nome de atributo e verificar ordem
			if(overrunAttNames.contains(attName))
			{
				
				switch (attName) {	

				case "end":
					
					if(semanticChecker.checkOptions(attValue, primaryOrSecondary, oCtx)){

						m.put(oCtx.attributeName().getText(), oCtx.attributeValue().getText());
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;

				case "length"://req
					boolean def = false;
					//ultimo char so attvalue
					Character lengthUnits = new Character (attValue.charAt(attValue.length()-1));
					if(!lengthUnits.equals('F') && !lengthUnits.equals('M'))
					{
							System.out.println("Line "+oCtx.getStart().getLine()+": warning: invalid length units in offsetthreshold. using default (M)");
							def=true;								
					}
					
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attValue.substring(0, attValue.length()-1)+"M");
						m.put(attName, value);
						requiredCounter++;
					}
					else
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					break;

				case "width":
					boolean def1 = false;
										
					//ultimo char so attvalue
					Character widthUnits = new Character (attValue.charAt(attValue.length()-1));
					if(!widthUnits.equals('F') && !widthUnits.equals('M'))
					{
							System.out.println("Line "+oCtx.getStart().getLine()+": warning: invalid width units in offsetthreshold. using default (M)");
							def1=true;								
					}
					
					if(def1==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attValue.substring(0, attValue.length()-1)+"M");
						m.put(attValue, value);
					}
					else
					{
						m.put(attValue, attValue);
					}
					break;

				case "surface":
					String[] surfaceOptions = {"ASPHALT","BITUMINOUS","BRICK","CLAY","CEMENT","CONCRETE","CORAL","DIRT","GRASS","GRAVEL","ICE","MACADAM","OIL_TREATED, PLANKS","SAND","SHALE","SNOW","STEEL_MATS","TARMAC","UNKNOWN","WATER"};
					if(semanticChecker.checkOptions(attValue, surfaceOptions, oCtx))
					{
						m.put(attName, attValue);
						
					}
					break;

				default:
					break;
				}

			}
			else
			{
				System.out.println("Line "+oCtx.getStart().getLine()+": Wrong argument name. use only "+ overrunAttNames);
			}
		}
		if(requiredCounter!=2)
		{
			System.out.println("Wrong number of required arguments in overun. Use at least " + overrunAttNames.get(0)+ " and " + overrunAttNames.get(1));
			parsingOK=false;
		}
		else
		{
			overrunAtts.put("OVERRUN"+actualOverrunIndex, m);
			runwayElems.put("OVERRUN"+actualOverrunIndex, "RUNWAY"+actualRunwayIndex);
		}
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
			String attValue = twpCtx.attributeValue().getText();
			//validar nome de atributo
			if(taxiwayPointAttNames.contains(attName))
			{

				switch (attName) {
				case "index":
					
					if(Integer.parseInt(attValue)>0 || Integer.parseInt(attValue)<3999)
					{
				
						m.put(attName, attValue);
						requiredCounter++;
						
					}
					else
						parsingOK=false;
					break;
					
				case "type":
					
					String[] typeOptions = {"NORMAL","HOLD_SHORT","ILS_HOLD_SHORT","HOLD_SHORT_NO_DRAW","ILS_HOLD_SHORT_NO_DRAW"};
					if(semanticChecker.checkOptions(attValue, typeOptions, twpCtx)){

						m.put(attName, attValue);
						requiredCounter++;
						
					}
					
					break;
					
				case "orientation":
					
					String[] orientationOptions = {"FORWARD", "REVERSE"};
					if(semanticChecker.checkOptions(attValue, orientationOptions, twpCtx)){
						m.put(twpCtx.attributeName().getText(), twpCtx.attributeValue().getText());
					}
					else
						parsingOK=false;
					
					break;
					
				case "lat":
					
					
					if (semanticChecker.checkLatitude(attValue, twpCtx)) {
						m.put(attName, attValue);		
						requiredCounter++;
						latlonCounter++;
					
					}
					break;
					
				case "lon":
					
					if(semanticChecker.checkLongitude(attValue, twpCtx))
					{
					m.put(attName, attValue);
					requiredCounter++;
					latlonCounter++;
					
					}
					break;
					
				case "biasX":

					def=false;


					//ultimo char so attvalue
					Character biasXUnits = new Character (attValue.charAt(attValue.length()-1));
					if(!biasXUnits.equals('F') && !biasXUnits.equals('M'))
					{
						System.out.println("Line "+twpCtx.getStart().getLine()+": warning: invalid biasX units in taxiwaypoint. using default (M)");
						def=true;								
					}

					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attValue.substring(0, attValue.length()-1)+"M");
						m.put(attName, value);
						biasCounter++;
						requiredCounter++;
						
					}
					else
					{
						m.put(attName, attValue);
						biasCounter++;
						requiredCounter++;
						
					}

					break;
				case "biasZ":
					
					def=false;


					//ultimo char so attvalue
					Character biasZUnits = new Character (attValue.charAt(attValue.length()-1));
					if(!biasZUnits.equals('F') && !biasZUnits.equals('M'))
					{
						System.out.println("Line "+twpCtx.getStart().getLine()+": warning: invalid biasZ units in taxiwaypoint. using default (M)");
						def=true;								
					}

					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attValue.substring(0, attValue.length()-1)+"M");
						m.put(attName, value);
						biasCounter++;
						requiredCounter++;
						
					}
					else
					{
						m.put(attName, attValue);
						biasCounter++;
						requiredCounter++;
						
					}
					
					break;
					
				default:
					break;
				}
			}
			else
			{

				System.out.println("Line "+twpCtx.getStart().getLine()+": Wrong attribute name. used: "+ twpCtx.attributeName().getText());
				parsingOK=false;
			}
		}//TODO testar
		if(((latlonCounter != 2) && (biasCounter != 2)) ||
				((latlonCounter == 2) && (biasCounter != 0))||
				((biasCounter == 2) && (latlonCounter!= 0)))
		{
			System.out.println("Choose one pair of attributes lat/lon or biasX/biasZ");
			parsingOK=false;
		}
		
		if(requiredCounter != 4){
			System.out.println("Wrong number of required arguments on taxiwatpoint. Use at least " + taxiwayPointAttNames.get(0)+ " and " + taxiwayPointAttNames.get(1));
			parsingOK=false;
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
			String attValue=tpCtx.attributeValue().getText();

			//validar nome de atributo
			if(taxiwayParkingAttNames.contains(attName))
			{
				
				switch (attName) {
				case "index":
					
					 val= Integer.parseInt(attValue);
					
					if(val<0 || val>3999)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong attribute value for index in taxiwayParking. Used " +val+ " must be between 0 and 3999.");
						parsingOK=false;
					}
					else
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					break;
					case "lat":
					
						
						if (semanticChecker.checkLatitude(attValue, tpCtx)) {
							m.put(attName, attValue);		
							requiredCounter++;
							latlonCounter++;
						}
						else
							parsingOK=false;
					break;
					
				case "lon":
					
					if(semanticChecker.checkLongitude(attValue, tpCtx))
					{
					m.put(attName, attValue);
					latlonCounter++;
					requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "biasX":
					
					def=false;
					
					
					//ultimo char so attvalue
					Character biasXUnits = new Character (attValue.charAt(attValue.length()-1));
					if(!biasXUnits.equals('F') && !biasXUnits.equals('M'))
					{
							System.out.println("Line "+tpCtx.getStart().getLine()+": warning: invalid biasX units in taxiwayParking. using default (M)");
							def=true;								
					}
					
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attValue.substring(0, attValue.length()-1)+"M");
						m.put(attName, value);
						biasCounter++;
					}
					else
					{
						m.put(attName, attValue);
						biasCounter++;
					}
					
					break;
				case "biasZ":
					
					def=false;
					
					
					//ultimo char so attvalue
					Character biasZUnits = new Character (attValue.charAt(attValue.length()-1));
					if(!biasZUnits.equals('F') && !biasZUnits.equals('M'))
					{
							System.out.println("Line "+tpCtx.getStart().getLine()+": warning: invalid biasZ units in taxiwayParking. using default (M)");
							def=true;								
					}
					
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(attValue.substring(0, attValue.length()-1)+"M");
						m.put(attName, value);
						biasCounter++;
					}
					else
					{
						m.put(attName, attValue);
						biasCounter++;
					}
					
					break;
				case "heading"://req
							
					if(semanticChecker.checkHeading(attValue, tpCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;

					break;
				case "radius":
					
					if(attValue.endsWith("M"))
						
					{
						attValue=attValue.substring(0, attValue.length()-1);
					}
					
					if(Float.parseFloat(attValue)<0 || Float.parseFloat(attValue)>360)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": invalid " + attName + " value : " + attValue + " in taxywayParking");
						parsingOK=false;
						return;
					}else{
						m.put(attName, attValue);
						requiredCounter++;
					}
					break;
				case "type":
					
					if(!taxiwayParkingTypeValues.contains(attValue))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong type value in taxywayParking. must be one of "+ taxiwayParkingTypeValues);
						parsingOK=false;
						return;
					}
					else
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					break;
				case "name":
					
					if(!taxiwayParkingNameValues.contains(attValue))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong name value in taxywayParking. must be one of "+ taxiwayParkingNameValues);
						parsingOK=false;
					}
					else
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					break;
				case "number":
					
					val= Integer.parseInt(attValue);
					
					if(val<0 || val>3999)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong attribute value for number in taxiwayParking. Used " +val+ " must be between 0 and 3999.");
						parsingOK=false;
						return;
					}
					else
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					break;
				case "airplaneCodes":
					
					int validCounter=0;
					String[] value= attValue.split(" ,");
					for(String s : value)
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
					
					if(validCounter!=value.length)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Warning: not using airplaneCodes in taxyparking");
					}
					else
					{
						m.put(attName, attValue);
						
					}
					break;
				case "pushBack":
					
					if(!attValue.equals("NONE") && !attValue.equals("BOTH"))
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for pushback in taxiwayParking. Used: " + attValue + "acepts NONE or BOTH");
						parsingOK=false;
						return;
					}
					else
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					break;
				case "teeOffset1":
					
					if(Float.parseFloat(attValue)<0.1 || Float.parseFloat(attValue)>50.0)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for "+ attName+ ". used: " + attValue+". Must be between 0.1 and 50.0");
					}
					else
					{
						m.put(attName, attValue);
					}
					
					break;
				case "teeOffset2":
					if(Float.parseFloat(attValue)<0.1 || Float.parseFloat(attValue)>50.0)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for "+ attName+ ". used: " + attValue+". Must be between 0.1 and 50.0");
					}
					else
					{
						m.put(attName, attValue);
					}
					break;
				case "teeOffset3":
					if(Float.parseFloat(attValue)<0.1 || Float.parseFloat(attValue)>50.0)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for "+ attName+ ". used: " + attValue+". Must be between 0.1 and 50.0");
					}
					else
					{
						m.put(attName, attValue);
					}
					break;
				case "teeOffset4":
					if(Float.parseFloat(attValue)<0.1 || Float.parseFloat(attValue)>50.0)
					{
						System.out.println("Line "+tpCtx.getStart().getLine()+": Wrong value for "+ attName+ ". used: " + attValue+". Must be between 0.1 and 50.0");
					}
					else
					{
						m.put(attName, attValue);
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
			parsingOK=false;
		}
		
		if(requiredCounter!=9)
		{
			System.out.println("Wrong Number of required Arguments in taxiwayParking.");
			parsingOK=false;
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
			String attValue=tnCtx.attributeValue().getText();
			//validar nome de atributo e verificar ordem
			if(taxiNameAttNames.contains(attName))
			{

				switch (attName) {	

				case "index":
					
					 int val= Integer.parseInt(attValue);
					
					if(val<0 || val>255)
					{
						System.out.println("Line "+tnCtx.getStart().getLine()+": Wrong attribute value for index in taxiName. Used " +val+ " must be between 0 and 255.");
						parsingOK=false;
					}
					else
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					break;

				case "name":
					if(attValue.length()>8)
					{
						System.out.println("Line "+tnCtx.getStart().getLine()+": taxiName name value too long: " + attValue + ". Max 8 Chars!");
						parsingOK=false;
						return;
					}
					m.put(attName, attValue);
					requiredCounter++;
					break;

				default:
					break;
				}
			}else
			{
				System.out.println("Line "+tnCtx.getStart().getLine()+": Wrong attribute name. used: "+ attName);
				parsingOK=false;
			}
		}
		if(requiredCounter != 2){
			System.out.println("Wrong number of required arguments. Use " + taxiNameAttNames.get(0)+ " and " + taxiNameAttNames.get(1));
			parsingOK=false;
		}
		else
		{
			taxiNameAtts.put("TAXINAME"+actualTaxiNameIndex, m);
			airportElems.put("TAXINAME"+actualTaxiNameIndex, "AIRPORT"+actualAirportIndex);
		}

		
	}
	
	@Override public void enterHelipadElement(@NotNull XMLParser.HelipadElementContext ctx) 
	{ 
		actualHelipadIndex++;
	}

	@Override public void exitHelipadElement(@NotNull XMLParser.HelipadElementContext ctx) 
	{ 
		int requiredCounter = 0;
		int val;
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext hCtx : ctx.attribute())
		{

			String attName=hCtx.attributeName().getText();
			String attValue=hCtx.attributeValue().getText();

			//validar nome de atributo
			if(helipadAttNames.contains(attName))
			{
				
				switch (attName) {
				case "lat":
					if(semanticChecker.checkLatitude(attValue, hCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
				case "lon":
					
					if(semanticChecker.checkLongitude(attValue, hCtx))
					{
					m.put(hCtx.attributeName().getText(), hCtx.attributeValue().getText());
					requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "alt":
					
					boolean def = false;
					
					if(semanticChecker.checkAltitude(attValue, hCtx, def))
					{
						if(def==true)
						{
							//valor antigo com as unidades por defeito M
							String value = new String(attValue.substring(0, attValue.length()-1)+"M");
							m.put(attName, value);
							requiredCounter++;
						}
						else
						{
							m.put(attName, attValue);
							requiredCounter++;
						}
					}
					else
						parsingOK=false;
					
				break;
				
				case "surface"://req
					String[] surfaceOptions = {"ASPHALT","BITUMINOUS","BRICK","CLAY","CEMENT","CONCRETE","CORAL","DIRT","GRASS","GRAVEL","ICE","MACADAM","OIL_TREATED, PLANKS","SAND","SHALE","SNOW","STEEL_MATS","TARMAC","UNKNOWN","WATER"};
					if(semanticChecker.checkOptions(attValue, surfaceOptions, hCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
					
				case "heading"://req
					
					if(semanticChecker.checkHeading(attValue, hCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;

					break;
					
				case "length"://req
					
					boolean def1 = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character lengthUnits = new Character (hCtx.attributeValue().getText().charAt(hCtx.attributeValue().getText().length()-1));
					if(!lengthUnits.equals('F') && !lengthUnits.equals('M'))
					{
							System.out.println("Line "+hCtx.getStart().getLine()+": warning: invalid alt units in runway. using default (M)");
							def1=true;								
					}
					
					if(def1==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(hCtx.attributeValue().getText().substring(0, hCtx.attributeValue().getText().length()-1)+"M");
						m.put(hCtx.attributeName().getText(), value);
						requiredCounter++;
					}
					else
					{
						m.put(hCtx.attributeName().getText(), hCtx.attributeValue().getText());
						requiredCounter++;
					}
					
					break;
					
				case "width"://req
					boolean def2 = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character widthUnits = new Character (hCtx.attributeValue().getText().charAt(hCtx.attributeValue().getText().length()-1));
					if(!widthUnits.equals('F') && !widthUnits.equals('M'))
					{
							System.out.println("Line "+hCtx.getStart().getLine()+": warning: invalid alt units in runway. using default (M)");
							def2=true;								
					}
					
					if(def2==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(hCtx.attributeValue().getText().substring(0, hCtx.attributeValue().getText().length()-1)+"M");
						m.put(hCtx.attributeName().getText(), value);
						requiredCounter++;
					}
					else
					{
						m.put(hCtx.attributeName().getText(), hCtx.attributeValue().getText());
						requiredCounter++;
					}
					
					break;
					
				case "type":
					String[] typeOptions = {"NONE","CIRCLE","H","MEDICAL","SQUARE"};
					
						
					if(semanticChecker.checkOptions(attValue, typeOptions, hCtx))
					{
						m.put(attName, attValue);
					}
					
					break;
				
				case "closed":
					
					if(semanticChecker.checkOptions(attValue, trueOrFalse, hCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;
					break;
				case "transparent":
					
					if(semanticChecker.checkOptions(attValue, trueOrFalse, hCtx))
					{
						m.put(attName, attValue);
						requiredCounter++;
					}
					else
						parsingOK=false;
				
					break;
				case "red":
					
					 val= Integer.parseInt(attValue);
						
						if(val<0 || val>255)
						{
							System.out.println("Line "+hCtx.getStart().getLine()+": Wrong attribute value for index in taxiName. Used " +val+ " must be between 0 and 255.");
						}
						else
						{
							m.put(attName, attValue);
							requiredCounter++;
						}
						break;
				case "green":
					
					 val= Integer.parseInt(attValue);
						
						if(val<0 || val>255)
						{
							System.out.println("Line "+hCtx.getStart().getLine()+": Wrong attribute value for index in taxiName. Used " +val+ " must be between 0 and 255.");
							parsingOK=false;
						}
						else
						{
							m.put(attName, attValue);
							requiredCounter++;
						}
						break;
				case "blue":
					
					 val= Integer.parseInt(attValue);
						
						if(val<0 || val>255)
						{
							System.out.println("Line "+hCtx.getStart().getLine()+": Wrong attribute value for index in taxiName. Used " +val+ " must be between 0 and 255.");
							parsingOK=false;
						}
						else
						{
							m.put(attName, attValue);
							requiredCounter++;
						}
						break;			
					default:
					break;
				}
			}
		}
		
		if(requiredCounter!=8)
		{
			System.out.println("counter manhoso:" + requiredCounter);
			System.out.println("Wrong helipad required arguments. Expected: " + helipadAttNames);
			parsingOK=false;
		}
		else
		{
			helipadAtts.put("HELIPAD"+actualHelipadIndex, m);
			airportElems.put("HELIPAD"+actualHelipadIndex, "AIRPORT"+actualAirportIndex);
		}
	}

	@Override public void enterRunwayAliasElement(@NotNull XMLParser.RunwayAliasElementContext ctx)
	{
		actualRunwayAliasIndex++;
	}
	
	@Override public void exitRunwayAliasElement(@NotNull XMLParser.RunwayAliasElementContext ctx)
	{
		Map<String, String> m = new LinkedHashMap<String, String>();
		int requiredCounter = 0;
		for(XMLParser.AttributeContext raCtx : ctx.attribute())
		{
			String attName=raCtx.attributeName().getText();
			String attValue=raCtx.attributeValue().getText();
			//validar nome de atributo e verificar ordem
			if(taxiNameAttNames.contains(attName))
			{

				switch (attName) {	

				case "number":
					
					String[] numberOptions = {"EAST","NORTH","NORTHEAST","NORTHWEST","SOUTH","SOUTHEAST","SOUTHWEST","WEST"};


					if(!Arrays.asList(numberOptions).contains(raCtx.attributeValue().getText()) &&
							(Integer.parseInt(raCtx.attributeValue().getText()) < 00 || Integer.parseInt(raCtx.attributeValue().getText()) > 36)){
						
						System.out.println("Line "+raCtx.getStart().getLine()+": Wrong runway number: " + raCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(numberOptions) + ",or a int from 00 to 36");
						parsingOK=false;
						
					}else{
						m.put(raCtx.attributeName().getText(), raCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;

				case "designator":
					String[] designatorOptions = {"NONE","C","CENTER","L","LEFT","R","RIGHT","W","WATER","A","B"};
					if(!Arrays.asList(designatorOptions).contains(raCtx.attributeValue().getText())){
						
						System.out.println("Line "+raCtx.getStart().getLine()+": Wrong runway surface: " + raCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(designatorOptions));
						parsingOK=false;
						
					}else{
						m.put(attName, attValue);
						m.put("secondaryDesignator", attValue);	//se designator estiver definido, secondary é igual
					}
					break;

				default:
					break;
				}
			}else
			{
				System.out.println("Line "+raCtx.getStart().getLine()+": Wrong attribute name. used: "+ attName);
				parsingOK=false;
			}
		}
		if(requiredCounter != 2){
			System.out.println("Wrong number of required arguments. Use " + runwayAliasAttNames.get(0)+ " and " + runwayAliasAttNames.get(1));
			parsingOK=false;
		}
		else
		{
			taxiNameAtts.put("RUNWAYALIAS"+actualRunwayAliasIndex, m);
			airportElems.put("RUNWAYALIAS"+actualRunwayAliasIndex, "AIRPORT"+actualAirportIndex);
		}
	}
	
	
}
