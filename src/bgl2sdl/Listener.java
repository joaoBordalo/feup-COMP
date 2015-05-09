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
	int airportIndex = 0;
	int fuelIndex =0;
	int actualAirportIndex=0;
	int actualTowerIndex=0;
	int actualRunwayIndex=0;
	int actualMarkingsIndex=0;
	int actualLightsIndex=0;
	int actualOffsetThresholdIndex=0;
	int actualApproachLightsIndex=0;
	int actualVasiIndex=0;
	int actualIlsIndex=0;
	
	
	
	public Listener(XMLParser parser)
	{
		this.parser= parser;
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
		runwayAttNames.put("secondaryLading", 17);
		runwayAttNames.put("secondaryPattern", 18);
		
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
		
		lightsAttNames.add("center");
		lightsAttNames.add("edge");
		lightsAttNames.add("centerRed");
		
		offsetThresholdAttNames.addElement("end");
		offsetThresholdAttNames.addElement("length");
		offsetThresholdAttNames.addElement("width");
		offsetThresholdAttNames.addElement("surface");
		
		approachLighstsAttNames.addElement("end");
		approachLighstsAttNames.addElement("system");
		approachLighstsAttNames.addElement("strobes");
		approachLighstsAttNames.addElement("reil");
		approachLighstsAttNames.addElement("touchdown");
		approachLighstsAttNames.addElement("endLights");
		
		vasiAttNames.add("end");
		vasiAttNames.add("type");
		vasiAttNames.add("side");
		vasiAttNames.add("biasX");
		vasiAttNames.add("biasZ");
		vasiAttNames.add("spacing");
		vasiAttNames.add("pitch");
		
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
	}
	
	@Override public void exitAttribute(@NotNull XMLParser.AttributeContext ctx) 
	{ 
		String attName=ctx.attributeName().getText();
		String attValue = ctx.attributeValue().getText();
		//AirportAtts.get(airportIndex).put(attName, attValue);
		System.out.println("att name: "+ attName + " att val: " + attValue);
		
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
		
		airportIndex++;
		
		
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
			
			//validar nome de atributo e verificar ordem
			if(airportAttNames.containsKey(attName) && airportAttNames.get(attName)>=counter)
			{
				
				switch (attName) {
				case "lat":
					
					if(aCtx.attributeValue().getText().split("-").length==3)
					{
						String[] attValue = aCtx.attributeValue().getText().split("-");
						
						if(Integer.parseInt(attValue[0])<-90 || Integer.parseInt(attValue[0])>90)
						{
							System.out.println("invalid " + attName + " value : " + attValue[0]);
							return;
						}
						
					}
					
					m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					requiredCounter++;
					break;
					
				case "lon":
					
					if(aCtx.attributeValue().getText().split("-").length==3)
					{
						String[] attValue = aCtx.attributeValue().getText().split("-");
						
						if(Integer.parseInt(attValue[0])<-180 || Integer.parseInt(attValue[0])>180)
						{
							System.out.println("invalid " + attName + " value : " + attValue[0]);
							return;
						}
						
					}				
					m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					requiredCounter++;
					break;
					
				case "alt":
		
					boolean def = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character altUnits = new Character (aCtx.attributeValue().getText().charAt(aCtx.attributeValue().getText().length()-1));
					if(!altUnits.equals('F') && !altUnits.equals('M'))
					{
							System.out.println("warning: invalid alt units in airport. using default (M)");
							def=true;								
					}
					
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(aCtx.attributeValue().getText().substring(0, aCtx.attributeValue().getText().length()-1)+"M");
						m.put(aCtx.attributeName().getText(), value);
						requiredCounter++;
					}
					else
					{
						m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
						requiredCounter++;
					}
	
				break;
				
				case "ident":
					
					//
					if(aCtx.attributeValue().getText().length()>4)
					{
						String attValue= aCtx.attributeValue().getText();
						System.out.println("Airport ident value too long: " + attValue + ". Max 4 Chars!");
						return;
					}
					m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					requiredCounter++;
				break;
					
				case "airportTestRadius":
					
					//ir buscar ultimo char
					Character testRadiusUnits = new Character(aCtx.attributeValue().getText().charAt(aCtx.attributeValue().getText().length()-1));
					if(!testRadiusUnits.equals('M') && !testRadiusUnits.equals('G') && !testRadiusUnits.equals('N'))
					{
						System.out.println("Wrong ariportTestRadius format: " + testRadiusUnits +". Use <float(M|G|N)>");
						return;
					}
					else {
						m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
						requiredCounter++;
					}
					
					
					
					break;
					
				case "trafficScalar":
					
					if(Float.parseFloat(aCtx.attributeValue().getText())<0.01 ||
							Float.parseFloat(aCtx.attributeValue().getText())>1.00)
					{
						System.out.println("Wrong airport trafficScalar value. used: "+ aCtx.attributeValue().getText()
											+ "expecting 0.1<=X<=1.0");
						return;
					}
					else
					{
						m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
						requiredCounter++;
					}
					
					break;
				default:
					m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					break;
				}
				
			}
			else
			{
				
				System.out.println("Wrong attribute name. used: "+ aCtx.attributeName().getText());
				return;
			}
			
			
		}
		if(requiredCounter!=6)
		{
			System.out.println("Wrong number of required attributes. must be 6: lat, lon, alt, ident, airportTestRadius, trafficScalar");
			return;
		}
		else
		{
		airportAtts.put("AIRPORT"+airportIndex,m);
		}
		
		System.out.println(airportAtts.get(m.get("ident")).keySet());
		System.out.println(airportAtts.get(m.get("ident")).values());
		airportIndex++;
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
		
		fuelIndex++;
	}
	
	@Override public void exitFuelElement(@NotNull XMLParser.FuelElementContext ctx) 
	{ 

		Map<String, String> m = new LinkedHashMap<String, String>();
		
		for(XMLParser.AttributeContext fCtx : ctx.attribute())
		{
			String attName=fCtx.attributeName().getText();
			
			if(fuelAttNames.containsKey(attName))
			{
				switch (attName) {
				case "type":
					String[] typeOptions = {"73","87","100","130","145","MOGAS","JET","JETA","JETA1","JETAP","JETB","JET4","JET5","UNKNOWN"};
					if(!Arrays.asList(typeOptions).contains(fCtx.attributeValue().getText())){
						
						System.out.println("Wrong fuel type: " + fCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(typeOptions));
						return;
						
					}else{
						m.put(fCtx.attributeName().getText(), fCtx.attributeValue().getText());
					}
					
					break;
					
				case "availability":
					String[] availabilityOptions = {"YES","NO","UNKNOWN","PRIOR_REQUEST"};
					if(!Arrays.asList(availabilityOptions).contains(fCtx.attributeValue().getText())){
						
						System.out.println("Wrong fuel availability: " + fCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(availabilityOptions));
						return;
						
					}else{
						m.put(fCtx.attributeName().getText(), fCtx.attributeValue().getText());
					}
					break;

				default:
					System.out.println("Wrong fuel attribute name. use only type or availability.");
					return;
					//break;
				}
			}
			
		}
		
		fuelAtts.put("fuel"+fuelIndex, m);
		airportElems.put("fuel"+fuelIndex, "AIRPORT"+airportIndex);
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
		
		//TODO METER REQUIREDCOUNTER AQUI!
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext tCtx : ctx.attribute())
		{
			
			
			String attName=tCtx.attributeName().getText();
			
			//validar nome de atributo e verificar ordem
			if(towerAttNames.containsKey(attName))
			{
				
				switch (attName) {
				case "lat":
					
					if(tCtx.attributeValue().getText().split("-").length==3)
					{
						String[] attValue = tCtx.attributeValue().getText().split("-");
						
						if(Integer.parseInt(attValue[0])<-90 || Integer.parseInt(attValue[0])>90)
						{
							System.out.println("invalid " + attName + " value : " + attValue[0]);
							return;
						}
						
					}
					
					m.put(tCtx.attributeName().getText(), tCtx.attributeValue().getText());		
					break;
					
				case "lon":
					
					if(tCtx.attributeValue().getText().split("-").length==3)
					{
						String[] attValue = tCtx.attributeValue().getText().split("-");
						
						if(Integer.parseInt(attValue[0])<-180 || Integer.parseInt(attValue[0])>180)
						{
							System.out.println("invalid " + attName + " value : " + attValue[0]);
							return;
						}
						
					}				
					m.put(tCtx.attributeName().getText(), tCtx.attributeValue().getText());
					break;
					
				case "alt":
		
					boolean def = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character altUnits = new Character (tCtx.attributeValue().getText().charAt(tCtx.attributeValue().getText().length()-1));
					if(!altUnits.equals('F') && !altUnits.equals('M'))
					{
							System.out.println("warning: invalid alt units in tower. using default (M)");
							def=true;								
					}
					
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(tCtx.attributeValue().getText().substring(0, tCtx.attributeValue().getText().length()-1)+"M");
						m.put(tCtx.attributeName().getText(), value);
					}
					else
					{
						m.put(tCtx.attributeName().getText(), tCtx.attributeValue().getText());
					}
	
				break;
				
				
				default:
					
					break;
				}
				
			}
			else
			{
				
				System.out.println("Wrong attribute name. used: "+ tCtx.attributeName().getText());
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
		

		int requiredCounter = 0;
		
		Map<String, String> m = new LinkedHashMap<String, String>();
		for(XMLParser.AttributeContext rCtx : ctx.attribute())
		{
			
			String attName=rCtx.attributeName().getText();
			
			//validar nome de atributo
			if(runwayAttNames.containsKey(attName))
			{
				
				switch (attName) {
				case "lat":
					
					if(rCtx.attributeValue().getText().split("-").length==3)
					{
						String[] attValue = rCtx.attributeValue().getText().split("-");
						
						if(Integer.parseInt(attValue[0])<-90 || Integer.parseInt(attValue[0])>90)
						{
							System.out.println("invalid " + attName + " value : " + attValue[0]);
							return;
						}
						
					}
					
					m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					requiredCounter++;
					break;
					
				case "lon":
					
					if(rCtx.attributeValue().getText().split("-").length==3)
					{
						String[] attValue = rCtx.attributeValue().getText().split("-");
						
						if(Integer.parseInt(attValue[0])<-180 || Integer.parseInt(attValue[0])>180)
						{
							System.out.println("invalid " + attName + " value : " + attValue[0]);
							return;
						}
						
					}				
					m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					requiredCounter++;
					break;
					
				case "alt":
		
					boolean def = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character altUnits = new Character (rCtx.attributeValue().getText().charAt(rCtx.attributeValue().getText().length()-1));
					if(!altUnits.equals('F') && !altUnits.equals('M'))
					{
							System.out.println("warning: invalid alt units in runway. using default (M)");
							def=true;								
					}
					
					if(def==true)
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

				case "surface"://req
					String[] surfaceOptions = {"ASPHALT","BITUMINOUS","BRICK","CLAY","CEMENT","CONCRETE","CORAL","DIRT","GRASS","GRAVEL","ICE","MACADAM","OIL_TREATED, PLANKS","SAND","SHALE","SNOW","STEEL_MATS","TARMAC","UNKNOWN","WATER"};
					if(!Arrays.asList(surfaceOptions).contains(rCtx.attributeValue().getText())){
						
						System.out.println("Wrong runway surface: " + rCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(surfaceOptions));
						return;
						
					}else{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;
					
				case "heading"://req
					

						String attValue = rCtx.attributeValue().getText();
						
						if(Float.parseFloat(attValue)<0 || Float.parseFloat(attValue)>360)
						{
							System.out.println("invalid " + attName + " value : " + attValue);
							return;
						}else{
							m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
							requiredCounter++;
						}

					break;
					
					
				case "length"://req
					
					boolean def1 = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character lengthUnits = new Character (rCtx.attributeValue().getText().charAt(rCtx.attributeValue().getText().length()-1));
					if(!lengthUnits.equals('F') && !lengthUnits.equals('M'))
					{
							System.out.println("warning: invalid alt units in runway. using default (M)");
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
							System.out.println("warning: invalid alt units in runway. using default (M)");
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
						
						System.out.println("Wrong runway number: " + rCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(numberOptions) + ",or a int from 00 to 36");
						return;
						
					}else{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;
					
				case "designator":
					String[] designatorOptions = {"NONE","C","CENTER","L","LEFT","R","RIGHT","W","WATER","A","B"};
					if(!Arrays.asList(designatorOptions).contains(rCtx.attributeValue().getText())){
						
						System.out.println("Wrong runway surface: " + rCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(designatorOptions));
						return;
						
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
							System.out.println("warning: invalid alt units in runway. using default (M)");
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

						System.out.println("Wrong runway primaryTakeoff: " + rCtx.attributeValue().getText() + ". Used default 'TRUE'");
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

						System.out.println("Wrong runway primaryLanding: " + rCtx.attributeValue().getText() + ". Used default 'TRUE'");
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

						System.out.println("Wrong runway secondaryTakeoff: " + rCtx.attributeValue().getText() + ". Used default 'TRUE'");
						m.put(rCtx.attributeName().getText(), "TRUE");
					}else{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					}
					break;
					
				case "secondaryLanding":
					String[] secondaryLandingOptions = {"TRUE","FALSE"};
					if(!Arrays.asList(secondaryLandingOptions).contains(rCtx.attributeValue().getText())){

						System.out.println("Wrong runway secondaryLanding: " + rCtx.attributeValue().getText() + ". Used default 'TRUE'");
						m.put(rCtx.attributeName().getText(), "TRUE");
					}else{
						m.put(rCtx.attributeName().getText(), rCtx.attributeValue().getText());
					}
					break;
					
				case "secondaryPattern":
					String[] secondaryPatternOptions = {"LEFT","RIGHT"};
					if(!Arrays.asList(secondaryPatternOptions).contains(rCtx.attributeValue().getText())){

						System.out.println("Wrong runway secondaryPattern: " + rCtx.attributeValue().getText() + ". Used default 'LEFT'");
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
				
				System.out.println("Wrong attribute name. used: "+ rCtx.attributeName().getText());
				return;
			}
			
			
		}
		if(requiredCounter!=8)
		{
			System.out.println("Wrong number of required attributes. must be 8: lat, lon, alt, surface, heading, length, width, number");
			return;
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
				String attValue= mCtx.attributeValue().getText();
				if(!attValue.equals("TRUE") && !attValue.equals("FALSE"))
				{
					System.out.println("Wrong value for attribute " + attValue+ ". must be TRUE or FALSE");
					return;
				}
				else
				{
					requiredCounter++;
				}
			}
			else
			{
			System.out.println("Wrong argument name. used:"+ attName+ ". use "+ markingsAttNames);	
			}
		}
		
		if(requiredCounter!=13)
		{
			System.out.println("Wrong number of arguments in markings");
			return;
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
			
			//validar nome de atributo e verificar ordem
			if(lightsAttNames.contains(attName))
			{
				
				switch (attName) {	
				
				case "center":
					String[] centerOptions = {"NONE","LOW", "MEDIUM", "HIGH"};
					if(!Arrays.asList(centerOptions).contains(lCtx.attributeValue().getText())){

						System.out.println("Wrong lights center: " + lCtx.attributeValue().getText() + ". Used default 'NONE'");
						m.put(lCtx.attributeName().getText(), "NONE");
						requiredCounter++;
					}else{
						m.put(lCtx.attributeName().getText(), lCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;
					
				case "edge":
					String[] edgeOptions = {"NONE","LOW", "MEDIUM", "HIGH"};
					if(!Arrays.asList(edgeOptions).contains(lCtx.attributeValue().getText())){

						System.out.println("Wrong lights edge: " + lCtx.attributeValue().getText() + ". Used default 'NONE'");
						m.put(lCtx.attributeName().getText(), "NONE");
						requiredCounter++;
					}else{
						m.put(lCtx.attributeName().getText(), lCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;
					
				case "centerRed":
					String[] centerRedOptions = {"TRUE", "FALSE"};
					if(!Arrays.asList(centerRedOptions).contains(lCtx.attributeValue().getText())){
						
						System.out.println("Wrong lights centerRed: " + lCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(centerRedOptions));
						return;
						
					}else{
						m.put(lCtx.attributeName().getText(), lCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;
				
				default:
					
					break;
				}
				
			}
			else
			{
				
				System.out.println("Wrong attribute name. used: "+ lCtx.attributeName().getText());
				return;
			}
			
			
		}
		if(requiredCounter!=3)
		{
			System.out.println("Wrong number of required attributes. must be 3: center, edge, centerRed");
			return;
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
			
			//validar nome de atributo e verificar ordem
			if(offsetThresholdAttNames.contains(attName))
			{
				
				switch (attName) {	

				case "end":
					String[] endOptions = {"PRIMARY","SECONDARY"};
					if(!Arrays.asList(endOptions).contains(otCtx.attributeValue().getText())){

						System.out.println("Wrong offsetThreshold end: " + otCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(endOptions));
						return;
					}else{
						m.put(otCtx.attributeName().getText(), otCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;

				case "length"://req
					boolean def = false;
					//System.out.println(aCtx.attributeValue().getText().split(" ").length);
					
					//ultimo char so attvalue
					Character lengthUnits = new Character (otCtx.attributeValue().getText().charAt(otCtx.attributeValue().getText().length()-1));
					if(!lengthUnits.equals('F') && !lengthUnits.equals('M'))
					{
							System.out.println("warning: invalid length units in offsetthreshold. using default (M)");
							def=true;								
					}
					
					if(def==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(otCtx.attributeValue().getText().substring(0, otCtx.attributeValue().getText().length()-1)+"M");
						m.put(otCtx.attributeName().getText(), value);
						requiredCounter++;
					}
					else
					{
						m.put(otCtx.attributeName().getText(), otCtx.attributeValue().getText());
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
							System.out.println("warning: invalid width units in offsetthreshold. using default (M)");
							def1=true;								
					}
					
					if(def1==true)
					{
						//valor antigo com as unidades por defeito M
						String value = new String(otCtx.attributeValue().getText().substring(0, otCtx.attributeValue().getText().length()-1)+"M");
						m.put(otCtx.attributeName().getText(), value);
					}
					else
					{
						m.put(otCtx.attributeName().getText(), otCtx.attributeValue().getText());
					}
					break;

				case "surface":
					String[] surfaceOptions = {"ASPHALT","BITUMINOUS","BRICK","CLAY","CEMENT","CONCRETE","CORAL","DIRT","GRASS","GRAVEL","ICE","MACADAM","OIL_TREATED, PLANKS","SAND","SHALE","SNOW","STEEL_MATS","TARMAC","UNKNOWN","WATER"};
					if(!Arrays.asList(surfaceOptions).contains(otCtx.attributeValue().getText())){

						System.out.println("Wrong offsetThreshold surface: " + otCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(surfaceOptions));
						return;
					}else{
						m.put(otCtx.attributeName().getText(), otCtx.attributeValue().getText());
					}
					break;

				default:
					break;
				}

			}
			else
			{				
				System.out.println("Wrong attribute name. used: "+ otCtx.attributeName().getText());
				return;
			}
			
			
		}
		if(requiredCounter!=2)
		{
			System.out.println("Wrong number of required attributes. must be 2: end, length");
			return;
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
			
			//validar nome de atributo e verificar ordem
			if(approachLighstsAttNames.contains(attName))
			{
				
				switch (attName) {	

				case "end":
					String[] endOptions = {"PRIMARY","SECONDARY"};
					if(!Arrays.asList(endOptions).contains(alCtx.attributeValue().getText())){

						System.out.println("Wrong approachLights end: " + alCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(endOptions));
						return;
					}else{
						m.put(alCtx.attributeName().getText(), alCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;
					
				case "system":
					String[] systemOptions = {"NONE","ALSF1","ALSF2","CALVERT","CALVERT2","MALS","MALSF","MALSR","ODALS","RAIL","SALS","SALSF","SSALF","SSALR","SSALS"};
					if(!Arrays.asList(systemOptions).contains(alCtx.attributeValue().getText())){

						System.out.println("Wrong approachLights system: " + alCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(systemOptions));
						return;
					}else{
						m.put(alCtx.attributeName().getText(), alCtx.attributeValue().getText());
					}
					break;
					
				case "strobes":
					
					if(!(Integer.parseInt(alCtx.attributeValue().getText()) > 0 )){
						System.out.println("Wrong approachLights strobes value: " + alCtx.attributeValue().getText() + ". Put default: 0");
						m.put(alCtx.attributeName().getText(), "0");
					}else{
						m.put(alCtx.attributeName().getText(), alCtx.attributeValue().getText());
					}
					break;
					
				case "reil":
					String[] reilOptions = {"TRUE","FALSE"};
					if(!Arrays.asList(reilOptions).contains(alCtx.attributeValue().getText())){
						System.out.println("Wrong approachLights reil: " + alCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(reilOptions));
						return;
					}else{
						m.put(alCtx.attributeName().getText(), alCtx.attributeValue().getText());
					}
					break;
					
				case "touchdown":
					String[] touchdownOptions = {"TRUE","FALSE"};
					if(!Arrays.asList(touchdownOptions).contains(alCtx.attributeValue().getText())){
						System.out.println("Wrong approachLights touchdown: " + alCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(touchdownOptions));
						return;
					}else{
						m.put(alCtx.attributeName().getText(), alCtx.attributeValue().getText());
					}
					break;
					
				case "endLights":
					String[] endLightsOptions = {"TRUE","FALSE"};
					if(!Arrays.asList(endLightsOptions).contains(alCtx.attributeValue().getText())){
						System.out.println("Wrong approachLights endLights: " + alCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(endLightsOptions));
						return;
					}else{
						m.put(alCtx.attributeName().getText(), alCtx.attributeValue().getText());
					}
					break;
					
					
				default:
					break;
				}

			}
			else
			{				
				System.out.println("Wrong attribute name. used: "+ alCtx.attributeName().getText());
				return;
			}
			
			
		}
		if(requiredCounter!=1)
		{
			System.out.println("Wrong number of required attributes. must be 1: end");
			return;
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
			
			//validar nome de atributo e verificar ordem
			if(vasiAttNames.contains(attName))
			{
				String attVal = vCtx.attributeValue().getText();
				
				switch (attName) {
				case "end":
					if(!attVal.equals("PRIMARY") && !attVal.equals("SECONDARY"))
					{
						System.out.println("Wrong end vaule. must be PRIMARY or SECONDARY");
						return;
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					
					break;
				case "type":
					
					if(!vasiTypeValues.contains(attVal))
					{
						System.out.println("Wrong type vaule. must be one of "+ vasiTypeValues);
						return;
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					
					break;
				case "side":
					if(!attVal.equals("LEFT") && !attVal.equals("RIGHT"))
					{
						System.out.println("Wrong end vaule. must be RIGHT or LEFT");
						return;
					}
					else
					{
						m.put(attName, attVal);
						requiredCounter++;
					}
					
					break;
				case "biasX":
					
					defBiasX=false;
					
					
					//ultimo char so attvalue
					Character biasXUnits = new Character (attVal.charAt(attVal.length()-1));
					if(!biasXUnits.equals('F') && !biasXUnits.equals('M'))
					{
							System.out.println("warning: invalid alt units in airport. using default (M)");
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
							System.out.println("warning: invalid alt units in airport. using default (M)");
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
							System.out.println("warning: invalid alt units in airport. using default (M)");
							defBiasX=true;								
					}
					
					if(Integer.parseInt(attVal)<0)
					{
						System.out.println("Wrong spacing value. value must be greater or equal to 0");
						return;
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
						System.out.println("Wrong value for pitch atribute. mute be <10.0 and >0.0");
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
				System.out.println("Wrong attribute name. used: "+ attName +". use only: " + vasiAttNames);
				return;
			}
		}
		if(requiredCounter!=7)
		{
			System.out.println("Wrong number of atributes. must be 7 valid: "+ vasiAttNames);
		}
		else
		{
		vasiAtts.put("VASI"+actualVasiIndex, m);
		runwayElems.put("VASI"+actualVasiIndex, "RUNWAY"+actualRunwayIndex);
		}
	}
	
	@Override public void enterIlsElement(@NotNull XMLParser.IlsElementContext ctx) { 
		//TODO ver o range: se nao estiver especificado-> colocar 27N como default
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
							System.out.println("invalid " + attName + " value : " + attValue[0]);
							return;
						}
						
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
							System.out.println("invalid " + attName + " value : " + attValue[0]);
							return;
						}
						
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
							System.out.println("warning: invalid alt units in ils. using default (M)");
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
							System.out.println("invalid " + attName + " value : " + attValue);
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
						System.out.println("invalid " + attName + " value : " + freqValue);
						return;
					}else{
						m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
						requiredCounter++;
					}					
					break;
					
				case "end":
					String[] endOptions = {"PRIMARY","SECONDARY"};
					if(!Arrays.asList(endOptions).contains(ilsCtx.attributeValue().getText())){

						System.out.println("Wrong ils end: " + ilsCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(endOptions));
						return;
					}else{
						m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
						requiredCounter++;
					}
					break;
					
				case "range":
					break;//TODO falta fazer
					
				case "magvar":
					
					String magvarValue = ilsCtx.attributeValue().getText();
					
					if(Float.parseFloat(magvarValue)<-360.0 || Float.parseFloat(magvarValue)>360)
					{
						System.out.println("invalid " + attName + " value : " + magvarValue + ". Must be -360 to 360. Put default: 0.0");
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
						System.out.println("ILS ident value too long: " + identValue + ". Max 4 Chars!");
						return;
					}
					m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
					requiredCounter++;
					break;
					
				case "width":
					String widthValue = ilsCtx.attributeValue().getText();
					
					if(Float.parseFloat(widthValue)<0 || Float.parseFloat(widthValue)>360)
					{
						System.out.println("invalid " + attName + " value : " + widthValue + ". Must be 0 to 360.");
						return;
					}else{
						m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
					}					
					break;
					
				case "name":
					if(ilsCtx.attributeValue().getText().length()>48)
					{
						String nameValue= ilsCtx.attributeValue().getText();
						System.out.println("ILS ident value too long: " + nameValue + ". Max 48 Chars!");
						return;
					}
					m.put(ilsCtx.attributeName().getText(), ilsCtx.attributeValue().getText());
					break;
					
				case "backCourse":
					String[] backCourseOptions = {"TRUE","FALSE"};
					if(!Arrays.asList(backCourseOptions).contains(ilsCtx.attributeValue().getText())){

						System.out.println("Wrong ils backCourse: " + ilsCtx.attributeValue().getText() + ". Expected: " + Arrays.toString(backCourseOptions));
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

				System.out.println("Wrong attribute name. used: "+ ilsCtx.attributeName().getText());
				return;
			}


		}
		if(requiredCounter!=8)
		{
			System.out.println("Wrong number of required attributes. must be 8: lat, lon, alt, heading, frequency, end, magvar, ident");
			return;
		}

		ilsAtts.put("ILS"+actualIlsIndex, m);
		airportElems.put("ILS"+actualIlsIndex, "AIRPORT"+actualAirportIndex);

	}
	
	@Override public void enterIlsElements(@NotNull XMLParser.IlsElementsContext ctx) { }
	
	@Override public void exitIlsElements(@NotNull XMLParser.IlsElementsContext ctx) { }
}
