parser grammar XMLParser;

options {      tokenVocab=XMLLexer; }


//cenas: O_AIRPORT C_AIRPORT;
document: xmlItems+ ;
xmlItems: airportElement | COMMENT;

tagName: STRING;
attribute: STRING EQUALS QUOTES attributeValue QUOTES;
attributeValue: OP? INT | OP? FLOAT | DISTANCE | STRING*;





airportElement: O_AIRPORT (airportElements | TAG )* C_AIRPORT;

airportElements: servicesElement | towerElement;

towerElement: O_TOWER attribute* C_TOWER;

servicesElement :O_SERVICES (servicesElements)* C_SERVICES;

fuelElement: O_FUEL? attribute+ C_FUEL;

servicesElements: fuelElement;














            /*(O_AIRPORT |
             O_TAXIWAYPOINT|
             O_TAXIPARKING|
             O_TAXINAME|
             O_TAXIWAYPATH|
             O_TOWER|
             O_SERVICES|
             O_RUNWAY|
             O_RUNWAYALIAS|
             O_TAXIWAYSIGN|
             O_WAYPOINT|
             O_HELIPAD|
             O_START|
             O_JETWAY)

            (xmlElement | TAG )*

            (C_AIRPORT |
             C_TAXIWAYPOINT|
             C_TAXIPARKING|
             C_TAXINAME|
             C_TAXIWAYPATH|
             C_TOWER|
             C_SERVICES|
             C_RUNWAY|
             C_RUNWAYALIAS|
             C_TAXIWAYSIGN|
             C_WAYPOINT|
             C_HELIPAD|
             C_START|
             C_JETWAY);*/

//Parser init
start: document EOF;