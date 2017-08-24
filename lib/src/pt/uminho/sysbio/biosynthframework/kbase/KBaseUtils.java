package pt.uminho.sysbio.biosynthframework.kbase;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import pt.uminho.ceb.biosystems.mew.biocomponents.container.components.GeneReactionRuleCI;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTreeNode;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.Variable;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.parser.TokenMgrError;

public class KBaseUtils {
  public static Set<String> collect(AbstractSyntaxTreeNode<DataTypeEnum, IValue> root) {
    Set<String> data = new HashSet<> ();
    if (root instanceof Variable) {
      Variable var = (Variable) root;
      data.add(var.toString());
    }
    int childs = root.getNumberOfChildren();
    for (int i = 0; i < childs; i++) {
      data.addAll(collect(root.getChildAt(i)));
    }
    
    return data;
  }

  public static Set<String> getGenes(GeneReactionRuleCI ruleCI) {
//    Set<String> genes = new HashSet<> ();
    
    AbstractSyntaxTreeNode<DataTypeEnum, IValue> root = ruleCI.getRule().getRootNode();
    
    return collect(root);
  }
  
  public static Set<String> getGenes(String gprExpression) {
    GeneReactionRuleCI grrci;
    try {
      grrci = new GeneReactionRuleCI(gprExpression);
      return getGenes(grrci);
    } catch (Exception | TokenMgrError e) {
      return new HashSet<> ();
    }
  }

  public static<T> T convert(Object object, Class<T> clazz) {
    ObjectMapper om = new ObjectMapper();
    T out = om.convertValue(object, clazz);
    return out;
  }
}
