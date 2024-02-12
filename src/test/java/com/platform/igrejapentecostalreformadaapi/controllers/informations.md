<h1>
   Teste de controllers
</h1>


## 🚀 @WebMvcTest Annotation
- O Spring boot fornece a anotação @WebMvcTest para testar controllers
Spring MVC. Além disso, os testes baseados em @WebMvcTest são mais
rápidos, pois carregam apenas o controller especificado e suas dependências,
sem carregar toda a aplicação.
- O Spring boot instancia apenas a camada web em vez de todo o application context.
Em uma aplicação com vários controllers, você pode definir a instanciação de apenas
um deles usando, por exemplo, o @WebMvcTest(PersonController.class).
- 
## 🚀 @WebMvcTest x @SpringBootTest
- O Spring boot fornece a anotação @SpringBootTest para testes de integração.
Ela carrega um application context completo.