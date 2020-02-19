package org.vaadin.addons.datefieldquickbuttons;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.DateField;
import org.vaadin.addons.datefieldquickbuttons.client.QuickButtonsState;

public class DateFieldQuickButtonsExtension extends AbstractExtension {

    private static final String BUTTON1 = "BUTTON1";
    private static final String BUTTON2 = "BUTTON2";
    private DateField dateField;

    public void setCurrentDateButtonCaption(String button1Caption) {
        getState().button1caption = button1Caption;
    }

    public void setClearButtonCaption(String button2Caption) {
        getState().button2caption = button2Caption;
    }

    public void setButtonCaptions(String currentDateButtonCaption, String clearButtonCaption) {
        setCurrentDateButtonCaption(currentDateButtonCaption);
        setClearButtonCaption(clearButtonCaption);
    }

    public void setCurrentDateButtonClassName(String button1ClassName) {
        getState().button1style = button1ClassName;
    }

    public void setClearButtonClassName(String button2ClassName) {
        getState().button2style = button2ClassName;
    }

    public void setButtonClassNames(String currentDateButtonClassName, String clearButtonClassName) {
        setCurrentDateButtonClassName(currentDateButtonClassName);
        setClearButtonClassName(clearButtonClassName);
    }

    public void extend(DateField dateField) {
        this.dateField = dateField;
        super.extend(dateField);
    }

    @Override
    protected QuickButtonsState getState() {
        return (QuickButtonsState) super.getState();
    }

}
