/**
 * <ul>
 *<li> Prog →  conjProd</li>
 * <li> Conjprod→ ConjProd</li>
 *         |Prod
 * <li>Prod  → variable DEF Expr;</li>
 * <li>Expr →  Expr ALT term</li>
 * <li>Term → term & factor |Factor </li>
 * <li>Factor {Expr} | [Expr] | primario</li>
 * <li>Primario → Expr | Variable | Terminal</li>
 * 
 * 
 * A)Concatenacion: caracter &
 * B) Alternacion: Caracter ' | '
 * C) Cerradura cero o mas : Se representa con los caracteres ' { } 'Ejemp {<digito>}
 * D) Cerradura cero o uno se representa por los caracteres ' [ ] ' ejemp [+|-] 
 * significa que se puede tomar el caracter + o - de manera opcional
 * E)Definicion: Se representa por la secuencia de caracteres  ' ::= '
 * F)Fin de produccion: Caracter ' ; ' 
 * G)fin de archivo: caracter '.'
 * </ul>
 * @author Carlos Zubiate
 */

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
