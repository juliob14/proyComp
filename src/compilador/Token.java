package compilador;

/**
 *Es el modelo que contienen los elementos que conforma las expresion divididos
 * en los elementos que describen el alfabeto y la gramatica.
 * @author Carlos Ivan Zubiate Galvan
 * since 06/03/2015
 */
public class Token {
    
    private Integer linea;
    private int token;
    private String lexema;
    
    /**
 *Es el modelo que contienen los elementos que conforma las expresion divididos
 * en los elementos que describen el alfabeto y la gramatica.
 * @author Carloes Ivan Zubiate Galvan
 * since 06/03/2015
 */

public Token (Integer linea, int token, String lexema){
    this.linea = linea;
    this.token = token;
    this.lexema = lexema;

}

    public Integer getLinea() {
        return linea;
    }

    public void setLinea(Integer linea) {
        this.linea = linea;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
      /**
 *Metodo que genera una cadena con los emelnetos del token
 * return cadena con el formato linea --- token --- lexema.
 * @author Carlos Ivan Zubiate Galvan
 * since 06/03/2015
 */ 
    @Override
    public String toString(){
        return String.format("%s --- %s --- %s", 
                this.linea, this.token, this.lexema);
    }
    
}