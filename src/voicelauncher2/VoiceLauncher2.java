/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voicelauncher2;

//Imports
import edu.cmu.sphinx.decoder.adaptation.Stats;
import edu.cmu.sphinx.decoder.adaptation.Transform;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.io.IOException;

public class VoiceLauncher2 {
    public static void main(String[] args) throws IOException {
        //Configuration Object
        Configuration configuration = new Configuration();

        // Set path to the acoustic model.
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        // Set path to the dictionary.
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        // Set path to the language model.
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        
        //Recognizer object, Pass the Configuration object
        LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);
        
        //Start Recognition Process (The bool parameter clears the previous cache if true)
        recognize.startRecognition(true);
        
        //Creating SpeechResult object
        SpeechResult result;
        
        //Check if recognizer recognized the speech
        while ((result = recognize.getResult()) != null) {
            
            //Get the recognized speech
            String command = result.getHypothesis();
            String work = null;
            Process p;
            System.out.println(command);
            
            //Some Extra Commands from my Corpus File
            if(command.equalsIgnoreCase("open search")) {
                  p = Runtime.getRuntime().exec("cmd /c start chrome.exe www.google.com");
            }
            else if (command.equalsIgnoreCase("new tab")) {
                  p = Runtime.getRuntime().exec("cmd /c start chrome.exe/new-window");
            }
            else if (command.equalsIgnoreCase("open mail")) {
                p = Runtime.getRuntime().exec("cmd /c start chrome.exe www.gmail.com");
            }
            else if (command.equalsIgnoreCase("open browser")) {
                  p = Runtime.getRuntime().exec("cmd /c start chrome.exe/new-window");
            }
            else if(command.equalsIgnoreCase("close browser")){
                p = Runtime.getRuntime().exec("cmd /c taskkill chrome.exe");
            }
            else if (command.equalsIgnoreCase("file manager")) {
                p = Runtime.getRuntime().exec("cmd /c start c:");
            } 
            else if(command.equalsIgnoreCase("no")){
                p = Runtime.getRuntime().exec("cmd /c start notepad.exe");
            }
            
            if(work != null) {
                p = Runtime.getRuntime().exec(work);
            }
        }
        
    }
    
}