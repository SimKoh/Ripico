package ripico.ui;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Wett {




    StringProperty sportart;
    StringProperty  mannschaft1;
    StringProperty  mannschaft2;
    FloatProperty quote;

    StringProperty zeit;

    public Wett() {
    }

    public Wett(String art, String mann1, String mann2, float quote, String zeit) {
        this.sportart = new SimpleStringProperty(art);
        this.mannschaft1 = new SimpleStringProperty(mann1);
        this.mannschaft2 = new SimpleStringProperty(mann2);
        this.zeit = new SimpleStringProperty(zeit);
        this.quote = new SimpleFloatProperty(quote);
    }

    public String getSportart() {
        return sportart.get();
    }

    public StringProperty sportartProperty() {
        return sportart;
    }

    public void setSportart(String sportart) {
        this.sportart.set(sportart);
    }

    public String getMannschaft1() {
        return mannschaft1.get();
    }

    public StringProperty mannschaft1Property() {
        return mannschaft1;
    }

    public void setMannschaft1(String mannschaft1) {
        this.mannschaft1.set(mannschaft1);
    }

    public String getMannschaft2() {
        return mannschaft2.get();
    }

    public StringProperty mannschaft2Property() {
        return mannschaft2;
    }

    public void setMannschaft2(String mannschaft2) {
        this.mannschaft2.set(mannschaft2);
    }

    public float getQuote() {
        return quote.get();
    }

    public FloatProperty quoteProperty() {
        return quote;
    }

    public void setQuote(float quote) {
        this.quote.set(quote);
    }


    public String getZeit() {
        return zeit.get();
    }

    public StringProperty zeitProperty() {
        return zeit;
    }

    public void setZeit(String zeit) {
        this.zeit.set(zeit);
    }




}
