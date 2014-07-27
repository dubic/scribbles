/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.servlets;

import com.dubic.scribbles.dto.JokeData;
import com.dubic.scribbles.idm.spi.IdentityServiceImpl;
import com.dubic.scribbles.models.Joke;
import com.dubic.scribbles.posts.JokeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author dubem
 */
@WebServlet(name = "PostServlet", urlPatterns = {"/posts/jokes/*", "/posts/quotes/*"})
public class PostServlet extends HttpServlet {
private final Logger log = Logger.getLogger(getClass());
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter os = response.getWriter();
        String pathInfo = request.getPathInfo();
        System.out.println("path - " + pathInfo);
        StringTokenizer t = new StringTokenizer(pathInfo, "/");
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
//        if(pathInfo.startsWith("/jokes")){
        try {
            int start = Integer.parseInt(t.nextToken());
            System.out.println("start - " + start);
            int amount = Integer.parseInt(t.nextToken());
            System.out.println("amount - " + amount);

            JokeService jokeService = applicationContext.getBean(JokeService.class);
            //load
            List<Joke> latestJokes = jokeService.getLatestPosts(start, amount);
            List<JokeData> jokeDatas = new ArrayList<JokeData>();
            for (Joke joke : latestJokes) {
                jokeDatas.add(new JokeData(joke));
            }
            GsonBuilder gb = new GsonBuilder();
            gb.disableHtmlEscaping();
            os.print(gb.create().toJson(jokeDatas));
            os.flush();

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (NoSuchElementException nsee) {
            nsee.printStackTrace();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            os.close();
        }

//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
