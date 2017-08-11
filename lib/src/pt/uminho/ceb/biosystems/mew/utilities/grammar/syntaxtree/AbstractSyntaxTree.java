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
import java.util.Stack;

//S - e o tipo que representa o tipo de dados
//T - e o objecto tipo resultado

public  class AbstractSyntaxTree<T,S> implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;
	protected AbstractSyntaxTreeNode<T,S> root;
	protected int size;
	
	public AbstractSyntaxTree(){
		root = null;
		size = 0;
	}
	
	public AbstractSyntaxTree(AbstractSyntaxTreeNode<T,S> node){
		root = node;
		size = countNodes(node);
	}
	
	public void setRootNode(AbstractSyntaxTreeNode<T,S> node){
		root = node;
        size = countNodes(node);
	}
	
	public AbstractSyntaxTreeNode<T,S> getRootNode(){
		return root;
	}
	
	public void setParent(AbstractSyntaxTreeNode<T,S> node, AbstractSyntaxTreeNode<T,S> parentNode){
		node.setParent(parentNode);
	}
	
	public AbstractSyntaxTreeNode<T,S> getParent(AbstractSyntaxTreeNode<T,S> node){
		return node.getParent();
	}
	
	public AbstractSyntaxTreeNode<T,S> getChildAt(AbstractSyntaxTreeNode<T,S> node,int index){
		return node.getChildAt(index);
	}
	
	public int getNumberOfChildren(AbstractSyntaxTreeNode<T,S> node){
		return node.getNumberOfChildren();
	}
	
	public void insertChildAt(AbstractSyntaxTreeNode<T,S> node,int index, AbstractSyntaxTreeNode<T,S> child){
		child.setParent(node);
        child.setParentIndex(index);
		node.insertChildAt(index,child);
		size += countNodes(child);
	}
	
	public void insertChildAt(AbstractSyntaxTreeNode<T,S> node,int index, AbstractSyntaxTree<T,S> tree){
		AbstractSyntaxTreeNode<T,S> child = tree.getRootNode();
		child.setParent(node);
        child.setParentIndex(index);
		node.insertChildAt(index,child);
		size += tree.size();
	}

    public int getParentIndex(AbstractSyntaxTreeNode<T,S> node){
        return node.getParentIndex();
    }

    public void setParentIndex(AbstractSyntaxTreeNode<T,S> node,int parentIndex){
        node.setParentIndex(parentIndex);
    }
		
	public int depth(AbstractSyntaxTreeNode<T,S> node){
		int currentDepth = 0;
		AbstractSyntaxTreeNode<T,S> currentNode = node;
		
		while(currentNode != null){
			currentNode = currentNode.getParent();
			currentDepth++;
		}
				
		return currentDepth;
	}
	
	//Return -1 - Empty Tree
	public int height(){
		int currentHeight = -1;
		AbstractSyntaxTreeNode<T,S> currentNode = root;
		Stack<AbstractSyntaxTreeNode<T,S>> nextLevelStackNode = new Stack<AbstractSyntaxTreeNode<T,S>>();
		
		if(currentNode != null){
			nextLevelStackNode.push(currentNode);
			while(nextLevelStackNode.size() != 0){
				currentHeight++;
				Stack<AbstractSyntaxTreeNode<T,S>> newLevelStackNode = createNextLevelNodeStack(nextLevelStackNode);
				nextLevelStackNode = newLevelStackNode;
			}
			
		}
		return currentHeight;
	}
	
	protected Stack<AbstractSyntaxTreeNode<T,S>> createNextLevelNodeStack(Stack<AbstractSyntaxTreeNode<T,S>> nextLevelStackNode){
		Stack<AbstractSyntaxTreeNode<T,S>> newLevelStackNode = new Stack<AbstractSyntaxTreeNode<T,S>>();
		
		while(nextLevelStackNode.size() != 0){
			AbstractSyntaxTreeNode<T,S> node = nextLevelStackNode.pop();
			insertNewNodes(newLevelStackNode,node);
		}
		
		return newLevelStackNode;
	}

	protected void insertNewNodes(Stack<AbstractSyntaxTreeNode<T,S>> nodeStack,	AbstractSyntaxTreeNode<T,S> node){
		for(int i = 0;i < node.getNumberOfChildren();i++){
			AbstractSyntaxTreeNode<T,S> childNode = node.getChildAt(i);
            if(childNode != null)
			    nodeStack.add(childNode);
		}		
	}

	public int size(){
		return size;
	}
	
	public S evaluate(IEnvironment<S> environment){
		return root.evaluate(environment);
	}
	
	public boolean typeCheck(){
		return root.typeCheck();
	}
	
	public String toString(){
		if(root == null)
			return "";
		else
			return root.toString();
	}
	
	//NOTE: adicionado por pvilaca por causa do MFAToolKit
	//public String toString(String andString, String orString){
	//	return root.toString(andString, orString);
	//}
	
	protected int countNodes(AbstractSyntaxTreeNode<T,S> node) {
		int currentNumberOfNodes = 0;
		Stack<AbstractSyntaxTreeNode<T,S>> nodeStack = new Stack<AbstractSyntaxTreeNode<T,S>>();
		nodeStack.push(node);
		while(nodeStack.size() != 0){
			AbstractSyntaxTreeNode<T,S> currentNode = nodeStack.pop();
			insertNewNodes(nodeStack,currentNode);
			currentNumberOfNodes++;
		}
		
		return currentNumberOfNodes;
	}

    public T getChildReturnType(AbstractSyntaxTreeNode<T,S> node, int index){
        return node.getChildReturnType(index);  
    }

    public AbstractSyntaxTreeNode<T,S> getRandomNode(){
        int randomNodeNumber = (int) (Math.random()*size);
        AbstractSyntaxTreeNode<T,S> currentNode = root;
        int currentNodeNumber = 1;
        Stack<AbstractSyntaxTreeNode<T,S>> nodeStack = new Stack<AbstractSyntaxTreeNode<T,S>>();
		nodeStack.push(currentNode);

        while(currentNodeNumber < randomNodeNumber){
            currentNode = nodeStack.pop();
            insertNewNodes(nodeStack,currentNode);
            currentNodeNumber++;
        }

        return currentNode;
    }

    public AbstractSyntaxTree<T,S> copy(){
        if(root == null)
            return new AbstractSyntaxTree<T,S>();

        AbstractSyntaxTreeNode<T,S> currentNode = root.newInstance();
        Stack<NodeContainer<T,S>> nodeStack = new Stack<NodeContainer<T,S>>();
        nodeStack.push(new NodeContainer(root));

        while(nodeStack.size() >0){
            NodeContainer nodeContainer = nodeStack.pop();
            AbstractSyntaxTreeNode<T,S> node = nodeContainer.getNode();
            int childNumberToVisit = nodeContainer.getCurrentChild();
            if(childNumberToVisit < node.getNumberOfChildren()){
                nodeContainer.incrementCurrentChild();
                nodeStack.push(nodeContainer);
                AbstractSyntaxTreeNode childNode = node.getChildAt(childNumberToVisit);
                AbstractSyntaxTreeNode newNode = childNode.newInstance();
                createNodeLinks(currentNode,newNode,childNumberToVisit);
                currentNode = newNode;
                nodeStack.push(new NodeContainer(childNode));
            } else if(currentNode.getParent() != null)
                currentNode = currentNode.getParent();    


        }

        return new AbstractSyntaxTree<T,S>(currentNode);
    }

    protected void createNodeLinks(AbstractSyntaxTreeNode currentNode, AbstractSyntaxTreeNode newNode,int parentIndex) {
        currentNode.insertChildAt(parentIndex,newNode);
        newNode.setParent(currentNode);
        newNode.setParentIndex(parentIndex);
    }
}
