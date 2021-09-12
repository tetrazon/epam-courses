package by.training.task08.dao.handler;

import lombok.extern.log4j.Log4j2;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

@Log4j2
public class FlowerErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) {
        log.warn(getLineColumnNumber(e) + "-" + e.getMessage());
    }

    public void error(SAXParseException e) {
        log.error(getLineColumnNumber(e) + " - " + e.getMessage());
    }
    public void fatalError(SAXParseException e) {
        log.fatal(getLineColumnNumber(e) + " - " + e.getMessage());
    }
    private String getLineColumnNumber(SAXParseException e) {
        // determine line and position of error
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
   /* public void warning(SAXParseException e) {
        System.out.println((getLineColumnNumber(e) + "-" + e.getMessage()));
    }
    public void error(SAXParseException e) {
        System.out.println(getLineColumnNumber(e) + " - " + e.getMessage());
    }
    public void fatalError(SAXParseException e) {
        System.out.println(getLineColumnNumber(e) + " - " + e.getMessage());
    }
    private String getLineColumnNumber(SAXParseException e) {
        // determine line and position of error
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }*/
}
