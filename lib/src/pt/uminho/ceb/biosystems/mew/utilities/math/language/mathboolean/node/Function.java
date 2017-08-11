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

import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTree;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTreeNode;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.IEnvironment;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;

public class Function extends AbstractSyntaxTreeNode<DataTypeEnum, IValue> {
	protected AbstractSyntaxTree<DataTypeEnum,IValue> functionBody;
	
	public  Function(AbstractSyntaxTreeNode<DataTypeEnum, IValue> functionBody) {
		super(DataTypeEnum.DOUBLE);
		this.functionBody = new AbstractSyntaxTree<DataTypeEnum, IValue>(functionBody);
	}

	public  Function(AbstractSyntaxTree<DataTypeEnum, IValue> functionBody) {
		super(DataTypeEnum.DOUBLE);
		this.functionBody = functionBody;
	}
	
	
	@Override
	public IValue evaluate(IEnvironment<IValue> environment) {
		return functionBody.evaluate(environment);
	}

	@Override
	public AbstractSyntaxTreeNode<DataTypeEnum, IValue> newInstance() {
		AbstractSyntaxTree<DataTypeEnum,IValue> functionBodyCopy = functionBody.copy();
		return new Function(functionBodyCopy);
	}

	@Override
	public String toString() {
		return functionBody.toString();
	}

	@Override
	public boolean typeCheck() {
		// TODO Auto-generated method stub
		return false;
	}

}
