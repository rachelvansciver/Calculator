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
ID: [a-z_A-Z_0-9]+;
WSPACE: [ \t\r] -> skip;
LPAR: '(';
RPAR: ')';
EXP: '^';
start:
    ID EQUAL NLINE        #Assign
    | addSub NLINE? EOF   #Calculate
    ;

addSub:
    addSub ADD multDiv      #Add
    | addSub MINUS multDiv  #Subtract
    | multDiv               #ToMultiplyorDivide
    ;
multDiv:
    multDiv MULT pow       #Multiply
    | multDiv DIV pow      #Divide
    | pow                   #Exponent
    ;
pow:
    negative (EXP pow)?    #RaisetoPower
    ;

negative:
    MINUS negative         #TimesNegativeOne
    | type                 #ToDataType
    ;
type:
    INT                 #Integer
    | DOUBLE            #Double
    | ID                #Variable
    | '(' addSub ')'    #Parenthesis
    ;
