package bgl2sdl;

import java.util.Map;

import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.misc.NotNull;

import gen.*;

public class Listener extends XMLParserBaseListener {
	XMLParser parser;
	Map<String,String> AirportAtts = new OrderedHashMap<String, String>();
	
	
	public Listener(XMLParser parser)
	{
		this.parser= parser;
	}
	@Override public void exitAttribute(@NotNull XMLParser.AttributeContext ctx) 
	{ 
		String attName=ctx.attributeName().getText();
		String attValue = ctx.attributeValue().getText();
		AirportAtts.put(attName, attValue);
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
		
	}
	
	@Override 
	public void exitAirportElement(@NotNull XMLParser.AirportElementContext ctx) 
	{ 
		
		
	}

}
