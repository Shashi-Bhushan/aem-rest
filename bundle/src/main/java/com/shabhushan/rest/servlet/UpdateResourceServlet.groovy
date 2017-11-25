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

import static com.shabhushan.rest.constants.BasicConstants.*

/**
 * @author Shashi Bhushan
 *
 * Change History:
 * Revision         Date            Dev             Comment
 * ----------------------------------------------------------------------------------------
 * 1.0              24/11/17         Shashi          Create UpdateResourceServlet
 */

@Slf4j
@CompileStatic
@SlingServlet(methods = ['POST'], resourceTypes = ['rest/update'])
class UpdateResourceServlet extends SlingAllMethodsServlet {

    @Reference
    UserService userService

    @Override
    protected void doPost(
        @Nonnull SlingHttpServletRequest request,
        @Nonnull SlingHttpServletResponse response) throws ServletException, IOException {

        if(request.pathInfo == '/rest/read') {
            response.contentType = CONTENT_TYPE_HTML
            response.writer.write this.class.getResourceAsStream('UpdateResourceText.txt').text
            response.status = SC_OK
        } else {
            response.contentType = CONTENT_TYPE_JSON
            JSONObject responseObject = new JSONObject()
            def urlTokens = request.pathInfo.split('/')

            // request is in form /rest/update/user/1/shashi
            if(urlTokens.size() == 6) {
                int id = urlTokens[-2] as Integer
                String name = urlTokens[-1]

                boolean userUpdated = userService.updateUser(id, name)

                responseObject.put(USER_UPDATE_STATUS_KEY, userUpdated)
                response.status = userUpdated ? SC_OK : SC_BAD_REQUEST
            } else {
                responseObject.put(USER_UPDATE_STATUS_KEY, false)
                responseObject.put(USER_ERROR_KEY, "Malformed URL.")
                response.status = SC_BAD_REQUEST
            }
        }
    }
}
