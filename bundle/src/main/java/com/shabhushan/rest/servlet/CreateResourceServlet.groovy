package com.shabhushan.rest.servlet

import static com.shabhushan.rest.constants.BasicConstants.*
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet

import javax.annotation.Nonnull
import javax.servlet.ServletException

/**
 * @author Shashi Bhushan
 *
 * Change History:
 * Revision         Date            Dev             Comment
 * ----------------------------------------------------------------------------------------
 * 1.0              24/11/17         Shashi          Create CreateResourceServlet
 */

@Slf4j
@CompileStatic
@SlingServlet(methods = ['GET'], resourceTypes = ['rest/create'])
class CreateResourceServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(
        @Nonnull SlingHttpServletRequest request,
        @Nonnull SlingHttpServletResponse response) throws ServletException, IOException {

        response.contentType = CONTENT_TYPE_JSON
        response.characterEncoding = UTF_8
        response.writer.write("Create")
    }
}
