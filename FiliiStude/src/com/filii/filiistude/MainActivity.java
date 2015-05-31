/*
*
* C�digo realizado por Jorge Antonio y Mar�a de Lourdes
*
*/

package com.filii.filiistude;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class MainActivity extends ActionBarActivity {
	ImageButton btnNuevo, btnIngresar, btnEditar;
	SQLiteDatabase db;
	private ListView lista;
	int idUsers = 0;
	String idNombre;
	TextView txtUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIngresar = (ImageButton) findViewById(R.id.button3);
        btnEditar = (ImageButton) findViewById(R.id.button2);
        btnNuevo = (ImageButton) findViewById(R.id.button1);

        txtUsuario = (TextView) findViewById(R.id.txtUsuario);

        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS estudiante ( id int(11) primary Key NOT NULL, nombre varchar(20), apellidoPaterno varchar(20), apellidoMaterno varchar(20), fechaNacimiento date, sexo varchar(10), puntuaje varchar(10) );");
		db.execSQL("CREATE TABLE IF NOT EXISTS actividades ( id int(11) primary Key NOT NULL, id_Estudiante int(11), contarManzanas varchar(10), Ascendentes varchar(10), Descendentes varchar(10), objetos varchar(10), numeroFaltantes varchar(10), codigo varchar(10), foreign key(id_Estudiante) references estudiante(id) );");

		ArrayList<Usuario> datos = new ArrayList<Usuario>();

		Cursor c = db.rawQuery("SELECT * FROM estudiante", null);
		if(c.getCount()==0)
		{
			showMessage("Error", "Sin resultados");
			return;
		}
		StringBuffer buffer = new StringBuffer();


		while(c.moveToNext())
		{
			datos.add(new Usuario(c.getInt(0), c.getString(1), c.getString(2) +" "+ c.getString(3)));
			/*buffer.append("C�digo: "+c.getString(0)+"\n");
			buffer.append("Nombre: "+c.getString(1)+"\n");
			buffer.append("Calificaci�n: "+c.getString(2)+"\n\n");*/
		}
		//showMessage("Estudiantes", buffer.toString());

		lista = (ListView) findViewById(R.id.listView1);
        lista.setAdapter(new UsuarioAdapter(this, R.layout.listview_item, datos){
			@Override
			public void onEntrada(Object listview_item, View view) {
		        if (listview_item != null) {
		            TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior);
		            if (texto_superior_entrada != null)
		            	texto_superior_entrada.setText(((Usuario) listview_item).get_textoEncima());

		            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior);
		            if (texto_inferior_entrada != null)
		            	texto_inferior_entrada.setText(((Usuario) listview_item).get_textoDebajo());

		            /*ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
		            if (imagen_entrada != null)
		            	imagen_entrada.setImageResource(((Usuario) listview_item).get_idImagen());*/
		        }
			}
		});
        lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
				Usuario elegido = (Usuario) pariente.getItemAtPosition(posicion);

                /*CharSequence texto = "Abriendo: " + elegido.get_idImagen();
                Toast toast = Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG);
                toast.show();*/

                txtUsuario.setText("Usuario seleccionado: " + elegido.get_textoEncima());
                idUsers = elegido.get_idImagen();
                idNombre = elegido.get_textoEncima();
			}
        });
        //lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		//lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		//lista.setItemChecked();

        registerForContextMenu(lista);
    }

    public void onClick(View v){
    	if(v == btnIngresar){
    		if (idUsers == 0){

    		}else{
    			Intent intent = new Intent(this, Indice.class );
    			intent.putExtra("idUsuario",idUsers+"");
    			intent.putExtra("idNombre",idNombre+"");
    			startActivity(intent);
    		}
    	} else if(v == btnNuevo){
    		Intent intent = new Intent(this, NuevoUsuario.class );
    		startActivity(intent);
    	} else if(v == btnEditar){
    		Intent intent = new Intent(this, EditarUsuario.class );
    		intent.putExtra("idUsuario",idUsers+"");
    		startActivity(intent);
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

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
	    super.onCreateContextMenu(menu, v, menuInfo);
	    android.view.MenuInflater inflater = getMenuInflater();
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
	    inflater.inflate(R.menu.opciones_personas, menu);
	}

	@Override
	public boolean onContextItemSelected(android.view.MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

	    switch (item.getItemId()) {
	        case R.id.menu_contextual_editar_usuario:
	        	//editarPersona((int)info.id);
	            return true;
	        case R.id.menu_contextual_eliminar_usuario:
	        	//eliminarPersona((int)info.id);
	        	//recuperarTodasPersonas();
	            return true;
	        case R.id.menu_contextual_iniciar_usuario:

	        	//eliminarPersona((int)info.id);
	        	//recuperarTodasPersonas();
	            return true;
	        default:
	            return super.onContextItemSelected((android.view.MenuItem) item);
	    }
	}

	/*
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_MENU)) {

		}
		return super.onKeyDown(keyCode, event);
	}*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
