package no.imr.messaging.errorresponse.domain.v1.generation;

import java.text.SimpleDateFormat;
import java.util.Date;
import no.imr.messaging.errorresponse.domain.v1.ErrorResponseType;

/**
 *
 * @author sjurl
 */
public class ErrorResponseGenerator {

    /**
     * Generate a ErrorResponseType based on input information
     *
     * @param name
     * @param originalMessage
     * @param date
     * @param throwable
     * @return
     */
    public static ErrorResponseType getErrorResponse(String name, String originalMessage, Date date, Throwable throwable) {
        ErrorResponseType response = new ErrorResponseType();
        response.setApplicationName(name);
        response.setOriginalMessage(originalMessage);

        response.setError(throwable.getMessage());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        response.setOccurenceTime(sdf.format(new Date()));
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append("\n".concat(element.toString()));
        }
        response.setStackTrace(sb.toString());
        return response;
    }
}
