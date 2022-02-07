package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> allVacancies = new ArrayList<>();

        int page = 0;
        try {
            while (true) {
                final Document doc = getDocument(searchString, page);

                final Elements vacanciesHtmlList = doc.getElementsByAttributeValue("data-qa",
                        "vacancy-serp__vacancy");

                if (vacanciesHtmlList.isEmpty()) {
                    break;
                }

                for (Element element : vacanciesHtmlList) {
                    final Elements links = element
                            .getElementsByAttributeValue("data-qa",
                                    "vacancy-serp__vacancy-title");
                    final Elements locations = element
                            .getElementsByAttributeValue("data-qa",
                                    "vacancy-serp__vacancy-address");
                    final Elements companyName = element
                            .getElementsByAttributeValue("data-qa",
                                    "vacancy-serp__vacancy-employer");
                    final Elements salary = element
                            .getElementsByAttributeValue("data-qa",
                                    "vacancy-serp__vacancy-compensation");
                    final Vacancy vacancy = new Vacancy();

                    vacancy.setSiteName("hh.ru");
                    vacancy.setTitle(links.get(0).text());
                    vacancy.setUrl(links.get(0).attr("href"));
                    vacancy.setCity(locations.get(0).text());
                    vacancy.setCompanyName(companyName.get(0).text());
                    vacancy.setSalary(salary.size() > 0 ? salary.get(0).text() : "");
                    allVacancies.add(vacancy);
                }
                page++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allVacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                .referrer("https://hh.ru/")
                .get();
    }
}
