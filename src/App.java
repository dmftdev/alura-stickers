import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair/parsear só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        // System.out.println(listaDeFilmes.size());
        // System.out.println(listaDeFilmes.get(0));

        // exibir e manipular os dados
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs_2.jpg").openStream();
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("Título: " + "\u001b[31m" + filme.get("title") + "\u001b[m");
            System.out.println("Cartaz: " + "\u001b[4m" + filme.get("image") + "\u001b[m");
            var nota = filme.get("imDbRating");            
            var notaInteger = Math.round(Float.parseFloat(nota));
            System.out.println("Nota: " + "\u001b[1m" + nota + "\u001b[m");
            System.out.println(String.join("", Collections.nCopies(notaInteger, "⭐️")));
            
        }

    }
    
}
