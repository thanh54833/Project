package com.example.constants;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_READ_CONTACTS = 79;

    private int numberPhone=0;
    private TextView tv;
    private Button btl;
    private Button btr;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.text_view);
        btl=findViewById(R.id.button_left);
        btr=findViewById(R.id.button_right);
        pb=findViewById(R.id.progressBar);

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestLocationPermission();
        }

        btl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                tv.setText("loading...");
                pb.setVisibility(View.VISIBLE);

                Allphone=0;
                numberPhone=0;

                new CountDownTimer(1000, 1000) {

                    @Override
                    public void onTick(long l) {

                    }
                    @Override
                    public void onFinish() {

                        test=1;

                        getContactListLeft();

                        Toast.makeText(getApplicationContext(),"Click button left !",Toast.LENGTH_SHORT).show();

                    }
                }.start();

            }
        });

        btr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv.setText("loading...");
                pb.setVisibility(View.VISIBLE);

                Allphone=0;
                numberPhone=0;

                new CountDownTimer(1000, 1000) {

                    @Override
                    public void onTick(long l) {

                    }
                    @Override
                    public void onFinish() {

                        test=1;

                        getContactListRight();

                        Toast.makeText(getApplicationContext(),"Click button right !",Toast.LENGTH_SHORT).show();

                    }
                }.start();



            }
        });


        /*for(int i=0; i< names.length;i++){
            writeContact(names[i], numbers[i]);
        }*/

        //DeleteAllContacts();
        //Toast.makeText(this,"Check : "+isStringNumeric("123456"),Toast.LENGTH_LONG).show();

    }


    private void DeleteAllContacts()
    {
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String lookupKey = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
            Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
            contentResolver.delete(uri, null, null);
        }

    }

    private void writeContact(String displayName, String number) {
        ArrayList contentProviderOperations = new ArrayList();

        contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());

        contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, displayName).build());

        contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number).withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build());
        try {
            getApplicationContext().getContentResolver().
                    applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    public static boolean isStringNumeric( String str )
    {
        DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
        char localeMinusSign = currentLocaleSymbols.getMinusSign();

        if ( !Character.isDigit( str.charAt( 0 ) ) && str.charAt( 0 ) != localeMinusSign ) return false;

        boolean isDecimalSeparatorFound = false;
        char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();

        for ( char c : str.substring( 1 ).toCharArray() )
        {
            if ( !Character.isDigit( c ) )
            {
                if ( c == localeDecimalSeparator && !isDecimalSeparatorFound )
                {
                    isDecimalSeparatorFound = true;
                    continue;
                }
                return false;
            }
        }
        return true;
    }


    private ArrayList<String> viettel=new ArrayList<>();
    private ArrayList<String> mobile=new ArrayList<>();
    private ArrayList<String> vinaphone=new ArrayList<>();
    private ArrayList<String> vietnamemobile=new ArrayList<>();
    private ArrayList<String> gmobile=new ArrayList<>();



    private int test=0;
    private String[] names = {"A A 1", "A A 2", "A A 3","A A 4", "A A 5", "A A 6","A A 7","A A 8","A A 9","A A 10","A A 11","A A 12","A A 13","A A 14","A A 15","A A 16","A A 17","A A 18","A A 19","A A 20","A A 21","A A 22","A A 23","A A 24","A A 25"};
    private String[] numbers = {"01696600000","01690000000","01680000000","01670000000","01660000000","01650000000","01640000000","01630000000","01620000000","01200000000","01210000000","01220000000","01260000000","01280000000","01230000000","01240000000","01250000000","01270000000","01290000000","01860000000","01880000000","01992000000","01993000000","01998000000","01999000000"};
    private int Allphone=0;


    private void getContactListLeft() {

        viettel.clear();
        mobile.clear();
        vietnamemobile.clear();
        gmobile.clear();
        vinaphone.clear();

        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        Calendar c = Calendar.getInstance();
        int day=c.get(Calendar.DATE);
        int month=c.get(Calendar.MONTH)+1;
        Log.d("time","day :"+day+" - month :"+month);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {

                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String headNumber = null;

                if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                    while (pCur.moveToNext()) {

                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        try {
                            headNumber = phoneNo.substring(0, 3);
                            if(headNumber.equals("+84"))
                            {
                                deleteContact(phoneNo,name);
                                phoneNo="0"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                            }
                        }catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"Error : "+e,Toast.LENGTH_SHORT ).show();
                        }

                        if(phoneNo.length()>10)
                        {
                            Allphone++;
                            if(isStringNumeric(name))
                            {
                                Log.d("log", "Name: " + "No name");
                            }
                            else
                            {
                                Log.d("log", "Name: " + name);
                            }
                            Log.d("log", "Phone Number: " + phoneNo);

                            //viettel
                            if(test==0)
                            {
                                month=9;
                                day=15;
                            }
                            if(phoneNo.substring(0, 6).equals("016966")&&month>=9&&day>=15){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n(15/9)"+name+":\n"+"(016966->03966) :"+phoneNo.substring(6,phoneNo.length())+"\n----------\n");

                                phoneNo="03966"+phoneNo.substring(6,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            if(test==0)
                            {
                                month=9;
                                day=17;
                            }
                            if(phoneNo.substring(0, 4).equals("0169")&&month>=9&&day>=17){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n(17/9)"+name+":\n"+"(0169->039) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="039"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            if(test==0)
                            {
                                month=9;
                                day=19;
                            }
                            if(phoneNo.substring(0, 4).equals("0168")&&month>=9&&day>=19){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n(19/9)"+name+":\n"+"(0168->038) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="038"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            if(test==0)
                            {
                                month=9;
                                day=23;
                            }
                            if(phoneNo.substring(0, 4).equals("0167")&&month>=9&&day>=23){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n(23/9)"+name+":\n"+"(0167->037) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="037"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            if(test==0)
                            {
                                month=9;
                                day=25;
                            }
                            if(phoneNo.substring(0, 4).equals("0166")&&month>=9&&day>=25){
                                numberPhone++;
                                deleteContact(phoneNo,name);
                                viettel.add("\n(25/9)"+name+":\n"+"(0166->036) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");
                                phoneNo="036"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            if(test==0)
                            {
                                month=9;
                                day=27;
                            }
                            if(phoneNo.substring(0, 4).equals("0165")&&month>=9&&day>=27){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n(27/9)"+name+":\n"+"(0165->035) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="035"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(test==0)
                            {
                                month=10;
                                day=3;
                            }
                            if(phoneNo.substring(0, 4).equals("0164")&&month>=10&&day>=3){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n(03/10)"+name+":\n"+"(0164->034) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="034"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            if(test==0)
                            {
                                month=10;
                                day=5;
                            }
                            if(phoneNo.substring(0, 4).equals("0163")&&month>=10&&day>=5){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n(05/10)"+name+":\n"+"(0163->033) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="033"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            if(test==0){
                                month=10;
                                day=7;
                            }
                            if(phoneNo.substring(0, 4).equals("0162")&&month>=10&&day>=7){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n(07/10)"+name+":\n"+"(0162->032) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="032"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            //mobile
                            if(test==0){
                                month=9;
                                day=15;
                            }
                            if(phoneNo.substring(0, 4).equals("0120")&&month>=9&&day>=15){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                mobile.add("\n(15/9)"+name+":\n"+"(0120->070) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="070"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(test==0){
                                month=9;
                                day=21;
                            }
                            if(phoneNo.substring(0, 4).equals("0121")&&month>=9&&day>=21){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                mobile.add("\n(21/9)"+name+":\n"+"(0121->079) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="079"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(test==0){
                                month=9;
                                day=25;
                            }
                            if(phoneNo.substring(0, 4).equals("0122")&&month>=9&&day>=25){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                mobile.add("\n(25/9)"+name+":\n"+"(0122->077) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="077"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(test==0){
                                month=9;
                                day=28;
                            }
                            if(phoneNo.substring(0, 4).equals("0126")&&month>=9&&day>=28){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                mobile.add("\n(28/9)"+name+":\n"+"(0126->076) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="076"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            if(test==0){
                                month=10;
                                day=2;
                            }
                            if(phoneNo.substring(0, 4).equals("0128")&&month>=10&&day>=2){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                mobile.add("\n(10/2)"+name+":\n"+"(0128->078) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="078"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            //vinaphone
                            if(test==0){
                                month=9;
                                day=24;
                            }
                            if(phoneNo.substring(0, 4).equals("0123")&&month>=9&&day>=24){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vinaphone.add("\n(24/9)"+name+":\n"+"(0123->083) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="083"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            if(test==0){
                                month=9;
                                day=15;
                            }
                            if(phoneNo.substring(0, 4).equals("0124")&&month>=9&&day>=15){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vinaphone.add("\n(15/9)"+name+":\n"+"(0124->084) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="084"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(test==0){
                                month=9;
                                day=27;
                            }
                            if(phoneNo.substring(0, 4).equals("0125")&&month>=9&&day>=27){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vinaphone.add("\n(27/9)"+name+":\n"+"(0125->085) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="085"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(test==0){
                                month=9;
                                day=18;
                            }
                            if(phoneNo.substring(0, 4).equals("0127")&&month>=9&&day>=18){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vinaphone.add("\n(18/9)"+name+":\n"+"(0127->081) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="081"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(test==0){
                                month=9;
                                day=21;
                            }
                            if(phoneNo.substring(0, 4).equals("0129")&&month>=9&&day>=21){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vinaphone.add("\n(21/9)"+name+":\n"+"(0129->082) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="082"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            //vietnamemobile

                            if(test==0){
                                month=9;
                                day=15;
                            }

                            if(phoneNo.substring(0, 4).equals("0186")&&month>=9&&day>=15){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vietnamemobile.add("\n(15/9)"+name+":\n"+"(0186->056) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="056"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            if(test==0){
                                month=9;
                                day=15;
                            }
                            if(phoneNo.substring(0, 4).equals("0188")&&month>=9&&day>=15){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vietnamemobile.add("\n(15/9)"+name+":\n"+"(0188->058) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="058"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            //gtel

                            if(test==0){
                                month=9;
                                day=15;
                            }
                            if(phoneNo.substring(0, 5).equals("01992")&&month>=9&&day>=15)
                            {
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                gmobile.add("\n(15/9)"+name+":\n"+"(01992->0592) :"+phoneNo.substring(5,phoneNo.length())+"\n----------\n");

                                phoneNo="0592"+phoneNo.substring(5,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(test==0){
                                month=9;
                                day=17;
                            }
                            if(phoneNo.substring(0, 5).equals("01993")&&month>=9&&day>=17){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                gmobile.add("\n(17/9)"+name+":\n"+"(01993->0593) :"+phoneNo.substring(5,phoneNo.length())+"\n----------\n");

                                phoneNo="0593"+phoneNo.substring(5,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(test==0){
                                month=9;
                                day=19;
                            }
                            if(phoneNo.substring(0, 5).equals("01998")&&month>=9&&day>=19){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                gmobile.add("\n(19/9)"+name+":\n"+"(01998->0598) :"+phoneNo.substring(5,phoneNo.length())+"\n----------\n");

                                phoneNo="0598"+phoneNo.substring(5,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(test==0){
                                month=9;
                                day=21;
                            }
                            if(phoneNo.substring(0, 5).equals("01999")&&month>=9&&day>=21){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                gmobile.add("\n(21/9)"+name+":\n"+"(01999->0599) :"+phoneNo.substring(5,phoneNo.length())+"\n----------\n");

                                phoneNo="0599"+phoneNo.substring(5,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                        }
                    }

                    this.setTitle("Đổi được tất cả : ("+numberPhone+"/"+Allphone+")");
                    tv.setText(null);

                    tv.append(Html.fromHtml("<b>" + "* Các sô Viettel đã chuyển từ 11 -> 10 số:" + "</b>"));
                    tv.append("\n");
                    for(String s :viettel){

                        tv.append(s);

                    }
                    tv.append(Html.fromHtml("<b>" + "* Các sô Mobile đã chuyển từ 11 -> 10 số:" + "</b>"));
                    tv.append("\n");
                    for(String s :mobile){

                        tv.append(s);

                    }
                    tv.append(Html.fromHtml("<b>" + "* Các sô Vinaphone đã chuyển từ 11 -> 10 số:" + "</b>"));
                    tv.append("\n");
                    for(String s :vinaphone){
                        tv.append(s);
                    }

                    tv.append(Html.fromHtml("<b>" + "* Các sô VietnameMobile đã chuyển từ 11 -> 10 số:" + "</b>"));
                    tv.append("\n");
                    for(String s :vietnamemobile){
                        tv.append(s);
                    }

                    tv.append(Html.fromHtml("<b>" + "* Các sô GMobile đã chuyển từ 11 -> 10 số:" + "</b>"));
                    tv.append("\n");
                    for(String s :gmobile){
                        tv.append(s);
                    }


                    pb.setVisibility(View.GONE);


                    pCur.close();

                }
            }
        }
        if(cur!=null){
            cur.close();
            if(Allphone==0)
            {
                tv.setText(null);
                tv.append("Không có số nào trong danh bạ ...");
            }
            pb.setVisibility(View.GONE);
        }
        Log.d("log", "Phone Number: " + numberPhone);
    }


    private void getContactListRight() {

        viettel.clear();
        mobile.clear();
        vietnamemobile.clear();
        gmobile.clear();
        vinaphone.clear();


        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        Calendar c = Calendar.getInstance();
        int day=c.get(Calendar.DATE);
        int month=c.get(Calendar.MONTH)+1;
        Log.d("time","day :"+day+" - month :"+month);

        if ((cur != null ? cur.getCount() : 0) > 0) {

            while (cur != null && cur.moveToNext()) {

                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String headNumber = null;

                if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                    while (pCur.moveToNext()) {

                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        try {
                            headNumber = phoneNo.substring(0, 3);
                            if(headNumber.equals("+84"))
                            {
                                deleteContact(phoneNo,name);
                                phoneNo="0"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                            }
                        }catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"Error : "+e,Toast.LENGTH_SHORT ).show();
                        }

                        if(8<=phoneNo.length()&&phoneNo.length()<=10)
                        {
                            Allphone++;
                            if(isStringNumeric(name))
                            {
                                Log.d("log", "Name: " + "No name");
                            }
                            else
                            {
                                Log.d("log", "Name: " + name);
                            }
                            Log.d("log", "Phone Number: " + phoneNo);

                            //viettel

                            if(phoneNo.substring(0, 5).equals("03966")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n"+name+":\n"+"(03966->016966) :"+phoneNo.substring(5,phoneNo.length())+"\n----------\n");

                                phoneNo="016966"+phoneNo.substring(5,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("039")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n"+name+":\n"+"(039->0169) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0169"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("038")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n"+name+":\n"+"(038->0168) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0168"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("037")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n"+name+":\n"+"(037->0167) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0167"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("036")){
                                numberPhone++;
                                deleteContact(phoneNo,name);
                                viettel.add("\n"+name+":\n"+"(036->0166) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");
                                phoneNo="0166"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("035")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n"+name+":\n"+"(035->0165) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0165"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("034")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n"+name+":\n"+"(034->0164) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0164"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("033")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n"+name+":\n"+"(033->0163) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0163"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("032")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                viettel.add("\n"+name+":\n"+"(032->0162) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0162"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            //mobile

                            if(phoneNo.substring(0, 3).equals("070")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                mobile.add("\n"+name+":\n"+"(070->0120) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0120"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("079")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                mobile.add("\n"+name+":\n"+"(079->0121) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0121"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("077")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                mobile.add("\n"+name+":\n"+"(077->0122) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0122"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("076")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                mobile.add("\n"+name+":\n"+"(076->0126) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0126"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("078")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                mobile.add("\n"+name+":\n"+"(078->0128) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0128"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }
                            //vinaphone

                            if(phoneNo.substring(0, 3).equals("083")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vinaphone.add("\n"+name+":\n"+"(083->0123) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0123"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("084")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vinaphone.add("\n"+name+":\n"+"(084->0124) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0124"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }


                            if(phoneNo.substring(0, 3).equals("085")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vinaphone.add("\n"+name+":\n"+"(085->0125) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0125"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }


                            if(phoneNo.substring(0, 3).equals("081")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vinaphone.add("\n"+name+":\n"+"(081->0127) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0127"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }


                            if(phoneNo.substring(0, 3).equals("082")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vinaphone.add("\n"+name+":\n"+"(082->0129) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0129"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            //vietnamemobile

                            if(phoneNo.substring(0, 3).equals("056")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vietnamemobile.add("\n"+name+":\n"+"(056->0186) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0186"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 3).equals("058")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                vietnamemobile.add("\n"+name+":\n"+"(058->0188) :"+phoneNo.substring(3,phoneNo.length())+"\n----------\n");

                                phoneNo="0188"+phoneNo.substring(3,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            //gtel

                            if(phoneNo.substring(0, 4).equals("0592"))
                            {
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                gmobile.add("\n"+name+":\n"+"(0592->01992) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="01992"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 4).equals("0593")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                gmobile.add("\n"+name+":\n"+"(0593->01993) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="01993"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 4).equals("0598")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                gmobile.add("\n"+name+":\n"+"(0598->01998) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="01998"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                            if(phoneNo.substring(0, 4).equals("0599")){
                                numberPhone++;
                                deleteContact(phoneNo,name);

                                gmobile.add("\n"+name+":\n"+"(0599->01999) :"+phoneNo.substring(4,phoneNo.length())+"\n----------\n");

                                phoneNo="01999"+phoneNo.substring(4,phoneNo.length());
                                writeContact(name,phoneNo);
                                Log.d("thanhthanh","day"+day+"name :"+name+"numberPhone :"+numberPhone+" - phoneNo:"+phoneNo);
                            }

                        }
                    }

                    this.setTitle("Đổi được tất cả : ("+numberPhone+"/"+Allphone+")");
                    tv.setText(null);

                    tv.append(Html.fromHtml("<b>" + "* Các sô Viettel đã chuyển từ 10 -> 11 số:" + "</b>"));
                    tv.append("\n");
                    for(String s :viettel){

                        tv.append(s);

                    }
                    tv.append(Html.fromHtml("<b>" + "* Các sô Mobile đã chuyển từ 10 -> 11 số:" + "</b>"));
                    tv.append("\n");
                    for(String s :mobile){

                        tv.append(s);

                    }
                    tv.append(Html.fromHtml("<b>" + "* Các sô Vinaphone đã chuyển từ 10 -> 11 số:" + "</b>"));
                    tv.append("\n");
                    for(String s :vinaphone){
                        tv.append(s);
                    }

                    tv.append(Html.fromHtml("<b>" + "* Các sô VietnameMobile đã chuyển từ 10 -> 11 số:" + "</b>"));
                    tv.append("\n");
                    for(String s :vietnamemobile){
                        tv.append(s);
                    }

                    tv.append(Html.fromHtml("<b>" + "* Các sô GMobile đã chuyển từ 10 -> 11 số:" + "</b>"));
                    tv.append("\n");
                    for(String s :gmobile){
                        tv.append(s);
                    }


                    pb.setVisibility(View.GONE);


                    pCur.close();

                }
            }
        }
        if(cur!=null){
            cur.close();
            if(Allphone==0)
            {
                tv.setText(null);
                tv.append("Không có số nào trong danh bạ ...");
            }
            pb.setVisibility(View.GONE);
        }
        Log.d("log", "Phone Number: " + numberPhone);
    }




    public boolean deleteContact(String phone, String name) {
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phone));
        Cursor cur = getContentResolver().query(contactUri, null, null, null, null);
        try {
            if (cur.moveToFirst()) {
                do {
                    if (cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME)).equalsIgnoreCase(name)) {
                        String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
                        getContentResolver().delete(uri, null, null);
                        return true;
                    }
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            cur.close();
        }
        return false;
    }


    protected void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.READ_CONTACTS)) {
            // show UI part if you want here to show some rationale !!!

        } else {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_CONTACTS: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                    // permission denied,Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

        }
    }






}
