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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.EnvaseSpinnerAdapter;
import com.nantia.repartonantia.adapters.ListaDePreciosSpinAdapter;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.listadeprecios.ListaDePrecio;
import com.nantia.repartonantia.map.ClienteMapaFragment;
import com.nantia.repartonantia.producto.Envase;
import com.nantia.repartonantia.utils.FechaHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE;

/**
 *
 */
public class ClienteNuevoFragment extends Fragment implements ClienteNuevoView, View.OnClickListener {
    private ClienteNuevoPresenter presenter;
    private Cliente cliente;
    private Cliente clienteOriginal;
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
    private TextView observaciones;
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
    private Spinner listaDePreciosSP;
    private List<ListaDePrecio> listasDePrecio;
    private List<EnvaseEnPrestamo> envasesEnPrestamo;
    private List<EnvaseEnPrestamo> envasesEnPrestamoOrig;
    private List<Envase> envases;
    private List<Dia> dias;
    private RadioButton domingo;
    private RadioButton lunes;
    private RadioButton martes;
    private RadioButton miercoles;
    private RadioButton jueves;
    private RadioButton viernes;
    private RadioButton sabado;
    private Button guardar;
    private ProgressBar progressBar;
    private String fechaDeNacimiento;
    private boolean updateCliente = false;


    public ClienteNuevoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dias = new ArrayList<>();
        envasesEnPrestamo = new ArrayList<>();
        envasesEnPrestamoOrig = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_nuevo, container, false);
        presenter = new ClienteNuevoPresenter(this);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initializeViewObjects(view);
        setOnClickListeners();

        presenter.getEnvases();

        if(getArguments() != null){
            if(getArguments().getDouble("lat") != 0.0){
                posicionMapa = new LatLng(getArguments().getDouble("lat"), getArguments().getDouble("lng"));
            }
            if(getArguments().getSerializable(KEY_CLIENTE) != null){
                updateCliente = true;
                cliente = (Cliente) getArguments().getSerializable(KEY_CLIENTE);
                clienteOriginal = (Cliente) getArguments().getSerializable(KEY_CLIENTE);
                if(posicionMapa != null){
                    cliente.getDireccion().setCoordLat(String.valueOf(posicionMapa.latitude));
                    cliente.getDireccion().setCoordLon(String.valueOf(posicionMapa.longitude));
                }
                loadClienteData(cliente);
            }
        }

        loadSpinners();

        if(!updateCliente){
            addEditTexts(null);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(cliente != null){
            envasesEnPrestamo = cliente.getEnvasesEnPrestamo();
            envasesEnPrestamoOrig = cliente.getEnvasesEnPrestamo();
            if(envasesEnPrestamo != null){
                for(int i = 0; i < envasesEnPrestamo.size(); i++){
                    addEditTexts(envasesEnPrestamo.get(i));
                }
            }
        }else{
            cliente = new Cliente();
        }

    }

    @Override
    public void saveCliente() {
        cliente.setNombre1(nombre1.getText().toString());
        cliente.setNombre2(nombre2.getText().toString());
        cliente.setNroDocumento(nroDeDoc.getText().toString());
        if(tipoDeDoc.getSelectedItem().equals(TipoDocumento.CI.name())){
            cliente.setTipoDocumento(TipoDocumento.CI.name());
        }else if (tipoDeDoc.getSelectedItem().equals(TipoDocumento.RUT.name())){
            cliente.setTipoDocumento(TipoDocumento.RUT.name());
        }else {
            cliente.setTipoDocumento(TipoDocumento.NA.name());
        }
        cliente.setFechaNacimiento(fechaDeNacimiento);
        //TODO cambiar a fecha de creado
        cliente.setFechaAlta(FechaHelper.getStringDate());
        cliente.setCelular(telefono1.getText().toString());
        cliente.setMail(email.getText().toString());
        String coordLat = "";
        String coordLng = "";
        if(this.posicionMapa != null){
            coordLat = String.valueOf(this.posicionMapa.longitude);
            coordLng = String.valueOf(this.posicionMapa.latitude);
        }
        Direccion direccion = new Direccion(0, this.direccion.getText().toString(),
                coordLat, coordLng, this.telefono2.getText().toString(), this.ciudad.getText().toString(),
                this.departamento.getText().toString(), this.codigoPostal.getText().toString());
        if(updateCliente){
            direccion.setId(cliente.getDireccion().getId());
        }
        cliente.setDireccion(direccion);
        cliente.setObservaciones(observaciones.getText().toString());
        cliente.setDias(dias);
        if(((ListaDePrecio)listaDePreciosSP.getSelectedItem()).getId() != 0){
            cliente.setIdLista(((ListaDePrecio)listaDePreciosSP.getSelectedItem()).getId());
        }

        addEnvases();

        if(updateCliente){
            presenter.updateCliente(cliente, clienteOriginal);
        }else {
            presenter.saveCliente(cliente);
        }
    }

    private void addEnvases(){
        envasesEnPrestamo.clear();
        for (int i = 0; i < envAPrestamoSPs.size(); i++){
            Envase envase = (Envase) envAPrestamoSPs.get(i).getSelectedItem();
            if(envase.getId() != 0){
                //TODO manejar los que ya existian
                EnvaseEnPrestamo envaseEnPrestamo =
                        new EnvaseEnPrestamo(0, envase, Integer.valueOf(envAPrestamoCantETs.get(i).getText().toString()));
                long updateId = presenter.getIdIfUpdateDeEnvase(envasesEnPrestamoOrig, envaseEnPrestamo);
                if(updateId > 0){
                    envaseEnPrestamo.setId(updateId);
                }
                envasesEnPrestamo.add(envaseEnPrestamo);
            }
        }
        cliente.setEnvasesEnPrestamo(envasesEnPrestamo);
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
            case R.id.boton_guardar:
                saveCliente();
                break;
            case R.id.domingo_rb:
                addRemoveDia(!domingo.isSelected(), Dia.DOMINGO);
                break;
            case R.id.lunes_rb:
                addRemoveDia(!lunes.isSelected(), Dia.LUNES);
                break;
            case R.id.martes_rb:
                addRemoveDia(!martes.isSelected(), Dia.MARTES);
                break;
            case R.id.miercoles_rb:
                addRemoveDia(!miercoles.isSelected(), Dia.MIERCOLES);
                break;
            case R.id.jueves_rb:
                addRemoveDia(!jueves.isSelected(), Dia.JUEVES);
                break;
            case R.id.viernes_rb:
                addRemoveDia(!viernes.isSelected(), Dia.VIERNES);
                break;
            case R.id.sabado_rb:
                addRemoveDia(!sabado.isSelected(), Dia.SABADO);
                break;
            default:
                break;
        }
    }

    @Override
    public void setEnvases(List<Envase> envases) {
        if(envases != null && envases.get(0) != null && envases.get(0).getId() != 0){
            envases.add(0, new Envase(0, "Nuevo envase a prestamo..."));
        }
        this.envases = envases;
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToClienteFragment(Cliente cliente) {
        Bundle b = new Bundle();
        b.putSerializable(KEY_CLIENTE, cliente);
        ClienteFragment clienteFragmentf = new ClienteFragment();
        clienteFragmentf.setArguments(b);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cliente_lista_layout, clienteFragmentf)
                .commit();
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
        listaDePreciosSP = view.findViewById(R.id.lista_precio_spin);
        envAPrestamoLO = view.findViewById(R.id.env_prestamo_lo);
        agregarEnvPrestamo = view.findViewById(R.id.agreagr_env_prestamo);
        observaciones = view.findViewById(R.id.comentario_et);
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
        guardar.setOnClickListener(this);
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

        domingo.setOnClickListener(this);
        lunes.setOnClickListener(this);
        martes.setOnClickListener(this);
        miercoles.setOnClickListener(this);
        jueves.setOnClickListener(this);
        viernes.setOnClickListener(this);
        sabado.setOnClickListener(this);
    }

    private void loadSpinners(){
        List<String> list = new ArrayList<>();
        list.add(TipoDocumento.CI.name());
        list.add(TipoDocumento.RUT.name());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoDeDoc.setAdapter(adapter);

        if(DataHolder.getListasDePrecios() != null && DataHolder.getListasDePrecios().size() > 0){
            this.listasDePrecio = DataHolder.getListasDePrecios();
            if(this.listasDePrecio != null && this.listasDePrecio.get(0) != null
                    && this.listasDePrecio.get(0).getId() != 0){
                this.listasDePrecio.add(0, new ListaDePrecio(0, "Elija una lista de precios...", "", null));
            }
            ListaDePreciosSpinAdapter adapterListaPrecios =
                    new ListaDePreciosSpinAdapter(getActivity(),this.listasDePrecio);
            listaDePreciosSP.setAdapter(adapterListaPrecios);
            if(cliente != null && cliente.getIdLista() > 0){
                boolean encontre = false;
                ListaDePrecio listaCliente = null;
                for (int i = 0; i < this.listasDePrecio.size() && !encontre; i++){
                    if(this.listasDePrecio.get(i).getId() == cliente.getIdLista()){
                        encontre = true;
                        listaCliente = this.listasDePrecio.get(i);
                    }
                }
                if(listaCliente != null){
                    listaDePreciosSP.setSelection(adapterListaPrecios.getPosition(listaCliente));
                }
            }
        }
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


    private void navigateToClienteNuevoMapaFragment() {
        addEnvases();
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
            envaseCantET.setText(String.valueOf(envaseEnPrestamo.getCantidad()));
        }

        if(envaseEnPrestamo == null){
            envAPrestamoSPs.add(envaseSp);
            envAPrestamoCantETs.add(envaseCantET);
        }
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
        if(cliente.getNombre1() != null) nombre1.setText(cliente.getNombre1());
        if(cliente.getNombre2() != null) nombre2.setText(cliente.getNombre2());
        if(cliente.getNroDocumento() != null) nroDeDoc.setText(cliente.getNroDocumento());
        if(cliente.getTipoDocumento().equals(TipoDocumento.RUT.name())){
            tipoDeDoc.setSelection(1);
            usuarioImage.setImageResource(R.drawable.factory_2);
            casaImage.setImageResource(R.drawable.factory_2);
        }
        if(cliente.getFechaNacimiento() != null){
            fechaDeNacimiento = cliente.getFechaNacimiento();
            fecDeNac.setText(FechaHelper.getFechParaMostrar(cliente.getFechaNacimiento()));
        }
        if(cliente.getCelular() != null) telefono1.setText(cliente.getCelular());
        if(cliente.getDireccion().getTelefono() != null) telefono2.setText(cliente.getDireccion().getTelefono());
        if(cliente.getMail() != null) email.setText(cliente.getMail());
        if(cliente.getDireccion().getDireccion() != null) direccion.setText(cliente.getDireccion().getDireccion());
        if(cliente.getDireccion().getCiudad() != null) ciudad.setText(cliente.getDireccion().getCiudad());
        if(cliente.getDireccion().getDepartamento() != null) departamento.setText(cliente.getDireccion().getDepartamento());
        if(cliente.getDireccion().getCodPostal() != null) codigoPostal.setText(cliente.getDireccion().getCodPostal());
        if(cliente.getObservaciones() != null) observaciones.setText(cliente.getObservaciones());
        envasesEnPrestamo = cliente.getEnvasesEnPrestamo();
        envasesEnPrestamoOrig = cliente.getEnvasesEnPrestamo();

//        for(int i = 0; i < envasesEnPrestamo.size(); i++){
//            addEditTexts(envasesEnPrestamo.get(i));
//        }
        List<Dia> diasTemp = cliente.getDias();
        for(int i  =0; i < diasTemp.size(); i++){
            selectDias(diasTemp.get(i), true);
        }
        if(cliente.getDireccion().getCoordLat() != null &&
                !cliente.getDireccion().getCoordLat().isEmpty()){
            posicionMapa = new LatLng(Double.valueOf(cliente.getDireccion().getCoordLat()),
                    Double.valueOf(cliente.getDireccion().getCoordLon()));
        }
    }


    private void selectDias(Dia dia, boolean isChecked){
        switch (dia){
            case DOMINGO:
                domingo.setSelected(isChecked);
                domingo.setChecked(isChecked);
                break;
            case LUNES:
                lunes.setSelected(isChecked);
                lunes.setChecked(isChecked);
                break;
            case MARTES:
                martes.setSelected(isChecked);
                martes.setChecked(isChecked);
                break;
            case MIERCOLES:
                miercoles.setSelected(isChecked);
                miercoles.setChecked(isChecked);
                break;
            case JUEVES:
                jueves.setSelected(isChecked);
                jueves.setChecked(isChecked);
                break;
            case VIERNES:
                viernes.setSelected(isChecked);
                viernes.setChecked(isChecked);
                break;
            case SABADO:
                viernes.setSelected(isChecked);
                sabado.setChecked(isChecked);
                break;
            default:
                break;
        }
    }

    private void updateLabel() {
        fechaDeNacimiento = FechaHelper.getFechaParaEnviar(calendar.getTime());
        fecDeNac.setText(FechaHelper.getFechParaMostrar(calendar.getTime()));
    }


    private void addRemoveDia(boolean add, Dia dia){
        if(add){
            selectDias(dia, true);
            dias.add(dia);
        }else{
            selectDias(dia, false);
            dias.remove(dia);
        }
    }
}
