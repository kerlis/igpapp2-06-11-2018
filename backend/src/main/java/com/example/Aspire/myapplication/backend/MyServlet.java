/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.example.Aspire.myapplication.backend;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class
MyServlet extends HttpServlet {
    static Logger Log = Logger.getLogger("com.example.Aspire.myapplication.backend.MyServlet");

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException {
        ejecutar(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException {
        ejecutar(req,resp);
    }

    protected void ejecutar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        Incidencia xsismo = new Incidencia();
        xsismo.setFechautc(req.getParameter("lfecha"));
        xsismo.setHorautc(req.getParameter("lhora"));
        xsismo.setEpicentro(req.getParameter("lepicentro"));
        xsismo.setMagnitud(req.getParameter("lmagnitud"));
        xsismo.setCategoria(req.getParameter("rpublicar"));
        xsismo.setProfundidad(req.getParameter("lprofundo"));
        xsismo.setLat(req.getParameter("latitud"));
        xsismo.setLon(req.getParameter("longitud"));
        xsismo.setReferencia(req.getParameter("lreferencia"));
        xsismo.setIntenso(req.getParameter("intenso"));


        resp.setContentType("text/plain");
        String ruta = getServletConfig().getServletContext().getRealPath ("/WEB-INF/");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream(ruta+"/gestionsismo-5485039fefc3.json"))
                .setDatabaseUrl("https://gestionsismo-155718.firebaseio.com/")
                .build();
        try {
            FirebaseApp.getInstance();
        }
        catch (Exception error){
            Log.info("no existe...");
        }
        try {
            FirebaseApp.initializeApp(options);
        }
        catch(Exception error){
            Log.info("ya existe...");
        }

        procesar_datos(xsismo);
        resp.getWriter().println("datos registrados");
    }

    private void procesar_datos(Incidencia seismo) {
        // TODO Auto-generated method stub
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("messages");

        // Create a new child with a auto-generated ID.
        DatabaseReference childRef = myRef.push();

        // Set the child's data to the value passed in from the text box.
        childRef.setValue(seismo);
    }


}
