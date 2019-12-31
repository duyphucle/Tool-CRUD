import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BConfig {
    public void createConfig() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();


        Path path = Paths.get(AMain.link + "ApplicationConfig.java");

        String question = "package com.codegym.cms;\n";
        for (int i = 0; i < dtp.tableSize(); i++) {
            question += "import com.codegym.cms.service." + dtp.table(i) + "Service;\n" +
                    "import com.codegym.cms.formatter." + dtp.table(i) + "Formatter;\n" +
                    "import com.codegym.cms.service.impl." + dtp.table(i) + "ServiceImpl;\n";

        }
        question +=
                "import org.springframework.beans.BeansException;\n" +
                        "import org.springframework.beans.factory.annotation.Qualifier;\n" +
                        "import org.springframework.context.ApplicationContext;\n" +
                        "import org.springframework.context.ApplicationContextAware;\n" +
                        "import org.springframework.context.MessageSource;\n" +
                        "import org.springframework.context.annotation.Bean;\n" +
                        "import org.springframework.context.annotation.ComponentScan;\n" +
                        "import org.springframework.context.annotation.Configuration;\n" +
                        "import org.springframework.context.support.ResourceBundleMessageSource;\n" +
                        "import org.springframework.data.jpa.repository.config.EnableJpaRepositories;\n" +
                        "import org.springframework.format.FormatterRegistry;\n" +
                        "import org.springframework.jdbc.datasource.DriverManagerDataSource;\n" +
                        "import org.springframework.orm.jpa.JpaTransactionManager;\n" +
                        "import org.springframework.orm.jpa.JpaVendorAdapter;\n" +
                        "import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;\n" +
                        "import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;\n" +
                        "import org.springframework.transaction.PlatformTransactionManager;\n" +
                        "import org.springframework.transaction.annotation.EnableTransactionManagement;\n" +
                        "import org.springframework.web.multipart.commons.CommonsMultipartResolver;\n" +
                        "import org.springframework.web.servlet.config.annotation.EnableWebMvc;\n" +
                        "import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;\n" +
                        "import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;\n" +
                        "import org.thymeleaf.TemplateEngine;\n" +
                        "import org.thymeleaf.spring5.SpringTemplateEngine;\n" +
                        "import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;\n" +
                        "import org.thymeleaf.spring5.view.ThymeleafViewResolver;\n" +
                        "import org.thymeleaf.templatemode.TemplateMode;\n" +
                        "\n" +
                        "import javax.persistence.EntityManager;\n" +
                        "import javax.persistence.EntityManagerFactory;\n" +
                        "import javax.sql.DataSource;\n" +
                        "import java.util.Properties;\n" +
                        "\n" +
                        "@Configuration\n" +
                        "@EnableWebMvc\n" +
                        "@EnableTransactionManagement\n" +
                        "@EnableJpaRepositories(\"com.codegym.cms.repository\")\n" +
                        "@ComponentScan(\"com.codegym.cms\")\n" +
                        "public class ApplicationConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {\n" +
                        "\n" +
                        "    private ApplicationContext applicationContext;\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {\n" +
                        "        this.applicationContext = applicationContext;\n" +
                        "    }\n" +
                        " @Bean\n" +
                        "    public SpringResourceTemplateResolver templateResolver(){\n" +
                        "        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();\n" +
                        "        templateResolver.setApplicationContext(applicationContext);\n" +
                        "        templateResolver.setPrefix(\"/WEB-INF/views\");\n" +
                        "        templateResolver.setSuffix(\".html\");\n" +
                        "        templateResolver.setTemplateMode(TemplateMode.HTML);\n" +
                        "        return templateResolver;\n" +
                        "    }\n" +
                        "\n" +
                        "    @Bean\n" +
                        "    public SpringTemplateEngine templateEngine(){\n" +
                        "        SpringTemplateEngine templateEngine = new SpringTemplateEngine();\n" +
                        "        templateEngine.setTemplateResolver(templateResolver());\n" +
                        "        return templateEngine;\n" +
                        "    }\n" +
                        "\n" +
                        "    @Bean\n" +
                        "    public ThymeleafViewResolver viewResolver(){\n" +
                        "        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();\n" +
                        "        viewResolver.setTemplateEngine(templateEngine());\n" +
                        "        return viewResolver;\n" +
                        "    }\n" +
                        "\n" +
                        "    //JPA configuration\n" +
                        "    @Bean\n" +
                        "    @Qualifier(value = \"entityManager\")\n" +
                        "    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {\n" +
                        "        return entityManagerFactory.createEntityManager();\n" +
                        "    }\n" +
                        "\n" +
                        "    @Bean\n" +
                        "    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {\n" +
                        "        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();\n" +
                        "        em.setDataSource(dataSource());\n" +
                        "        em.setPackagesToScan(new String[]{\"com.codegym.cms.model\"});\n" +
                        "\n" +
                        "        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();\n" +
                        "        em.setJpaVendorAdapter(vendorAdapter);\n" +
                        "        em.setJpaProperties(additionalProperties());\n" +
                        "        return em;\n" +
                        "    }\n" +
                        "\n" +
                        "    @Bean\n" +
                        "    public DataSource dataSource(){\n" +
                        "        DriverManagerDataSource dataSource = new DriverManagerDataSource();\n" +
                        "        dataSource.setDriverClassName(\"com.mysql.cj.jdbc.Driver\");\n" +
                        "        dataSource.setUrl(\"jdbc:mysql://localhost:3306/" + AMain.dbName2 + "\");\n" +
                        "        dataSource.setUsername( \"" + AMain.userName + "\" );\n" +
                        "        dataSource.setPassword( \"" + AMain.password + "\" );\n" +
                        "        return dataSource;\n" +
                        "    }\n" +
                        "\n" +
                        "    @Bean\n" +
                        "    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){\n" +
                        "        JpaTransactionManager transactionManager = new JpaTransactionManager();\n" +
                        "        transactionManager.setEntityManagerFactory(emf);\n" +
                        "        return transactionManager;\n" +
                        "    }\n" +
                        "\n" +
                        "    Properties additionalProperties() {\n" +
                        "        Properties properties = new Properties();\n" +
                        "        properties.setProperty(\"hibernate.hbm2ddl.auto\", \"update\");\n" +
                        "        properties.setProperty(\"hibernate.dialect\", \"org.hibernate.dialect.MySQL5Dialect\");\n" +
                        "        return properties;\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void addFormatters(FormatterRegistry registry) {\n";
        for (int i = 0; i < dtp.tableSize(); i++) {
            question += "        registry.addFormatter(new " + dtp.table(i) + "Formatter(applicationContext.getBean(" + dtp.table(i) + "Service.class)));\n";
        }
        question += "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void addResourceHandlers(ResourceHandlerRegistry registry) {\n" +
                "        registry.addResourceHandler(\"/sb2/**\")\n" +
                "                .addResourceLocations(\"/sb2/\").resourceChain(false);\n" +
                "\n" +
                "        registry.addResourceHandler(\"/upload/**\")\n" +
                "                .addResourceLocations(\"/upload/\").resourceChain(false);\n" +
                "    }\n" +
                "\n" +
                "    @Bean(name = \"multipartResolver\")\n" +
                "    public CommonsMultipartResolver multipartResolver() {\n" +
                "        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();\n" +
                "        multipartResolver.setMaxUploadSize(1000000);\n" +
                "        return multipartResolver;\n" +
                "    }\n" +
                "\n" +
                "    @Bean\n" +
                "    public MessageSource messageSource() {\n" +
                "        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();\n" +
                "        messageSource.setBasenames(\"ValidationMessages\");\n" +
                "        return messageSource;\n" +
                "    }\n";

        for (int i = 0; i < dtp.tableSize(); i++) {
            question += "    @Bean\n" +
                    "           public " + dtp.table(i) + "Service " + dtp.table(i).toLowerCase() + "Service(){\n" +
                    "               return new " + dtp.table(i) + "ServiceImpl();\n" +
                    "           }\n";
        }
        question += "}";


        Charset charset = Charset.forName("ISO-8859-1");
        try {
            Files.write(path, question.getBytes());
            System.out.println("Config created by Le Duy Phuc");
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
