package com.labuda.gdlunch.parser.utils;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;
import com.labuda.gdlunch.tools.ApiKeys;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IBM cloud TTS client
 */
public class IbmTtsClient {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(IbmTtsClient.class);

    /**
     * Voice ID
     */
    private final static String VOICE_ID = "en-US_AllisonV2Voice";

    /**
     * Audio codec definition
     */
    private final static String AUDIO_CODEC = "audio/wav";

    /**
     * Gateway URL
     */
    private final static String GATEWAY_URL = "https://stream-fra.watsonplatform.net/text-to-speech/api";

    /**
     * Instance of TTS service
     */
    private final TextToSpeech textToSpeech;

    /**
     * Creates and sets up instance of TTS service
     */
    public IbmTtsClient() {
        IamOptions options = new IamOptions.Builder()
                .apiKey(ApiKeys.getInstance().getString("ibmTts"))
                .build();

        textToSpeech = new TextToSpeech(options);
        textToSpeech.setEndPoint(GATEWAY_URL);
    }

    /**
     * Synthesizes text to a speech and saves the file into ttsAudio folder
     * @param text text to be synthesized
     */
    public void tts(String text) {
        try {
            SynthesizeOptions synthesizeOptions =
                    new SynthesizeOptions.Builder()
                            .text(text)
                            .accept(AUDIO_CODEC)
                            .voice(VOICE_ID)
                            .build();

            InputStream inputStream =
                    textToSpeech.synthesize(synthesizeOptions).execute().getResult();
            InputStream in = WaveUtils.reWriteWaveHeader(inputStream);

            File speech = new File("ttsAudio/" + text.hashCode() + ".wav");
            speech.getParentFile().mkdirs();
            OutputStream out = new FileOutputStream(speech);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            out.close();
            in.close();
            inputStream.close();
        } catch (IOException e) {
            log.error("Could not create sound file properly", e);
        }
    }

}
