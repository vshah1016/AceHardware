package sup.mr.geary.dataClass;

import sup.mr.geary.guiComponent.App;

import java.text.NumberFormat;
import java.util.Locale;

public class Singleton {
    public static Singleton instance = new Singleton();

    private final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
    private final Data data = new Data();
    private final App app = new App();

    public App getApp() {
        return app;
    }

    public Data getData() {
        return data;
    }

    public NumberFormat getNumberFormat() {
        return numberFormat;
    }

}
