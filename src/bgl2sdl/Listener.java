package bgl2sdl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.misc.NotNull;

import gen.*;

public class Listener extends XMLParserBaseListener {
	XMLParser parser;
	List<Map<String,String>> AirportAtts = new ArrayList<Map<String, String>>();
	int airportIndex = 0;
	
	
	public Listener(XMLParser parser)
	{
		this.parser= parser;
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
		//AirportAtts.get(airportIndex) = new Map<String, String>();
		for(XMLParser.AttributeContext aCtx : ctx.attribute())
		{
			Map<String, String> m = new LinkedHashMap<String, String>();
			m.put(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
			//Map temp = new Map<String, String>(aCtx.attributeName().getText(), aCtx.attributeValue().getText());
			AirportAtts.add(m);
		}
	}
	
	@Override 
	public void exitAirportElement(@NotNull XMLParser.AirportElementContext ctx) 
	{ 
		
		System.out.println(AirportAtts.get(airportIndex).keySet());
		System.out.println(AirportAtts.get(airportIndex).values());
		airportIndex++;
	}

}
