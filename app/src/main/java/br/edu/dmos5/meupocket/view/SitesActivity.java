package br.edu.dmos5.meupocket.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.dmos5.meupocket.R;
import br.edu.dmos5.meupocket.dao.SiteDao;
import br.edu.dmos5.meupocket.model.Site;

public class SitesActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    //Referência para o elemento de layout.
    private ListView sitesListView;

    //Fonte de dados, essa lista possue os dados que são apresentados
    // na tela dos dispositivo.
    private List<Site> siteList;

    // Um adapter é responsável pela ligação da fonte de dados com o elemento
    // de interface (ListView), é esse objeto que configura a apresentação
    // dos dados na tela do app.
    private ArrayAdapter<Site> siteArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);

        //Recupera a referência do elemento no layout
        sitesListView = findViewById(R.id.list_sites);

        //Carrega a fonte de dados
        siteList = SiteDao.recuperateAll();
        /* Parte - 1
        //Instancia do adapter, aqui configura-se como os dados serão apresentados e também
        // qual a fonte de dados será utilizada.
        siteArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, siteList);
        */
        //agora com NOSSO adapter.
        siteArrayAdapter = new ItemSiteAdapter(this, siteList);

        //Com o adapter pronto, vinculamos ele ao nosso ListView. Após esse comando o
        // ListView já consegue apresentar os dados na tela.
        sitesListView.setAdapter(siteArrayAdapter);

        sitesListView.setOnItemClickListener(this);

        /* Parte - 1
        //Insere-se um listener para os itens da ListView. O método onItemClick() possui um
        // argumento que indica qual o elemento (posição) foi clicado, assim, basta recuperar esse
        // elemento de nossa lista e temos o objeto.
        sitesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), siteList.get(i).getTitulo() + "\n" + siteList.get(i).getEndereco(), Toast.LENGTH_SHORT).show();
            }
        });
         */
    }

    //Como fazer uma chamada para abrir o navegador do celular
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String url = corrigeEndereco(siteList.get(i).getEndereco());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private String corrigeEndereco(String endereco) {
        String url = endereco.trim().replace(" ", "");
        if (!url.startsWith("http://")) {
            return "http://" + url;
        }
        return url;
    }
}
