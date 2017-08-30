package sbmltools.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import pt.uminho.sysbio.biosynthframework.BFunction;

public class ManualFixes {
  public static BFunction<String, String> get_iLCBaumannia() {
    BFunction<String, String> f = new BFunction<String, String>() {
      
      @Override
      public String apply(String gene) {
        if (StringUtils.countMatches(gene, '_') > 1) {
          String[] tk = gene.split("_");
          List<String> parts = new ArrayList<> ();
          for (int i = 0; i < tk.length - 1; i++) {
            parts.add(tk[i]);
          }
          String k = StringUtils.join(parts, '_');
          if (!k.trim().isEmpty()) {
            gene = k;
          }
        }

        if (gene.equals("gapA") || gene.equals("NA")) {
          return null;
        }
        
        return gene;
      }
    };
    return f;
  }
  
  public static BFunction<String, String> get_iCAC() {
    BFunction<String, String> f = new BFunction<String, String>() {
      
      @Override
      public String apply(String geneEntry) {
        if (geneEntry.startsWith("CA_")) {
          return geneEntry;
        } else if (geneEntry.startsWith("CA")) {
          return "CA_".concat(geneEntry.substring(2));
        }
        return geneEntry;
      }
    };
    return f;
  }
  
  public static BFunction<String, String> get_iPB890() {
    BFunction<String, String> f = new BFunction<String, String>() {
      
      @Override
      public String apply(String geneEntry) {
        if (geneEntry.startsWith("PST_")) {
          return geneEntry;
        } else if (geneEntry.startsWith("PST")) {
          return "PST_".concat(geneEntry.substring(3));
        }
        return geneEntry;
      }
    };
    return f;
  }
  
  public static BFunction<String, String> get_iOD907() {
    BFunction<String, String> f = new BFunction<String, String>() {
      
      @Override
      public String apply(String geneEntry) {
        if (geneEntry.contains("_")) {
          String[] tk = geneEntry.trim().split("_");
          geneEntry = tk[tk.length - 1];
        }
        return geneEntry;
      }
    };
    return f;
  }
  
  public static BFunction<String, String> get_iJH728() {
    BFunction<String, String> f = new BFunction<String, String>() {
      
      @Override
      public String apply(String geneEntry) {
        if (geneEntry.startsWith("SYNPCC7002_")) {
          return geneEntry;
        } else if (geneEntry.startsWith("A")) {
          return "SYNPCC7002_".concat(geneEntry);
        }
        return geneEntry;
      }
    };
    return f;
  }
  
  public static BFunction<String, String> get_iJP962_iJP815() {
    BFunction<String, String> f = new BFunction<String, String>() {
      
      @Override
      public String apply(String geneEntry) {
        if (geneEntry.startsWith("PP_")) {
          return geneEntry;
        } else if (geneEntry.startsWith("PP")) {
          return "PP_".concat(geneEntry.substring(2));
        }
        return geneEntry;
      }
    };
    return f;
  }
}
