/*
 * Copyright 2010
 * IBB-CEB - Institute for Biotechnology and Bioengineering - Centre of Biological Engineering
 * CCTC - Computer Science and Technology Center
 *
 * University of Minho 
 * 
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created inside the SysBioPseg Research Group (http://sysbio.di.uminho.pt)
 */
package pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * DynamicModelSimulator
 * Created By
 * User: ptiago
 * Date: Feb 22, 2009
 * Time: 10:56:49 AM
 */
public class Environment<S> implements IEnvironment<S>{
   
	protected IEnvironment<S> parent;//TODO  - acrescentar os metodos que faltam
    protected Map<String,S> elementMap;

    public Environment(){
        this.parent = null;
        elementMap = new Hashtable<String,S>();
    }

    public Environment(Environment<S> oldEnvironment){
    	Map<String,S> oldElementMap = oldEnvironment.getElementMap();
		Set<String> oldKeySet = oldElementMap.keySet();
		elementMap = new Hashtable<String,S>();
		
		for(String key:oldKeySet){
			S element = oldElementMap.get(key);
			elementMap.put(key,element);
		}
	}

	protected Map<String, S> getElementMap(){
		return elementMap;
	}

	@Override
    public S find(String id) {
        return elementMap.get(id);
    }

    @Override
    public void associate(String id,S value) {
        elementMap.put(id,value);
    }
}
