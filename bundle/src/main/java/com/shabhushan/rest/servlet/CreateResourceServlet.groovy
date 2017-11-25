package com.shabhushan.rest.servlet

import com.shabhushan.rest.service.UserService
import org.apache.felix.scr.annotations.Reference
import org.apache.sling.api.servlets.SlingAllMethodsServlet
import org.apache.sling.commons.json.JSONObject

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse

import javax.annotation.Nonnull
import javax.servlet.ServletException

import static com.shabhushan.rest.constants.BasicConstants.*

/**
 * @author Shashi Bhushan
 *
 * Change History:
 * Revision         Date            Dev             Comment
 * ----------------------------------------------------------------------------------------
 * 1.0              24/11/17         Shashi          Create CreateResourceServlet
 * 1.0.1            24/11/17         Shashi          Added Usage Documentation via CreateResourceText.txt
 * 1.0.2            24/11/17         Shashi          Create User Implemented
 */

@Slf4j
@CompileStatic
@SlingServlet(methods = ['PUT'], resourceTypes = ['rest/create'])
class CreateResourceServlet extends SlingAllMethodsServlet {

    @Reference
    UserService userService

    @Override
    protected void doPut(
        @Nonnull SlingHttpServletRequest request,
        @Nonnull SlingHttpServletResponse response) throws ServletException, IOException {

        response.characterEncoding = UTF_8

        if (request.pathInfo == '/rest/create') {
            response.contentType = CONTENT_TYPE_HTML
            response.writer.write this.class.getResourceAsStream('CreateResourceText.txt').text
            response.status = SC_OK
        } else {
            response.contentType = CONTENT_TYPE_JSON
            JSONObject responseObject = new JSONObject()
            def urlTokens = request.pathInfo.split('/')

            if (urlTokens.size() == 6) {
                int id = urlTokens[-2] as Integer
                String name = urlTokens[-1]

                boolean userCreated = userService.createUser(id, name)

                responseObject.put(USER_CREATED_STATUS_KEY, userCreated)
                response.status = userCreated ? SC_CREATED : SC_BAD_REQUEST
            } else if (urlTokens.size() == 5) {
                int id = urlTokens[-1] as Integer

                boolean userCreated = userService.createUser(id)

                responseObject.put(USER_CREATED_STATUS_KEY, userCreated)
                response.status = userCreated ? SC_CREATED : SC_BAD_REQUEST
            } else {
                responseObject.put(USER_CREATED_STATUS_KEY, false)
                responseObject.put(USER_ERROR_KEY, "Malformed URL.")
                response.status = SC_BAD_REQUEST
            }

            response.writer.write responseObject.toString()
        }
    }
}
