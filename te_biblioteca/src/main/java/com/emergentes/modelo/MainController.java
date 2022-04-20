package com.emergentes.modelo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
    
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        libro objlibro = new libro();
        int id;
        int pos;
        String opcion = request.getParameter("op");
        String op = (opcion != null)? request.getParameter("op"):"view";
        if (op.equals("nuevo")){
            HttpSession ses = request.getSession();
            gestor_libro agenda = (gestor_libro) ses.getAttribute("agenda");
            objlibro.setId(agenda.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miTarea", objlibro);
            request.getRequestDispatcher("editar_libro.jsp").forward(request, response);
        }
        if (op.equals("modificar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            gestor_libro agenda = (gestor_libro) ses.getAttribute("agenda");
            pos = agenda.ubicarlibro(id);
            objlibro = agenda.getLista().get(pos);
            request.setAttribute("op", op);
            request.setAttribute("miTarea", objlibro);
            request.getRequestDispatcher("editar_libro.jsp").forward(request, response);
        }
        if (op.equals("eliminar")){
            id = Integer.parseInt(request.getParameter("id"));
             HttpSession ses = request.getSession();
            gestor_libro agenda = (gestor_libro) ses.getAttribute("agenda");
            pos = agenda.ubicarlibro(id);
            agenda.eliminarlibro(pos);
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
        }
        if(op.equals("view")){
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       libro objlibro = new libro();
        int pos;
        String op = request.getParameter("op");
        if(op.equals("grabar")){
            objlibro.setId(Integer.parseInt(request.getParameter("id")));
            objlibro.setTitulo(request.getParameter("titulo"));
            objlibro.setAutor(request.getParameter("autor"));
            objlibro.setDisponible(request.getParameter("disponible"));
            objlibro.setCategoria(request.getParameter("categoria"));
            HttpSession ses = request.getSession();
            gestor_libro agenda = (gestor_libro) ses.getAttribute("agenda");
            String opg=request.getParameter("opg");
            if (opg.equals("nuevo")){
                agenda.insertarlibro(objlibro);
            }else{
                pos=agenda.ubicarlibro(objlibro.getId());
                agenda.modificarlibro(pos, objlibro);
            }
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

