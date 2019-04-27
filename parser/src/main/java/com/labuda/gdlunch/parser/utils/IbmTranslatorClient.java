package com.labuda.gdlunch.parser.utils;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.labuda.gdlunch.tools.ApiKeys;

/**
 * IBM cloud translator client
 */
public class IbmTranslatorClient {

    /**
     * Date of the used version
     */
    private final static String TRANSLATOR_VERSION = "2018-05-01";

    /**
     * Gateway URL
     */
    private final static String GATEWAY_URL = "https://gateway-fra.watsonplatform.net/language-translator/api";

    /**
     * Instance of translator
     */
    private final LanguageTranslator languageTranslator;

    /**
     * Creates an instance of language translator and sets it up
     */
    public IbmTranslatorClient() {
        IamOptions options = new IamOptions.Builder()
                .apiKey(ApiKeys.getInstance().getString("ibmTranslator"))
                .build();

        languageTranslator = new LanguageTranslator(TRANSLATOR_VERSION, options);
        languageTranslator.setEndPoint(GATEWAY_URL);
    }

    /**
     * Translates given text from czech language to english and returns the translated text
     * @param text text to translate
     * @return translated text
     */
    public String translate(String text) {
        if (text == null || text.isEmpty()) {
            return "Empty :(";
        }

        TranslateOptions translateOptions = new TranslateOptions.Builder()
                .addText(text)
                .modelId("cs-en")
                .build();

        TranslationResult result = languageTranslator.translate(translateOptions)
                .execute().getResult();

        return result.getTranslations().get(0).getTranslationOutput();
    }
}
