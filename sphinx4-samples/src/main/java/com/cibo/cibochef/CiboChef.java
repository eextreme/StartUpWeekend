package com.cibo.cibochef;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

public class CiboChef {
	
	private static final String ACOUSTIC_MODEL =
	        "resource:/edu/cmu/sphinx/models/en-us/en-us";
	    private static final String DICTIONARY_PATH =
	        "resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict";
	    private static final String GRAMMAR_PATH =
	        "resource:/edu/cmu/sphinx/demo/dialog/";
	    private static final String LANGUAGE_MODEL_PATH = 
	    	"resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin";
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();

		// Set path to acoustic model.
		configuration.setAcousticModelPath(ACOUSTIC_MODEL);
		// Set path to dictionary.
		configuration.setDictionaryPath(DICTIONARY_PATH);
		// Set language model.
		configuration.setLanguageModelPath(LANGUAGE_MODEL_PATH);		
		
		LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
		// Start recognition process pruning previously cached data.
		recognizer.startRecognition(true);
		
		System.out.println("Cibo is now listening...");
		
		while (1==1) {
			SpeechResult result = recognizer.getResult(); 
			
			if (result.getWords().contains("display")) {
				recognizer.stopRecognition();
				
				System.out.println("List of Words");
	            for (WordResult r : result.getWords()) {
	                System.out.println(r);
	            }
				recognizer.startRecognition(true);
			}
			
			if (result.getWords().contains("exit")) {
				break;
			}
		}		
		
		// Pause recognition process. It can be resumed then with startRecognition(false).
		recognizer.stopRecognition();

	}

}
