package sistemasfireg.igp.org.sismosperu.m_ui;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import sistemasfireg.igp.org.sismosperu.DetailActivity;
import sistemasfireg.igp.org.sismosperu.R;
import sistemasfireg.igp.org.sismosperu.m_model.messages;

public class CustomAdapter extends BaseAdapter {
    Double valor;
    String magnitud, magnitud2,ubicaciontxt,ubicacion_texto,ubi,caden;
    String gt,uno,dos,tres;
    String horax,horax2,fechax,fechax2,profundidax,profundidax2,intensidadx, intensidadx2;

    String magnitudnull,magnitud3;
    TextView asignacion;
    int horas_resta,horas,minutos,segundos;
    public Context c;
    private ArrayList<messages> objetosismos;

    DatabaseReference db;
    private FirebaseDatabase mFirebaseInstance;


    public CustomAdapter(Context c, ArrayList<messages> objetosismos) {
        this.c = c;
        this.objetosismos = objetosismos;
        FirebaseDatabase.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        db= FirebaseDatabase.getInstance().getReference();
        db = database.getReference("messages");
        db.keepSynced(true);
    }




    @Override
    public int getCount() {
        return objetosismos.size();
    }

    @Override
    public Object getItem(int pos) {
        return objetosismos.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
        }

        asignacion = (TextView) convertView.findViewById(R.id.asignacion);

        TextView fecha = (TextView) convertView.findViewById(R.id.fecha);
        TextView ubicacion = (TextView) convertView.findViewById(R.id.ubicacion);
        TextView profundidad = (TextView) convertView.findViewById(R.id.profundidad);

        final messages s= (messages) this.getItem(position);

        //fecha.setText(s.getFechautc()+"  "+s.getHorautc());
        // String ubi = s.getReferencia();



        //evaluar dato referencia
        ubicaciontxt = s.getReferencia();
        if (ubicaciontxt.length() == 0){
            //if (ubicaciontxt == null){
            ubi ="nulo";
        }
        else{
            ubi = s.getReferencia();
        }
        ubicacion_texto = ubi.replace("-","\n");
        ubicacion.setText(ubicacion_texto);


        //evaluar dato profundidad
        profundidax = s.getProfundidad();
        if (profundidax.length() == 0){
            profundidax2 = " - - ";
        }
        else{
            profundidax2 = s.getProfundidad();
        }

        //evaluar dato intensidad
        intensidadx = s.getIntenso();
        if (intensidadx.length() == 0){
            intensidadx2 = " - - ";
        }
        else{
            intensidadx2 = s.getIntenso();
        }

        profundidad.setText("Prof : "+profundidax2+"km"+" / "+"Imax : "+intensidadx2);

        //profundidad.setText("Prof : "+s.getProfundidad()+"km"+" / "+"Imax : "+s.getIntenso());



        //evaluar dato magnitud
        magnitud = s.getMagnitud();
        double w;
        try {
            w = new Double(magnitud);
        } catch (NumberFormatException e) {
            w = 0;
        }

        if (w == 0){
            magnitud2 = "0";
            asignacion.setText("0");
        }
        else
        {
            magnitud2 = s.getMagnitud();
            asignacion.setText(s.getMagnitud());
        }



        //evaluar dato hora
        horax = s.getHorautc();

        if (horax.length() == 0){
            horax2 = " - - ";
        }
        else{
            horax2 = s.getHorautc();
        }



        //evaluar dato fecha
        fechax = s.getFechautc();
        if (fechax.length() == 0){
            fechax2 = " - - ";
        }
        else{
            fechax2 = s.getFechautc();
        }


        /*
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mm:ss");
            Date fecha_string = dateFormat.parse(horax.toString());
            horas = fecha_string.getHours();
            minutos = fecha_string.getMinutes();
            segundos = fecha_string.getSeconds();
            horas_resta = horas - 5;
            caden = dateFormat2.format(fecha_string);
            Log.e("Time", caden);
        } catch (ParseException e) {
            fecha = fecha;
        }

        if (horas_resta < 10){
            uno = String.valueOf("0"+horas_resta).toString();

        }
        else{
            uno = String.valueOf(horas_resta).toString();

        }



        if (minutos < 10){
            dos = String.valueOf("0"+minutos).toString();

        }
        else{
            dos = String.valueOf(minutos).toString();


        }


        if (segundos < 10){
            tres = String.valueOf("0"+segundos).toString();

        }
        else{
            tres = String.valueOf(segundos).toString();


        }
        */


      //  fecha.setText(s.getFechautc()+"  "+uno+":"+dos+":"+tres);


//        fecha.setText(s.getFechautc()+"   "+s.getHorautc());

        fecha.setText(fechax2+"   "+horax2);

        Double val = Double.parseDouble(magnitud2);
       // asignacion.setText(magnitud);

        if (val < 4.5){

            asignacion.setBackgroundResource(R.drawable.circuloverde);
        }

        else if (val >= 4.5 && val <= 6.0){
            asignacion.setBackgroundResource(R.drawable.circuloamarillo);

        }


        else if (val > 6){
            asignacion.setBackgroundResource(R.drawable.circulorojo);

        }



        /*
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
        */

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL
                openDetailActivity(s.getCategoria(),
                        s.getEpicentro(),
                        s.getFechautc(),
                        s.getHorautc(),
                        s.getIntenso(),
                        s.getLat(),
                        s.getLon(),
                        s.getMagnitud(),
                        s.getProfundidad(),
                        s.getReferencia(),
                        s.getSimulacro(),
                        s.getTiporeporte());
            }
        });

        return convertView;
    }
    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c,DetailActivity.class);
        i.putExtra("CATEGORIA",details[0]);
        i.putExtra("EPICENTRO",details[1]);
        i.putExtra("FECHA",details[2]);
        i.putExtra("HORA",details[3]);
        i.putExtra("INTENSIDAD",details[4]);
        i.putExtra("LATITUD",details[5]);
        i.putExtra("LONGITUD",details[6]);
        i.putExtra("MAGNITUD",details[7]);
        i.putExtra("PROFUNDIDAD",details[8]);
        i.putExtra("REFERENCIA",details[9]);
        i.putExtra("SIMULACRO",details[10]);
        i.putExtra("TIPOREPORTE",details[11]);
        c.startActivity(i);
    }
}














