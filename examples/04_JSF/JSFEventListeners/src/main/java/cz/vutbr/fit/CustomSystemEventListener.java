package cz.vutbr.fit;

import jakarta.faces.application.Application;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.PostConstructApplicationEvent;
import jakarta.faces.event.PreDestroyApplicationEvent;
import jakarta.faces.event.SystemEvent;
import jakarta.faces.event.SystemEventListener;

/**
 *
 * SystemEventListener for The application. Shows how can system event be
 * listened to.
 */
public class CustomSystemEventListener implements SystemEventListener {

    @Override
    public boolean isListenerForSource(Object value) {
        // only for Application
        return (value instanceof Application);
    }

    /**
     * Prints text to stdout on aplication start and termination.
     *
     * @param event event holding information about the system event
     * occured
     * @throws AbortProcessingException
     */
    @Override
    public void processEvent(SystemEvent event) throws AbortProcessingException {
        if (event instanceof PostConstructApplicationEvent) {
            System.out.println("Application Started. PostConstructApplicationEvent occurred!");
        }
        if (event instanceof PreDestroyApplicationEvent) {
            System.out.println("PreDestroyApplicationEvent occurred. Application is stopping.");
        }
    }
}
