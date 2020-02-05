package webapp;

import webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTest {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");
        resume.addContact(ContactsType.TELEPHONE, "+7(921) 855-0482");
        resume.addContact(ContactsType.SKYPE, "grigory.kislin");
        resume.addContact(ContactsType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactsType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactsType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        List<String> strings = new ArrayList<>();
        strings.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        strings.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        strings.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        strings.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        strings.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        strings.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(strings));
        List<String> strings2 = new ArrayList<>();
        strings2.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        strings2.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        strings2.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        strings2.add("MySQL, SQLite, MS SQL, HSQLDB");
        strings2.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        strings2.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        strings2.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        strings2.add("Python: Django.");
        strings2.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        strings2.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        strings2.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        strings2.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        strings2.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        strings2.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        strings2.add("Родной русский, английский \"upper intermediate\"");
        resume.addSection(SectionType.QUALIFICATION, new ListSection(strings2));
        List<Organization> organizations = new ArrayList<>();
        organizations.add(new Organization("http://javaops.ru", "Java Online Projects", LocalDate.of(2013, 10, 1), LocalDate.now(), "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок."));
        organizations.add(new Organization("https://www.wrike.com", "Wrike", LocalDate.of(2014, 10,1), LocalDate.of(2016, 1, 1), "\tСтарший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        organizations.add(new Organization("", "RIT Center", LocalDate.of(2012, 4,1), LocalDate.of(2014, 10, 1), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"));
        organizations.add(new Organization("https://www.luxoft.com", "Luxoft (Deutsche Bank)", LocalDate.of(2012, 10,1), LocalDate.of(2012, 4, 1), "\tВедущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
        organizations.add(new Organization("https://www.yota.ru", "Yota", LocalDate.of(2008, 6,1), LocalDate.of(2010, 12, 1), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));
        organizations.add(new Organization("https://www.pega.com", "Enkata", LocalDate.of(2007, 3,1), LocalDate.of(2008, 6, 1), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."));
        organizations.add(new Organization("https://new.siemens.com", "Siemens AG", LocalDate.of(2005, 1,1), LocalDate.of(2007, 2, 1), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        organizations.add(new Organization("http://www.alcatel.ru", "Alcatel", LocalDate.of(1997, 9,1), LocalDate.of(2005, 1, 1), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationListSection(organizations));
        List<Organization> eduList = new ArrayList<>();
        eduList.add(new Organization("https://www.coursera.org", "Coursera", LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1), "\"Functional Programming Principles in Scala\" by Martin Odersky", ""));
        eduList.add(new Organization("http://www.luxoft-training.ru", "Luxoft", LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", ""));
        eduList.add(new Organization("http://www.siemens.ru", "Siemens", LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1), "3 месяца обучения мобильным IN сетям (Берлин)", ""));
        eduList.add(new Organization("http://www.alcatel.ru", "Alcatel", LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1), "\t6 месяцев обучения цифровым телефонным сетям (Москва)", ""));
        eduList.add(new Organization("http://www.ifmo.ru", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1), "Аспирантура (программист С, С++)", ""));
        eduList.add(new Organization("http://www.ifmo.ru", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), "Аспирантура (программист С, С++)", ""));
        eduList.add(new Organization("http://www.school.mipt.ru", "Заочная физико-техническая школа при МФТИ", LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1), "Закончил с отличием", ""));
        resume.addSection(SectionType.EDUCATION, new OrganizationListSection(eduList));
        System.out.println(resume);
    }
}
