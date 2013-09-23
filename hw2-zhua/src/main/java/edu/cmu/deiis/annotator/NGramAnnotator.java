package edu.cmu.deiis.annotator;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import edu.cmu.deiis.types.Answer;
import edu.cmu.deiis.types.NGram;
import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.Token;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.objectbank.TokenizerFactory;
import edu.stanford.nlp.process.PTBTokenizer.PTBTokenizerFactory;
import edu.stanford.nlp.process.Tokenizer;

public class NGramAnnotator extends JCasAnnotator_ImplBase{

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
TokenizerFactory<Word> factory = PTBTokenizerFactory.newTokenizerFactory();
    
    FSIndex answerIndex = aJCas.getAnnotationIndex(Answer.type);
    Iterator<Answer> answerIter = answerIndex.iterator();
    while (answerIter.hasNext()) {
      Answer ans = (Answer) answerIter.next();
      
      for (int N = 1; N <= 3; N++){
        for (int i = N; i < ans.getTokens().size(); i++){
          NGram ngram = new NGram(aJCas);
          ngram.setCasProcessorId("NGramAnnotator");
          ngram.setConfidence(1.0);
          ngram.setBegin((ans.getTokens(i - N).getBegin()));
          ngram.setEnd((ans.getTokens(i).getEnd()));
          FSArray a = new FSArray(aJCas, N);
          for (int j = 0; j < N; j++){
            a.set(j, ans.getTokens(i - j));
          }
          ngram.setElements(a);
          ngram.setElementType(Token.class.toString());
          ngram.addToIndexes();
        }
      }
      
    }

    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
    Iterator<Question> questionIter = questionIndex.iterator();
    while (questionIter.hasNext()) {
      Question question = (Question) questionIter.next();
      
      for (int N = 1; N <= 3; N++){
        for (int i = N; i < question.getTokens().size(); i++){
          NGram ngram = new NGram(aJCas);
          ngram.setCasProcessorId("NGramAnnotator");
          ngram.setConfidence(1.0);
          ngram.setBegin((question.getTokens(i - N).getBegin()));
          ngram.setEnd((question.getTokens(i).getEnd()));
          FSArray a = new FSArray(aJCas, N);
          for (int j = 0; j < N; j++){
            a.set(j, question.getTokens(i - j));
          }
          ngram.setElements(a);
          ngram.setElementType(Token.class.toString());
          ngram.addToIndexes();
        }
      }
      
    }
  }

}