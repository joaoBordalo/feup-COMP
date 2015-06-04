package bgl2sdl;

import gen.XMLParser;
import gen.XMLParserBaseListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import org.antlr.v4.runtime.misc.NotNull;

public class AttributesChecker {
	
	static String[] typefuel(XMLParser.AttributeContext patt)
	{
		    
			String[] typeOptions = {"73","87","100","130","145","MOGAS","JET","JETA","JETA1","JETAP","JETB","JET4","JET5","UNKNOWN"};
			if(!Arrays.asList(typeOptions).contains(patt.attributeValue().getText())){
				
				System.out.println("Line "+patt.getStart().getLine()+": Wrong fuel type: " + patt.attributeValue().getText() + ". Expected: " + Arrays.toString(typeOptions));
				return null;
				
			}
			else
			{
				
				 return new String[]{ patt.attributeName().getText(), patt.attributeValue().getText() };
			}
		
		
	}

	static String[] availability(XMLParser.AttributeContext patt)
	{
		    
		String[] availabilityOptions = {"YES","NO","UNKNOWN","PRIOR_REQUEST"};
		if(!Arrays.asList(availabilityOptions).contains(patt.attributeValue().getText()))
		{
			
			System.out.println("Line "+patt.getStart().getLine()+": Wrong fuel availability: " + patt.attributeValue().getText() + ". Expected: " + Arrays.toString(availabilityOptions));
			return null;
			
		}
		else
		{
			 return new String[]{ patt.attributeName().getText(), patt.attributeValue().getText() };
		}
		
	}
	
	static boolean lat(XMLParser.AttributeContext patt)

	{
		String attName=patt.attributeName().getText();
		
		if(patt.attributeValue().getText().split("-").length==3)
		{
			String[] attValue = patt.attributeValue().getText().split("-");
			
			
			if(Float.parseFloat(attValue[0])<-90 || Float.parseFloat(attValue[0])>90)
			{
				System.out.println("Line "+patt.getStart().getLine()+": invalid " + attName + " value : " + attValue[0]);
				return false;
			}
			
		}
		
		try
		{
		if(Float.parseFloat(patt.attributeValue().getText())<-90.0 || Float.parseFloat(patt.attributeValue().getText())>90.0)
		{
			System.out.println("Line "+patt.getStart().getLine()+": invalid " + attName + " value : " + patt.attributeValue().getText());
			return false;
		}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Line "+patt.getStart().getLine()+": invalid " + attName + " value : " + patt.attributeValue().getText());
			return false;
		}
		
		return true;
		
	}
	
	static boolean lon(XMLParser.AttributeContext patt)

	
	{
		String attName=patt.attributeName().getText();
		
		if(patt.attributeValue().getText().split("-").length==3)
		{
			String[] attValue = patt.attributeValue().getText().split("-");
			
			
			if(Float.parseFloat(attValue[0])<-180 || Float.parseFloat(attValue[0])>180)
			{
				System.out.println("Line "+patt.getStart().getLine()+": invalid " + attName + " value : " + attValue[0]);
				return false;
			}
			
		}
		
		try
		{
		if(Float.parseFloat(patt.attributeValue().getText())<-180.0 || Float.parseFloat(patt.attributeValue().getText())>180.0)
		{
			System.out.println("Line "+patt.getStart().getLine()+": invalid " + attName + " value : " + patt.attributeValue().getText());
			return false;
		}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Line "+patt.getStart().getLine()+": invalid " + attName + " value : " + patt.attributeValue().getText());
			return false;
		}
		
		return  true;
		
	}

	static String alt(XMLParser.AttributeContext patt)


	{
		boolean def = false;
		
		//ultimo char so attvalue
		Character altUnits = new Character (patt.attributeValue().getText().charAt(patt.attributeValue().getText().length()-1));
		if(!altUnits.equals('F') && !altUnits.equals('M'))
		{
				System.out.println("Line "+patt.getStart().getLine()+": warning: invalid alt units in airport. using default (M)");
				def=true;								
		}
		
		if(def==true)
		{
			//valor antigo com as unidades por defeito M
			String value = new String(patt.attributeValue().getText().substring(0, patt.attributeValue().getText().length()-1)+"M");
			return value;
			
		}
		else
		{
			return patt.attributeValue().getText();
			
		}
		
		
		
	}

	static boolean ident(XMLParser.AttributeContext patt)
	{
		if(patt.attributeValue().getText().length()>4)
		{
			String attValue= patt.attributeValue().getText();
			System.out.println("Line "+patt.getStart().getLine()+": Airport ident value too long: " + attValue + ". Max 4 Chars!");
			return false;
		}
		return true;
		
	}

	static boolean atr(XMLParser.AttributeContext patt)
	{
		Character testRadiusUnits = new Character(patt.attributeValue().getText().charAt(patt.attributeValue().getText().length()-1));
		if(!testRadiusUnits.equals('M') && !testRadiusUnits.equals('G') && !testRadiusUnits.equals('N'))
		{
			System.out.println("Line "+patt.getStart().getLine()+": Wrong ariportTestRadius format: " + testRadiusUnits +". Use <float(M|G|N)>");
			return false;
		}
		else {
			return true;
		}
		
	}

	static boolean trafficscalar(XMLParser.AttributeContext patt)
	{
		
		if(Float.parseFloat(patt.attributeValue().getText())<0.01 ||
				Float.parseFloat(patt.attributeValue().getText())>1.00)
		{
			System.out.println("Line "+patt.getStart().getLine()+": Wrong airport trafficScalar value. used: "+ patt.attributeValue().getText()
								+ "expecting 0.1<=X<=1.0");
			return false;
		}
		else
		{
			return true;
			
		}
	}

	static boolean magvar(XMLParser.AttributeContext patt)
	{
		String attName=patt.attributeName().getText();
		try
		{
		if(Float.parseFloat(patt.attributeValue().getText())<-360.0 || Float.parseFloat(patt.attributeValue().getText())>360.0)
		{
			System.out.println("Line "+patt.getStart().getLine()+": invalid " + attName + " value : " + patt.attributeValue().getText());
			return false;
		}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Line "+patt.getStart().getLine()+": invalid " + attName + " value : " + patt.attributeValue().getText());
			return false;
		}
		
		return  true;
	}
	
	static boolean stringchecker(XMLParser.AttributeContext patt)
	{
		if(patt.attributeValue().getText().length()>48)
		{
			String attValue= patt.attributeValue().getText();
			System.out.println("Line "+patt.getStart().getLine()+": Airport "+patt.attributeName().getText()+" value too long: " + attValue + ". Max 48 Chars!");
			return false;
		}
		return true;
		
	}

	static boolean rwsurface(XMLParser.AttributeContext patt)
	{
		String[] surfaceOptions = {"ASPHALT","BITUMINOUS","BRICK","CLAY","CEMENT","CONCRETE","CORAL","DIRT","GRASS","GRAVEL","ICE","MACADAM","OIL_TREATED, PLANKS","SAND","SHALE","SNOW","STEEL_MATS","TARMAC","UNKNOWN","WATER"};
		if(!Arrays.asList(surfaceOptions).contains(patt.attributeValue().getText())){
			
			System.out.println("Line "+patt.getStart().getLine()+": Wrong runway surface: " + patt.attributeValue().getText() + ". Expected: " + Arrays.toString(surfaceOptions));
			return false;
			
		}else{
			return true;
		}
		
	}
	
	static boolean heading(XMLParser.AttributeContext patt)
	{
		String attValue = patt.attributeValue().getText();
		String attName=patt.attributeName().getText();
		
		if(Float.parseFloat(attValue)<0 || Float.parseFloat(attValue)>360)
		{
			System.out.println("Line "+patt.getStart().getLine()+": invalid " + attName + " value : " + attValue);
			return false;
		}
		else
		{
			return true;
		}
		
	}

	static boolean number(XMLParser.AttributeContext patt)
	{
		String[] numberOptions = {"EAST","NORTH","NORTHEAST","NORTHWEST","SOUTH","SOUTHEAST","SOUTHWEST","WEST"};


		if(!Arrays.asList(numberOptions).contains(patt.attributeValue().getText()) &&
				(Integer.parseInt(patt.attributeValue().getText()) < 00 || Integer.parseInt(patt.attributeValue().getText()) > 36)){
			
			System.out.println("Line "+patt.getStart().getLine()+": Wrong runway number: " + patt.attributeValue().getText() + ". Expected: " + Arrays.toString(numberOptions) + ",or a int from 00 to 36");
			return false;
			
		}else{
			return true;
		}
	}

	static boolean designator(XMLParser.AttributeContext patt)
	{
		String[] designatorOptions = {"NONE","C","CENTER","L","LEFT","R","RIGHT","W","WATER","A","B"};
		if(!Arrays.asList(designatorOptions).contains(patt.attributeValue().getText())){
			
			System.out.println("Line "+patt.getStart().getLine()+": Wrong runway surface: " + patt.attributeValue().getText() + ". Expected: " + Arrays.toString(designatorOptions));
			return false;
			
		}else
		{
			
		return true;	
		}
		
	}

	static String trueorfalse(XMLParser.AttributeContext patt)
	{
		String[] primaryTakeoffOptions = {"TRUE","YES","FALSE","NO"};
		if(!Arrays.asList(primaryTakeoffOptions).contains(patt.attributeValue().getText())){

			System.out.println("Line "+patt.getStart().getLine()+": Wrong runway primaryTakeoff: " + patt.attributeValue().getText() + ". Used default 'TRUE'");
			return "TRUE";
		}else{
			if(patt.attributeValue().getText().equals("TRUE") || patt.attributeValue().getText().equals("YES")){
				return "TRUE";
			}else{
				return "FALSE";
			}
		}
	}

	static String leftorright(XMLParser.AttributeContext patt)
	{
		String[] primaryPatternOptions = {"LEFT","RIGHT"};
		if(!Arrays.asList(primaryPatternOptions).contains(patt.attributeValue().getText())){

			System.out.println("Wrong runway primaryPattern: " + patt.attributeValue().getText() + ". Used default 'LEFT'");
			return "LEFT";
		}else{
			return patt.attributeValue().getText() ;
		}
	}

	static String markingbias(XMLParser.AttributeContext patt)


	{
		boolean def = false;
		
		//ultimo char so attvalue
		Character altUnits = new Character (patt.attributeValue().getText().charAt(patt.attributeValue().getText().length()-1));
		if(!altUnits.equals('F') && !altUnits.equals('M') && !altUnits.equals('N'))
		{
				System.out.println("Line "+patt.getStart().getLine()+": warning: invalid MarkingBias units in Runway. using default (M)");
				def=true;								
		}
		
		if(def==true)
		{
			//valor antigo com as unidades por defeito M
			String value = new String(patt.attributeValue().getText().substring(0, patt.attributeValue().getText().length()-1)+"M");
			return value;
			
		}
		else
		{
			return patt.attributeValue().getText();
			
		}
		
		
		
	}

	static String lights(XMLParser.AttributeContext patt)
	{
boolean def = false;
		
		//ultimo char so attvalue
		String attValue= patt.attributeValue().getText();
		if(!attValue.equals("LOW") && !attValue.equals("MEDIUM") && !attValue.equals("HIGH") && !attValue.equals("NONE"))
		{
				System.out.println("Line "+patt.getStart().getLine()+": warning: invalid  units in lights . using default (NONE)");
				def=true;								
		}
		
		if(def==true)
		{
			//valor antigo com as unidades por defeito M
			
			return "NONE";
			
		}
		else
		{
			return patt.attributeValue().getText();
			
		}
	}
	
	 static boolean end(XMLParser.AttributeContext patt)
	    {
		 String[] endOptions = {"PRIMARY","SECONDARY"};
			if(!Arrays.asList(endOptions).contains(patt.attributeValue().getText())){

				System.out.println("Line "+patt.getStart().getLine()+": Wrong offsetThreshold end: " + patt.attributeValue().getText() + ". Expected: " + Arrays.toString(endOptions));
				return false;
			}else{
				return true;
			}
	    }
	 
		static boolean otsurface(XMLParser.AttributeContext patt)
		{
			String[] surfaceOptions = {"ASPHALT","BITUMINOUS","BRICK","CLAY","CEMENT","CONCRETE","CORAL","DIRT","GRASS","GRAVEL","ICE","MACADAM","OIL_TREATED, PLANKS","SAND","SHALE","SNOW","STEEL_MATS","TARMAC","UNKNOWN","WATER"};
			if(!Arrays.asList(surfaceOptions).contains(patt.attributeValue().getText())){
				
				System.out.println("Line "+patt.getStart().getLine()+": Wrong runway surface: " + patt.attributeValue().getText() + ". Expected: " + Arrays.toString(surfaceOptions));
				return false;
				
			}else{
				return true;
			}
			
		}
		
		static boolean system(XMLParser.AttributeContext patt)
		{
			String[] systemOptions = {"NONE","ALSF1","ALSF2","CALVERT","CALVERT2","MALS","MALSF","MALSR","ODALS","RAIL","SALS","SALSF","SSALF","SSALR","SSALS"};
			if(!Arrays.asList(systemOptions).contains(patt.attributeValue().getText())){

				System.out.println("Line "+patt.getStart().getLine()+": Wrong approachLights system: " + patt.attributeValue().getText() + ". Expected: " + Arrays.toString(systemOptions));
				return false;
			}else{
				return true;
			}
			
		}	
		
		static String integerchecker(XMLParser.AttributeContext patt)
		{
			if(!(Integer.parseInt(patt.attributeValue().getText()) < 0 )){
				System.out.println("Line "+patt.getStart().getLine()+": Wrong approachLights strobes value: " + patt.attributeValue().getText() + ". Put default: 0");
				return "0";
			}else{
				return patt.attributeValue().getText();
			}
		}	
		
		static boolean positive(XMLParser.AttributeContext patt)
		{
			if(!(Integer.parseInt(patt.attributeValue().getText()) <= 0 )){
				System.out.println("Line "+patt.getStart().getLine()+": Wrong value. Must be positive " + patt.attributeValue().getText() );
				return false;
			}else{
				
				return true;
			}
		}	
		
		static boolean pitch(XMLParser.AttributeContext patt)
		
		{
			
			String attVal = patt.attributeValue().getText();
			
			if(Float.parseFloat(attVal)<0.0 || Float.parseFloat(attVal)>10.0)
			{
				System.out.println("Line "+patt.getStart().getLine()+": Wrong value for pitch atribute. mute be <10.0 and >0.0");
				return false;
			}
			else
			{
				return true;
			}
		}	
		
		
		static String rangeGS(XMLParser.AttributeContext patt)
		{
			boolean def = false;
			
			//ultimo char so attvalue
			Character altUnits = new Character (patt.attributeValue().getText().charAt(patt.attributeValue().getText().length()-1));
			if(!altUnits.equals('N'))
			{
					System.out.println("Line "+patt.getStart().getLine()+": warning: invalid alt units in GlideSlope. using default (27)");
					def=true;								
			}
			
			if(def==true)
			{
				//valor antigo com as unidades por defeito M
				return "27N";
				
			}
			else
			{
				return patt.attributeValue().getText();
			}
				
			
		}
}

   

