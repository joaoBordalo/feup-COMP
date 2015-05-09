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
COM: 'Com';
RUNWAY:'Runway';
RUNWAYALIAS:'RunwayAlias'; //? para que é este?

APPROACH: 'Approach';
APPROACHLEGS: 'ApproachLegs';
LEG: 'Leg';
MISSEDAPPROACHLEGS: 'MissedApproachLegs';
TRANSITION: 'Transition';
TRANSITIONLEGS: 'TransitionLegs';

WAYPOINT:'Waypoint';
HELIPAD:'Helipad';
START:'Start';
JETWAY:'Jetway';
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
APRONS: ('<Aprons>' .*? '</Aprons>') ->skip;
APRONEDGELIGHTS: ('<ApronEdgeLights>' .*? '</ApronEdgeLights>') ->skip;
TAXIWAYSIGN:('<TaxiwaySign' .*? '/>') ->skip;
BOUNDARYFENCE: ('<BoundaryFence' .*? '>' .*? '</BoundaryFence>')->skip;

//APPROACH: ('<Approach ' .* '>' .*? '</Approach>') ->skip;

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


