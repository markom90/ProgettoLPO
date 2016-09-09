package parser;

import java.io.IOException;

/**
 * Definisce un tokenizzatore che prende in input uno stream
 */
public interface StreamTokenizer {
    /**
     * Avanza sullo stream in input
     * @return il tipo di token successivo
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws TokenizerException
     *         se trova un token sconosciuto
     */
	TokenType next() throws IOException, TokenizerException;

    /**
     * Restituisce l'ultimo token riconosciuto
     * @return l'ultimo token riconosciuto
     */
	String tokenString();

    /**
     * Restituisce il valore dell'ultimo token riconosciuto
     * @return il valore intero dell'ultimo token
     */
	int intValue();

    /**
     * Restituisce l'ultima stringa riconosciuta, privata di eventuali caratteri di SKIP
     * @return l'ultima stringa riconosciuta
     */
	String stringValue();

    /**
     * Restituisce il tipo dell'ultimo token riconosciuto
     * @return il tipo dell'ultimo token riconosciuto
     */
	TokenType tokenType();

    /**
     * Controlla se c'è ancora da leggere nell'input
     * @return <tt>true</tt> se e solo se c'è qualcosa da leggere nell'input
     */

	boolean hasNext();

}