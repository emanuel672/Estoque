package Base;

public class Produtos {
    private int ID;
	private String Nome;
	private int Categoria;
	private float Preco;
	private int Quantidade;
	private String Modelo; 
	private String Cor; 

public Produtos(int ID, String Nome, String Modelo, String Cor, int Categoria, float Preco, int Quantidade  )
    {
    this.ID = ID;
    this.Nome = Nome;
    this.Categoria = Categoria;
    this.Preco = Preco;
    this.Quantidade = Quantidade;
    this.Modelo = Modelo;
    this.Cor = Cor;
    }
    public int getID(){
        return ID;
    }
    public void getID(int ID){
        this.ID = ID;
    }
    public String getNome(){
        return Nome;
    }
    public void getNome(String Nome){
        this.Nome = Nome;
    }
    public int getCategoria(){
        return Categoria;
    }
    public void getCategoria(int Categoria){
        this.Categoria = Categoria;
    }
    public float getpreco(){
        return Preco;
    }
    public void getpreco(float Preco){
        this.Preco = Preco;
    }
    public int getquantidade(){
        return Quantidade;
    }
    public void getquantidade(int Quantidade){
        this.Quantidade = Quantidade;
    }
    public String getmodelo(){
        return Modelo;
    }
    public void getmodelo(String Modelo){
        this.Modelo = Modelo;
    }
    public String getCor() {
    	return Cor;
    }
    public void getCor(String Cor) {
    	this.Cor = Cor;
    }
    
    @Override
	public String toString() {
		return ",\n Nome= " +Nome+ 
				",\n Categoria= " +Categoria+ 
				",\n preco= " +Preco+ 
				",\n quantidade= " +Quantidade+ 
				",\n modelo= " +Modelo+
				",\n Obeservação= "+Cor+ ":\n";
	}

}