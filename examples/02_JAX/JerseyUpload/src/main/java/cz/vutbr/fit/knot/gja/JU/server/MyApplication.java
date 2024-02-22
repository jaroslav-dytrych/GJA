/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych dytrych@fit.vutbr.cz
 * File: MyApplication.java
 * Description: Example application file for Jersey2 file upload
 *              Adapted from https://github.com/aruld/jersey2-multipart-sample
 */

/**
 * @file MyApplication.java
 *
 * @brief Example application file
 */

package cz.vutbr.fit.knot.gja.JU.server;

import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import java.util.HashSet;
import java.util.Set;

/**
 * Portable JAX-RS application.
 */
public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        // register resources and features
        classes.add(MultiPartFeature.class);
        classes.add(FileUploadService.class);
        return classes;
    }
}