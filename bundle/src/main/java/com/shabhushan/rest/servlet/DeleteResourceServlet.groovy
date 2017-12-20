package com.shabhushan.rest.servlet

import com.shabhushan.rest.service.UserService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.Reference
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingAllMethodsServlet
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
 * 1.0              24/11/17         Shashi          Create DeleteResourceServlet
 */

@Slf4j
@CompileStatic
@SlingServlet(methods = ['DELETE'], resourceTypes = ['rest/delete'])
class DeleteResourceServlet extends SlingAllMethodsServlet {

    @Reference
    UserService userService

    @Override
    protected void doDelete(
        @Nonnull SlingHttpServletRequest request,
        @Nonnull SlingHttpServletResponse response) throws ServletException, IOException {

        if (request.pathInfo == '/rest/read') {
            response.contentType = CONTENT_TYPE_HTML
            response.writer.write this.class.getResourceAsStream('DeleteResourceText.txt').text
        } else {
            response.contentType = CONTENT_TYPE_JSON
            JSONObject responseObject = new JSONObject()
            def urlTokens = request.pathInfo.split('/')

            // request is in form /rest/delete/user/1
            if (urlTokens.size() == 5) {
                int id = urlTokens[-1] as Integer

                boolean userDeleted = userService.deleteUser(id)
                responseObject.put(USER_DELETED_STATUS_KEY, userDeleted)

                response.status = userDeleted ? SC_OK : SC_BAD_REQUEST
            } else if (urlTokens.size() == 4) {
                // request is in form /rest/read/user
                boolean usersDeleted = userService.deleteAllUsers()

                responseObject.put(USERS_DELETED_STATUS_KEY, usersDeleted)

                response.status = usersDeleted ? SC_OK : SC_BAD_REQUEST
            } else {
                responseObject.put(USERS_DELETED_STATUS_KEY, false)
                responseObject.put(USER_ERROR_KEY, "Malformed URL.")

                response.status = SC_BAD_REQUEST
            }

            response.writer.write responseObject.toString()
        }
    }
}
