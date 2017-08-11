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
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DoubleValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;

public class Minus extends AbstractSyntaxTreeNode<DataTypeEnum,IValue> {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Minus(){
        super(DataTypeEnum.DOUBLE);
        childNodeArray = new ArrayList<>();
        childNodeArrayType = new ArrayList<>();
        childNodeArrayType.add(0,DataTypeEnum.DOUBLE);
        childNodeArrayType.add(1,DataTypeEnum.DOUBLE);
    }

    public Minus(AbstractSyntaxTreeNode<DataTypeEnum,IValue> leftTerm,AbstractSyntaxTreeNode<DataTypeEnum,IValue> rightTerm){
        super(DataTypeEnum.DOUBLE);
        childNodeArray = new ArrayList<>();
        childNodeArray.add(0,leftTerm);
        childNodeArray.add(1,rightTerm);
        childNodeArrayType = new ArrayList<>();
        childNodeArrayType.add(0,DataTypeEnum.DOUBLE);
        childNodeArrayType.add(1,DataTypeEnum.DOUBLE);
    }

    public IValue evaluate(IEnvironment<IValue> environment){
        IValue leftTermResult = childNodeArray.get(0).evaluate(environment);
        IValue rightTermResult = childNodeArray.get(1).evaluate(environment);
        Double result = (Double)leftTermResult.getValue() - (Double)rightTermResult.getValue();
        return new DoubleValue(result);
    }

    @Override
    public boolean typeCheck() {
        return true;
    }

    @Override
    public String toString(){
        String leftTermString = childNodeArray.get(0).toString();
        String rightTermString = childNodeArray.get(1).toString();
        return "("+leftTermString + "-" +rightTermString+")";
    }

    @Override
    public AbstractSyntaxTreeNode<DataTypeEnum, IValue> newInstance() {
        return new Minus();
    }

	/*@Override
	public String toString(String andString, String orString) {
		String leftTermString = childNodeArray[0].toString();
        String rightTermString = childNodeArray[1].toString();
        return leftTermString + "-" +rightTermString;
	}*/
}
