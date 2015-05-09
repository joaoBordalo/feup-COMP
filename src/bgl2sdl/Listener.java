package bgl2sdl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;

import gen.*;

public class Listener extends XMLParserBaseListener {
	XMLParser parser;
	List<Map<String,String>> airportAtts = new ArrayList<Map<String, String>>();
	Map<String, Integer> airportAttNames= new HashMap<String, Integer>();
	int airportIndex = 0;
	
	
	
	
	public Listener(XMLParser parser)
	{
		this.parser= parser;
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
		airportAttNames.put("trafficScaler", 12);
		
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
		System.out.println("entrei no documento!!");
	}
	
	
	
	@Override 
	public void enterAirportElement(@NotNull XMLParser.AirportElementContext ctx) 
	{ 
		/*
		 * airport atributes:
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
		 * airportTestRadius+ float (m|f|n)
		 * traficScaler+ float 0.01<= traficScaler<=1
		 */	
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
					}
					else
					{
						m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
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
					
				break;
					
				case "airportTestRadius":
					
					//ir buscar ultimo char
					Character testRadiusUnits = new Character(aCtx.attributeValue().getText().charAt(aCtx.attributeValue().getText().length()-1));
					if(!testRadiusUnits.equals('M') && !testRadiusUnits.equals('G') && !testRadiusUnits.equals('N'))
					{
						System.out.println("Wrong ariportTestRadius format. use <float(M|G|N)>");
						return;
					}
					else {
						m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
					}
					
					
					
					break;
					
				case "trafficScaler":
					
					if(Float.parseFloat(aCtx.attributeValue().getText())<0.01 ||
							Float.parseFloat(aCtx.attributeValue().getText())>1.00)
					{
						System.out.println("Wrong airport trafficScaler value. used: "+ aCtx.attributeValue().getText()
											+ "expecting 0.1<=X<=1.0");
						return;
					}
					else
					{
						m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
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
		airportAtts.add(m);
		
		
		System.out.println(airportAtts.get(airportIndex).keySet());
		System.out.println(airportAtts.get(airportIndex).values());
		airportIndex++;
	}

}
