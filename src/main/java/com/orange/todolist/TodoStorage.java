package com.orange.todolist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe responsable de stoquer la liste des taches rafraichie via {@link #put(String)}
 */
public class TodoStorage {
	public static final Logger logger = LoggerFactory.getLogger(TodoStorage.class);

	private static JSONArray todos = new JSONArray();

	/**
	 * Met a jour la liste des t�ches
	 * @param todosAsString La liste des t�ches sous la formes d'un tableau JSON serialis� 
	 * @throws TodoStorageException si la valeur n'es tpas correctement form�e
	 */
	public void put(String todosAsString) throws TodoStorageException {
		JSONTokener tokener = new JSONTokener(todosAsString);
		try {
			todos  = (JSONArray) tokener.nextValue();
			logger.debug("Added todos : {}", todos);
		} catch (JSONException e) {
			throw new TodoStorageException(e);
		}
	}
	
	/**
	 * @return les taches sous la forme d'un {@link JSONArray}
	 */
	public JSONArray get(){
		logger.debug("Requesting todos : {}", todos);
		return todos;
	}

}
