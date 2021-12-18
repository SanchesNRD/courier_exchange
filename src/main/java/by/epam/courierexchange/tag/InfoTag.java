package by.epam.courierexchange.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;

public class InfoTag extends TagSupport{
    private static final Logger logger = LogManager.getLogger();

    @Override
    public int doStartTag() throws JspException {
        String footer = """
        <footer class="main-footer">
            <div class="pull-right hidden-xs">
                <b><fmt:message key="footer.version"/></b>2.4.0
            </div>
            <div>Developed by Gilep A.A</div>
            <fmt:message key="footer.rights"/>
        </footer>
        """;

        try{
            JspWriter out = pageContext.getOut();
            out.write(footer);
        } catch (IOException e) {
            logger.error("Exception during recording");
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
