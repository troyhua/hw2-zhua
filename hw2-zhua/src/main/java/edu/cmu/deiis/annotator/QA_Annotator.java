package edu.cmu.deiis.annotator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.Answer;
import edu.cmu.deiis.types.Question;

public class QA_Annotator extends JCasAnnotator_ImplBase{

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    String docText = aJCas.getDocumentText();
    Pattern questionPattern = Pattern.compile("Q\\s(.*)");
    Pattern answerPattern = Pattern.compile("A\\s([0-1])\\s(.*)");
    Matcher matcher = questionPattern.matcher(docText);
    while (matcher.find()){
      Question questionAnnotation = new Question(aJCas);
      questionAnnotation.setCasProcessorId("QA_Annotator");
      questionAnnotation.setConfidence(1.0);
      questionAnnotation.setBegin(matcher.start(1));
      questionAnnotation.setEnd(matcher.end(1));
      questionAnnotation.addToIndexes();
    }
    matcher = answerPattern.matcher(docText);
    while (matcher.find()){
      Answer answerAnnotation = new Answer(aJCas);
      answerAnnotation.setCasProcessorId("QA_Annotator");
      answerAnnotation.setConfidence(1.0);
      answerAnnotation.setBegin(matcher.start(2));
      answerAnnotation.setEnd(matcher.end(2));
      if (matcher.group(1).equals("0"))
        answerAnnotation.setIsCorrect(false);
      else
        answerAnnotation.setIsCorrect(true);
      answerAnnotation.addToIndexes();
    }
  }

}
