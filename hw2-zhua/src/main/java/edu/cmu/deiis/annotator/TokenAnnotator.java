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
import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.Token;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.objectbank.TokenizerFactory;
import edu.stanford.nlp.process.PTBTokenizer.PTBTokenizerFactory;
import edu.stanford.nlp.process.Tokenizer;

public class TokenAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {

    TokenizerFactory<Word> factory = PTBTokenizerFactory.newTokenizerFactory();
    
    FSIndex answerIndex = aJCas.getAnnotationIndex(Answer.type);
    Iterator<Answer> answerIter = answerIndex.iterator();
    while (answerIter.hasNext()) {
      Answer ans = (Answer) answerIter.next();
      Tokenizer<Word> tokenizer = factory.getTokenizer(new StringReader(ans.getCoveredText()));
      List<Token> l = new ArrayList<Token>();
      while (tokenizer.hasNext()) {
        Word word = tokenizer.next();
        Token token = new Token(aJCas);
        token.setCasProcessorId("TokenAnnotator");
        token.setConfidence(1.0);
        token.setBegin(ans.getBegin() + word.beginPosition());
        token.setEnd(ans.getBegin() + word.endPosition());
        token.addToIndexes();
        l.add(token);
      }
      FSArray a = new FSArray(aJCas, l.size());
      for (int i = 0; i < l.size(); i++){
        a.set(i, l.get(i));
      }
      ans.setTokens(a);
    }

    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
    Iterator<Question> questionIter = questionIndex.iterator();
    while (questionIter.hasNext()) {
      Question question = (Question) questionIter.next();
      Tokenizer<Word> tokenizer = factory.getTokenizer(new StringReader(question.getCoveredText()));
      List<Token> l = new ArrayList<Token>();
      while (tokenizer.hasNext()) {
        Word word = tokenizer.next();
        Token token = new Token(aJCas);
        token.setCasProcessorId("TokenAnnotator");
        token.setConfidence(1.0);
        token.setBegin(question.getBegin() + word.beginPosition());
        token.setEnd(question.getBegin() + word.endPosition());
        token.addToIndexes();
        l.add(token);
      }
      FSArray a = new FSArray(aJCas, l.size());
      for (int i = 0; i < l.size(); i++)
        a.set(i, l.get(i));
      question.setTokens(a);
    }
  }
}
