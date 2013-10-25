package com.orange.todolist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe responsable de stoquer la liste des taches rafraichie via {@link #put(String)}
 */
public abstract class TodoStorage {
	public static final Logger logger = LoggerFactory.getLogger(TodoStorage.class);

	
	private void validate(String todosAsString){
		logger.debug("Checking [{}]",todosAsString);
		if(todosAsString.matches(".*([Rr].silier.[Oo]range).*")  || todosAsString.matches(".*([Cc]ancel.*[Oo]range).*") ){
			throw new CancellationException();
		}
		if(todosAsString.matches(".*([fF]ree|[Ss][Ff][Rr]|[Bb]ouygues).*")){
			throw new BusinessConcurrencyException();
		}
	}
	
	public void put(String todosAsString) throws TodoStorageException{
		validate(todosAsString);
		JSONTokener tokener = new JSONTokener(todosAsString);
		try {
			doPut((JSONArray) tokener.nextValue());
		} catch (JSONException e) {
			throw new TodoStorageException(e);
		}
	}

	/**
	 * Met a jour la liste des t�ches
	 * @param todosAsString La liste des t�ches sous la formes d'un tableau JSON serialis� 
	 * @throws TodoStorageException si la valeur n'es tpas correctement form�e
	 */
	public abstract void doPut(JSONArray todosAsString) throws TodoStorageException;
	
	/**
	 * @return les taches sous la forme d'un {@link JSONArray}
	 */
	public abstract JSONArray get() throws TodoStorageException ;

}
