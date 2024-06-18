package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {
    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Wesll\\driver\\chromedriver_v123\\chromedriver.exe");
        this.navegador = new ChromeDriver();
        this.navegador.manage().window().maximize();
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.navegador.get("http://165.227.93.41/lojinha-web/v2");
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {

        //Fazer o login
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("nome_do_usuario")
                .informarSenha("senha_do_usuario")
                .submeterFormulario()
                .acessarFormularioDeAdicaoNovoProduto()
                .informarNomeDoProduto("Samsung Book E400")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("Preto")
                .submeterFormularioAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Não é permitido registar um produto que o valor maior que 7.000,00")
    public void testNaoEPermitidoRegistrarValoresAcimaDeSeteMil() {
        String mensagemApresentada = new LoginPage(navegador).
                informarUsuario("nome_do_usuario")
                .informarSenha("senha_do_usuario")
                .submeterFormulario()
                .acessarFormularioDeAdicaoNovoProduto()
                .informarNomeDoProduto("Samsung Galaxy S24")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("Preto, roxo")
                .submeterFormularioAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam na faixa de 0,01 a 7.000,00")
    public void testPossoAdicionarProdutosComValorDeUmCentavoASeteMilReais() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("nome_do_usuario")
                .informarSenha("senha_do_usuario")
                .submeterFormulario()
                .acessarFormularioDeAdicaoNovoProduto()
                .informarNomeDoProduto("Samsung Galaxy M50")
                .informarValorDoProduto("190000")
                .informarCoresDoProduto("Azul, Branco, Preto")
                .submeterFormularioAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 0,01")
    public void testPossoAdicionarProdutosComValorDeUmCentavo() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("nome_do_usuario")
                .informarSenha("senha_do_usuario")
                .submeterFormulario()
                .acessarFormularioDeAdicaoNovoProduto()
                .informarNomeDoProduto("Chiclete")
                .informarValorDoProduto("001")
                .informarCoresDoProduto("Rosa")
                .submeterFormularioAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 0,01")
    public void testPossoAdicionarProdutosComValorDeSeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("nome_do_usuario")
                .informarSenha("senha_do_usuario")
                .submeterFormulario()
                .acessarFormularioDeAdicaoNovoProduto()
                .informarNomeDoProduto("Samsung Galaxy S23")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("Rosa")
                .submeterFormularioAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @AfterEach
    public void afterEach() {
        navegador.quit();
    }
}
//Outros jeitos de clicar no botão:
//navegador.findElement(By.tagName("button")).click();
//navegador.findElement(By.name("action")).click();



