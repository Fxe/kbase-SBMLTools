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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractSyntaxTreeNode<T,S> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected List<AbstractSyntaxTreeNode<T,S>> childNodeArray;
    protected List<T> childNodeArrayType;
	protected AbstractSyntaxTreeNode<T,S> parent;
    protected int parentIndex;
    protected T returnType;


    protected AbstractSyntaxTreeNode(List<AbstractSyntaxTreeNode<T, S>> childNodeArray, AbstractSyntaxTreeNode<T, S> parent, T returnType) {
        this.childNodeArray = childNodeArray;
        this.parent = parent;
        this.returnType = returnType;
    }

    protected AbstractSyntaxTreeNode(AbstractSyntaxTreeNode<T, S> parent, T returnType) {
        this.parent = parent;
        this.returnType = returnType;
    }

    protected AbstractSyntaxTreeNode(T returnType) {
        this.returnType = returnType;
        childNodeArray = new ArrayList<>();
    }

    public int getParentIndex(){
        return parentIndex;
    }

    public void setParentIndex(int parentIndex){
        this.parentIndex = parentIndex;
    }

    public void setParent(AbstractSyntaxTreeNode<T,S> parentNode){
		parent = parentNode;
	}
	
	public AbstractSyntaxTreeNode<T,S> getParent(){
		return parent;
	}
	
	public int getNumberOfChildren(){
		return childNodeArray.size();
	}
		
	public void insertChildAt(int index, AbstractSyntaxTreeNode<T,S> child){
		childNodeArray.add(index,child);
	} 
		
	public AbstractSyntaxTreeNode<T,S> getChildAt(int index) {
		return childNodeArray.get(index);
	}
	
	public boolean isLeaf(){
		return childNodeArray.size() == 0;
	}
	
	public boolean isRoot(){
		return parent == null;
	}
	
	public abstract S evaluate(IEnvironment<S> environment);
	
	public abstract boolean typeCheck();
	
	public abstract String toString();
	
	//NOTE: adicionado por pvilaca por causa do MFAToolKit
	//public abstract String toString(String andString, String orString);
	
	public T getReturnType(){
		return returnType;
	}
	
	public T getChildReturnType(int index){
		return childNodeArrayType.get(index);
	}


    public abstract AbstractSyntaxTreeNode<T, S> newInstance();
}
