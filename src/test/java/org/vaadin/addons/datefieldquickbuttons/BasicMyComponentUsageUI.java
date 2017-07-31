package org.vaadin.addons.datefieldquickbuttons;

import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import org.vaadin.addonhelpers.AbstractTest;

import java.time.LocalDate;

/**
 * Add many of these with different configurations, combine with different components, for regressions and also make them dynamic if needed.
 */
public class BasicMyComponentUsageUI extends AbstractTest {

    @Override
    public Component getTestComponent() {
        DateField df = new DateField();

        DateFieldQuickButtonsExtension e = new DateFieldQuickButtonsExtension(() -> df.setValue(LocalDate.now()), () -> df.setValue(null));
        e.setButtonCaptions("Now", "Reset");
        e.setButtonClassNames("quick-button-1", "quick-button-2");
        e.extend(df);
        return df;
    }

}
