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


import java.util.List;

import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTree;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTreeNode;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.Environment;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.IEnvironment;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.Closure;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;


public class FunctionCall extends AbstractSyntaxTreeNode<DataTypeEnum, IValue> {
	protected String functionName;
	protected List<String> parameterList;
	
	public FunctionCall(String functionName, List<String> parameterList) {
		super(DataTypeEnum.DOUBLE);
		this.functionName = functionName;
		this.parameterList = parameterList;
	}

	@Override
	public IValue evaluate(IEnvironment<IValue> environment){
		IEnvironment<IValue> newEnvironment = new Environment<IValue>((Environment<IValue>) environment);
		IValue functionClosureValue = newEnvironment.find(functionName);
		Closure functionClosure = (Closure)functionClosureValue.getValue();
		AbstractSyntaxTree<DataTypeEnum,IValue> functionBody = functionClosure.getFunctionBody();
		return functionBody.evaluate(newEnvironment);
	}

	@Override
	public AbstractSyntaxTreeNode<DataTypeEnum, IValue> newInstance() {
		return new FunctionCall(functionName,parameterList);
	}

	@Override
	public String toString() {
		String parameterListString = createArgumentsString();
		return functionName+"("+parameterListString+")";
	}

	protected String createArgumentsString() {
		String parameterListString = "";
		
		for(String parameterId:parameterList){
			if(parameterListString.equals(""))
				parameterListString += parameterId;
			else
				parameterListString +=","+parameterId;
		}
		
		return parameterListString;
	}

	@Override
	public boolean typeCheck() {
		return false;
	}

}
