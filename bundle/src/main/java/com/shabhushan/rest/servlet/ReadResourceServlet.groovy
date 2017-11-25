package com.shabhushan.rest.servlet

import com.shabhushan.rest.service.UserService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.Reference
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.apache.sling.commons.json.JSONObject

import javax.annotation.Nonnull
import javax.servlet.ServletException

import static com.shabhushan.rest.constants.BasicConstants.CONTENT_TYPE_HTML
import static com.shabhushan.rest.constants.BasicConstants.CONTENT_TYPE_JSON
import static com.shabhushan.rest.constants.BasicConstants.USER_CREATED_STATUS_KEY

/**
 * @author Shashi Bhushan
 *
 * Change History:
 * Revision         Date            Dev             Comment
 * ----------------------------------------------------------------------------------------
 * 1.0              24/11/17         Shashi          Create CreateResourceServlet
 * 1.0.1            24/11/17         Shashi          Added Usage Documentation via ReadResourceText.txt
 * 1.0.2            24/11/17         Shashi          Read user and ReadAllUsers Implemented
 */

@Slf4j
@CompileStatic
@SlingServlet(methods = ['GET'], resourceTypes = ['rest/read'])
class ReadResourceServlet extends SlingSafeMethodsServlet {

    @Reference
    UserService userService

    @Override
    protected void doGet(
        @Nonnull SlingHttpServletRequest request,
        @Nonnull SlingHttpServletResponse response) throws ServletException, IOException {

        if(request.pathInfo == '/rest/read') {
            response.contentType = CONTENT_TYPE_HTML
            response.writer.write this.class.getResourceAsStream('ReadResourceText.txt').text
        } else {
            response.contentType = CONTENT_TYPE_JSON
            def urlTokens = request.pathInfo.split('/')

            // request is in form /rest/read/user/1
            if(urlTokens.size() == 5) {
                int id = urlTokens[-1] as Integer

                response.writer.write userService.readUser(id)
            } else if(urlTokens.size() == 4) {
                // request is in form /rest/read/user
                response.writer.write userService.readAllUsers()
            }
        }
    }
}
