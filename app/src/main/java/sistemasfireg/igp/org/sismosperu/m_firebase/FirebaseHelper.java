package sistemasfireg.igp.org.sismosperu.m_firebase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

import sistemasfireg.igp.org.sismosperu.m_model.messages;


public class FirebaseHelper {
    //  static Firebase myFirebaseRef;

    private DatabaseReference db;
    private FirebaseDatabase mFirebaseInstance;

    //Boolean saved=null;
    ArrayList<messages> objetosismos=new ArrayList<>();


    public FirebaseHelper(DatabaseReference db) {
        // myFirebaseRef = new Firebase("https://proyecto1-97897.firebaseio.com");
        this.db = db;
    }




    /*
    ///para guardar datos en la base de datos firebase
    public Boolean save(messages objetosismo)
    {
        if(objetosismo==null){
            saved=false;
        }
        else
        {
            try
            {
                db.child("messages").push().setValue(objetosismo);
                saved=true;
            }
            catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }
    */







    //  mFirebaseDatabase.orderByChild("fechautc").limitToLast(1).addChildEventListener(new ChildEventListener() {

    private void fetchData(DataSnapshot dataSnapshot)
    {
        //    objetosismos.clear();

        //for (DataSnapshot ds : dataSnapshot.getChildren())
        // {
        messages objetosismo = dataSnapshot.getValue(messages.class);
        objetosismos.add(objetosismo);
        // Collections.reverse(objetosismos);
        //.
        //
        //   Collections.reverseOrder((Comparator<Object>) objetosismos);

        // }



/*
        objetosismos.clear();
       for (DataSnapshot ds : dataSnapshot.getChildren())
         {
        messages objetosismo = dataSnapshot.getValue(messages.class);
        objetosismos.add(objetosismo);
        Collections.reverse(objetosismos);
       }
        */



    }



    public ArrayList<messages> retrieve() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        db = mFirebaseInstance.getReference("messages");
        // String query = "31/01/2017";
        //db.orderByChild("fechautc"+"horautc").startAt(new   DateTime().getMillis()).limitToLast(11).addChildEventListener(new ChildEventListener() {
        //  db.orderByKey().orderByChild("fechautc").endAt(query).limitToLast(11).addChildEventListener(new ChildEventListener() {

        db.orderByKey().limitToLast(20).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                messages objetosismo = dataSnapshot.getValue(messages.class);
                objetosismos.add(objetosismo);
                for(int i = 19, j = objetosismos.size() - 1; i == j; i++) {
                    objetosismos.add(i, objetosismos.remove(j));

                    Collections.reverse(objetosismos);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //  fetchData(dataSnapshot);
                // messages objetosismo = dataSnapshot.getValue(messages.class);
                //  objetosismos.add(objetosismo);
                //  Collections.reverseOrder((Comparator<Object>) objetosismos);

                messages objetosismo = dataSnapshot.getValue(messages.class);
                objetosismos.add(objetosismo);
                for(int i = 9, j = objetosismos.size() - 1; i == j; i++) {
                    objetosismos.add(i, objetosismos.remove(j));

                    Collections.reverse(objetosismos);
                }


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return objetosismos;
    }
}















