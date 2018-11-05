package sistemasfireg.igp.org.sismosperu.m_model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class messages {

    private  String categoria;
    private String epicentro;
    private String fechautc;
    private String horautc;
    private String intenso;
    private String lat;
    private String lon;
    private String magnitud;
    private String profundidad;
    private String referencia;
    private String simulacro;
    private String tiporeporte;

    public messages() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEpicentro() {
        return epicentro;
    }

    public void setEpicentro(String epicentro) {
        this.epicentro = epicentro;
    }

    public String getFechautc() {
        return fechautc;
    }

    public void setFechautc(String fechautc) {
        this.fechautc = fechautc;
    }

    public String getHorautc() {
        return horautc;
    }

    public void setHorautc(String horautc) {
        this.horautc = horautc;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getIntenso() {
        return intenso;
    }

    public void setIntenso(String intenso) {
        this.intenso = intenso;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }

    public String getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(String profundidad) {
        this.profundidad = profundidad;
    }

    public String getSimulacro() {
        return simulacro;
    }

    public void setSimulacro(String simulacro) {
        this.simulacro = simulacro;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTiporeporte() {
        return tiporeporte;
    }

    public void setTiporeporte(String tiporeporte) {
        this.tiporeporte = tiporeporte;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nome", epicentro);
        result.put("latitude", lat);
        result.put("longitude", lon);

        return result;
    }


}









/**package sistemasfireg.igp.org.detectasismo;


 * Created by root on 25/01/17.


 public class Objetosismos {
 }
 */







/**package sistemasfireg.igp.org.detectasismo.m_model;


 * Created by root on 25/01/17.


public class messages {
}
*/