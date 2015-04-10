lexer grammar XMLLexer;




WS: [\t\n\r]+ -> skip;


TAG_OPEN: '<';
TAG_OPEN_SLASH: '</';
TAG_CLOSE: '>';
TAG_CLOSE_SLASH: '/>';
INT: [0-9]+;
FLOAT: [0-9]* '.' [0-9]+;
EQUALS: '=';
STRING: [a-zA-Z]+;
QUOTES: '"';
OP: ('+' | '-');
DISTANCE: (FLOAT | INT) [mM];







O_AIRPORT :'<Airport>';
O_TAXIWAYPOINT:'<TaxiwayPoint>';
O_TAXIPARKING:'<TaxiwayParking>';
O_TAXINAME:'<TaxiName>';
O_TAXIWAYPATH:'<TaxiwayPath>';
O_TOWER:'<Tower>';
O_SERVICES:'<Services>';
O_RUNWAY:'<Runway>';
O_RUNWAYALIAS:'<RunwayAlias>';
O_TAXIWAYSIGN:'<TaxiwaySign>';
O_WAYPOINT:'<Waypoint>';
O_HELIPAD:'<Helipad>';
O_START:'<Start>';
O_JETWAY:'<Jetway>';
O_FUEL:'<Fuel';


C_AIRPORT :'</Airport>';
C_TAXIWAYPOINT:'</TaxiwayPoint>';
C_TAXIPARKING:'</TaxiwayParking>';
C_TAXINAME:'</TaxiName>';
C_TAXIWAYPATH:'</TaxiwayPath>';
C_TOWER:'</Tower>';
C_SERVICES:'</Services>';
C_RUNWAY:'</Runway>';
C_RUNWAYALIAS:'</RunwayAlias>';
C_TAXIWAYSIGN:'</TaxiwaySign>';
C_WAYPOINT:'</Waypoint>';
C_HELIPAD:'</Helipad>';
C_START:'</Start>';
C_JETWAY:'</Jetway>';
C_FUEL:'/>';

TAG
    :   '<' (~'>')* '>'
    ;


COMMENT: '<!--' .*? '-->';


