grammar calcGrammar;
EQUAL: '=';
NLINE: '\n';
INT: [0-9]+;
DOUBLE: [0-9]+ '.' [0-9]+
    | '.'[0-9]+;
ADD: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
ID: [a-z_A-Z_]*[a-z_A-Z_0-9]+;
WSPACE: [ \t\r] -> skip;
LPAR: '(';
RPAR: ')';
start:
    ID EQUAL NLINE       #Assign
    | addSub NLINE? EOF  #Calculate
    ;

addSub:
    addSub ADD multDiv      #Add
    | addSub MINUS multDiv  #Subtract
    | multDiv               #ToMultiplyorDivide
    ;
multDiv:
    multDiv MULT type   #Multiply
    | multDiv DIV type  #Divide
    | type              #DataType
    ;
type:
    INT             #Integer
    | DOUBLE        #Double
    | ID            #Variable
    | '(' addSub ')'#Parenthesis
    ;
