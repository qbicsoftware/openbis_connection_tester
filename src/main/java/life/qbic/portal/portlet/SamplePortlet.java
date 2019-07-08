package life.qbic.portal.portlet;

import ch.systemsx.cisd.openbis.generic.shared.basic.dto.Space;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Layout;
import com.vaadin.ui.HorizontalLayout;

import com.vaadin.ui.TextField;
import life.qbic.openbis.openbisclient.OpenBisClient;
import life.qbic.portal.utils.ConfigurationManager;
import life.qbic.portal.utils.ConfigurationManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Entry point for portlet connection-tester. This class derives from {@link QBiCPortletUI}, which is found in the {@code portal-utils-lib} library.
 * 
 * @see <a href=https://github.com/qbicsoftware/portal-utils-lib>portal-utils-lib</a>
 */
@Theme("mytheme")
@SuppressWarnings("serial")
@Widgetset("life.qbic.portal.portlet.AppWidgetSet")
public class SamplePortlet extends QBiCPortletUI {

    private static final Logger LOG = LogManager.getLogger(SamplePortlet.class);

    @Override
    protected Layout getPortletContent(final VaadinRequest request) {
        LOG.info("Generating content for {}", SamplePortlet.class);


        ConfigurationManager config = ConfigurationManagerFactory.getInstance();
        StringBuilder stringBuilder = new StringBuilder()
        OpenBisClient obis = new OpenBisClient(config.getDataSourceUser(),
                config.getDataSourcePassword(), config.getDataSourceUrl());
        obis.login();
        final TextField field = new TextField();
        List<String> spaces = obis.listSpaces();
        for (String space : spaces) {
            stringBuilder.append(space);
            stringBuilder.append("\n");
        }

        field.setValue(stringBuilder.toString());
        // TODO: generate content for your portlet
        //       this method returns any non-null layout to avoid a NullPointerException later on
        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(field);

        return layout;
    }    
}
