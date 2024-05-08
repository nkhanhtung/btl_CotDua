package App.Activities;

import java.util.Locale;
import javax.speech.*;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class TextSpeech {

    public void Speak(String word) {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");

            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

            SynthesizerModeDesc desc = new SynthesizerModeDesc(Locale.US);
            Synthesizer synthesizer = Central.createSynthesizer(desc);

            synthesizer.allocate();
            synthesizer.resume();

            synthesizer.speakPlainText(word, null);

            // Add an EngineListener to deallocate the Synthesizer when speaking is done
            synthesizer.addEngineListener(new EngineListener() {
                public void engineAllocated(EngineEvent event) {
                }

                @Override
                public void engineAllocatingResources(EngineEvent engineEvent) {
                }

                public void engineDeallocated(EngineEvent event) {
                }

                @Override
                public void engineDeallocatingResources(EngineEvent engineEvent) {
                }

                @Override
                public void engineError(EngineErrorEvent engineErrorEvent) {
                }

                @Override
                public void enginePaused(EngineEvent engineEvent) {
                }

                @Override
                public void engineResumed(EngineEvent engineEvent) {
                }

                public void engineError(EngineEvent event) {
                }

                public void speakableEnded(EngineEvent event) throws EngineException {
                    // Deallocate the Synthesizer when speaking is done
                    synthesizer.deallocate();
                }
            });

            // Wait until speaking is done
            while (synthesizer.getEngineModeDesc().toString().equals("Speaking")) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}