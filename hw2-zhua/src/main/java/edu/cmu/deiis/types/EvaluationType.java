

/* First created by JCasGen Sat Sep 21 12:23:26 EDT 2013 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** For showing the precision at N score for a scoring component.
 * Updated by JCasGen Sun Oct 06 19:33:33 EDT 2013
 * XML source: /Users/troy/git/hw2-zhua/hw2-zhua/src/main/resources/descriptors/ScoreDescriptor.xml
 * @generated */
public class EvaluationType extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(EvaluationType.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected EvaluationType() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public EvaluationType(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public EvaluationType(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public EvaluationType(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: precisionAtN

  /** getter for precisionAtN - gets 
   * @generated */
  public double getPrecisionAtN() {
    if (EvaluationType_Type.featOkTst && ((EvaluationType_Type)jcasType).casFeat_precisionAtN == null)
      jcasType.jcas.throwFeatMissing("precisionAtN", "edu.cmu.deiis.types.EvaluationType");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((EvaluationType_Type)jcasType).casFeatCode_precisionAtN);}
    
  /** setter for precisionAtN - sets  
   * @generated */
  public void setPrecisionAtN(double v) {
    if (EvaluationType_Type.featOkTst && ((EvaluationType_Type)jcasType).casFeat_precisionAtN == null)
      jcasType.jcas.throwFeatMissing("precisionAtN", "edu.cmu.deiis.types.EvaluationType");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((EvaluationType_Type)jcasType).casFeatCode_precisionAtN, v);}    
  }

    