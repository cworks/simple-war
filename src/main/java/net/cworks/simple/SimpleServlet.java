/**
 * Created with love by corbett.
 * User: corbett
 * Date: 6/9/14
 * Time: 10:49 PM
 */
package net.cworks.simple;

import net.cworks.json.JsonObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

public class SimpleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        JsonObject responseData = new JsonObject();
        System.out.println("Got request: " + request.getRequestURI());
        System.out.println("Headers are: ");

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> headers = request.getHeaders(headerName);
            while (headers.hasMoreElements()) {
                String headerValue = headers.nextElement();
                responseData.setString("header-" + headerName, headerValue);
            }
        }

        response.setHeader("Content-Type", "application/json; charset=UTF-8");
        responseData.setString("uri", request.getRequestURI());
        responseData.setString("path", request.getPathInfo());
        responseData.setString("date", new Date().toString());
        out.write(responseData.asString());
    }
}
