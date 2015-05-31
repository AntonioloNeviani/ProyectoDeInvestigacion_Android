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

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class EditarUsuario extends ActionBarActivity {
	private Spinner spinnerSexo, spinnerDia, spinnerMes;
	private EditText id, nombre, aPaterno, aMaterno, anno;
	private ImageButton editar, eliminar, buscar;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_usuario);


		spinnerSexo = (Spinner) findViewById(R.id.sexo);
		spinnerDia = (Spinner) findViewById(R.id.spinnerDia);
		spinnerMes = (Spinner) findViewById(R.id.spinnerMes);

		id = (EditText) findViewById(R.id.editID);
		nombre = (EditText) findViewById(R.id.editNombre);
		aPaterno = (EditText) findViewById(R.id.editApaterno);
		aMaterno = (EditText) findViewById(R.id.editAmaterno);
		anno = (EditText) findViewById(R.id.editAnno);

		editar = (ImageButton) findViewById(R.id.btnActualizar);
		eliminar = (ImageButton) findViewById(R.id.btnEliminar);
		buscar = (ImageButton) findViewById(R.id.btnBuscar);

		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

		String id2 = getIntent().getStringExtra("idUsuario");
		id.setText(id2);

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

        cargarDatos();
	}

	public void onClick(View view)
    {
    	if(view == editar)
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

    		if(id.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Introduzca el c�digo");
    			return;
    		}
    		Cursor c = db.rawQuery("SELECT * FROM estudiante WHERE id='"+id.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    			db.execSQL(
    					"UPDATE estudiante SET nombre='"+nombre.getText()+"',"+
    					"apellidoPaterno='"+aPaterno.getText()+"',"+
    					"apellidoMaterno='"+aMaterno.getText()+"',"+
    					"fechaNacimiento='"+date+"',"+
    					"sexo='"+spinnerSexo.getSelectedItem()+"',"+
    					"puntuaje='"+null+
    					"' WHERE "+
    					"id='"+id.getText()+"'"
    				);
    			showMessage("Exito", "Registro modificado");
    		}
    		else
    		{
    			showMessage("Error", "C�digo invalido");
    		}
    		clearText();


    	} else if (view == buscar){
    		cargarDatos();
    	} else if (view == eliminar){

    		if(id.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Por favor introduzca el codigo");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM estudiante WHERE id='"+id.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    			// este es el query que elimina tus datos
    			db.execSQL("DELETE FROM estudiante WHERE id='"+id.getText()+"'");
    			showMessage("Exito", "Registro eliminado");
    		}
    		else
    		{
    			showMessage("Error", "C�digo invalido");
    		}
    		clearText();
    	}
    }
	public void cargarDatos(){
		if(id.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Introduzca el c�digo");
    			return;
    		}
    		// este es el query que selecciona tus datos a mostrar
    		Cursor c=db.rawQuery("SELECT * FROM estudiante WHERE id='"+id.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    	    	nombre.setText(c.getString(1));
    	    	aPaterno.setText(c.getString(2));
    	    	aMaterno.setText(c.getString(3));
    	    	anno.setText(c.getString(4));
    	    	//spinnerSexo.setSelected(c.getString(4));
    		}
    		else
    		{
    			showMessage("Error", "C�digo invalido");
    			clearText();
    		}

	}


	private void modify() // esta es la funcion que modifica
    {
		if(id.getText().toString().trim().length()==0)
		{
			showMessage("Error", "Introduzca el c�digo");
			return;
		}
		Cursor c=db.rawQuery("SELECT * FROM estudiante WHERE id='"+id.getText()+"'", null);
		if(c.moveToFirst())
		{
			// este es el query que actualiza tus datos
			db.execSQL(
				"UPDATE estudiante SET nombre='"+nombre.getText()+"','"+
    						"apellidoPaterno='"+aPaterno.getText()+
    						"apellidoMaterno='"+aMaterno.getText()+
    						//"fechaNacimiento='"+date+
    						"sexo='"+spinnerSexo.getSelectedItem()+
    						"' WHERE "+
    						"id='"+id.getText()+"'"
			);
			showMessage("Exito", "Registro modificado");
		}
		else
		{
			showMessage("Error", "C�digo invalido");
		}
		clearText();
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
    	//id.setText(rnd.nextInt(900000)+"");
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editar_usuario, menu);
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
