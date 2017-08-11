
package pt.uminho.ceb.biosystems.mew.biocomponents.container.components;

import java.io.Serializable;

import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTree;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTreeNode;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.parser.ParseException;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.parser.ParserSingleton;

public class GeneReactionRuleCI implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    protected AbstractSyntaxTree<DataTypeEnum, IValue> rule;
    
    public GeneReactionRuleCI(AbstractSyntaxTree<DataTypeEnum, IValue> rule){
        if(rule == null)
            this.rule = new AbstractSyntaxTree<DataTypeEnum, IValue>();
        else
            this.rule = rule;
    }
    
    public GeneReactionRuleCI(String rule) throws ParseException{
        
        if(rule == null || rule.equals("")){
            this.rule = new AbstractSyntaxTree<DataTypeEnum, IValue>();
        }else{
        
            AbstractSyntaxTreeNode<DataTypeEnum, IValue> ast;
            
            ast = ParserSingleton.boolleanParserString(rule);
            
            this.rule = new AbstractSyntaxTree<DataTypeEnum, IValue>(ast);
        }
    }
    
    public AbstractSyntaxTree<DataTypeEnum, IValue> getRule() {
        return rule;
    }

    public void setRule(String rule) throws ParseException {
        if(rule == null || rule.equals("")){
            this.rule = new AbstractSyntaxTree<DataTypeEnum, IValue>();
        }else{
        
            AbstractSyntaxTreeNode<DataTypeEnum, IValue> ast = ParserSingleton.boolleanParserString(rule);
            
            this.rule = new AbstractSyntaxTree<DataTypeEnum, IValue>(ast);
        }
    }
    
}
