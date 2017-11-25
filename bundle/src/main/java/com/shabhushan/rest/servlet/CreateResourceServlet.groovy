package com.shabhushan.rest.servlet

import com.shabhushan.rest.service.UserService
import org.apache.felix.scr.annotations.Reference
import org.apache.sling.commons.json.JSONObject

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
 * 1.0.1            24/11/17         Shashi          Added Usage Documentation via CreateResourceText.txt
 * 1.0.2            24/11/17         Shashi          Create User Implemented
 */

@Slf4j
@CompileStatic
@SlingServlet(methods = ['GET'], resourceTypes = ['rest/create'])
class CreateResourceServlet extends SlingSafeMethodsServlet {

    @Reference
    UserService userService

    @Override
    protected void doGet(
        @Nonnull SlingHttpServletRequest request,
        @Nonnull SlingHttpServletResponse response) throws ServletException, IOException {

        response.characterEncoding = UTF_8

        if(request.pathInfo == '/rest/create') {
            response.contentType = CONTENT_TYPE_HTML
            response.writer.write this.class.getResourceAsStream('CreateResourceText.txt').text
        } else {
            response.contentType = CONTENT_TYPE_JSON
            JSONObject responseObject = new JSONObject()
            def urlTokens = request.pathInfo.split('/')

            int id
            String name = ""
            if(urlTokens.size() == 5) {
                id = urlTokens[-2] as Integer
                name = urlTokens[-1]
            } else {
                id = urlTokens[-1] as Integer
            }

            responseObject.put(USER_CREATED_STATUS, userService.createUser(id, name) ? true: false)
            response.writer.write responseObject.toString()
        }
    }
}
