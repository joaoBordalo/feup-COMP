lexer grammar XMLLexer;


//NWS : [' ']+;
WS  :
       (' '|'\r'|'\t'|'\u000C'|'\n') ->skip;



COMMENT: '<!--' .*? '-->';
TAG_START_OPEN : '<' ;
TAG_END_OPEN : '</' ;
TAG_CLOSE :  '>' ;
TAG_EMPTY_CLOSE : '/>' ;




AIRPORT: 'Airport';
TAXIWAYPOINT:'TaxiwayPoint';
TAXIWAYPARKING:'TaxiwayParking';
TAXINAME:'TaxiName';
TAXIWAYPATH:'TaxiwayPath';
TOWER:'Tower';
SERVICES:'Services';
RUNWAY:'Runway';
RUNWAYALIAS:'RunwayAlias'; //? para que é este?

APPROACHLEGS: 'ApproachLegs';
LEG: 'Leg';
MISSEDAPPROACHLEGS: 'MissedApproachLegs';
TRANSITION: 'Transition';
TRANSITIONLEGS: 'TransitionLegs';

HELIPAD:'Helipad';
FUEL:'Fuel';
MARKINGS:'Markings';
LIGHTS:'Lights';
OFFSETTHRESHOLD:'OffsetThreshold';
APPROACHLIGHTS:'ApproachLights';
VASI:'Vasi';
ILS:'Ils';
GLIDESLOPE:'GlideSlope';
DME:'Dme';
ROUTE:'Route';
NEXT:'Next';
PREVIOUS:'Previous';
SCENERYOBJECT:'SceneryObject';
LIBRARYOBJECT:'LibraryObject';

//Ignore:
APPROACH: ('<Approach' .*? '>' .*? '</Approach>')->skip; //new
APRONS: ('<Aprons>' .*? '</Aprons>') ->skip;
JETWAY:('<Jetway>' .*? '</Jetway>') ->skip; //new
APRONEDGELIGHTS: ('<ApronEdgeLights>' .*? '</ApronEdgeLights>') ->skip;
TAXIWAYSIGN:('<TaxiwaySign' .*? '/>') ->skip;
BOUNDARYFENCE: ('<BoundaryFence' .*? '>' .*? '</BoundaryFence>')->skip;
WAYPOINT:('<Waypoint' .*? '>' .*? '</Waypoint>')->skip; //new
NDB: ('<Ndb' .*? '>' .*? '</Ndb>')->skip; //new
START:('<Start' .*? '/>')->skip; //new
BLASTFENCE: ('<BlastFence' .*? '>' .*? '</BlastFence>')->skip; //new
DELETEAIRPORT: ('<DeleteAirport' .*? '>' .*? '</DeleteAirport>')->skip;//new
COM: ('<Com' .*? '/>')->skip; //new


fragment DIGIT: [0-9] ;
fragment CHAR: [a-zA-Z];



STRING: (DIGIT | CHAR | SPECIAL_CHAR )+;

SPECIAL_CHAR:   '/' |
                '_' |
                '[' |
                ']' |
                '&' |
                '|' |
                ';' |
                '{' |
                '}' |
                '\\'|
                '`' |
                '-' |
                '+' |
                '.';
EQUALS: '=';
QUOTES: '"';
OP: ('+' | '-');
DISTANCE: (FLOAT | INT) [mM];

INT: [0-9]+;
FLOAT: [0-9]* '.' [0-9]+;



/*TAG
    :   '<' (~'>')* '>'
    ;*/


