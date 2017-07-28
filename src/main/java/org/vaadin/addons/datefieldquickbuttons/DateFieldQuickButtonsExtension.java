package org.vaadin.addons.datefieldquickbuttons;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.DateField;
import org.vaadin.addons.datefieldquickbuttons.client.QuickButtonsServerRpc;
import org.vaadin.addons.datefieldquickbuttons.client.QuickButtonsState;

import java.util.HashMap;
import java.util.Map;

public class DateFieldQuickButtonsExtension extends AbstractExtension {

    private static final String BUTTON1 = "BUTTON1";
    private static final String BUTTON2 = "BUTTON2";
    Map<String, ClickListener> listenerMap = new HashMap<>();

    public DateFieldQuickButtonsExtension(ClickListener button1Listener,
        ClickListener button2Listener) {
        listenerMap.put(BUTTON1, button1Listener);
        listenerMap.put(BUTTON2, button2Listener);

        registerRpc(new QuickButtonsServerRpc() {
            @Override
            public void clicked(String buttonId) {
                ClickListener listener = listenerMap.get(buttonId);
                if (listener != null) {
                    listener.buttonClick();
                }
            }
        });
    }

    public void setButton1Caption(String button1Caption) {
        getState().button1caption = button1Caption;
    }

    public void setButton2Caption(String button2Caption) {
        getState().button2caption = button2Caption;
    }

    public void setButtonCaptions(String button1Caption, String button2Caption) {
        setButton1Caption(button1Caption);
        setButton2Caption(button2Caption);
    }

    public void setButton1ClassName(String button1ClassName) {
        getState().button1style = button1ClassName;
    }

    public void setButton2ClassName(String button2ClassName) {
        getState().button2style = button2ClassName;
    }

    public void setButtonClassNames(String button1ClassName, String button2ClassName) {
        setButton1ClassName(button1ClassName);
        setButton2ClassName(button2ClassName);
    }

    public void extend(DateField dateField) {
        super.extend(dateField);
    }

    @Override
    protected QuickButtonsState getState() {
        return (QuickButtonsState) super.getState();
    }

    @FunctionalInterface
    public interface ClickListener {
        void buttonClick();
    }

}
