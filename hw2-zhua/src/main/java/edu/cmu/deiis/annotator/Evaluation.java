package edu.cmu.deiis.annotator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.AnswerScore;
import edu.cmu.deiis.types.EvaluationType;

public class Evaluation extends JCasAnnotator_ImplBase{

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    FSIndex scoreIndex = aJCas.getAnnotationIndex(AnswerScore.type);
    Iterator<AnswerScore> scoreIter = scoreIndex.iterator();
    List<AnswerScore> scores = new ArrayList<AnswerScore>();
    int N = 0;
    while (scoreIter.hasNext()){
      AnswerScore score = scoreIter.next();
      if (score.getAnswer().getIsCorrect())
        N++;
      scores.add(score);
    }
    Collections.sort(scores, new Comparator<AnswerScore>(){
      public int compare(AnswerScore s1, AnswerScore s2){
        if (s1.getScore() > s2.getScore())
          return -1;
        else
          return 1;
      }
    });
    int trueCorrect = 0;
    for (int i = 0; i < N; i++){
      if (scores.get(i).getAnswer().getIsCorrect())
        trueCorrect++;
    }
    EvaluationType evaluation = new EvaluationType(aJCas);
    evaluation.setPrecisionAtN((double)trueCorrect/N);
    String docText = aJCas.getDocumentText();
    evaluation.setBegin(0);
    evaluation.setEnd(docText.length());
    evaluation.addToIndexes();
  }
}
