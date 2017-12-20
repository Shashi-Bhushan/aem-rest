package com.shabhushan.rest.servlet

import com.shabhushan.rest.resource.RandomizeResource
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
import static com.shabhushan.rest.constants.BasicConstants.SC_BAD_REQUEST
import static com.shabhushan.rest.constants.BasicConstants.SC_OK
import static com.shabhushan.rest.constants.BasicConstants.USER_CREATED_STATUS_KEY
import static com.shabhushan.rest.constants.BasicConstants.USER_UPDATE_STATUS_KEY

/**
 * @author Shashi Bhushan
 *
 * Change History:
 * Revision         Date            Dev             Comment
 * ----------------------------------------------------------------------------------------
 *
 */
@Slf4j
@CompileStatic
@SlingServlet(methods = ['GET'], resourceTypes = ['rest/randomize'])
class RandomizeResourceServlet extends SlingSafeMethodsServlet {

    @Reference
    UserService userService

    @Override
    protected void doGet(
        @Nonnull SlingHttpServletRequest request,
        @Nonnull SlingHttpServletResponse response) throws ServletException, IOException {

        if (request.pathInfo == '/rest/randomize') {
            response.contentType = CONTENT_TYPE_HTML
            response.writer.write this.class.getResourceAsStream('RandomizeResourceText.txt').text
            response.status = SC_OK
        } else {
            response.contentType = CONTENT_TYPE_JSON
            JSONObject responseObject = new JSONObject()
            def urlTokens = request.pathInfo.split('/')

            // request is in form /rest/randomize/10
            if (urlTokens.size() == 4) {
                int count = urlTokens[-1] as Integer

                boolean usersCreated = userService.createRandomUsers(count)

                responseObject.put(USER_CREATED_STATUS_KEY, usersCreated)

                response.writer.write responseObject.toString()
                response.status = usersCreated ? SC_OK : SC_BAD_REQUEST
            }
        }
    }
}
