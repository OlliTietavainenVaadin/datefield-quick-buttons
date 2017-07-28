package org.vaadin.addons.datefieldquickbuttons.client;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Window;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VButton;
import com.vaadin.client.ui.VPopupCalendar;
import com.vaadin.client.ui.datefield.DateFieldConnector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.addons.datefieldquickbuttons.DateFieldQuickButtonsExtension;

@Connect(DateFieldQuickButtonsExtension.class)
public class QuickButtonsConnector extends AbstractExtensionConnector {

    private static final String BUTTON1 = "BUTTON1";
    private static final String BUTTON2 = "BUTTON2";
    VPopupCalendar dateFieldWidget;
    VButton button = new VButton();
    VButton button2 = new VButton();
    // ServerRpc is used to send events to server. Communication implementation
    // is automatically created here
    QuickButtonsServerRpc rpc = RpcProxy.create(QuickButtonsServerRpc.class, this);

    public QuickButtonsConnector() {
        // To receive RPC events from server, we register ClientRpc implementation
        if (1 < 0) {
            registerRpc(MyComponentClientRpc.class, new MyComponentClientRpc() {
                public void alert(String message) {
                    Window.alert(message);
                }
            });

            rpc.clicked(null);
        }
    }

    private native void enhance(com.google.gwt.user.client.Element element, String id)/*-{
        var that = this;
        element.onclick = function () {
            that.@org.vaadin.addons.datefieldquickbuttons.client.QuickButtonsConnector::executeRpc(Ljava/lang/String;)(id);
        }
    }-*/;

    private void executeRpc(String id) {
        dateFieldWidget.closeCalendarPanel();
        rpc.clicked(id);
    }

    @Override
    protected void extend(ServerConnector serverConnector) {
        dateFieldWidget = ((DateFieldConnector) serverConnector).getWidget();
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
