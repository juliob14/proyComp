
package compproy;

import java.util.StringTokenizer;

/**
 * Prog ---- conjProd
Conjprod----ConjProd
                  |Prod
Prod--------variable DEF Expr;
Expr-----Expr ALT term

Term----term &factor
               |Factor

Factor {Expr} | [Expr] | primario

Primario---- Expr
                     |Variable
                     |Terminal

 *
 * @author JULIO
 */
public class comSint {
    private static final int FIN_SENT = ';';
    private static final int CONCA = '&';
    private static final int ALTER = '|';
    private static final int DEF = '=';
    private static final int CERRUNO = '';
    private static final int PAR_DER = ')';
    private static final int PAR_IZQ = '(';
    private static final int NUMERO = 600;
    private static final int ID = 700;
    private static final int EOF = '.';
    
    
    private Integer linea = 1; 
    private StringTokenizer tokenizer = null;
    private Token currentToken;
    private String salida = "";
   
    
    public void paser(){
        this.currentToken = lex();
        sent();
        if(this.currentToken.getToken() == EOF){
            System.out.println(String.format("El resultado : %s", this.salida));
        }
    }
    public void sent(){
        expr();
        if(!(this.currentToken.getToken() == FIN_SENT)){
            throw new Error(String.format("\nError de sintaxis: se esperaba '%s'",
                    (char)FIN_SENT));
          }
    }
    public void expr(){
        term();
        while (this.currentToken.getToken() == SUMA || 
                this.currentToken.getToken() == RESTA){
            if(this.currentToken.getToken()== SUMA){
                this.currentToken = lex();
                term();
                if(!esnumeroId(this.currentToken)){
                    throw new Error("Error de Sintaxis:Se esperaba un numero o ID");
                }
                this.salida = String.format("%s%s", this.salida,(char)SUMA);
                }else if(this.currentToken.getToken()== RESTA){
                    this.currentToken = lex();
                    term();
                    if(!esnumeroId(this.currentToken)){
                    throw new Error("Error de Sintaxis:Se esperaba un numero o ID");
                }
                    this.salida = String.format("%s%s", this.salida,(char)RESTA);
                    
                }
        }
        
    }
    public void term(){
        factor();
        this.currentToken = lex();
        while(this.currentToken.getToken() == DIVISION ||
                this.currentToken.getToken() == MULTIPLICACION){
            if(this.currentToken.getToken() == DIVISION){
                this.currentToken = lex();
                factor();
                if(!esnumeroId(this.currentToken)){
                    throw new Error("Error de Sintaxis:Se esperaba un numero o ID");
                }
                this.currentToken = lex();
                this.salida = String.format("%s%s", this.salida,
                        (char)DIVISION);
                
            }else if(this.currentToken.getToken() == MULTIPLICACION){
                this.currentToken = lex();
                factor();
                if(!esnumeroId(this.currentToken)){
                    throw new Error("Error de Sintaxis:Se esperaba un numero o ID");
                }
                this.currentToken = lex();    
                this.salida = String.format("%s%s", this.salida,
                        (char)MULTIPLICACION);
                }
        
        }
        
    }
    public void factor(){
        if(this.currentToken.getToken() == ID){
            this.salida = String.format("%s%s", this.salida,
                        this.currentToken.getLexema());
            
        }else if(this.currentToken.getToken() == NUMERO){
            this.salida = String.format("%s%s", this.salida,
                        this.currentToken.getLexema());
            
        }else if(this.currentToken.getToken() == PAR_DER){
            this.currentToken = lex();
            expr();
            if(!(this.currentToken.getToken() == PAR_IZQ)){
                throw new Error(String.format("\nError de Sintaxis: Se esperaba %s%s", (char)PAR_IZQ));
            }
        }
        
    }
    private Boolean esnumeroId(Token token){
        return token.getToken() == ID || token.getToken() == NUMERO;
    }
    private StringTokenizer getTokenizer(String codigoFuente){
        if(this.tokenizer == null){
            String alfabeto = String.format("%s%s%s%s%s%s",
                    (char)SUMA,(char) RESTA,(char)MULTIPLICACION,
                    (char)DIVISION,(char)PAR_DER,(char)PAR_IZQ);
            this.tokenizer = new StringTokenizer(
                    codigoFuente.trim(),alfabeto,true);
        }
       
        return this.tokenizer;
        
    }
    
    private Token lex(){
        Token token = null;
        if (this.getTokenizer("").hasMoreTokens()){
            
        String currentToken = this.getTokenizer("").nextToken();
        if(esNumero(currentToken)){
            token = new Token(this.linea,NUMERO,currentToken);
        }else if (esIdentificador(currentToken)){
            token = new Token(this.linea,ID,currentToken);
        }else{
              int tokenSimple = currentToken.charAt(0);
            switch (tokenSimple) {
                case FIN_SENT:
                    linea++;
                    token = new Token(this.linea,FIN_SENT,String.format("%s", 
                            (char)tokenSimple));
                    break;
                case SUMA:
                    token = new Token(this.linea,FIN_SENT,String.format("%s", 
                            (char)tokenSimple));
                    break;
                case RESTA:
                    token = new Token(this.linea,FIN_SENT,String.format("%s",
                            (char)tokenSimple));
                    break;
                case MULTIPLICACION:
                    token = new Token(this.linea,FIN_SENT,String.format("%s",
                            (char)tokenSimple));
                    break;
                case DIVISION:
                    token = new Token(this.linea,FIN_SENT,String.format("%s", 
                            (char)tokenSimple));
                    break;
                case PAR_DER:
                    token = new Token(this.linea,FIN_SENT,String.format("%s", 
                            (char)tokenSimple));
                    break;
                case PAR_IZQ:
                    token = new Token(this.linea,FIN_SENT,String.format("%s", 
                            (char)tokenSimple));
                    break;    
                default:
                    throw new 
        Error("Error de lexico: El caracter no esta dentro del alfabeto.");
            }
        }         
        }else{
           ;
        }
        return token;
    }
    
    public static Boolean esNumero(String textoRevisar){
        Boolean esNumero = true;
        for (int i = 0; i < textoRevisar.length(); i++) {
            esNumero = esNumero && Character.isDigit(textoRevisar.charAt(i));
        }
        return esNumero;
    }
    
    public static Boolean esIdentificador(String textoRevisar){
        Boolean esId = true;
        for (int i = 0; i < textoRevisar.length(); i++) {
            esId = esId && (Character.isDigit(textoRevisar.charAt(1)));
            
        }
        return esId;
    }
    public static void main(String... args) {
        comSint analizador = new comSint();
       analizador.getTokenizer(("55+alfa;"));
      
        }
    
}
