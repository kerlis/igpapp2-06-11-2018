package sistemasfireg.igp.org.sismosperu;
import java.io.Serializable;
public class DatSismo implements Serializable {
    private static final long serialVersionUID = 8799656478674716638L;
    private String simulacro;
    private String magnitud;
    private String categoria;
    private String tiporeporte;
    private String lon;
    private String profundidad;
    private String fechautc;
    private String referencia;
    private String horautc;
    private String epicentro;
    private String lat;
    private String intenso;

    public String getIntenso() {
        return intenso;
    }

    public void setIntenso(String intenso) {
        this.intenso = intenso;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getSimulacro() {
        return simulacro;
    }

    public void setSimulacro(String simulacro) {
        this.simulacro = simulacro;
    }

    public String getTiporeporte() {
        return tiporeporte;
    }

    public void setTiporeporte(String tiporeporte) {
        this.tiporeporte = tiporeporte;
    }

    public DatSismo(String simulacro, String magnitud, String categoria,
                    String tiporeporte, String lon, String profundidad, String fechautc,
                    String referencia, String horautc, String epicentro, String lat, String intenso) {
        this.simulacro = simulacro;
        this.magnitud = magnitud;
        this.categoria = categoria;
        this.tiporeporte = tiporeporte;
        this.lon = lon;
        this.profundidad = profundidad;
        this.fechautc = fechautc;
        this.referencia = referencia;
        this.horautc = horautc;
        this.epicentro = epicentro;
        this.lat = lat;
        this.intenso = intenso;
    }

    public DatSismo() {
    }
}
