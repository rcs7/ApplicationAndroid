/*Abra o android studio e faça um novo projeto

CadProdutos, API 19(que é SDK). Escolho Activity empty.

Criado o projeto!
Vou em layout e as demais aulas serão mais back end.
É na pasta res->layout->activity_main.xml na parte de Design.
Aí pego um Linear layout vertical e arrasto pra dentro da tela.
Dps pesquiso ListView e arrasto e tb coloco dentro da tela. E lá
no ID coloco listview_Produtos. E aí já aparece a lista de itens lá na tela.

Lá na pasta values -> string
mudo o nome da minha aplicaçao, na linha de código que tem app_name
coloco lá >Cadastro de Produtos<.
Dps vou lá em values->color e clico em cima das cores e mudo pra que eu quiser.
Lá embaixo dos itens coloco um Button e coloco o ID btn_Cadastrar e troco lá
o text dele que tava Button para Cadastrar. E tb lá nos tributos deste Button,
vou tb em style e escolho Widget.AppCompat.Button que é um layout bonitinho para
ele.


New-> Activity->Activity Empty. Coloco o nome dela FormularioProduto. E clico em Finish.
É na pasta res->layout->activity_formulario_produtos.xml na parte de Design.
Aí vou lá de novo na parte de layout de Cad_Produtos, sendo que é a tela de Formulário
e coloco um LinearLayout Vertical, arrasto e coloco dentro. Dps coloc 3 Plain Text, 
um embaixo do outro pra que a pessoa insira os dados. E em baixo coloco um Button.
Mudo o ID do primeiro Plan Text para editText_NomeProd, aí tiro lá em baixo o Name que é 
o que ficava aparecendo por trás dentro da caixinha de texto. Ou seja o Text, lá da 
parte Text View. E coloco um hint : Inserir nome(que fica lá trás transparente e sai qd 
ele começar a digitar). Na outra Plan Text coloco um hint: Descrição do Produto, apago 
o text nome, e coloco o ID dele como editText_Descricao. Na outra Plan Text coloco um 
hint: Quantidade. E coloco o ID dele como editText_Quantidade. 
Clico no Button e mudo o ID dele para btn_poliform. E coloco o Text dele que antes tava 
Button para btn.

Agr compilo só  pra ver a tela.

Vou na pasta app e dps no apcote java e em cima de cadprodutos.cursoaap.com.cadproduto 
clico com o botão direito e crio um novo package chamado model, e ele ficará dentro desse
package cadprodutos.cursoaap.com.cadproduto e dentro dele estará as classes que criei 
anteriormente: FormularioProdutos e MainActivity. E em cima desse package model, crio uma
nova class chamada Produtos.
E é nessa classe que iremos definir os nossos atributos:*/
package cadprodutos.cursoaap.com.cadproduto.model;
//essa parte de por implements Serializable inibe o erro na parte do put da MainActivity lá em baixo
public class Produtos implements Serializable{
	private Long id;
	private String nomeProduto;
	private String descricao;
	private int quantidade;
	
	/*clico em ALt+enter pra definir os métodos getters and setters, clicando em Getter
	and Setter e aperto Cltr + a para selecionar todas as opções que foram mostradas e dou ok.
	E então vai aparecer tudo isso aí de baixo:*/
	@Override
	public String toString(){
		return nomeProduto.toString();
	}
	
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getNomeProduto(){
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto){
		this.nomeProduto = nomeProduto;
	}
	public String getDescricao(){
		return descricao;
	}
	
/*agr vou criar outro package dentro do package java e em cima de cadprodutos.cursoaap.com.cadproduto
com o botão direito crio um new package com o nome BDHelper e ok.
Esse package criado está vazio e agr crio em cima dele uma java class chamada 
ProdutosBd e lá nela escrevo extends SQLiteOpenHelper e aí aparece uma opção lá
pra selecionar os métodos onCreate e onUpdate e aí deixo os dois selecionados e aperto ok,
então eles são criados. E dps destes dois métodos criados ele pede pra vc construir os 
construtores, mas vc pode clicar na lâmpada lá e tem Create constructor matching super.
E aí ele sugere duas formas para criação deste construtor, mas eu escolho a primeira, mas
eu nao aperto ok, pq eu vou querer fazer pessoalmente, na unha.*/
package cadprodutos.cursoaap.com.cadprodutos.BDHelper;

//Esses imports foram criados qd criei o extend pra SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProdutosBd extends SQLiteOpenHelper{
	
	///////////////começo da criação do banco de dados///////////////////////////
	//vai ser o nome do meu banco de dados
	private static final String DATABASE = "bdprodutos";
	private static final int VERSION = 1;
	
	/*criando manualmente o Construtor(que tem que ter o mesmo nome da class):
	*/public ProdutosBd(Context context){
		super(context, DATABASE, null, VERSION);	
	}
	/////// final da criação do banco de dados/////////////////////

	@Override /*eu defini automáticamente*/
	public void onCreate(SQLiteDatabase db){
		/*eu defini manualmente*/
		/*Query = comando SQL*/
		String produto = "CREATE TABLE produtos(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
		nomeProduto TEXT NOT NULL,descricao TEXT NOT NULL, quantidade INTEGER);";
		/*agr é a parte de comando para que a Query de cima possa ser executada:*/
		db.execSQL(produto);
	}
	
	@Override /*eu defini automáticamente*/
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		/*eu defini manualmente*/
		String produto = "DROP TABLE IF EXISTS produtos";
		db.execSQL(produto);
	}
	
	//aqui salva
	public void salvarProduto(Produtos produto){
		ContentValues values = new ContentValues();
		values.put("nomeproduto",produto.getNomeProduto());
		values.put("descricao",produto.getDescricao());
		values.put("quantidade",produto.getQuantidade());
		
		/*compactação numa variável única trazendo uma variável que já tem valores
		definidos, pois se eu trouxesse cada valor ficaria mt feio.*/
		
		getWritableDatabase().insert("produtos",null, values);
	}
    //#Retorno pra essa parte do código lá em baixo
	//Alterar
	public void alterarProduto(Produto produto){
		
		ContentValues values = new ContentValues();
		
		values.put("nomeProduto", produto.getNomeProduto());
		values.put("descrição", produto.getDescricao());
		values.put("quantidade", produto.getQuantidade());
		
		String [] args = [produto.getId().toString()];
		getWritableDatabase().update("produtos",values,"id=?",args);
		
	}	
	//e
	//Deletar
	public void deletarProduto(Produto produto){
		String [] args = [produto.getId().toString()];
		getWritableDatabase().delete("produto","id=?",args);
	}
	//obs que até o momento os métodos deletarProduto e alterarProduto
	//estão cinza, pois não estão sendo utilizados.
	
	///////////continuo daq: ///
	//Lista - mostrar
	//Produtos é a classe que eu criei
	public ArrayList<Produtos> getLista(){
		String [] columns = {"id","nomeproduto","descricao","quantidade"};
		//parte de cursor pra estar salvando nossa lista
		//preciso de 6 null, pois estou fazendo um select completo, não preciso de where
		Cursor cursor = getWritableDatabase().query("produtos",null,null,null,null,null,null);
		ArrayList<Produtos> produtos = new ArrayList<Produtos>();
		//pra que ele possa passar pra um próximo registro
		while(cursor.moveToNext()){
			Produtos produto = new Produtos();
			produto.setId(cursor.getLong(0));
			produto.setNomeProduto(cursor.getString(1));
			produto.setDescricao(cursor.getString(2));
			produto.setQuantidade(cursor.getInt(3));
		
			//pra inserir dentro do Array
			produtos.add(produto);
		}
		
		return produtos;
		
	}
 }	
}

/*Implementação da parte do botão cadastrar pra poder chamar o formulário do produto que fiz.
app->res->layout->activity_main.xml, vou criar uma intenção por meio do button cadastrar*/
//parte que já tinha lá na activity_main.xlm:
package cadprodutos.cursoaap.com.cadprodutos;

public class MainActivity extends AppCompatActivity{
	@Override
	protect void onCreate(Bundle saveInstanceState){
		super.onCreate(saveInstanceState);
		setContentView(R.layout.activity_main);
	}
}

/*Agr eu vou add o que tem que ser adicionado*/

package cadprodutos.cursoaap.com.cadprodutos;

public class MainActivity extends AppCompatActivity{
	@Override
	protect void onCreate(Bundle saveInstanceState){
		super.onCreate(saveInstanceState);
		setContentView(R.layout.activity_main);
	
		Button btnCadastrar = (Button) findViewById(R.id.btn_Cadastrar);
		btnCadastrar.setOnClickListener(new android.view.View.OnClickListener(){
			public void onClick(View v){
			    //                       (pontoDePartida,class que criei que é o ponto de chegada)                   
				Intent intent = new Intent(MainActivity.this, FormularioProdutos.class);
				startActivity(intent);
			}
		});
	}
}

/*Vou implementar agr a parte do FormularioProduto, lá na 
activity_formulario_produtos.xml:*/
//a class era assim:
package cadprodutos.cursoaap.com.cadprodutos;

import...

public class FormularioProdutos extends AppCompatActivity{
	
	@Override
	protect void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_produtos);
		
	}
}

//Agr vou add o que tenho que add:
package cadprodutos.cursoaap.com.cadprodutos;

import...

public class FormularioProdutos extends AppCompatActivity{
	EditText editText_NomeProd, editText_Descricao, editText_Quantidade;
	Button btn_Poliform;
	//pra fazer um if , pra saber se esse produto vai ser editável ou não.
	Produtos editarProduto, produto;
	ProdutosBd bdHelper;
	
	@Override
	protect void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_produtos);
		
		/*pule isso e só venha pra cá qd mandar lá em baixo*/
		/* ## */
		produto = new Produtos();
		
		
		bdHelper = new ProdutosBd(FormularioProdutos.this);
		
		Intent intent = getIntent();
		editarProduto = (Produtos) intent.getSerializableExtra("produto-escolhido");
		
		editText_NomeProd = (EditText) findViewById(R.id.editText_NomeProd); 
		editText_Descricao = (EditText) findViewById(R.id.editText_Descricao);
		editText_Quantidade = (EditText) findViewById(R.id.editTextQuantidade);
	
		btn_Poliform = (Button) findViewById(R.id.btn_poliform);
	
		if(editarProduto != null){
			btn_Poliform.setText("Modificar");
		
		/*veja isso só dps lá embaixo pede pra voltar pra cá!
		comentário ####*/
		//Preciso fazer isso pra poder conseguir alterar esses dados, 
		//ele traz pra vc certinho na tela a parte de alteração.
		editText_NomeProd.setText(editarProduto.getNomeProduto());
		editText_NomeProd.setText(editarProduto.getDescricao());
		editText_NomeProd.setText(editarProduto.getQuantidade()+"");
		//esse +"" é pra eu estar debugando caso precise!
		produto.setId(editarProduto.getId());
		/*posso voltar lá pra baixo onde chamou ####*/
		}
		else{
			btn_Poliform.setText("Cadastrar");
		}
	
		btn_Poliform.setOnClickListener(new View.OnClickListener()){
			@Override
			public void onClick(View v){
				produto.setNomeProduto(editText_NomeProd.getText().toString());
				produto.setDescricao(editText_Descricao.getText().toString());
				produto.setQuantidade(Integer.parseInt(editText_Quantidade.getText().toString());
			
				if(btn_Poliform.getText().toString().equals("Cadastrar")){
					bdHelper.salvarProduto(produto);
					bdHelper.close();
	
				//Retorne pra parte do código lá de cima onde tem Lista - Mostrar e tem um comentário
				// #
				//continuando agr o else daí de cima
			    }else{
					bdHelper.alterarProduto(produto);
					bdHelper.close();
				}
			}
		});
	}

	
/*Agr vou implentar no MainActivity, incluindo o ListView. Obs que antes ela tava assim:
*/

package cadprodutos.cursoaap.com.cadprodutos;

public class MainActivity extends AppCompatActivity{
	@Override
	protect void onCreate(Bundle saveInstanceState){
		super.onCreate(saveInstanceState);
		setContentView(R.layout.activity_main);
	
		Button btnCadastrar = (Button) findViewById(R.id.btn_Cadastrar);
		btnCadastrar.setOnClickListener(new android.view.View.OnClickListener(){
			public void onClick(View v){
			    //                       (pontoDePartida,class que criei que é o ponto de chegada)                   
				Intent intent = new Intent(MainActivity.this, FormularioProdutos.class);
				startActivity(intent);
			}
		});
	}
}
//agr vou alterar colocando coisas:


package cadprodutos.cursoaap.com.cadprodutos;

public class MainActivity extends AppCompatActivity{
	/*add essa parte que não tinha:*/
	ListView lista;
	ProdutosBd bdHelper;
	ArrayList<Produtos> listview_Produtos;
	Produtos produto;
	ArrayAdapter adapter;
	
	@Override
	protect void onCreate(Bundle saveInstanceState){
		super.onCreate(saveInstanceState);
		setContentView(R.layout.activity_main);
	
	    /*add essa parte que não tinha:*/
		lista = (Listview) findViewById(R.id.listview_Produtos);
	           //////////////////	
			   //Comentário #11 que será chamado lá em baixo, venha pra cá só dps!
			   registerForContextMenu(lista);
	
		/*Vê essa parte dps que é pra poder mudar os produtor. Qd tiver o comentário com
         ###*/
		lista.setOnClickListener(new AdpaterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id){
				Produtos produtoEscolhido = (Produtos) adapter.getItemAtPosition(position);
				
				Intent i = new Intent(MainActivity.this, FormularioProdutos.class);
                //por causa desta parte tenho que pôr implements Serializable na class Produtos, 
				//isso inibe o erro
				i.putExtra("produto-escolhido",produtoEscolhido);
				}	
		});
		 
	
		Button btnCadastrar = (Button) findViewById(R.id.btn_Cadastrar);
		btnCadastrar.setOnClickListener(new android.view.View.OnClickListener(){
			public void onClick(View v){
			    //                       (pontoDePartida,class que criei que é o ponto de chegada)                   
				Intent intent = new Intent(MainActivity.this, FormularioProdutos.class);
				startActivity(intent);
			}
		});
	}
	
	/*vá primeiro pra add Primeiro abaixo e dps volte pra cá*/
	protected void onResume(){
		super.onResume();
		carregarProduto();
	}
	
	//Comentário #22 venha pra cá só qd for chamado lá em baixo
	//pressiono e aparece um menu de item que estarei selecionando lá
	lista.setOnClickListener(new AdapterView.OnItemLongClickListener(){
		@Override
		public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id){
			produto = (Produtos)adapter.getItemAtPosition(position);
			return false; //pq nao pode aparecer
		}
	});
	
	@Override
	public void onCreateContextMenu(Context menu, View v, ContextMenu.ContextMenuInfo menuInfo){
		MenuItem menuDelete = menu.add("Delete Este Produto");
		menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
			@Override
			public boolean onMenuItemClick(MenuItem item){
				bdHelper = new ProdutosBd(MainActivity.this);
				bdHelper.deletarProduto(produto);
				bdHelper.close();
				carregarProduto();
				return true;
				
			}
		});
	}
	
	
//	@Override
//	protected void onResume(){
//		super.onResume();
//		carregarProduto();
//	}
	
	/////////////////////////////////////////
	
	/*add Primeiro essa parte que não tinha:*/
	public void carregarProduto(){
		bdHelper = new Produtos(MainActivity.this);
		listview_Produtos = bdHelper().getLista();
      	bdHelper.close();
		
		if(listview_Produtos != null){
			adapter = new ArrayAdapter<Produtos>(MainActivity.this, android.R.layout.simple_list_item_1,listview_Produtos);
		lista.setAdapter(adapter);
		}
	}
	//finish();
}

/*Agr vou lpa em FormularioProduto.java e instancio produto pq ainda faltou isso!
vá em ## lá em cima!*/

/*Vê essa parte que é tem ###, dentro da MainActivity*/

/*Agr vou lá na parte de btn_Polimorf e coloco isso que tá lá
na class FormulárioProduto com o comentário com #### que tá dentro do if*/

/*Criando um menu de contexto que vai aparecer como Long*/
//Vá pra Comentário #11 lá na MainActivity!
			   
/*Procuro a opção de dar um Clean project e clico lá.*/