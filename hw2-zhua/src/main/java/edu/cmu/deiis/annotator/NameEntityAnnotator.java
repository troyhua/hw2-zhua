package edu.cmu.deiis.annotator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.aae.client.UimaASProcessStatus;
import org.apache.uima.aae.client.UimaAsBaseCallbackListener;
import org.apache.uima.aae.client.UimaAsynchronousEngine;
import org.apache.uima.aae.monitor.statistics.AnalysisEnginePerformanceMetrics;
import org.apache.uima.adapter.jms.client.BaseUIMAAsynchronousEngine_impl;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.collection.EntityProcessStatus;
import org.apache.uima.jcas.JCas;
import org.cleartk.ne.type.NamedEntityMention;

public class NameEntityAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    UimaAsynchronousEngine uimaAsEngine = new BaseUIMAAsynchronousEngine_impl();
    uimaAsEngine.addStatusCallbackListener(new MyStatusCallbackListener(uimaAsEngine));
    Map<String, Object> appCtx = new HashMap<String, Object>();
    // Add Broker URI on local machine
    appCtx.put(UimaAsynchronousEngine.ServerUri, "tcp://mu.lti.cs.cmu.edu:61616");
    // Add Queue Name
    appCtx.put(UimaAsynchronousEngine.Endpoint, "ScnlpQueue");
    // Add the Cas Pool Size
    appCtx.put(UimaAsynchronousEngine.CasPoolSize, 2);
    // initialize
    try {
      uimaAsEngine.initialize(appCtx);

      CAS cas;

      cas = uimaAsEngine.getCAS();
      // Initialize it with input data
      cas.setDocumentText("Some text to pass to this service.");
      // Send Cas to service for processing
      uimaAsEngine.sendCAS(cas);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

class MyStatusCallbackListener extends UimaAsBaseCallbackListener {
  private UimaAsynchronousEngine uimaAsEngine;

  public MyStatusCallbackListener(UimaAsynchronousEngine uimaAsEngine) {
    this.uimaAsEngine = uimaAsEngine;
  }

  @Override
  public void entityProcessComplete(CAS aCas, EntityProcessStatus aStatus) {
    if (aStatus != null && aStatus.isException()) {
      List<Exception> exceptions = aStatus.getExceptions();
      for (int i = 0; i < exceptions.size(); i++) {
        ((Throwable) exceptions.get(i)).printStackTrace();
      }
      try {
        uimaAsEngine.stop();
      } catch (Exception e) {
        e.printStackTrace();
      }
      return;
    }

    // Process the retrieved Cas here
    if (aStatus instanceof UimaASProcessStatus) {
      String casReferenceId = ((UimaASProcessStatus) aStatus).getCasReferenceId();
      List<AnalysisEnginePerformanceMetrics> metrics = ((UimaASProcessStatus) aStatus)
              .getPerformanceMetricsList();
    }
    JCas jcas;
    try {
      jcas = aCas.getJCas();
      FSIndex neIndex = jcas.getAnnotationIndex(NamedEntityMention.type);
    } catch (CASException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // ...
  }

  // Add other required callback methods below...

}
