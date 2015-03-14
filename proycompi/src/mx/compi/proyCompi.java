
package mx.compi;

/**
 * <ul>
 *<li> Prog →  conjProd</li>
 * <li> Conjprod→ ConjProd</li>
 *         |Prod
 * <li>Prod  → variable DEF Expr;</li>
 * <li>Expr →  Expr ALT term</li>
 * <li>Term → term & factor |Factor </li>
 * <li>Factor {Expr} | [Expr] | primario</li>
 * <li>Primario → Expr | Variable | Terminal</li>
 * 
 * 
 * A)Concatenacion: caracter &
 * B) Alternacion: Caracter ' | '
 * C) Cerradura cero o mas : Se representa con los caracteres ' { } 'Ejemp {<digito>}
 * D) Cerradura cero o uno se representa por los caracteres ' [ ] ' ejemp [+|-] 
 * significa que se puede tomar el caracter + o - de manera opcional
 * E)Definicion: Se representa por la secuencia de caracteres  ' ::= '
 * F)Fin de produccion: Caracter ' ; ' 
 * G)fin de archivo: caracter '.'
 * </ul>
 * @author juliobitar
 */
public class proyCompi {
    private static final int FIN_SENT = ';';
    private static final int CONCA = '&';
    private static final int ALTER = '|';
    private static final int EOF = '.';
    
}
