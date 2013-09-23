package edu.cmu.deiis.annotator;

import java.util.HashSet;
import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.Answer;
import edu.cmu.deiis.types.AnswerScore;
import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.Token;

public class ScoreAnnotator extends JCasAnnotator_ImplBase{

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
    Iterator<Question> questionIter = questionIndex.iterator();
    Question question = (Question)questionIter.next();
    
    FSIndex answerIndex = aJCas.getAnnotationIndex(Answer.type);
    Iterator<Answer> answerIter = answerIndex.iterator();
    while (answerIter.hasNext()) {
      int unigram = 0;
      Answer ans = (Answer)answerIter.next();
      HashSet<String> set = new HashSet<String>();
      for (int i = 0; i < ans.getTokens().size(); i++){
        set.add(ans.getTokens(i).getCoveredText());
      }
      for (int i = 0; i < question.getTokens().size(); i++)
        if (set.contains(question.getTokens(i).getCoveredText()))
          unigram += 1;
      
      double uniScore = (double)unigram / ans.getTokens().size();
      
      AnswerScore finalScore = new AnswerScore(aJCas);
      
      finalScore.setAnswer(ans);
      finalScore.setCasProcessorId("ScoreAnnotator");
      finalScore.setConfidence(0.5);
      finalScore.setBegin(ans.getBegin());
      finalScore.setEnd(ans.getEnd());
      finalScore.setScore(uniScore);
      finalScore.addToIndexes();
    }
  }
}