package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {
    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Wesll\\driver\\chromedriver_v123\\chromedriver.exe");
        this.navegador = new ChromeDriver();
        this.navegador.manage().window().maximize();
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.navegador.get("http://165.227.93.41/lojinha-web/v2");
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {

        //Fazer o login
        navegador.findElement(By.cssSelector("label[for=usuario]")).click();
        navegador.findElement(By.id("usuario")).sendKeys("admin");
        navegador.findElement(By.cssSelector("label[for=senha]")).click();
        navegador.findElement(By.id("senha")).sendKeys("admin");
        navegador.findElement(By.cssSelector("button[type=submit]")).click();

        //Abrir tela de produto
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        //Preencher formulário de um novo produto
        navegador.findElement(By.id("produtonome")).sendKeys("Notebook Samsung E300");
        navegador.findElement(By.id("produtovalor")).sendKeys("000");
        navegador.findElement(By.id("produtocores")).sendKeys("Preto");

        //Submeter o formulário
        navegador.findElement(By.cssSelector("button[type=submit]")).click();

        //Vou validar a mensagem
        String mensagem = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagem);

    }

    @AfterEach
    public void afterEach() {
        navegador.quit();
    }
}
//Outros jeitos de clicar no botão:
//navegador.findElement(By.tagName("button")).click();
//navegador.findElement(By.name("action")).click();

