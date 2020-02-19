package org.vaadin.addons.datefieldquickbuttons.client;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.i18n.client.TimeZoneInfo;
import com.vaadin.client.DateTimeService;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VButton;
import com.vaadin.client.ui.VPopupCalendar;
import com.vaadin.client.ui.datefield.DateFieldConnector;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.datefield.LocalDateFieldState;
import org.vaadin.addons.datefieldquickbuttons.DateFieldQuickButtonsExtension;

import java.util.Date;

@Connect(DateFieldQuickButtonsExtension.class)
public class QuickButtonsConnector extends AbstractExtensionConnector {

    private static final String BUTTON1 = "BUTTON1";
    private static final String BUTTON2 = "BUTTON2";
    VPopupCalendar dateFieldWidget;
    VButton button = new VButton();
    VButton button2 = new VButton();

    private TimeZone timeZone = null;

    public QuickButtonsConnector() {

    }

    private native void enhance(com.google.gwt.user.client.Element element, String id)/*-{
        var that = this;
        element.onclick = function () {
            that.@org.vaadin.addons.datefieldquickbuttons.client.QuickButtonsConnector::executeRpc(Ljava/lang/String;)(id);
        }
    }-*/;

    private void executeRpc(String id) {
        if (BUTTON1.equalsIgnoreCase(id)) {
            DateTimeService dts = dateFieldWidget.getDateTimeService();
            String formatString = dateFieldWidget.getFormatString();
            String dateText = dts.formatDate(new Date(), formatString, timeZone);
            dateFieldWidget.text.setValue(dateText, true);
        } else {
            dateFieldWidget.text.setValue(null, true);
        }
        dateFieldWidget.updateBufferedValues();
        dateFieldWidget.closeCalendarPanel();


        //rpc.clicked(id);
    }


    @Override
    protected void extend(ServerConnector serverConnector) {
        DateFieldConnector connector = (DateFieldConnector) serverConnector;
        dateFieldWidget = ((DateFieldConnector) serverConnector).getWidget();
        LocalDateFieldState state = connector.getState();
        String timeZoneJSON = state.timeZoneJSON;
        if (timeZoneJSON != null) {
            TimeZoneInfo timeZoneInfo = TimeZoneInfo
                    .buildTimeZoneData(timeZoneJSON);
            timeZone = TimeZone.createTimeZone(timeZoneInfo);
        } else {
            timeZone = null;
        }
        enhance(button.getElement(), BUTTON1);
        enhance(button2.getElement(), BUTTON2);
        DivElement wrapper = Document.get().createDivElement();
        wrapper.addClassName("quick-buttons-wrapper");
        wrapper.appendChild(button.getElement());
        wrapper.appendChild(button2.getElement());
        dateFieldWidget.popup.getElement().appendChild(wrapper);

    }

    @Override
    public QuickButtonsState getState() {
        return (QuickButtonsState) super.getState();
    }

    // Whenever the state changes in the server-side, this method is called
    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        button.setText(getState().button1caption);
        button.setStyleName(getState().button1style, true);
        button2.setText(getState().button2caption);
        button2.setStyleName(getState().button2style, true);

    }

}
