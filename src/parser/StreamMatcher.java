package parser;

import java.io.IOException;

/**
 * Definisce un matcher che analizza uno stream in input
 */
interface StreamMatcher {
    /**
     * Controlla se, a partire dal carattere puntato, esiste una sottostringa che matcha con l'espressione regolare
     * @return <tt>true</tt> se e solo se esiste una stringa che matcha
     * @throws IOException
     *         se si verifica un errore di I/O
     */
	boolean nextMatches() throws IOException;

	/**
	 * Controlla se c'è ancora qualcosa da leggere nell'input
	 * @return <tt>true</tt> se e solo se c'è qualcosa da leggere
	 */

	boolean hasNext();

	/**
	 * Restituisce la sottostringa che non matcha con l'espressione regolare
	 * @return la sottostringa che non matcha
	 */
	String getSkipped();

	/**
	 * Restituisce l'ultima sottostringa che ha matchato con l'espressione regolare
	 * @return l'ultima sottostringa che ha matchato
	 */
	String group();

	/**
	 * Restituisce l'ultima sottostringa che ha matchato, appartenente al gruppo di cattura
	 * @param group
     *        gruppo di cattura
	 * @return l'ultima sottostringa che ha matchato
	 */
	String group(int group);
}