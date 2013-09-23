
/* First created by JCasGen Sat Sep 21 12:23:26 EDT 2013 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Sat Sep 21 13:22:34 EDT 2013
 * @generated */
public class EvaluationType_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (EvaluationType_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = EvaluationType_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new EvaluationType(addr, EvaluationType_Type.this);
  			   EvaluationType_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new EvaluationType(addr, EvaluationType_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = EvaluationType.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.deiis.types.EvaluationType");
 
  /** @generated */
  final Feature casFeat_precisionAtN;
  /** @generated */
  final int     casFeatCode_precisionAtN;
  /** @generated */ 
  public double getPrecisionAtN(int addr) {
        if (featOkTst && casFeat_precisionAtN == null)
      jcas.throwFeatMissing("precisionAtN", "edu.cmu.deiis.types.EvaluationType");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_precisionAtN);
  }
  /** @generated */    
  public void setPrecisionAtN(int addr, double v) {
        if (featOkTst && casFeat_precisionAtN == null)
      jcas.throwFeatMissing("precisionAtN", "edu.cmu.deiis.types.EvaluationType");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_precisionAtN, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public EvaluationType_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_precisionAtN = jcas.getRequiredFeatureDE(casType, "precisionAtN", "uima.cas.Double", featOkTst);
    casFeatCode_precisionAtN  = (null == casFeat_precisionAtN) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_precisionAtN).getCode();

  }
}



    