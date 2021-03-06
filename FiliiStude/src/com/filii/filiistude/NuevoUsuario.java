/*
*
* C�digo realizado por Jorge Antonio y Mar�a de Lourdes
*
*/

package com.filii.filiistude;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class NuevoUsuario extends ActionBarActivity{
	private Spinner spinnerSexo, spinnerDia, spinnerMes;
	private EditText id, nombre, aPaterno, aMaterno, anno;
	private ImageButton nuevo;
	SQLiteDatabase db;
	Random rnd = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo_usuario);

		spinnerSexo = (Spinner) findViewById(R.id.sexo);
		spinnerDia = (Spinner) findViewById(R.id.spinnerDia);
		spinnerMes = (Spinner) findViewById(R.id.spinnerMes);

		id = (EditText) findViewById(R.id.editID);
		nombre = (EditText) findViewById(R.id.editNombre);
		aPaterno = (EditText) findViewById(R.id.editApaterno);
		aMaterno = (EditText) findViewById(R.id.editAmaterno);
		anno = (EditText) findViewById(R.id.editAnno);

		nuevo = (ImageButton) findViewById(R.id.btnNuevo);

		id.setText(rnd.nextInt(900000)+"");

		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

        List<String> list = new ArrayList<String>();
        list.add("Masculino");
        list.add("Femenino");
        list.add("Indistinto");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource
        (android.R.layout.simple_spinner_dropdown_item);

        spinnerSexo.setAdapter(dataAdapter);

        List<String> list2 = new ArrayList<String>();
        for(int i  = 1 ; i <= 31; i++)
        list2.add(Integer.toString(i));

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item,list2);

        dataAdapter.setDropDownViewResource
        (android.R.layout.simple_spinner_dropdown_item);

        spinnerDia.setAdapter(dataAdapter2);

        List<String> list3 = new ArrayList<String>();
        list3.add("Enero"); list3.add("Febrero"); list3.add("Marzo");
        list3.add("Abril"); list3.add("Mayo"); list3.add("Junio");
        list3.add("Julio"); list3.add("Agosto"); list3.add("Septiembre");
        list3.add("Octubre"); list3.add("Noviembre"); list3.add("Diciembre");

        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item,list3);

        dataAdapter.setDropDownViewResource
        (android.R.layout.simple_spinner_dropdown_item);

        spinnerMes.setAdapter(dataAdapter3);

	}

	public void onClick(View view)
    {
    	if(view == nuevo)
    	{
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    		String fecha = spinnerDia.getSelectedItem()+"/"+(spinnerMes.getSelectedItemPosition()+1)+"/"+anno.getText();
    		Date date = null;
			try {
				date = formatter.parse(fecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(id.getText().toString().trim().length()==0||
    		 		   nombre.getText().toString().trim().length()==0||
    		 		   aPaterno.getText().toString().trim().length()==0||
    		 		   aMaterno.getText().toString().trim().length()==0||
    		 		   anno.getText().toString().trim().length()==0
    		   	)
    			{
    	 			showMessage("Error", "Introduzca los valores");
    	 			return;
    	 		}
    			// este es el query que inserta tus datos
    	 		db.execSQL(
    				"INSERT INTO estudiante VALUES('"+
    				Integer.parseInt(id.getText()+"")+"','"+
    				nombre.getText()+"','"+
    				aPaterno.getText()+"','"+
    				aMaterno.getText()+"','"+
    				date+"','"+ spinnerSexo.getSelectedItem()+"','"+
    				null +"');"
    			);
    	 		showMessage("Exito", "Registro agregado");
    	 		clearText();
			//nombre.setText(formatter.format(date));
    	}
    }

	private void showMessage(String title,String message)
    {
    	Builder builder = new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.setPositiveButton("Aceptar", null);
    	builder.show();
	}
    private void clearText()
    {
    	id.setText("");
    	nombre.setText("");
    	aPaterno.setText("");
    	aMaterno.setText("");
    	anno.setText("");
    	id.requestFocus();
    	id.setText(rnd.nextInt(900000)+"");
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nuevo_usuario, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
