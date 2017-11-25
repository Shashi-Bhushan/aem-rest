package com.shabhushan.rest.servlet

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet

import javax.annotation.Nonnull
import javax.servlet.ServletException

import static com.shabhushan.rest.constants.BasicConstants.*

/**
 * @author Shashi Bhushan
 *
 * Change History:
 * Revision         Date            Dev             Comment
 * ----------------------------------------------------------------------------------------
 * 1.0              25/11/17         Shashi
 */
@Slf4j
@CompileStatic
@SlingServlet(methods = ['GET'], resourceTypes = ['rest'])
class RootResourceServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(
        @Nonnull SlingHttpServletRequest request,
        @Nonnull SlingHttpServletResponse response) throws ServletException, IOException {

        response.contentType = CONTENT_TYPE_HTML
        response.characterEncoding = UTF_8
        response.writer.write this.class.getResourceAsStream("RootResourceText.txt").text
    }
}
