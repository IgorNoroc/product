<h2>Rest Api получения продукта по разным атрибутам!</h2></br>
При создании приложения использованы технологии:</br>
<ul>
<li>Spring(Boot, Data, Security, SpringFox, Web)</li>
<li>Liquibase</li>
<li>Swagger2</li>
<li>VCS Git</li>
<li>Java 14</li>
<li>Apache Maven</li>
<li>PostgreSQL</li>
<li>Hibernate</li>
<li>Lombok</li>
</ul>

Миграция сделана при помощи liquibase, так что для запуска проекта нужно только:</br>
- заранее создать базу данных: product_database
- задать свои имя и пароль для базы данных:</br>
- <i>spring.datasource.username=ваше имя пользователя PostgreSql, </br>
- spring.datasource.password=ваш пароль для базы данных.</i></br>
Для работы с методами админа, нужно авторизоваться по:</br>
- localhost:8080/api/admin/login (method POST: content type: application/json) </br>
- login : admin
- password : admin</br>
<b>Более подробная документация будет доступна после запуска по : http://localhost:8080/swagger-ui.html </b></br>


![Image alt](https://github.com/IgorNoroc/product/blob/master/src/main/resources/img/product-docs.png)