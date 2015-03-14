package compilador;

import java.util.StringTokenizer;

/**
 *
 * @author Zubi
 */
public class Compilador {

    private static final int FIN_SENT = ';';
    private static final int CONCAT = '&';
    private static final int ALTER = '|';
    
    private Integer linea = 1;
    private StringTokenizer tokenizer = null;
    
     private StringTokenizer getTokenizer (String codigoFuente){
        if(this.tokenizer == null){
            String alfabeto = String.format("%S%S", 
                    (char) CONCAT,
                    (char) ALTER
            
            );
            this.tokenizer = new StringTokenizer (codigoFuente.trim(),alfabeto, true);
        }
        return this.tokenizer;
        
    }

}
