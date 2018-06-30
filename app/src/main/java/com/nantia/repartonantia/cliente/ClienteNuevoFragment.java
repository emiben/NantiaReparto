package com.nantia.repartonantia.cliente;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.EnvaseSpinnerAdapter;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.map.ClienteMapaFragment;
import com.nantia.repartonantia.producto.Envase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.nantia.repartonantia.cliente.Dia.DOMINGO;

/**
 *
 */
public class ClienteNuevoFragment extends Fragment implements ClienteNuevoView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ClienteNuevoPresenter presenter;
    private Cliente cliente;
    private TextView nombre1;
    private TextView nombre2;
    private TextView nroDeDoc;
    private Spinner tipoDeDoc;
    private TextView fecDeNac;
    private TextView telefono1;
    private TextView telefono2;
    private TextView email;
    private TextView direccion;
    private TextView ciudad;
    private TextView departamento;
    private TextView codigoPostal;
    private TextView comentarios;
    private ArrayList<Spinner> envAPrestamoSPs = new ArrayList<>();
    private ArrayList<TextView> envAPrestamoCantETs = new ArrayList<>();
    private LinearLayout envAPrestamoLO;
    private ImageView usuarioImage;
    private ImageView casaImage;
    private FloatingActionButton fab;
    private LatLng posicionMapa;
    private ImageView agregarEnvPrestamo;
    private DatePickerDialog.OnDateSetListener date;
    private Calendar calendar = Calendar.getInstance();
    private ArrayList<EnvaseEnPrestamo> envasesEnPrestamo;
    private ArrayList<Envase> envases;
    private ArrayList<Dia> dias;
    private RadioButton domingo;
    private RadioButton lunes;
    private RadioButton martes;
    private RadioButton miercoles;
    private RadioButton jueves;
    private RadioButton viernes;
    private RadioButton sabado;
    private Button guardar;
    private ProgressBar progressBar;

    @Override
    public void saveCliente() {
        if(cliente == null){
            cliente = new Cliente();
        }
        cliente.setNombre1(nombre1.getText().toString());
        cliente.setNombre2(nombre2.getText().toString());
        cliente.setNroDocumento(nroDeDoc.getText().toString());
        if(tipoDeDoc.getSelectedItem().equals(TipoDocumento.CI.name())){
            cliente.setTipoDocumento(TipoDocumento.CI);
        }else if (tipoDeDoc.getSelectedItem().equals(TipoDocumento.RUT.name())){
            cliente.setTipoDocumento(TipoDocumento.RUT);
        }else {
            cliente.setTipoDocumento(TipoDocumento.NA);
        }
        cliente.setFechaNacimiento(fecDeNac.getText().toString());
        cliente.setCelular(telefono1.getText().toString());
        cliente.setMail(email.getText().toString());
        Direccion direccion = new Direccion(0, this.direccion.getText().toString(), this.posicionMapa.longitude, this.posicionMapa.latitude,
            this.telefono2.getText().toString(), this.ciudad.getText().toString(), this.departamento.getText().toString(), this.codigoPostal.getText().toString());
        cliente.setDireccion(direccion);
        cliente.setObservaciones(comentarios.getText().toString());
        cliente.setDias(dias);
        envasesEnPrestamo.clear();
        for (int i = 0; i < envAPrestamoSPs.size(); i++){
            Envase envase = (Envase) envAPrestamoSPs.get(i).getSelectedItem();
            if(envase.getId() != 0){
                EnvaseEnPrestamo envaseEnPrestamo =
                        new EnvaseEnPrestamo(envase, Integer.valueOf(envAPrestamoCantETs.get(i).getText().toString()));
                envasesEnPrestamo.add(envaseEnPrestamo);
            }
        }
        cliente.setEnvasesEnPrestamo(envasesEnPrestamo);
        presenter.saveCliente(cliente);
    }

    public ClienteNuevoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_nuevo, container, false);
        presenter = new ClienteNuevoPresenter(this);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initializeViewObjects(view);
        setOnClickListeners();
        loadSpinner();

        //TODO: Traer envases posta
//        envases = new ArrayList<>();
//        envases.add(new Envase(0, "Nuevo envase a prestamo..."));
//        for(int i=1; i < 50; i++){
//            envases.add(new Envase(i, "Envase " + i));
//        }
        presenter.getEnvases();
        addEditTexts(null);

        if(getArguments() != null){
            if(getArguments().getDouble("lat") != 0.0){
                posicionMapa = new LatLng(getArguments().getDouble("lat"), getArguments().getDouble("lng"));
            }
            if(getArguments().getSerializable("cliente") != null){
                cliente = (Cliente) getArguments().getSerializable("cliente");
                loadClienteData(cliente);
            }
        }


        return view;
    }


    private void initializeViewObjects(View view){
        nombre1 = view.findViewById(R.id.nombre_1_et);
        nombre2 = view.findViewById(R.id.nombre_2_et);
        nroDeDoc = view.findViewById(R.id.numero_doc_et);
        tipoDeDoc = view.findViewById(R.id.tipo_doc_spin);
        fecDeNac = view.findViewById(R.id.fecha_de_nac_et);
        telefono1 = view.findViewById(R.id.tel_1_et);
        telefono2 = view.findViewById(R.id.tel_2_et);
        email = view.findViewById(R.id.email_et);
        direccion = view.findViewById(R.id.direccion_et);
        ciudad = view.findViewById(R.id.ciudad_et);
        departamento = view.findViewById(R.id.departamento_et);
        codigoPostal = view.findViewById(R.id.cp_et);
        usuarioImage = view.findViewById(R.id.user_image);
        casaImage = view.findViewById(R.id.home_image);
        fab = view.findViewById(R.id.cliente_nuevo_fab);
        envAPrestamoLO = view.findViewById(R.id.env_prestamo_lo);
        agregarEnvPrestamo = view.findViewById(R.id.agreagr_env_prestamo);
        comentarios = view.findViewById(R.id.comentario_et);
        domingo = view.findViewById(R.id.domingo_rb);
        lunes = view.findViewById(R.id.lunes_rb);
        martes = view.findViewById(R.id.martes_rb);
        miercoles = view.findViewById(R.id.miercoles_rb);
        jueves = view.findViewById(R.id.jueves_rb);
        viernes = view.findViewById(R.id.viernes_rb);
        sabado = view.findViewById(R.id.sabado_rb);
        guardar = view.findViewById(R.id.boton_guardar);
        progressBar = view.findViewById(R.id.cliente_nuevo_progress);
    }

    private void setOnClickListeners(){
        fab.setOnClickListener(this);
        agregarEnvPrestamo.setOnClickListener(this);
        tipoDeDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateUiRutoCI(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        fecDeNac.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        domingo.setOnCheckedChangeListener(this);
        lunes.setOnCheckedChangeListener(this);
        martes.setOnCheckedChangeListener(this);
        miercoles.setOnCheckedChangeListener(this);
        jueves.setOnCheckedChangeListener(this);
        viernes.setOnCheckedChangeListener(this);
        sabado.setOnCheckedChangeListener(this);
    }

    private void loadSpinner(){
        List<String> list = new ArrayList<>();
        list.add(TipoDocumento.CI.name());
        list.add(TipoDocumento.RUT.name());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoDeDoc.setAdapter(adapter);
    }

    private void updateUiRutoCI(int pos){
        if(pos == 0){
            usuarioImage.setImageResource(R.drawable.cliente_icono_gris_2);
            casaImage.setImageResource(R.drawable.home_2);
            nombre1.setHint(R.string.cliente_nombre);
            nombre2.setHint(R.string.cliente_apellido);
            nroDeDoc.setHint(R.string.cliente_nro_CI);
        }else{
            usuarioImage.setImageResource(R.drawable.factory_2);
            casaImage.setImageResource(R.drawable.factory_2);
            nombre1.setHint(R.string.cliente_empresa_nombre_fantasia);
            nombre2.setHint(R.string.cliente_empresa_nombre_legal);
            nroDeDoc.setHint(R.string.cliente_nro_RUT);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cliente_nuevo_fab:
                navigateToClienteNuevoMapaFragment();
                break;
            case R.id.agreagr_env_prestamo:
                addEditTexts(null);
                break;
            default:
                break;
        }
    }

    private void navigateToClienteNuevoMapaFragment() {
        ClienteMapaFragment clienteMapaFragment = new ClienteMapaFragment();
        if(posicionMapa != null){
            Bundle b = new Bundle();
            b.putParcelable("latLng", posicionMapa);
            clienteMapaFragment.setArguments(b);
        }
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cliente_lista_layout, clienteMapaFragment)
                .addToBackStack(null)
                .commit();
    }

    private void removeEditTextsDialog(final int viewId){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.cliente_eliminar_env_prestamo));
        builder.setMessage(getString(R.string.cliente_eliminar_env_prestamo_desc));
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeEditTexts(viewId);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void addEditTexts(EnvaseEnPrestamo envaseEnPrestamo){
        EnvaseSpinnerAdapter envaseSpinnerAdapter = null;

        if(envaseEnPrestamo != null){
            ArrayList<Envase> envase = new ArrayList<>();
            envase.add(envaseEnPrestamo.getEnvase());
            envaseSpinnerAdapter = new EnvaseSpinnerAdapter(getActivity(), envase);
        }else{
            envaseSpinnerAdapter = new EnvaseSpinnerAdapter(getActivity(), envases);
        }

        Spinner envaseSp = new Spinner(getActivity());
        envaseSp.setAdapter(envaseSpinnerAdapter);
        envaseSp.setId(Integer.parseInt("1"+envAPrestamoSPs.size() + 1));
        envaseSp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                removeEditTextsDialog(v.getId());
                return false;
            }
        });

        EditText envaseCantET = new EditText(getActivity());
        envaseCantET.setId(Integer.parseInt("2"+envAPrestamoCantETs.size() + 1));
        envaseCantET.setInputType(InputType.TYPE_CLASS_NUMBER);
        envaseCantET.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        envaseCantET.setHint(R.string.cliente_cantidad);

        if(envaseEnPrestamo != null){
            envaseCantET.setText(envaseEnPrestamo.getCantidad());
        }

        envAPrestamoSPs.add(envaseSp);
        envAPrestamoCantETs.add(envaseCantET);
        envAPrestamoLO.addView(envaseSp, 0);
        envAPrestamoLO.addView(envaseCantET, 1);
    }

    private void removeEditTexts(int viewId){
        String cantId = Integer.toString(viewId);
        cantId = cantId.substring(1);
        boolean encontre = false;
        int i = 0;
        while (!encontre && i < envAPrestamoSPs.size()){
            if(envAPrestamoSPs.get(i).getId() == viewId){
                envAPrestamoSPs.remove(i);
                encontre = true;
            }
            i++;
        }
        encontre = false;
        i = 0;
        while (!encontre && i < envAPrestamoCantETs.size()){
            if(envAPrestamoCantETs.get(i).getId() == Integer.parseInt("2"+cantId)){
                envAPrestamoCantETs.remove(i);
                encontre = true;
            }
            i++;
        }
        envAPrestamoLO.removeView(getActivity().findViewById(viewId));
        envAPrestamoLO.removeView(getActivity().findViewById(Integer.parseInt("2"+cantId)));
    }

    private void loadClienteData(Cliente cliente) {
        nombre1.setText(cliente.getNombre1());
        nombre2.setText(cliente.getNombre2());
        nroDeDoc.setText(cliente.getNroDocumento());
        if(cliente.getTipoDocumento() == TipoDocumento.RUT){
            tipoDeDoc.setSelection(1);
            usuarioImage.setImageResource(R.drawable.factory_2);
            casaImage.setImageResource(R.drawable.factory_2);
        }
        fecDeNac.setText(cliente.getFechaNacimiento().toString());
        telefono1.setText(String.valueOf(cliente.getCelular()));
        telefono2.setText(String.valueOf(cliente.getDireccion().getTelefono()));
        email.setText(cliente.getMail());
        direccion.setText(cliente.getDireccion().getDireccion());
        ciudad.setText(cliente.getDireccion().getCiudad());
        departamento.setText(cliente.getDireccion().getDepartamento());
        codigoPostal.setText(cliente.getDireccion().getCodPostal());
        envasesEnPrestamo = cliente.getEnvasesEnPrestamo();

        for(int i = 0; i < envasesEnPrestamo.size(); i++){
            addEditTexts(envasesEnPrestamo.get(i));
        }
        dias = cliente.getDias();
        for(int i  =0; i < dias.size(); i++){
            selectDias(dias.get(i));
        }
        posicionMapa = new LatLng(cliente.getDireccion().getCordLat(), cliente.getDireccion().getCordLon());
    }


    private void selectDias(Dia dia){
        switch (dia){
            case DOMINGO:
                domingo.setChecked(true);
                break;
            case LUNES:
                lunes.setChecked(true);
                break;
            case MARTES:
                martes.setChecked(true);
                break;
            case MIERCOLES:
                miercoles.setChecked(true);
                break;
            case JUEVES:
                jueves.setChecked(true);
                break;
            case VIERNES:
                viernes.setChecked(true);
                break;
            case SABADO:
                sabado.setChecked(true);
                break;
            default:
                break;
        }
    }




    @Override
    public void setEnvases(ArrayList<Envase> envases) {
        envases.add(0, new Envase(0, "Nuevo envase a prestamo..."));
        this.envases = envases;
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        fecDeNac.setText(sdf.format(calendar.getTime()));
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.domingo_rb:
                addRemoveDia(isChecked, Dia.DOMINGO);
                break;
            case R.id.lunes_rb:
                addRemoveDia(isChecked, Dia.LUNES);
                break;
            case R.id.martes_rb:
                addRemoveDia(isChecked, Dia.MARTES);
                break;
            case R.id.miercoles_rb:
                addRemoveDia(isChecked, Dia.MIERCOLES);
                break;
            case R.id.jueves_rb:
                addRemoveDia(isChecked, Dia.JUEVES);
                break;
            case R.id.viernes_rb:
                addRemoveDia(isChecked, Dia.VIERNES);
                break;
            case R.id.sabado_rb:
                addRemoveDia(isChecked, Dia.SABADO);
                break;
            default:
                break;
        }
    }

    private void addRemoveDia(boolean add, Dia dia){
        if(add){
            dias.add(dia);
        }else{
            dias.remove(dia);
        }
    }
}
