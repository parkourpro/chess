package com.example.chess.chess.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localization
{
    private static final Locale LOCALE = Locale.getDefault();
    private static ResourceBundle resourceBundle;

    static
    {
        try
        {
            resourceBundle = ResourceBundle.getBundle("com/example/chess/lang/chess", LOCALE);
        }
        catch (Exception exception)
        {
            resourceBundle = ResourceBundle.getBundle("com/example/chess/lang/chess", Locale.US);
        }
    }

    public static String translate(String key)
    {
        return resourceBundle.getString(key);
    }

    private Localization()
    {
        throw new IllegalStateException("You should not instantiate this class!");
    }
}
