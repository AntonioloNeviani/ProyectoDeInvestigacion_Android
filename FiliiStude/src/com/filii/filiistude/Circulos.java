/*
*
* Código realizado por Jorge Antonio y María de Lourdes
*
*/

package com.filii.filiistude;
import java.io.IOException;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog.Builder;


public class Circulos extends ActionBarActivity {
	ImageView idImagen, idNumero;
	int poss, contador, contador2, errores, numeroPreguntas;
	Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, btnJuego;
	TextView textView3;
	MediaPlayer mpl;
	Random rnd = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circulos);

		idImagen = (ImageView) findViewById(R.id.imageView_imagen);
		idImagen.setImageResource(R.drawable.manzana1);
		idNumero = (ImageView) findViewById(R.id.numero);
		idNumero.setImageResource(R.drawable.numero1);
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button3 = (Button)findViewById(R.id.button3);
		button4 = (Button)findViewById(R.id.button4);
		button5 = (Button)findViewById(R.id.button5);
		button6 = (Button)findViewById(R.id.button6);
		button7 = (Button)findViewById(R.id.button7);
		button8 = (Button)findViewById(R.id.button8);
		button9 = (Button)findViewById(R.id.button9);
		button10 = (Button)findViewById(R.id.button10);
		btnJuego =(Button)findViewById(R.id.juego);
		textView3 = (TextView)findViewById(R.id.textView3);
		mpl = MediaPlayer.create(this, R.raw.numero1);

		contador = rnd.nextInt(9);
		contador2 = contador;
		eJuegoN();
		contador = -1;
		idNumero.setImageResource(R.drawable.interrogacio);
	}
	public void prueba(View view) {

			if(view == button1){
				contador = 0;
				nexPreviu(view);
			}else if(view == button2){
				contador = 1;
				nexPreviu(view);
			}else if(view == button3){
				contador = 2;
				nexPreviu(view);
			}else if(view == button4){
				contador = 3;
				nexPreviu(view);
			}else if(view == button5){
				contador = 4;
				nexPreviu(view);
			}else if(view == button6){
				contador = 5;
				nexPreviu(view);
			}else if(view == button7){
				contador = 6;
				nexPreviu(view);
			}else if(view == button8){
				contador = 7;
				nexPreviu(view);
			}else if(view == button9){
				contador = 8;
				nexPreviu(view);
			}else if(view == button10){
				contador = 9;
				nexPreviu(view);
			}else if(view == btnJuego){
				if(contador == contador2){
					showMessage("Felicidades", "Felicidades bien hecho");
				}else{
					showMessage(":(", "Intenta otra vez");
					errores++;
				}
				contador = rnd.nextInt(9);
				contador2 = contador;
				eJuegoN();
				contador = -1;
				idNumero.setImageResource(R.drawable.interrogacio);
				numeroPreguntas++;
				if (errores == 3){
					showMessage(":(", "Lociento se te acabaron las oportunidades");
					CountDownTimer timer = new CountDownTimer(5000, 500){

						@Override
						public void onTick(long millisUntilFinished) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							finish();
						}

					}.start();

				}else if(numeroPreguntas == 5){
					showMessage(":D", "Felicidades bien hecho avanzaste al siguiente nivel");
					CountDownTimer timer = new CountDownTimer(5000, 500){

						@Override
						public void onTick(long millisUntilFinished) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							finish();
						}

					}.start();
				}
			}

	}
	private void showMessage(String title,String message) {
		Builder builder=new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.setPositiveButton("Aceptar", null);
    	builder.show();
	}
	public void siguiente(View view) {
		if (contador == 9) {
			Toast.makeText(getApplicationContext(), "No hay mas",
					Toast.LENGTH_SHORT).show();
		} else {
			contador++;
			nexPreviu(view);
		}
	}

	public void anterior(View view) {
		if (contador == 0) {
			Toast.makeText(getApplicationContext(), "No hay mas",
					Toast.LENGTH_SHORT).show();
		} else {
			contador--;
			nexPreviu(view);
		}
	}
	private void nexPreviu(View v) {
		mpl.pause();
		switch (contador) {
		case 0:
			contador = 0;
			idNumero.setImageResource(R.drawable.numero1);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.numero1);
				mpl.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 1:
			contador = 1;
			idNumero.setImageResource(R.drawable.numero2);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.numero2);
				mpl.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			contador = 2;
			idNumero.setImageResource(R.drawable.numero3);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.numero3);
				mpl.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			contador = 3;
			idNumero.setImageResource(R.drawable.numero4);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.numero4);
				mpl.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			contador = 4;
			idNumero.setImageResource(R.drawable.numero5);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.numero5);
				mpl.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			contador = 5;
			idNumero.setImageResource(R.drawable.numero6);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.numero6);
				mpl.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 6:
			contador = 6;
			idNumero.setImageResource(R.drawable.numero7);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.numero7);
				mpl.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 7:
			contador = 7;
			idNumero.setImageResource(R.drawable.numero8);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.numero8);
				mpl.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 8:
			contador = 8;
			idNumero.setImageResource(R.drawable.numero9);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.numero9);
				mpl.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 9:
			contador = 9;
			idNumero.setImageResource(R.drawable.numero10);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.numero10);
				mpl.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		mpl.start();
	}

	private void eJuegoN() {
		switch (contador) {
		case 0:
			contador = 0;
			idImagen.setImageResource(R.drawable.circulo1);
			break;
		case 1:
			contador = 1;
			idImagen.setImageResource(R.drawable.circulo2);
			break;
		case 2:
			contador = 2;
			idImagen.setImageResource(R.drawable.circulo3);
			break;
		case 3:
			contador = 3;
			idImagen.setImageResource(R.drawable.circulo4);
			break;
		case 4:
			contador = 4;
			idImagen.setImageResource(R.drawable.circulo5);
			break;
		case 5:
			contador = 5;
			idImagen.setImageResource(R.drawable.circulo6);
			break;
		case 6:
			contador = 6;
			idImagen.setImageResource(R.drawable.circulo7);
			break;
		case 7:
			contador = 7;
			idImagen.setImageResource(R.drawable.circulo8);
			break;
		case 8:
			contador = 8;
			idImagen.setImageResource(R.drawable.circulo9);
			break;
		case 9:
			contador = 9;
			idImagen.setImageResource(R.drawable.circulo10);
			break;
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.circulos, menu);
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
