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
package pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node;

import java.util.ArrayList;

import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTreeNode;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.IEnvironment;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.BooleanValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;

public class Not extends AbstractSyntaxTreeNode<DataTypeEnum,IValue>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Not(){
		super(DataTypeEnum.BOOLEAN);
        childNodeArray = new ArrayList<>();
        childNodeArrayType = new ArrayList<>();
        childNodeArrayType.add(0,DataTypeEnum.BOOLEAN);
	}
	
	public Not(AbstractSyntaxTreeNode node){
		super(DataTypeEnum.BOOLEAN);
        childNodeArray = new ArrayList<>();
        childNodeArray.add(0,node);
        childNodeArrayType = new ArrayList<>();
        childNodeArrayType.add(0,DataTypeEnum.BOOLEAN);
	}

	@Override
	public IValue evaluate(IEnvironment<IValue> environment) {
		IValue node = childNodeArray.get(0).evaluate(environment);
		Boolean resultValue = !(Boolean)node.getValue();
		return new BooleanValue(resultValue);
	}

	@Override
	public AbstractSyntaxTreeNode<DataTypeEnum,IValue> newInstance() {
		return new Not();
	}

	@Override
	public String toString() {
		String node = childNodeArray.get(0).toString();
		return " not( "+ node + ")";
	}

	@Override
	public boolean typeCheck() {
		return true;
	}

/*	@Override
	public String toString(String andString, String orString) {
		return toString();
	}*/

}
