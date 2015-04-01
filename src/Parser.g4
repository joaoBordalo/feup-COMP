grammar Parser;

options{
language=Java;
}

INT: [0-9]+;

HELLO: 'HELLO';

start: HELLO cenas HELLO;

cenas: HELLO | INT;

