package org.brunoeleodoro.com.cda;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    ArrayList<Ponto> pontos;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        pontos = (ArrayList<Ponto>) getIntent().getSerializableExtra("pontos");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    public void CadastrarUsuario(String nome, String senha, String rg, String telefone, String email){

        String data = "nome_completo=" + nome
                +"&senha=" + senha
                +"&rg=" + rg
                +"&telefone=" + telefone
                +"&email=" + email;

        new _cadastrarUsuario(this,data).execute("");
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        int i = 0;

        while(i < pontos.size())
        {
            Ponto ponto = pontos.get(i);
            LatLng sydney = new LatLng(ponto.getLat(), ponto.getLng());
            mMap.addMarker(new MarkerOptions().position(sydney).title(ponto.getData()));
            i++;
        }

        LatLng point = new LatLng(-22.9064, -47.0616);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10),2000,null);

    }
}
