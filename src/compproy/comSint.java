
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
    private static final int LLDER = '{';
    private static final int LLIZQ = '}';
    private static final int CERR_DER = '[';
    private static final int CERR_IZQ = ']';
    private static final int VAR = 'R';
    private static final int EOF = '.';
    private static final int TERMIN ='"';
    private static final int NUMERO = 600;
    private static final int ID = 700;
    
    private Integer linea = 1; 
    private StringTokenizer tokenizer = null;
    private Token currentToken;
    private String salida = "";
   
    
    public void paser(){
        this.currentToken = lex();
        prog();
        if(this.currentToken.getToken() == EOF){
            System.out.println(String.format("El resultado : %s", this.salida));
        }
    }
    public void prog(){
        conjprod();
        if(!(this.currentToken.getToken() == FIN_SENT)){
            throw new Error(String.format("\nError de sintaxis: se esperaba '%s'",
                    (char)FIN_SENT));
          }
    }
    public void conjprod(){
        conjprod();
        while (this.currentToken.getToken() == ALTER)
        {
            if(this.currentToken.getToken()== ALTER){
                this.currentToken = lex();
                prod();
                if(!esnumeroId(this.currentToken)){
                    throw new Error("Error de Sintaxis:Se esperaba un numero o ID");
                }
                this.salida = String.format("%s%s", this.salida,(char)ALTER);
                }
            
        }
        
    }
    public void prod(){
        expr();
        this.currentToken = lex();
        while(this.currentToken.getToken() == VAR ||
                this.currentToken.getToken() == DEF){
            if(this.currentToken.getToken() == VAR){
                this.currentToken = lex();
                expr();
                if(!esnumeroId(this.currentToken)){
                    throw new Error("Error de Sintaxis:Se esperaba un numero o ID");
                }
                this.currentToken = lex();
                this.salida = String.format("%s%s", this.salida,
                        (char)VAR);
                
            }else if(this.currentToken.getToken() == DEF){
                this.currentToken = lex();
                expr();
                if(!esnumeroId(this.currentToken)){
                    throw new Error("Error de Sintaxis:Se esperaba un numero o ID");
                }
                this.currentToken = lex();    
                this.salida = String.format("%s%s", this.salida,
                        (char)DEF);
                }
        
        }
        
    }
    public void expr(){
        term();
        if(this.currentToken.getToken() == ALTER){
            this.salida = String.format("%s%s", this.salida,
                        this.currentToken.getLexema());
            
        
            term();
            if(!(this.currentToken.getToken() == ALTER)){
                throw new Error(String.format("\nError de Sintaxis: Se esperaba %s%s", (char)ALTER));
            }
        }
        
    }

    public void term(){
        factor();
        this.currentToken = lex();
        while(this.currentToken.getToken() == CONCA)
        {
            if(this.currentToken.getToken() == CONCA){
                this.currentToken = lex();
                factor();
                if(!esnumeroId(this.currentToken)){
                    throw new Error("Error de Sintaxis:");
                }
                this.currentToken = lex();
                this.salida = String.format("%s%s", this.salida,
                        (char)CONCA);
                
            }
                       
                }
        
        }
    public void factor(){
        primario();
        if(this.currentToken.getToken() == LLDER){
            this.salida = String.format("%s%s", this.salida,
                        this.currentToken.getLexema());
            
        }else if(this.currentToken.getToken() == LLIZQ){
            this.salida = String.format("%s%s", this.salida,
                        this.currentToken.getLexema());
            
        }else if(this.currentToken.getToken() == CERR_DER){
            this.currentToken = lex();
            expr();
            if(!(this.currentToken.getToken() == CERR_IZQ)){
                throw new Error(String.format("\nError de Sintaxis: Se esperaba %s%s", (char)CERR_IZQ));
            }
        }
        
    }
    public void primario(){
        expr();
        if(this.currentToken.getToken() == VAR){
            this.salida = String.format("%s%s", this.salida,
                        this.currentToken.getLexema());
            
        }else if(this.currentToken.getToken() == TERMIN){
            this.salida = String.format("%s%s", this.salida,
                        this.currentToken.getLexema());
            
        
            }
        }
        
    
    
    private Boolean esnumeroId(Token token){
        return token.getToken() == ID || token.getToken() == NUMERO;
    }
    private StringTokenizer getTokenizer(String codigoFuente){
        if(this.tokenizer == null){
            String alfabeto = String.format("%s%s%s%s%s%s%s%s",
                    (char)ALTER,(char)CONCA ,(char)LLDER,
                    (char)LLIZQ,(char)CERR_DER,(char)CERR_IZQ,
                    (char)VAR,(char)DEF);
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
            token = new Token(this.linea,VAR,currentToken);
        }else if (esIdentificador(currentToken)){
            token = new Token(this.linea,DEF,currentToken);
        }else{
              int tokenSimple = currentToken.charAt(0);
            switch (tokenSimple) {
                case FIN_SENT:
                    linea++;
                    token = new Token(this.linea,FIN_SENT,String.format("%s", 
                            (char)tokenSimple));
                    break;
                case DEF:
                    token = new Token(this.linea,FIN_SENT,String.format("%s", 
                            (char)tokenSimple));
                    break;
                case ALTER:
                    token = new Token(this.linea,FIN_SENT,String.format("%s",
                            (char)tokenSimple));
                    break;
                case CONCA:
                    token = new Token(this.linea,FIN_SENT,String.format("%s",
                            (char)tokenSimple));
                    break;
                case LLDER:
                    token = new Token(this.linea,FIN_SENT,String.format("%s", 
                            (char)tokenSimple));
                    break;
                case LLIZQ:
                    token = new Token(this.linea,FIN_SENT,String.format("%s", 
                            (char)tokenSimple));
                    break;
                case CERR_DER:
                    token = new Token(this.linea,FIN_SENT,String.format("%s", 
                            (char)tokenSimple));
                    break;  
                case CERR_IZQ:
                    token = new Token(this.linea,FIN_SENT,String.format("%s", 
                            (char)tokenSimple));
                    break;
                case VAR:
                    token = new Token(this.linea,FIN_SENT,String.format("%s", 
                            (char)tokenSimple));
                    break;
                case TERMIN:
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
